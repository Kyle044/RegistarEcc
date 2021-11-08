package GUITemps;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class Btn extends JButton {
	
	public Btn(String name) throws FontFormatException, IOException {
		//Hover Effect
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBorder(new LineBorder(Color.BLUE));
			
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setBorder(new RoundedBorder(20));
				
			}
		});
		File font_file = new File("C:\\Users\\KyleLaptop\\eclipse-workspace\\EccSystem\\fonts\\Poppins-Regular.ttf");
	    Font font = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(12f);
		
		this.setFont(font);
		this.setText(name);
		this.setPreferredSize(new Dimension(60, 60));
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.setBorder(new RoundedBorder(20));
		this.setBorderPainted(true);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
	}
	
	public Btn(String name,int size) throws FontFormatException, IOException {
		//Hover Effect
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBorder(new LineBorder(Color.BLUE));
			
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setBorder(new RoundedBorder(20));
				
			}
		});
		File font_file = new File("C:\\Users\\KyleLaptop\\eclipse-workspace\\EccSystem\\fonts\\Poppins-Regular.ttf");
	    Font font = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(9f);
		
		this.setFont(font);
		this.setText(name);
		this.setPreferredSize(new Dimension(60, 60));
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.setBorder(new RoundedBorder(20));
		this.setBorderPainted(true);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
	}
	
	private static class RoundedBorder implements Border {

	    private int radius;

	    RoundedBorder(int radius) {
	        this.radius = radius;
	    }


	    public Insets getBorderInsets(Component c) {
	        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
	    }


	    public boolean isBorderOpaque() {
	        return true;
	    }


	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
	    }
	}

}
