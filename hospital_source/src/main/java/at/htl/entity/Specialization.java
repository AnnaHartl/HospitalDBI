package at.htl.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @JsonbTransient
    @ManyToMany(mappedBy = "specializations")
    private Set<Doctor> doctors = new HashSet<>();

    public Specialization(String name) {
        this.name = name;
    }

    public Specialization() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addDoctor(Doctor doctor, boolean addToDoctor){
        doctors.add(doctor);
        if(addToDoctor)
            doctor.addSpecialization(this, false);
    }
}
