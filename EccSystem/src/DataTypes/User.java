package DataTypes;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Tools.*;
import com.mysql.cj.jdbc.DatabaseMetaData;

public class User implements Serializable {

	public int uid;
	public String email;
	public String password;
	public String department;
	public String firstname;
	public String lastname;
	public String middlename;
	public String schoolID;
	public String major;
	public String Authority;
	public boolean isLogged = false;

	public User(String _email, String _password, String _department, String _firstname, String _lastname,
			String _middlename, String _schoolID, String _major,String _Authority) {
		this.email = _email;
		this.password = _password;
		this.department = _department;
		this.firstname = _firstname;
		this.lastname = _lastname;
		this.middlename = _middlename;
		this.schoolID = _schoolID;
		this.major = _major;
		this.Authority=_Authority;
	}

	public User(String _email, String _password) {
		this.email = _email;
		this.password = _password;
	}

	public User() {

	}

	public User AuthenticateUser() {
		User user = new User();
		try {
			Class.forName(Database.FORNAME);
			Connection con = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
			// the query
			PreparedStatement stmt = con.prepareStatement("select*from usertbl where email = ? and password = ?");
			stmt.setString(1, this.email);
			stmt.setString(2, InputSanitazion.passEncrypt(this.password));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// id
				user.uid = rs.getInt(1);
				// email
				user.email = rs.getString(2);
				// department
				user.department = rs.getString(4);

				// fName
				user.firstname = rs.getString(5);

				// lName
				user.lastname = rs.getString(6);

				// mName
				user.middlename = rs.getString(7);

				// major
				user.major = rs.getString(8);

				// schoolID
				user.schoolID = rs.getString(9);
				//Autority
				user.Authority=rs.getString(10);

				// Log in
				user.isLogged = true;
			}
			// close connection
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		return user;
	}

	public boolean registerUser() {
		boolean isUser = false;
		if (!this.checkUserExists()) {
			try {
				// connect to database
				Class.forName(Database.FORNAME);
				Connection con = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
				// the query
				PreparedStatement stmt = con.prepareStatement(
						"insert into usertbl (`email`,`password`,`department`,`fName`,`lName`,`mName`,`major`,`schoolID`,Authority) values(?,?,?,?,?,?,?,?,?)");
				stmt.setString(1, this.email);// 1 specifies the first parameter in the query
				stmt.setString(2, InputSanitazion.passEncrypt(this.password));
				stmt.setString(3, this.department);
				stmt.setString(4, this.firstname);
				stmt.setString(5, this.lastname);
				stmt.setString(6, this.middlename);
				stmt.setString(7, this.major);
				stmt.setString(8, this.schoolID);
				stmt.setString(9, this.Authority);
				int i = stmt.executeUpdate();
				System.out.println(i + " records inserted");
				if (i == 1) {
					isUser = true;
				} else {
					isUser = false;
				}
				con.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			} catch (ClassNotFoundException ex) {
				System.out.println(ex.getMessage());
			}

		} else {
			System.out.println("There is an existing account with this email " + this.email);
		}
		return isUser;
	}

	public boolean checkUserExists() {
		boolean doesIt = false;
		String dbEmail = "onTop";
		try {
			Class.forName(Database.FORNAME);
			Connection con = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
			// the query
			PreparedStatement stmt = con.prepareStatement("select * from userTBL where email = ?");
			stmt.setString(1, this.email);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				dbEmail = rs.getString(2);
			}
			// close connection
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		if (dbEmail.equals("onTop")) {
			doesIt = false;
		} else {
			doesIt = true;
		}
		return doesIt;

	}

	public static User one(int id) {
		User user = new User();
		try {
			Class.forName(Database.FORNAME);
			Connection con = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
			// the query
			PreparedStatement stmt = con.prepareStatement("select*from usertbl where userID=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// id
				user.uid = rs.getInt(1);
				// email
				user.email = rs.getString(2);
				// department
				user.department = rs.getString(4);

				// fName
				user.firstname = rs.getString(5);

				// lName
				user.lastname = rs.getString(6);

				// mName
				user.middlename = rs.getString(7);

				// major
				user.major = rs.getString(8);

				// schoolID
				user.schoolID = rs.getString(9);
				// Authority
				
				user.Authority=rs.getString(10);
			}
			// close connection
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		return user;
	}

	public boolean delete() {
		boolean isDeleted = false;

		try {
			// connect to database
			Class.forName(Database.FORNAME);
			Connection con = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
			// the query
			PreparedStatement stmt = con.prepareStatement("delete from usertbl where userID = ?");
			stmt.setInt(1, this.uid);// 1 specifies the first parameter in the query

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

	public boolean update(String[] input, String query) {
		boolean updateSuccess = false;
		long id = 0;
		try {
			Class.forName(Database.FORNAME);
			Connection con = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
			// the query
			String generatedColumns[] = { "userID" };
			PreparedStatement stmt = con.prepareStatement(query, generatedColumns);
			for (int i = 0; i < input.length; i++) {
				stmt.setString(i + 1, input[i]);
			}
			int rs = stmt.executeUpdate();
			if (rs == 1) {
				updateSuccess = true;
			}

			// close connection
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		return updateSuccess;
	}

	public static Files getImage(String refID,String refname) {
		Files file=null;
		try {
			Class.forName(Database.FORNAME);
			Connection con = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);
			// the query
			PreparedStatement stmt = con.prepareStatement("select*from filetbl where refID = ? AND refName = ?");
			stmt.setString(1, refID);
			stmt.setString(2,refname );
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				file = new Files(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
			}
			// close connection
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}

		return file;
	}

	public String toString() {
		String strings = this.uid + " || " + this.firstname + " " + this.lastname + " " + this.middlename + " "
				+ this.email + " " + this.major + " " + this.department + " " + this.schoolID;
		return strings;
	}

}
