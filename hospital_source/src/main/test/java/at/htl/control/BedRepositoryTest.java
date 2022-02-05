package at.htl.control;

import at.htl.entity.Bed;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.transaction.Transactional;

import static org.assertj.db.api.Assertions.assertThat;

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

    @Test
    @Order(2)
    public void getBedByIdTest(){
        var b1 = bedRepository.findBedById(1L);
        var b2 = bedRepository.findBedById(32L);
        var b3 = bedRepository.findBedById(63L);

        org.assertj.core.api.Assertions.assertThat(b1.getId()).isEqualTo(1);
        org.assertj.core.api.Assertions.assertThat(b1.getBedNumber()).isEqualTo(1);
        org.assertj.core.api.Assertions.assertThat(b1.getRoom().getId()).isEqualTo(1);

        org.assertj.core.api.Assertions.assertThat(b2.getId()).isEqualTo(32);
        org.assertj.core.api.Assertions.assertThat(b2.getBedNumber()).isEqualTo(32);
        org.assertj.core.api.Assertions.assertThat(b2.getRoom().getId()).isEqualTo(21);

        org.assertj.core.api.Assertions.assertThat(b3.getId()).isEqualTo(63);
        org.assertj.core.api.Assertions.assertThat(b3.getBedNumber()).isEqualTo(62);
        org.assertj.core.api.Assertions.assertThat(b3.getRoom().getId()).isEqualTo(35);
    }

    @Test
    @Order(3)
    public void updateBedTest(){
        var b = bedRepository.findBedById(63L);
        b.setBedNumber(64);

        updateBed(b);

        Table bT = new Table(ds, "bed");
        assertThat(bT).hasNumberOfRows(63)
                .row(62)
                .hasValues(b.getId(),
                        b.getBedNumber(),
                        b.getRoom().getId());
    }

    @Transactional
    private void updateBed(Bed bed){
        bedRepository.updateBed(bed);
    }
}
