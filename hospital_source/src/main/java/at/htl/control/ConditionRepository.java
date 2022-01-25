package at.htl.control;

import at.htl.entity.Condition;
import at.htl.entity.Patient;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

public class ConditionRepository {
    private final EntityManager em;

    public ConditionRepository(EntityManager em) {
        this.em = em;
    }

    //READ
    public List<Condition> getAllConditions(){
        return em.createQuery("select c from Condition c", Condition.class).getResultList();
    }

    //CREATE
    @Transactional
    public void addCondition(Condition condition){
        em.persist(condition);
    }

    //UPDATE
    @Transactional
    public Condition updateCondition(Condition condition){
        return em.merge(condition);
    }

    //DELETE
    @Transactional
    public void deleteCondition(Long id){
        Condition c = findConditionById(id);
        em.remove(c);
    }

    //READ
    public Condition findConditionById(Long id) {
        TypedQuery<Condition> query = em.createQuery("select c from Condition c where c.id = :id", Condition.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public List<Condition> getConditionsForPatient(Patient p) {
        return null;
    }
}
