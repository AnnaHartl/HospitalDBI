package at.htl.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int floorNumber;
    private int roomNumber;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private RoomType roomType;
    @JsonbTransient
    @ManyToOne
    private Station station;

    @JsonbTransient
    @OneToMany(mappedBy = "room", cascade = CascadeType.PERSIST)
    private List<Bed> beds = new ArrayList<>();

    public Room(int floorNumber, int roomNumber, RoomType roomType) {
        this.floorNumber = floorNumber;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
    }

    public Room() {
        
    }

    public void addBed(Room room, int bedNumber) {
        Bed bed = new Bed();
        bed.setRoom(this);
        bed.setBedNumber(bedNumber);

        this.beds.add(bed);
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public List<Bed> getBeds() {
        return beds;
    }

    public void setBeds(List<Bed> beds) {
        this.beds = beds;
    }

    //endregion

}
