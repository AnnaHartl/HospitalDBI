package at.htl.control;

import at.htl.entity.Symptom;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
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
    public void addSymptom(Symptom symptom){
        em.persist(symptom);
    }

    @Transactional
    public void deleteSymptom(Long id){
        em.remove(findSymptomById(id));
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
