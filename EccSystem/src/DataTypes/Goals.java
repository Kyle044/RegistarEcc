package DataTypes;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Tools.Database;
import Tools.InputSanitazion;

public class Goals implements Serializable {

	public int goalID;
	public String Title;
	public String Username;
	public String UpdatesID;
	public String Description;
	public String FileID;
	public String timestamp;
	public String status;
	public String Deadline;

	public Goals(String _Title, String _Username, String _Description,String _Deadline) {

		this.Title = _Title;
		this.Username = _Username;

		this.Description = _Description;
		this.Deadline=_Deadline;
		this.status = "false";

	}

	public Goals(int _goalID, String _Title, String _Username, String _Description, String _time, String _status) {

		this.goalID = _goalID;
		this.Title = _Title;
		this.Username = _Username;

		this.Description = _Description;

		this.timestamp = _time;
		this.status = _status;

	}

	public static ArrayList getGoals() {
		ArrayList goals = new ArrayList();
		try {
			Class.forName(Database.FORNAME);
			Connection con = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
			// the query
			PreparedStatement stmt = con.prepareStatement("select*from goaltbl order by goalID DESC");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				goals.add(new Goals(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6)));
			}
			// close connection
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		return goals;
	}

	public boolean InsertGoal() {
		boolean isInserted = false;
		try {
			// connect to database
			Class.forName(Database.FORNAME);
			Connection con = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
			// the query
			PreparedStatement stmt = con.prepareStatement(
					"insert into goaltbl (`Title`,`Username`,`Description`,Deadline) values(?,?,?,?)");
			stmt.setString(1, this.Title);// 1 specifies the first parameter in the query
			stmt.setString(2, this.Username);
			
			stmt.setString(3, this.Description);
			stmt.setString(4, this.Deadline);
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

	public boolean delete() {
		boolean isDeleted = false;

		try {
			// connect to database
			Class.forName(Database.FORNAME);
			Connection con = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
			// the query
			PreparedStatement stmt = con.prepareStatement("delete from goaltbl where goalID = ?");
			stmt.setInt(1, this.goalID);// 1 specifies the first parameter in the query

			int i = stmt.executeUpdate();
			System.out.println(i + " records deleted");
			if (i == 1) {
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

	public String toString() {
		return this.goalID + " " + this.Title + " " + this.Username + " Status : " + this.status;
	}

}
