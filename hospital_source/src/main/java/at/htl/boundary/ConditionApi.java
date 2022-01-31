package at.htl.boundary;

import at.htl.control.ConditionRepository;
import at.htl.entity.Condition;
import at.htl.entity.Patient;
import at.htl.service.ConditionService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("api/condition")
public class ConditionApi {
    private final ConditionService conditionService;

    public ConditionApi(ConditionService conditionService) {
        this.conditionService = conditionService;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("getAllConditions")
    public List<Condition> getAllConditions(){
        return conditionService.getAllConditions();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("addCondition")
    public void addCondition(Condition condition){
        conditionService.addCondition(condition);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PATCH
    @Path("updateCondition")
    public Condition updatePatient(Condition condition){
        return conditionService.updateCondition(condition);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @DELETE
    @Path("deleteCondition/{id}")
    public void deletePatient(@PathParam("id") Long id){
        conditionService.deleteCondition(id);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("getConditionById/{id}")
    public Condition getPatientId(@PathParam("id") Long id){
        return conditionService.findConditionById(id);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("getConditionsForPatient/{id}")
    public List<Condition> getConditionsForPatient(@PathParam("id") Long patient_id){
        return conditionService.getConditionsForPatient(patient_id);
    }
}
