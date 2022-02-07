package at.htl.api;

import at.htl.entity.RoomType;
import at.htl.service.RoomTypeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("api/roomType")
public class RoomTypeApi {
    private final RoomTypeService roomTypeService;

    public RoomTypeApi(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    @Path("getAllRoomTypes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RoomType> getAllRoomTypes() {
        return roomTypeService.getAllRoomTypes();
    }

    @Path("findRoomTypeById/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RoomType getRoomTypeById(@PathParam("id") Long id) {
        return roomTypeService.findRoomTypeById(id);
    }

    @Path("updateRoomType")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public RoomType updateRoomType(RoomType roomType) {
        return roomTypeService.updateRoomType(roomType);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @DELETE
    @Path("deleteRoomType/{id}")
    public void deleteRoomType(@PathParam("id") Long id) {
        roomTypeService.deleteRoomType(id);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("addRoomType")
    public Response addRoomType(RoomType roomType) {
        return Response.ok(roomTypeService.addRoomType(roomType)).build();
    }
}
