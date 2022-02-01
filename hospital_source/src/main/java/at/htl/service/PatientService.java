package at.htl.service;

import at.htl.control.ConditionRepository;
import at.htl.control.MedicalStaffRepository;
import at.htl.control.PatientRepository;
import at.htl.entity.*;
import at.htl.id.BedPatientId;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.List;
@ApplicationScoped
public class PatientService {
    private final PatientRepository patientRepository;
    private final ConditionRepository conditionRepository;
    private final MedicalStaffRepository medicalStaffRepository;
    private final BedService bedService;
    //private final BedRe patientRepository;

    public PatientService(PatientRepository patientRepository, ConditionRepository conditionRepository, MedicalStaffRepository medicalStaffRepository, BedService bedService) {
        this.patientRepository = patientRepository;
        this.conditionRepository = conditionRepository;
        this.medicalStaffRepository = medicalStaffRepository;
        this.bedService = bedService;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.getAllPatients();
    }

    public void addPatient(Patient patient) {
        patientRepository.addPatient(patient);
    }

    public Patient updatePatient(Patient patient) {
        return patientRepository.updatePatient(patient);
    }

    public void deletePatient(Long id) {
        patientRepository.deletePatient(id);
    }

    public Patient findPatientById(Long id) {
        return patientRepository.findPatientById(id);
    }

    public List<Patient> getPatientsForConditions(Long conditionId) {
        /*
        var p = patientRepository.findPatientById(patientId);
        return conditionRepository.getConditionsForPatient(p);

         */
        return null;
    }

    public BedPatient addBedForPatient(Long bedId, Long patientId) {
        Patient p = patientRepository.findPatientById(patientId);
        Bed b = bedService.findBedById(bedId);

        if(p == null)
            return null;

        if(b == null)
            return null;

        return bedService.addBedForPatient(b, p);
    }

    public PatientCondition addConditionForPatient(Long patientId, Long conditionId){
        Condition c = conditionRepository.findConditionById(conditionId);
        Patient p = patientRepository.findPatientById(patientId);

        if(p == null)
            return null;

        if(c == null)
            return null;

        return patientRepository.addPatientCondition(p, c);
    }

    public PatientMedicalStaff addMedicalStaffForPatient(Long medicalStaffId, Long patientId){
        Patient p = patientRepository.findPatientById(patientId);
        MedicalStaff m = medicalStaffRepository.findMedicalStaffById(medicalStaffId);

        if(p == null)
            return null;

        if(m == null)
            return null;

        return patientRepository.addMedicalStaffForPatient(p, m);
    }

    public void PatientLeavesBed(Long patientId, Long bedId){
        PatientLeavesBed(patientId, bedId, LocalDateTime.now());
    }

    public void PatientLeavesBed(Long patientId, Long bedId, LocalDateTime leaveTime){
        Patient p = patientRepository.findPatientById(patientId);
        Bed b = bedService.findBedById(bedId);

        if(p == null)
            return ;

        if(b == null)
            return ;

        BedPatient bp = null; //= bedService.findBedPatient(p, b);
        bp.setToDateTime(leaveTime);
    }

}