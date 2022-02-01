package at.htl.control;

import at.htl.entity.*;
import at.htl.id.PatientConditionId;
import at.htl.id.PatientMedicalStaffId;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class PatientRepository {
    private final EntityManager em;

    public PatientRepository(EntityManager em) {
        this.em = em;
    }

    public List<Patient> getAllPatients(){
        return em.createQuery("select p from Patient p", Patient.class).getResultList();
    }

    @Transactional
    public void addPatient(Patient patient){
        em.persist(patient);
    }

    @Transactional
    public Patient updatePatient(Patient patient){
        return em.merge(patient);
    }

    @Transactional
    public void deletePatient(Long id){
        Patient p = findPatientById(id);
        em.remove(p);
    }

    public Patient findPatientById(Long id) {
        TypedQuery<Patient> query = em.createQuery("select p from Patient p where p.id = :id", Patient.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Transactional
    public PatientCondition addPatientCondition(Patient p, Condition c) {
        PatientConditionId id = new PatientConditionId();
        id.setPatient(p);
        id.setCondition(c);
        PatientCondition pc = new PatientCondition();
        pc.setId(id);
        pc.setFromDateTime(LocalDateTime.now());
        return em.merge(pc);
    }

    public PatientMedicalStaff addMedicalStaffForPatient(Patient p, MedicalStaff m) {
        PatientMedicalStaffId id = new PatientMedicalStaffId();
        id.setPatient(p);
        id.setMedicalStaff(m);
        PatientMedicalStaff pm = new PatientMedicalStaff();
        pm.setId(id);
        return em.merge(pm);
    }
}
