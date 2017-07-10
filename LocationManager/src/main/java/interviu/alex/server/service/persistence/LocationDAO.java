package interviu.alex.server.service.persistence;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import interviu.alex.server.service.persistence.model.LocationEntity;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Persistence service for retrieving Locations
 * Created by alexa on 7/10/2017.
 */
public class LocationDAO {

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
        return em.createNamedQuery(LocationEntity.FIND_LOCATIONS_BY_NAME, LocationEntity.class).getSingleResult();
    }

    @Transactional
    public List<LocationEntity> getAllLocations(){
        return em.createNamedQuery(LocationEntity.FIND_ALL_LOCATIONS, LocationEntity.class).getResultList();
    }

    @Transactional
    public void createLocation(LocationEntity locationEntity){
        em.persist(locationEntity);
    }

    @Transactional
    public void removeLocation(LocationEntity locationEntity){
        em.remove(locationEntity);
    }
}
