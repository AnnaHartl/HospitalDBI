package at.htl.api;

import at.htl.entity.*;
import at.htl.service.ConditionService;
import at.htl.service.PatientService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("api/patient")
public class PatientApi {
    private final PatientService patientService;
    private final ConditionService conditionService;

    public PatientApi(PatientService patientService, ConditionService conditionService) {
        this.patientService = patientService;
        this.conditionService = conditionService;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("getAllPatients")
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("addPatient")
    public void addPatient(Patient patient){
        System.out.println(patient.getFirstName());
        patientService.addPatient(patient);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PUT
    @Path("updatePatient")
    public Patient updatePatient(Patient patient){
        return patientService.updatePatient(patient);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @DELETE
    @Path("deletePatient/{id}")
    public void deletePatient(@PathParam("id") Long id){
        patientService.deletePatient(id);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("getPatientById/{id}")
    public Patient getPatientId(@PathParam("id") Long id){
        return patientService.findPatientById(id);
    }


    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("addConditionForPatient")
    public PatientCondition addConditionForPatient(@QueryParam("patient") Long patientId,@QueryParam("condition") Long conditionId){
       return patientService.addConditionForPatient(patientId, conditionId);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("addMedicalStaffForPatient")
    public PatientMedicalStaff addMedicalStaffForPatient(@QueryParam("patient") Long patientId, @QueryParam("medicalstaff") Long medicalStaff){
        return patientService.addMedicalStaffForPatient(patientId, medicalStaff);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("addMedicalStaffForPatient")
    public BedPatient addBedForPatient(@QueryParam("patient") Long patientId, @QueryParam("bed") Long bedId){
        return patientService.addBedForPatient(patientId, bedId);
    }
}
