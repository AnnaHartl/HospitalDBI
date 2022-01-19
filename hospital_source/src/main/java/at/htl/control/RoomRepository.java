package at.htl.control;

import at.htl.entity.Room;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class RoomRepository {
    private final EntityManager entityManager;

    public RoomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Room> getAllRooms(){
        return entityManager.createQuery("Select r from Room r", Room.class)
                .getResultList();
    }

    public void addRoom(Room room){
        entityManager.persist(room);
    }

    public void deleteRoom(Long id){
        entityManager.remove(findRoomById(id));
    }

    public Room findRoomById(Long id){
        return entityManager.createQuery("Select r from Room r where r.id = :id", Room.class)
                .setParameter("id", id).getResultList()
                .stream().findFirst().orElse(null);
    }

    public Room updateRoom(Room room){
        return entityManager.merge(room);
    }
}
