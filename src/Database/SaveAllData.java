package Database;

import HotesDataase.SaveHotelRooms;
import SQLTable.HotelSQL;
import SQLTable.UserSQL;
import UserDatabase.SaveUserDatabase;

public class SaveAllData {

    public SaveAllData() {

    }

    public void save(String userDatabasePath, String hotelDatabasePath,
                     UserSQL userSQL, HotelSQL hotelSQL){

        SaveUserDatabase saveUserDatabase = new SaveUserDatabase();
        if (saveUserDatabase.saveUserData(userDatabasePath, userSQL)) {
            System.out.println("User data is saved succeed!");
        } else {
            System.out.println("User data is not saved");
        }

        SaveHotelRooms saveHotelRooms = new SaveHotelRooms();
        if (saveHotelRooms.saveHotelData(hotelDatabasePath, hotelSQL)) {
            System.out.println("Hotel data is saved succeed!");
        } else {
            System.out.println("Hotel data is not saved");
        }

    }

}
