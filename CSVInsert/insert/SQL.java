package insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

//REMEMBER: For insertion the query goes like this:
//			INSERT IGNORE INTO LOCATION(Zip,State) VALUES(...,...);

public class SQL {
	private static Connection con = null;
	//Make Connection
	public void initSQLConnection(){
		if(con==null){
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con = DriverManager.getConnection("jdbc:mysql://athena.ecs.csus.edu:3306/drocck?autoReconnect=true", "drocck", "drocck_db");
			} catch(SQLException e){
				System.out.println("Failed.");
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("VendorError: "+ e.getErrorCode());
			} catch (Exception e) {
				System.out.println("Failed.");
				System.out.println(e.getMessage()+'\n'+e.getStackTrace());
			}
		}
	}
	
	//Insert Location Data
	public void insertLocation(String zip, String state) throws SQLException{
		String query ="";
		query += "INSERT IGNORE INTO LOCATION(Zip,State) VALUES('";
		query += zip +"','" +state+"');";
		con.createStatement().execute(query);
	}
	
	//Insert Utility Data
	public void insertUtilityData(String zip, String state, String utilityName, String serviceType, String ownership, String commRate, String indRate, String resRate) throws SQLException{
		//First find foreign key
		String query="";
		query +="SELECT LocId FROM LOCATION WHERE Zip='"+zip+"' AND State='"+state+"';";
		ResultSet r = con.createStatement().executeQuery(query);
		r.first();
		int locId = r.getInt(1);
		//Insert statement
		query="";
		query += "INSERT IGNORE INTO UTILITY(Location_Id, Utility_Name, Service_Type, Ownership, Comm_Rate, Ind_Rate, Res_Rate) ";
		query += "VALUES("+locId+",'"+utilityName+"','"+serviceType+"','"+ownership+"',"+Float.parseFloat(commRate)+","+Float.parseFloat(indRate)+","+Float.parseFloat(resRate)+");";
		con.createStatement().execute(query);
	}
	
	public void insertLatLng(String zip, String lat, String lng) throws SQLException{
		String query ="";
		query += "UPDATE LOCATION "
				+ "SET Latitude="+lat+", Longitude="+lng+" ";
		query += "WHERE Zip='"+zip+"';";
		con.createStatement().execute(query);
	}
	
	//Close Connection
	public void closeConnection() {
		if (con != null)
			try {
				con.close();
				con = null;
			} catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("VendorError: "+ e.getErrorCode());
			}
	}
	
}
