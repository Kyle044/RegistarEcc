
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;



public class Frame extends JFrame implements ActionListener {
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	int unitSize = 5;
	int x = 0;
	int y = 0;
	Timer timer;
	char direction='R';
	JPanel panel=new JPanel(){

	// overwrite
	public void paintComponent(Graphics g){draw(g);

	}};

	Frame() {
		panel.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.addKeyListener(new MyKeyAdapterz());
		timer = new Timer(75, this);
		timer.start();
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);

	}

	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
//		for (int i = 0; i < SCREEN_WIDTH; i++) {
//			g2D.drawLine(i * unitSize, 0, i * unitSize, SCREEN_WIDTH);
//			g2D.drawLine(0, i * unitSize, SCREEN_WIDTH, i * unitSize);
//		}

		g2D.drawOval(x, y, unitSize, unitSize);
		g2D.fillOval(x, y, unitSize, unitSize);

	}

	public void move() {
		switch (direction) {
		case 'R': 
			x+=unitSize;
			break;
		case 'L':
			x-=unitSize;
			break;
		case 'D':
			y+=unitSize;
			break;
		case 'U':
			y-=unitSize;
			break;
			
			
			
		}
	}

	public void checkBorder() {
		if ((x > (SCREEN_WIDTH-unitSize)) ) {
			direction='L';
		}
		else if(x<0) {
			direction='R';
		}
		else 	if ((y > (SCREEN_HEIGHT-unitSize)) ) {
			direction='U';
		}
		else if(y<0) {
			direction='D';
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		move();
		checkBorder();
		System.out.println(x + " : " + y);
		repaint();
	}
	
	public class MyKeyAdapterz extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction!='R') {
					direction='L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction!='L') {
					direction='R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction!='D') {
					direction='U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction!='U') {
					direction='D';
					System.out.println("down");
				}
				break;
			}

		}
	}
}
