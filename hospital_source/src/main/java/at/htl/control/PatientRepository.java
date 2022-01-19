package at.htl.control;

import at.htl.entity.Patient;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

    public void addPatient(Patient patient){
        em.persist(patient);
    }

    public Patient updatePatient(Patient patient){
        return em.merge(patient);
    }

    public void deletePatient(Long id){
        Patient p = findPatientById(id);
        em.remove(p);
    }

    public Patient findPatientById(Long id) {
        TypedQuery<Patient> query = em.createQuery("select p from Patient p where p.id = :id", Patient.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

}
