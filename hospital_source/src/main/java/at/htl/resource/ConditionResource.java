package at.htl.resource;

import at.htl.control.ConditionRepository;
import at.htl.control.PatientRepository;
import at.htl.entity.Condition;
import at.htl.entity.Patient;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("conditionTemplate")
public class ConditionResource {
    @Inject
    ConditionRepository conditionRepository;
    @Inject
    PatientRepository patientRepository;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance condition(List<Condition> conditions, String filter, Long cid);
        public static native TemplateInstance conditionAdd();
    }

    @GET
    @Path("filter")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getConditions(@QueryParam("filter") String filter,
                                          @QueryParam("id") Long id) {
        System.out.println("Achtung: " + filter);
        List<Condition> c = conditionRepository.filterByName(filter);
        return ConditionResource.Templates.condition(c, filter,id);
    }

    @GET()
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance clear(@QueryParam("id") Long id) {
        return ConditionResource.Templates.condition(conditionRepository.getAllConditions(), "",id);
    }

    @GET()
    @Path("detailAdd")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance showDetailAdd(){
        return ConditionResource.Templates.conditionAdd();
    }

    @Path("addConToPat/{cid}/{pid}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_HTML)
    public Response addConToPat(@PathParam("cid") Long cid,
                                @PathParam("pid") Long pid){


        patientRepository.addPatientCondition(patientRepository.findPatientById(pid),
                conditionRepository.findConditionById(cid));
        return Response.status(301).location(URI.create("/patientTemplate")).build();

    }



    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public Response add(@FormParam("name") String  name,
                        @FormParam("desc") String desc
    ){
        Condition c = new Condition();
        c.setName(name);
        c.setDescription(desc);
        conditionRepository.addCondition(c);

        ConditionResource.Templates.conditionAdd();

        return Response.status(301).location(URI.create("/conditionTemplate")).build();
    }
}
