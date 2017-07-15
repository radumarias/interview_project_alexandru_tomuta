package interviu.alex.server.service.persistence;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import interviu.alex.server.service.persistence.model.LocationEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Persistence service for retrieving Locations
 * Created by alexa on 7/10/2017.
 */
@Transactional
public class LocationDAO {

    Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    public LocationDAO() {
    }

    @Inject
    private Provider<EntityManager> em;

    public LocationEntity getLocationById(Integer locationId){
        return em.get().find(LocationEntity.class, locationId);
    }

    public LocationEntity getLocationByName(String name){
        LocationEntity result = null;
        try{
            result = em.get().createNamedQuery(LocationEntity.FIND_LOCATIONS_BY_NAME, LocationEntity.class)
                            .setParameter(LocationEntity.NAME, name).getSingleResult();
        }
        catch (NoResultException noResult){
            logger.info("No result found for place name :"+name);
        }
        return result;
    }

    public List<LocationEntity> getAllLocations(){
        return em.get().createNamedQuery(LocationEntity.FIND_ALL_LOCATIONS, LocationEntity.class).getResultList();
    }

    public void persistLocation(LocationEntity locationEntity){
        em.get().persist(locationEntity);
    }

    public void removeLocation(LocationEntity locationEntity){
        em.get().remove(locationEntity);
    }

    public void updateLocation(LocationEntity locationEntity) {
        em.get().merge(locationEntity);
    }
}
