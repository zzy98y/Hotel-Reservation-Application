package model;

import java.util.Date;

public class Reservation {
     Customer customer;
     IRoom room;
     Date checkInDate;
     Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString() {
        return "The customer is " + customer + "and has " + room + " and check in date is " + checkInDate + " and " +
                "check out date will be " + checkOutDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Reservation))
            return false;

        Reservation otherReservation = (Reservation)o;

        boolean customerEquals = (this.customer == null && otherReservation.customer == null)
                || (this.customer != null && this.customer.equals(otherReservation.customer));
        boolean roomEquals = (this.room == null && otherReservation.room == null)
                || (this.room != null && this.room.equals(otherReservation.room));
        boolean checkInDateEquals = (this.checkInDate == null && otherReservation.checkInDate == null)
                || (this.checkInDate != null && this.checkInDate.equals(otherReservation.checkInDate));
        boolean checkOutDateEquals = (this.checkOutDate == null && otherReservation.checkOutDate == null)
                || (this.checkOutDate != null && this.checkOutDate.equals(otherReservation.checkOutDate));

        return customerEquals && roomEquals && checkInDateEquals && checkOutDateEquals;
    }

    @Override
    public final int hashCode() {
        int result = 17;
        if (customer != null) {
            result = 31 * result + customer.hashCode();
        }
        if (room != null) {
            result = 31 * result + room.hashCode();
        }
        if (checkInDate != null) {
            result = 31 * result + checkInDate.hashCode();
        }
        if (checkOutDate != null) {
            result = 31 * result + checkOutDate.hashCode();
        }
        return result;
    }



}
