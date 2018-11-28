package helpers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
	
}
