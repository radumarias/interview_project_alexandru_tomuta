package interviu.alex.client;

import com.google.gwt.cell.client.*;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ListDataProvider;
import interviu.alex.shared.model.MyLocation;
import interviu.alex.shared.model.googleapi.Place;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class LocationManager implements EntryPoint {
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  private final LocationSearchServiceAsync locationService = GWT.create(LocationSearchService.class);

  private final Messages messages = GWT.create(Messages.class);

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {

    final Button sendButton = new Button( messages.sendButton() );
    final Label errorLabel = new Label();


    final Label serverResponseLabel = new Label();
    serverResponseLabel.setVisible(false);

    RootPanel.get("sendButtonContainer").add(sendButton);
    RootPanel.get("errorLabelContainer").add(errorLabel);
    CellTable<Place> table = buildDataTable();
    RootPanel.get("serverResponseContainer").add(table);

    // Create a data provider.
    ListDataProvider<Place> dataProvider = new ListDataProvider<>();

    // Connect the table to the data provider.
    dataProvider.addDataDisplay(table);

    class MyHandler implements ClickHandler, KeyUpHandler {
      /**
       * Fired when the user clicks on the sendButton.
       */
      public void onClick(ClickEvent event) {
        sendCoordinatesToServer();
      }

      /**
       * Fired when the user types in the nameField.
       */
      public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
          sendCoordinatesToServer();
        }
      }

      /**
       * Send the name from the nameField to the server and wait for a response.
       */
      private void sendCoordinatesToServer() {
        errorLabel.setText("");

        MyLocation location = buildLocation();

        sendButton.setEnabled(false);
        serverResponseLabel.setText("");
        locationService.searchByCity(location, new AsyncCallback<MyLocation>() {
          public void onFailure(Throwable caught) {
            serverResponseLabel.addStyleName("serverResponseLabelError");
            serverResponseLabel.setText(SERVER_ERROR);
            serverResponseLabel.setVisible(true);
            sendButton.setEnabled(true);
          }

          public void onSuccess(MyLocation result) {
            serverResponseLabel.removeStyleName("serverResponseLabelError");
            dataProvider.getList().clear();
            dataProvider.getList().addAll(result.getPlaceList());
            serverResponseLabel.setVisible(true);
            sendButton.setEnabled(true);
          }
        });
      }
    }
    MyHandler handler = new MyHandler();
    sendButton.addClickHandler(handler);
  }

  private CellTable<Place> buildDataTable() {
      CellTable<Place> table = new CellTable<>();

      Column<Place, String> imageColumn = new Column<Place, String>(new ImageCell()){
          @Override
          public String getValue(Place place) {
              /*Image image = null;
              Optional<Photo> first = place.getPhotos().stream().findFirst()
                      .ifPresent(photo -> image = new Image(photo.getPhotoRef()));*/
              // todo build google request for image url
              return null;
          }
      };
      table.addColumn(imageColumn, "Images");

      TextColumn<Place> addressColumn = new TextColumn<Place>() {
          @Override
          public String getValue(Place object) {
              return object.getFormattedAddress();
          }
      };
      table.addColumn(addressColumn, "Address");

      Column<Place, Number> latColumn = new Column<Place, Number>(new NumberCell()) {
          @Override
          public Float getValue(Place object) {
              return object.getGeometry().getLocation().getLat();
          }
      };
      table.addColumn(latColumn, "Latitude");

      Column<Place, Number> lngColumn = new Column<Place, Number>(new NumberCell()) {
          @Override
          public Float getValue(Place object) {
              return object.getGeometry().getLocation().getLng();
          }
      };
      table.addColumn(lngColumn, "Longitude");


      TextColumn<Place> nameColumn = new TextColumn<Place>() {
          @Override
          public String getValue(Place place) {
              return place.getName();
          }
      };
      nameColumn.setSortable(true);
      table.addColumn(nameColumn, "Name");

      Column<Place, Number> ratingColumn = new Column<Place, Number>(new NumberCell()) {
          @Override
          public Float getValue(Place place) {
              return place.getRating();
          }
      };
      ratingColumn.setSortable(true);
      table.addColumn(ratingColumn, "Rating");

      TextColumn<Place> googlePlaceIdColumn = new TextColumn<Place>() {
          @Override
          public String getValue(Place place) {
              return place.getPlaceId();
          }
      };
      table.addColumn(googlePlaceIdColumn, "Google Place Id");

      return table;
  }

    private MyLocation buildLocation() {
        MyLocation location = new MyLocation();

        String jsLat = getLat();
        String trimmedLat = jsLat.substring(0, 6);
        location.setLatitude(Float.parseFloat(trimmedLat));

        String jsLng = getLng();
        String trimmedLng = jsLng.substring(0, 6);
        location.setLongitude(Float.parseFloat(trimmedLng));

        location.setName(getName());
        return location;
    }

  /**
   * Getting JS variable via native interface
   * @return the update coordinates variable. e.g. structure "46.123,-24.100"
   */
  public native String getLat()/*-{
        return $wnd.lat;
  }-*/;

    public native String getLng()/*-{
        return $wnd.lng;
    }-*/;

  public native String getName()/*-{
      return $wnd.name;
  }-*/;

}
