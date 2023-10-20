package UserOperations;

import Dto.HotelBookedHistory;
import Dto.UserBookedHistory;
import SQLTable.HotelSQL;
import SQLTable.UserSQL;

public class CancelBooking {

    public void canselRoom(UserSQL userSQL, HotelSQL hotelSQL, String userName, long bookingId) {

        UserBookedHistory userBookedHistory = userSQL.getByIdUserBookedHistory(bookingId, userName);
        int roomNumber = userBookedHistory.getRoomNumber();
        String dates = userBookedHistory.getStartAndEndDate();
        hotelSQL.cancelBooking(roomNumber, dates);
        double setCanselFee = hotelSQL.getRoomByNumber(roomNumber).getCancellationFee();
        userBookedHistory.setCancellationFee(setCanselFee);
        userSQL.setCancelFee(userBookedHistory, userName);
        HotelBookedHistory hotelBookedHistory = hotelSQL.getByIdBookedHistory(bookingId);
        hotelBookedHistory.setCancellationFee(setCanselFee);
        hotelSQL.addToBookedHistories(hotelBookedHistory);
        System.out.printf("You successfully canceled the room number %s with id %d!%n", roomNumber, bookingId);
    }

}
