import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) {
		//Client
		
		
		
		try {
			Socket sock = new Socket("192.168.31.173", 3232);
			
			
			PrintWriter writer = new PrintWriter(sock.getOutputStream(),true);
			
			writer.println("login");
			writer.close();
			
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
