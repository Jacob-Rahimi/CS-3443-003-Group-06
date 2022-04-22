package application.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ListView;

/**
 * Class which maintains the STIG data displayed and accessed from the 
 * Online Catalog scene. Shows the full Online Catalog and allows the user to 
 * download specified STIG XML files.
 */
public class OnlineCatalog {
	private String selectedFile;
	private List<String> namesList = new ArrayList<>(); // Stores names to be displayed in Online Catalog List
	private List<String> pageLinksList = new ArrayList<>(); // Stores entire STIG webpage link
	private List<String> XMLFileList = new ArrayList<>();	// Stores XML link to be downloaded
	
	/**
	 * Loads the list of available STIGs and displays them in the Online
	 * Catalog List.
	 * Called from initialize method.
	 * @param onlineCatalogList Passes the ListView object to display 
	 * all STIGs.
	 * @throws IOException when there is an error reading the STIG list
	 */
	public void loadOnlineCatalog(ListView<String> onlineCatalogList) throws IOException {
		try {
			// Parsing https://www.stigviewer.com/stigs for all initial page links
			org.jsoup.nodes.Document doc = Jsoup.connect("https://www.stigviewer.com/stigs").get();
			Elements links = doc.select("td");
			String s;
			int begin, end;
			
			for (int i = 0; i < links.size(); i++) {
				s = links.get(i).toString();
				begin = s.indexOf("/stig/") + 6;
				end = s.indexOf("/\">", begin);
				namesList.add(s.substring(begin, end));
				pageLinksList.add("https://www.stigviewer.com/stig/" + namesList.get(i));
			}
			
			// Adding all XML file names to Online Catalog List
			onlineCatalogList.getItems().addAll(namesList);
		
			onlineCatalogList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
				
				@Override
				public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
						selectedFile = onlineCatalogList.getSelectionModel().getSelectedItem();
					}
			});
			}

		catch(Exception e) {
			System.out.println(e);
			}
	}
	
	/**
	 * Adds the specified files from "Online Catalog" List to 
	 * "Files to be Downloaded" list.
	 * Used with the right arrow button
	 * @param filestbd the list of files to be downloaded
	 */
	public void queueFiles( ListView<String> filestbd) {
		filestbd.getItems().add(selectedFile);	// Adds file name to list of "Files to be Downloaded"			
	}
	
	/**
	 * Finds and stores the specified XML file links, then downloads
	 * each specified XML file within the "Files to be Downloaded" list.
	 * Used with the "Download" button
	 * Clears the "Files to be Downloaded" list once downloading finishes.
	 * @param filestbd Passes ListView Object to create XML links from.
	 * @throws IOException when there is an issue with downloading the file
	 */
	public void downloadFiles(ListView<String> filestbd) throws IOException {
		int size = filestbd.getItems().size();
		
		org.jsoup.nodes.Document doc;
		Elements links;
		String s, s2;
		int begin, end;
		// Stores XML webpage links from each webpage and downloads each specified file.
		for (int i = 0; i < size; i++) {
			s = "https://www.stigviewer.com/stig/" + filestbd.getItems().get(i); // Appends each webpage link to be accessed
			doc = Jsoup.connect(s).get(); // Stores each page's HTML text  
			links = doc.select("a#xml"); // Targets XML link on each page
			s2 = links.get(0).toString();
			begin = s2.indexOf("/stig/") + 6;
			end = s2.indexOf("\">");
			XMLFileList.add("https://www.stigviewer.com/stig/" + s2.substring(begin, end)); // Creates and stores each full XML link
			doc = Jsoup.connect(XMLFileList.get(i)).get();
			File file = new File(LocalCatalog.catalogPath + "\\" + filestbd.getItems().get(i) + ".xml");
				
			// Downloads XML file
			FileWriter fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);
			doc.outputSettings().charset("ISO-8859-1");
			pw.println(doc);
			pw.close();
		}
		// Clearing "Files to be Downloaded" list after files finish downloading.
		XMLFileList.clear();
	}

	/**
	 * Returns the selected file name stored.
	 * @return selectedFile
	 */
	public String getX() {
		return selectedFile;
	}

	/**
	 * Sets the selected file name.
	 * @param selectedFile the string to be used to set the objects' selected file
	 */
	public void setX(String selectedFile) {
		this.selectedFile = selectedFile;
	}

	/**
	 * Returns the list of file names to be displayed in 
	 * Online Catalog List.
	 * @return namesList
	 */
	public List<String> getLeftList() {
		return namesList;
	}

	/**
	 * Sets the list of files names to be displayed in the Online 
	 * Catalog List.
	 * @param namesList the string list to be used to set the objects' namesList
	 */
	public void setLeftList(List<String> namesList) {
		this.namesList = namesList;
	}

	/**
	 * Returns the list of individual STIG webpage links.
	 * @return pageLinksList
	 */
	public List<String> getPageLinksList() {
		return pageLinksList;
	}

	/**
	 * Sets the list of individual STIG webpage links.
	 * @param pageLinksList the string list to be used to set the objects' pageLinksList
	 */
	public void setPageLinksList(List<String> pageLinksList) {
		this.pageLinksList = pageLinksList;
	}

	/**
	 * Returns the list of individual XML File links.
	 * @return XMLFileList
	 */
	public List<String> getXMLFileList() {
		return XMLFileList;
	}

	/**
	 * Sets the list of individual XML file links.
	 * @param XMLFileList the string list to be used to set the objects' XMLFileList
	 */
	public void setXMLFileList(List<String> XMLFileList) {
		this.XMLFileList = XMLFileList;
	}
}
