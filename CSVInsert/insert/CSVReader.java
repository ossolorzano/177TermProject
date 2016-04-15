package insert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import com.csvreader.CsvReader;

public class CSVReader {
	private CsvReader latlng;
	private static SQL sql = new SQL();
	
	public void start() throws SQLException{
		//Create SQL Connection
		sql.initSQLConnection();
		try{
			//longlat csv
			latlng = new CsvReader("CSVInsert"+File.separator+"latlong.csv");
			latlng.readHeaders();
			while(latlng.readRecord()){
				String zip = latlng.get("ZIP");
				String latitude = latlng.get("LAT");
				String longitude = latlng.get("LNG");
				System.out.println(zip+","+latitude+","+longitude);

				sql.insertLatLng(zip, latitude, longitude);
			}
			latlng.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		//close SQL connection
		sql.closeConnection();
	}
}
