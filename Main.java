import java.sql.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://192.168.31.171/wpf","root","KyleKyleKyle");
			Statement sqlState = conn.createStatement();
			String selectStuff = "Select * from usertbl";
			ResultSet rows = sqlState.executeQuery(selectStuff);
			while(rows.next()) {
				System.out.println(rows.getString("position"));
				
			}
		}
		catch(SQLException ex) {
			System.out.println("SQLEXCEPTION : " + ex.getMessage());
			System.out.println("Vendor Error : " + ex.getErrorCode());
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
