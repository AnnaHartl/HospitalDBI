package at.htl.service;

import at.htl.control.RoomRepository;
import at.htl.entity.Room;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void addRoom(Room room){
        roomRepository.addRoom(room);
    }

    public void deleteRoom(Long id){
        roomRepository.deleteRoom(id);
    }

    public Room updateRoom(Room room){
        return roomRepository.updateRoom(room);
    }

    public List<Room> getAllRooms(){
        return roomRepository.getAllRooms();
    }

    public Room findRoomById(Long id){
        return roomRepository.findRoomById(id);
    }


}
