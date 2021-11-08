package Tools;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import DataTypes.Goals;
import DataTypes.User;

public class ServerConnector {
	public static RequestClass req = null;
	public static FileTransferProcessor ftp=null;
	public static User loginRequest(String email, String password) {
		User user = null;
		ObjectInputStream ois = null;
		ObjectOutputStream os = null;
		PrintWriter writer = null;

		try {
			Socket s = new Socket("192.168.31.172", 3232);
			writer = new PrintWriter(s.getOutputStream(), true);
			os = new ObjectOutputStream(s.getOutputStream());
			req = new RequestClass("login", new User(email, password));
			os.writeObject(req);
			ois = new ObjectInputStream(s.getInputStream());

			try {
				user = (User) ois.readObject();
				if (os != null) {
					os.close();
				}
				if (ois != null) {
					ois.close();
				}
				if (writer != null) {
					writer.close();
				}
				if (s != null) {
					s.close();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("cannot connect to server?");

		}

		return user;
	}

	public static boolean registerRequest(User user) {
		boolean isRegistered = false;

		ObjectOutputStream os = null;
		InputStreamReader sr = null;
		BufferedReader br = null;
		req = new RequestClass("register", user);

		try {
			Socket s = new Socket("192.168.31.172", 3232);

			OutputStream outputStream = s.getOutputStream();
			sr = new InputStreamReader(s.getInputStream());
			br = new BufferedReader(sr);
			os = new ObjectOutputStream(outputStream);
			os.writeObject(req);
			String res = br.readLine();
			if (res.contains("true")) {
				isRegistered = true;
			}
			br.close();
			os.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("cannot connect to server?");

		}

		return isRegistered;
	}

	public static ArrayList requestGoals() {

		ArrayList goals = null;
		ObjectOutputStream os = null;
		boolean isRegistered = false;
		ObjectInputStream ois = null;
		try {
			Socket s = new Socket("192.168.31.172", 3232);
			req = new RequestClass("getgoals");
			// The constructor of ObjectInputStream reads a stream header that is written by
			// the constructor of ObjectOutputStream. As that isn't happening in this code,
			// and presumably not at the peer either, it blocks forever.
			os = new ObjectOutputStream(s.getOutputStream());
			os.writeObject(req);
			ois = new ObjectInputStream(s.getInputStream());
			try {
				goals = (ArrayList) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			os.flush();
			os.close();
			ois.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("cannot connect to server?");

		}

		return goals;

	}

	public static boolean requestUpdateProfile(String[] data) {

		boolean isUpdated = false;

		try {
			Socket s = new Socket("192.168.31.172", 3232);

			req = new RequestClass("updateProf", data);
			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
			os.writeObject(req);
			InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
			BufferedReader reader = new BufferedReader(streamReader);
			String res = reader.readLine();
			if (res.equals("true")) {
				isUpdated = true;
			}
			reader.close();
			os.close();
			s.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("cannot connect to server?");

		}

		return isUpdated;

	}

	public static boolean requestUpdateProfileWFile(String[] data, File file) {

		boolean isUpdated = false;

		try {
			Socket s = new Socket("192.168.31.172", 3232);

			req = new RequestClass("updateProf", data, file);
			req.hasFile = true;
			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
			os.writeObject(req);
			InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
			BufferedReader reader = new BufferedReader(streamReader);
			String res = reader.readLine();
			if (res.equals("true")) {
				isUpdated = true;
			}
			FileTransferProcessor ftp = new FileTransferProcessor(s);
			ftp.sendFile(file);

			reader.close();
			os.close();
			s.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("cannot connect to server?");

		}

		return isUpdated;

	}

	public static BufferedImage requestProfile(String refname, String refID) {
		BufferedImage image = null;
		try {
			Socket s = new Socket("192.168.31.172", 3232);
			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
			req = new RequestClass("getProfileImage", refname, refID);

			os.writeObject(req);

			image = ImageIO.read(s.getInputStream());
			s.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("cannot connect to server?");

		}
		return image;

	}
	
	public static boolean requestInsertGoal(Goals goal) {
		boolean isIt=false;

		ArrayList goals = null;
		ObjectOutputStream os = null;
		try {
			Socket s = new Socket("192.168.31.172", 3232);
			req = new RequestClass("insertgoal",goal);
			// The constructor of ObjectInputStream reads a stream header that is written by
			// the constructor of ObjectOutputStream. As that isn't happening in this code,
			// and presumably not at the peer either, it blocks forever.
			
			os = new ObjectOutputStream(s.getOutputStream());
			os.writeObject(req);
			InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
			BufferedReader reader = new BufferedReader(streamReader);
			String res = reader.readLine();
			if(res.equals("true")) {
				isIt=true;
			}
			os.flush();
			os.close();
			

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("cannot connect to server?");

		}

		return isIt;
	}

	
	public static boolean requestInsertGoal(Goals goal,ArrayList<File> files) {
		boolean isIt=false;

		ArrayList goals = null;
		ObjectOutputStream os = null;
		ArrayList<FileData> filez = new ArrayList();
		try {
			Socket s = new Socket("192.168.31.172", 3232);
		
			req = new RequestClass("insertgoal",goal,files);
			// The constructor of ObjectInputStream reads a stream header that is written by
			// the constructor of ObjectOutputStream. As that isn't happening in this code,
			// and presumably not at the peer either, it blocks forever.
	
			os = new ObjectOutputStream(s.getOutputStream());
			os.writeObject(req);
			ftp = new FileTransferProcessor(s);
			ftp.send(files);
			
	
			
				
			
			InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
			BufferedReader reader = new BufferedReader(streamReader);
			String res = reader.readLine();
			if(res.equals("true")) {
				isIt=true;
			}
			
			os.flush();
			os.close();
			

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("cannot connect to server?");

		}

		return isIt;
	}
	
	public static boolean deleteGoal(Goals goal) {
		
		boolean isIt=false;
		
		
		try {
			Socket s = new Socket("192.168.31.172", 3232);
			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
			req = new RequestClass("deletegoal",goal);
			os.writeObject(req);
			InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
			BufferedReader reader = new BufferedReader(streamReader);
			String res = reader.readLine();
			if(res.equals("true")) {
				isIt=true;
			}
			reader.close();
			
			
			// The constructor of ObjectInputStream reads a stream header that is written by
			// the constructor of ObjectOutputStream. As that isn't happening in this code,
			// and presumably not at the peer either, it blocks forever.
	
			
			
			

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("cannot connect to server?");

		}
		
		
		
		
		return isIt;
		
		
	}
	
	private static String getFileExtension(File file) {
		String name = file.getName();
		int lastIndexOf = name.lastIndexOf(".");
		if (lastIndexOf == -1) {
			return ""; // empty extension
		}
		return name.substring(lastIndexOf);
	}
}
