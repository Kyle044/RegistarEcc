import javax.swing.JOptionPane;

public  class GUIClass {
	
	
	
	
	
	
	
	
	
	// method or function in a class
	public static void whatName() {
		
		//basic gui using swing
		String name = JOptionPane.showInputDialog("Enter Your Name");
		JOptionPane.showMessageDialog(null,"Your name is "+name);
	}
public  void Display(String text) {
		
		//display text
		
		JOptionPane.showMessageDialog(null,text);
	}

}
