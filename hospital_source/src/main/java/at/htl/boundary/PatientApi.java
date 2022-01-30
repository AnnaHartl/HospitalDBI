package at.htl.boundary;

import at.htl.control.ConditionRepository;
import at.htl.control.PatientRepository;
import at.htl.entity.Condition;
import at.htl.entity.Patient;
import at.htl.entity.PatientCondition;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("api/patient")
public class PatientApi {
    private final PatientRepository patientRepository;
    private final ConditionRepository conditionRepository;

    public PatientApi(PatientRepository patientRepository, ConditionRepository conditionRepository) {
        this.patientRepository = patientRepository;
        this.conditionRepository = conditionRepository;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("getAllPatients")
    public List<Patient> getAllPatients(){
        return patientRepository.getAllPatients();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("addPatient")
    public void addPatient(Patient patient){
        System.out.println(patient.getFirstName());
        patientRepository.addPatient(patient);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PATCH
    @Path("updatePatient")
    public Patient updatePatient(Patient patient){
        return patientRepository.updatePatient(patient);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @DELETE
    @Path("deletePatient/{id}")
    public void deletePatient(@PathParam("id") Long id){
        patientRepository.deletePatient(id);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("getPatientById/{id}")
    public Patient getPatientId(@PathParam("id") Long id){
        return patientRepository.findPatientById(id);
    }


    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("addConditionForPatient")
    public PatientCondition addConditionForPatient(@QueryParam("patient") Long patientId,@QueryParam("condition") Long conditionId){
        Patient p = patientRepository.findPatientById(patientId);
        Condition c = conditionRepository.findConditionById(conditionId);

        if(p == null)
            return null;

        if(c == null)
            return null;

       return patientRepository.addPatientCondition(p, c);
    }
}
