package at.htl.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BedPatientId implements Serializable {
    @ManyToOne
    private Bed bed;

    @ManyToOne
    private Patient patient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BedPatientId that = (BedPatientId) o;
        return Objects.equals(bed, that.bed) && Objects.equals(patient, that.patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bed, patient);
    }

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
}
