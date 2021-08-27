import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
public class textField extends JFrame implements ActionListener {

	JButton button;
	JTextField textfield;
	
	textField(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		
		button = new JButton("Submit");
		button.addActionListener(this);
		
		textfield = new JTextField();
		textfield.setPreferredSize(new Dimension(250,40));
		textfield.setFont(new Font("Consolas",Font.PLAIN,35));
		textfield.setForeground(new Color(0x00FF00));
		textfield.setBackground(Color.black);
		textfield.setCaretColor(Color.white);
		textfield.setText("username");
		
		
		this.add(button);
		this.add(textfield);
		this.pack();
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==button) {
			System.out.println("Welcome "+ textfield.getText());
			//button.setEnabled(false);
			//textField.setEditable(false);
		}
		
	}
}
