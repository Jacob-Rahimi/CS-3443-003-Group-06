package application.model;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class LocalCatalog {
	
	public static ArrayList<String> arrayOfSTIGS = new ArrayList<String>();
	public static String[] fileList;
	public static String catalogPath = "src/data/catalog";
	
	public static ArrayList<String> readList(){
		File catalogLocation = new File(catalogPath);
		ArrayList<String> arrayOfSTIGS = enumerate(catalogLocation);
		for (String STIGS : arrayOfSTIGS)
		{
			System.out.println(STIGS);
		}
		return arrayOfSTIGS;
	}
	public static ArrayList<String> enumerate(File catalogLocation){
		fileList = catalogLocation.list();
		
		for(String fileName: fileList) {
			if(Pattern.matches(".*\\.xml$", fileName)) {
				arrayOfSTIGS.add(fileName);
			}
		}
		return arrayOfSTIGS;
		
	}
	
	public static void deleteSTIGs(File stigFile) {
		stigFile.delete();
	}
	
}
