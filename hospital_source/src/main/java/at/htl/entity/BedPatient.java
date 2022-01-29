package at.htl.entity;

import at.htl.id.BedPatientId;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class BedPatient {

    @EmbeddedId
    private BedPatientId id;

    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    public BedPatientId getId() {
        return id;
    }

    public void setId(BedPatientId id) {
        this.id = id;
    }

}
