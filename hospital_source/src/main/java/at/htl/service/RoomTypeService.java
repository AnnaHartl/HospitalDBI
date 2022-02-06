package at.htl.service;

import at.htl.control.RoomTypeRepository;
import at.htl.entity.Room;
import at.htl.entity.RoomType;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;

    public RoomTypeService(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    public RoomType addRoomType(RoomType roomType){
        return roomTypeRepository.addRoomType(roomType);
    }

    public void deleteRoomType(Long id){
        roomTypeRepository.deleteRoomType(id);
    }

    public RoomType updateRoomType(RoomType roomType){
        return roomTypeRepository.updateRoomType(roomType);
    }

    public List<RoomType> getAllRoomTypes(){
        return roomTypeRepository.getAllRoomTypes();
    }

    public RoomType findRoomTypeById(Long id){
        return roomTypeRepository.findRoomTypeById(id);
    }
}
