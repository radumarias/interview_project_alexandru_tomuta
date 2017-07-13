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
@Table(name = "LOCATION")
@NamedQueries({
        @NamedQuery(name = LocationEntity.FIND_LOCATIONS_BY_NAME, query = "from LocationEntity l where l.name = :name"),
        @NamedQuery(name = LocationEntity.FIND_ALL_LOCATIONS, query = "from LocationEntity l")
})
public class LocationEntity {

    public static final String FIND_LOCATIONS_BY_NAME = "findLocationsByName";
    public static final String NAME = "name";
    public static final String FIND_ALL_LOCATIONS= "findAllLocations";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    @NotNull
    private String name;

    @Column(name = "LATITUDE")
    @NotNull
    private float latitude;

    @Column(name = "LONGITUDE")
    @NotNull
    private float longitude;

    @OneToMany(mappedBy = "location")
    @Cascade(CascadeType.ALL)
    private List<PlaceEntity> places = new ArrayList<>();

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

        if (id != that.id) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
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
