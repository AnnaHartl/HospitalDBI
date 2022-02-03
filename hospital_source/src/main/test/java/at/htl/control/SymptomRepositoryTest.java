package at.htl.control;

import at.htl.entity.Condition;
import at.htl.entity.Symptom;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.transaction.Transactional;

import java.util.ArrayList;

import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SymptomRepositoryTest {

    private final SymptomRepository symptomRepository;
    private final ConditionRepository conditionRepository;

    private final AgroalDataSource ds;

    SymptomRepositoryTest(SymptomRepository symptomRepository, ConditionRepository conditionRepository, AgroalDataSource ds) {
        this.symptomRepository = symptomRepository;
        this.conditionRepository = conditionRepository;
        this.ds = ds;
    }

    @Order(1)
    @Test
    public void getAllSymptomsTest(){
        var symptoms = symptomRepository.getAllSymptoms();
        org.assertj.core.api.Assertions.assertThat(symptoms.size()).isEqualTo(47);
    }

    @Order(2)
    @Test
    public void getSymptomByIdTest(){
        var s1 = symptomRepository.findSymptomById(1L);
        var s2 = symptomRepository.findSymptomById(47L);
        var s3 = symptomRepository.findSymptomById(23L);

        org.assertj.core.api.Assertions.assertThat(s1.getName()).isEqualTo("Runny or stuffy nose");
        org.assertj.core.api.Assertions.assertThat(s1.getId()).isEqualTo(1);
        org.assertj.core.api.Assertions.assertThat(s1.getConditions().size()).isEqualTo(3);

        org.assertj.core.api.Assertions.assertThat(s2.getName()).isEqualTo("Sensation of a lump in your throat");
        org.assertj.core.api.Assertions.assertThat(s2.getId()).isEqualTo(47);
        org.assertj.core.api.Assertions.assertThat(s2.getConditions().size()).isEqualTo(1);

        org.assertj.core.api.Assertions.assertThat(s3.getName()).isEqualTo("Itching of the eyes");
        org.assertj.core.api.Assertions.assertThat(s3.getId()).isEqualTo(23);
        org.assertj.core.api.Assertions.assertThat(s3.getConditions().size()).isEqualTo(1);
    }

    @Order(3)
    @Test
    public void deleteSymptomAndAddItAgainTest(){
        Table sT = new Table(ds, "symptom");

        assertThat(sT).hasNumberOfRows(47);

        var conditions = new ArrayList<>(symptomRepository.findSymptomById(1L).getConditions());

        var symptom = deleteSymptom(1L);

        sT = new Table(ds, "symptom");
        assertThat(sT).hasNumberOfRows(46);

        Symptom newSymptom = new Symptom();
        newSymptom.setName(symptom.getName());
        for(Condition c : conditions){
            newSymptom.addCondition(c, true);
        }
        newSymptom = addSymptom(newSymptom);

        sT = new Table(ds, "symptom");
        assertThat(sT).hasNumberOfRows(47)
                .row(46)
                .hasValues(newSymptom.getId(),
                        newSymptom.getName());
    }

    private void updateCondition(Condition condition){
        conditionRepository.updateCondition(condition);
    }

    @Transactional
    private Symptom addSymptom(Symptom symptom) {
        var s = symptomRepository.addSymptom(symptom);
        for(Condition c  : s.getConditions()){
            updateCondition(c);
        }
        return s;
    }

    @Transactional
    public Symptom deleteSymptom(Long id){
        return symptomRepository.deleteSymptom(id);
    }
}
