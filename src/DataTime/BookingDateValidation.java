package DataTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookingDateValidation {


    public boolean isDateValid(String datesStr) {
        String[] splitDates = datesStr.split("\\s+-*\\s+");
        if (splitDates.length  < 2) {
            System.out.println("Enter two dates!");
            return false;
        }

        String startDateStr = splitDates[0];
        String endDateStr = splitDates[1];
        String dateFormat = "dd.MM.yyyy";
        DateTimeFormatter format = DateTimeFormatter.ofPattern(dateFormat);
        LocalDate today = LocalDate.now();

        if (!isDateValid(startDateStr, dateFormat)) {
            System.out.println("Start date is not valid.");
            return false;
        }

        if (!isDateValid(endDateStr, dateFormat)) {
            System.out.println("End date is not valid.");
            return false;
        }


        LocalDate dateStart = LocalDate.parse(startDateStr, format);
        LocalDate dateEnd = LocalDate.parse(endDateStr, format);

        if (today.isAfter(dateStart)) {
            System.out.println("Start date can't be smaller then today!");
            return false;
        }

        return !dateStart.isAfter(dateEnd);

    }

    public static boolean isDateValid(String dateStr, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false); // Disable lenient parsing

        try {
            sdf.parse(dateStr);
            return true; // Date is valid
        } catch (ParseException e) {
            return false; // Date is not valid
        }
    }

}
