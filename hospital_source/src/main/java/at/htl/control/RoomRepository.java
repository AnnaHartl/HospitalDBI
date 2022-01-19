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
}
