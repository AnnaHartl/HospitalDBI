package at.htl.control;

import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SymptomRepositoryTest {

    private final SymptomRepository symptomRepository;

    private final AgroalDataSource ds;

    SymptomRepositoryTest(SymptomRepository symptomRepository, AgroalDataSource ds) {
        this.symptomRepository = symptomRepository;
        this.ds = ds;
    }

   @Order(1)
   @Test
   public void getAllSymptomsTest(){
        var symptoms = symptomRepository.getAllSymptoms();
        org.assertj.core.api.Assertions.assertThat(symptoms.size()).isEqualTo(47);
   }
}
