package model;

public class FreeRoom extends Room{
    public FreeRoom(String RoomNumber, double price, RoomType enumeration) {
        super(RoomNumber,0,enumeration);

    }

    @Override
    public String toString() {
        return "It's a free room.";
    }

}
