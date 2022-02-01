package at.htl.api;

import at.htl.entity.Room;
import at.htl.service.RoomService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("api/room")
public class RoomApi {
    private final RoomService roomService;

    public RoomApi(RoomService roomService) {
        this.roomService = roomService;
    }

    @Path("getAllRooms")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
    }

    @Path("findRoomById/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Room getRoomById(@PathParam("id") Long id){
        return roomService.findRoomById(id);
    }

    @Path("updateRoom")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Room updateRoom(Room room){
        return roomService.updateRoom(room);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @DELETE
    @Path("deleteRoom/{id}")
    public void deleteRoom(@PathParam("id") Long id){
        roomService.deleteRoom(id);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("addRoom")
    public void addRoom(Room room){
        System.out.println(room.getRoomType());
        roomService.addRoom(room);
    }

}
