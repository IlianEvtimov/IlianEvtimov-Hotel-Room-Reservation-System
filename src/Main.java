import Account.CreateUserAccount;
import Account.UserLoginValidation;
import DataTime.BookingDateValidation;
import Database.SaveAllData;
import Dto.HotelBookedHistory;
import Dto.User;
import Features.DisplayFeature;
import HotesDataase.AddRoom;
import HotesDataase.LoadHotelRooms;
import Rooms.Room;
import SQLTable.HotelSQL;
import SQLTable.UserSQL;
import UserDatabase.LoadUserDatabase;
import UserOperations.Booking;
import UserOperations.CancelBooking;

import java.nio.file.Path;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {

        System.out.println(System.getProperties());
        Path path = Path.of("");
        String root = path.toAbsolutePath().toString();
        String hotelDatabasePath = root + "\\src\\Files\\hotelDatabaseSerializable.txt";
        String userDatabasePath = root + "\\src\\Files\\userDatabaseSerializable.txt";

        LoadHotelRooms loadHotelRooms = new LoadHotelRooms();
        // Loading the database with all rooms in the hotel if exist
        HotelSQL hotelSQL = loadHotelRooms.loadHotelRooms(hotelDatabasePath);
        SaveAllData saveAllData = new SaveAllData();
        Scanner scanner = new Scanner(System.in);

        if (hotelSQL.getSize() == 0) {
            AddRoom addRoom = new AddRoom();
            // Class for adding rooms
            hotelSQL = addRoom.addRoom(hotelDatabasePath, hotelSQL);
        } else {
            System.out.println("There are some rooms already been added.");
            System.out.print("If you wanna add more type 'add' otherwise 'type something': ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("add")) {
                AddRoom addRoom = new AddRoom();
                hotelSQL = addRoom.addRoom(hotelDatabasePath, hotelSQL);
            }
        }

        LoadUserDatabase loadUserDatabase = new LoadUserDatabase();
        // Loading the user database if exist
        UserSQL userSQL = loadUserDatabase.LoadUserDatabase(userDatabasePath);

        User user = null;
        // Until exit, we are checking for command "Login; Create or exit"
        while (true) {
            String command;
            System.out.print("Login or create account (login/create or exit): ");
            command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("exit")) {
                break;
            }

            if (command.equalsIgnoreCase("login")) {
                UserLoginValidation userLoginValidation = new UserLoginValidation();
                user = userLoginValidation.isSucceedLogin(userSQL);

                // Still working on that
                DisplayFeature displayFeature = new DisplayFeature();
                displayFeature.display(user, hotelSQL, userSQL);
                break;
            } else if (command.equalsIgnoreCase("create")) {
                CreateUserAccount create = new CreateUserAccount();
                user = create.CreateUserAccount(userSQL);
                break;
            } else {
                System.out.println("Wrong command!");
            }

        }

        // If there is no user logged in, we terminate the program
        if (user == null) {
            saveAllData.save(userDatabasePath, hotelDatabasePath, userSQL, hotelSQL);
            return;
        }

        System.out.println("Welcome to the hotel California.");
        // Until exit, we are checking for different command such ""view rooms; book a room; cansel booking or exit"
        while (true) {
            System.out.print("Choose between options (view rooms; book a room; cancel booking or exit): ");
            String choice = scanner.nextLine().trim();
            if (choice.equalsIgnoreCase("exit")) {
                break;
            }

            switch (choice.toLowerCase()) {
                case "view rooms" -> {
                    hotelSQL.viewRooms();
                }
                case "book a room" -> {
                    System.out.print("Choose a begin and end date(format: DD.MM.YYYY - DD.MM.YYYY)");
                    String datesStr = scanner.nextLine().trim();

                    BookingDateValidation bookingDateValidation = new BookingDateValidation();
                    if (!bookingDateValidation.isDateValid(datesStr)) {
                        continue;
                    }

                    // Check for all available room between the two dates
                    List<Room> rooms = hotelSQL.checkAllAvailableRooms(datesStr);
                    if (rooms == null) {
                        System.out.printf("There is no available rooms between those dates!%nCheck another day, thanks!");
                        saveAllData.save(userDatabasePath, hotelDatabasePath, userSQL, hotelSQL);
                        return;
                    }

                    String command;
                    int roomNumber = 0;
                    boolean isBack = false;
                    while (true) {
                        System.out.print("Select room number or type back: ");
                        command = scanner.nextLine().trim();
                        if (command.equalsIgnoreCase("back")) {
                            isBack = true;
                            break;
                        }

                        roomNumber = Integer.parseInt(command);

                        if (!hotelSQL.isRoomExist(roomNumber)) {
                            System.out.println("There is not such a room number!");
                            continue;
                        }

                        // If room is available we break and book the room
                        if (hotelSQL.isAvailableRoom(datesStr, roomNumber)){
                            break;
                        } else {
                            System.out.printf("The room number %d is already booked!%n", roomNumber);
                            System.out.println("All available rooms between the dates!");
                            // Print all available rooms, if not exit, there is no frey rooms
                            hotelSQL.printAllAvailableRooms(rooms);

                        }

                    }

                    if (isBack) {
                        continue;
                    }
                    
                    Booking booking = new Booking();
                    // Add to user booking history he is booked the room
                    booking.bookRoom(roomNumber, datesStr, hotelSQL, userSQL, user);

                }
                case "cancel booking" -> {

                    HotelBookedHistory hotelBookedHistory;
                    long reservationID = 0;
                    boolean isBack = false;
                    while (true) {
                        String command;
                        System.out.print("Enter reservation ID or back: ");
                        command = scanner.nextLine().trim();
                        if (command.equalsIgnoreCase("back")) {
                            isBack = true;
                            break;
                        }

                        reservationID = Long.parseLong(command);


                        hotelBookedHistory = hotelSQL.getReservationHistory(reservationID);
                        if (hotelBookedHistory == null) {
                            System.out.println("There is no such a reservation ID!");
                        } else {
                            if (hotelBookedHistory.getClientName().equalsIgnoreCase(user.getUserName())) {
                                break;
                            } else {
                                System.out.println("You dint booked that room!");
                            }
                        }

                    }

                    if (isBack) {
                        continue;
                    }
                    
                    CancelBooking canselBooking = new CancelBooking();
                    canselBooking.canselRoom(userSQL, hotelSQL, user.getUserName(), reservationID);
                }
                default -> {
                    System.out.println("Wrong command!");
                }

            }

        }

        saveAllData.save(userDatabasePath, hotelDatabasePath, userSQL, hotelSQL);
        scanner.close();
    }
}