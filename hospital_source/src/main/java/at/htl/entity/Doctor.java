package at.htl.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue(value="D")
@PrimaryKeyJoinColumn(referencedColumnName = "person_id")
public class Doctor extends MedicalStaff{

    //region fields
    @JsonbTransient
    @ManyToMany
    @JoinTable(
            name = "doctor_specialization",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "specialization_id")
    )
    private Set<Specialization> specializations = new HashSet<>();
    //endregion

    //region constructors
    public Doctor() {

    }
    //endregion

    //region getter and setter

    //endregion

    public void addSpecialization(Specialization specialization, boolean addToSpecialization){
        specializations.add(specialization);
        if(addToSpecialization)
            specialization.addDoctor(this, false);
    }
}
