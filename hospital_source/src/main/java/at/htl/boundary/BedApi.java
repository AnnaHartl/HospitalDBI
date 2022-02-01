package at.htl.boundary;

import at.htl.entity.Bed;
import at.htl.entity.Room;
import at.htl.service.BedService;
import at.htl.service.RoomService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("api/bed")
public class BedApi {
    private final BedService bedService;

    public BedApi(BedService bedService) {
        this.bedService = bedService;
    }

    @Path("getAllBeds")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bed> getAllBeds() {
        return bedService.getAllBeds();
    }

    @Path("findBedById/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Bed getBedById(@PathParam("id") Long id) {
        return bedService.findBedById(id);
    }

    @Path("updateBed")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Bed updateBed(Bed bed) {
        return bedService.updateBed(bed);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @DELETE
    @Path("deleteBed/{id}")
    public void deleteBed(@PathParam("id") Long id) {
        bedService.deleteBed(id);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("addBed")
    public void addBed(Bed bed) {
        bedService.addBed(bed);
    }
}
