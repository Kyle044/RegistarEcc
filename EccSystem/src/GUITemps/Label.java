package GUITemps;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.*;

public class Label extends JLabel {

	public Label(String text, String type) throws FontFormatException, IOException {

		if (type == "p") {
			this.setText(text);
			File font_file = new File(
					"C:\\Users\\KyleLaptop\\eclipse-workspace\\EccSystem\\fonts\\Poppins-Regular.ttf");
			String fName = "../fonts/Poppins-Regular.ttf";
			Font font = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(12f);
			this.setFont(font);
		}else if(type=="bold") {
		
			this.setText(text);
			File font_file = new File(
					"C:\\Users\\KyleLaptop\\eclipse-workspace\\EccSystem\\fonts\\Poppins-Bold.ttf");
			String fName = "../fonts/Poppins-Regular.ttf";
			Font font = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(24f);
			this.setFont(font);
		}

	}
	
	public Label(String text, String type,float Size) throws FontFormatException, IOException {

		if (type == "p") {
			this.setText(text);
			File font_file = new File(
					"C:\\Users\\KyleLaptop\\eclipse-workspace\\EccSystem\\fonts\\Poppins-Regular.ttf");
			String fName = "../fonts/Poppins-Regular.ttf";
			Font font = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(Size);
			this.setFont(font);
		}else if(type=="bold") {
		
			this.setText(text);
			File font_file = new File(
					"C:\\Users\\KyleLaptop\\eclipse-workspace\\EccSystem\\fonts\\Poppins-Bold.ttf");
			String fName = "../fonts/Poppins-Regular.ttf";
			Font font = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(Size);
			this.setFont(font);
		}

	}
}
