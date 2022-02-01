package at.htl.control;

import at.htl.entity.Bed;
import at.htl.entity.Room;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class BedRepository {
    private final EntityManager entityManager;

    public BedRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Bed> getAllBeds(){
        return entityManager.createQuery("Select b from Bed b", Bed.class)
                .getResultList();
    }

    @Transactional
    public void addBed(Bed bed){
        entityManager.persist(bed);
    }

    @Transactional
    public void deleteBed(Long id){
        entityManager.remove(findBedById(id));
    }

    public Bed findBedById(Long id){
        return entityManager.createQuery("Select b from Bed b where b.id = :id", Bed.class)
                .setParameter("id", id).getResultList()
                .stream().findFirst().orElse(null);
    }

    @Transactional
    public Bed updateBed(Bed bed){
        return entityManager.merge(bed);
    }
}
