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
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class LocationSearchServiceImpl extends RemoteServiceServlet implements
        LocationSearchService {

    @Inject
    private LocationDAO locationDAO;

    @Inject
    private MyLocationMapper mapper;

    @Inject
    private GooglePlacesService googlePlacesService;

    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    public LocationSearchServiceImpl(){}

    @Transactional
    public MyLocation searchByCity(MyLocation input) throws IllegalArgumentException {
        GooglePlacesResponse response = googlePlacesService.queryGoogleForPlaces(input);
        LocationEntity locationEntity = locationDAO.getLocationByName(input.getName());
        // todo if we don't find it in the db, map google response directly to MyLocation. User can dedice to save or not
        // todo and if that's the case, MyLocation will be mapped to locationEntity on request.
        if(locationEntity == null){
            locationEntity = new LocationEntity();
        }
        checkAndUpdatePlaces(response, locationEntity);
        MyLocation location = mapper.buildLocation(locationEntity);
        logger.log(Level.INFO, "Sending to client :\n"+String.valueOf(location)+ "\n\n");
        return location;
    }


    private void checkAndUpdatePlaces(GooglePlacesResponse response, LocationEntity locationEntity) {
        if(response == null){
            return;
        }

        List<Place> googleApiPlaces = response.getResults();
        googleApiPlaces.forEach(place ->{
            // search for place current list places in locationEntity -> if we don't find one, we just map the place to a new placeEntity
            PlaceEntity placeEntity = locationEntity.getPlaces().stream()
                    .filter(placeEnt -> place.getPlaceId().equals(placeEnt.getGooglePlaceId()))
                    .findAny()
                    .orElse(mapper.mapPlaceEntity(place));
            // if the placeEntity found is not edited (dirty flag) and has an ID (already persisted to db)
            if(!placeEntity.getUserEdited() && placeEntity.getId() != 0){
                updateDbVersionWithNewValues(placeEntity, place);
            }
            // if this is a new mapped place -> add it to the locationEntity places list
            if(placeEntity.getId() == 0){
                locationEntity.getPlaces().add(placeEntity);
            }
        });

    }

    private void updateDbVersionWithNewValues(PlaceEntity myPlace, Place place) {
        myPlace.setAddress(place.getFormattedAddress());
        myPlace.setGooglePlaceId(place.getPlaceId());
        myPlace.setLatitude(place.getGeometry().getLocation().getLat());
        myPlace.setLongitude(place.getGeometry().getLocation().getLng());
        myPlace.setName(place.getName());
        myPlace.setType(place.getTypes().stream().collect(Collectors.joining(",")));
        myPlace.setPhotoList(place.getPhotos().stream()
                .map(photo -> new PhotoEntity(photo.getPhotoRef(), myPlace)).collect(Collectors.toList()));
    }

    public void addNewLocation(MyLocation location){
        if(location == null){
            throw new IllegalArgumentException("Cannot persist null values");
        }
        LocationEntity locationEntity = mapper.buildEntity(location);
        locationDAO.persistLocation(locationEntity);
    }

    public void updatePlaces(MyLocation location){
        if(location == null){
            throw new IllegalArgumentException("Cannot persist null values");
        }
        LocationEntity locationEntity = mapper.buildEntity(location);
        locationDAO.updateLocation(locationEntity);
    }

}
