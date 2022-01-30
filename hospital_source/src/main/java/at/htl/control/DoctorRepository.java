package at.htl.control;

import at.htl.entity.Doctor;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class DoctorRepository {

    private final EntityManager em;

    public DoctorRepository(EntityManager em) {
        this.em = em;
    }

    //READ
    public List<Doctor> getAllDoctors(){
        return em.createQuery("select d from Doctor d", Doctor.class).getResultList();
    }

    //CREATE
    @Transactional
    public void addDoctor(Doctor condition){
        em.persist(condition);
    }

    //UPDATE
    @Transactional
    public Doctor updateDoctor(Doctor condition){
        return em.merge(condition);
    }

    //DELETE
    @Transactional
    public void deleteDoctor(Long id){
        Doctor c = findDoctorById(id);
        em.remove(c);
    }

    //READ
    public Doctor findDoctorById(Long id) {
        TypedQuery<Doctor> query = em.createQuery("select d from Doctor d where d.id = :id", Doctor.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
