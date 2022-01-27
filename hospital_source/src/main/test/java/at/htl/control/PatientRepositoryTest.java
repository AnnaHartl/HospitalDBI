package at.htl.control;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.derby.client.am.DateTime;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PatientRepositoryTest {
    private final PatientRepository patientRepository;

    PatientRepositoryTest(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Test
    @Order(1)
    public void getAllPatients_Test(){
        var patients = patientRepository.getAllPatients();
        org.assertj.core.api.Assertions.assertThat(patients.size()).isEqualTo(100);
    }

    @Test
    @Order(2)
    public void getPatientById_Test(){
        var patient1 = patientRepository.findPatientById(1L);
        var patient2 = patientRepository.findPatientById(100L);
        var patient3 = patientRepository.findPatientById(38L);
        var patient4 = patientRepository.findPatientById(67L);

        org.assertj.core.api.Assertions.assertThat(patient1.getFirstName()).isEqualTo("Shirley");
        org.assertj.core.api.Assertions.assertThat(patient1.getLastName()).isEqualTo("Whitsett");
        org.assertj.core.api.Assertions.assertThat(patient1.getSsn()).isEqualTo("2301200590");
        org.assertj.core.api.Assertions.assertThat(patient1.getDob()).isEqualTo("1990-05-20");

        org.assertj.core.api.Assertions.assertThat(patient2.getFirstName()).isEqualTo("John");
        org.assertj.core.api.Assertions.assertThat(patient2.getLastName()).isEqualTo("Macchiarella");
        org.assertj.core.api.Assertions.assertThat(patient2.getSsn()).isEqualTo("2131030380");
        org.assertj.core.api.Assertions.assertThat(patient2.getDob()).isEqualTo("1980-03-03");

        org.assertj.core.api.Assertions.assertThat(patient3.getFirstName()).isEqualTo("Kimberly");
        org.assertj.core.api.Assertions.assertThat(patient3.getLastName()).isEqualTo("Williams");
        org.assertj.core.api.Assertions.assertThat(patient3.getSsn()).isEqualTo("5945171215");
        org.assertj.core.api.Assertions.assertThat(patient3.getDob()).isEqualTo("2015-12-17");

        org.assertj.core.api.Assertions.assertThat(patient4.getFirstName()).isEqualTo("Sandra");
        org.assertj.core.api.Assertions.assertThat(patient4.getLastName()).isEqualTo("Hamrick");
        org.assertj.core.api.Assertions.assertThat(patient4.getSsn()).isEqualTo("9258251166");
        org.assertj.core.api.Assertions.assertThat(patient4.getDob()).isEqualTo("1966-11-25");
    }
}
