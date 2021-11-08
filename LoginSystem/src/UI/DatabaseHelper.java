package UI;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class DatabaseHelper {

	Object[][] databaseInfo;
	
	Object[] columns = {
			"Name","Deadline","Description","Date_Published"
	};
	
	ResultSet rows;
	
	ResultSetMetaData metaData;
	DefaultTableModel dTableModel = new DefaultTableModel(databaseInfo,columns) {
		public Class getColumnClass(int column) {
			Class returnValue;
			if((column>=0)&&(column<getColumnCount())) {
				returnValue=getValueAt(0,column).getClass();
			}else {
				returnValue=Object.class;
			}
			return returnValue;
		}
	};
	
	
	
	public DefaultTableModel getData() {
		Connection conn = null;
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/EccDB","root","1234");
			Statement sqlState = conn.createStatement();
			String selectSched ="SELECT * FROM schedTBL";
			rows = sqlState.executeQuery(selectSched);
			Object[] tempRow;
			
			while(rows.next()) {
				tempRow = new Object[] {
						rows.getString(2),rows.getDate(3),rows.getString(4),rows.getDate(5)
				};
				dTableModel.addRow(tempRow);
			}
			
			return dTableModel;
			
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		catch(ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		
return dTableModel;
		
		
		
	}


	
	public boolean Authenticate(String username,String password) {
		Connection conn = null;
		boolean isUser = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/EccDB","root","1234");
			Statement sqlState = conn.createStatement();
			String selectUser = "SELECT * FROM userTBL where username = '"+username+"' and password='"+password+"'";
			rows = sqlState.executeQuery(selectUser);
			
			
			if(rows.next()) {
				isUser=true;
			}
			else {
				isUser=false;
			}
		
			
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		catch(ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		return isUser;
		
		
		
	}
	
	
	
	
	
}
