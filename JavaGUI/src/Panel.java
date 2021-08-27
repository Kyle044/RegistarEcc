import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Panel extends JFrame {

	JLabel label = new JLabel();
	ImageIcon icon = new ImageIcon("thumb-up.png");
	JPanel redPanel = new JPanel();
	JPanel bluePanel = new JPanel();
	JPanel greenPanel = new JPanel();
	// you can only setbounds if the setlayout is null
	// borderlayout can set the label vertically and horizontally
	
	Panel(){
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(750,750);
		this.setVisible(true);
		this.setLayout(null);
		redPanel.setBackground(Color.red);
		redPanel.setBounds(0,0,250,250);
		redPanel.setLayout(new BorderLayout());
		this.add(redPanel);
		bluePanel.setBackground(Color.blue);
		bluePanel.setBounds(250,0,250,250);
		this.add(bluePanel);
		greenPanel.setBackground(Color.green);
		greenPanel.setBounds(0,250,500,250);
		greenPanel.setLayout(new BorderLayout());
		this.add(greenPanel);
		label.setText("Hi");
		label.setIcon(icon);
		label.setHorizontalAlignment(JLabel.RIGHT);
		greenPanel.add(label);
		
		
	}
}
