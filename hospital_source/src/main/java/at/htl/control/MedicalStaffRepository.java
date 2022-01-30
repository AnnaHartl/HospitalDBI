package at.htl.control;

import at.htl.entity.MedicalStaff;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class MedicalStaffRepository {

    private final EntityManager em;

    public MedicalStaffRepository(EntityManager em) {
        this.em = em;
    }

    //READ
    public List<MedicalStaff> getAllStaff(){
        return em.createQuery("select m from MedicalStaff m", MedicalStaff.class).getResultList();
    }

    //UPDATE
    @Transactional
    public MedicalStaff updateMedicalStaff(MedicalStaff medicalStaff){
        return em.merge(medicalStaff);
    }

    //READ
    public MedicalStaff findMedicalStaffById(Long id) {
        TypedQuery<MedicalStaff> query = em.createQuery("select m from MedicalStaff m where m.id = :id",
                MedicalStaff.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
