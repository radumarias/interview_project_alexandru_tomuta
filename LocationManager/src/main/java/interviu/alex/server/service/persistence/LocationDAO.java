package interviu.alex.server.service.persistence;

import com.google.inject.Inject;
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
public class LocationDAO {

    Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    public LocationDAO() {
    }

    @Inject
    private EntityManager em;

    @Transactional
    public LocationEntity getLocationById(Integer locationId){
        return em.find(LocationEntity.class, locationId);
    }

    @Transactional
    public LocationEntity getLocationByName(String name){
        LocationEntity result = null;
        try{
            result = em.createNamedQuery(LocationEntity.FIND_LOCATIONS_BY_NAME, LocationEntity.class)
                            .setParameter(LocationEntity.NAME, name).getSingleResult();
        }
        catch (NoResultException noResult){
            logger.info("No result found for place name :"+name);
        }
        return result;
    }

    @Transactional
    public List<LocationEntity> getAllLocations(){
        return em.createNamedQuery(LocationEntity.FIND_ALL_LOCATIONS, LocationEntity.class).getResultList();
    }

    @Transactional
    public void persistLocation(LocationEntity locationEntity){
        em.persist(locationEntity);
    }

    @Transactional
    public void removeLocation(LocationEntity locationEntity){
        em.remove(locationEntity);
    }

    public void updateLocation(LocationEntity locationEntity) {
        em.merge(locationEntity);
    }
}
