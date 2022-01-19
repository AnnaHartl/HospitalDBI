package at.htl.entity;

import javax.persistence.*;

@Entity
public class Bed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //region fields
    private int bedNumber;
    @ManyToOne
    private Room room;

    //endregion

    //region constructors
    public Bed(int bedNumber, Room room) {
        this.bedNumber = bedNumber;
        this.room = room;
    }

    public Bed() {

    }
    //endregion

    //region getter and setter
    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //endregion

}
