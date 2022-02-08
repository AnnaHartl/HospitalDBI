package at.htl.resource;

import at.htl.control.*;
import at.htl.entity.*;
import at.htl.service.*;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Path("patientTemplate")
public class PatientResource {
    @Inject
    PatientRepository patientRepository;

    @Inject
    ConditionRepository conditionRepository;

    @Inject
    DoctorRepository doctorRepository;

    @Inject
    NurseRepository nurseRepository;

    private final RoomTypeService roomTypeService;
    private final StationService stationService;
    private final BedService bedService;
    private final PatientService patientService;
    private final ConditionService conditionService;

    public PatientResource(RoomTypeService roomTypeService, StationService stationService, BedService bedService, PatientService patientService, ConditionService conditionService) {
        this.roomTypeService = roomTypeService;
        this.stationService = stationService;
        this.bedService = bedService;
        this.patientService = patientService;
        this.conditionService = conditionService;
    }

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance patients(List<Patient> patients, String filter);

        public static native TemplateInstance patientUpdate(Patient patient);

        public static native TemplateInstance patientRecord(Patient patient);

        public static native TemplateInstance addCondition(Long patientId, List<Condition> conditions);

        public static native TemplateInstance newCondition(Long patientId);

        public static native TemplateInstance patientAdd();

        public static native TemplateInstance addBed(Long patientId, List<RoomType> roomTypes, List<Station> stations,
                                                     LocalDateTime from, LocalDateTime to, List<Bed> beds,
                                                     String fromStr, String toStr,
                                                     Long roomTypeId, Long stationId);
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
    public TemplateInstance showDetailView(@PathParam("id") Long patientId) {
        System.out.println("ID: " + patientId);
        return Templates.patientUpdate(patientRepository.findPatientById(patientId));
    }

    @POST
    @Path("update/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public TemplateInstance updatePatientRecord(@PathParam("id") Long id,
                                                @FormParam("fn") String fn,
                                                @FormParam("ln") String ln,
                                                @FormParam("height") double height,
                                                @FormParam("weight") double weight
    ) {
        System.out.println("Name: " + height);

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
    public TemplateInstance showDetailAddView() {
        return Templates.patientAdd();
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public TemplateInstance add(@FormParam("fn") String fn,
                                @FormParam("ln") String ln,
                                @FormParam("height") double height,
                                @FormParam("weight") double weight
    ) {
        System.out.println("Name: " + height);

        Patient p = new Patient();
        p.setFirstName(fn);
        p.setLastName(ln);
        p.setHeight(height);
        p.setWeight(weight);
        patientRepository.addPatient(p);
        //Long id = p.getId();

        return Templates.patientAdd();
    }

    @GET()
    @Path("record/{id}")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance showRecordView(@PathParam("id") Long patientId) {
        Patient p = patientRepository.findPatientById(patientId);
        return Templates.patientRecord(p);
    }

    @GET()
    @Path("addCondition/{id}")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance addConditionView(@PathParam("id") Long patientId) {
        return Templates.addCondition(patientId, conditionService.getAllConditions());
    }

    @GET()
    @Path("newCondition/{id}")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance newConditionView(@PathParam("id") Long patientId) {
        return Templates.newCondition(patientId);
    }

    @POST
    @Path("addNewCondition/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public TemplateInstance addNewCondition(@PathParam("id") Long id,
                                            @FormParam("name") String name,
                                            @FormParam("description") String description
    ) {

        conditionService.addCondition(new Condition(name, description));
        return Templates.addCondition(id, conditionService.getAllConditions());
    }

    @POST
    @Path("addPatientCondition/{patientId}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance addPatientCondition(@PathParam("patientId") Long patId,
                                                @FormParam("conditionId") Long conId
    ) {
        Patient p = patientService.findPatientById(patId);
        Condition c = conditionService.findConditionById(conId);
        patientRepository.addPatientCondition(p, c);
        return Templates.patientRecord(p);
    }

    @GET()
    @Path("addBed/filter/{id}")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance addBedViewFiltered(@PathParam("id") Long patientId,
                                               @DefaultValue("0") @QueryParam("room-type") Long roomTypeId,
                                               @DefaultValue("0") @QueryParam("station") Long stationId,
                                               @DefaultValue("") @QueryParam("start-date") String fromStr,
                                               @DefaultValue("") @QueryParam("end-date") String toStr) {
        LocalDateTime from = fromStr.isEmpty() ? null : convertToDateTime(fromStr);
        LocalDateTime to = toStr.isEmpty() ? null : convertToDateTime(toStr);


        return Templates.addBed(patientId, roomTypeService.getAllRoomTypes(),
                stationService.getAllStations(), from, to,
                bedService.findAvailableBeds(stationId, roomTypeId, from, to),fromStr,toStr,roomTypeId,stationId);
    }

    public static LocalDateTime convertToDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a").localizedBy(Locale.US);
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    @GET()
    @Path("addBed/{id}")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance addBedView(@PathParam("id") Long patientId) {
        return Templates.addBed(patientId, roomTypeService.getAllRoomTypes(),
                stationService.getAllStations(), null, null, bedService.getAllBeds(),
                null, null,
                roomTypeService.getAllRoomTypes().get(0).getId(), stationService.getAllStations().get(0).getId());
    }

    @POST
    @Path("addPatientBed/{patientId}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance addBedPatient(@PathParam("patientId") Long patId,
                                          @FormParam("bedId") Long bId,
                                          @QueryParam("start-date") String fromStr,
                                          @QueryParam("end-date") String toStr) {
        LocalDateTime from = fromStr.isEmpty() ? null : convertToDateTime(fromStr);
        LocalDateTime to = toStr.isEmpty() ? null : convertToDateTime(toStr);

        System.out.println(patId + "   " + bId);
        Patient p = patientService.findPatientById(patId);
        Bed bed = bedService.findBedById(bId);
        bedService.addBedForPatient(bed, p, from, to);
        //return Response.status(301).location(URI.create("/patientTemplate")).build();
        return Templates.patientRecord(p);
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

    @POST
    @Path("addNurse/{id}")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public TemplateInstance addNurse(@PathParam("id") Long id, @FormParam("fromDateTime") String fromDateTimeStr) {

        LocalDateTime fromDateTime = LocalDateTime.parse(fromDateTimeStr);

        List<Nurse> nurses = nurseRepository.getAllNurses();

        int number =(int) (Math.random() * nurses.size());
        System.out.println(number);
        Nurse nurse = nurses.get(number);

        patientService.addMedicalStaffForPatient(nurse.getId(),id, fromDateTime, null);
        return showRecordView(id);
    }
}
