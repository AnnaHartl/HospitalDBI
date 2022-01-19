package at.htl;

import at.htl.control.RoomRepository;
import at.htl.entity.Room;
import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class InitBean {
    private final RoomRepository roomRepository;

    public InitBean(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void Init(@Observes StartupEvent event){
        Room room1 = new Room(10, 10, "BigRoom");
        Room room2 = new Room(69, 69, "SmallRoom");

        roomRepository.addRoom(room1);
        roomRepository.addRoom(room2);

    }
}
