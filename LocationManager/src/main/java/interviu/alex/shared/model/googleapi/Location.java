package interviu.alex.shared.model.googleapi;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Location object based on google places api response
 * Created by alexa on 7/8/2017.
 */
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location implements Serializable{

    private Double lat;
    private Double lng;

    public Location() {
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (!lat.equals(location.lat)) return false;
        return lng.equals(location.lng);
    }

    @Override
    public int hashCode() {
        int result = lat.hashCode();
        result = 31 * result + lng.hashCode();
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
