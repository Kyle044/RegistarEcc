package DataTypes;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Tools.Database;
import Tools.FileHandler;

public class Files implements Serializable {

	public int fileID;
	public String fileName;
	public String path;
	public String refID;
	public 	String refName;
	public String pathFromDB;
	public FileHandler fh = new FileHandler();

	public Files(File file, String _refID, String _refName) {
		this.fileName = file.getName();
		this.path = file.getAbsolutePath();
		this.refID = _refID;
		this.refName = _refName;
	}
	public Files( String _refID, String _refName) {
		this.refID = _refID;
		this.refName = _refName;
	}

	public Files(int id, File file, String _refID, String _refName) {
		this.fileID = id;
		this.fileName = file.getName();
		this.path = file.getAbsolutePath();
		this.refID = _refID;
		this.refName = _refName;
	}

	public Files(int id, String _fileName, String _path, String _refID, String _refName) {
		this.fileID = id;
		this.fileName = _fileName;
		this.path = _path;
		this.refID = _refID;
		this.refName = _refName;
	}

	Files() {

	}

	public boolean insertFile() {

		boolean isInserted = false;

		try {
			// connect to database
			Class.forName(Database.FORNAME);
			Connection con = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
			// the query
			PreparedStatement stmt = con
					.prepareStatement("insert into filetbl (`fileName`,`path`,`refID`,`refName`) values(?,?,?,?)");
			stmt.setString(1, this.fileName);// 1 specifies the first parameter in the query
			stmt.setString(2, this.path);
			stmt.setString(3, this.refID);
			stmt.setString(4, this.refName);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
			if (i == 1) {
				isInserted = true;
			} else {
				isInserted = false;
			}
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}

		return isInserted;

	}

	public boolean checkFileExists() {

		boolean isIn = false;
		try {
			// connect to database
			Class.forName(Database.FORNAME);
			Connection con = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
			// the query
			PreparedStatement stmt = con.prepareStatement("select * from filetbl WHERE refName=? AND refID=?");
			stmt.setString(1, this.refName);
			stmt.setString(2, this.refID);

			ResultSet i = stmt.executeQuery();

			if (i.next()) {
				isIn = true;
			} else {
				isIn = false;
			}
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		return isIn;

	}

	public String getPath() {

		try {
			// connect to database
			Class.forName(Database.FORNAME);
			Connection con = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
			// the query
			PreparedStatement stmt = con.prepareStatement("select * from filetbl WHERE refName=? AND refID=?");
			stmt.setString(1, this.refName);
			stmt.setString(2, this.refID);

			ResultSet i = stmt.executeQuery();

			while (i.next()) {
				this.pathFromDB = i.getString(3);
			}
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		return this.pathFromDB;

	}

	public boolean deleteFile() {
		boolean isDeleted = false;
		try {
			// connect to database
			Class.forName(Database.FORNAME);
			Connection con = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
			// the query
			PreparedStatement stmt = con.prepareStatement("Delete from fileTBL where refID=? AND refName=?");
			stmt.setString(1, this.refID);
			stmt.setString(2, this.refName);
			int i = stmt.executeUpdate();

			if (i > 0) {
				isDeleted = true;
			} else {
				isDeleted = false;
			}
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		return isDeleted;
	}

}
