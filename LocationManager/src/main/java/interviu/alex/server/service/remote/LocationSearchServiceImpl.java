package interviu.alex.server.service.remote;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import interviu.alex.client.LocationSearchService;
import interviu.alex.server.LocationManagerModule;
import interviu.alex.server.model.GooglePlacesResponse;
import interviu.alex.server.service.GooglePlacesService;
import interviu.alex.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import interviu.alex.shared.model.googleapi.Location;
import interviu.alex.shared.model.googleapi.Place;

import java.util.stream.Collectors;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class LocationSearchServiceImpl extends RemoteServiceServlet implements
        LocationSearchService {

  @Inject
  private GooglePlacesService googlePlacesService;

  public LocationSearchServiceImpl(){}

  public String searchByCity(Location input) throws IllegalArgumentException {
    //todo create full structures with what we're interested in CLIENT

    return buildListOfPlaces(googlePlacesService.queryGoogleForPlaces(input));
  }

  private String buildListOfPlaces(GooglePlacesResponse response){
      return response.getResults().stream().map(Place::getName).collect(Collectors.joining(", "));
  }

}
