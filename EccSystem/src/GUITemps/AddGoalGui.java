package GUITemps;

import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JDialog;

import javax.swing.JPanel;

import GUI.MasterDash;
import Tools.FileHandler;
import Tools.InputSanitazion;
import Tools.ServerConnector;
import Tools.Warning;

import javax.swing.JScrollPane;

import DataTypes.Goals;
import DataTypes.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddGoalGui extends JDialog {
	ArrayList<File> selectedFile = new ArrayList<File>();
	AddGoalGui gui;

	AddGoalGui(MasterDash frame, boolean ist, User currentUser, Dashboard dash) {

		// this is a must too
		super(frame, ist);

		// you can edit this area ------

		getContentPane().setLayout(null);
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(10, 11, 462, 381);
		getContentPane().add(mainPanel);

		try {

			// labels
			Label titlelbl = new Label("Title", "p");
			titlelbl.setBounds(37, 11, 123, 47);
			Label descriptionlbl = new Label("Description", "p");
			descriptionlbl.setBounds(37, 69, 123, 47);
			mainPanel.setLayout(null);
			mainPanel.add(titlelbl);
			mainPanel.add(descriptionlbl);

			// text box
			TxtBox titleBox = new TxtBox("");
			titleBox.setSize(269, 47);
			titleBox.setLocation(170, 11);
			mainPanel.add(titleBox);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(170, 69, 269, 165);
			mainPanel.add(scrollPane);
			TxtArea descriptionBox = new TxtArea();
			scrollPane.setViewportView(descriptionBox);

			Label deadlinelbl = new Label("Deadline", "p");
			deadlinelbl.setBounds(37, 253, 123, 47);
			mainPanel.add(deadlinelbl);

			TxtBox deadlineBox = new TxtBox("MM/DD/YYYY");
			deadlineBox.setBounds(170, 253, 269, 47);
			mainPanel.add(deadlineBox);

			Btn submitBtn = new Btn("Submit");
			submitBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (InputSanitazion.checkIsEmptyOrNull(
							new String[] { titleBox.getText(), descriptionBox.getText(), deadlineBox.getText() })) {
					if(	InputSanitazion.validateDate(deadlineBox.getText())) {
						if (selectedFile.isEmpty()) {

							Goals goal = new Goals(titleBox.getText(), String.valueOf(currentUser.email),
									descriptionBox.getText(),deadlineBox.getText());

							if (ServerConnector.requestInsertGoal(goal)) {

								try {
									dash.scrollPane.setViewportView(dash.getRow(ServerConnector.requestGoals()));
									dash.revalidate();
									dash.repaint();
								} catch (FontFormatException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								new Warning("Succesfully Added.", "Success");

							} else {
								new Warning("Failed to add.", "Fail");
							}

						}

						else {

							Goals goal = new Goals(titleBox.getText(), String.valueOf(currentUser.email),
									descriptionBox.getText(),deadlineBox.getText());

							if (ServerConnector.requestInsertGoal(goal, selectedFile)) {

								try {
									dash.scrollPane.setViewportView(dash.getRow(ServerConnector.requestGoals()));
									dash.revalidate();
									dash.repaint();
								} catch (FontFormatException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								new Warning("Succesfully Added.", "Success");

							} else {
								new Warning("Failed to add.", "Fail");
							}

						}
					}
					else {
						new Warning("Invalid Date Format", "Invalid Format");
					}
						
					} else {
						new Warning("Please Fill Up All The Fields.", "Some Missing Fields");
					}
				}
			});
			submitBtn.setLocation(307, 311);
			submitBtn.setSize(132, 42);

			this.gui = this;
			Btn attachBtn = new Btn("Attachment");
			attachBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					Attachments attach = new Attachments(frame, true, gui);

				}
			});
			attachBtn.setLocation(164, 311);
			attachBtn.setSize(133, 42);
			mainPanel.add(submitBtn);
			mainPanel.add(attachBtn);

		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// this is the must
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setSize(498, 442);
		this.setLocationRelativeTo(frame);
		this.setVisible(true);
	}

	public ArrayList<File> getSelectedFile() {
		return selectedFile;
	}
}
