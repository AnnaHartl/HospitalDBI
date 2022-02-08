package at.htl.resource;

import at.htl.control.*;
import at.htl.entity.*;
import at.htl.service.PatientService;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import org.jboss.logging.annotations.Pos;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Path("patientTemplate")
public class PatientResource {
    @Inject
    PatientRepository patientRepository;

    @Inject
    PatientService patientService;

    @Inject
    ConditionRepository conditionRepository;

    @Inject
    BedRepository bedRepository;

    @Inject
    DoctorRepository doctorRepository;

    @Inject
    NurseRepository nurseRepository;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance patients(List<Patient> patients, String filter);
        public static native TemplateInstance patientUpdate(Patient patient);
        public static native TemplateInstance patientRecord(Patient patient);
        public static native TemplateInstance addCondition(Long patientId, List<Condition> conditions);
        public static native TemplateInstance newCondition(Long patientId);
        public static native TemplateInstance patientAdd();
        public static native TemplateInstance addBed(Long patientId, List<Bed> beds);
        public static native TemplateInstance addDoctor(Long patientId, List<Doctor> doctors);
    }

    @GET
    @Path("filter")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getPersons(@QueryParam("filter") String filter) {
        System.out.println("Achtung: " + filter);
        List<Patient> p = patientRepository.filterByName(filter);
        return Templates.patients(p, filter);
    }

    @GET()
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance clear() {
        return Templates.patients(patientRepository.getAllPatients(), "");
    }

    @GET()
    @Path("detail/{id}")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance showDetail(@PathParam("id") Long patientId){
        System.out.println("ID: "+patientId);
        return Templates.patientUpdate(patientRepository.findPatientById(patientId));
    }

    @POST
    @Path("update/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public TemplateInstance update(@PathParam("id") Long id,
                                   @FormParam("fn") String  fn,
                                   @FormParam("ln") String ln,
                                   @FormParam("height") double height,
                                   @FormParam("weight") double weight
                                   ){
        System.out.println("Name: "+height);

        Patient p = patientRepository.findPatientById(id);
        p.setFirstName(fn);
        p.setLastName(ln);
        p.setHeight(height);
        p.setWeight(weight);

        return Templates.patientUpdate(p);
    }

    @GET()
    @Path("detailAdd")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance showDetailAdd(){
        return Templates.patientAdd();
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public TemplateInstance add(@FormParam("fn") String  fn,
                           @FormParam("ln") String ln,
                           @FormParam("height") double height,
                           @FormParam("weight") double weight
    ){
        System.out.println("Name: "+height);

        Patient p = new Patient();
        p.setFirstName(fn);
        p.setLastName(ln);
        p.setHeight(height);
        p.setWeight(weight);
        p = patientRepository.addPatient(p);

        Long i = p.getId();

        return Templates.patientAdd();
    }

    @GET()
    @Path("record/{id}")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance showRecord(@PathParam("id") Long patientId){
        Patient p = patientRepository.findPatientById(patientId);
        return Templates.patientRecord(p);
    }

    @GET()
    @Path("addCondition/{id}")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance addCondition(@PathParam("id") Long patientId){
        return Templates.addCondition(patientId, conditionRepository.getAllConditions());
    }

    @GET()
    @Path("newCondition/{id}")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance newCondition(@PathParam("id") Long patientId){
        return Templates.newCondition(patientId);
    }

    @POST
    @Path("addNewCondition/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public TemplateInstance addNewCondition(@PathParam("id") Long id,
                                   @FormParam("name") String  name,
                                   @FormParam("description") String description
    ){

        conditionRepository.addCondition(new Condition(name, description));
        return Templates.addCondition(id, conditionRepository.getAllConditions());
    }

    @POST
    @Path("addPatientCondition/{patientId}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance addPatientCondition(@PathParam("patientId") Long patId,
                                            @FormParam("conditionId") Long  conId
    ){
        Patient p = patientRepository.findPatientById(patId);
        Condition c = conditionRepository.findConditionById(conId);
        patientRepository.addPatientCondition(p, c);
        return Templates.patientRecord(p);
    }

    @GET()
    @Path("addBed/{id}")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance addBed(@PathParam("id") Long patientId){
        return Templates.addBed(patientId, bedRepository.getAllBeds());
    }


    @POST
    @Path("addPatientBed/{patientId}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public TemplateInstance addPatientBed(@PathParam("patientId") Long patId,
                                                @FormParam("bedId") Long  bId
    ){
        System.out.println(patId+"   "+bId);
        Patient p = patientRepository.findPatientById(patId);
        Bed bed = bedRepository.findBedById(bId);
        bedRepository.addBedForPatient(bed,p);
        return Templates.patientAdd();
    }


    @GET()
    @Path("addDoctor/{id}")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance addDoctor(@PathParam("id") Long patientId){
        return Templates.addDoctor(patientId, doctorRepository.getAllDoctors());
    }

    @POST
    @Path("addPatientDoctor/{patientId}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public TemplateInstance addPatientDoctor(@PathParam("patientId") Long patId,
                                          @FormParam("doctorId") Long  dId
    ){
        System.out.println(patId+"   "+dId);
        Patient p = patientRepository.findPatientById(patId);
        Doctor doctor = doctorRepository.findDoctorById(dId);
        patientRepository.addMedicalStaffForPatient(p, doctor, LocalDateTime.now(), LocalDateTime.now());
        return Templates.patientAdd();
    }

    @GET
    @Path("addNurse/{id}")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getAddNursePage(@PathParam("id") Long id) {
        List<Nurse> nurses = nurseRepository.getAllNurses();

        Random r = new Random();
        int number =(int) (Math.random() * nurses.size());
        System.out.println(number);
        Nurse nurse = nurses.get(number);

        PatientMedicalStaff pn =  patientService.addMedicalStaffForPatient(nurse.getId(),id, LocalDateTime.now(), LocalDateTime.now());
        return showRecord(id);
    }
}
