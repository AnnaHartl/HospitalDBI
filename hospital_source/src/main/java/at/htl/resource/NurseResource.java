package at.htl.resource;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("nurseTemplate")
public class NurseResource {

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance addNurse();
    }

    @GET
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getAddNursePage() {
        return NurseResource.Templates.addNurse();
    }
}
