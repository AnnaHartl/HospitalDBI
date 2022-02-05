package at.htl.control;

import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoomTypeRepositoryTest {

    private final RoomTypeRepository roomTypeRepository;

    private final AgroalDataSource ds;

    RoomTypeRepositoryTest(RoomTypeRepository roomTypeRepository, AgroalDataSource ds) {
        this.roomTypeRepository = roomTypeRepository;
        this.ds = ds;
    }

    @Test
    @Order(1)
    public void getAllRoomTypesTest(){
        var roomTypes = roomTypeRepository.getAllRoomTypes();
        org.assertj.core.api.Assertions.assertThat(roomTypes.size()).isEqualTo(17);
    }

    @Test
    @Order(2)
    public void getRoomTypeByIdTest(){
        var rt1 = roomTypeRepository.findRoomTypeById(1L);
        var rt2 = roomTypeRepository.findRoomTypeById(3L);
        var rt3 = roomTypeRepository.findRoomTypeById(17L);

        org.assertj.core.api.Assertions.assertThat(rt1.getId()).isEqualTo(1);
        org.assertj.core.api.Assertions.assertThat(rt1.getName()).isEqualTo("1-Bedroom");

        org.assertj.core.api.Assertions.assertThat(rt2.getId()).isEqualTo(3);
        org.assertj.core.api.Assertions.assertThat(rt2.getName()).isEqualTo("3-Bedroom");

        org.assertj.core.api.Assertions.assertThat(rt3.getId()).isEqualTo(17);
        org.assertj.core.api.Assertions.assertThat(rt3.getName()).isEqualTo("Intensive care surveillance rooms");
    }
}
