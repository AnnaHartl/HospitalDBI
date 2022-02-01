package at.htl.api;

import at.htl.entity.Station;
import at.htl.service.StationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("api/station")
public class StationApi {
    private final StationService stationService;

    public StationApi(StationService stationService) {
        this.stationService = stationService;
    }

    @Path("getAllStations")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Station> getAllStations(){
        return stationService.getAllStations();
    }

    @Path("findStationById/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Station getStationById(@PathParam("id") Long id){
        return stationService.findStationById(id);
    }

    @Path("updateStation")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Station updateStation(Station station){
        return stationService.updateStation(station);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @DELETE
    @Path("deleteStation/{id}")
    public void deleteStation(@PathParam("id") Long id){
        stationService.deleteStation(id);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("addStation")
    public void addStation(Station station){
        stationService.addStation(station);
    }

}
