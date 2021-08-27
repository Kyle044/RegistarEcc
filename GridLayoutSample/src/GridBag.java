import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GridBag extends JFrame implements ActionListener {

	JButton but1, but2, but3, but4, but5, but6,

			but7, but8, but9, but0, butPlus, butMinus,

			clearAll;

	JPanel thePanel;
	JTextField textResult;

	GridBag() {
		this.setTitle("ECC Dashboard");
		this.setSize(800, 600);// sets the x-dimension, and y-dimension of this
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
		this.setResizable(false);// prevent the this to resize
		this.setLocationRelativeTo(null);
		thePanel = new JPanel();
		thePanel.setLayout(new GridBagLayout());
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
		textResult = new JTextField("0", 20);
		Font font = new Font("Helvetica", Font.PLAIN, 18);
		but1 = new JButton("1");
		but2 = new JButton("2");
		but3 = new JButton("3");
		but4 = new JButton("4");
		but5 = new JButton("5");
		but6 = new JButton("6");
		but7 = new JButton("7");
		but8 = new JButton("8");
		but9 = new JButton("9");
		butPlus = new JButton("+");
		but0 = new JButton("0");
		butMinus = new JButton("-");
		clearAll = new JButton("C");
		thePanel.add(clearAll, gridConstraints);
		gridConstraints.gridwidth = 2;
		gridConstraints.gridx = 2;
		thePanel.add(textResult, gridConstraints);
		gridConstraints.gridwidth = 1;
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 2;
		thePanel.add(but1, gridConstraints);
		gridConstraints.gridx = 2;
		thePanel.add(but2, gridConstraints);
		gridConstraints.gridx = 3;
		thePanel.add(but3, gridConstraints);
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 3;
		thePanel.add(but4, gridConstraints);
		gridConstraints.gridx = 2;
		thePanel.add(but5, gridConstraints);
		gridConstraints.gridx = 3;
		thePanel.add(but6, gridConstraints);
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 4;
		thePanel.add(but7, gridConstraints);
		gridConstraints.gridx = 2;
		thePanel.add(but8, gridConstraints);
		gridConstraints.gridx = 3;
		thePanel.add(but9, gridConstraints);
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 5;
		thePanel.add(butPlus, gridConstraints);
		gridConstraints.gridx = 2;
		thePanel.add(but0, gridConstraints);
		gridConstraints.gridx = 3;
		thePanel.add(butMinus, gridConstraints);
		this.add(thePanel);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
