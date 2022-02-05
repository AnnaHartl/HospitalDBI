package at.htl.control;

import at.htl.entity.Room;
import at.htl.entity.Station;
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
class StationRepositoryTest {

    private final StationRepository stationRepository;

    private final AgroalDataSource ds;

    StationRepositoryTest(StationRepository stationRepository, AgroalDataSource ds) {
        this.stationRepository = stationRepository;
        this.ds = ds;
    }

    @Test
    @Order(1)
    public void getAllStationsTest(){
        var stations = stationRepository.getAllStations();
        org.assertj.core.api.Assertions.assertThat(stations.size()).isEqualTo(15);
    }

    @Test
    @Order(2)
    public void getStationByIdTest(){
        var s1 = stationRepository.findStationById(1L);
        var s2 = stationRepository.findStationById(15L);
        var s3 = stationRepository.findStationById(9L);

        org.assertj.core.api.Assertions.assertThat(s1.getId()).isEqualTo(1);
        org.assertj.core.api.Assertions.assertThat(s1.getName()).isEqualTo("Neonatal Intensive Care");
        org.assertj.core.api.Assertions.assertThat(s1.getRooms().size()).isEqualTo(6);

        org.assertj.core.api.Assertions.assertThat(s2.getId()).isEqualTo(15);
        org.assertj.core.api.Assertions.assertThat(s2.getName()).isEqualTo("Long-term Care Wards");
        org.assertj.core.api.Assertions.assertThat(s2.getRooms().size()).isEqualTo(0);

        org.assertj.core.api.Assertions.assertThat(s3.getId()).isEqualTo(9);
        org.assertj.core.api.Assertions.assertThat(s3.getName()).isEqualTo("Pediatric");
        org.assertj.core.api.Assertions.assertThat(s3.getRooms().size()).isEqualTo(0);
    }

    @Test
    @Order(3)
    public void updateStationTest(){
        var s = stationRepository.findStationById(15L);
        s.setName("Long Term Care Wards");
        updateStation(s);

        Table sT = new Table(ds, "station");
        assertThat(sT).hasNumberOfRows(15)
                .row(14)
                .hasValues(s.getId(),
                        s.getName());
    }

    @Transactional
    private void updateStation(Station s){
        stationRepository.updateStation(s);
    }
}
