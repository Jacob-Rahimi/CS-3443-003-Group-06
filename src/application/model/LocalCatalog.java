package application.model;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

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
	
	public static void uploadXML (ListView<String> listView) {
		FileChooser fc = new FileChooser();
    	
    	fc.getExtensionFilters().addAll(new ExtensionFilter("XML Files", "*.xml"));
    	
    	File selectedFile = fc.showOpenDialog(null);
    	
    	if (selectedFile != null) {
    		listView.getItems().add(selectedFile.getName());
    		
    	}
    	else {
    		System.out.println("File was not uploaded");
    	}
    	
		
	}
	
}
