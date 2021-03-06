package at.htl.service;

import at.htl.control.DoctorRepository;
import at.htl.control.MedicalStaffRepository;
import at.htl.control.NurseRepository;
import at.htl.entity.*;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class MedicalStaffService {
    private final DoctorRepository doctorRepository;
    private final NurseRepository nurseRepository;
    private final MedicalStaffRepository medicalStaffRepository;

    public MedicalStaffService(DoctorRepository doctorRepository, NurseRepository nurseRepository,
                               MedicalStaffRepository medicalStaffRepository) {
        this.doctorRepository = doctorRepository;
        this.nurseRepository = nurseRepository;
        this.medicalStaffRepository = medicalStaffRepository;
    }

    public void addDoctor(Doctor doctor){
        doctorRepository.addDoctor(doctor);
    }

    public void addNurse(Nurse nurse){
        nurseRepository.addNurse(nurse);
    }

    public void deleteMedicalStaff(Long id){
        var person = findMedicalStaffById(id);
        if (person instanceof Doctor) {
            doctorRepository.deleteDoctor(id);
        } else if (person instanceof Nurse) {
            nurseRepository.deleteNurse(id);
        }
    }

    public MedicalStaff updateMedicalStaff(MedicalStaff medicalStaff){
        return medicalStaffRepository.updateMedicalStaff(medicalStaff);
    }

    public List<MedicalStaff> getAllStaff(){
        return medicalStaffRepository.getAllStaff();
    }

    public List<Doctor> getAllDoctors(){
        return doctorRepository.getAllDoctors();
    }

    public List<Nurse> getAllNurses(){
        return nurseRepository.getAllNurses();
    }

    public MedicalStaff findMedicalStaffById(Long id){
        return medicalStaffRepository.findMedicalStaffById(id);
    }
}
