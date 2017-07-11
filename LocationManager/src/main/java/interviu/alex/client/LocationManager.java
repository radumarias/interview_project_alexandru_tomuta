package interviu.alex.client;

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
import interviu.alex.shared.model.MyLocation;
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
    RootPanel.get("serverResponseContainer").add(serverResponseLabel);

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
//            serverResponseLabel.setText(result));
            serverResponseLabel.setVisible(true);
            sendButton.setEnabled(true);
          }
        });
      }
    }
    MyHandler handler = new MyHandler();
    sendButton.addClickHandler(handler);
  }

  private MyLocation buildLocation() {
    MyLocation location = new MyLocation();
    String coordinates = getCoordinates();
    String[] split = coordinates.split(",");

    String lat = split[0];
    String lng = split[1];

    //trim them a bit
    lat = lat.substring(0, 6);
    lng = lng.substring(0, 6);

    location.setLatitude(Float.parseFloat(lat));
    location.setLongitude(Float.parseFloat(lng));

    location.setName(getName());
    return location;
  }

  /**
   * Getting JS variable via native interface
   * @return the update coordinates variable. e.g. structure "46.123,-24.100"
   */
  public native String getCoordinates()/*-{
        return $wnd.coordinates;
  }-*/;

  public native String getName()/*-{
      return $wnd.name;
  }-*/;

}
