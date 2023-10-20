package Dto;

import java.io.Serializable;

public class UserBookedHistory implements Serializable {

    private long bookingId;
    private int roomNumber;
    private String type;
    private String amenities;
    private int maxOccupancy;
    private double pricePerNight;
    private double cancellationFee;
    private String startAndEndDate;

    public UserBookedHistory() {

    }

    public UserBookedHistory(long bookingId, int roomNumber, String type, String amenities, int maxOccupancy, double pricePerNight, double cancellationFee, String startAndEndDate) {
        this.bookingId = bookingId;
        this.roomNumber = roomNumber;
        this.type = type;
        this.amenities = amenities;
        this.maxOccupancy = maxOccupancy;
        this.pricePerNight = pricePerNight;
        this.cancellationFee = cancellationFee;
        this.startAndEndDate = startAndEndDate;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public double getCancellationFee() {
        return cancellationFee;
    }

    public void setCancellationFee(double cancellationFee) {
        this.cancellationFee = cancellationFee;
    }

    public long getBookingId() {
        return bookingId;
    }

    public String getStartAndEndDate() {
        return startAndEndDate;
    }
}
