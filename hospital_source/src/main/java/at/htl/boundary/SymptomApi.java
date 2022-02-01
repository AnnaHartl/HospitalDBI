package at.htl.boundary;

import at.htl.entity.Condition;
import at.htl.entity.Symptom;
import at.htl.service.SymptomService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("api/symptom")
public class SymptomApi {

    private final SymptomService symptomService;

    public SymptomApi(SymptomService symptomService) {
        this.symptomService = symptomService;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("getAll")
    public Response getAllSymptoms(){
        return Response.ok(symptomService.getAllSymptoms()).build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("{id}")
    public Response getSymptomById(@PathParam("id")Long id){
        return Response.ok(symptomService.findSymptomById(id)).build();
    }
    
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response addSymptom(Symptom symptom){
        var s = symptomService.addSymptom(symptom);
        return Response.created(URI.create("/api/symptom/" + s.getId())).build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @DELETE
    @Path("{id}")
    public Response deleteSymptom(@PathParam("id")Long id){
        return Response.ok(symptomService.deleteSymptom(id)).build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PUT
    public Response updateSymptom(Symptom symptom){
        return Response.ok(symptomService.updateSymptom(symptom)).build();
    }
}
