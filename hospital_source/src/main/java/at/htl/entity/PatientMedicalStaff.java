package at.htl.entity;

import at.htl.id.PatientMedicalStaffId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PATIENT_MEDICALSTAFF")
public class PatientMedicalStaff {
    @EmbeddedId
    PatientMedicalStaffId id;

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
