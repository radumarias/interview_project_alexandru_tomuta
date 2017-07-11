package interviu.alex.server.service.remote;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import interviu.alex.client.LocationSearchService;
import interviu.alex.server.model.GooglePlacesResponse;
import interviu.alex.server.service.GooglePlacesService;
import interviu.alex.server.service.persistence.LocationDAO;
import interviu.alex.server.service.persistence.PlaceDAO;
import interviu.alex.server.service.persistence.model.LocationEntity;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import interviu.alex.server.service.persistence.model.PhotoEntity;
import interviu.alex.server.service.persistence.model.PlaceEntity;
import interviu.alex.shared.mapper.MyLocationMapper;
import interviu.alex.shared.model.MyLocation;
import interviu.alex.shared.model.googleapi.Location;
import interviu.alex.shared.model.googleapi.Place;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class LocationSearchServiceImpl extends RemoteServiceServlet implements
        LocationSearchService {

    @Inject
    private PlaceDAO placeDAO;

    @Inject
    private LocationDAO locationDAO;

    @Inject
    private MyLocationMapper mapper;

    @Inject
    private GooglePlacesService googlePlacesService;

    public LocationSearchServiceImpl(){}

    @Transactional
    public MyLocation searchByCity(MyLocation input) throws IllegalArgumentException {
        GooglePlacesResponse response = googlePlacesService.queryGoogleForPlaces(input);
        LocationEntity locationEntity = locationDAO.getLocationByName(input.getName());

        checkAndUpdatePlaces(response, locationEntity);
        return mapper.buildLocation(locationEntity);
    }


    private void checkAndUpdatePlaces(GooglePlacesResponse response, LocationEntity locationEntity) {
        if(response == null || locationEntity == null){
            return;
        }

        List<Place> googleApiPlaces = response.getResults();
        googleApiPlaces.forEach(place ->{
            List<PlaceEntity> updatedPlaces = locationEntity.getPlaces().stream()
                    .filter(myPlace -> place.getPlaceId().equals(myPlace.getGooglePlaceId()))
                    .filter(myPlace -> !myPlace.getUserEdited())
                    .map(myPlace -> {
                        myPlace.setAddress(place.getFormattedAddress());
                        myPlace.setGooglePlaceId(place.getPlaceId());
                        myPlace.setLatitude(place.getGeometry().getLocation().getLat());
                        myPlace.setLongitude(place.getGeometry().getLocation().getLng());
                        myPlace.setName(place.getName());
                        myPlace.setType(place.getTypes().stream().collect(Collectors.joining(",")));
                        myPlace.setPhotoList(place.getPhotos().stream()
                                .map(photo -> new PhotoEntity(photo.getPhotoRef(), myPlace)).collect(Collectors.toList()));

                        return myPlace;
                    }).collect(Collectors.toList());
            locationEntity.setPlaces(updatedPlaces);
        });

    }

    public void addNewLocation(MyLocation location){
        if(location == null){
            throw new IllegalArgumentException("Cannot persist null values");
        }
        LocationEntity locationEntity = mapper.buildEntity(location);
        locationDAO.createLocation(locationEntity);
    }

    public void updatePlaces(MyLocation location){
        if(location == null){
            throw new IllegalArgumentException("Cannot persist null values");
        }
        LocationEntity locationEntity = mapper.buildEntity(location);
        locationDAO.createLocation(locationEntity);
    }

}
