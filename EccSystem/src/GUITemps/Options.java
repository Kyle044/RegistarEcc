package GUITemps;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.*;

import javax.swing.JComponent;

import Tools.Style;

@SuppressWarnings("serial")
public class Options extends JComponent {

	public Color step1_color = Color.black;
	public Color step2_color = Color.black;
	public Color step3_color = Color.black;

	public final int REC_WIDTH = 3;
	public final int REC_HEIGHT = 80;
	public final int CIR_WIDTH = 4;
	public final int CIR_HEIGHT = 4;

	public final int RECX = 100;
	public final int RECY = 100;

	public final int CIRX = 0;
	public final int CIRY = 12;

	public Options() {
		setPreferredSize(new Dimension(100, 100));
	}

	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Shape step1_circle = new Ellipse2D.Float(CIRX, CIRY, CIR_WIDTH, CIR_HEIGHT);
		g2D.setPaint(step1_color);
		g2D.draw(step1_circle);
		g2D.fill(step1_circle);
		Shape step2_circle = new Ellipse2D.Float(CIRX, CIRY + 10, CIR_WIDTH, CIR_HEIGHT);
		g2D.setPaint(step2_color);
		g2D.draw(step2_circle);
		g2D.fill(step2_circle);
		Shape step3_circle = new Ellipse2D.Float(CIRX, CIRY + 20, CIR_WIDTH, CIR_HEIGHT);
		g2D.setPaint(step3_color);
		g2D.draw(step3_circle);
		g2D.fill(step3_circle);
	}

	public void hover() {
		step1_color = Color.red;
		step2_color = Color.red;
		step3_color = Color.red;
		repaint();
	}

	public void hoverout() {
		step1_color = Color.black;
		step2_color = Color.black;
		step3_color = Color.black;
		repaint();
	}

}
