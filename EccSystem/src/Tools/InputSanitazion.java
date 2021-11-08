package Tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.*;

public class InputSanitazion {
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	// Check Is Empty Or Null
	public static boolean checkIsEmptyOrNull(String[] input) {
		boolean isIt = false;
		boolean hasFalse = false;
		for (int i = 0; i < input.length; i++) {
			if (!input[i].isBlank()) {
				isIt = true;
			} else {
				hasFalse = true;
			}
		}

		if (hasFalse) {
			return false;
		} else {
			return true;
		}

	}

	// Encrypt
	public static String passEncrypt(String input) {
		char[] myChar = input.toCharArray();
		String EncryptedPass = "";
		for (char c : myChar) {
			c += 5;
			EncryptedPass += c;
		}
		return EncryptedPass;
	}

	// Decryption
	public static String passDecrypt(String input) {
		String DecryptPass = "";
		char[] myChars = input.toCharArray();
		for (char a : myChars) {
			a -= 5;
			DecryptPass += a;
		}
		return DecryptPass;
	}

	// Test Password Strength
	public static String PasswordStrength(String input) {
		boolean hasUppercase = false, atleastSix, hasLowercase = false, hasDigit = false;
		char[] password = input.toCharArray();
		String msg = "Input some";
		for (char c : password) {
			if (Character.isUpperCase(c)) {
				hasUppercase = true;
			}
			if (Character.isLowerCase(c)) {
				hasLowercase = true;
			}
			if (Character.isDigit(c)) {
				hasDigit = true;
			}
		}
		if (password.length > 5) {
			atleastSix = true;
		} else {
			atleastSix = false;
		}
		System.out.println();

		if (hasUppercase == false) {

			msg += " Uppercase Letter ";
		}
		if (hasLowercase == false) {

			msg += " Lowercase Letter ";
		}
		if (hasDigit == false) {

			msg += " Numeric value ";
		}
		if (atleastSix == false) {

			msg += " Six characters above ";
		}

		return msg;
	}

	public static boolean validateEmail(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

	public static String formatDate(String input) {
		String strDate = null;
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(input.substring(0, 10));
			SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
			strDate = formatter.format(date1);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strDate;
	}
	
	
	public static boolean validateDate(String date) {
		
		if (date.matches("\\d{2}/\\d{2}/\\d{4}")) {
		 return true;
		}
		else {
			return false;
		}
	}
}
