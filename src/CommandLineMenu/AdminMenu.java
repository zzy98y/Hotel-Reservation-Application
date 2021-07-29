package CommandLineMenu;

import api.AdminResource;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    public static AdminResource adminResource = AdminResource.getInstance();
    public static Scanner scanner;

    public static void setAdminResource(AdminResource adminResource) {
        AdminMenu.adminResource = adminResource;
    }

    public static void startAdmin() {
        scanner = new Scanner(System.in);
        try {
            boolean exit = false;
            while (!exit) {
                String option = showAdminMenu();
                switch (option) {
                    case "1": System.out.println(adminResource.getAllCustomers());
                    case "2": System.out.println(adminResource.getAllRooms());
                    case "3": adminResource.displayAllReservations();
                    case "4": addRoom();
                    case "5": exit = true;
                    default: showAdminMenu();
                }
            }
            String[] arguments = new String[] {""};
            MainMenu.main(arguments);
        } catch(Exception e){
            e.getLocalizedMessage();
        } finally{
            scanner.close();
        }
    }

    private static String showAdminMenu() {
        System.out.println("=================================================");
        System.out.println("            Admin Menu");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.println("==================================================");
        System.out.println("Please choose a number from the menu :");
        return scanner.nextLine();
    }

    private static void addRoom() {
        List<IRoom> room = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter room number");
        String roomNumber = scanner.nextLine().trim();
        System.out.println("Enter price per night");
        double price = scanner.nextDouble();
        System.out.println("Enter room type: 1 for single,  2 for double bed");
        int type = scanner.nextInt();
        if (type == 1) {
            RoomType enumeration = RoomType.SINGLE;
            Room newRoom = new Room(roomNumber,price,enumeration);
            room.add(newRoom);
        } else {
            RoomType enumeration = RoomType.DOUBLE;
            Room newRoom = new Room(roomNumber,price,enumeration);
            room.add(newRoom);
        }

        adminResource.addRoom(room);
    }


}
