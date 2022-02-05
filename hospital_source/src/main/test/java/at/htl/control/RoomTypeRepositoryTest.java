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
}
