package at.htl.entity;

public class Condition {
    private String name;
    private String description;
    private String symptoms;

    public Condition(String name, String description, String symptoms) {
        this.name = name;
        this.description = description;
        this.symptoms = symptoms;
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

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }
}
