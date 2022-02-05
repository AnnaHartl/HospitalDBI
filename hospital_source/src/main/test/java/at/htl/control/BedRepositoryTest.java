package at.htl.control;

import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BedRepositoryTest {

    private final BedRepository bedRepository;

    private final AgroalDataSource ds;

    BedRepositoryTest(BedRepository bedRepository, AgroalDataSource ds) {
        this.bedRepository = bedRepository;
        this.ds = ds;
    }

    @Test
    @Order(1)
    public void getAllBedsTest(){
        var beds = bedRepository.getAllBeds();
        org.assertj.core.api.Assertions.assertThat(beds.size()).isEqualTo(63);
    }
}
