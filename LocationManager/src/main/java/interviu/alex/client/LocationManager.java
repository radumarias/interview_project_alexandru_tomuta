package interviu.alex.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ListDataProvider;
import interviu.alex.client.components.AlertPopup;
import interviu.alex.client.components.PlacesTable;
import interviu.alex.client.service.LocationSearchService;
import interviu.alex.client.service.LocationSearchServiceAsync;
import interviu.alex.shared.model.MyLocation;
import interviu.alex.shared.model.googleapi.Place;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

  private MyLocation currentLoadedLocation;


  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {

    final Button sendButton = new Button( messages.sendButton() );
    final Label errorLabel = new Label();
    final PlacesTable dataTable = new PlacesTable();
    final Button savePlacesButton = new Button(messages.savePlaces());
    final Label serverResponseLabel = new Label();

    savePlacesButton.setEnabled(false);
    savePlacesButton.setVisible(false);
    serverResponseLabel.setVisible(false);

    RootPanel.get("sendButtonContainer").add(sendButton);
    RootPanel.get("errorLabelContainer").add(errorLabel);
    RootPanel.get("serverResponseContainer").add(dataTable);
    RootPanel.get("saveButtonContainer").add(savePlacesButton);

    class MyHandler implements ClickHandler, KeyUpHandler {
      /**
       * Fired when the user clicks on the sendButton.
       */
      public void onClick(ClickEvent event) {
        sendCoordinatesToServer();
        dataTable.getDataProvider().getList().clear();
      }

      /**
       * Fired when the user types in the nameField.
       */
      public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            sendCoordinatesToServer();
            dataTable.getDataProvider().getList().clear();
        }
      }

      /**
       * Send the name from the nameField to the server and wait for a response.
       */
      private void sendCoordinatesToServer() {
        errorLabel.setText("");

        MyLocation location = buildNewLocation();

        sendButton.setEnabled(false);
        serverResponseLabel.setText("");
        locationService.searchByCity(location, new AsyncCallback<MyLocation>() {
          public void onFailure(Throwable caught) {
            serverResponseLabel.addStyleName("serverResponseLabelError");
            serverResponseLabel.setText(SERVER_ERROR);
            serverResponseLabel.setVisible(true);
            sendButton.setEnabled(true);
            savePlacesButton.setEnabled(false);
            savePlacesButton.setVisible(false);
            GWT.log("failure on search by city");
          }

          public void onSuccess(MyLocation result) {
            currentLoadedLocation = result;
            serverResponseLabel.removeStyleName("serverResponseLabelError");
            if(result == null ||result.getPlaceList() == null || result.getPlaceList().isEmpty()){
                new AlertPopup("Empty response from google and empty response from DB - probably exceeded daily quota!").show();
                sendButton.setEnabled(true);
                return;
            }
            dataTable.getDataProvider().getList().clear();
            dataTable.getDataProvider().getList().addAll(result.getPlaceList());
            serverResponseLabel.setVisible(true);
            sendButton.setEnabled(true);
            savePlacesButton.setEnabled(true);
            savePlacesButton.setVisible(true);
            GWT.log("success on search by city");
            GWT.log("Current location set = "
                  + " name = " + currentLoadedLocation.getName()
                  + ", id = " + currentLoadedLocation.getId()
                  + ", lat = " + currentLoadedLocation.getLatitude()
                  + ", lng = " + currentLoadedLocation.getLongitude());
              GWT.log("Places =");
              for(Place place : result.getPlaceList()){
                  GWT.log(place.getName() + "; "+place.getFormattedAddress()+"; "+place.getRating());
              }
          }
        });
      }
    }
    MyHandler handler = new MyHandler();
    sendButton.addClickHandler(handler);
    savePlacesButton.addClickHandler(event -> sendDataToServer(dataTable));

  }

  private void sendDataToServer(PlacesTable placesTable) {
      ArrayList<Place> places = new ArrayList<>(placesTable.getDataProvider().getList());
      if(places.isEmpty()){
          GWT.log("Error - places list is empty");
          throw new NullPointerException();
      }
      currentLoadedLocation.setPlaceList(places);
      GWT.log("Sending data to server:");
      GWT.log("Location = "
              + " name = " + currentLoadedLocation.getName()
              + ", id = " + currentLoadedLocation.getId()
              + ", lat = " + currentLoadedLocation.getLatitude()
              + ", lng = " + currentLoadedLocation.getLongitude());
      GWT.log("Places =");
      for(Place place : places){
          GWT.log(place.getName() + "; "+place.getFormattedAddress()+"; "+place.getRating());
      }
      if(currentLoadedLocation.getId() != 0) {
          locationService.updateLocation(currentLoadedLocation, new AsyncCallback<Void>() {
              @Override
              public void onFailure(Throwable caught) {
                  new AlertPopup("Failed to updated places").show();
                  GWT.log("Failed on update ["+caught+"]");
              }

              @Override
              public void onSuccess(Void result) {
                  new AlertPopup("Successfully update places").show();
                  GWT.log("Success on update");
              }
          });
      }
      else{
          currentLoadedLocation.setName(getName());
          locationService.addNewLocation(currentLoadedLocation, new AsyncCallback<Void>() {
              @Override
              public void onFailure(Throwable caught) {
                  new AlertPopup("Failed to save places").show();
                  GWT.log("Failed on save ["+caught+"]");
              }

              @Override
              public void onSuccess(Void result) {
                  new AlertPopup("Successfully saved places").show();
                  GWT.log("Success on save");
              }
          });
      }

  }

  private MyLocation buildNewLocation() {
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
