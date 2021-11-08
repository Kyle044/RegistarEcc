package GUITemps;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;

import GUITemps.TxtBox.RoundedBorder;

public class TxtArea extends JTextArea {
	
	
	TxtArea(){
		File font_file = new File("C:\\Users\\KyleLaptop\\eclipse-workspace\\EccSystem\\fonts\\Poppins-Regular.ttf");
	    Font font;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(12f);
			this.setFont(font);
			this.setBorder(new RoundedBorder(10,Color.red));
			DefaultCaret caret = (DefaultCaret)this.getCaret();
			caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
			this.setLineWrap(true);
			this.setWrapStyleWord(true);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static class RoundedBorder implements Border {

	    private int radius;
	    private Color color;
	    RoundedBorder(int radius,Color color) {
	        this.radius = radius;
	        this.color= color;	
	    }


	    public Insets getBorderInsets(Component c) {
	        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
	    }


	    public boolean isBorderOpaque() {
	        return true;
	    }


	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	    	g.setColor(color);
	        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
	    }
	}
}
