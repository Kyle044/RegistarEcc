package UI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.util.Arrays;

public class Register extends JFrame implements ActionListener {
	JTextField username = new JTextField();
	JPasswordField password = new JPasswordField();
	JPasswordField confirm = new JPasswordField();
	JLabel title;
	JLabel labelUsername = new JLabel();
	JLabel labelPassword = new JLabel();
	JLabel labelConfirm = new JLabel();
	JPanel leftPanel = new JPanel();
	JPanel rightPanel = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JButton submit = new JButton();
	JButton login = new JButton();

	Register() {
		this.setSize(850, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(new Color(78, 24, 102));
		this.setLayout(new GridLayout(0, 2, 0, 0));
		this.setLocationRelativeTo(null);
//		this.setUndecorated(true);// curve
//		this.setShape(new RoundRectangle2D.Double(0, 0, 850, 600, 50, 50));// Curved
		// Button
		submit.setText("Submit");
		submit.setFocusable(false);
		submit.setBounds(100, 50, 110, 50);
		submit.addActionListener(this);
		login.setText("Log - in");
		login.setFocusable(false);
		login.setBounds(230, 50, 110, 50);
		login.addActionListener(this);
		// Label for Username
		labelUsername.setText("Username");
		labelUsername.setBounds(185, 60, 250, 40);

		// Label for Password
		labelPassword.setText("Password");
		labelPassword.setBounds(185, 60, 250, 40);

		// Label for Confirm Password
		labelConfirm.setText("Confirm Password");
		labelConfirm.setBounds(160, 60, 250, 40);
		// TITLE
		title = new JLabel();
		title.setText("ECC Schedule System");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("MV Boli", Font.PLAIN, 20));
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.CENTER);

		// uTextField
		username.setPreferredSize(new Dimension(250, 40));
		username.setHorizontalAlignment(JTextField.CENTER);
		username.setBounds(90, 100, 250, 40);

		// pTextField
		password.setPreferredSize(new Dimension(250, 40));
		password.setHorizontalAlignment(JTextField.CENTER);
		password.setBounds(90, 100, 250, 40);

		// cTextField
		confirm.setPreferredSize(new Dimension(250, 40));
		confirm.setHorizontalAlignment(JTextField.CENTER);
		confirm.setBounds(90, 100, 250, 40);

		// Left Panel
		leftPanel.setBackground(new Color(223, 75, 75));
		leftPanel.setLayout(new BorderLayout());

		// Right Panel
		rightPanel.setBackground(Color.gray);
		rightPanel.setLayout(new GridLayout(4, 0, 0, 0));
		// Adding Components to the right Panel
		rightPanel.add(panel1);
		rightPanel.add(panel2);
		rightPanel.add(panel4);
		rightPanel.add(panel3);

		// Adding Components to the left Panel
		leftPanel.add(title);

		// Adding Components to Panel 1
		panel1.add(labelUsername);
		panel1.add(username);
		panel1.setLayout(null);
		// Adding Components to Panel 2
		panel2.add(labelPassword);
		panel2.add(password);
		panel2.setLayout(null);
		// Adding Components to Panel 3
		panel3.add(submit);
		panel3.add(login);
		panel3.setLayout(null);
		// Adding Compnents to Panel 4
		panel4.add(labelConfirm);
		panel4.add(confirm);
		panel4.setLayout(null);
		// Adding Components Frame
		this.add(leftPanel);
		this.add(rightPanel);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == login) {
			this.dispose();
			Login logen = new Login();
		}
		if (e.getSource() == submit) {

			if (username.getText().isEmpty() && password.getPassword().length == 0
					&& confirm.getPassword().length == 0) {
				System.out.println("Please Fill up all the Fields");
			} else if (!Arrays.equals(password.getPassword(), confirm.getPassword())) {
				System.out.println("Password Does not Match");

			} else if (Arrays.equals(password.getPassword(), confirm.getPassword()) && username.getText().isEmpty()) {
				System.out.println("Please Fill up the Username");

			} else {
				System.out.println("Successfully Registered");
				System.out.println("Hi " + username.getText());

			}

		}

	}

}
