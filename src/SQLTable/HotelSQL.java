package SQLTable;

import Dto.HotelBookedHistory;
import Rooms.Room;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HotelSQL implements Serializable {

    // Map<roomNumber, Room>
    private Map<Integer, Room> hotels;
    // Map<bookingID, HotelBookedHistory>
    private Map<Long, HotelBookedHistory> hotelBookingHistories;

    public HotelSQL() {
        this.hotels = new HashMap<>();
        this.hotelBookingHistories = new HashMap<>();
    }

    public void addRoom(Room room) {
        if (!hotels.containsKey(room.getRoomNumber())) {
            hotels.put(room.getRoomNumber(), room);
        }
    }

    public int getSize() {
        return hotels.size();
    }

    public void viewRooms() {
        for (var entry : hotels.entrySet()) {
            System.out.println(entry.getValue());
        }

    }

    public void bookTheRoom(int roomNumber, String datesStr) {
        String[] splitDates = datesStr.split("\\s+-*\\s+");
        String startDateStr = splitDates[0];
        String endDateStr = splitDates[1];
        String dateFormat = "dd.MM.yyyy";

        DateTimeFormatter format = DateTimeFormatter.ofPattern(dateFormat);
        LocalDate dateStart = LocalDate.parse(startDateStr, format);
        LocalDate dateEnd = LocalDate.parse(endDateStr, format);

        LocalDate today = LocalDate.now();

        int year = dateStart.getYear() - today.getYear();
        int start = dateStart.getDayOfYear();
        int end = dateEnd.getDayOfYear() + 1;


        Room room = this.hotels.get(roomNumber);
        int[][] statusDate = room.getStatusDate();
        for (int j = start; j < end; j++) {
            statusDate[year][j] = 1;
        }

        room.setStatusDate(statusDate);
        this.hotels.put(roomNumber, room);
    }

    public Room getRoomByNumber(int roomNumber) {
        if (hotels.containsKey(roomNumber)) {
            Room room = hotels.get(roomNumber);
            return room;
        }

        return null;
    }

    public void cancelBooking(int roomNumber, String datesStr) {
        String[] splitDates = datesStr.split("\\s+-*\\s+");
        String startDateStr = splitDates[0];
        String endDateStr = splitDates[1];
        String dateFormat = "dd.MM.yyyy";

        DateTimeFormatter format = DateTimeFormatter.ofPattern(dateFormat);
        LocalDate dateStart = LocalDate.parse(startDateStr, format);
        LocalDate dateEnd = LocalDate.parse(endDateStr, format);

        LocalDate today = LocalDate.now();

        int year = dateStart.getYear() - today.getYear();
        int start = dateStart.getDayOfYear();
        int end = dateEnd.getDayOfYear() + 1;


        Room room = this.hotels.get(roomNumber);
        int[][] statusDate = room.getStatusDate();
        for (int j = start; j < end; j++) {
            statusDate[year][j] = 0;
        }

        room.setStatusDate(statusDate);
        this.hotels.put(roomNumber, room);

    }

    public boolean isRoomExist(int roomNumber) {
        return hotels.containsKey(roomNumber);
    }

    public void addToBookedHistories(HotelBookedHistory hotelBookedHistory) {
        long bookId = hotelBookedHistory.getBookId();
        this.hotelBookingHistories.put(bookId, hotelBookedHistory);
    }

    public int getHotelBookingHistoriesCount() {
        return hotelBookingHistories.size();
    }

    public List<Room> checkAllAvailableRooms(String datesStr) {
        String[] splitDates = datesStr.split("\\s+-*\\s+");
        String startDateStr = splitDates[0];
        String endDateStr = splitDates[1];
        String dateFormat = "dd.MM.yyyy";

        DateTimeFormatter format = DateTimeFormatter.ofPattern(dateFormat);
        LocalDate dateStart = LocalDate.parse(startDateStr, format);
        LocalDate dateEnd = LocalDate.parse(endDateStr, format);

        LocalDate today = LocalDate.now();

        int year = dateStart.getYear() - today.getYear();
        int start = dateStart.getDayOfYear();
        int end = dateEnd.getDayOfYear();
        int roomCount = hotels.size();
        int startRoomCount = 1;


        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < roomCount; i++) {

            boolean isAvailable = true;
            int[][] statusDate = hotels.get(startRoomCount).getStatusDate();

            for (int j = start; j < end; j++) {

                if (statusDate[year][j] == 1) {
                    isAvailable = false;
                    break;
                }
            }

            if (isAvailable) {
                rooms.add(hotels.get(startRoomCount));
            }
            startRoomCount++;

        }

        return rooms;
    }

    public boolean isAvailableRoom(String datesStr, int roomNumber) {
        String[] splitDates = datesStr.split("\\s+-*\\s+");
        String startDateStr = splitDates[0];
        String endDateStr = splitDates[1];
        String dateFormat = "dd.MM.yyyy";

        DateTimeFormatter format = DateTimeFormatter.ofPattern(dateFormat);
        LocalDate dateStart = LocalDate.parse(startDateStr, format);
        LocalDate dateEnd = LocalDate.parse(endDateStr, format);

        LocalDate today = LocalDate.now();

        int year = dateStart.getYear() - today.getYear();
        int start = dateStart.getDayOfYear();
        int end = dateEnd.getDayOfYear();

        int[][] statusDate = hotels.get(roomNumber).getStatusDate();

        for (int j = start; j < end; j++) {

            if (statusDate[year][j] == 1) {
                return false;
            }
        }

        return true;
    }

    public void printAllAvailableRooms(List<Room> rooms) {
        if (rooms == null) {
            System.out.println("There is no available rooms!");
            return;
        }
        for (var room : rooms) {
            System.out.println(room);
        }
    }

    public Map<Long, HotelBookedHistory> getHotelBookedHistories(){
        return hotelBookingHistories;
    }

    public HotelBookedHistory getByIdBookedHistory(long reservationID) {
        return hotelBookingHistories.get(reservationID);
    }

    public HotelBookedHistory getReservationHistory(Long reservationID) {
        return hotelBookingHistories.get(reservationID);
    }

    public Room removeRoom(int roomNumber) {
        return hotels.remove(roomNumber);
    }

    public void updateRoom(int roomNumber, Room room) {
        hotels.put(roomNumber, room);
    }
}
