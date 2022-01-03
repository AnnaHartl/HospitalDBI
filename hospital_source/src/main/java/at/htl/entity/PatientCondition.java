package at.htl.entity;

public class PatientCondition {
    Patient patient;
    Condition condition;

    public PatientCondition(Patient patient, Condition condition) {
        this.patient = patient;
        this.condition = condition;
    }

    public PatientCondition() {
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
