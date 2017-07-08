package interviu.alex.shared.model.googleapi;

/**
 * Created by alexa on 7/8/2017.
 */
public enum Status {

    /**
     * indicates that no errors occurred;
     * the place was successfully detected and at least one result was returned.
     */
    OK,
    /**
     * indicates that the search was successful but returned no results.
     * This may occur if the search was passed a latlng in a remote location.
     */
    ZERO_RESULTS,
    /**
     * indicates that you are over your quota.
     */
    OVER_QUERY_LIMIT,
    /**
     * indicates that your request was denied,
     * generally because of lack of an invalid key parameter.
     */
    REQUEST_DENIED,
    /**
     * generally indicates that a required query parameter (location or radius) is missing.
     */
    INVALID_REQUEST
}
