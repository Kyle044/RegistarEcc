package GUITemps;

import java.awt.Color;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.*;

public class OptionBtns extends JPanel {

	String goalID;
	
	OptionBtns() throws FontFormatException, IOException{
		setLayout(null);
		setOpaque(false);
		Btn finish = new Btn("Finish",6);
		finish.setSize(74, 20);
		finish.setLocation(10, 35);
		Btn edit = new Btn("Edit",6);
		edit.setSize(74, 20);
		edit.setLocation(10, 11);
		this.add(edit);
		this.add(finish);
		
	}
	
	
}
