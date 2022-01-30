package at.htl.entity;

import at.htl.id.PatientMedicalStaffId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "PATIENT_MEDICALSTAFF")
public class PatientMedicalStaff {
    @EmbeddedId
    PatientMedicalStaffId id;

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

    public Patient getPatient() {
        return id.getPatient();
    }

    public void setPatient(Patient patient) {
        id.setPatient(patient);
    }

    public MedicalStaff getMedicalStaff() {
        return id.getMedicalStaff();
    }

    public void setMedicalStaff(MedicalStaff medicalStaff) {
        id.setMedicalStaff(medicalStaff);
    }
}
