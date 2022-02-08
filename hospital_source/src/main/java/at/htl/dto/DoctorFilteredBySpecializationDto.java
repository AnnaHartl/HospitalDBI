package at.htl.dto;

import at.htl.entity.Condition;
import at.htl.entity.Doctor;

public class DoctorFilteredBySpecializationDto {
    public Doctor doctor;
    public long amount;

    public DoctorFilteredBySpecializationDto(Doctor doctor, long amount) {
        this.doctor = doctor;
        this.amount = amount;
    }

    public DoctorFilteredBySpecializationDto(Doctor doctor) {
        this.doctor = doctor;
    }
}
