package at.htl.entity;

public class Room {

    //region fields
    private int floorNumber;
    private int roomNumber;
    private String roomType;
    //endregion

    //region constructors
    public Room(int floorNumber, int roomNumber, String roomType) {
        this.floorNumber = floorNumber;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
    }

    public Room() {
        
    }
    //endregion

    //region getter and setter
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

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    //endregion

}
