### Kate Spade Automation - Selenium Automation
---
#### System Requirement:

* JDK 1.8
* Maven 3.3.3
* Eclipse or IDE of choice in case there is need to update the script. (optional)
* Set the system environment path of JAVA And Maven


## Execution

1: Go to the project directory where pom.xml exists through cmd terminal, and write following commands to execute script

For Regression Test in UK Desktop, 
mvn clean verify -Dtestxml=Desktop_Regression_UK.xml

For Regression Test in UK iPhone, 
mvn clean verify -Dtestxml=iPhone_Regression_UK.xml

For Regression Test in FR Desktop, 
mvn clean verify -Dtestxml=Desktop_Regression_FR.xml

For Regression Test in FR iPhone, 
mvn clean verify -Dtestxml=iPhone_Regression_FR.xml

For Regression Test in ROE Desktop, 
mvn clean verify -Dtestxml=Desktop_Regression_ROE.xml

For Regression Test in ROE iPhone, 
mvn clean verify -Dtestxml=iPhone_Regression_ROE.xml


## Test Package naming distribution by Locale and Viewport:

|           | regions           | US                           | UK                           |
|-----------|-------------------|------------------------------|------------------------------|
| viewports | regions.viewports | regions.us.viewports         | regions.uk.viewports         |
| desktop   | regions.desktop   | regions.us.viewports.desktop | regions.uk.viewports.desktop |
| mobile    | regions.mobile    | regions.us.viewports.mobile  | regions.uk.viewports.mobile  |
