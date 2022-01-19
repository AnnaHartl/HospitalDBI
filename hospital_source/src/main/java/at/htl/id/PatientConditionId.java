package at.htl.id;

import at.htl.entity.Condition;
import at.htl.entity.Patient;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientConditionId that = (PatientConditionId) o;
        return Objects.equals(patient, that.patient) && Objects.equals(condition, that.condition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patient, condition);
    }
}
