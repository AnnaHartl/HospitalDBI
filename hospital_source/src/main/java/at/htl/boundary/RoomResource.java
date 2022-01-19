package at.htl.boundary;

import at.htl.control.RoomRepository;
import at.htl.entity.Room;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("room")
public class RoomResource {
    private final RoomRepository roomRepository;

    public RoomResource(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Room> getAllRooms(){
        return roomRepository.getAllRooms();
    }
}
