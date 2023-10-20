package Account;

import Dto.User;
import SQLTable.UserSQL;

import java.util.Scanner;

public class UserLoginValidation {

    public User isSucceedLogin(UserSQL userSQL) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String userName = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        User user = new User(userName, password);
        boolean isPasswordValid = false;
        boolean isUsernameExist = userSQL.isUserExist(userName);
        if (isUsernameExist) {
            isPasswordValid = userSQL.passwordValidation(user);
            if (!isPasswordValid) {
                int tryCount = 0;
                while (tryCount < 3) {
                    System.out.println("Incorrect password!");
                    System.out.print("Enter your password again: ");
                    password = scanner.nextLine().trim();
                    user = new User(userName, password);
                    isPasswordValid = userSQL.passwordValidation(user);
                    tryCount++;
                    if (isPasswordValid) {
                        break;
                    }

                }

            }

        }

        if (isPasswordValid) {
            user = userSQL.getUserRoll(userName, password);
            System.out.printf("You logged successfully as %s!%n", user.getRole());
            return user;
        } else {
            System.out.println("Username not exist!");
            return null;
        }

    }

}
