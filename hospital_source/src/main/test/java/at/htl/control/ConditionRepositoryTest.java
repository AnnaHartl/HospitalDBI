package at.htl.control;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConditionRepositoryTest {
    private final ConditionRepository conditionRepository;

    ConditionRepositoryTest(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @Test
    @Order(1)
    public void getAllConditions_Test(){
        var conditions = conditionRepository.getAllConditions();
        org.assertj.core.api.Assertions.assertThat(conditions.size()).isEqualTo(12);
    }

    @Test
    @Order(2)
    public void getConditionById_Test(){
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
}
