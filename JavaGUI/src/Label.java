import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
public class Label extends JFrame{
	
JLabel label = new JLabel(); //create a label
ImageIcon image = new ImageIcon("logolo.jpg"); //create an image
Border border = BorderFactory.createLineBorder(Color.green,3); //create a border

	Label(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		this.setVisible(true);
		label.setText("Bro do you even Code?");// set text of label
		label.setIcon(image); //add an image in an label
		label.setHorizontalTextPosition(JLabel.CENTER); //make the text centered inside the image
		label.setVerticalTextPosition(JLabel.TOP); //set text TOP,CENTER,BOTTOM of image
		label.setForeground(new Color(105, 49, 130)); //set Text Color
		label.setFont(new Font("MV Boli",Font.PLAIN,20)); //set Font Font family, Style,Size
		label.setIconTextGap(10); // set gap of text to image
		label.setBackground(Color.BLACK); //set background color
		label.setOpaque(true); //display background color
		label.setBorder(border); //set the border of the label
		label.setVerticalAlignment(JLabel.CENTER); //set vertical position of label within the label
		label.setHorizontalAlignment(JLabel.CENTER); // set horizontal alignment of label within the label
		//this.setLayout(null); //set the layout manager to null
		//label.setBounds(0,0,350,350); // x,y , height, width sets within the frame as well as dimensions
		this.add(label); //add the label to the frame
		this.pack(); // make sure to add all the components and then set the pack
	}
	
	
}
