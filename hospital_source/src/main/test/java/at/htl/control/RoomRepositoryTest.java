package at.htl.control;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;


@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoomRepositoryTest {

    private final RoomRepository roomRepository;

    RoomRepositoryTest(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Test
    @Order(1)
    public void getAllRooms_Test() {
        var rooms = roomRepository.getAllRooms();
        org.assertj.core.api.Assertions.assertThat(rooms.size()).isEqualTo(35);
    }

}
