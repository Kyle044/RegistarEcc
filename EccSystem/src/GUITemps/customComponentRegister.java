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
public class customComponentRegister extends JComponent {

	public Color step1_color = Color.white;
	public Color step2_color = Color.white;
	public Color step3_color = Color.white;

	public final int REC_WIDTH = 3;
	public final int REC_HEIGHT = 80;
	public final int CIR_WIDTH = 10;
	public final int CIR_HEIGHT = 15;

	public final int RECX = 100;
	public final int RECY = 100;

	public final int CIRX = RECX - 3;
	public final int CIRY = RECY - 13;

	public customComponentRegister() {
		setPreferredSize(new Dimension(100, 100));
	}

	public void paint(Graphics g) {

		Graphics2D g2D = (Graphics2D) g;
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Strings
		String step1_string = "Personal Information";
		String step2_string = "School Information";
		String step3_string = "Registration";

		Shape step1 = new Rectangle2D.Float(RECX, RECY, REC_WIDTH, REC_HEIGHT);
		g2D.setPaint(step1_color);
		g2D.draw(step1);
		g2D.fill(step1);

		Shape step1_circle = new Ellipse2D.Float(CIRX, CIRY, CIR_WIDTH, CIR_HEIGHT);
		g2D.setPaint(step1_color);
		g2D.draw(step1_circle);
		g2D.fill(step1_circle);
		g2D.setPaint(Color.white);
		g2D.setFont(Style.fontFile());
		g2D.drawString(step1_string, RECX + 30, RECY + 20);

		Shape step2 = new Rectangle2D.Float(RECX, RECY + 90, REC_WIDTH, REC_HEIGHT);
		g2D.setPaint(step2_color);
		g2D.draw(step2);
		g2D.fill(step2);

		Shape step2_circle = new Ellipse2D.Float(CIRX, CIRY + 90, CIR_WIDTH, CIR_HEIGHT);
		g2D.setPaint(step2_color);
		g2D.draw(step2_circle);
		g2D.fill(step2_circle);
		g2D.setPaint(Color.white);
		g2D.setFont(Style.fontFile());
		g2D.drawString(step2_string, RECX + 30, RECY + 20 + 90);

		Shape step3 = new Rectangle2D.Float(RECX, RECY + 180, REC_WIDTH, REC_HEIGHT);
		g2D.setPaint(step3_color);
		g2D.draw(step3);
		g2D.fill(step3);

		Shape step3_circle = new Ellipse2D.Float(CIRX, CIRY + 180, CIR_WIDTH, CIR_HEIGHT);
		g2D.setPaint(step3_color);
		g2D.draw(step3_circle);
		g2D.fill(step3_circle);
		g2D.setPaint(Color.white);
		g2D.setFont(Style.fontFile());
		g2D.drawString(step3_string, RECX + 30, RECY + 20 + 180);
	}

	public void step1Complete() {
		this.step1_color =Color.black;
	}

	public void step2Complete() {
		this.step2_color =Color.black;
	}

	public void step3Complete() {
		this.step3_color =Color.black;
	}
}
