package at.htl.control;

import at.htl.dto.ConditionFilteredBySymptomDto;
import at.htl.entity.Condition;
import at.htl.entity.Patient;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
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
    public Condition addCondition(Condition condition){
        em.persist(condition);
        return condition;
    }

    //UPDATE
    @Transactional
    public Condition updateCondition(Condition condition){
        return em.merge(condition);
    }

    //DELETE
    @Transactional
    public Condition deleteCondition(Long id){
        Condition c = findConditionById(id);
        em.remove(c);
        return c;
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

    public List<Condition> filterByName(String filter) {
        TypedQuery<Condition> query = em.createQuery("select c from Condition c " +
                        "where LOWER(c.name) LIKE LOWER(:filter) " +
                        "or LOWER(c.description) LIKE LOWER(:filter)",
                Condition.class);
        query.setParameter("filter", '%' + filter + '%');
        return query.getResultList();
    }

    public List<ConditionFilteredBySymptomDto> getConditionsFilteredBySymptoms(ArrayList<Long> symptomIds) {
        return em.createQuery("select new at.htl.dto.ConditionFilteredBySymptomDto(c, count(s)) from Condition c" +
                " join c.symptoms s where s.id in (:symptoms) group by c order by count(s) desc", ConditionFilteredBySymptomDto.class)
                .setParameter("symptoms", symptomIds).getResultList();
    }
}
