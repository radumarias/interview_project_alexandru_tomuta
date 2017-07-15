package interviu.alex.server.service.persistence;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import interviu.alex.server.service.persistence.model.PlaceEntity;

import javax.persistence.EntityManager;
import java.util.logging.Logger;

/**
 * Created by alexa on 7/10/2017.
 */
@Transactional
public class PlaceDAO {

    Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    public PlaceDAO() {
    }

    @Inject
    private Provider<EntityManager> em;

    public void updatePlace(PlaceEntity placeEntity){
        em.get().merge(placeEntity);
    }

}
