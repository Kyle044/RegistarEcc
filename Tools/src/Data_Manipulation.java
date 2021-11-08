import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.*;

public class Data_Manipulation {

	Data_Manipulation() {
		char[] dateInputed;
		int month;
		int day;
		int year;
		String[] nameOfDay = {"0","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
		Scanner scan = new Scanner(System.in);
		System.out.print("Input the Month Day and Year With the Format of [MM/DD/YYYY] : ");
		String input = scan.nextLine();
		String regex = "\\d{2}\\/\\d{2}\\/\\d{4}";

		if (regexChecker(input, regex)) {
			dateInputed = input.toCharArray();
			
			if(dateInputed[0] == '0') {
				month = Character.getNumericValue(dateInputed[1]);
			}
			else {
				String temp = Character.toString(dateInputed[0]) + Character.toString(dateInputed[1]);
				month = Integer.parseInt(temp);
			}
			if(dateInputed[3] == '0') {
				day = Character.getNumericValue(dateInputed[4]);
			
			}
			else {
				String temp = Character.toString(dateInputed[3]) + Character.toString(dateInputed[4]);
				day = Integer.parseInt(temp);
				
			}
			String tomp = Character.toString(dateInputed[6]) + Character.toString(dateInputed[7])+ Character.toString(dateInputed[8])+ Character.toString(dateInputed[9]);
			year = Integer.parseInt(tomp);
			System.out.println("The Day is " + nameOfDay[getDay(month,day,year)]);
			
			
		} else {
			System.out.println("Please Input The Correct Format MM/DD/YYYY");
		}
		
	}

	public boolean regexChecker(String toCheck, String theRegex) {
		Pattern checkRegex = Pattern.compile(theRegex);
		Matcher regexMatcher = checkRegex.matcher(toCheck);
		if (regexMatcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	public int getDay(int m, int d, int y) {
		// TODO getDay Practice #1
		Calendar rightNow = Calendar.getInstance();
		rightNow.set(y, m, d);
		rightNow.add(Calendar.MONTH, -1);
		rightNow.add(Calendar.DATE, -1);
		return rightNow.get(Calendar.DAY_OF_WEEK);

	}

	public void PasswordStrength(String input) {
		// TODO Test Password Strength
		boolean hasUppercase = false, atleastSix, hasLowercase = false, hasDigit = false;
		char[] password = input.toCharArray();

		for (char c : password) {
			if (Character.isUpperCase(c)) {
				hasUppercase = true;
			} else {
				hasUppercase = false;
			}
			if (Character.isLowerCase(c)) {
				hasLowercase = true;
			} else {
				hasLowercase = false;
			}
			if (Character.isDigit(c)) {
				hasDigit = true;
			} else {
				hasDigit = false;
			}
		}

		if (password.length > 5) {
			atleastSix = true;
		} else {
			atleastSix = false;
		}
		System.out.println();

		if (hasUppercase == false) {
			System.out.println("Input atleast one upper case letter in your password");
		}
		if (hasLowercase == false) {
			System.out.println("Please Inout atleast one Lower case letter in your password");
		}
		if (hasDigit == false) {
			System.out.println("Please Input atleast one numeric value in your password");
		}
		if (atleastSix == false) {
			System.out.println("Please input atleast six characters for a strong password");
		}
	}
}
