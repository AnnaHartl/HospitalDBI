package at.htl.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue(value="D")
@PrimaryKeyJoinColumn(referencedColumnName = "person_id")
public class Doctor extends MedicalStaff{

    //region fields
    private String specialization;
    //endregion

    //region constructors
    public Doctor(String specialization) {
        this.specialization = specialization;
    }

    public Doctor() {

    }
    //endregion

    //region getter and setter
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    //endregion
}
