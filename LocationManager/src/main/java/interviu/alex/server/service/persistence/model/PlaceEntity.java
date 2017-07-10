package interviu.alex.server.service.persistence.model;

import interviu.alex.shared.model.googleapi.Location;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by alexa on 7/10/2017.
 */
@Entity
public class PlaceEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "NAME")
    @NotNull
    private String name;

    @Column(name = "FAVOURITE")
    private Boolean favourite;

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
    private Location location;

    @Column(name = "PICTURE_URL")
    private String pictureUrl;

    @Column(name = "COMMENT")
    private String comment;

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

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaceEntity that = (PlaceEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null) return false;
        return longitude != null ? longitude.equals(that.longitude) : that.longitude == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PlaceEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", favourite=" + favourite +
                ", type='" + type + '\'' +
                ", Address='" + Address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", location=" + location +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
