package at.htl.id;

import at.htl.entity.MedicalStaff;
import at.htl.entity.Patient;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;

@Embeddable
public class PatientMedicalStaffId implements Serializable {

    @ManyToOne
    private Patient patient;
    @ManyToOne
    private MedicalStaff medicalStaff;

    //region getter and setter
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public MedicalStaff getMedicalStaff() {
        return medicalStaff;
    }

    public void setMedicalStaff(MedicalStaff medicalStaff) {
        this.medicalStaff = medicalStaff;
    }
    //endregion

    //region constructors
    public PatientMedicalStaffId(Patient patient, MedicalStaff medicalStaff) {
        this.patient = patient;
        this.medicalStaff = medicalStaff;
    }

    public PatientMedicalStaffId() {

    }
    //endregion

}
