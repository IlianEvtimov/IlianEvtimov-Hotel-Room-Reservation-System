package HotesDataase;


import Rooms.Room;
import SQLTable.HotelSQL;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class AddRoom {

    public HotelSQL addRoom(String inputPath, HotelSQL hotelSQL) {
        try (FileOutputStream fos = new FileOutputStream(inputPath);
             ObjectOutputStream oss = new ObjectOutputStream(fos)) {
            String getValidInput;
            while (true) {
                getValidInput = isValidInput();
                if (getValidInput.equalsIgnoreCase("exit")) {
                    break;
                }

                String[] commands = getValidInput.split("\\s+");
                String roomType = commands[0];
                String amenities = commands[1];
                int maxOccupancy = Integer.parseInt(commands[2]);
                double price = Double.parseDouble(commands[3]);
                int roomNumber = hotelSQL.getSize() + 1;

                Room room = new Room(roomNumber, roomType, amenities, maxOccupancy, price, price / 10);
                hotelSQL.addRoom(room);

                System.out.printf("Room %s is added successfully!\n", roomType);

            }

            oss.writeObject(hotelSQL);
        } catch (IOException ignored) {
        }

        return hotelSQL;
    }

    private String isValidInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.printf("Enter type of room separated with space.%n" +
                    "Exam: 'Room type(Deluxe, Suite, Single, Double) " +
                    "Amenities maximumOccupancy pricePerNight'" +
                    "%nFor exits type: 'exit'.%n" +
                    "Enter your choose: ");

            String input = scanner.nextLine().trim();
            String[] commands = input.split("\\s+");
            if (input.isEmpty()) {
                System.out.println("Invalid input!");
            } else if (commands.length != 4 && commands.length != 1) {
                System.out.println("Invalid input!");
            } else if (commands.length == 1) {
                if (commands[0].equalsIgnoreCase("exit")) {
                    return input;
                } else {
                    System.out.println("Invalid input!");
                }
            } else {
                String roomType = commands[0];
                if (!roomType.equalsIgnoreCase("deluxe") &&
                        !roomType.equalsIgnoreCase("suite") &&
                        !roomType.equalsIgnoreCase("single") &&
                        !roomType.equalsIgnoreCase("double")) {
                    System.out.println("Invalid room type!");
                } else {
                    return input;
                }

            }

        }

    }

}
