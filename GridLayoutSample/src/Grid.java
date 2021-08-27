import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Grid extends JFrame implements ActionListener {

	JPanel panel1 = new JPanel();

	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JPanel panel5;

	Grid() {

		panel1.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel1.setLayout(new GridLayout(5, 3, 10, 10)); // column ,row
		panel2.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel2.setLayout(new GridLayout(5, 3, 10, 10));
		panel3.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel3.setLayout(new GridLayout(5, 3, 10, 10));
		panel4.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel4.setLayout(new GridLayout(5, 3, 10, 10));


		this.setSize(1000, 700);
		this.setLayout(new GridLayout(2, 2, 10, 10));
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);

		for (int i = 1; i <= 15; i++) {
			panel5 = new JPanel();
			panel5.setBackground(Color.PINK);
			panel1.add(panel5);
		}

		for (int i = 1; i <= 15; i++) {
			panel5 = new JPanel();
			panel5.setBackground(Color.YELLOW);
			panel2.add(panel5);
		}

		for (int i = 1; i <= 15; i++) {
			panel5 = new JPanel();
			panel5.setBackground(Color.DARK_GRAY);
			panel3.add(panel5);
		}
		for (int i = 1; i <= 15; i++) {
			panel5 = new JPanel();
			panel5.setBackground(Color.CYAN);
			panel4.add(panel5);
		}
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
