package insert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.csvreader.CsvReader;

public class CSVReader {
	private CsvReader iou;
	
	public void start(){
		try{
			iou = new CsvReader("CSVInsert"+File.separator+"iouzipcodes2011.csv");
			iou.readHeaders();
			while(iou.readRecord()){
				String zip = iou.get("zip");
				String utilityName = iou.get("utility_name");
				String state = iou.get("state");
				String ownership = iou.get("ownership");
				String commRate = iou.get("comm_rate");
				String indRate = iou.get("ind_rate");
				String resRate = iou.get("res_rate");
				System.out.println(zip+","+utilityName+","+state+","+ownership+","+commRate+","+indRate+","+resRate);
			}
			iou.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
;	}
}
