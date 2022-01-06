package at.htl.entity;

public class BedPatient {

    //region fields
    private Bed bed;
    private Patient patient;
    //endregion

    //region constructors
    public BedPatient(Bed bed, Patient patient) {
        this.bed = bed;
        this.patient = patient;
    }

    public BedPatient() {

    }
    //endregion

    //region getter and setter
    public Bed getBed() {
        return bed;
    }

    public void setBed(Bed bed) {
        this.bed = bed;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    //endregion
}
