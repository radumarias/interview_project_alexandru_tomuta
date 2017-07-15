package interviu.alex.client.components;

import com.google.gwt.cell.client.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.Range;
import interviu.alex.client.handlers.MarkPlaceEdited;
import interviu.alex.client.handlers.OnUpdateHandler;
import interviu.alex.client.validation.FloatNumberFormatValidator;
import interviu.alex.shared.model.googleapi.Photo;
import interviu.alex.shared.model.googleapi.Place;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexa on 7/14/2017.
 */
public class PlacesTable extends CellTable<Place> {

    private final ListDataProvider<Place> dataProvider;
//    private Column<Place, String> imageColumn;
    private Column<Place, String> addressColumn;
    private Column<Place, Number> latColumn;
    private Column<Place, Number> dbIdColumn;
    private Column<Place, Number> lngColumn;
    private Column<Place, String> nameColumn;
    private Column<Place, String> ratingColumn;

    public PlacesTable(){

        // Connect the table to the data provider.
        dataProvider = new ListDataProvider<>();
        dataProvider.addDataDisplay(this);
        initColumns();
        this.setVisibleRange(new Range(0, 20));
    }


    private void initColumns() {
        /*imageColumn = new Column<Place, String>(new ImageCell()){
            @Override
            public String getValue(Place place) {
                //todo still needs a fix
                if(place.getPhotos() == null || !place.getPhotos().isEmpty()){
                    return "";
                }
                Photo photo = place.getPhotos().get(0);
                return photo.getImageUrl();
            }
        };
        this.addColumn(imageColumn, "Images");*/

        dbIdColumn = new Column<Place, Number>(new NumberCell()) {
            @Override
            public Integer getValue(Place object) {
                return object.getId();
            }
        };
        this.addColumn(dbIdColumn, "Database ID");

        latColumn = new Column<Place, Number>(new NumberCell()) {
            @Override
            public Float getValue(Place object) {
                return object.getGeometry().getLocation().getLat();
            }
        };
        this.addColumn(latColumn, "Latitude");

        lngColumn = new Column<Place, Number>(new NumberCell()) {
            @Override
            public Float getValue(Place object) {
                return object.getGeometry().getLocation().getLng();
            }
        };
        this.addColumn(lngColumn, "Longitude");

        // Editable cells

        //CustomeEditTextCell handles updates to the actual Place
        CustomEditTextCell<Place> nameEditCell = new CustomEditTextCell<>(this,
                Collections.emptyList(),
                Arrays.asList(new MarkPlaceEdited(), (place, value) -> {
                    place.setName(value);
                    GWT.log("Update placeId["+place.getId()+"] with name=["+value+"]");
                }));
        nameColumn = new Column<Place, String>(nameEditCell) {
            @Override
            public String getValue(Place place) {
                return place.getName();
            }
        };
        nameColumn.setSortable(true);
        this.addColumn(nameColumn, "Name");

        CustomEditTextCell<Place> addressEditCell = new CustomEditTextCell<>(this,
                Collections.emptyList(),
                Arrays.asList(new MarkPlaceEdited(), (place, value) -> {
                    place.setFormattedAddress(value);
                    GWT.log("Update placeId["+place.getId()+"] with formattedAddress=["+value+"]");
                }));
        addressColumn = new Column<Place, String>(addressEditCell) {
            @Override
            public String getValue(Place object) {
                return object.getFormattedAddress();
            }
        };
        this.addColumn(addressColumn, "Address");

        CustomEditTextCell<Place> ratingEditCell = new CustomEditTextCell<>(this,
                Collections.singletonList(new FloatNumberFormatValidator()),
                Arrays.asList(new MarkPlaceEdited(), (place, value) -> {
                    place.setRating(Float.parseFloat(value));
                    GWT.log("Update placeId["+place.getId()+"] with rating=["+value+"]");
                }));

        ratingColumn = new Column<Place, String>(ratingEditCell){
            @Override
            public String getValue(Place place) {
                return String.valueOf(place.getRating());
            }
        };
        ratingColumn.setSortable(true);
        this.addColumn(ratingColumn, "Rating");

    }

    public ListDataProvider<Place> getDataProvider(){
        return dataProvider;
    }
}
