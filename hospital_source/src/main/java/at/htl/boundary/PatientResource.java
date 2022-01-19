package at.htl.boundary;

import at.htl.control.PatientRepository;
import at.htl.entity.Patient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("patient")
public class PatientResource {
    private final PatientRepository patientRepository;

    public PatientResource(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
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
}
