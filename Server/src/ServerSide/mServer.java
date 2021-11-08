package ServerSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.JFrame;

import DataTypes.Goals;
import DataTypes.User;
import GUI.ServerGui;

public class mServer implements Runnable {

	public boolean power;

	public ServerSocket serverSock;
	public InputStreamReader streamReader;
	public BufferedReader reader;
	public PrintWriter writer;
	public String request;
	public boolean isWaiting;
	public ServerGui guiState;

	ObjectOutputStream os;
	ObjectInputStream ois;

	public mServer(ServerGui _guiState) {
		this.guiState = _guiState;
	}

	@Override
	public void run() {

		try {
			serverSock = new ServerSocket(3232);
			Thread.sleep(3000);
			guiState.textArea.setText("Server Started... \n");
			System.out.println("Server Started");
			guiState.revalidate();
			guiState.repaint();
			while (true) {

				Socket sock = serverSock.accept();
				SocketHandler h = new SocketHandler(sock);
				h.start();

			}

		} catch (IOException e) {
			// TODO: handle exception
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



}
