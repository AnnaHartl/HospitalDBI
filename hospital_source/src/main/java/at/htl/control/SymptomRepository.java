package at.htl.control;

import at.htl.entity.Condition;
import at.htl.entity.Symptom;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SymptomRepository {
    private final EntityManager em;

    public SymptomRepository(EntityManager em) {
        this.em = em;
    }

    public List<Symptom> getAllSymptoms(){
        return em.createQuery("Select s from Symptom s", Symptom.class)
                .getResultList();
    }

    @Transactional
    public Symptom addSymptom(Symptom symptom){
        em.persist(symptom);
        return symptom;
    }

    @Transactional
    public Symptom deleteSymptom(Long id){
        var s = findSymptomById(id);
        var conditions = new ArrayList<>(s.getConditions());
        for(Condition c : conditions){
            s.removeCondition(c,true);
        }
        em.remove(s);
        return s;
    }

    public Symptom findSymptomById(Long id){
        return em.createQuery("Select s from Symptom s where s.id = :id", Symptom.class)
                .setParameter("id", id).getResultList()
                .stream().findFirst().orElse(null);
    }

    @Transactional
    public Symptom updateSymptom(Symptom symptom){
        return em.merge(symptom);
    }
}
