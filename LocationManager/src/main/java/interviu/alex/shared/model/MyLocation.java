package interviu.alex.shared.model;

import interviu.alex.shared.model.googleapi.Place;

import java.io.Serializable;
import java.util.List;

/**
 * Created by alexa on 7/11/2017.
 */
public class MyLocation implements Serializable{

    private String name;
    private Integer id;
    private Float latitude;
    private Float longitude;

    private List<Place> placeList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Place> getPlaceList() {
        return placeList;
    }

    public void setPlaceList(List<Place> placeList) {
        this.placeList = placeList;
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
}
