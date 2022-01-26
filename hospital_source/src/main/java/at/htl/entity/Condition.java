package at.htl.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "condition_id")
    private Long id;

    private String name;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "symptom_condition",
            joinColumns = @JoinColumn(name = "condition_id"),
            inverseJoinColumns = @JoinColumn(name = "symptom_id")
    )
    Set<Symptom> symptoms = new HashSet<>();

    public Condition(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Condition() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(Set<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addSymptom(Symptom symptom, boolean addToSymptom){
        symptoms.add(symptom);
        if(addToSymptom)
            symptom.addCondition(this, false);
    }
}
