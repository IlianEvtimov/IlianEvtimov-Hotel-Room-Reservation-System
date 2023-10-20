package Account;

import Dto.User;
import SQLTable.UserSQL;

import java.util.Scanner;

public class CreateUserAccount {


    public User CreateUserAccount(UserSQL userSQL) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String userName = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.println("Enter role (administration or user): ");
        String role = scanner.nextLine().trim();
        User user = new User(userName, password, role);

        if (userSQL.addUser(user)) {
            System.out.println("You created your account successfully!");
            return user;
        } else {
            System.out.println("Username is already exist!");
            return null;
        }

    }

}

