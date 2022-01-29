package at.htl.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue(value="N")
@PrimaryKeyJoinColumn(referencedColumnName = "person_id")
public class Nurse extends MedicalStaff{

}
