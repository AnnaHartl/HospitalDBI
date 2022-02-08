package at.htl.resource;

import at.htl.control.MedicalStaffRepository;
import at.htl.control.PatientRepository;
import at.htl.dto.ConditionFilteredBySymptomDto;
import at.htl.dto.DoctorFilteredBySpecializationDto;
import at.htl.entity.*;
import at.htl.service.MedicalStaffService;
import at.htl.service.PatientService;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Path("doctorTemplate")
public class DoctorResource {

    @Inject
    PatientService patientService;

    @Inject
    MedicalStaffService medicalStaffService;

    @Inject
    MedicalStaffRepository medicalStaffRepository;

    @Inject
    PatientRepository patientRepository;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance filterDoctorBySpecialization(List<Specialization> specializations, List<Patient> patients, Long patientid);
        public static native TemplateInstance selectDoctor(List<DoctorFilteredBySpecializationDto> doctors, List<Patient> patients, Long patientid);
    }

    @POST
    @Path("addDoctorToPatient")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addDoctorToPatient(@FormParam("doctor") Long doctorId,
                                          @FormParam("patient") Long patientId,
                                          @FormParam("from-Date") String fromDateStr,
                                          @FormParam("to-Date") String toDateStr){
        LocalDate fromDate = LocalDate.parse(fromDateStr);
        LocalDate toDate = LocalDate.parse(toDateStr);

        var pc =  patientService.addMedicalStaffForPatient(doctorId, patientId);
        System.out.println("TEST");
        return Response.status(301).location(URI.create("/patientTemplate")).build();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.TEXT_HTML)
    @Path("getFilterBySpecializationsPage/{patient_id}")
    public TemplateInstance getFilterBySpecializationsPage(@PathParam("patient_id") Long patientId){
        return Templates.filterDoctorBySpecialization(medicalStaffRepository.getAllSpecializations(), patientRepository.getAllPatients(), patientId);
    }

    @GET
    @Path("filterBySpecializations")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance filterBySpecializations(@Context UriInfo uriInfo, @QueryParam("patient") Long patientId){

        var valStr = uriInfo.getQueryParameters().get("specialization");

        List<DoctorFilteredBySpecializationDto> specializations = medicalStaffRepository.getDoctorsFilteredBySpecializations(valStr);

        return Templates.selectDoctor(specializations, patientRepository.getAllPatients(), patientId);
    }
}
