package Features;

import Dto.HotelBookedHistory;
import Rooms.Room;
import SQLTable.HotelSQL;

import java.util.Map;
import java.util.Scanner;

public class AdministrationFeature {

    public void viewAllBooking(HotelSQL hotelSQL) {
        Map<Long, HotelBookedHistory> hotelHistory = hotelSQL.getHotelBookedHistories();
        if (isHistoryEmpty(hotelHistory)) {
            return;
        }

        for (var line: hotelHistory.entrySet()) {
            System.out.println(line.getValue());
        }
    }

    public void totalIncome(HotelSQL hotelSQL) {
        Map<Long, HotelBookedHistory> hotelHistory = hotelSQL.getHotelBookedHistories();
        if (isHistoryEmpty(hotelHistory)) {
            return;
        }

        double totalIncome = 0;
        for (var line: hotelHistory.entrySet()) {
            totalIncome += line.getValue().getPriceForNight();

        }

        System.out.printf("Total income is: %.2f%n", totalIncome);
    }

    public void removeRoom(int roomNumber, HotelSQL hotelSQL) {
        if (hotelSQL.removeRoom(roomNumber) == null) {
            System.out.println("There is no such a room!");
        } else {
            System.out.println("The room have been removed!");
        }

    }

    public void totalCancelFees(HotelSQL hotelSQL) {
        Map<Long, HotelBookedHistory> hotelHistory = hotelSQL.getHotelBookedHistories();
        if (isHistoryEmpty(hotelHistory)) {
            return;
        }

        double totalFCancelFee = 0;
        for (var line: hotelHistory.entrySet()) {
            totalFCancelFee += line.getValue().getPriceForNight();

        }

        System.out.printf("Total cancel fee is: %.2f%n", totalFCancelFee);
    }

    public boolean isHistoryEmpty(Map<Long, HotelBookedHistory> hotelHistory) {
        if (hotelHistory.isEmpty()) {
            System.out.println("There is no booking history!");
            return true;
        }

        return false;
    }

    public void modifyRoomByNumber(int roomNumber, HotelSQL hotelSQL) {

        if (!hotelSQL.isRoomExist(roomNumber)) {
            System.out.printf("Room number %d is not exist!%n" , roomNumber);
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new (room type; amenities; max occupancy and price per ninth separate by space: ");
        String input = scanner.nextLine().trim();
        String[] commands = input.split("\s+");
        String roomType = commands[0];
        String amenities = commands[1];
        int maxOccupancy = Integer.parseInt(commands[2]);
        double pricePerNight = Double.parseDouble(commands[3]);

        Room room = hotelSQL.getRoomByNumber(roomNumber);
        room.setType(roomType);
        room.setAmenities(amenities);
        room.setMaxOccupancy(maxOccupancy);
        room.setPricePerNight(pricePerNight);
        room.setCancellationFee(pricePerNight / 10);
        hotelSQL.updateRoom(roomNumber, room);

    }
}
