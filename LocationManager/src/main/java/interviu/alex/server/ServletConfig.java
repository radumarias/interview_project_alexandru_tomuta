package interviu.alex.server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet configuration
 * Created by alexa on 7/6/2017.
 */
public class ServletConfig extends GuiceServletContextListener {
    Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @Override
    protected Injector getInjector() {
        logger.log(Level.INFO, "Initializing");
        return Guice.createInjector(new LocationManagerModule());
    }
}
