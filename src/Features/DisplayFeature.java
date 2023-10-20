package Features;

import Dto.User;
import SQLTable.HotelSQL;
import SQLTable.UserSQL;

import java.util.Scanner;

public class DisplayFeature {

    public DisplayFeature() {

    }

    public void display(User user, HotelSQL hotelSQL, UserSQL userSQL) {
        if (user == null) {
            return;
        }

        Scanner scanner = new Scanner(System.in);
        if (user.getRole().equalsIgnoreCase("administration")) {
            System.out.println("Feature administrator, still working!");
            String command;
            while (true) {
                System.out.print("Select from (view all booking; total income; total cancellation fees; remove room; modify room) or exit: ");
                command = scanner.nextLine().trim().toLowerCase();
                if (command.equals("exit")) {
                    break;
                }

                AdministrationFeature administrationFeature = new AdministrationFeature();

                switch (command) {
                    case "view all booking" -> administrationFeature.viewAllBooking(hotelSQL);
                    case "total income" -> administrationFeature.totalIncome(hotelSQL);
                    case "total cancellation fees" -> administrationFeature.totalCancelFees(hotelSQL);
                    case "remove room" -> {
                        System.out.print("Enter room number: ");
                        String roomNumber = scanner.nextLine().trim();
                        administrationFeature.removeRoom(Integer.parseInt(roomNumber), hotelSQL);
                    }
                    case "modify room" -> {
                        System.out.print("Enter the room number witch you wanna modify: ");
                        int roomNumber = Integer.parseInt(scanner.nextLine().trim());
                        administrationFeature.modifyRoomByNumber(roomNumber, hotelSQL);
                    }
                    default -> System.out.println("Wrong command!");
                }

            }
        }

    }

}
