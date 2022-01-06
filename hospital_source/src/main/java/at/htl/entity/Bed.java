package at.htl.entity;

public class Bed {

    //region fields
    private int bedNumber;
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
    //endregion

}
