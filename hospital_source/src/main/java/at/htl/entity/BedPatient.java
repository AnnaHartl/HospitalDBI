package at.htl.entity;

import at.htl.id.BedPatientId;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "BED_PATIENT")
public class BedPatient {

    @EmbeddedId
    private BedPatientId id;

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

    public BedPatientId getId() {
        return id;
    }

    public void setId(BedPatientId id) {
        this.id = id;
    }

}
