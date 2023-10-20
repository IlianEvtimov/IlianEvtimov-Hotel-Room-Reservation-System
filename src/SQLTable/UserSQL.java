package SQLTable;

import Dto.User;
import Dto.UserBookedHistory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserSQL implements Serializable {

    // private Map<Username, Map<password, role>>
    private Map<String, Map<String, String>> userAccounts;

    // private Map<Username, Map< bookingId, UserBookingHistory>>
    private Map<String, Map<Long, UserBookedHistory>> userBookedHistoryMap;


    public UserSQL() {
        this.userAccounts = new HashMap<>();
        this.userBookedHistoryMap = new HashMap<>();
    }

    public Map<String, Map<String, String>> getUserDatabase() {
        return userAccounts;
    }

    public boolean addUser(User user) {
        if (userAccounts.containsKey(user.getUserName())) {
            return false;
        }

        Map<String, String> userPassType = userAccounts.get(user.getUserName());
        if (userPassType == null) {
            userPassType = new HashMap<>();
        }
        userPassType.put(user.getPassword(), user.getRole());
        userAccounts.put(user.getUserName(), userPassType);
        return true;
    }

    public boolean isUserExist(String userName) {
        return userAccounts.containsKey(userName);
    }

    public boolean passwordValidation(User user) {
        Map<String, String> password = userAccounts.get(user.getUserName());
        return password.containsKey(user.getPassword());
    }

    public void addToBookingHistory(long bookingId, String username, UserBookedHistory userBookedHistory) {
        if (!userBookedHistoryMap.containsKey(username)) {
            userBookedHistoryMap.put(username, new HashMap<>());
        }

        Map<Long, UserBookedHistory> bookedHistoryMap = userBookedHistoryMap.get(username);
        bookedHistoryMap.put(bookingId, userBookedHistory);
        userBookedHistoryMap.put(username, bookedHistoryMap);
    }

    public Map<Long, UserBookedHistory> getUserBookedHistory(String username) {
        return userBookedHistoryMap.get(username);
    }

    public UserBookedHistory getByIdUserBookedHistory(Long bookingID, String userName) {
        Map<Long, UserBookedHistory> idBookHistory = userBookedHistoryMap.get(userName);
        return idBookHistory.get(bookingID);
    }

    public User getUserRoll(String userName, String password) {
        Map<String, String> roll = userAccounts.get(userName);
        return new User(userName, password, roll.get(password));
    }

    public UserBookedHistory checkIfUserBookedTheRoom(User user, int roomNumber) {

        Map<Long, UserBookedHistory> userHistoryMap = userBookedHistoryMap.get(user.getUserName());
        for (var userHistory: userHistoryMap.entrySet()) {
            if (userHistory.getValue().getRoomNumber() == roomNumber) {
                return userHistory.getValue();
            }
        }
        return null;
    }

    public void setCancelFee(UserBookedHistory userBookedHistory, String userName ) {
        Map<Long, UserBookedHistory> userHistoryMap = userBookedHistoryMap.get(userName);
        userHistoryMap.put(userBookedHistory.getBookingId(), userBookedHistory);
    }

}
