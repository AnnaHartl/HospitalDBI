package at.htl.id;

import at.htl.entity.Condition;
import at.htl.entity.Patient;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class PatientConditionId implements Serializable {

    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Condition condition;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
