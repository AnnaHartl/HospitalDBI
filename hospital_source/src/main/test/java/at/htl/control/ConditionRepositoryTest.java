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

import javax.transaction.*;

import java.util.ArrayList;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConditionRepositoryTest {
    private final ConditionRepository conditionRepository;

    private final AgroalDataSource ds;

    ConditionRepositoryTest(ConditionRepository conditionRepository, AgroalDataSource ds) {
        this.conditionRepository = conditionRepository;
        this.ds = ds;
    }

    @Test
    @Order(1)
    public void getAllConditionsTest(){
        var conditions = conditionRepository.getAllConditions();
        org.assertj.core.api.Assertions.assertThat(conditions.size()).isEqualTo(12);
    }

    @Test
    @Order(2)
    public void getConditionByIdTest(){
        var condition1 = conditionRepository.findConditionById(1L);
        var condition2 = conditionRepository.findConditionById(5L);
        var condition3 = conditionRepository.findConditionById(12L);

        org.assertj.core.api.Assertions.assertThat(condition1.getName()).isEqualTo("Common cold");
        org.assertj.core.api.Assertions.assertThat(condition2.getName()).isEqualTo("Hay fever");
        org.assertj.core.api.Assertions.assertThat(condition3.getName()).isEqualTo("Gastroesophageal reflux disease (GERD)");

        org.assertj.core.api.Assertions.assertThat(condition1.getDescription()).isEqualTo("The common cold is a viral infection of your nose and throat (upper respiratory tract). It's usually harmless, although it might not feel that way. Many types of viruses can cause a common cold.");
        org.assertj.core.api.Assertions.assertThat(condition2.getDescription()).isEqualTo("");
        org.assertj.core.api.Assertions.assertThat(condition3.getDescription()).isEqualTo("Gastroesophageal reflux disease (GERD) occurs when stomach acid frequently flows back into the tube connecting your mouth and stomach (esophagus). This backwash (acid reflux) can irritate the lining of your esophagus.");

        org.assertj.core.api.Assertions.assertThat(condition1.getSymptoms().size()).isEqualTo(9);
        org.assertj.core.api.Assertions.assertThat(condition2.getSymptoms().size()).isEqualTo(7);
        org.assertj.core.api.Assertions.assertThat(condition3.getSymptoms().size()).isEqualTo(5);
    }

    @Test
    @Order(3)
    public void deleteConditionAndAddItAgainTest() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException, InterruptedException {
        Table cT = new Table(ds, "condition");

        assertThat(cT).hasNumberOfRows(12);

        var symptoms = new ArrayList<>(conditionRepository.findConditionById(12L).getSymptoms());

        var condition = deleteCondition(12L);

        cT = new Table(ds, "condition");
        assertThat(cT).hasNumberOfRows(11);

        Condition newCondition = new Condition(condition.getName(), condition.getDescription());
        for(Symptom s : symptoms){
            newCondition.addSymptom(s, true);
        }
        newCondition = addCondition(newCondition);

        cT = new Table(ds, "condition");
        assertThat(cT).hasNumberOfRows(12)
                .row(11)
                .hasValues(newCondition.getId(),
                        newCondition.getDescription(),
                        newCondition.getName());
    }

    @Test
    @Order(4)
    public void updateConditionTest(){
        var condition = conditionRepository.findConditionById(5L);
        condition.setDescription("Allergy");
        condition = updateCondition(condition);

        Table cT = new Table(ds, "condition");
        assertThat(cT).hasNumberOfRows(12)
                .row(4)
                .hasValues(condition.getId(),
                        condition.getDescription(),
                        condition.getName());
    }

    @Transactional
    private Condition updateCondition(Condition condition){
        return conditionRepository.updateCondition(condition);
    }

    @Transactional
    private Condition deleteCondition(Long id){
        return conditionRepository.deleteCondition(id);
    }

    @Transactional
    private Condition addCondition(Condition condition){
        return conditionRepository.addCondition(condition);
    }
}
