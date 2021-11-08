package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.mysql.cj.protocol.a.result.ByteArrayRow;

import DataTypes.Goals;
import DataTypes.User;
import GUITemps.Btn;
import GUITemps.Dashboard;
import GUITemps.Label;
import GUITemps.Options;
import GUITemps.Registar;
import GUITemps.Row;
import GUITemps.SearchBox;
import GUITemps.Settings;
import GUITemps.TxtBox;
import Tools.ServerConnector;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MasterDash extends JFrame {

	public User user;

	public Dashboard dash;
	public Registar reg;
	public JLabel imagelabel;
	public Label namelbl;

	public MasterDash(User _user) throws FontFormatException, IOException {
		this.user = _user;

		getContentPane().setLayout(null);

		// Layered Pane
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setSize(1140, 486);
		layeredPane.setLocation(0, 92);
		if (user.Authority.equals("admin")) {
			// dashboard for ADMIN
			dash = new Dashboard(this, _user);
			dash.setSize(1120, 465);
			dash.setLocation(10, 10);
			layeredPane.add(dash, Integer.valueOf(1));
		} else if (user.Authority.equals("client")) {
			reg = new Registar(this, _user);
			reg.setSize(1120, 465);
			reg.setLocation(20, 20);
			layeredPane.add(reg, Integer.valueOf(1));
		}

		// Settings
		Settings set = new Settings(user, this);
		set.setSize(1150, 465);
		set.setLocation(20, 10);
		layeredPane.add(set, Integer.valueOf(0));

		// adding layered pane

		getContentPane().add(layeredPane);

		// Picture and Name
		namelbl = new Label(user.firstname + " " + user.middlename + " " + user.lastname, "p");
		namelbl.setSize(287, 35);
		namelbl.setLocation(189, 43);
		BufferedImage buf;
		if (ServerConnector.requestProfile("profilePic", String.valueOf(user.uid)) == null) {
			buf = ImageIO.read(
					new File("C:\\Users\\KyleLaptop\\eclipse-workspace\\EccSystem\\src\\GUITemps\\imgs\\imgProf.png"));

		} else {
			buf = ServerConnector.requestProfile("profilePic", String.valueOf(user.uid));
		}

		imagelabel = new JLabel(resize(circlePic(buf), 80));
		imagelabel.setSize(118, 80);
		imagelabel.setLocation(75, 20);

		getContentPane().add(imagelabel);
		getContentPane().add(namelbl);
		// Navigation Btns

		Btn schedulebtn = new Btn("Schedule");
		schedulebtn.setLocation(930, 36);
		schedulebtn.setSize(101, 45);
		Btn profilebtn = new Btn("Settings");
		profilebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(user.Authority.equals("admin")) {
					layeredPane.setLayer(dash, Integer.valueOf(0));
				}
				else {
					layeredPane.setLayer(reg, Integer.valueOf(0));
				}
				layeredPane.setLayer(set, Integer.valueOf(1));
				revalidate();
				repaint();
			}
		});
		profilebtn.setLocation(1041, 36);
		profilebtn.setSize(109, 45);

		if(user.Authority.equals("admin")) {
			Btn proposalbtn = new Btn("Proposals");
			proposalbtn.setLocation(802, 36);
			proposalbtn.setSize(118, 45);
			getContentPane().add(proposalbtn);
		}
		if(user.Authority.equals("admin")) {
			Btn Dashbtn = new Btn("Dashboard");
			Dashbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if(user.Authority.equals("admin")) {
						layeredPane.setLayer(dash, Integer.valueOf(1));
					}
					else {
						layeredPane.setLayer(reg, Integer.valueOf(1));
					}
					layeredPane.setLayer(set, Integer.valueOf(0));
					revalidate();
					repaint();
				}
			});
			Dashbtn.setLocation(674, 36);
			Dashbtn.setSize(118, 45);

			getContentPane().add(Dashbtn);
		} else if(user.Authority.equals("client")) {
			
				Btn Dashbtn = new Btn("Dashboard");
				Dashbtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if(user.Authority.equals("admin")) {
							layeredPane.setLayer(dash, Integer.valueOf(1));
						}
						else {
							layeredPane.setLayer(reg, Integer.valueOf(1));
						}
						layeredPane.setLayer(set, Integer.valueOf(0));
						revalidate();
						repaint();
					}
				});
				Dashbtn.setLocation(800, 36);
				Dashbtn.setSize(118, 45);

				getContentPane().add(Dashbtn);
			
		}
		getContentPane().add(schedulebtn);
		getContentPane().add(profilebtn);
	

		this.setSize(1200, 700);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);

	}

	public void updateUser(String[] data) {
		this.user.lastname = data[0];
		this.user.firstname = data[1];
		this.user.middlename = data[2];
		this.user.schoolID = data[3];
		this.user.department = data[4];
		this.user.major = data[5];
		namelbl.setText(user.firstname + " " + user.middlename + " " + user.lastname);
		this.revalidate();
		this.repaint();
	}

	public void refreshImage(BufferedImage bef) {
		try {

			imagelabel.setIcon(resize(circlePic(bef), 80));
			this.revalidate();
			this.repaint();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ImageIcon circlePic(BufferedImage master) throws IOException {

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

}
