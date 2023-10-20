package Rooms;

import java.io.Serializable;

public class Room implements Serializable {
    private int roomNumber;
    private String type;
    private String amenities;
    private int maxOccupancy;
    private double pricePerNight;
    private double cancellationFee;
    private int[][] statusDate;

    public Room(int roomNumber, String type, String amenities, int maxOccupancy, double pricePerNight, double cancellationFee) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.amenities = amenities;
        this.maxOccupancy = maxOccupancy;
        this.pricePerNight = pricePerNight;
        this.cancellationFee = cancellationFee;
        this.statusDate = new int[2][366];
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public String getAmenities() {
        return amenities;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public double getCancellationFee() {
        return cancellationFee;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public void setCancellationFee(double cancellationFee) {
        this.cancellationFee = cancellationFee;
    }

    public int[][] getStatusDate() {
        return this.statusDate;
    }

    public void setStatusDate(int[][] statusDate) {
        this.statusDate = statusDate;
    }

    @Override
    public String toString() {
        String cancellationFeeStr = String.format("%.2f", cancellationFee);
        return  "Room Number = " + roomNumber +
                ", type = " + type  +
                ", amenities = " + amenities +
                ", maxOccupancy = " + maxOccupancy +
                ", pricePerNight = " + pricePerNight +
                ", cancellationFee = " + cancellationFeeStr;
    }
}
