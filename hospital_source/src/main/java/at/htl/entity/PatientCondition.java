package at.htl.entity;

import at.htl.id.PatientConditionId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "PATIENT_CONDITION")
public class PatientCondition {
    @EmbeddedId
    PatientConditionId id;

    private LocalDate fromDate;
    private LocalDate toDate;

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public PatientConditionId getId() {
        return id;
    }

    public void setId(PatientConditionId id) {
        this.id = id;
    }
}
