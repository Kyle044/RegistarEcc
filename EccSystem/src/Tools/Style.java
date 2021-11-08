package Tools;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;

import javax.swing.JComponent;
public class Style {


public static Color red = new Color(242, 55, 43);
	















public static Font fontFile() {
	File font_file = new File(
			"C:\\Users\\KyleLaptop\\eclipse-workspace\\EccSystem\\fonts\\Poppins-Regular.ttf");
	Font font = new Font("Serif",Font.PLAIN,16);
 try {
	font = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(16f);
	
} catch (FontFormatException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
 return font;
}




	
}
