package at.htl.control;

import at.htl.dto.ConditionFilteredBySymptomDto;
import at.htl.dto.DoctorFilteredBySpecializationDto;
import at.htl.entity.MedicalStaff;
import at.htl.entity.Specialization;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
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

    public List<Specialization> getAllSpecializations(){
        return em.createQuery("select s from Specialization s", Specialization.class).getResultList();
    }

    public List<DoctorFilteredBySpecializationDto> getDoctorsFilteredBySpecializations(List<String> valStr) {
        var symptomIds = new ArrayList<Long>();
        for(String s : valStr) symptomIds.add(Long.valueOf(s));

        return getDoctorsFilteredBySpecializations(symptomIds);
    }

    public List<DoctorFilteredBySpecializationDto> getDoctorsFilteredBySpecializations(ArrayList<Long> specializationIds) {
        return em.createQuery("select new at.htl.dto.DoctorFilteredBySpecializationDto(d, count(s)) from Doctor" +
                        " d join d.specializations s where s.id in (:specializations) group by d order by count(s) desc", DoctorFilteredBySpecializationDto.class)
                .setParameter("specializations", specializationIds).getResultList();
    }


}
