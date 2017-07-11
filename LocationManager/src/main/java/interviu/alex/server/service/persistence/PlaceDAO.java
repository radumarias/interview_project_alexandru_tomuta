package interviu.alex.server.service.persistence;

import com.google.inject.Inject;
import interviu.alex.server.service.persistence.model.PlaceEntity;

import javax.persistence.EntityManager;

/**
 * Created by alexa on 7/10/2017.
 */
public class PlaceDAO {

    @Inject
    private EntityManager em;

}
