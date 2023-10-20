package UserDatabase;

import SQLTable.UserSQL;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadUserDatabase {

    public LoadUserDatabase() {

    }

    public UserSQL LoadUserDatabase(String path) {
        UserSQL userSQL = new UserSQL();
        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            boolean isNext = true;
            while (isNext) {
                try {
                    userSQL = (UserSQL) ois.readObject();
                    return userSQL;
                } catch (EOFException | ClassNotFoundException e) {
                    isNext = false;
                }

            }

        } catch (IOException ignored) {
        }

        return userSQL;
    }

}

