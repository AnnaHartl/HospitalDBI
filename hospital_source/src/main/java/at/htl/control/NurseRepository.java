package at.htl.control;

import at.htl.entity.Nurse;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class NurseRepository {

    private final EntityManager em;

    public NurseRepository(EntityManager em) {
        this.em = em;
    }

    //READ
    public List<Nurse> getAllNurses(){
        return em.createQuery("select n from Nurse n", Nurse.class).getResultList();
    }

    //CREATE
    @Transactional
    public void addNurse(Nurse condition){
        em.persist(condition);
    }

    //UPDATE
    @Transactional
    public Nurse updateNurse(Nurse condition){
        return em.merge(condition);
    }

    //DELETE
    @Transactional
    public void deleteNurse(Long id){
        Nurse c = findNurseById(id);
        em.remove(c);
    }

    //READ
    public Nurse findNurseById(Long id) {
        TypedQuery<Nurse> query = em.createQuery("select n from Nurse n where n.id = :id", Nurse.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
