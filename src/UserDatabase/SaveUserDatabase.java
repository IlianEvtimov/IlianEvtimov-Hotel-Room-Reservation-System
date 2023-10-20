package UserDatabase;

import SQLTable.UserSQL;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveUserDatabase {
    public boolean saveUserData(String path, UserSQL userSQL) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oss = new ObjectOutputStream(fos)) {

            oss.writeObject(userSQL);
        } catch (IOException ignored) {
            return false;
        }

        return true;
    }
}
