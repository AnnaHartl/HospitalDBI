package at.htl.control;

import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DoctorRepositoryTest {

    private final DoctorRepository doctorRepository;

    private final AgroalDataSource ds;

    DoctorRepositoryTest(DoctorRepository doctorRepository, AgroalDataSource ds) {
        this.doctorRepository = doctorRepository;
        this.ds = ds;
    }

    @Test
    public void getAllDoctorsTest(){
        var doctors = doctorRepository.getAllDoctors();
        org.assertj.core.api.Assertions.assertThat(doctors.size()).isEqualTo(15);
    }

    @Test
    public void getDoctorByIdTest(){
        var d1 = doctorRepository.findDoctorById(101L);
        var d2 = doctorRepository.findDoctorById(115L);

        org.assertj.core.api.Assertions.assertThat(d1.getId()).isEqualTo(101);
        org.assertj.core.api.Assertions.assertThat(d1.getFirstName()).isEqualTo("Stephanie");

        org.assertj.core.api.Assertions.assertThat(d2.getId()).isEqualTo(115);
        org.assertj.core.api.Assertions.assertThat(d2.getFirstName()).isEqualTo("Kris");
    }
}
