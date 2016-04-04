package insert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import com.csvreader.CsvReader;

public class CSVReader {
	private CsvReader iou, niou;
	private static SQL sql = new SQL();
	
	public void start() throws SQLException{
		//Create SQL Connection
		sql.initSQLConnection();
		try{
			//investor owned csv
			iou = new CsvReader("CSVInsert"+File.separator+"iouzipcodes2011.csv");
			iou.readHeaders();
			while(iou.readRecord()){
				String zip = iou.get("zip");
				String utilityName = iou.get("utility_name");
				String state = iou.get("state");
				String serviceType = iou.get("service_type");
				String ownership = iou.get("ownership");
				String commRate = iou.get("comm_rate");
				String indRate = iou.get("ind_rate");
				String resRate = iou.get("res_rate");
				System.out.println(zip+","+utilityName+","+state+","+ownership+","+commRate+","+indRate+","+resRate);

				sql.insertLocation(zip, state);
				
				sql.insertUtilityData(zip, state, utilityName, serviceType, ownership, commRate, indRate, resRate);
			}
			iou.close();
			
			//non investor owned csv
			niou = new CsvReader("CSVInsert"+File.separator+"noniouzipcodes2011.csv");
			niou.readHeaders();
			while(niou.readRecord()){
				String zip = niou.get("zip");
				String utilityName = niou.get("utility_name");
				String state = niou.get("state");
				String serviceType = niou.get("service_type");
				String ownership = niou.get("ownership");
				String commRate = niou.get("comm_rate");
				String indRate = niou.get("ind_rate");
				String resRate = niou.get("res_rate");
				System.out.println(zip+","+utilityName+","+state+","+ownership+","+commRate+","+indRate+","+resRate);

				sql.insertLocation(zip, state);
				
				sql.insertUtilityData(zip, state, utilityName, serviceType, ownership, commRate, indRate, resRate);
			}
			niou.close();
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
