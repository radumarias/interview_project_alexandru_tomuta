package interviu.alex.server.service.persistence.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by alexa on 7/10/2017.
 */

@Entity
@Table(name = "LOCATION")
@NamedQueries({
        @NamedQuery(name = LocationEntity.FIND_LOCATIONS_BY_NAME, query = "select l from LOCATION l where l.name = :name"),
        @NamedQuery(name = LocationEntity.FIND_ALL_LOCATIONS, query = "select l from LOCATION l")
})
public class LocationEntity {

    public static final String FIND_LOCATIONS_BY_NAME = "findLocationsByName";
    public static final String NAME = "name";
    public static final String FIND_ALL_LOCATIONS= "findAllLocations";

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    @NotNull
    private String name;

    @Column(name = "LATITUDE")
    @NotNull
    private Float latitude;

    @Column(name = "LONGITUDE")
    @NotNull
    private Float longitude;

    @OneToMany(mappedBy = "location")
    @Cascade(CascadeType.ALL)
    private List<PlaceEntity> places;

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

    public List<PlaceEntity> getPlaces() {
        return places;
    }

    public void setPlaces(List<PlaceEntity> places) {
        this.places = places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationEntity that = (LocationEntity) o;

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
        return "LocationEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
