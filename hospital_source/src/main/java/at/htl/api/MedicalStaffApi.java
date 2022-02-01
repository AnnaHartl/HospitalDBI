package at.htl.api;

import at.htl.entity.*;
import at.htl.service.MedicalStaffService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("api/staff")
public class MedicalStaffApi {
    private final MedicalStaffService medicalStaffService;

    public MedicalStaffApi(MedicalStaffService medicalStaffService) {
        this.medicalStaffService = medicalStaffService;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public List<MedicalStaff> getAllMedicalStaff(){
        return medicalStaffService.getAllStaff();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("doctor")
    public List<Doctor> getAllDoctors(){
        return medicalStaffService.getAllDoctors();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("nurse")
    public List<Nurse> getAllNurses(){
        return medicalStaffService.getAllNurses();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("doctor")
    public void addDoctor(Doctor doctor){
        medicalStaffService.addDoctor(doctor);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("nurse")
    public void addNurse(Nurse nurse){
        medicalStaffService.addNurse(nurse);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PATCH
    public MedicalStaff updateMedicalStaff(MedicalStaff medicalStaff){
        return medicalStaffService.updateMedicalStaff(medicalStaff);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @DELETE
    @Path("{id}")
    public void deleteMedicalStaff(@PathParam("id") Long id){
        medicalStaffService.deleteMedicalStaff(id);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("{id}")
    public MedicalStaff getMedicalStaffById(@PathParam("id") Long id){
        return medicalStaffService.findMedicalStaffById(id);
    }
}
