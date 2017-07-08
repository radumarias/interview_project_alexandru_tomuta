package interviu.alex.shared.model.googleapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by alexa on 7/8/2017.
 */
public class AddressComponent implements Serializable {

    private List<String> types;

    @JsonProperty("long_name")
    private String longName;
    @JsonProperty("short_name")
    private String shortName;

    public AddressComponent() {
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressComponent that = (AddressComponent) o;

        if (types != null ? !types.equals(that.types) : that.types != null) return false;
        if (longName != null ? !longName.equals(that.longName) : that.longName != null) return false;
        return shortName != null ? shortName.equals(that.shortName) : that.shortName == null;
    }

    @Override
    public int hashCode() {
        int result = types != null ? types.hashCode() : 0;
        result = 31 * result + (longName != null ? longName.hashCode() : 0);
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AddressComponent{" +
                "types=" + types +
                ", longName='" + longName + '\'' +
                '}';
    }
}

