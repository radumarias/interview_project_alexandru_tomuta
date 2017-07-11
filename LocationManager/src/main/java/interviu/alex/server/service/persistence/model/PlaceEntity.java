package interviu.alex.server.service.persistence.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by alexa on 7/10/2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = PlaceEntity.FIND_ALL_FAVOURITE_PLACES, query = "select l from PLACE l where l.FAVOURITE = true"),
})
public class PlaceEntity {

    public static final String FIND_ALL_FAVOURITE_PLACES = "findFavouritePlaces";

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "NAME")
    @NotNull
    private String name;

    //todo change this to an enum
    @Column(name = "TYPE")
    private String type;

    @Column(name = "ADDRESS")
    private String Address;

    @NotNull
    @Column(name = "LATITUDE")
    private Float latitude;

    @NotNull
    @Column(name = "LONGITUDE")
    private Float longitude;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_ID")
    private LocationEntity location;

    @OneToMany(mappedBy = "place")
    @Cascade(CascadeType.ALL)
    private List<PhotoEntity> photoList;

    @NotNull
    @Column(name = "GOOGLE_PLACE_ID")
    private String googlePlaceId;

    @Column(name = "USER_EDITED")
    private Boolean userEdited;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    public Boolean getUserEdited() {
        return userEdited;
    }

    public void setUserEdited(Boolean userEdited) {
        this.userEdited = userEdited;
    }

    public List<PhotoEntity> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<PhotoEntity> photoList) {
        this.photoList = photoList;
    }

    public String getGooglePlaceId() {
        return googlePlaceId;
    }

    public void setGooglePlaceId(String googlePlaceId) {
        this.googlePlaceId = googlePlaceId;
    }
}
