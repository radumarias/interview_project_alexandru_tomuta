package interviu.alex.server.service.persistence.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexa on 7/10/2017.
 */
@Entity
@Table(name = "PLACE")
@NamedQueries({
})
public class PlaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

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
    private float latitude;

    @NotNull
    @Column(name = "LONGITUDE")
    private float longitude;

    @Column(name = "RATING")
    private float rating;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_ID")
    private LocationEntity location;

    @OneToMany(mappedBy = "place", orphanRemoval = true)
    @Cascade(CascadeType.ALL)
    private List<PhotoEntity> photoList = new ArrayList<>();

    @NotNull
    @Column(name = "GOOGLE_PLACE_ID")
    private String googlePlaceId;

    @Column(name = "USER_EDITED")
    private boolean userEdited = Boolean.FALSE;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    public boolean getUserEdited() {
        return userEdited;
    }

    public void setUserEdited(boolean userEdited) {
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

    public float getRating() {
        return rating;
    }

    public boolean isUserEdited() {
        return userEdited;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
