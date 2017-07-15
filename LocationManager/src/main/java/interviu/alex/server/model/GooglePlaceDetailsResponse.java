package interviu.alex.server.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import interviu.alex.shared.model.googleapi.Place;

import java.util.List;

/**
 * Created by alexa on 7/13/2017.
 */
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class GooglePlaceDetailsResponse {

    @JsonProperty("html_attributions")
    private List<String> htmlAttributions;
    @JsonProperty("error_message")
    private String errorMessage;
    @JsonProperty("result")
    private Place place;

    public List<String> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<String> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "GooglePlaceDetailsResponse{" +
                "htmlAttributions=" + htmlAttributions +
                ", place=" + place +
                '}';
    }
}
