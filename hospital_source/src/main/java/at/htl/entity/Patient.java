package at.htl.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("P")
@PrimaryKeyJoinColumn(referencedColumnName = "person_id")
public class Patient extends Person {

    private double weight;
    private double height;

    @OneToMany(mappedBy = "id.patient", cascade = CascadeType.PERSIST)
    private List<PatientCondition> conditions;
    @OneToMany(mappedBy = "id.patient", cascade = CascadeType.PERSIST)
    private List<BedPatient> beds;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public List<PatientCondition> getConditions() {
        return conditions;
    }

    public void setConditions(List<PatientCondition> conditions) {
        this.conditions = conditions;
    }

    public List<BedPatient> getBeds() {
        return beds;
    }

    public void setBeds(List<BedPatient> beds) {
        this.beds = beds;
    }

    public String getSsn() {
        return super.getSsn();
    }

    public void setSsn(String ssn) {
        super.setSsn(ssn);
    }

    public Long getId() {
        return super.getId();
    }

    public void setId(Long id) {
        super.setId(id);
    }

    public String getFirstName() {
        return super.getFirstName();
    }

    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    public String getLastName() {
        return super.getLastName();
    }

    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }
}
