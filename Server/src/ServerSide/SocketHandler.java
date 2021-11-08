package ServerSide;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.HashMap;

import javax.imageio.ImageIO;

import DataTypes.Files;
import DataTypes.Goals;
import DataTypes.User;
import Tools.FileHandler;
import Tools.FileTransferProcessor;
import Tools.RequestClass;

public class SocketHandler extends Thread {
	Socket sock;
	FileTransferProcessor ftp= null;
	public SocketHandler(Socket _c) {
		this.sock = _c;
	}

	public void run() {

		try {
			// request reader
			ObjectInputStream ois = new ObjectInputStream(this.sock.getInputStream());
			RequestClass req = (RequestClass) ois.readObject();

			if (req.request.contains("login")) {
				System.out.println("LOGIN REQUEST FROM SERVER");
				ObjectOutputStream os = new ObjectOutputStream(this.sock.getOutputStream());
				User loginUser;
				loginUser = (User) req.user;
				os.writeObject(loginUser.AuthenticateUser());
				os.flush();
				os.close();
			}

			else if (req.request.contains("register")) {

				System.out.println("REGISTER REQUEST FROM SERVERSSS");
				PrintWriter writer = new PrintWriter(sock.getOutputStream(), true);
				User user = req.user;
				if (user.registerUser()) {
					writer.write("true");
				} else {
					writer.write("false");
				}
				writer.close();
			}

			else if (req.request.contains("getgoals")) {
				System.out.println("GETGOALS REQUEST FROM SERVER");
				ObjectOutputStream os = new ObjectOutputStream(this.sock.getOutputStream());
				os.writeObject(Goals.getGoals());
				os.flush();
				os.close();

			}

			else if (req.request.contains("updateProf")) {

				PrintWriter writer = new PrintWriter(sock.getOutputStream());
				String[] data;
				User user = new User();
				data = req.data;
				int id = Integer.parseInt(data[6]);
				FileTransferProcessor ftp = new FileTransferProcessor(sock);
				if (req.hasFile) {
					if (user.update(data,
							"update usertbl set  lName = ?,fName = ? ,  mName = ?,  schoolID = ?,department = ?,major = ?  Where userID = ?  ")) {
						writer.println("true");
						writer.flush();

						// check muna kung meron ng file

						Files check = new Files(data[6], "profilePic");
						if (check.checkFileExists()) {
							System.out.println("Meron na syang dating picture");
							File localFile = new File(check.getPath());
							if (localFile.delete() && check.deleteFile()) {

								// nasa folder na yung file
								ftp.receiveFile(
										"D:\\filefolder\\" + data[0] + "profilePic" + this.getFileExtension(req.file));

								File fileDB = new File(
										"D:\\filefolder\\" + data[0] + "profilePic" + this.getFileExtension(req.file));

								Files filetoDG = new Files(fileDB, String.valueOf(id), "profilePic");

								if (filetoDG.insertFile()) {
									System.out.println("Successfull to Insert File to DB");
								} else {
									System.out.println("The File Data is not recorded on DB");
								}
							}

						} else {
							System.out.println("wala pa syang picture " + data[0]);

							// nasa folder na yung file
							ftp.receiveFile(
									"D:\\filefolder\\" + data[0] + "profilePic" + this.getFileExtension(req.file));

							File fileDB = new File(
									"D:\\filefolder\\" + data[0] + "profilePic" + this.getFileExtension(req.file));

							Files filetoDG = new Files(fileDB, String.valueOf(id), "profilePic");

							if (filetoDG.insertFile()) {
								System.out.println("Successfull to Insert File to DB");
							} else {
								System.out.println("The File Data is not recorded on DB");
							}

						}

					} else {
						writer.println("false");
						writer.flush();
						System.out.println("false sya");
					}
				} else {
					if (user.update(data,
							"update usertbl set  lName = ?,fName = ? ,  mName = ?,  schoolID = ?,department = ?,major = ?  Where userID = ?  ")) {
						writer.println("true");
						writer.flush();
					} else {
						writer.println("false");
						writer.flush();

					}
				}

				writer.close();

			} else if (req.request.contains("getProfileImage")) {

				OutputStream outputStream = sock.getOutputStream();
				if (User.getImage(req.refID, req.refName) == null) {
					outputStream.close();

				} else {
					Files picFile = User.getImage(req.refID, req.refName);
					File file = new File(picFile.path);
					BufferedImage screencapture = ImageIO.read(file);

					ImageIO.write(screencapture, getFileExtension(file).substring(1), sock.getOutputStream());

				}

			}
			else if (req.request.contains("insertgoal")) {
				Goals goal = req.goal;
				FileHandler fh = new FileHandler();
				PrintWriter writer = new PrintWriter(sock.getOutputStream());
				if(req.hasFile) {
					
					if(goal.InsertGoal()) {
							
						ftp = new FileTransferProcessor(sock);
						new File("D:\\goalDirectory\\"+req.goal.Username+"_"+req.goal.Title).mkdirs();
						ftp.receive("D:\\goalDirectory\\"+req.goal.Username+"_"+req.goal.Title);
							
						writer.write("true");
					}
					else {
						writer.write("false");
					}
					
				}
				else {
					if(goal.InsertGoal()) {

						writer.write("true");
					}
					else {
						writer.write("false");
					}
				}
				
				writer.close();
			} else if(req.request.contains("deletegoal")) {
				PrintWriter writer = new PrintWriter(sock.getOutputStream());
			
				
				File f = new File("D:\\goalDirectory//"+req.goal.Username+"_"+req.goal.Title);
				System.out.println(req.goal.Username+"_"+req.goal.Title);
				if(req.goal.delete()) {
					if(f.isDirectory()) { 
						  FileHandler fh = new FileHandler();
						  fh.deleteDirectory(f);
						
						}
					writer.write("true");
				}
				else {
					writer.write("false");
				}
				writer.close();
				
			}
			ois.close();
			this.sock.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public String removeCharsFromString(String word1, String word2) {
		StringBuilder sb = new StringBuilder(word1);

		// char[] word2characters= word2.toCharArray();
		HashMap<Character, Integer> table = new HashMap<Character, Integer>();

		for (int i = 0; i < word2.length(); i++) {
			table.put(word2.charAt(i), 1);

		}

		int p = 0;
		for (int i = 0; i < word1.length(); i++) {

			if (table.containsKey(word1.charAt(i))) {
				if (p == 0) {
					sb.deleteCharAt(i);
					// p++;
				} else {
					sb.deleteCharAt(i - p);
				}
				// System.out.println(sb);
				p++;
			}

		}

		return sb.toString();
	}

	private String getFileExtension(File file) {
		String name = file.getName();
		int lastIndexOf = name.lastIndexOf(".");
		if (lastIndexOf == -1) {
			return ""; // empty extension
		}
		return name.substring(lastIndexOf);
	}
}
