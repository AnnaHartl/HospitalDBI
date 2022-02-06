package at.htl.resource;

import at.htl.entity.Patient;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.Path;
import java.util.List;


public class HospitalResource {

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance hospital();
    }


}
