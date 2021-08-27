import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.ImageIcon;
public class GUI {
	int width;
	int height;
	
	GUI(int _w,int _h){
		
		this.width=_w;
		this.height=_h;
		
		// JFrame = GUI window to add components to
		JFrame frame = new JFrame(); //creates a frame
		frame.setTitle("Simple Frame");
		frame.setSize(this.width,this.height);//sets the x-dimension, and y-dimension of frame
		frame.setVisible(true);//make frame visible
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
		frame.setResizable(false);//prevent the frame to resize
		ImageIcon image = new ImageIcon("logolo.jpg"); //create an ImageIcon
		frame.setIconImage(image.getImage()); //change icon of frame 
		frame.getContentPane().setBackground(new Color(78,24,102));//change background of the frame
		
	}
	
	
	
}
