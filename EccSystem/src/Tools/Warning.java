package Tools;

import javax.swing.JOptionPane;

public class Warning {

	
	
	public Warning(String message,String title){
		
		JOptionPane.showMessageDialog(null, message, title,
				JOptionPane.INFORMATION_MESSAGE);
		
	}
}
