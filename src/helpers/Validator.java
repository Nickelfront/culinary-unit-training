package helpers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.TextField;

public class Validator {

    public static boolean validatePhoneNumber(String phoneNumber) {
        String regex = "0[\\d]{9}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }

    public static boolean validateEmail(String email) {
        String regex = "[\\w\\d\\-]+@[\\w\\d]+\\.[\\w]{2,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static boolean validateAge(LocalDate birthDateInput) {
        Date birthDate = Date.from(Instant.from(birthDateInput.atStartOfDay(ZoneId.systemDefault())));

        return DateManager.getYearDifference(birthDate) > 16;
    }

    public static boolean validateDates(LocalDate startDate, LocalDate endDate) {
        return startDate.compareTo(endDate) < 1;
    }

    public static void setFieldInputAsInvalid(TextField textField) {
        textField.setStyle("-fx-border-color: #D50000");
    }

    public static boolean isValidFormat(String format, String value, Locale locale) {
        LocalDateTime ldt = null;
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format, locale);

        try {
            ldt = LocalDateTime.parse(value, fomatter);
            String result = ldt.format(fomatter);
            return result.equals(value);
        } catch (DateTimeParseException e) {
            try {
                LocalDate ld = LocalDate.parse(value, fomatter);
                String result = ld.format(fomatter);
                return result.equals(value);
            } catch (DateTimeParseException exp) {
                try {
                    LocalTime lt = LocalTime.parse(value, fomatter);
                    String result = lt.format(fomatter);
                    return result.equals(value);
                } catch (DateTimeParseException e2) {
                    // Debugging purposes
                    //e2.printStackTrace();
                }
            }
        }

        return false;
    }

}
