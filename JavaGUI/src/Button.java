import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
public class Button extends JFrame implements ActionListener {

	JButton button = new JButton();     
	ImageIcon icon = new ImageIcon("thumb-up.png");
	ImageIcon icon2 = new ImageIcon("dude.png");
	JLabel label = new JLabel();
	boolean toggle = false;
	Button(){
		this.setSize(750, 750);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		label.setIcon(icon2);
		label.setBounds(0,0,150,150);
		label.setVisible(this.toggle);
		button.setBounds(200,100,500,500);
		button.addActionListener(this);
		button.setText("im a button");
		button.setFocusable(false);
		button.setIcon(icon);
		button.setHorizontalTextPosition(JButton.CENTER);
		button.setVerticalTextPosition(JButton.BOTTOM);
		button.setFont(new Font("Comic Sans",Font.BOLD,25));
		button.setIconTextGap(-15);
		button.setForeground(Color.GREEN);
		button.setBackground(Color.LIGHT_GRAY);
		button.setBorder(BorderFactory.createEtchedBorder());
		this.add(label);
		this.add(button);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button) {
			System.out.println("Poo");
			label.setVisible(!this.toggle);
			this.toggle =!this.toggle;
		}
		
	}
	
}
