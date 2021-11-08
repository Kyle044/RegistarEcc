import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static void main(String[] args) {
		//Server
		
		
		try {
			ServerSocket serverSock = new ServerSocket(4242);
			
			
			
			
			
			
			
			System.out.println("Server about to start.");
			
			while(true) {
				System.out.println("Server is active.");
				Socket sock = serverSock.accept();
				
				InputStreamReader is = new InputStreamReader(sock.getInputStream());
				
				BufferedReader reader = new BufferedReader(is);
				
				
				System.out.println(reader.readLine());
				reader.close();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		
		
	}
	
	
	
	
	
		


}
