package at.htl.control;

import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NurseRepositoryTest {

    private final NurseRepository nurseRepository;

    private final AgroalDataSource ds;

    NurseRepositoryTest(NurseRepository nurseRepository, AgroalDataSource ds) {
        this.nurseRepository = nurseRepository;
        this.ds = ds;
    }

    @Test
    public void getAllNurses(){
        var nurses = nurseRepository.getAllNurses();
        org.assertj.core.api.Assertions.assertThat(nurses.size()).isEqualTo(14);
    }

    @Test
    public void getDoctorByIdTest(){
        var n1 = nurseRepository.findNurseById(116L);
        var n2 = nurseRepository.findNurseById(130L);

        org.assertj.core.api.Assertions.assertThat(n1.getId()).isEqualTo(116);
        org.assertj.core.api.Assertions.assertThat(n1.getFirstName()).isEqualTo("Gary");

        org.assertj.core.api.Assertions.assertThat(n2.getId()).isEqualTo(130);
        org.assertj.core.api.Assertions.assertThat(n2.getFirstName()).isEqualTo("Donald");
    }
}
