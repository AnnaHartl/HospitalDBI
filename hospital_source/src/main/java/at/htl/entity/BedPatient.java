package at.htl.entity;

import at.htl.id.BedPatientId;

import javax.persistence.*;

@Entity
public class BedPatient {

    @EmbeddedId
    private BedPatientId id;

    public BedPatientId getId() {
        return id;
    }

    public void setId(BedPatientId id) {
        this.id = id;
    }

}
