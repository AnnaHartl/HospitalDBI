package at.htl.entity;

import at.htl.id.PatientConditionId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class PatientCondition {
    @EmbeddedId
    PatientConditionId id;

    public PatientConditionId getId() {
        return id;
    }

    public void setId(PatientConditionId id) {
        this.id = id;
    }
}
