package interviu.alex.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;
import interviu.alex.server.service.GooglePlacesService;
import interviu.alex.server.service.remote.LocationSearchServiceImpl;

/**
 * Module configuration
 * Created by alexa on 7/8/2017.
 */

public class LocationManagerModule extends ServletModule{

    @Override
    protected void configureServlets() {
        bind(ObjectMapper.class).in(Singleton.class);
        bind(GooglePlacesService.class).in(Singleton.class);
        bind(LocationSearchServiceImpl.class).in(Singleton.class);

        // TODO fix this:
        // Error in custom provider, com.google.inject.OutOfScopeException: Cannot access scoped [org.springframework.web.client.RestTemplate].
        // Either we are not currently inside an HTTP Servlet request,
        // or you may have forgotten to apply com.google.inject.servlet.GuiceFilter as a servlet filter for this request.

        // bind(RestTemplate.class).in(RequestScoped.class);

        serve("/LocationManager/main").with(LocationSearchServiceImpl.class);
    }
}
