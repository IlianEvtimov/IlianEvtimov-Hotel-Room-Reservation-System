package UserOperations;

import Dto.HotelBookedHistory;
import Dto.User;
import Dto.UserBookedHistory;
import Rooms.Room;
import SQLTable.HotelSQL;
import SQLTable.UserSQL;

public class Booking {

    public void bookRoom(int roomNumber, String dates, HotelSQL hotelSQL, UserSQL userSQL, User user) {
        // We book the room if exist or is not already booked
        hotelSQL.bookTheRoom(roomNumber, dates);
        Room room = hotelSQL.getRoomByNumber(roomNumber);

        long bookingID = hotelSQL.getHotelBookingHistoriesCount() + 1;
        UserBookedHistory userBookedHistory = new UserBookedHistory(bookingID, room.getRoomNumber(), room.getType(), room.getAmenities(),
                room.getMaxOccupancy(), room.getPricePerNight(), 0, dates);

        HotelBookedHistory hotelBookedHistory = new HotelBookedHistory(bookingID, user.getUserName(), room.getRoomNumber(),
                room.getType(), room.getPricePerNight(), 0.00, dates);

        hotelSQL.addToBookedHistories(hotelBookedHistory);
        userSQL.addToBookingHistory(bookingID, user.getUserName(), userBookedHistory);

        System.out.println("You booked a room " + room.getRoomNumber() + " " + room.getType() + ". Your reservationId for canceling is " + bookingID + ".");

    }
}
