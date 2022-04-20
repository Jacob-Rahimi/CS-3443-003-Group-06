# Command Cyber's Open STIG
Our team here at Command Cyber has developed OpenSTIG, which is a tool designed around the Defense Information Systems Agency’s (DISA) Security Technical Implementation Guides (STIGs) that allows users to view, filter, and download STIGs. The main improvements that our application provides compared to the tools that DISA offers is that our application allows STIGs to be downloaded from the application and it is open source to allow users to modify this application to add additional features or incorporate it into their own application.
## Requirements
[comment]: <> (List any of the external requirements, if it needs to be installed, and why they are used)
* Example Package - Already Installed
  * Reason why the package was used
* <b>jsoup</b> - Java Library for HTML Parsing.
  * jsoup is an Open Source Java library. In this project, it is used to efficiently parse the HTML required for grabbing and building the STIG XML files that are to be downloaded.
## Installation 
[comment]: <> (Add steps for installation and common troubleshooting steps if necessary)
Note: These instructions are assuming that you are using Eclipse and GitHub Desktop.<br>
1. Clone the github...
## Known Bugs
[comment]: <> (List any known bugs by the deadline of the project)
* <b>Main Menu Size Error:</b><br>
  *When switching from local/online catalouges, the size of the main menu screen can become warped. To recreate:*
    * Go to local catalouge. View a catalouge. Switch to online catalouge. Go to main menu.<br><br>
    <b>Possible fixes:</b>
    * Setting a minimum/maximum size limit to the stage at the start using primaryStage.setMinHeight(), primaryStage.setMinWidth(), primaryStage.setMaxHeight(), and primaryStage.setMaxWidth() functions, and then passing these settings to other controllers.
    * Going into the fxml and changing the Min/Max height/width through the Border Pane Layout options, and then passing these settings through to other controllers.
* <b>Certain filters do not work when accessing files downloaded from the online catalouge.</b><br>
  *When attempting to filter for a file downloaded from the online catalouge, certain filter fields like "fixText" yield no results even for filter type of "Contains" for searches that theoretically should provide results. To recreate:*<br>
    * Go to online catalogue. Download "zos_roscoeacf.xml" or "zos_roscoeacf2.xml". Select Filter field "fixText" and filter text with any possible string or character. Select "Contains". Search with this filter.<br><br>
  
## Authors
* Ismail Celik
* Brandon Chung
* Hunter Drummond
* Leon Le
* Jacob Rahimi
