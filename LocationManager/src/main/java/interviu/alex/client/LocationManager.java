package interviu.alex.client;

import interviu.alex.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import interviu.alex.shared.model.googleapi.Location;

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

  //https://maps.googleapis.com/maps/api/place/autocomplete/output?parameters

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {

//    final TextBox citySearch = new TextBox();
//    citySearch.setText( messages.citySearchField() );

    final Button sendButton = new Button( messages.sendButton() );
    final Label errorLabel = new Label();

    final Label serverResponseLabel = new Label();
    serverResponseLabel.setVisible(false);

//    RootPanel.get("searchCityFieldContainer").add(citySearch);
    RootPanel.get("sendButtonContainer").add(sendButton);
    RootPanel.get("errorLabelContainer").add(errorLabel);
    RootPanel.get("serverResponseContainer").add(serverResponseLabel);

//    citySearch.setFocus(true);
//    citySearch.selectAll();

    class MyHandler implements ClickHandler, KeyUpHandler {
      /**
       * Fired when the user clicks on the sendButton.
       */
      public void onClick(ClickEvent event) {
        sendNameToServer();
      }

      /**
       * Fired when the user types in the nameField.
       */
      public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
          sendNameToServer();
        }
      }

      /**
       * Send the name from the nameField to the server and wait for a response.
       */
      private void sendNameToServer() {
        // First, we validate the input.
        errorLabel.setText("");

        Location location = buildLocation();

        // Then, we send the input to the server.
        sendButton.setEnabled(false);
        serverResponseLabel.setText("");
        locationService.searchByCity(location, new AsyncCallback<String>() {
          public void onFailure(Throwable caught) {
            // Show the RPC error message to the user
            serverResponseLabel.addStyleName("serverResponseLabelError");
            serverResponseLabel.setText(SERVER_ERROR);
            serverResponseLabel.setVisible(true);
            sendButton.setEnabled(true);
          }

          public void onSuccess(String result) {
            serverResponseLabel.removeStyleName("serverResponseLabelError");
            serverResponseLabel.setText(result);
            serverResponseLabel.setVisible(true);
            sendButton.setEnabled(true);
          }
        });
      }
    }
    MyHandler handler = new MyHandler();
    sendButton.addClickHandler(handler);
  }

  private Location buildLocation() {
    Location location = new Location();
    String coordinates = getCoordinates();
    String[] split = coordinates.split(",");

    String lat = split[0];
    String lng = split[1];

    //trim them a bit
    lat = lat.substring(0, 6);
    lng = lng.substring(0, 6);

    location.setLat(Double.parseDouble(lat));
    location.setLng(Double.parseDouble(lng));
    return location;
  }

  /**
   * Getting JS variable via native interface
   * @return the update coordinates variable. e.g. structure "46.123,-24.100"
   */
  public native String getCoordinates()/*-{
        return $wnd.coordinates;
  }-*/;

}
