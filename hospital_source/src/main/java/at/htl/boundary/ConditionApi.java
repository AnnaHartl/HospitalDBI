package at.htl.boundary;

import at.htl.control.ConditionRepository;
import at.htl.entity.Condition;
import at.htl.entity.Patient;
import at.htl.entity.Symptom;
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
    @PUT
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
    public List<Condition> getConditionsForPatient(@PathParam("id") Long patientId){
        return conditionService.getConditionsForPatient(patientId);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("addSymptom")
    public Symptom addSymptomToCondition(@QueryParam("condition_id") Long conditionId, @QueryParam("symptom_id") Long symptomId){
        return conditionService.addSymptom(conditionId, symptomId);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("deleteSymptom")
    public Symptom deleteSymptomFromCondition(@QueryParam("condition_id") Long conditionId, @QueryParam("symptom_id") Long symptomId){
        return conditionService.deleteSymptom(conditionId, symptomId);
    }
}
