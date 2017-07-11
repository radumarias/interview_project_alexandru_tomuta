package interviu.alex.server.service;

import interviu.alex.server.model.GooglePlacesResponse;
import interviu.alex.shared.model.MyLocation;
import interviu.alex.shared.model.googleapi.Location;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Component responsible with GooglePlaces API interaction
 * Created by alexa on 7/8/2017.
 */

public class GooglePlacesService {
    //todo move to property file or db
    private static final String RADIUS = "radius";
    private static final String KEY = "key";
    private static final String LOCATION = "location";
    private static final String API_KEY = "AIzaSyD2eKQ7H9Y3ukmE5oM6ttISMTwinWQnAAw";
    private static final String RESPONSE_CONTENT_TYPE = "json";
    private static final String BASE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/";
    private static final int RADIUS_SIZE = 5000;

    Logger logger = Logger.getLogger(this.getClass().getSimpleName());

   /* @Inject
    private RestTemplate restTemplate;*/

    public GooglePlacesService(){ // no-arg
    }

    public GooglePlacesResponse queryGoogleForPlaces(MyLocation searchKey){
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL + RESPONSE_CONTENT_TYPE)
                .queryParam(LOCATION, searchKey.getLatitude()+","+searchKey.getLongitude())
                .queryParam(RADIUS, RADIUS_SIZE)
                .queryParam(KEY, API_KEY);

        String url = builder.toUriString();
        logger.log(Level.INFO, "Requestu URL: "+url);
        ResponseEntity<GooglePlacesResponse> responseEntity = restTemplate.getForEntity(url, GooglePlacesResponse.class);
        logger.log(Level.INFO, getLogString(responseEntity));
        return responseEntity.getBody();
    }

    private String getLogString(ResponseEntity<GooglePlacesResponse> responseEntity) {
        return  "Response from google: " + responseEntity.getStatusCode().name() +", with values: \n\n" + responseEntity.getBody() + "\n\n";
    }
}
