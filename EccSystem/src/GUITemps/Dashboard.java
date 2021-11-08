package GUITemps;

import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import DataTypes.Goals;
import DataTypes.User;
import GUI.MasterDash;
import Tools.ServerConnector;
import Tools.Warning;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dashboard extends JPanel {

	GridBagConstraints gbc = new GridBagConstraints();
	JPanel container = new JPanel();
	ImageIcon xIcon;
	ImageIcon sIcon;
	ImageIcon checkIcon;
	ImageIcon profilepic;
	MasterDash currentDash;
	User currentUser;
	JCheckBox checkBox;
	ArrayList goals;
	public JScrollPane scrollPane;
	ArrayList<Row> arrayRow = new ArrayList<Row>();

	public Dashboard(MasterDash _currentDash, User _currentUser) throws FontFormatException, IOException {
		this.setLayout(null);
		this.goals = ServerConnector.requestGoals();
//		this.goals = Goals.getGoals();
		// labels
		try {
			Label titlelbl = new Label("Title", "p");
			titlelbl.setLocation(172, 122);
			titlelbl.setSize(81, 45);
			Label userlbl = new Label("User", "p");
			userlbl.setLocation(281, 122);
			userlbl.setSize(58, 45);
			Label descriptionlbl = new Label("Description", "p");
			descriptionlbl.setLocation(685, 122);
			descriptionlbl.setSize(75, 45);
			Label timestamplbl = new Label("DateCreated", "p");
			timestamplbl.setLocation(395, 122);
			timestamplbl.setSize(75, 45);

			Label fileAttachmentlbl = new Label("File", "p");
			fileAttachmentlbl.setLocation(810, 122);
			fileAttachmentlbl.setSize(75, 45);

			Label updateslbl = new Label("Updates", "p");
			updateslbl.setLocation(568, 122);
			updateslbl.setSize(75, 45);
			Label statuslbl = new Label("Status", "p");
			statuslbl.setLocation(910, 122);
			statuslbl.setSize(75, 45);

			Label optionlbl = new Label("Options", "p");
			optionlbl.setLocation(1000, 122);
			optionlbl.setSize(75, 45);

			this.add(optionlbl);
			this.add(statuslbl);
			this.add(updateslbl);
			this.add(titlelbl);
			this.add(userlbl);
			this.add(descriptionlbl);
			this.add(timestamplbl);
			this.add(fileAttachmentlbl);
		} catch (FontFormatException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		// CheckBox
		xIcon = new ImageIcon(Row.class.getResource("/GUITemps/imgs/redx.png"));
		checkIcon = new ImageIcon(Row.class.getResource("/GUITemps/imgs/greencheck.png"));
		checkBox = new JCheckBox();
		checkBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {// checkbox has been selected

					arrayRow.forEach((r) -> {
						r.checkBox.setSelected(true);
					});

				} else {// checkbox has been deselected
					arrayRow.forEach((r) -> {
						r.checkBox.setSelected(false);
					});
				}
				;
			}
		});
		checkBox.setIcon(this.resize(xIcon));
		checkBox.setSelectedIcon(this.resize(checkIcon));
		checkBox.setLocation(91, 132);
		checkBox.setSize(24, 24);
		this.add(checkBox);

		// searchBox

		TxtBox search = new TxtBox("Search");
		search.setSize(200, 36);
		search.setLocation(91, 54);
		sIcon = new ImageIcon(Row.class.getResource("/GUITemps/imgs/searchIcon.png"));
//		search.setIcon(resize(sIcon));
		this.add(search);

		// Delete Btn

		Btn delbtn = new Btn("Delete");
		delbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isSuccess = false;

				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to delete the goal?", "Warning",
						dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
					// check the checked checkbox

					for (Row f : arrayRow) {
						if (f.checkBox.isSelected()) {
							System.out.println(f.goalName + " checkbox is checked");
							System.out.println("Goal to Delete : " + f.goalName);
							if (ServerConnector.deleteGoal(
									new Goals(f.uid, f.goalName, f.userName, f.Description, f.goalCreated, f.status))) {
								isSuccess = true;
							}
						}
					}
					if (isSuccess) {
						new Warning("Deleted Successfully.", "Success");
					} else {
						new Warning("Deleting Failed", "Failed");
					}

				}
				// will refresh the table
				try {
					goals = ServerConnector.requestGoals();
					scrollPane.setViewportView(getRow(goals));
					revalidate();
					repaint();
				} catch (FontFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		delbtn.setSize(91, 36);
		delbtn.setLocation(420, 53);
		this.add(delbtn);

		// Add Btn
		Btn addbtn = new Btn("Add");
		Dashboard das = this;
		addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddGoalGui dialog = new AddGoalGui(currentDash, true, _currentUser, das);
			}
		});
		addbtn.setSize(109, 36);
		addbtn.setLocation(301, 53);
		this.add(addbtn);

		// rows
		scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setBounds(44, 178, 1100, 317);
		this.add(scrollPane);
		if (goals != null) {
			scrollPane.setViewportView(getRow(goals));
		} else {
			JOptionPane.showMessageDialog(null, "Cant Connect to the server...", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	public JPanel getRow(ArrayList goals) throws FontFormatException, IOException {
//		container.setBackground(Color.black);
		container.setLayout(new GridBagLayout());

		// gbc default
		gbc.gridx = 1;

		// Define the y position of the component

		gbc.gridy = 1;

		// Number of columns the component takes up

		gbc.gridwidth = 1;

		// Number of rows the component takes up

		gbc.gridheight = 1;

		// Gives the layout manager a hint on how to adjust

		// component width (0 equals fixed)

		gbc.weightx = 50;

		// Gives the layout manager a hint on how to adjust

		// component height (0 equals fixed)

		gbc.weighty = 100;

		// Defines padding top, left, bottom, right

		gbc.insets = new Insets(5, 5, 5, 5);

		// Defines where to place components if they don't

		// fill the space: CENTER, NORTH, SOUTH, EAST, WEST

		// NORTHEAST, etc.

		gbc.anchor = GridBagConstraints.CENTER;

		// How should the component be stretched to fill the

		// space: NONE, HORIZONTAL, VERTICAL, BOTH

		gbc.fill = GridBagConstraints.HORIZONTAL;

		for (int i = 0; i < goals.size(); i++) {

			gbc.gridx = 0;
			gbc.gridy = i;
			gbc.gridwidth = 3;
			gbc.gridheight = 1;
			gbc.ipady = 1;
			gbc.insets = new Insets(3, 10, 10, 10);
			Row row = new Row((Goals) goals.get(i),currentDash) {
				public Dimension getPreferredSize() {
					return new Dimension(0, 60);
				}
			};

			arrayRow.add((Row) row);
			container.add(row, gbc);
		}

		return container;
	}

	public ImageIcon resize(ImageIcon icon) {
		Image image = icon.getImage(); // transform it
		Image newimg = image.getScaledInstance(18, 18, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		icon = new ImageIcon(newimg); // transform it back
		return icon;
	}
}
