package interviu.alex.shared.model.googleapi;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by alexa on 7/8/2017.
 */
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenHours implements Serializable {

    @JsonProperty("open_now")
    private Boolean openNow;

    @JsonProperty("weekday_text")
    private List<String> weekdayText;

    public OpenHours() {
    }

    public Boolean getOpenNow() {
        return openNow;
    }

    public void setOpenNow(Boolean openNow) {
        this.openNow = openNow;
    }

    public List<String> getWeekdayText() {
        return weekdayText;
    }

    public void setWeekdayText(List<String> weekdayText) {
        this.weekdayText = weekdayText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OpenHours openHours = (OpenHours) o;

        if (openNow != null ? !openNow.equals(openHours.openNow) : openHours.openNow != null) return false;
        return weekdayText != null ? weekdayText.equals(openHours.weekdayText) : openHours.weekdayText == null;
    }

    @Override
    public int hashCode() {
        int result = openNow != null ? openNow.hashCode() : 0;
        result = 31 * result + (weekdayText != null ? weekdayText.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OpenHours{" +
                "openNow=" + openNow +
                ", weekdayText=" + weekdayText +
                '}';
    }
}
