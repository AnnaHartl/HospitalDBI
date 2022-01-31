package at.htl.service;

import at.htl.control.ConditionRepository;
import at.htl.control.PatientRepository;
import at.htl.control.SymptomRepository;
import at.htl.entity.Condition;
import at.htl.entity.Symptom;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ConditionService {
    private final ConditionRepository conditionRepository;
    private final SymptomRepository symptomRepository;
    private final PatientRepository patientRepository;

    public ConditionService(ConditionRepository conditionRepository, SymptomRepository symptomRepository, PatientRepository patientRepository) {
        this.conditionRepository = conditionRepository;
        this.symptomRepository = symptomRepository;
        this.patientRepository = patientRepository;
    }


    public List<Condition> getAllConditions() {
        return conditionRepository.getAllConditions();
    }

    public void addCondition(Condition condition) {
        conditionRepository.addCondition(condition);
    }

    public Condition updateCondition(Condition condition) {
        return conditionRepository.updateCondition(condition);
    }

    public void deleteCondition(Long id) {
        conditionRepository.deleteCondition(id);
    }

    public Condition findConditionById(Long id) {
        return conditionRepository.findConditionById(id);
    }

    public List<Condition> getConditionsForPatient(Long patientId) {
        var p = patientRepository.findPatientById(patientId);
        return conditionRepository.getConditionsForPatient(p);
    }

    public Symptom addSymptom(Long conditionId, Long symptomId) {
        var c = conditionRepository.findConditionById(conditionId);
        var s = symptomRepository.findSymptomById(symptomId);
        c.addSymptom(s, true);
        conditionRepository.updateCondition(c);
        return s;
    }
}
