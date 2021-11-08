import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Iterator;

public class Myframe extends JFrame {

	Myframe(){
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new FlowLayout());
		
		
		for (int i = 0; i < 5; i++) {
			String str = String.valueOf(i);
			JButton btn = new JButton(str);
			
			myPanel.add(btn);
		}
		
		
		
		this.add(myPanel, BorderLayout.CENTER);
	
		this.setVisible(true);
	}
	
	
	
	
}
