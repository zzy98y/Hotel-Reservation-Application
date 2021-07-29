package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private static ReservationService reservationService = null;
    private ReservationService(){}
    public static ReservationService getInstance() {
        if (null == reservationService) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }
    Collection<IRoom> rooms = new HashSet<>();
    Collection<Reservation> reservations = new HashSet<>();

    private void addRoom(IRoom room) {
        rooms.add(room);
    }

    public IRoom getARoom(String roomId) {
        for (IRoom room: rooms) {
            if (room.getRoomNumber().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

    public Reservation ReserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> specificRoom = new ArrayList<>();
        try {
            for (Reservation reservation: reservations) {
                if (reservation.getCheckInDate() == checkInDate
                        && reservation.getCheckOutDate() == checkOutDate) {
                    specificRoom.add(reservation.getRoom());
                }
            }
        }
        catch (Exception e) {
            if (specificRoom.isEmpty()) return null;
        }
        return specificRoom;
    }

    public Collection<Reservation> getCustomerReservation(Customer customer) {
        List<Reservation> customerReservation = new ArrayList<>();
        for (Reservation reservation: reservations){
            if (reservation.getCustomer().equals(customer)){
                customerReservation.add(reservation);
            }
        }
        return customerReservation;
    }

    public void printAllReservation() {
        for (Reservation reservation:reservations) {
            System.out.println(reservation);
        }
    }

    private Collection<IRoom> getAllRooms() {
        return rooms;
    }

     static boolean containReservation(Reservation reservation) {
        if (reservation.equals(reservation)) {
            return true;
        }
        return false;
    }





}
