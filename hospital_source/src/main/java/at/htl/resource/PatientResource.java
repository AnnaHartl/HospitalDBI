package at.htl.resource;

import at.htl.control.PatientRepository;
import at.htl.entity.Patient;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("patientTemplate")
public class PatientResource {
    @Inject
    PatientRepository patientRepository;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance patients(List<Patient> patients, String filter);
    }

    @GET
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getPersons(@QueryParam("filter") String filter) {
        System.out.println("Achtung: " + filter);
        List<Patient> p = patientRepository.filterByName(filter);
        return Templates.patients(p, filter);
    }

    @GET()
    @Path("clear")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance clear() {
        return Templates.patients(patientRepository.getAllPatients(), "");
    }
}
