package GUI;

import javax.swing.*;
import GUITemps.*;
import Tools.ServerConnector;
import DataTypes.User;
import java.awt.Color;
import java.awt.FontFormatException;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
	TxtBox email;
	PasswordBox password;

	public Login() throws FontFormatException, IOException {
		getContentPane().setLayout(null);
		// right Panel
		JPanel rightPanel = new JPanel();
		rightPanel.setBounds(449, 0, 335, 471);
		rightPanel.setBackground(new Color(242, 55, 43));
		getContentPane().add(rightPanel);

		// left Panel
		JPanel leftPanel = new JPanel();
		leftPanel.setBounds(10, 11, 429, 449);
		getContentPane().add(leftPanel);

		// Buttons
		Btn submit = new Btn("Log-in");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				;
				try {
					Login(email.getText(), new String(password.getPassword()));
				} catch (FontFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		submit.setBounds(100, 318, 244, 38);
		leftPanel.setLayout(null);
		Btn register = new Btn("Register");
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register(email.getText(), new String(password.getPassword()));
			}
		});
		register.setBounds(100, 367, 244, 38);
		leftPanel.add(register);
		leftPanel.add(submit);
		// Text Fields
		email = new TxtBox("Enter Email");
		email.setLocation(100, 92);
		email.setSize(244, 45);
		password = new PasswordBox("Enter Password");
		password.setLocation(100, 205);
		password.setSize(244, 45);
		leftPanel.add(email);
		leftPanel.add(password);
		// Label
		Label lblEmail = new Label("Email", "p");
		lblEmail.setLocation(100, 36);
		lblEmail.setSize(244, 45);
		Label lblPassword = new Label("Password", "p");
		lblPassword.setLocation(100, 161);
		lblPassword.setSize(244, 38);
		leftPanel.add(lblEmail);
		leftPanel.add(lblPassword);
		rightPanel.setLayout(null);
		Label lblTitle = new Label("Earist Schedule System", "bold");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 186, 315, 73);
		lblTitle.setForeground(Color.WHITE);

		rightPanel.add(lblTitle);
		this.setSize(800, 500);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	// Login Function
	public void Login(String email, String password) throws FontFormatException, IOException {

//		User loginUser = new User(email, password);
//		loginUser =	loginUser.AuthenticateUser();
//		if(loginUser.isLogged) {
//			this.dispose();
//			new MasterDash(loginUser);
//		}
//		else {
//			System.out.println("Invalid email or password");
//		}
		User user = ServerConnector.loginRequest(email, password);
		if (user != null) {
			if (user.isLogged) {
				this.dispose();
				new MasterDash(user);
			} else {
				JOptionPane.showMessageDialog(null, "Invalid email or password.", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			System.out.println("Server is not open or cannot connect to the server");
		}

	}

	// Register Function
	public void Register(String email, String password) {

		try {
			new Register();
		} catch (FontFormatException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		this.dispose();
	}

}
