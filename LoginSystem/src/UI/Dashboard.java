package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import com.mysql.cj.conf.PropertyDefinitions.DatabaseTerm;

import javax.swing.JLabel;

public class Dashboard extends JFrame implements ActionListener {
	DatabaseHelper db = new DatabaseHelper();

	JLabel HelloLabel = new JLabel();
	JButton Home = new JButton();
	JButton Settings = new JButton();
	JButton Employee = new JButton();
	JPanel PicturePanel = new JPanel();
	JPanel panelRight = new JPanel();
	JPanel panelMiddle = new JPanel();
	JPanel panelLeft = new JPanel();
	JPanel panelHome = new JPanel();
	JPanel panelEmployee = new JPanel();
	JPanel panelSettings = new JPanel();
	JPanel panelTable = new JPanel();
	JTable schedTable;

	public Dashboard() {

		this.setTitle("ECC Dashboard");
		this.setSize(1000, 600);// sets the x-dimension, and y-dimension of this

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
		this.setResizable(false);// prevent the this to resize
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());

		// Button
		Home.setText("Home");
		Settings.setText("Settings");
		Employee.setText("Employee");
		Home.setPreferredSize(new Dimension(160, 50));
		Employee.setPreferredSize(new Dimension(160, 50));
		Settings.setPreferredSize(new Dimension(160, 50));
		// panel
		panelRight.setBackground(Color.red);
		panelMiddle.setBackground(Color.yellow);
		panelRight.setPreferredSize(new Dimension(150, 100));
		panelMiddle.setPreferredSize(new Dimension(100, 100));
		panelMiddle.setLayout(null);
		// Panel Left
		panelLeft.setPreferredSize(new Dimension(250, 100));
		// panelLeft.setBackground(Color.GRAY);

		panelLeft.setLayout(new GridBagLayout());
		panelLeft.setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 1;
		gridConstraints.gridwidth = 1;
		gridConstraints.gridheight = 1;
		gridConstraints.weightx = 50;
		gridConstraints.weighty = 100;
		gridConstraints.insets = new Insets(5, 5, 5, 5);
		gridConstraints.anchor = GridBagConstraints.CENTER;
		gridConstraints.fill = GridBagConstraints.BOTH;
		gridConstraints.gridy = 1;
		gridConstraints.gridheight = 3;
		panelLeft.add(PicturePanel, gridConstraints);
		gridConstraints.gridheight = 1;
		gridConstraints.gridy = 5;
		panelLeft.add(panelHome, gridConstraints);
		gridConstraints.gridy = 6;
		panelLeft.add(panelEmployee, gridConstraints);
		gridConstraints.gridy = 7;
		panelLeft.add(panelSettings, gridConstraints);
		// add the button to panels
		panelHome.setLayout(new GridBagLayout());
		// panelHome.setBackground(Color.GRAY);
		panelHome.add(Home);
		panelEmployee.setLayout(new GridBagLayout());
		// panelEmployee.setBackground(Color.GRAY);
		panelEmployee.add(Employee);
		panelSettings.add(Settings);
		panelSettings.setLayout(new GridBagLayout());
		// panelSettings.setBackground(Color.GRAY);

		// adding to panel middle
		panelMiddle.add(HelloLabel);
		HelloLabel.setBounds(50, 30, 200, 100);
		HelloLabel.setText("Hello Kyle!");
		HelloLabel.setFont(new Font("Serif", Font.PLAIN, 35)); // set Font Font family, Style,Size
		panelMiddle.add(panelTable);
		panelTable.setBackground(Color.PINK);
		panelTable.setBounds(40, 140, 500, 400);
		schedTable = new JTable(db.getData());
		schedTable.setRowHeight(schedTable.getRowHeight() + 10);
		schedTable.setFont(new Font("Serif", Font.PLAIN, 20));
		schedTable.setAutoCreateRowSorter(true);
		panelTable.add(schedTable);
		schedTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn col1 = schedTable.getColumnModel().getColumn(0);
		col1.setPreferredWidth(100);
		TableColumn col2 = schedTable.getColumnModel().getColumn(1);
		col2.setPreferredWidth(120);
		TableColumn col3 = schedTable.getColumnModel().getColumn(2);
		col3.setPreferredWidth(120);
		TableColumn col4 = schedTable.getColumnModel().getColumn(3);
		col4.setPreferredWidth(120);
		TableColumn tc = schedTable.getColumn("Name");
		CenterTableCellRenderer cr = new CenterTableCellRenderer();
		tc.setCellRenderer(cr);
		col2.setCellRenderer(cr);

		JScrollPane scrollPane = new JScrollPane(schedTable);
		panelTable.add(scrollPane, BorderLayout.CENTER);
		// adding panel
		this.add(panelLeft, BorderLayout.WEST);
		this.add(panelRight, BorderLayout.EAST);
		this.add(panelMiddle, BorderLayout.CENTER);

		this.setVisible(true);// make this visible

	}

	public class CenterTableCellRenderer extends DefaultTableCellRenderer {

		public CenterTableCellRenderer() {
			setHorizontalAlignment(JLabel.CENTER);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
