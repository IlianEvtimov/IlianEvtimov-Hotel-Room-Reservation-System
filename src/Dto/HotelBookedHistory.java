package Dto;

import java.io.Serializable;

public class HotelBookedHistory implements Serializable {

    private final long bookingId;
    private final String clientName;
    private final int roomNumber;
    private final String roomType;
    private double pricePerNight;
    private double cancellationFee;
    private final String startAndEndDate;

    public HotelBookedHistory(long bookId, String clientName, int roomNumber, String roomType, double pricePerNight, double cancellationFee, String startAndEndDate) {
        this.bookingId = bookId;
        this.clientName = clientName;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.cancellationFee = cancellationFee;
        this.startAndEndDate = startAndEndDate;
    }

    public String getClientName() {
        return clientName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPriceForNight() {
        return pricePerNight;
    }

    public double getCancellationFee() {
        return cancellationFee;
    }

    public long getBookingId() {
        return bookingId;
    }

    public String getStartAndEndDate() {
        return startAndEndDate;
    }

    public void setPriceForNight(double priceForNight) {
        this.pricePerNight = priceForNight;
    }

    public void setCancellationFee(double cancellationFee) {
        this.cancellationFee = cancellationFee;
    }

    public long getBookId() {
        return bookingId;
    }

    @Override
    public String toString() {
        String cancellationFeeStr = String.format("%.2f", cancellationFee);
        return  "bookingId = " + bookingId +
                ", clientName =" + clientName  +
                ", roomNumber = " + roomNumber +
                ", roomType = " + roomType +
                ", pricePerNight = " + pricePerNight +
                ", cancellationFee = " + cancellationFeeStr +
                ", startAndEndDate = " + startAndEndDate;

    }
}
