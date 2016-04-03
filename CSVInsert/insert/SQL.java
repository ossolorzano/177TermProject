package insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQL {
	private static Connection con = null;
	
	public void initSQLConnection(){
		if(con==null){
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con = DriverManager.getConnection("jdbc:mysql://athena.ecs.csus.edu:3306/drocck", "drocck", "drocck_db");
			} catch(SQLException e){
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("VendorError: "+ e.getErrorCode());
			} catch (Exception e) {
				System.out.println(e.getMessage()+'\n'+e.getStackTrace());
			}
		}
	}
	
	
}
