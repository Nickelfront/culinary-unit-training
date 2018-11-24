package application;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntityManager {

	public static boolean validatePhoneNumber(String phoneNumber) {
		String regex = "0[\\d]{9}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phoneNumber);
		
		return matcher.matches();
	}
	
	public static boolean validateClientAge(int age) {
		return age > 16;
	}
	
	public  static boolean validateEmail(String email) {
		String regex = "[\\w\\d\\-]+@[\\w\\d]+\\.[\\w]{2,}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		
		return matcher.matches();
	}
}
