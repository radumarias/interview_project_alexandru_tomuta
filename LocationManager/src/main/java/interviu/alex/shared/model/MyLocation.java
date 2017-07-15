package interviu.alex.shared.model;

import interviu.alex.shared.model.googleapi.Place;

import java.io.Serializable;
import java.util.List;

/**
 * Created by alexa on 7/11/2017.
 */
public class MyLocation implements Serializable{

    private String name;
    private int id;
    private float latitude;
    private float longitude;

    private List<Place> placeList;

    public MyLocation() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Place> getPlaceList() {
        return placeList;
    }

    public void setPlaceList(List<Place> placeList) {
        this.placeList = placeList;
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

    @Override
    public String toString() {
        return "MyLocation{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", placeList=" + placeList +
                '}';
    }
}
