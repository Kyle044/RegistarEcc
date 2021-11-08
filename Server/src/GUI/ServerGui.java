package GUI;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ServerSide.mServer;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;

public class ServerGui extends JFrame {

	public mServer server;
	public ServerGui guiState;
	public JTextArea textArea;
	public int port;
	public ServerGui() {
		this.guiState = this;

		getContentPane().setBackground(new Color(35, 39, 42));
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(10, 11, 274, 215);
		getContentPane().add(mainPanel);
		mainPanel.setLayout(null);

		textArea = new JTextArea();
		textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
		textArea.setBackground(new Color(246, 246, 246));
		textArea.setBounds(0, 0, 274, 215);
		mainPanel.add(textArea);
		JButton btnStart = new JButton("Start Server");
		// when start button was pressed
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("Server is about to Start...\n");
				server = new mServer(guiState);
				Thread serverThread = new Thread(server);
				btnStart.setEnabled(false);
				serverThread.start();
			

			}
		});
		btnStart.setBounds(150, 237, 134, 23);
		getContentPane().add(btnStart);

		JButton btnStop = new JButton("Stop Server");
		// when close button was pressed
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					server.serverSock.close();
					textArea.setText("Server was Closed \n");
					btnStart.setEnabled(true);
				} catch (IOException e1) {
					if (server.serverSock != null && !server.serverSock.isClosed()) {
						try {
							server.serverSock.close();
						} catch (IOException e11) {
							e11.printStackTrace(System.err);
						}
					}
					e1.printStackTrace();
				}
			}
		});
		btnStop.setBounds(20, 237, 120, 23);
		getContentPane().add(btnStop);
		getContentPane().setLayout(null);
		this.setSize(310, 310);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);

	}
}
