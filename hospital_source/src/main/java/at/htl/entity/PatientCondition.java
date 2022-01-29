package at.htl.entity;

import at.htl.id.PatientConditionId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class PatientCondition {
    @EmbeddedId
    PatientConditionId id;

    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    public LocalDateTime getFromDateTime() {
        return fromDateTime;
    }

    public void setFromDateTime(LocalDateTime fromDateTime) {
        this.fromDateTime = fromDateTime;
    }

    public LocalDateTime getToDateTime() {
        return toDateTime;
    }

    public void setToDateTime(LocalDateTime toDateTime) {
        this.toDateTime = toDateTime;
    }

    public PatientConditionId getId() {
        return id;
    }

    public void setId(PatientConditionId id) {
        this.id = id;
    }
}
