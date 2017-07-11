package interviu.alex.shared.model.googleapi;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 *
 * Created by alexa on 7/8/2017.
 */
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class Place implements Serializable {

    private String icon;
    private String name;
    private Float rating;
    private String reference;
    private String scope;
    private String vicinity;
    private List<String> types;

    private Geometry geometry;
    private List<Photo> photos;

    @JsonProperty("address_components")
    private List<AddressComponent> addressComponents;
    @JsonProperty("formatted_address")
    private String formattedAddress;
    @JsonProperty("place_id")
    private String placeId;
    @JsonProperty("opening_hours")
    private OpenHours openHours;
    @JsonProperty("price_level")
    private Integer priceLevel;

    // domain specific data

    @JsonIgnore
    private Integer id;

    @JsonIgnore
    private Boolean userEdited;

    public Place() {
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

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

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public OpenHours getOpenHours() {
        return openHours;
    }

    public void setOpenHours(OpenHours openHours) {
        this.openHours = openHours;
    }

    public Integer getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(Integer priceLevel) {
        this.priceLevel = priceLevel;
    }

    public List<AddressComponent> getAddressComponents() {
        return addressComponents;
    }

    public void setAddressComponents(List<AddressComponent> addressComponents) {
        this.addressComponents = addressComponents;
    }

    public Boolean getUserEdited() {
        return userEdited;
    }

    public void setUserEdited(Boolean userEdited) {
        this.userEdited = userEdited;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place place = (Place) o;

        return placeId.equals(place.placeId);
    }

    @Override
    public int hashCode() {
        return placeId.hashCode();
    }

    @Override
    public String toString() {
        return "PlaceEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", placeId='" + placeId + '\'' +
                '}';
    }
}