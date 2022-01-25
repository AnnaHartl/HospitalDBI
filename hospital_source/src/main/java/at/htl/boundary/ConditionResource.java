package at.htl.boundary;

import at.htl.control.ConditionRepository;
import at.htl.control.PatientRepository;
import at.htl.entity.Condition;
import at.htl.entity.Patient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("condition")
public class ConditionResource {
    private final ConditionRepository conditionRepository;
    private final PatientRepository patientRepository;

    public ConditionResource(ConditionRepository conditionRepository, PatientRepository patientRepository) {
        this.conditionRepository = conditionRepository;
        this.patientRepository = patientRepository;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("getAllConditions")
    public List<Condition> getAllConditions(){
        return conditionRepository.getAllConditions();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("addCondition")
    public void addCondition(Condition condition){
        conditionRepository.addCondition(condition);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PATCH
    @Path("updateCondition")
    public Condition updatePatient(Condition condition){
        return conditionRepository.updateCondition(condition);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @DELETE
    @Path("deleteCondition/{id}")
    public void deletePatient(@PathParam("id") Long id){
        conditionRepository.deleteCondition(id);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("getConditionById/{id}")
    public Condition getPatientId(@PathParam("id") Long id){
        return conditionRepository.findConditionById(id);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("getConditionsForPatient/{id}")
    public List<Condition> getConditionsForPatient(@PathParam("id") Long id){
        Patient p = patientRepository.findPatientById(id);

        return conditionRepository.getConditionsForPatient(p);
    }
}
