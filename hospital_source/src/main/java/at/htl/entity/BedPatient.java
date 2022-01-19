package at.htl.entity;

import javax.persistence.*;

@Entity
public class BedPatient {

    @EmbeddedId
    private BedPatientId id;
    //region fields
    @ManyToOne
    private Bed bed;
    @ManyToOne
    private Patient patient;
    //endregion

    //region constructors
    public BedPatient(Bed bed, Patient patient) {
        this.bed = bed;
        this.patient = patient;
    }

    public BedPatient() {

    }
    //endregion

    //region getter and setter
    public Bed getBed() {
        return bed;
    }

    public void setBed(Bed bed) {
        this.bed = bed;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public BedPatientId getId() {
        return id;
    }

    public void setId(BedPatientId id) {
        this.id = id;
    }

    //endregion
}
