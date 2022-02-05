package at.htl.control;

import at.htl.entity.Room;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.*;

import javax.transaction.Transactional;

import static org.assertj.db.api.Assertions.assertThat;


@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoomRepositoryTest {

    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;

    private final AgroalDataSource ds;

    RoomRepositoryTest(RoomRepository roomRepository, RoomTypeRepository roomTypeRepository, AgroalDataSource ds) {
        this.roomRepository = roomRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.ds = ds;
    }

    @Test
    @Order(1)
    public void getAllRooms_Test() {
        var rooms = roomRepository.getAllRooms();
        org.assertj.core.api.Assertions.assertThat(rooms.size()).isEqualTo(35);
    }

    @Test
    @Order(2)
    public void getRoomById_Test(){
        var room1 = roomRepository.findRoomById(1L);
        var room2 = roomRepository.findRoomById(32L);
        var room3 = roomRepository.findRoomById(29L);
        var room4 = roomRepository.findRoomById(20L);
        var room5 = roomRepository.findRoomById(9L);
        var room6 = roomRepository.findRoomById(16L);
        var room7 = roomRepository.findRoomById(12L);
        var room8 = roomRepository.findRoomById(35L);

        org.assertj.core.api.Assertions.assertThat(room1.getRoomNumber()).isEqualTo(1);
        org.assertj.core.api.Assertions.assertThat(room2.getRoomNumber()).isEqualTo(32);
        org.assertj.core.api.Assertions.assertThat(room3.getRoomNumber()).isEqualTo(29);
        org.assertj.core.api.Assertions.assertThat(room4.getRoomNumber()).isEqualTo(20);
        org.assertj.core.api.Assertions.assertThat(room5.getRoomNumber()).isEqualTo(9);
        org.assertj.core.api.Assertions.assertThat(room6.getRoomNumber()).isEqualTo(16);
        org.assertj.core.api.Assertions.assertThat(room7.getRoomNumber()).isEqualTo(12);
        org.assertj.core.api.Assertions.assertThat(room8.getRoomNumber()).isEqualTo(35);

        org.assertj.core.api.Assertions.assertThat(room1.getStation().getId()).isEqualTo(1);
        org.assertj.core.api.Assertions.assertThat(room2.getStation().getId()).isEqualTo(13);
        org.assertj.core.api.Assertions.assertThat(room3.getStation().getId()).isEqualTo(13);
        org.assertj.core.api.Assertions.assertThat(room4.getStation().getId()).isEqualTo(8);
        org.assertj.core.api.Assertions.assertThat(room5.getStation().getId()).isEqualTo(2);
        org.assertj.core.api.Assertions.assertThat(room6.getStation().getId()).isEqualTo(6);
        org.assertj.core.api.Assertions.assertThat(room7.getStation().getId()).isEqualTo(4);
        org.assertj.core.api.Assertions.assertThat(room8.getStation().getId()).isEqualTo(13);

        org.assertj.core.api.Assertions.assertThat(room1.getFloorNumber()).isEqualTo(0);
        org.assertj.core.api.Assertions.assertThat(room2.getFloorNumber()).isEqualTo(2);
        org.assertj.core.api.Assertions.assertThat(room3.getFloorNumber()).isEqualTo(2);
        org.assertj.core.api.Assertions.assertThat(room4.getFloorNumber()).isEqualTo(1);
        org.assertj.core.api.Assertions.assertThat(room5.getFloorNumber()).isEqualTo(0);
        org.assertj.core.api.Assertions.assertThat(room6.getFloorNumber()).isEqualTo(1);
        org.assertj.core.api.Assertions.assertThat(room7.getFloorNumber()).isEqualTo(1);
        org.assertj.core.api.Assertions.assertThat(room8.getFloorNumber()).isEqualTo(2);

        org.assertj.core.api.Assertions.assertThat(room1.getBeds().size()).isEqualTo(1);
        org.assertj.core.api.Assertions.assertThat(room2.getBeds().size()).isEqualTo(3);
        org.assertj.core.api.Assertions.assertThat(room3.getBeds().size()).isEqualTo(2);
        org.assertj.core.api.Assertions.assertThat(room4.getBeds().size()).isEqualTo(1);
        org.assertj.core.api.Assertions.assertThat(room5.getBeds().size()).isEqualTo(2);
        org.assertj.core.api.Assertions.assertThat(room6.getBeds().size()).isEqualTo(1);
        org.assertj.core.api.Assertions.assertThat(room7.getBeds().size()).isEqualTo(1);
        org.assertj.core.api.Assertions.assertThat(room8.getBeds().size()).isEqualTo(4);
    }

    @Test
    @Order(3)
    public void updateRoomTest(){
        var r = roomRepository.findRoomById(1L);
        r.setRoomType(roomTypeRepository.findRoomTypeById(2L));

        updateRoom(r);

        Table rT = new Table(ds, "room");
        assertThat(rT).hasNumberOfRows(35)
                .row(0)
                .hasValues(r.getId(),
                        r.getFloorNumber(),
                        r.getRoomNumber(),
                        r.getRoomType().getId(),
                        r.getStation().getId());

    }

    @Transactional
    private void updateRoom(Room room){
        roomRepository.updateRoom(room);
    }

}
