package GUITemps;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class TestAutoResizeImage extends JComponent {
	public BufferedImage img;

	public TestAutoResizeImage(File imgs) throws MalformedURLException, IOException {

		ImageIcon icon = circlePic(imgs);
		BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.createGraphics();
		// paint the Icon to the BufferedImage.
		icon.paintIcon(null, g, 0, 0);
		g.dispose();
		this.img = bi;

	}

	@Override
	public Dimension getPreferredSize() {
		// Using image size as preferred size
		return new Dimension(img.getWidth(), img.getHeight());
	}

	@Override
	public Dimension getMinimumSize() {
		// Using image size as minimum size
		return new Dimension(img.getWidth(), img.getHeight());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Compute scaling for both axis according to current component size
		// then use the lower value
		double scaleX = getWidth() * 1.0 / img.getWidth();
		double scaleY = getHeight() * 1.0 / img.getHeight();
		double scale = Math.min(scaleX, scaleY);

		// Center the image on the axis with greater scale
		double offsetX = (getWidth() - scale * img.getWidth()) / 2.0;
		double offsetY = (getHeight() - scale * img.getHeight()) / 2.0;

		Graphics2D g2 = (Graphics2D) g.create();

		// This gives a better quality in upscaling, but also slow down the repainting,
		// remove it if the resizing not responsive enough
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

		// We must consider the current possible transform before applying our scaling
		// Current transform
		AffineTransform at = g2.getTransform();
		// Translate to center
		at.concatenate(AffineTransform.getTranslateInstance(offsetX, offsetY));
		// Scale image
		at.concatenate(AffineTransform.getScaleInstance(scale, scale));

		g2.setTransform(at);
		// Paint the transformed image
		g2.drawImage(img, 0, 0, null);

		g2.dispose();
	}

	public void setNewImage(File newImg) {

		ImageIcon icon;
		try {
			icon = circlePic(newImg);
			BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics g = bi.createGraphics();
			// paint the Icon to the BufferedImage.
			icon.paintIcon(null, g, 0, 0);
			g.dispose();
			this.img = bi;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ImageIcon circlePic(File pic) throws IOException {
		BufferedImage master = ImageIO.read(pic);

		int diameter = Math.min(master.getWidth(), master.getHeight());
		BufferedImage mask = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = mask.createGraphics();
		applyQualityRenderingHints(g2d);
		g2d.fillOval(0, 0, diameter - 1, diameter - 1);
		g2d.dispose();

		BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
		g2d = masked.createGraphics();
		applyQualityRenderingHints(g2d);
		int x = (diameter - master.getWidth()) / 2;
		int y = (diameter - master.getHeight()) / 2;
		g2d.drawImage(master, x, y, null);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
		g2d.drawImage(mask, 0, 0, null);
		g2d.dispose();
		return new ImageIcon(masked);

	}

	public static void applyQualityRenderingHints(Graphics2D g2d) {

		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

	}

	public ImageIcon resize(ImageIcon icon, int num) {
		Image image = icon.getImage(); // transform it
		Image newimg = image.getScaledInstance(num, num, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		icon = new ImageIcon(newimg); // transform it back
		return icon;
	}

	public void bufferImageTocon(BufferedImage image) {

		ImageIcon icon = new ImageIcon(image);
	}
}
