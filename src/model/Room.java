package model;

public class Room implements IRoom {

   public String roomNumber;
   public double price;
   public RoomType enumeration;

    public Room(String RoomNumber,double price, RoomType enumeration) {
        this.price = price;
        this.enumeration = enumeration;
    }


    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public boolean isFree() {
        return price == 0;
    }

    @Override
    public String toString() {
        return "The Room Number is " + roomNumber + " and the price is " + price  + " and it's a " + enumeration + " room.";
    }

}
