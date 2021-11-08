package GUITemps;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DataTypes.User;
import GUI.MasterDash;
import Tools.FileHandler;
import Tools.InputSanitazion;
import Tools.ServerConnector;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Settings extends JPanel {
	FileHandler fh = new FileHandler();
	File imageChoosed = null;
	User user = new User();
	
	public Settings(User currentUser,MasterDash currentDash) throws IOException, FontFormatException {

		setLayout(null);
		// Layered Pane
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setSize(702, 443);
		layeredPane.setLocation(335, 11);

		// Profile Settings
		JPanel profPanel = new JPanel();
		profPanel.setLocation(10, 0);
		profPanel.setSize(660, 457);
		profPanel.setLayout(null);
		;

		// adding profile to layered pane
		layeredPane.add(profPanel, Integer.valueOf(1));

		// image
		JLabel imagelabel;
		BufferedImage buf = ImageIO.read(
				new File("C:\\Users\\KyleLaptop\\eclipse-workspace\\EccSystem\\src\\GUITemps\\imgs\\imgProf.png"));
		if (ServerConnector.requestProfile("profilePic", String.valueOf(currentUser.uid)) == null) {
			imagelabel = new JLabel(resize(circlePic(buf), 100));
			System.out.println("wala syang image sa db");
		} else {
			imagelabel = new JLabel(resize(circlePic(
					ServerConnector.requestProfile("profilePic", String.valueOf(currentUser.uid))), 100));
		}

		imagelabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imagelabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// getting the image file when profile pic is clicked
				File img = fh.getSingleFile();
				// validate if its null
				if (img != null) {
					// validate if its an image
					if (fh.imagesOnly(img)) {
						// its an image file
						// replacing the old pic to new pic
						try {
							BufferedImage buffimg = ImageIO.read(img);
							imagelabel.setIcon(resize(circlePic(buffimg), 90));
							currentDash.refreshImage(buffimg);
							imageChoosed = img;
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} else {
						// its not a image file
					}
				} else {
					// image is null
				}

			}
		});
		imagelabel.setBounds(506, 28, 120, 120);
		profPanel.add(imagelabel);

		// Label
		Label editlbl = new Label("Edit Profile", "bold", (float) 16.00000);
		editlbl.setBounds(36, 28, 134, 65);
		profPanel.add(editlbl);

		Label fNamelbl = new Label("First Name", "p", (float) 12.00000);
		fNamelbl.setBounds(180, 120, 134, 42);
		profPanel.add(fNamelbl);

		Label lNamelbl = new Label("Last Name", "p", (float) 12.00000);
		lNamelbl.setBounds(36, 120, 134, 42);
		profPanel.add(lNamelbl);

		Label mNamelbl = new Label("Middle Name", "p", (float) 12.00000);
		mNamelbl.setBounds(324, 120, 134, 42);
		profPanel.add(mNamelbl);

		Label schoolnumlbl = new Label("School #", "p", (float) 12.00000);
		schoolnumlbl.setBounds(36, 321, 134, 42);
		profPanel.add(schoolnumlbl);

		Label deplbl = new Label("Department", "p", (float) 12.00000);
		deplbl.setBounds(180, 222, 134, 42);
		profPanel.add(deplbl);

		Label majlbl = new Label("Major", "p", (float) 12.00000);
		majlbl.setBounds(36, 222, 134, 42);
		profPanel.add(majlbl);

		// Txt box

		TxtBox lNamebox = new TxtBox("Input Last Name");
		TxtBox fNamebox = new TxtBox("Input First Name");
		TxtBox mNamebox = new TxtBox("Input Middle Name");
		TxtBox schoolNumbox = new TxtBox("Input School #");
		TxtBox depbox = new TxtBox("Input Department");
		TxtBox majbox = new TxtBox("Input Major");
		lNamebox.setBounds(36, 169, 134, 42);
		fNamebox.setBounds(180, 169, 134, 42);
		mNamebox.setBounds(324, 169, 134, 42);
		schoolNumbox.setBounds(36, 374, 134, 42);
		depbox.setBounds(180, 270, 134, 42);
		majbox.setBounds(36, 270, 134, 42);

		profPanel.add(lNamebox);
		profPanel.add(fNamebox);
		profPanel.add(mNamebox);
		profPanel.add(schoolNumbox);
		profPanel.add(depbox);
		profPanel.add(majbox);

		// Btns

		Btn subBtn = new Btn("Submit");
		subBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("submit btn has been clicked");
				if (InputSanitazion.checkIsEmptyOrNull(new String[] { lNamebox.getText(), fNamebox.getText(),
						mNamebox.getText(), schoolNumbox.getText(), depbox.getText(), majbox.getText() })) {
					// its not empty
					// send to server
					if (imageChoosed == null) {
						System.out.println("wala pinili na image");
						if (ServerConnector.requestUpdateProfile(new String[] { lNamebox.getText(), fNamebox.getText(),
								mNamebox.getText(), schoolNumbox.getText(), depbox.getText(), majbox.getText(),
								String.valueOf(currentUser.uid) })) {
								currentDash.updateUser(new String[] { lNamebox.getText(), fNamebox.getText(),
								mNamebox.getText(), schoolNumbox.getText(), depbox.getText(), majbox.getText()});
							
							JOptionPane.showMessageDialog(null, "Updated Successfully", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Something Went Wrong in the Server", "Failed",
									JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						System.out.println("may pinili sya");
						if (ServerConnector.requestUpdateProfileWFile(new String[] { lNamebox.getText(), fNamebox.getText(),
								mNamebox.getText(), schoolNumbox.getText(), depbox.getText(), majbox.getText(),
								String.valueOf(currentUser.uid) }, imageChoosed)) {
							imageChoosed=null;
							currentDash.updateUser(new String[] { lNamebox.getText(), fNamebox.getText(),
									mNamebox.getText(), schoolNumbox.getText(), depbox.getText(), majbox.getText()});
							JOptionPane.showMessageDialog(null, "Updated Successfully", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Something Went Wrong in the Server", "Failed",
									JOptionPane.INFORMATION_MESSAGE);
						}
//			

					}
				} else {
					// its empty
					System.out.println("Its empty");

				}
			}
		});
		subBtn.setBounds(492, 373, 134, 42);
		profPanel.add(subBtn);

		// Account Settings
		JPanel accPanel = new JPanel();
		accPanel.setLocation(10, 0);
		accPanel.setSize(660, 457);
		accPanel.setLayout(null);
		;

		// adding profile to layered pane
		layeredPane.add(accPanel, Integer.valueOf(0));

		// Label
		Label heading = new Label("Edit Account", "bold", (float) 16.00000);
		heading.setBounds(36, 28, 134, 65);
		accPanel.add(heading);

		Label emaillbl = new Label("Email", "p", 12.0f);
		emaillbl.setBounds(232, 51, 134, 42);
		accPanel.add(emaillbl);

		Label passlbl = new Label("Password", "p", (float) 12.00000);
		passlbl.setBounds(232, 160, 134, 42);
		accPanel.add(passlbl);

		Label cpasslbl = new Label("Confirm Password", "p", (float) 12.00000);
		cpasslbl.setBounds(232, 266, 134, 42);
		accPanel.add(cpasslbl);

		// Txt box

		TxtBox emailbox = new TxtBox("Input Email");
		PasswordBox passbox = new PasswordBox("Input Password");
		PasswordBox cpassbox = new PasswordBox("Confirm Password");

		emailbox.setBounds(228, 107, 239, 42);
		passbox.setBounds(228, 213, 239, 42);
		cpassbox.setBounds(232, 319, 239, 42);

		accPanel.add(emailbox);
		accPanel.add(passbox);
		accPanel.add(cpassbox);

		// Btns

		Btn accsubBtn = new Btn("Submit");
		accsubBtn.setBounds(285, 382, 134, 42);
		accPanel.add(accsubBtn);

		// left JPanel
		JPanel navigationPanel = new JPanel();
		navigationPanel.setSize(197, 444);
		navigationPanel.setLocation(128, 11);
		navigationPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		Btn editBtn = new Btn("Edit Profile");
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				layeredPane.setLayer(profPanel, Integer.valueOf(1));
				layeredPane.setLayer(accPanel, Integer.valueOf(0));
				revalidate();
				repaint();

			}
		});
		Btn editAccBtn = new Btn("Edit Account");
		editAccBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.setLayer(profPanel, Integer.valueOf(0));
				layeredPane.setLayer(accPanel, Integer.valueOf(1));
				revalidate();
				repaint();
			}
		});
		navigationPanel.setLayout(new GridLayout(0, 1, 20, 20));
		Btn LogOutBtn = new Btn("Log-out");

		navigationPanel.add(editBtn);
		navigationPanel.add(editAccBtn);
		navigationPanel.add(LogOutBtn);

		this.add(navigationPanel);

		// Adding layered Pane
		this.add(layeredPane);
		// masterPanel setting
		this.setPreferredSize(new Dimension(1150, 465));

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
