import java.io.UnsupportedEncodingException;
import java.security.*;

public class Main {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		String myString = "Hello";
		
		char[] myChar = myString.toCharArray();
		for(char c : myChar) {
			c+=5;
			
			System.out.print(c);
			
		}
		System.out.println('\n');
		
		char[] myChars = myString.toCharArray();
		

		
		
		for(char a : myChars) {
			a-=5;
			System.out.print(a);
		}
		
	
	}

}
