package at.htl.boundary;

import at.htl.control.RoomRepository;
import at.htl.entity.Room;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("api/room")
public class RoomApi {
    private final RoomRepository roomRepository;

    public RoomApi(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Path("getAllRooms")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Room> getAllRooms(){
        return roomRepository.getAllRooms();
    }

    @Path("findRoomById/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Room getRoomById(@PathParam("id") Long id){
        return roomRepository.findRoomById(id);
    }

    @Path("updateRoom")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Room updateRoom(Room room){
        return roomRepository.updateRoom(room);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @DELETE
    @Path("deleteRoom/{id}")
    public void deleteRoom(@PathParam("id") Long id){
        roomRepository.deleteRoom(id);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("addRoom")
    public void addRoom(Room room){
        System.out.println(room.getRoomType());
        roomRepository.addRoom(room);
    }

}
