package GUI;

import GUITemps.*;
import Tools.InputSanitazion;
import Tools.ServerConnector;
import Tools.Style;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import DataTypes.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register extends JFrame {

	String fname;
	String lname;
	String mname;

	String schID;
	String dep;
	String maj;

	String email;
	String pass;
	String cpass;

	private int top = 2;
	private int middle = 1;
	private int bottom = 0;
	JLayeredPane leftPane;
	customComponentRegister bar;
	JPanel personalPanel;
	JPanel schoolPanel;
	JPanel registerPanel;

	Register() throws FontFormatException, IOException {

		// bar
		bar = new customComponentRegister();
		bar.setBounds(10, 22, 316, 440);

		// Main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(10, 11, 774, 449);
		getContentPane().add(mainPanel);
		mainPanel.setLayout(null);

		// Panels
		leftPane = new JLayeredPane();
		leftPane.setBounds(10, 11, 426, 427);
		mainPanel.add(leftPane);

		JPanel rightPanel = new JPanel();

		rightPanel.setBounds(446, -12, 364, 473);
		rightPanel.add(bar);
		rightPanel.setBackground(Style.red);
		rightPanel.setLayout(null);
		mainPanel.add(rightPanel);

		personalPanel = new JPanel();
		personalPanel.setSize(426, 427);
		personalPanel.setLocation(0, 0);

		schoolPanel = new JPanel();
		schoolPanel.setSize(426, 427);
		schoolPanel.setLocation(0, 0);

		registerPanel = new JPanel();
		registerPanel.setLocation(0, 0);
		registerPanel.setSize(426, 427);

		leftPane.add(registerPanel, Integer.valueOf(bottom));
		registerPanel.setLayout(null);
		leftPane.add(schoolPanel, Integer.valueOf(middle));
		leftPane.add(personalPanel, Integer.valueOf(top));
		personalPanel.setLayout(null);

		// Personal Panel Components

		// Labels
		Label fName = new Label("First Name", "p");
		fName.setBounds(91, 35, 219, 42);
		personalPanel.add(fName);
		Label lName = new Label("Last Name", "p");
		lName.setBounds(91, 142, 219, 42);
		personalPanel.add(lName);
		Label mName = new Label("Middle Name", "p");
		mName.setBounds(91, 248, 219, 42);
		personalPanel.add(mName);

		// Txt Box

		TxtBox fBox = new TxtBox("Input First Name");
		fBox.setBounds(91, 88, 219, 42);
		TxtBox lBox = new TxtBox("Input Last Name");
		lBox.setBounds(91, 194, 219, 42);
		TxtBox mBox = new TxtBox("Input Middle Name");
		mBox.setBounds(91, 301, 219, 42);
		personalPanel.add(fBox);
		personalPanel.add(lBox);
		personalPanel.add(mBox);
		// Button
		Btn next = new Btn("Next");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (fBox.getText().equals("Input First Name") || lBox.getText().equals("Input Last Name")
						|| mBox.getText().equals("Input Middle Name")) {
					System.out.println("Please Fill Up All The Fields");
				} else {
					if (InputSanitazion
							.checkIsEmptyOrNull(new String[] { fBox.getText(), lBox.getText(), mBox.getText() })) {
						fname = fBox.getText();
						lname = lBox.getText();
						mname = mBox.getText();
						nextPersonal();
					} else {

						System.out.println("Please Fill Up All The Fields");
					}
				}

			}
		});
		next.setBounds(210, 374, 147, 42);
		personalPanel.add(next);
		// Button
		Btn returns = new Btn("Return Log-in");
		returns.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					dispose();
					new Login();
				} catch (FontFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		returns.setBounds(50, 374, 147, 42);
		personalPanel.add(returns);
		schoolPanel.setLayout(null);

		// School Components

		// School ID #, Department , Major

		// Labels
		Label schoolLbl = new Label("School ID #", "p");
		schoolLbl.setBounds(91, 35, 219, 42);
		schoolPanel.add(schoolLbl);
		Label departmentLbl = new Label("Department", "p");
		departmentLbl.setBounds(91, 142, 219, 42);
		schoolPanel.add(departmentLbl);
		Label majorLbl = new Label("Major", "p");
		majorLbl.setBounds(91, 248, 219, 42);
		schoolPanel.add(majorLbl);

		// Txt Box

		TxtBox sBox = new TxtBox("Input School #");
		sBox.setBounds(91, 88, 219, 42);
		TxtBox dBox = new TxtBox("Input Department");
		dBox.setBounds(91, 194, 219, 42);
		TxtBox majorBox = new TxtBox("Input Major");
		majorBox.setBounds(91, 301, 219, 42);

		schoolPanel.add(sBox);
		schoolPanel.add(dBox);
		schoolPanel.add(majorBox);
		// Button
		Btn Schoolnext = new Btn("Next");
		Schoolnext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sBox.getText().equals("Input School #") || dBox.getText().equals("Input Department")
						|| majorBox.getText().equals("Input Major")) {
					System.out.println("Please Fill Up All The Fields");
				} else {

					if (InputSanitazion
							.checkIsEmptyOrNull(new String[] { sBox.getText(), dBox.getText(), majorBox.getText() })) {
						schID = sBox.getText();
						dep = dBox.getText();
						maj = majorBox.getText();
						nextSchool();
					} else {

						System.out.println("Please Fill Up All The Fields");
					}
				}

			}

		});
		Schoolnext.setBounds(210, 374, 147, 42);
		schoolPanel.add(Schoolnext);
		Btn Schoolback = new Btn("Back");
		Schoolback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				backSchool();

			}
		});
		Schoolback.setBounds(50, 374, 147, 42);
		schoolPanel.add(Schoolback);
		// Register Components
		// Labels
		Label emailLbl = new Label("Email", "p");
		emailLbl.setBounds(91, 35, 219, 42);
		registerPanel.add(emailLbl);
		Label passwordLbl = new Label("Password", "p");
		passwordLbl.setBounds(91, 142, 219, 42);
		registerPanel.add(passwordLbl);
		Label confirmLbl = new Label("Confirm Password", "p");
		confirmLbl.setBounds(91, 248, 219, 42);
		registerPanel.add(confirmLbl);

		// Txt Box

		TxtBox emailBox = new TxtBox("Input Email");
		emailBox.setBounds(91, 88, 219, 42);
		PasswordBox pBox = new PasswordBox("Input Password");
		pBox.setBounds(91, 194, 219, 42);
		PasswordBox pcBox = new PasswordBox("Input Confirm Password");
		pcBox.setBounds(91, 301, 219, 42);

		registerPanel.add(emailBox);
		registerPanel.add(pBox);
		registerPanel.add(pcBox);

		// Button
		Btn registerBack = new Btn("Back");
		registerBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				backRegister();

			}
		});
		registerBack.setBounds(50, 374, 147, 42);
		registerPanel.add(registerBack);

		// Button
		Btn registerSubmit = new Btn("Submit");
		registerSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (emailBox.getText().equals("Input Email") || new String(pBox.getPassword()).equals("Input Password")
						|| new String(pcBox.getPassword()).equals("Input Confirm Password")) {
					System.out.println("Please Fill Up All The Fields");
				} else if (!InputSanitazion.PasswordStrength(new String(pBox.getPassword())).equals("Input some")) {
					System.out.println(InputSanitazion.PasswordStrength(new String(pBox.getPassword())));
				} else if (!InputSanitazion.validateEmail(emailBox.getText())) {
					System.out.println("The Email Is Not Valid");

				} else {
					if (InputSanitazion.checkIsEmptyOrNull(new String[] { emailBox.getText(),
							new String(pBox.getPassword()), new String(pcBox.getPassword()) })) {
						email = emailBox.getText();
						pass = new String(pBox.getPassword());
						cpass = new String(pcBox.getPassword());
						try {
							submitRegister();
						} catch (FontFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						System.out.println("Please Fill Up All The Fields");
					}
				}

			}
		});
		registerSubmit.setBounds(210, 374, 147, 42);
		registerPanel.add(registerSubmit);
		// Email , Password , Confirm Password

		this.setSize(800, 500);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);

	}

	public void nextPersonal() {
		bar.step1_color = Color.black;
		leftPane.setLayer(personalPanel, bottom);
		leftPane.setLayer(schoolPanel, top);
		leftPane.setLayer(registerPanel, middle);
		this.revalidate();
		bar.repaint();
	}

	public void nextSchool() {

		bar.step2_color = Color.black;
		leftPane.setLayer(personalPanel, bottom);
		leftPane.setLayer(schoolPanel, middle);
		leftPane.setLayer(registerPanel, top);
		this.revalidate();
		bar.repaint();

	}

	public void backSchool() {

		bar.step1_color = Color.white;
		leftPane.setLayer(personalPanel, top);
		leftPane.setLayer(schoolPanel, middle);
		leftPane.setLayer(registerPanel, bottom);
		this.revalidate();
		bar.repaint();

	}

	public void backRegister() {

		bar.step2_color = Color.white;
		leftPane.setLayer(personalPanel, bottom);
		leftPane.setLayer(schoolPanel, top);
		leftPane.setLayer(registerPanel, middle);
		this.revalidate();
		bar.repaint();
	}

	public void submitRegister() throws FontFormatException, IOException {

		if (InputSanitazion
				.checkIsEmptyOrNull(new String[] { fname, lname, mname, schID, dep, maj, email, pass, cpass })) {

			if (cpass.equals(pass)) {
				User user = new User(email, pass, dep, fname, lname, mname, schID, maj,"client");
				if (ServerConnector.registerRequest(user)) {
					// go to dashboard
					bar.step3_color = Color.black;
					bar.repaint();
					JOptionPane.showMessageDialog(null,"Your Account was Registered Successfully!","Information",JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
					new Login();
				}

			} else {
				System.out.println("The password does not match");
				System.out.println(cpass);
				System.out.println(pass);
			}

		} else {
			System.out.println("Please fill up all the Fields");
		}

	}
}
