package at.htl.control;

import at.htl.entity.RoomType;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class RoomTypeRepository {
    private final EntityManager entityManager;

    public RoomTypeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<RoomType> getAllRoomTypes(){
        return entityManager.createQuery("Select rt from RoomType rt", RoomType.class)
                .getResultList();
    }

    @Transactional
    public RoomType addRoomType(RoomType roomType){
        entityManager.persist(roomType);
        return roomType;
    }

    @Transactional
    public void deleteRoomType(Long id){
        entityManager.remove(findRoomTypeById(id));
    }

    public RoomType findRoomTypeById(Long id){
        return entityManager.createQuery("Select rt from RoomType rt where rt.id = :id", RoomType.class)
                .setParameter("id", id).getResultList()
                .stream().findFirst().orElse(null);
    }

    @Transactional
    public RoomType updateRoomType(RoomType roomType){
        return entityManager.merge(roomType);
    }
}
