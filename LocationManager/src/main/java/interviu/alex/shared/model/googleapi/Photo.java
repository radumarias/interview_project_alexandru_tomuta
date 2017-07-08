package interviu.alex.shared.model.googleapi;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
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

    private Integer height;
    private Integer width;

    @JsonProperty("photo_reference")
    private String photoRef;
    @JsonProperty("html_attributions")
    private List<String > htmlAttributes;

    public Photo() {
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
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
