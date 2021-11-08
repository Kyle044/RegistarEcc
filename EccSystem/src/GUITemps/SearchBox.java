package GUITemps;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchBox extends JTextField {

	ImageIcon icon;

	public SearchBox(String placeholder) throws FontFormatException, IOException{
		 super();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(getText().equals(placeholder)) {
					setText("");
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(getText().equals(placeholder)||getText().equals("")) {
					setText(placeholder);
				}
			}
		});
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(getText().equals(placeholder)||getText().equals("")) {
					setText("");
				}
			}
		});
		
		File font_file = new File("C:\\Users\\KyleLaptop\\eclipse-workspace\\EccSystem\\fonts\\Poppins-Regular.ttf");
	    Font font = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(12f);
		this.setFont(font);
		this.setBorder(new RoundedBorder(10,Color.red));
		this.setText(placeholder);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		
	}
	public void paintComponent(Graphics g) {
		 super.paintComponent(g);
	    Graphics2D g2D = (Graphics2D)g;
	    
	    icon.paintIcon(this, g2D, 10, 10);

	}
	public void setIcon(ImageIcon newIcon){
	    this.icon = newIcon;
	    this.repaint();
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
	
	private class TextBubbleBorder extends AbstractBorder {

	    private Color color;
	    private int thickness = 2;
	    private int radii = 8;
	    private int pointerSize = 7;
	    private Insets insets = null;
	    private BasicStroke stroke = null;
	    private int strokePad;
	    private int pointerPad = 4;
	    RenderingHints hints;

	    TextBubbleBorder(
	        Color color) {
	            this(color, 4, 8, 7);
	    }

	    TextBubbleBorder(
	        Color color, int thickness, int radii, int pointerSize) {
	            this.thickness = thickness;
	            this.radii = radii;
	            this.pointerSize = pointerSize;
	        this.color = color;

	        stroke = new BasicStroke(thickness);
	        strokePad = thickness/2;

	        hints = new RenderingHints(
	            RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);

	        int pad = radii + strokePad;
	        int bottomPad = pad + pointerSize + strokePad;
	        insets = new Insets(pad,pad,bottomPad,pad);
	    }

	    @Override
	    public Insets getBorderInsets(Component c) {
	        return insets;
	    }

	    @Override
	    public Insets getBorderInsets(Component c, Insets insets) {
	        return getBorderInsets(c);
	    }

	    @Override
	    public void paintBorder(
	        Component c,
	        Graphics g,
	        int x, int y,
	        int width, int height) {

	        Graphics2D g2 = (Graphics2D)g;

	        int bottomLineY = height-thickness-pointerSize;

	        RoundRectangle2D.Double bubble = new RoundRectangle2D.Double(
	            0+strokePad,
	            0+strokePad,
	            width-thickness,
	            bottomLineY,
	            radii,
	            radii
	            );

	        Polygon pointer = new Polygon();

	        // left point
	        pointer.addPoint(
	            strokePad+radii+pointerPad,
	            bottomLineY);
	        // right point
	        pointer.addPoint(
	            strokePad+radii+pointerPad+pointerSize,
	            bottomLineY);
	        // bottom point
	        pointer.addPoint(
	            strokePad+radii+pointerPad+(pointerSize/2),
	            height-strokePad);

	        Area area = new Area(bubble);
	        area.add(new Area(pointer));

	        g2.setRenderingHints(hints);

	        Area spareSpace = new Area(new Rectangle(0,0,width,height));
	        spareSpace.subtract(area);
	        g2.setClip(spareSpace);
	        g2.clearRect(0,0,width,height);
	        g2.setClip(null);

	        g2.setColor(color);
	        g2.setStroke(stroke);
	        g2.draw(area);
	    }

	   
	}
}
