package HotesDataase;

import SQLTable.HotelSQL;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveHotelRooms {

    public boolean saveHotelData(String path, HotelSQL hotelSQL) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oss = new ObjectOutputStream(fos)) {

            oss.writeObject(hotelSQL);
        } catch (IOException ignored) {
            return false;
        }

        return true;
    }
}
