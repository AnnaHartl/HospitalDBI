package at.htl.service;

import at.htl.control.SymptomRepository;
import at.htl.entity.Condition;
import at.htl.entity.Symptom;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class SymptomService {
    private final SymptomRepository symptomRepository;

    public SymptomService(SymptomRepository symptomRepository) {
        this.symptomRepository = symptomRepository;
    }

    public Symptom addSymptom(Symptom symptom){
        return symptomRepository.addSymptom(symptom);
    }

    public Symptom deleteSymptom(Long id){
        return symptomRepository.deleteSymptom(id);
    }

    public List<Symptom> getAllSymptoms(){
        return symptomRepository.getAllSymptoms();
    }

    public Symptom findSymptomById(Long id){
        return symptomRepository.findSymptomById(id);
    }

    public Symptom updateSymptom(Symptom symptom){
        return symptomRepository.updateSymptom(symptom);
    }
}
