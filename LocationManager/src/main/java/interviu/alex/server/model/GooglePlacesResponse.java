package interviu.alex.server.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import interviu.alex.shared.model.googleapi.Place;
import interviu.alex.shared.model.googleapi.Status;

import java.util.List;

/**
 * Created by alexa on 7/8/2017.
 */
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class GooglePlacesResponse {

    private Status status;
    private List<Place> results;

    @JsonProperty("error_message")
    private String errorMessage;
    @JsonProperty("html_attributions")
    private List<String> htmlAttributions;

    public GooglePlacesResponse() {
    }

    public List<String> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<String> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Place> getResults() {
        return results;
    }

    public void setResults(List<Place> results) {
        this.results = results;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GooglePlacesResponse that = (GooglePlacesResponse) o;

        if (htmlAttributions != null ? !htmlAttributions.equals(that.htmlAttributions) : that.htmlAttributions != null)
            return false;
        if (status != that.status) return false;
        return results != null ? results.equals(that.results) : that.results == null;
    }

    @Override
    public int hashCode() {
        int result = htmlAttributions != null ? htmlAttributions.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (results != null ? results.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GooglePlacesResponse{" +
                "htmlAttributions=" + htmlAttributions +
                ", status=" + status +
                ", results=" + results +
                '}';
    }
}
