package GUITemps;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.border.Border;

import DataTypes.User;
import GUI.MasterDash;

public class Registar extends JPanel {
	
	
	MasterDash masterState;
	User userState;
	
	public Registar(MasterDash _master, User _user){
		
		this.masterState=_master;
		this.userState=_user;
		this.setBorder(new RoundedBorder(14,new Color(92,92,92)));
		
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
