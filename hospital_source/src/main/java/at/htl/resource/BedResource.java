package at.htl.resource;

import at.htl.entity.Bed;
import at.htl.entity.RoomType;
import at.htl.entity.Station;
import at.htl.service.BedService;
import at.htl.service.RoomTypeService;
import at.htl.service.StationService;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.List;

@Path("bedTemplate")
public class BedResource {
    private final BedService bedService;
    private final StationService stationService;
    private final RoomTypeService roomTypeService;

    public BedResource(BedService bedService, StationService stationService, RoomTypeService roomTypeService) {
        this.bedService = bedService;
        this.stationService = stationService;
        this.roomTypeService = roomTypeService;
    }

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance bed(List<Bed> beds, List<Station> stations,
                                                  List<RoomType> roomTypes);
    }

    @GET
    @Path("filter")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getBedsFiltered(@QueryParam("station") Station station,
                                            @QueryParam("room-type") RoomType roomType,
                                            @QueryParam("from") String fromStr,
                                            @QueryParam("to") String toStr) {
        var from = LocalDateTime.parse(fromStr);
        var to = LocalDateTime.parse(toStr);
        List<Bed> beds = bedService.findAvailableBeds(station.getId(),roomType.getId(),from,to);
        List<Station> stations = stationService.getAllStations();
        List<RoomType> roomTypes = roomTypeService.getAllRoomTypes();
        return BedResource.Templates.bed(beds,stations,roomTypes);
    }

    @GET()
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance clear() {
        List<Station> stations = stationService.getAllStations();
        List<RoomType> roomTypes = roomTypeService.getAllRoomTypes();
        return BedResource.Templates.bed(bedService.getAllBeds(),stations,roomTypes);
    }

    /*@POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public Response add(@FormParam("name") String  name,
                        @FormParam("desc") String desc
    ){
        Bed bed = new Bed();
        bedService.addBed(bed);
        return Response.status(301).location(URI.create("/bedTemplate")).build();
    }*/
}
