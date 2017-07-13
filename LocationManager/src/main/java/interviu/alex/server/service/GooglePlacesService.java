package interviu.alex.server.service;

import interviu.alex.server.model.GooglePlaceDetailsResponse;
import interviu.alex.server.model.GooglePlacesResponse;
import interviu.alex.shared.model.MyLocation;
import interviu.alex.shared.model.googleapi.Place;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Component responsible with GooglePlaces API interaction
 * Created by alexa on 7/8/2017.
 */

public class GooglePlacesService {
    //todo move to property file or db
    private static final String RADIUS_PARAM = "radius";
    private static final String KEY_PARAM = "key";
    private static final String LOCATION_PARAM = "location";
    private static final String API_KEY_PARAM = "AIzaSyD2eKQ7H9Y3ukmE5oM6ttISMTwinWQnAAw";
    private static final String RESPONSE_CONTENT_TYPE = "json";
    private static final String BASE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/";
    private static final String PLACE_ID_PARAM = "placeid";
    private static final String BASE_URL_DETAILS = "https://maps.googleapis.com/maps/api/place/details/";
    private static final int RADIUS_SIZE = 5000;

    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

   /* @Inject
    private RestTemplate restTemplate;*/

    public GooglePlacesService(){ // no-arg
    }

    public GooglePlacesResponse queryGoogleForPlaces(MyLocation searchKey){
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL + RESPONSE_CONTENT_TYPE)
                .queryParam(LOCATION_PARAM, searchKey.getLatitude()+","+searchKey.getLongitude())
                .queryParam(RADIUS_PARAM, RADIUS_SIZE)
                .queryParam(KEY_PARAM, API_KEY_PARAM);

        String url = builder.toUriString();
        logger.log(Level.INFO, "Request URL: "+url);
        ResponseEntity<GooglePlacesResponse> responseEntity = restTemplate.getForEntity(url, GooglePlacesResponse.class);
        logger.log(Level.INFO, getLogString(responseEntity));
        return populateWithDetails(responseEntity.getBody());
    }

    /**
     * Map additional details to current places for a location
     * @param googleLocation location with places around
     * @return googlePlacesResponse with detailed places around it
     */
    private GooglePlacesResponse populateWithDetails(GooglePlacesResponse googleLocation) {
        List<Place> results = googleLocation.getResults();
        results.forEach(place ->{
            Place detailedPlace = queryDetails(place.getPlaceId());
            place.setFormattedAddress(detailedPlace.getFormattedAddress());
            place.setRating(detailedPlace.getRating());
            //todo add other stuff we could be interested in
        });
        return googleLocation;
    }

    /**
     * Second call to google - lookup details regarding a certain place.
     * @param placeId place to get detauls about
     * @return Place with details
     */
    private Place queryDetails(String placeId) {
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL_DETAILS + RESPONSE_CONTENT_TYPE)
                .queryParam(PLACE_ID_PARAM, placeId)
                .queryParam(KEY_PARAM, API_KEY_PARAM);

        String url = builder.toUriString();
        logger.log(Level.INFO, "Place details request URL: "+url);
        ResponseEntity<GooglePlaceDetailsResponse> responseEntity = restTemplate.getForEntity(url, GooglePlaceDetailsResponse.class);
        logger.log(Level.INFO, getLogString(responseEntity));

        return responseEntity.getBody().getPlace();
    }

    private String getLogString(ResponseEntity<?> responseEntity) {
        return  "Response from google: " + responseEntity.getStatusCode().name() +", with values: \n\n" + responseEntity.getBody() + "\n\n";
    }
}
