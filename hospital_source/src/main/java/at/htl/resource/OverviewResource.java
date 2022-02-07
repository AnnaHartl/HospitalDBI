package at.htl.resource;

import at.htl.control.PatientRepository;
import at.htl.entity.Patient;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class OverviewResource {

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance index();
    }

    @GET
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getOverview() {
        return Templates.index();
    }

}
