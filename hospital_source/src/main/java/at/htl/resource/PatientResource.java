package at.htl.resource;

import at.htl.control.PatientRepository;
import at.htl.entity.Patient;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import org.jboss.logging.annotations.Pos;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("patientTemplate")
public class PatientResource {
    @Inject
    PatientRepository patientRepository;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance patients(List<Patient> patients, String filter);
        public static native TemplateInstance patientUpdate(Patient patient);
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
    public Response update(@PathParam("id") Long id,
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

        Templates.patientUpdate(p);

        return Response.status(301).location(URI.create("/patientTemplate")).build();
    }
}
