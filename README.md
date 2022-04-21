# Command Cyber's Open STIG
Our team here at Command Cyber has developed OpenSTIG, which is a tool designed around the Defense Information Systems Agency’s (DISA) Security Technical Implementation Guides (STIGs) that allows users to view, filter, and download STIGs. The main improvements that our application provides compared to the tools that DISA offers is that our application allows STIGs to be downloaded from the application and it is open source to allow users to modify this application to add additional features or incorporate it into their own application.
## Requirements
[comment]: <> (List any of the external requirements, if it needs to be installed, and why they are used)
* <b>jsoup</b> - Java Library for HTML Parsing.
  * jsoup is an Open Source Java library. In this project, it is used to efficiently parse the HTML required for grabbing and building the STIG XML files that are to be downloaded.
## Installation 
[comment]: <> (Add steps for installation and common troubleshooting steps if necessary)
Note: These instructions are assuming that you are using Eclipse and GitHub Desktop.<br>
1. Clone the github (https://github.com/Jikkou9/CS-3443-003-Group-06) by either using Git Desktop (CTRL + SHIFT + O) or click on code and in the drop down menu click on download zip and specify the local path you want to store it as.
2. Import the copy to eclipse by clicking file > import > general > projects from folder or archive > directory or archive wherever your folder is and click finish.
3. Right click on the file and look at the end of the drop down and click on properties.
4. Click on the java compiler and check "enable project specific settings".
5. Go to the drop down menu to the right and make sure the jre or jdk is 1.8
6. Apply > Apply and Close.
## Known Bugs
[comment]: <> (List any known bugs by the deadline of the project)
* <b>Main Menu Size Error:</b><br>
  *When switching from local/online catalouges, the size of the main menu screen can become warped. To recreate:*
    * Go to local catalouge. View a catalouge. Switch to online catalouge. Go to main menu.<br><br>
    <b>Possible fixes:</b>
    * Setting a minimum/maximum size limit to the stage at the start using primaryStage.setMinHeight(), primaryStage.setMinWidth(), primaryStage.setMaxHeight(), and primaryStage.setMaxWidth() functions, and then passing these settings to other controllers.
    * Going into the fxml and changing the Min/Max height/width through the Border Pane Layout options, and then passing these settings through to other controllers.
  
## Authors
* Ismail Celik
* Brandon Chung
* Hunter Drummond
* Leon Le
* Jacob Rahimi
