package at.htl.control;

import at.htl.entity.Room;
import at.htl.entity.Station;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class StationRepository {

    private final EntityManager entityManager;

    public StationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Station> getAllStations(){
        return entityManager.createQuery("Select s from Station s", Station.class)
                .getResultList();
    }

    @Transactional
    public void addStation(Station station){
        entityManager.persist(station);
    }

    @Transactional
    public void deleteStation(Long id){
        entityManager.remove(findStationById(id));
    }

    public Station findStationById(Long id){
        return entityManager.createQuery("Select s from Station s where s.id = :id", Station.class)
                .setParameter("id", id).getResultList()
                .stream().findFirst().orElse(null);
    }

    @Transactional
    public Station updateStation(Station station){
        return entityManager.merge(station);
    }

}
