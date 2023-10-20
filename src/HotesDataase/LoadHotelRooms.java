package HotesDataase;

import SQLTable.HotelSQL;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadHotelRooms {

    public LoadHotelRooms() {

    }

    public HotelSQL loadHotelRooms(String inputPath) {
        HotelSQL hotelSQL = new HotelSQL();
        try (FileInputStream fis = new FileInputStream(inputPath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            boolean isNext = true;
            while (isNext) {
                try {
                    hotelSQL = (HotelSQL) ois.readObject();
                    return hotelSQL;
                } catch (EOFException | ClassNotFoundException e) {
//                    System.out.println("There is no more");
                    isNext = false;
                }

            }

        } catch (IOException ignored) {
        }

        return hotelSQL;
    }

}
