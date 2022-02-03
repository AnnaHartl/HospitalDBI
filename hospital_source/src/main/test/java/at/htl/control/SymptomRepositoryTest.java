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
}
