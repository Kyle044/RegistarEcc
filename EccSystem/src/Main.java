import java.awt.FontFormatException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import DataTypes.Files;
import DataTypes.Goals;
import DataTypes.User;
import GUI.Login;
import GUI.MasterDash;
import Tools.FileTransferProcessor;
import Tools.InputSanitazion;


public class Main {

	public static void main(String[] args) throws FontFormatException, IOException {
//
	
//		Files f = new Files("12","profilePic");
//		System.out.println(f.refID);
//		System.out.println(f.refName);
//		if(f.checkFileExists()) {
//			System.out.println("it Exists");
//		}

		new Login();
		
//		new Profile();

//		Getting the goal	

//		Goals.getGoals().forEach((d)->{
//			
//			System.out.println(d.toString());
//		});

//		Goals goal = new Goals("Goal 2", "Otits", "3", "4", "5");
//		
//		if (goal.InsertGoal()) {
//			System.out.println("Success");
//		} else {
//			System.out.println("Failed");
//		}

	}
	


	

}
