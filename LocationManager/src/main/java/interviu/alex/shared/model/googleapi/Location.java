package interviu.alex.shared.model.googleapi;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Location object based on google places api response
 * Created by alexa on 7/8/2017.
 */
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location implements Serializable{

    private Float lat;
    private Float lng;

    public Location() {
    }

    public Location(Float lat, Float lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (lat != null ? !lat.equals(location.lat) : location.lat != null) return false;
        return lng != null ? lng.equals(location.lng) : location.lng == null;
    }

    @Override
    public int hashCode() {
        int result = lat != null ? lat.hashCode() : 0;
        result = 31 * result + (lng != null ? lng.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
