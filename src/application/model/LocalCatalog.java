package application.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**This class is in charge of the Local Catalog section of the STIG viewer. It allows the users to upload, delete and view STIGS .xml files that are on their local directory.
 * @author Brandon Chung, Leon Le
 *
 */
public class LocalCatalog {
	
	
	// declaring and intiializing arrays and making them global to call when needed for changes.
	public static ArrayList<String> arrayOfSTIGS = new ArrayList<String>();
	public static String[] fileList;
	public static String catalogPath = "src/data/catalog/";
	
	// reads and calls on to enumerate to create that arraylist as well. Prints out the arrayList in the console and returns the array of STIGS.
	/**
	 * @return , reutns teh array of stigs after reading in the catalogPath and creating an arraylist of the STIGS in the catalog path.
	 */
	public static ArrayList<String> readList(){
		File catalogLocation = new File(catalogPath);
		ArrayList<String> arrayOfSTIGS = enumerate(catalogLocation);
		for (String STIGS : arrayOfSTIGS)
		{
			System.out.println(STIGS);
		}
		return arrayOfSTIGS;
	}
	
	//takes in the converts the file, into a string thats readable to add to the arrayList of String which is the .xml files. Returns the array back.
	/**
	 * @param catalogLocation, takes in the catalog location to enumerate and create the array list of stigs.
	 * @return, returns the array of STIGs after creating it from the given directory. 
	 */
	public static ArrayList<String> enumerate(File catalogLocation){
		fileList = catalogLocation.list();
		
		for(String fileName: fileList) {
			if(Pattern.matches(".*\\.xml$", fileName)) {
				arrayOfSTIGS.add(fileName);
			}
		}
		return arrayOfSTIGS;
		
	}
	
	// Takes in the .xml STIG file and deletes it from the directory and updates the arraylist and listview as well.
	/**
	 * @param stigFile, takes in the file.
	 */
	public static void deleteSTIGs(File stigFile) {
		stigFile.delete();
	}
	
	// Lets the user upload an .xml file from their local directory and checks if it was selected, if not then it outputs "ERROR: no file was selected".
	/**
	 * @param listView, takes in a list as a paramenter to update it.
	 * @throws IOException, throws an exception if there is an error taking in the file.
	 */
	public static void uploadXML (ListView<String> listView) throws IOException {
		FileChooser fc = new FileChooser();
    	
    	fc.getExtensionFilters().addAll(new ExtensionFilter("XML Files", "*.xml"));
    	
    	File selectedFile = fc.showOpenDialog(null);
    	
    	if (selectedFile != null) {
    		Files.copy(selectedFile.toPath(),
    				   Paths.get(catalogPath + selectedFile.getName()), 
    				   StandardCopyOption.REPLACE_EXISTING);
    	}
    	else {
    		System.out.println("ERROR: No file was selected.");
    	}
	}
	
}
