package interviu.alex.shared.mapper;

import interviu.alex.server.service.persistence.model.LocationEntity;
import interviu.alex.server.service.persistence.model.PhotoEntity;
import interviu.alex.server.service.persistence.model.PlaceEntity;
import interviu.alex.shared.model.MyLocation;
import interviu.alex.shared.model.googleapi.Geometry;
import interviu.alex.shared.model.googleapi.Location;
import interviu.alex.shared.model.googleapi.Photo;
import interviu.alex.shared.model.googleapi.Place;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by alexa on 7/11/2017.
 */
public class MyLocationMapper {

    public LocationEntity buildEntity(MyLocation myLocation){
        if(myLocation == null){
            return null;
        }

        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setName(myLocation.getName());
        locationEntity.setLatitude(myLocation.getLatitude());
        locationEntity.setLongitude(myLocation.getLongitude());

        if(myLocation.getPlaceList() != null && !myLocation.getPlaceList().isEmpty()){
            locationEntity.setPlaces(myLocation.getPlaceList().stream()
                    .map(this::mapPlaceEntity)
                    .collect(Collectors.toList()));
            locationEntity.getPlaces().forEach(place -> place.setLocation(locationEntity));
        }

        return locationEntity;
    }

    public PlaceEntity mapPlaceEntity(Place place) {
        if(place == null){
            return null;
        }

        PlaceEntity placeEntity = new PlaceEntity();
        placeEntity.setName(place.getName());
        placeEntity.setType(place.getTypes().stream().collect(Collectors.joining(",")));
        placeEntity.setAddress(place.getFormattedAddress());
        placeEntity.setLatitude(place.getGeometry().getLocation().getLat());
        placeEntity.setLongitude(place.getGeometry().getLocation().getLng());
        //map photos
        Optional.ofNullable(place.getPhotos())
                .ifPresent(photos ->
                        placeEntity.setPhotoList(photos.stream().map(photo -> new PhotoEntity(photo.getPhotoRef()))
                                .collect(Collectors.toList())));
        Optional.ofNullable(placeEntity.getPhotoList())
                .ifPresent(photoEntities ->
                        photoEntities.forEach(photo->photo.setPlace(placeEntity)));
        placeEntity.setGooglePlaceId(place.getPlaceId());
        placeEntity.setUserEdited(place.getUserEdited());

        return placeEntity;
    }


    public MyLocation buildLocation(LocationEntity locationEntity) {
        if(locationEntity == null){
            return null;
        }

        MyLocation location = new MyLocation();
        location.setId(locationEntity.getId());
        location.setName(locationEntity.getName());

        if(locationEntity.getPlaces() != null && !locationEntity.getPlaces().isEmpty()){
            location.setPlaceList(
                    locationEntity.getPlaces().stream()
                            .map(this::mapPlace)
                            .collect(Collectors.toList()));
        }

        return location;
    }

    public Place mapPlace(PlaceEntity placeEntity) {
        if(placeEntity == null){
            return null;
        }

        Place place = new Place();
        place.setId(placeEntity.getId());
        place.setName(placeEntity.getName());
        place.setFormattedAddress(placeEntity.getAddress());
        place.setGeometry(new Geometry(new Location(placeEntity.getLatitude(), placeEntity.getLongitude())));
        Optional.ofNullable(placeEntity.getPhotoList())
                .ifPresent(list ->
                        place.setPhotos(list.stream()
                                .map(this::mapPhoto)
                                .collect(Collectors.toList())));
        place.setTypes(Arrays.asList(placeEntity.getType().split(",")));
        place.setPlaceId(placeEntity.getGooglePlaceId());
        place.setUserEdited(placeEntity.getUserEdited());

        return place;
    }

    public Photo mapPhoto(PhotoEntity photoEntity) {
        if(photoEntity == null) {
            return null;
        }

        Photo photo = new Photo();
        photo.setPhotoRef(photoEntity.getPictureRef());
        photo.setId(photoEntity.getId());

        return photo;
    }
}
