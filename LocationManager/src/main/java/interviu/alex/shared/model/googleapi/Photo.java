package interviu.alex.shared.model.googleapi;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Photo object based on google places api response
 * Created by alexa on 7/8/2017.
 */
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class Photo implements Serializable {

    private int height;
    private int width;

    @JsonProperty("photo_reference")
    private String photoRef;
    @JsonProperty("html_attributions")
    private List<String> htmlAttributes;

    //domain specific data

    /**
     * used in Client to store image urls from google
     */
    @JsonIgnore
    private String imageUrl;

    @JsonIgnore
    private int id;

    public Photo() {
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getPhotoRef() {
        return photoRef;
    }

    public void setPhotoRef(String photoRef) {
        this.photoRef = photoRef;
    }

    public List<String> getHtmlAttributes() {
        return htmlAttributes;
    }

    public void setHtmlAttributes(List<String> htmlAttributes) {
        this.htmlAttributes = htmlAttributes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        return photoRef.equals(photo.photoRef);
    }

    @Override
    public int hashCode() {
        return photoRef.hashCode();
    }

    @Override
    public String toString() {
        return "Photo{" +
                "photoRef='" + photoRef + '\'' +
                '}';
    }
}
