package GUITemps;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.Border;

import DataTypes.Goals;
import GUI.MasterDash;
import Tools.InputSanitazion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Row extends JPanel {
	int uid;
	String goalName;
	String userName;
	String goalCreated;
	String Description;
	String status;;
	boolean options = false;
	ImageIcon xIcon;
	ImageIcon checkIcon;
	Options op;
	public JCheckBox checkBox;
	JPanel instacep;

	public Row(Goals goal,MasterDash _currentDash) throws FontFormatException, IOException {
		this.uid = goal.goalID;
		this.goalName = goal.Title;
		this.userName = goal.Username;
		this.goalCreated = goal.timestamp;
		this.Description = goal.Description;
		this.status = goal.status;
		// Labels
		Label Title = new Label(this.goalName, "p");
		Title.setBounds(120, 9, 76, 50);
		Label Username = new Label(this.userName, "p");
		Username.setBounds(230, 9, 76, 50);
		Label timestamp = new Label(InputSanitazion.formatDate(this.goalCreated), "p");
		timestamp.setBounds(320, 9, 114, 50);
		Label statuslbl = new Label(this.status == "true" ? "Finished" : "On Going", "p");
		statuslbl.setBounds(845, 6, 114, 50);
		this.add(statuslbl);
		this.add(Title);
		this.add(Username);
		this.add(timestamp);

		// Buttons

		Btn updateBtn = new Btn("Updates", (int) 6f);
	
		updateBtn.setLocation(495, 10);
		updateBtn.setSize(80, 40);
		Btn descriptionBtn = new Btn("Description", (int) 6f);
		descriptionBtn.setLocation(610, 10);
		descriptionBtn.setSize(93, 40);
		Btn fileBtn = new Btn("File", (int) 6f);
		fileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				new Attachments(_currentDash,goal);
				
				
			}
		});
		fileBtn.setLocation(730, 10);
		fileBtn.setSize(60, 40);
		this.add(updateBtn);
		this.add(descriptionBtn);
		this.add(fileBtn);

		// checkBox

		xIcon = new ImageIcon(Row.class.getResource("/GUITemps/imgs/redx.png"));
		checkIcon = new ImageIcon(Row.class.getResource("/GUITemps/imgs/greencheck.png"));
		checkBox = new JCheckBox();
		checkBox.setIcon(resize(xIcon));
		checkBox.setSelectedIcon(resize(checkIcon));
		checkBox.setLocation(32, 21);
		checkBox.setSize(24, 24);

		this.add(checkBox);
		// options
		op = new Options();
		op.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				try {

					OptionBtns btn = new OptionBtns();
					if (!options) {
						options = !options;
						btn.setSize(100, 100);
						btn.setLocation(970, -3);
						add(btn);
						revalidate();
						repaint();
						savePanel(btn);
			
			
					} else {
				
						remove(instacep);
						revalidate();
						repaint();
					
						options=!options;
				
					}

				} catch (FontFormatException e1) {

					e1.printStackTrace();
				} catch (IOException e1) {

					e1.printStackTrace();
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				op.hover();

			}

			@Override
			public void mouseExited(MouseEvent e) {
				op.hoverout();
			}
		});
		op.setLocation(960, 6);
		op.setSize(20, 45);
		this.add(op);

		// row

		setLayout(null);

		this.setBorder(new RoundedBorder(10, Color.black));
		this.setBackground(new Color(199, 190, 162));

	}

	public ImageIcon resize(ImageIcon icon) {
		Image image = icon.getImage(); // transform it
		Image newimg = image.getScaledInstance(18, 18, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		icon = new ImageIcon(newimg); // transform it back
		return icon;
	}

	public static class RoundedBorder implements Border {

		private int radius;
		private Color color;

		RoundedBorder(int radius, Color color) {
			this.radius = radius;
			this.color = color;
		}

		public Insets getBorderInsets(Component c) {
			return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
		}

		public boolean isBorderOpaque() {
			return true;
		}

		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			g.setColor(color);
			g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
		}
	}
	
	
	public void savePanel(JPanel p ) {
		this.instacep=p;
	}
	
	public JPanel getPanel (JPanel p) {
		
		return this.instacep;
	}
}
