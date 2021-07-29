package CommandLineMenu;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {
    public static AdminResource adminResource = AdminResource.getInstance();
    public static HotelResource hotelResource = HotelResource.getInstance();
    public static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        try {
            boolean exit = false;
            while (!exit) {
                String selection = showMenus();
                switch(selection) {
                    case "1": findAndReserveARoom();
                    case "2": seeMyReservation();
                    case "3": createAnAccount();
                    case "4": {
                        AdminMenu.setAdminResource(adminResource);
                        AdminMenu.startAdmin();
                    }
                    case "5": exit = true;
                    default: showMenus();
                }
            }
            System.exit(0);
        } catch (Exception e) {
            e.getLocalizedMessage();
        } finally {
            scanner.close();
        }
    }


    private static String showMenus() {
        System.out.println("==============================================");
        System.out.println("              Main Menu");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an Account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("==============================================");
        System.out.println("Please choose a number from the menu: ");
        return scanner.nextLine();

    }

    private static void findAndReserveARoom() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        System.out.println("Enter CheckIn Date dd/mm/yyyy");
        Date checkInDate = dateFormat.parse(scanner.nextLine());
        System.out.println("Enter CheckOut Date dd/mm/yyyy");
        Date checkOutDate = dateFormat.parse(scanner.nextLine());
        Collection<IRoom> availableRooms =  hotelResource.findARoom(checkInDate, checkOutDate);

        if(!availableRooms.isEmpty()){
            Customer customer;

            System.out.println("Would you like to book a room? y/n");
            char optionBookARoom = scanner.next().trim().charAt(0);

            if(optionBookARoom == 'y'){
                System.out.println("Do you have an account with us? y/n");
                char optionHasAccount = scanner.next().trim().charAt(0);

                if(optionHasAccount == 'y'){
                    customer = getExistingAccount();

                    if(customer == null){
                        System.out.println("Customer was not found.");
                        return;
                    }

                } else {
                    customer = createAnAccount();
                }

                boolean isRoomAvailable = false;
                while (!isRoomAvailable) {
                    System.out.println("Available rooms:");
                    System.out.println(availableRooms);
                    System.out.println("Please enter room number from the available rooms:");
                    String roomNumber = scanner.next();
                    IRoom selectedRoom = hotelResource.getRoom(roomNumber);

                    if(!availableRooms.contains(selectedRoom)){
                        System.out.println("Sorry, room number '" + roomNumber + "' is not available.");
                    } else {
                        hotelResource.bookARoom(customer, selectedRoom, checkInDate, checkOutDate);
                        System.out.println("Reservation was successfully created!");
                        scanner.nextLine();
                        isRoomAvailable = true;
                    }
                }
            }
        } else {
            System.out.println("Sorry there are no rooms available.");
        }

    }



    private static void seeMyReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Customer Information");
        System.out.println("Enter first name:");
        String firstName = scanner.next();
        System.out.println("Enter last name:");
        String lastName = scanner.next();
        System.out.println("Enter the email, format:name@domain.com:");
        String email = scanner.next();
        Customer customer = new Customer(firstName,lastName,email);
        System.out.println(hotelResource.getCustomerReservation(customer));
    }


    private static Customer createAnAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Customer First Name:");
        String firstName = scanner.next();
        System.out.println("Enter Customer Last Name:");
        String lastName = scanner.next();
        System.out.println("Enter Customer Email format:name@domain.com:");
        String email = scanner.next();

        try {
            hotelResource.createACustomer(email, firstName, lastName);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return new Customer(firstName, lastName, email);
    }

    private static Customer getExistingAccount(){
        System.out.println("Enter Email format:name@domain.com");
        String email = scanner.next();

        return hotelResource.getCustomer(email);
    }


}


