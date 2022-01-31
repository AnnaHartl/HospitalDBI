package at.htl.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Symptom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String Name;
    @JsonbTransient
    @ManyToMany(mappedBy = "symptoms")
    Set<Condition> conditions = new HashSet<>();

    public Symptom(String name) {
        Name = name;
    }

    public Symptom() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Set<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(Set<Condition> conditions) {
        this.conditions = conditions;
    }

    public void addCondition(Condition condition, boolean addToCondition){
        conditions.add(condition);
        if(addToCondition)
            condition.addSymptom(this,false);
    }

    public void removeCondition(Condition condition, boolean removeFromCondition) {
        conditions.remove(condition);
        if(removeFromCondition)
            condition.removeSymptom(this, false);
    }
}
