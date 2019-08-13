package com.netcracker.shaw.at_shaw_sd.pageobject;

import static com.netcracker.shaw.element.pageor.DnRFlowPageElement.*;
import static com.netcracker.shaw.element.pageor.RIFlowPageElement.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.netcracker.shaw.at_shaw_sd.pageobject.LandingPage;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import com.netcracker.shaw.at_shaw_sd.jdbc.JDBCValidator;
import com.netcracker.shaw.at_shaw_sd.util.Constants;
import com.netcracker.shaw.at_shaw_sd.util.ExcelOperation;
import com.netcracker.shaw.at_shaw_sd.util.PuttyConnector;
import com.netcracker.shaw.at_shaw_sd.util.Utility;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@SuppressWarnings(value = {"unchecked", "rawtypes"})
public class DnRFlowPage<DnRFlowPageElement> extends BasePage {

	LandingPage landing=null;

	private JDBCValidator jdbc = new JDBCValidator();

	Logger log = Logger.getLogger(DnRFlowPage.class);

	public DnRFlowPage(ExtentTest test) {
		super(test);
	}
	public DnRFlowPage(ExtentTest test, WebDriver driver) {
		super(test, driver);
	}
	public void setTest(ExtentTest test1) {
		test = test1;
	}

	public void setDriver(WebDriver driver1) {
		driver = driver1;
	}



	/*F****************************************************************************
	 *
	 * FUNCTION: selectLocation
	 *
	 * PURPOSE : To navigate to Location specified.
	 *           
	 */
	public void selectLocation(String location)
	{
		try {
			log.debug("Entering selectLocation : " + location );
			wait(2);
			switch (location) {
			case "ShawProject":
				navigate(Utility.getValueFromPropertyFile("inventory_url"));
				wait(1);
				click(SHAW_PROJECT);
				wait(2);
				break;
			case "Country":
				click(COUNTRY_CANADA);
				wait(2);
				break;
			case "State":
				click(STATE_ALBERTA);
				wait(2);
				break;
			case "City":
				scrollDown();
				click(CITY_CALGARY);
				wait(2);
				break;
			case "Building":
				click(BUILDING_ARBOUR_LAKE);
				wait(2);
				break;
			case "DefaultLoc" : 
				navigate(Utility.getValueFromPropertyFile("default_Loc"));
				wait(2);
				break;
			case "IPPlanner" : 
				navigate(Utility.getValueFromPropertyFile("ipPlanner_Loc"));
				wait(2);
				break;
			case "EOD_VLAN" : 
				navigate(Utility.getValueFromPropertyFile("eod_vlan_url"));
				wait(2);
				break;
			case "Transmission Circuit" : 
				click(CIRCUITS_TAB);
				wait(2);
				clickDynamic("span", location);
				wait(1);
				break;
			default:
				log.debug("Given Location not found on Page");
			}
			wait(3);
			test.log(LogStatus.INFO, "Navigated to Path shown below: " + test.addScreenCapture(addScreenshot()));
			log.debug("Leaving selectLocation : " + location);
		} catch (Exception e) {
			log.error("Error in Selecting Location :" + location + " " + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error in Selecting Location with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}


	/*F*******************************************************************************************
	 *
	 * FUNCTION: navigateToDataTransition
	 *
	 * PURPOSE : Navigate to DataTransition and Run the Pre-Requisite Scripts for DE and Recon.
	 *           
	 */
	public void navigateToDataTransition(String location)
	{
		try {
			log.debug("Entering navigateToDataTransition : " + location );
			wait(1);
			navigate(Utility.getValueFromPropertyFile("documents_url"));
			wait(1);
			test.log(LogStatus.INFO, "Navigation to Documents path: " + test.addScreenCapture(addScreenshot()));
			click(DATA_TRANSITION_PATH);
			wait(1);
			test.log(LogStatus.INFO, "Navigation to Data Transition path: " + test.addScreenCapture(addScreenshot()));
			click(CONFIGURATION_TAB);
			test.log(LogStatus.INFO, "Navigation to Configuration Tab of Data Transition: " + test.addScreenCapture(addScreenshot()));
			wait(1);
			switch (location) {
			case "Alcatel":
				click(ALCATEL_CONFIG);
				test.log(LogStatus.INFO, "Navigation to Alcatel Config Folder: " + test.addScreenCapture(addScreenshot()));
				generateScriptsSDBIDB("Alcatel");
				generateScriptsRecon("NFM-P DnR", "Generate");
				generateScriptsRecon("NFM-P DnR", "Update");
				wait(2);
				break;

			case "IPBB":
				click(CISCO_CONFIG);
				test.log(LogStatus.INFO, "Navigation to CiscoIP BB Config Folder: " + test.addScreenCapture(addScreenshot()));
				generateScriptsDataExport();
				generateScriptsRecon("Cisco IP Backbone", "Generate");
				generateScriptsRecon("Cisco IP Backbone", "Update");
				wait(2);
				break;

			case "CMTS":
				click(CMTS_CONFIG);
				test.log(LogStatus.INFO, "Navigation to CMTS Config Folder: " + test.addScreenCapture(addScreenshot()));
				generateScriptsDataExportCMTS();
				generateScriptsRecon("CMTS", "Generate");
				generateScriptsRecon("CMTS", "Update");
				wait(2);
				break;

			case "CMTS-OnDemand":
				click(CMTS_ON_DEMAND_CONFIG);
				test.log(LogStatus.INFO, "Navigation to CMTS On-Demand Config Folder: " + test.addScreenCapture(addScreenshot()));
				generateScriptsDataExportCMTS();
				generateScriptsRecon("CMTS On Demand", "Generate");
				generateScriptsRecon("CMTS On Demand", "Update");
				wait(2);
				break;

			case "Spectrum":
				click(SPECTRUM_CONFIG);
				test.log(LogStatus.INFO, "Navigation to Spectrum Config Folder: " + test.addScreenCapture(addScreenshot()));
				generateScriptsDataExportCMTS();
				generateScriptsRecon("Spectrum", "Generate");
				generateScriptsRecon("Spectrum", "Update");
				wait(2);
				break;										

			case "ETX":
				click(ETX_CONFIG);
				test.log(LogStatus.INFO, "Navigation to Spectrum Config Folder: " + test.addScreenCapture(addScreenshot()));
				generateScriptsSDBIDB("ETX");
				generateScriptsRecon("ETX", "Generate");
				generateScriptsRecon("ETX", "Update");
				wait(2);
				break;

			case "FDB":						
				click(FDB_CONFIG);
				wait(1);
				click(FDB_INTEG);
				wait(2);
				test.log(LogStatus.INFO, "Navigation to FDB Config Folder: " + test.addScreenCapture(addScreenshot()));
				generateScriptsSDBIDB("FDB");
				generateScriptsRecon("FDB", "Generate");
				generateScriptsRecon("FDB", "Update");
				wait(2);
				break;

			case "IPV4":
				click(IPV4_CONFIG);
				wait(1);
				test.log(LogStatus.INFO, "Navigation to IPV4 Config Folder: " + test.addScreenCapture(addScreenshot()));
				generateScriptsSDBIDB("IPV4");
				generateScriptsRecon("IP Range", "Generate");
				generateScriptsRecon("IP Range", "Update");
				wait(2);
				break;

			case "CIN" : 
				click(CIN_CONFIG);
				test.log(LogStatus.INFO, "Navigation to CIN Config Folder: " + test.addScreenCapture(addScreenshot()));
				generateScriptsDataExportCIN();
				generateScriptsRecon("CIN Recon", "Generate");
				generateScriptsRecon("CIN Recon", "Update");
				wait(2);
				break;

			default:
				log.debug("Given Location not found on Page");
			}
			wait(3);
			test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			log.debug("Leaving navigateToDataTransition : " + location);
		} catch (Exception e) {
			log.error("Error in navigateToDataTransition :" + location + " " + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error in Data Transition Navigation with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}


	/*F*********************************************************************************************************
	 *
	 * FUNCTION: generateScriptsDataExport
	 *
	 * PURPOSE : To Run Validation and Transformation scripts for IDB and SDB tables required for Data Export(IPBB).
	 *           
	 */
	public void generateScriptsDataExport() {				
		try {
			log.debug("Entering generateScriptsDataExport");
			wait(1);
			click(SDB_TABLE_IPBB);
			wait(1);
			click(GENERATE_SCRIPTS);
			wait(1);
			clickNthElement(TRANSFORM_SCRIPTS, 0);
			wait(1);
			switchToAlert();
			test.log(LogStatus.INFO, "Ran TransformScripts for SDB: " + test.addScreenCapture(addScreenshot()));
			do {
				wait(2);
			} while (!isDisplayed(SDB_TABLE_IPBB));
			click(SDB_TABLE_IPBB);
			wait(1);
			click(GENERATE_SCRIPTS);
			wait(1);
			clickNthElement(VALIDATE_SCRIPTS, 0);
			wait(1);
			switchToAlert();
			test.log(LogStatus.INFO, "Run ValidateScripts for SDB: " + test.addScreenCapture(addScreenshot()));
			do {
				wait(2);
			} while (!isDisplayed(IDB_TABLE_IPBB));
			click(IDB_TABLE_IPBB);   
			wait(1);
			click(GENERATE_SCRIPTS);
			wait(1);
			clickNthElement(TRANSFORM_SCRIPTS, 0);
			wait(1);
			switchToAlert();
			test.log(LogStatus.INFO, "Run TransformScripts for IDB: " + test.addScreenCapture(addScreenshot()));
			do {
				wait(2);
			} while (!isDisplayed(PROCEED_BUTTON));
			if ( isElementPresent(ERROR_REC)) {
				test.log(LogStatus.FAIL, "Error in generating scripts with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
				Assert.assertTrue(false, "ERROR Entries found while clicking the Proceed Btn.");
			}else {
				click(PROCEED_BUTTON);
				do {
					wait(2);
				} while (!isDisplayed(IDB_TABLE_IPBB));
				click(IDB_TABLE_IPBB);
				wait(1);
				click(GENERATE_SCRIPTS);
				wait(1);
				clickNthElement(VALIDATE_SCRIPTS, 0);
				wait(1);
				switchToAlert();
				test.log(LogStatus.INFO, "Run ValidateScripts for IDB: " + test.addScreenCapture(addScreenshot()));
				do {
					wait(2);
				} while (!isDisplayed(PROCEED_BUTTON));
			}
			if ( isElementPresent(ERROR_REC)) {
				test.log(LogStatus.FAIL, "Error in generating scripts with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
				Assert.assertTrue(false, "ERROR Entries found while clicking the Proceed Btn.");
			}else {
				click(PROCEED_BUTTON);
			}
			wait(2);
			log.debug("Leaving generateScriptsDataExport");
		} catch (Exception e) {
			log.error("Error in generateScriptsDataExport:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error in generating scripts with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}

	/*F***********************************************************************************************************************************
	 *
	 * FUNCTION: generateScriptsDataExportCIN
	 *
	 * PURPOSE : To Run Validation and Transformation scripts for IDB and SDB tables required for DE (CIN only).
	 *           
	 */

	public void generateScriptsDataExportCIN() {				
		try {
			log.debug("Entering generateScriptsDataExportCIN");
			wait(1);
			click(IDB_TABLE_SET);   
			wait(1);
			click(GENERATE_SCRIPTS);
			wait(1);
			clickNthElement(TRANSFORM_SCRIPTS, 0);
			wait(1);
			switchToAlert();
			test.log(LogStatus.INFO, "Run TransformScripts for IDB: " + test.addScreenCapture(addScreenshot()));
			do {
				wait(2);
			} while (!isDisplayed(PROCEED_BUTTON));
			if ( isElementPresent(ERROR_REC)) {
				test.log(LogStatus.FAIL, "Error in generating scripts with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
				Assert.assertTrue(false, "ERROR Entries found while clicking the Proceed Btn.");
			}else {
				click(PROCEED_BUTTON);
				do {
					wait(2);
				} while (!isDisplayed(IDB_TABLE_SET));
				click(IDB_TABLE_SET);
				wait(1);
				click(GENERATE_SCRIPTS);
				wait(1);
				clickNthElement(VALIDATE_SCRIPTS, 0);
				wait(1);
				switchToAlert();
				test.log(LogStatus.INFO, "Run ValidateScripts for IDB: " + test.addScreenCapture(addScreenshot()));
				do {
					wait(2);
				} while (!isDisplayed(PROCEED_BUTTON));
			}
			if ( isElementPresent(ERROR_REC)) {
				test.log(LogStatus.FAIL, "Error in generating scripts with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
				Assert.assertTrue(false, "ERROR Entries found while clicking the Proceed Btn.");
			}else {
				click(PROCEED_BUTTON);
			}
			wait(2);
			log.debug("Leaving generateScriptsDataExportCIN");
		} catch (Exception e) {
			log.error("Error in generateScriptsDataExportCIN:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error in generating scripts with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}

	/*F***********************************************************************************************************************************
	 *
	 * FUNCTION: generateScriptsSDBIDB
	 *
	 * PURPOSE : To Run Validation and Transformation scripts for IDB and SDB tables required for DE (CIN, IPV4, FDB, Alcatel, ETX).
	 *           
	 */

	public void generateScriptsSDBIDB(String deviceName) {				
		try {
			log.debug("Entering generateScriptsSDBIDB for deviceName : " + deviceName);
			wait(1);
			if ( deviceName.equalsIgnoreCase("CIN"))
			{
				click(SDB_SNMP_TABLE_SET);
			}
			else
			{
				click(SDB_TABLE_SET);
			}
			wait(1);
			click(GENERATE_SCRIPTS);
			wait(1);
			if ( deviceName.equalsIgnoreCase("Alcatel") )
			{
				clickNthElement(TRANSFORM_SCRIPTS, 1);
			}
			else
			{
				clickNthElement(TRANSFORM_SCRIPTS, 0);
			}
			wait(1);
			switchToAlert();
			wait(1);
			test.log(LogStatus.INFO, "Ran TransformScripts for SDB: " + test.addScreenCapture(addScreenshot()));
			if ( deviceName.equalsIgnoreCase("CIN"))
			{
				do {
					wait(2);
				} while (!isDisplayed(SDB_SNMP_TABLE_SET));
				click(SDB_SNMP_TABLE_SET);
			}
			else
			{
				do {
					wait(2);
				} while (!isDisplayed(PROCEED_BUTTON));
				if ( isElementPresent(ERROR_REC)) {
					test.log(LogStatus.FAIL, "Error in generating scripts with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
					Assert.assertTrue(false, "ERROR Entries found while clicking the Proceed Btn.");
				}else {
					click(PROCEED_BUTTON);
					do {
						wait(2);
					} while (!isDisplayed(SDB_TABLE_SET));
					click(SDB_TABLE_SET);
				}
				wait(1);
				click(GENERATE_SCRIPTS);
				wait(1);
				if ( deviceName.equalsIgnoreCase("Alcatel") )
				{
					clickNthElement(VALIDATE_SCRIPTS, 1);
				}
				else
				{
					clickNthElement(VALIDATE_SCRIPTS, 0);
				}
				wait(1);
				switchToAlert();
				wait(1);
				test.log(LogStatus.INFO, "Ran ValidateScripts for SDB: " + test.addScreenCapture(addScreenshot()));

				if (deviceName.equalsIgnoreCase("IPV4") || deviceName.equalsIgnoreCase("CIN"))
				{
					if ( isElementPresent(RETURN) ) {
						wait(1);
						driver.navigate().back();
						wait(2);
						if ( deviceName.equalsIgnoreCase("IPV4"))
						{
							click(SDB_TABLE_SET);
						}else
						{
							click(SDB_SNMP_TABLE_SET);
						}
					}
				}
				do {
					wait(2);
				} while (!isDisplayed(PROCEED_BUTTON));
			}
			if ( isElementPresent(ERROR_REC)) {
				test.log(LogStatus.FAIL, "Error in generating scripts with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
				Assert.assertTrue(false, "ERROR Entries found while clicking the Proceed Btn.");
			}else {
				click(PROCEED_BUTTON);

				if (deviceName.equalsIgnoreCase("ETX") ) {
					wait(1);
					if ( isElementPresent(RETURN) ) {
						wait(2);
						if (deviceName.equalsIgnoreCase("ETX") )
						{
							returnToPreviousPageTwosteps();
							wait(2);
							click(SDB_TABLE_SET);
						}
					}	
				}
				do {
					wait(2);
				} while (!isDisplayed(IDB_TABLE_SET));
				click(IDB_TABLE_SET);   
				wait(1);
				click(GENERATE_SCRIPTS);
				wait(1);
				if ( deviceName.equalsIgnoreCase("Alcatel") )
				{
					clickNthElement(TRANSFORM_SCRIPTS, 1);
				}
				else
				{
					clickNthElement(TRANSFORM_SCRIPTS, 0);
				}
				wait(1);
				switchToAlert();
				wait(1);
				test.log(LogStatus.INFO, "Ran TransformScripts for IDB: " + test.addScreenCapture(addScreenshot()));
				do {
					wait(2);
				}while (!isDisplayed(PROCEED_BUTTON));
			}
			if ( isElementPresent(ERROR_REC)) {
				test.log(LogStatus.FAIL, "Error in generating scripts with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
				Assert.assertTrue(false, "ERROR Entries found while clicking the Proceed Btn.");
			}else {
				click(PROCEED_BUTTON);
				wait(1);
				do {
					wait(2);
				} while (!isDisplayed(IDB_TABLE_SET));
				click(IDB_TABLE_SET);
				wait(1);
				click(GENERATE_SCRIPTS);
				wait(1);
				if ( deviceName.equalsIgnoreCase("Alcatel") )
				{
					clickNthElement(VALIDATE_SCRIPTS, 1);
				}
				else
				{
					clickNthElement(VALIDATE_SCRIPTS, 0);
				}
				wait(1);
				switchToAlert();
				wait(1);
				test.log(LogStatus.INFO, "Ran ValidateScripts for IDB: " + test.addScreenCapture(addScreenshot()));
				do {
					wait(2);
				} while (!isDisplayed(PROCEED_BUTTON));
			}
			wait(2);
			test.log(LogStatus.PASS, "Data Run Successfully");
			log.debug("Leaving generateScriptsSDBIDB");
		} catch (Exception e) {
			log.error("Error in generateScriptsSDBIDB:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error in generating scripts with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}

	/*F***************************************************************************************************************************************
	 *
	 * FUNCTION: generateScriptsDataExportCMTS
	 *
	 * PURPOSE : To Run Validation and Transformation scripts for IDB and SDB tables required for Data Export(CMTS, CMTS-OnDemand, Spectrum).
	 *           
	 */
	public void generateScriptsDataExportCMTS() {				
		try {
			log.debug("Entering generateScriptsDataExportCMTS");
			wait(1);
			click(SDB_SNMP_CLI_TABLE);   
			wait(1);
			click(GENERATE_SCRIPTS);
			wait(1);
			clickNthElement(TRANSFORM_SCRIPTS, 0);
			wait(1);
			switchToAlert();
			test.log(LogStatus.INFO, "Run TransformScripts for CMTS SDB SNMP: " + test.addScreenCapture(addScreenshot()));
			do {
				wait(2);
			} while (!isDisplayed(SDB_SNMP_CLI_TABLE));
			click(SDB_SNMP_CLI_TABLE);
			wait(1);
			click(GENERATE_SCRIPTS);
			wait(1);
			clickNthElement(VALIDATE_SCRIPTS, 0);
			wait(1);
			switchToAlert();
			test.log(LogStatus.INFO, "Run ValidateScripts for CMTS SDB SNMP: " + test.addScreenCapture(addScreenshot()));
			do {
				wait(2);
			} while (!isDisplayed(INT_IDB_TABLE));
			click(INT_IDB_TABLE);
			wait(2);
			click(GENERATE_SCRIPTS);
			wait(1);
			clickNthElement(TRANSFORM_SCRIPTS, 0);
			wait(1);
			switchToAlert();
			test.log(LogStatus.INFO, "Run TransformScripts for CMTS IDB: " + test.addScreenCapture(addScreenshot()));
			do {
				wait(2);
			} while (!isDisplayed(PROCEED_BUTTON));
			if ( isElementPresent(ERROR_REC)) {
				test.log(LogStatus.FAIL, "Error in generating scripts with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
				Assert.assertTrue(false, "ERROR Entries found while clicking the Proceed Btn.");
			}else {
				click(PROCEED_BUTTON);
				do {
					wait(2);
				} while (!isDisplayed(INT_IDB_TABLE));
				click(INT_IDB_TABLE);
				wait(1);
				click(GENERATE_SCRIPTS);
				wait(1);
				clickNthElement(VALIDATE_SCRIPTS, 0);
				wait(1);
				switchToAlert();
				test.log(LogStatus.INFO, "Run ValidateScripts for CMTS IDB: " + test.addScreenCapture(addScreenshot()));
				do {
					wait(2);
				} while (!isDisplayed(PROCEED_BUTTON));
			}
			if ( isElementPresent(ERROR_REC)) {
				test.log(LogStatus.FAIL, "Error in generating scripts with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
				Assert.assertTrue(false, "ERROR Entries found while clicking the Proceed Btn.");
			}else {
				click(PROCEED_BUTTON);
			}
			wait(2);
			test.log(LogStatus.PASS, "CMTS Data Run Successfully");
			log.debug("Leaving generateScriptsDataExportCMTS");
		} catch (Exception e) {
			log.error("Error in generateScriptsDataExportCMTS:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error in generate Scripts DataExport with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}

	/*F****************************************************************************
	 *
	 * FUNCTION: generateScriptsRecon
	 *
	 * PURPOSE : To Run Recon Scripts required for Reconciliation.
	 *           
	 */
	public void generateScriptsRecon(String deviceName, String scriptName) throws Exception 
	{				
		try {
			log.debug("Entering generateScriptsRecon for running : " + scriptName + "script" + " for device " +  deviceName);
			wait(1);
			navigate(Utility.getValueFromPropertyFile("documents_url"));
			wait(1);
			click(RECON_DOCUMENTS);
			wait(1);
			clickDynamic("span", deviceName);
			wait(1);
			try
			{
				if ( scriptName.equalsIgnoreCase("Generate"))
				{
					click(GENERATE_SCRIPTS_RECON);
					wait(3);
					test.log(LogStatus.INFO, "Clicked GenerateScripts: " + test.addScreenCapture(addScreenshot()));
					wait(2);
					while (isDisplayed(CLOSE))
					{
						wait(5);
					}
				}
				else
				{
					click(UPDATE_DATA);
					wait(3);
					test.log(LogStatus.INFO, "Clicked Update Data Structure: " + test.addScreenCapture(addScreenshot()));
					wait(2);
					while (isDisplayed(CLOSE))
					{
						wait(5);
					}
				}
			}
			catch (Exception e)
			{
				clickNthElement(REPORT_TAB, 0);
				wait(2);
			}
		}
		catch (Exception e1) 
		{
			log.error("Error in generateScriptsRecon:" + e1.getMessage());
			test.log(LogStatus.ERROR, e1);
			test.log(LogStatus.FAIL, "Error in generate Scripts Recon with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			log.debug("Leaving generateScriptsRecon");
			Assert.assertTrue(false, e1.getMessage());
		}
		log.debug("Leaving generateScriptsRecon for running : " + scriptName + "script");
		test.log(LogStatus.PASS, "Reconciliation Document Script: " + scriptName + " script ran Successfully");
	}


	/*F****************************************************************************
	 *
	 * FUNCTION: navigateToDataFlow
	 *
	 * PURPOSE : Navigate to DATAFLOW repository and run session.
	 *           
	 */
	public String navigateToDataFlow(String deviceName, String sessionName, String spclAttribute, String sessionPurpose, String isDualSession)
	{
		String sessionId = null;
		try {
			log.debug("Entering navigateToDataFlow of : " + deviceName + " to run the session : " + sessionName  );
			wait(2);
			navigate(Utility.getValueFromPropertyFile("documents_url"));
			wait(2);
			test.log(LogStatus.INFO, "Navigation to Documents Path: " + test.addScreenCapture(addScreenshot()));
			click(DATAFLOW_PROJECT); 
			wait(1);
			test.log(LogStatus.INFO, "Navigation to DataFlow Project Path: " + test.addScreenCapture(addScreenshot()));
			switch (deviceName) {
			case "Alcatel":
				click(ALCATEL_INTEGRATION);  
				log.debug("Entered ALCATEL Integration Folder");
				if ( sessionName.equalsIgnoreCase("DE") ) 
				{
					runStartSessionDE("Alcatel", sessionPurpose, isDualSession);
				}
				else
				{
					sessionId = runStartSessionRecon("Alcatel", sessionPurpose);
				}
				wait(2);
				break;
				
			case "IPBB":
				click(CISCO_INTEGRATION);
				test.log(LogStatus.INFO, "Navigation to Cisco IP Backbone Integration Path: " + test.addScreenCapture(addScreenshot()));
				log.debug("Entered IPBB Integration Folder");
				if ( sessionName.equalsIgnoreCase("DE") ) 
				{
					runStartSessionDE("IPBB", sessionPurpose, isDualSession);
				}
				else
				{
					sessionId = runStartSessionRecon("IPBB", sessionPurpose);
				}
				wait(2);
				break;
				
			case "CMTS":
				click(CMTS_INTEGRATION); 
				log.debug("Entered CMTS Integration Folder");
				if ( sessionName.equalsIgnoreCase("DE") ) 
				{
					runStartSessionDE("CMTS", sessionPurpose, isDualSession);
				}
				else
				{
					sessionId = runStartSessionRecon("CMTS", sessionPurpose);
				}
				wait(2);
				break;

			case "CMTS-OnDemand":
				click(CMTS_ON_DEMAND_INTEGRATION); 
				log.debug("Entered CMTS On Demand Integration Folder");
				sessionId = runOnDemandStartSession(spclAttribute, "CMTS-OnDemand");
				wait(2);
				break;

			case "Spectrum":
				click(SPECTRUM_INTEGRATION);
				log.debug("Entered SPECTRUM Integration Folder");
				if ( sessionName.equalsIgnoreCase("DE") ) 
				{
					runStartSessionDE("Spectrum", sessionPurpose, isDualSession);
				}
				else
				{
					sessionId = runStartSessionRecon("Spectrum", sessionPurpose);
				}
				wait(2);
				break;
				
			case "ETX":
				click(ETX_INTEGRATION);
				log.debug("Entered ETX Integration Folder");
				if ( sessionName.equalsIgnoreCase("DE") ) 
				{
					runStartSessionDE("ETX", sessionPurpose, isDualSession);
				}
				else
				{
					sessionId = runStartSessionRecon("ETX", sessionPurpose);
				}
				wait(2);
				break;
				
			case "FDB":
				click(FDB_INTEGRATION);
				log.debug("Entered FDB Integration Folder");
				if ( sessionName.equalsIgnoreCase("DE") ) 
				{
					runStartSessionDE("FDB", sessionPurpose, isDualSession);
				}
				else
				{
					sessionId = runStartSessionRecon("FDB", sessionPurpose);
				}
				wait(2);
				break;
				
			case "IPV4":
				click(FDB_INTEGRATION);
				log.debug("Entered FDB Integration Folder");
				if ( sessionName.equalsIgnoreCase("DE") ) 
				{
					runStartSessionDE("IPV4", sessionPurpose, isDualSession);
				}
				else
				{
					sessionId = runStartSessionRecon("IPV4", sessionPurpose);
				}
				wait(2);
				break;
				
			case "CIN":
				click(CIN_INTEGRATION);
				log.debug("Entered CIN Integration Folder");
				if ( sessionName.equalsIgnoreCase("DE") ) 
				{
					runStartSessionDE("CIN", sessionPurpose, isDualSession);
				}
				else
				{
					sessionId = runStartSessionRecon("CIN", sessionPurpose);
				}
				wait(2);
				break;

			case "CIN-OnDemand" : 
				click(CIN_INTEGRATION); 
				log.debug("Entered CIN Integration Folder");
				sessionId = runOnDemandStartSession(spclAttribute, "CIN-OnDemand");
				wait(2);
				break;
				
			default:
				log.debug("Given Location not found on Page");
			}
			wait(2);
			log.debug("Leaving navigateToDataFlow : " + deviceName);
		} catch (Exception e) {
			log.error("Error in navigateToDataFlow :" + deviceName + " " + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error in navigateToDataFlow with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
		return sessionId;
	}


	/*F****************************************************************************
	 *
	 * FUNCTION: runStartSessionDE
	 *
	 * PURPOSE : To Trigger the DataExport Session.
	 *           
	 */
	public void runStartSessionDE(String deviceName, String sessionPurpose, String isDualSession) {
		try {
			log.debug("Entering runStartSessionDE with deviceName : " + deviceName + " with sessionPurpose : " + sessionPurpose);

			if ( deviceName.equalsIgnoreCase("FDB") || deviceName.equalsIgnoreCase("IPV4") )
			{
				if ( deviceName.equalsIgnoreCase("FDB")) 
				{
					click(FDB_DATA_EXPORT);
					wait(2);
					click(FDB_CUST_DATA_EXPORT);
					wait(1);
				}
				else
				{
					click(IPV4_DATA_EXPORT);
					wait(2);
					click(EXPORT_IPV4_RANGE);
					wait(1);
				}
			}
			else
			{
				click(DATA_EXPORT);
				wait(2);
			}
			if ( deviceName.equalsIgnoreCase("CMTS") || deviceName.equalsIgnoreCase("ETX") || deviceName.equalsIgnoreCase("Spectrum") || deviceName.equalsIgnoreCase("CIN") )
			{
				clickNthElement(SNMP_NTW_DISCOVERY, 0);
				wait(2);
			}
			/*if ( deviceName.equalsIgnoreCase("CIN") )
			{
				click(OXIDIZED_DISC_EXPORT);
				wait(2);
			}*/

			if ( sessionPurpose.equalsIgnoreCase("ParallelRun") ) {
				if ( isDualSession.equalsIgnoreCase("No")) {
					System.out.println("In If of IsDualSession ");
					validateSecondSession(deviceName, "DataExport", isDualSession);
				}
				else {
					System.out.println("In else of IsDualSession ");
					validateSecondSession(deviceName, "Recon", isDualSession);
				}
			} else {
				click(START_SESSION);  
				wait(1);

				test.log(LogStatus.INFO, "Triggered Start Session of Data Export at time : " + Constants.DATE_FORMAT.format(new Date()) + test.addScreenCapture(addScreenshot()));
				log.debug("Session Started for Data Export");
				refreshPage();
				wait(3);
				refreshPage();
				wait(3);
				while(isElementPresent(PRIORITY))
				{
					wait(5);
					refreshPage();
					wait(5);
					System.out.println("In Progress while of DE");

					if (isElementPresent(DATA_SESSION_TERMINATED))
					{
						System.out.println("In DATA_SESSION_TERMINATED");
						test.log(LogStatus.INFO, "Data Export Session Terminated with Errors at time :  " + Constants.DATE_FORMAT.format(new Date()) );
						test.log(LogStatus.FAIL, "Data Export Session Terminated with Errors. Please resolve the errors and re-run: " + test.addScreenCapture(addScreenshot()));
						Assert.assertTrue(false, "Data Export Session Terminated with Errors. Please resolve the errors and re-run");
						break;
					}
					if ( isElementPresent(DATA_SESSION_COMPLETE) || isElementPresent(DATA_SESSION_WARNING))
					{
						System.out.println("In DATA_SESSION_COMPLETE DE ");
						test.log(LogStatus.INFO, "Data Export Session Completed Successfully at time : " + Constants.DATE_FORMAT.format(new Date()));
						test.log(LogStatus.PASS, "Data Export Session Completed Successfully. " + test.addScreenCapture(addScreenshot()));
						Assert.assertTrue(true, "Data Export Session Completed Successfully.");
						break;
					}
					System.out.println("Dint go to SESSION COMPLETE LOOP");
				}
			}
			wait(2);
		}catch (Exception e) {
			log.error("Error in runStartSessionDE:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error while Running DataExport with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}


	/*F****************************************************************************
	 *
	 * FUNCTION: runStartSessionRecon
	 *
	 * PURPOSE : To Trigger the DataExport and Reconciliation Sessions.
	 *           
	 */
	public String runStartSessionRecon(String deviceName, String sessionPurpose) {
		String sessionId = null;
		try {
			log.debug("Entering runStartSessionRecon with deviceName : " + deviceName);
			wait(1);
			if ( deviceName.equalsIgnoreCase("FDB") || deviceName.equalsIgnoreCase("IPV4") )
			{
				if ( deviceName.equalsIgnoreCase("FDB")) 
				{
					click(FDB_RECON);
					wait(2);
					click(FDB_CUST_DATA_EXPORT);
					wait(2);
				}
				else
				{
					click(IPV4_RECON);
					wait(2);
					click(EXPORT_IPV4_RANGE);
					wait(2);
				}
			}
			else
			{
				click(RECONCILIATION);
			}
			test.log(LogStatus.INFO, "Navigation to Reconciliation : " + test.addScreenCapture(addScreenshot()));
			wait(1);
			if ( deviceName.equalsIgnoreCase("CMTS"))
			{
				click(SNMP_CLI_RECON);
				wait(2);
			}
			if ( deviceName.equalsIgnoreCase("Spectrum") || deviceName.equalsIgnoreCase("ETX") || deviceName.equalsIgnoreCase("CIN"))
			{
				clickNthElement(SNMP_NTW_DISCOVERY, 0);
				wait(2);
			}
			if ( sessionPurpose.equalsIgnoreCase("ParallelRun") ) {

				validateSecondSession(deviceName, "Recon", "");

			} else {
				click(START_SESSION);
				wait(1);
				test.log(LogStatus.INFO, "Triggered Start Session of Reconciliation at time : " + Constants.DATE_FORMAT.format(new Date()) + test.addScreenCapture(addScreenshot()));
				log.debug("Session Started for Reconciliation");
				wait(2);
				refreshPage();
				String statusUrl = driver.getCurrentUrl();
				sessionId = statusUrl.split("=")[2];
				System.out.println("Session Id : " + sessionId);
				wait(2);

				while(isElementPresent(PRIORITY))
				{
					wait(10);
					refreshPage();
					wait(5);
					if (isElementPresent(DATA_SESSION_TERMINATED))
					{
						System.out.println("In DATA_SESSION_TERMINATED");
						test.log(LogStatus.INFO, "Reconciliation Session Terminated with Errors at time : " + Constants.DATE_FORMAT.format(new Date()));
						test.log(LogStatus.FAIL, "Reconciliation Session Terminated with Errors. Please resolve the errors and re-run: " + test.addScreenCapture(addScreenshot()));
						if (!sessionPurpose.equalsIgnoreCase("VLANRun"))
							Assert.assertTrue(false, "Reconciliation Session Terminated with Errors. Please resolve the errors and re-run");
						break;
					}
					if ( isElementPresent(DATA_SESSION_COMPLETE) || isElementPresent(DATA_SESSION_WARNING))
					{
						test.log(LogStatus.INFO, "Reconciliation Session Completed Successfully at time : " + Constants.DATE_FORMAT.format(new Date()));
						test.log(LogStatus.PASS, "Reconciliation Session Completed Successfully. " + test.addScreenCapture(addScreenshot()));
						Assert.assertTrue(true, "Reconciliation Session Completed Successfully.");
						break;
					}
				}
			}

		}catch (Exception e) {
			log.error("Error in runStartSessionRecon:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error while Running Reconciliation with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
		return sessionId;
	}

	/*F****************************************************************************
	 *
	 * FUNCTION: runOnDemandStartSession
	 *
	 * PURPOSE : To Trigger the DataExport and Reconciliation of On-Demand Sessions.
	 *           
	 */
	public String runOnDemandStartSession(String ipOrDNSValue, String integName) {
		String sessionId = null;
		try {
			log.debug("Entering runOnDemandStartSession with Value : " + ipOrDNSValue);
			wait(1);
			if ( integName.equalsIgnoreCase("CMTS-OnDemand") ) {
			clickNthElement(OPERATIONS, 1);
			wait(1);
			click(DEVICE_SPLIT);
			wait(1);
			inputText(ENTER_VALUE, ipOrDNSValue);
			wait(1);
			test.log(LogStatus.INFO, "Entered IP : " + ipOrDNSValue + test.addScreenCapture(addScreenshot()));
			wait(1);
			click(LAUNCH_BTN);
			test.log(LogStatus.INFO, "Launched Session at time : " + Constants.DATE_FORMAT.format(new Date()) );
			wait(2);
			click(CMTS_START_DE_RECON);
			} else {
			click(CIN_ON_DEMAND_FOLDER);
			wait(2);
			clickNthElement(OPERATIONS, 1);
			wait(1);
			click(DISCOVER_CIN_DEVICES);
			wait(1);
			inputText(ENTER_VALUE, ipOrDNSValue);
			wait(1);
			test.log(LogStatus.INFO, "Entered DNS Names : " + ipOrDNSValue + test.addScreenCapture(addScreenshot()));
			wait(1);
			click(LAUNCH_BTN);
			test.log(LogStatus.INFO, "Launched Session at time : " + Constants.DATE_FORMAT.format(new Date()) );
			wait(2);
			click(CIN_START_DE_RECON);
			}
			wait(2);
			click(SESSIONS_TAB);
			wait(1);
			click(LATEST_VALID_SESSION);
			wait(1);
			clickNthElement(STATUS_TAB, 0);
			wait(2);
			refreshPage();
			String statusUrl = driver.getCurrentUrl();
			sessionId = statusUrl.split("=")[2];

			test.log(LogStatus.INFO, "Session In Progress: " + test.addScreenCapture(addScreenshot()));
			wait(2);
			try
			{
				while(isElementPresent(PRIORITY))
				{
					wait(10);
					refreshPage();
					wait(5);
					if (isElementPresent(ERROR_RUN))
					{
						test.log(LogStatus.INFO, "Session Terminated with Errors at time : " + Constants.DATE_FORMAT.format(new Date()));
						test.log(LogStatus.FAIL, "Session Terminated with Errors. Please resolve the errors and re-run: " + test.addScreenCapture(addScreenshot()));
						Assert.assertTrue(false, "Session Terminated with Errors. Please resolve the errors and re-run");
						break;
					}
					if (isElementPresent(DATA_SESSION_COMPLETE))
					{
						test.log(LogStatus.INFO, "Session Completed Successfully at time : " + Constants.DATE_FORMAT.format(new Date()));
						test.log(LogStatus.PASS, "Session Completed Successfully. " + test.addScreenCapture(addScreenshot()));
						Assert.assertTrue(true, "Session Completed Successfully.");
						test.log(LogStatus.PASS, "Data Export and Reconciliation ran Successfully");
						break;
					}
				}
			}
			catch(Exception e1)
			{
				log.error("Error in runOnDemandStartSession:" + e1.getMessage());
				test.log(LogStatus.ERROR, e1);
				test.log(LogStatus.INFO, "There was an error while running Start Session");
				test.log(LogStatus.FAIL, "Error while Running Start Export and Reconciliation for CMTS with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
				Assert.assertTrue(false, e1.getMessage());
			} 
		}catch (Exception e) {
			log.error("Error in runOnDemandStartSession:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.INFO, "There was an error while running Session");
			test.log(LogStatus.FAIL, "Error while Running Start Export and Reconciliation for CMTS with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}

		return sessionId;

	}

	/*F********************************************************************************************
	 *
	 * FUNCTION: runReconQuery
	 *
	 * PURPOSE : To run the Recon Query and get the actions and process as per the actions derived.  
	 *           
	 */
	public void runReconQuery(String deviceName, String runMode, String sessionId)
	{
		try
		{
			String tableName = null;
			if ( sessionId == null )
			{
				test.log(LogStatus.FAIL, "No Valid DF Session is found to run the recon query.");
				Assert.assertTrue(false, "No Valid DF Session is found to run the recon query.");
			}else
			{
				test.log(LogStatus.INFO, "Processing Recon Query for device : " + deviceName + " for " + runMode);
				tableName = jdbc.getTableName(sessionId, deviceName);
				System.out.println("Table Name Fetched : " + tableName);
				processActionsByRunMode(tableName, runMode);
			}
		}catch (Exception e) {
			log.error("Error in runReconQuery:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error in running the Recon query with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}

	}

	/*F*******************************************************************************************************
	 *
	 * FUNCTION: processActionsByRunMode
	 *
	 * PURPOSE : Prints the Recon Query result and pass/fail the validations as per the run mode and actions.
	 *           
	 */

	private void processActionsByRunMode(String tableName, String runMode) throws Exception {


		String runReconquery = jdbc.printReconQueryDetails(tableName);
		test.log(LogStatus.INFO, "<p>Recon Query Result: <p>" + runReconquery);

		List<String> actionDetailList = jdbc.getReconQueryDetails(tableName);

		if(actionDetailList.size() == 0) {
			test.log(LogStatus.PASS, "No rows returned in Recon query for the : " + runMode);
			Assert.assertTrue(true, "No rows returned in Recon query for the : " + runMode);
			return;
		}

		String actionToCheck;
		switch (runMode) {
		case "FirstRun" : actionToCheck = "Insert"; break;
		case "SecondRun" : actionToCheck = "No Action"; break;
		case "ThirdRun" : actionToCheck = "No Action Instead if Delete"; break;
		default: actionToCheck = ""; break;
		}

		boolean testFailed = false;
		for (String actionFromDB : actionDetailList) {
			if(actionFromDB.equalsIgnoreCase(actionToCheck)) {
				// actions are equal so do nothing


			} else {
				// Actions are different. So Fail the test and break
				System.out.println("Action From DB: "+actionFromDB+", Action to Check: "+actionToCheck);
				testFailed = true;

				log.error("Recon query returned invalid action ("+actionFromDB+") for the  :" + runMode);
				test.log(LogStatus.FAIL, "Recon query returned invalid action ("+actionFromDB+") for the  :" + runMode);
				Assert.assertTrue(false, "Recon query returned invalid action ("+actionFromDB+") for the  :" + runMode);

				break;
			}	

		}

		if (!testFailed) {
			log.info("Recon query returned valid action ("+actionToCheck+") for the  :" + runMode);
			test.log(LogStatus.PASS, "Recon query returned valid action  ("+actionToCheck+") for the  :" + runMode);
			Assert.assertTrue(true, "Recon query returned valid action  ("+actionToCheck+") for the  :" + runMode);
		}
	}


	/*F****************************************************************************
	 *
	 * FUNCTION: validateIDBSDBTable
	 *
	 * PURPOSE : To Validate the counts of IDB and SDB Tables.
	 * 
	 * CALL :  order.validateIDBSDBTableCount("IPBB");
	 */
	public void validateIDBSDBTableCount(String deviceName)
	{
		try
		{
			log.debug("Entering validateIDBSDBTableCount for device : " + deviceName);

			String sdbTablesQuery = null;
			String sdbTablesCountQuery = null;
			String idbTablesQuery = null;
			String idbTablesCountQuery = null;
			String sdbTableCountsQuery = null;
			String idbTableCountsQuery = null;

			switch (deviceName) {

			case "IPBB" : 

				sdbTablesQuery = jdbc.printSDBTables("No", "IPBB");
				test.log(LogStatus.INFO, "<p>SDB Tables List for : " + deviceName + " </p>" + sdbTablesQuery);
				sdbTablesCountQuery = jdbc.printSDBTables("Yes", "IPBB");
				test.log(LogStatus.INFO, "<p>Total Num of SDB Tables for : " + deviceName + " </p>" + sdbTablesCountQuery);
				idbTablesQuery = jdbc.printIDBTables("No", "IPBB");
				test.log(LogStatus.INFO, "<p>IDB Tables List for : " + deviceName + " </p>" + idbTablesQuery);
				idbTablesCountQuery = jdbc.printIDBTables("Yes", "IPBB");
				test.log(LogStatus.INFO, "<p>Total Num of IDB Tables for : " + deviceName + " </p>" +  idbTablesCountQuery);
				sdbTableCountsQuery = jdbc.printSDBCount("IPBB");
				test.log(LogStatus.INFO, "<p>Each SDB Table Counts for : " + deviceName + " </p>" +  sdbTableCountsQuery);
				idbTableCountsQuery = jdbc.printIDBCount("IPBB");
				test.log(LogStatus.INFO, "<p>Each IDB Table Counts for : " + deviceName + " </p>" + idbTableCountsQuery);
				break;

			case "Alcatel" : 

				sdbTablesQuery = jdbc.printSDBTables("No", "Alcatel");
				test.log(LogStatus.INFO, "<p>SDB Tables List for : " + deviceName + " </p>" + sdbTablesQuery);
				sdbTablesCountQuery = jdbc.printSDBTables("Yes", "Alcatel");
				test.log(LogStatus.INFO, "<p>Total Num of SDB Tables for : " + deviceName + " </p>" + sdbTablesCountQuery);
				idbTablesQuery = jdbc.printIDBTables("No", "Alcatel");
				test.log(LogStatus.INFO, "<p>IDB Tables List for : " + deviceName + " </p>" + idbTablesQuery);
				idbTablesCountQuery = jdbc.printIDBTables("Yes", "Alcatel");
				test.log(LogStatus.INFO, "<p>Total Num of IDB Tables for : " + deviceName + " </p>" +  idbTablesCountQuery);
				sdbTableCountsQuery = jdbc.printSDBCount("Alcatel");
				test.log(LogStatus.INFO, "<p>Each SDB Table Counts for : " + deviceName + " </p>" +  sdbTableCountsQuery);
				idbTableCountsQuery = jdbc.printIDBCount("Alcatel");
				test.log(LogStatus.INFO, "<p>Each IDB Table Counts for : " + deviceName + " </p>" + idbTableCountsQuery);
				break;	

			case "CMTS" : 

				sdbTablesQuery = jdbc.printSDBTables("No", "CMTS");
				test.log(LogStatus.INFO, "<p>SDB Tables List for : " + deviceName + " </p>" + sdbTablesQuery);
				sdbTablesCountQuery = jdbc.printSDBTables("Yes", "CMTS");
				test.log(LogStatus.INFO, "<p>Total Num of SDB Tables for : " + deviceName + " </p>" + sdbTablesCountQuery);
				idbTablesQuery = jdbc.printIDBTables("No", "CMTS");
				test.log(LogStatus.INFO, "<p>IDB Tables List for : " + deviceName + " </p>" + idbTablesQuery);
				idbTablesCountQuery = jdbc.printIDBTables("Yes", "CMTS");
				test.log(LogStatus.INFO, "<p>Total Num of IDB Tables for : " + deviceName + " </p>" +  idbTablesCountQuery);
				sdbTableCountsQuery = jdbc.printSDBCount("CMTS");
				test.log(LogStatus.INFO, "<p>Each SDB Table Counts for : " + deviceName + " </p>" +  sdbTableCountsQuery);
				idbTableCountsQuery = jdbc.printIDBCount("CMTS");
				test.log(LogStatus.INFO, "<p>Each IDB Table Counts for : " + deviceName + " </p>" + idbTableCountsQuery);
				break;

			case "CMTS-OnDemand" : 

				sdbTablesQuery = jdbc.printSDBTables("No", "CMTS-OnDemand");
				test.log(LogStatus.INFO, "<p>SDB Tables List for : " + deviceName + " </p>" + sdbTablesQuery);
				sdbTablesCountQuery = jdbc.printSDBTables("Yes", "CMTS-OnDemand");
				test.log(LogStatus.INFO, "<p>Total Num of SDB Tables for : " + deviceName + " </p>" + sdbTablesCountQuery);
				idbTablesQuery = jdbc.printIDBTables("No", "CMTS-OnDemand");
				test.log(LogStatus.INFO, "<p>IDB Tables List for : " + deviceName + " </p>" + idbTablesQuery);
				idbTablesCountQuery = jdbc.printIDBTables("Yes", "CMTS-OnDemand");
				test.log(LogStatus.INFO, "<p>Total Num of IDB Tables for : " + deviceName + " </p>" +  idbTablesCountQuery);
				sdbTableCountsQuery = jdbc.printSDBCount("CMTS-OnDemand");
				test.log(LogStatus.INFO, "<p>Each SDB Table Counts for : " + deviceName + " </p>" +  sdbTableCountsQuery);
				idbTableCountsQuery = jdbc.printIDBCount("CMTS-OnDemand");
				test.log(LogStatus.INFO, "<p>Each IDB Table Counts for : " + deviceName + " </p>" + idbTableCountsQuery);
				break;

			case "Spectrum" : 

				sdbTablesQuery = jdbc.printSDBTables("No", "Spectrum");
				test.log(LogStatus.INFO, "<p>SDB Tables List for : " + deviceName + " </p>" + sdbTablesQuery);
				sdbTablesCountQuery = jdbc.printSDBTables("Yes", "Spectrum");
				test.log(LogStatus.INFO, "<p>Total Num of SDB Tables for : " + deviceName + " </p>" + sdbTablesCountQuery);
				idbTablesQuery = jdbc.printIDBTables("No", "Spectrum");
				test.log(LogStatus.INFO, "<p>IDB Tables List for : " + deviceName + " </p>" + idbTablesQuery);
				idbTablesCountQuery = jdbc.printIDBTables("Yes", "Spectrum");
				test.log(LogStatus.INFO, "<p>Total Num of IDB Tables for : " + deviceName + " </p>" +  idbTablesCountQuery);
				sdbTableCountsQuery = jdbc.printSDBCount("Spectrum");
				test.log(LogStatus.INFO, "<p>Each SDB Table Counts for : " + deviceName + " </p>" +  sdbTableCountsQuery);
				idbTableCountsQuery = jdbc.printIDBCount("Spectrum");
				test.log(LogStatus.INFO, "<p>Each IDB Table Counts for : " + deviceName + " </p>" + idbTableCountsQuery);
				break;

			case "ETX" : 

				sdbTablesQuery = jdbc.printSDBTables("No", "ETX");
				test.log(LogStatus.INFO, "<p>SDB Tables List for : " + deviceName + " </p>" + sdbTablesQuery);
				sdbTablesCountQuery = jdbc.printSDBTables("Yes", "ETX");
				test.log(LogStatus.INFO, "<p>Total Num of SDB Tables for : " + deviceName + " </p>" + sdbTablesCountQuery);
				idbTablesQuery = jdbc.printIDBTables("No", "ETX");
				test.log(LogStatus.INFO, "<p>IDB Tables List for : " + deviceName + " </p>" + idbTablesQuery);
				idbTablesCountQuery = jdbc.printIDBTables("Yes", "ETX");
				test.log(LogStatus.INFO, "<p>Total Num of IDB Tables for : " + deviceName + " </p>" +  idbTablesCountQuery);
				sdbTableCountsQuery = jdbc.printSDBCount("ETX");
				test.log(LogStatus.INFO, "<p>Each SDB Table Counts for : " + deviceName + " </p>" +  sdbTableCountsQuery);
				idbTableCountsQuery = jdbc.printIDBCount("ETX");
				test.log(LogStatus.INFO, "<p>Each IDB Table Counts for : " + deviceName + " </p>" + idbTableCountsQuery);
				break;

			case "FDB" : 

				sdbTablesQuery = jdbc.printSDBTables("No", "FDB");
				test.log(LogStatus.INFO, "<p>SDB Tables List for : " + deviceName + " </p>" + sdbTablesQuery);
				sdbTablesCountQuery = jdbc.printSDBTables("Yes", "FDB");
				test.log(LogStatus.INFO, "<p>Total Num of SDB Tables for : " + deviceName + " </p>" + sdbTablesCountQuery);
				idbTablesQuery = jdbc.printIDBTables("No", "FDB");
				test.log(LogStatus.INFO, "<p>IDB Tables List for : " + deviceName + " </p>" + idbTablesQuery);
				idbTablesCountQuery = jdbc.printIDBTables("Yes", "FDB");
				test.log(LogStatus.INFO, "<p>Total Num of IDB Tables for : " + deviceName + " </p>" +  idbTablesCountQuery);
				sdbTableCountsQuery = jdbc.printSDBCount("FDB");
				test.log(LogStatus.INFO, "<p>Each SDB Table Counts for : " + deviceName + " </p>" +  sdbTableCountsQuery);
				idbTableCountsQuery = jdbc.printIDBCount("FDB");
				test.log(LogStatus.INFO, "<p>Each IDB Table Counts for : " + deviceName + " </p>" + idbTableCountsQuery);
				break;

			case "IPV4" : 

				sdbTablesQuery = jdbc.printSDBTables("No", "IPV4");
				test.log(LogStatus.INFO, "<p>SDB Tables List for: " + deviceName + "SDB_IP_RANGE\n" + "SDB_IP_ADDRESS\n" + "SDB_IP_RANGE_OWNER");
				test.log(LogStatus.INFO, "<p>Total Num of SDB Tables for : " + deviceName + ": 3");
				test.log(LogStatus.INFO, "<p>IDB Tables List for: " + deviceName + "IDB_PUBLIC_IP_RANGE\n" + "IDB_PRIVATE_IP_RANGE\n" + "IDB_PUBLIC_IP_ADDRESS\n" + "IDB_PRIVATE_IP_ADDRESS");
				test.log(LogStatus.INFO, "<p>Total Num of IDB Tables for: " + deviceName + ": 4");
				sdbTableCountsQuery = jdbc.printSDBCount("IPV4");
				test.log(LogStatus.INFO, "<p>Each SDB Table Counts for: " + deviceName + sdbTableCountsQuery);
				idbTableCountsQuery = jdbc.printIDBCount("IPV4");
				test.log(LogStatus.INFO, "<p>Each IDB Table Counts for: " + deviceName + idbTableCountsQuery);
				break;

			case "CIN" : 

				sdbTablesQuery = jdbc.printSDBTables("No", "CIN");
				test.log(LogStatus.INFO, "<p>SDB Tables List for : " + deviceName + " </p>" + sdbTablesQuery);
				sdbTablesCountQuery = jdbc.printSDBTables("Yes", "CIN");
				test.log(LogStatus.INFO, "<p>Total Num of SDB Tables for : " + deviceName + " </p>" + sdbTablesCountQuery);
				idbTablesQuery = jdbc.printIDBTables("No", "CIN");
				test.log(LogStatus.INFO, "<p>IDB Tables List for : " + deviceName + " </p>" + idbTablesQuery);
				idbTablesCountQuery = jdbc.printIDBTables("Yes", "CIN");
				test.log(LogStatus.INFO, "<p>Total Num of IDB Tables for : " + deviceName + " </p>" +  idbTablesCountQuery);
				sdbTableCountsQuery = jdbc.printSDBCount("CIN");
				test.log(LogStatus.INFO, "<p>Each SDB Table Counts for : " + deviceName + " </p>" +  sdbTableCountsQuery);
				idbTableCountsQuery = jdbc.printIDBCount("CIN");
				test.log(LogStatus.INFO, "<p>Each IDB Table Counts for : " + deviceName + " </p>" + idbTableCountsQuery);
				break;

			case "CIN-OnDemand" : 

				sdbTablesQuery = jdbc.printSDBTables("No", "CIN");
				test.log(LogStatus.INFO, "<p>SDB Tables List for : " + deviceName + " </p>" + sdbTablesQuery);
				sdbTablesCountQuery = jdbc.printSDBTables("Yes", "CIN");
				test.log(LogStatus.INFO, "<p>Total Num of SDB Tables for : " + deviceName + " </p>" + sdbTablesCountQuery);
				idbTablesQuery = jdbc.printIDBTables("No", "CIN");
				test.log(LogStatus.INFO, "<p>IDB Tables List for : " + deviceName + " </p>" + idbTablesQuery);
				idbTablesCountQuery = jdbc.printIDBTables("Yes", "CIN");
				test.log(LogStatus.INFO, "<p>Total Num of IDB Tables for : " + deviceName + " </p>" +  idbTablesCountQuery);
				sdbTableCountsQuery = jdbc.printSDBCount("CIN");
				test.log(LogStatus.INFO, "<p>Each SDB Table Counts for : " + deviceName + " </p>" +  sdbTableCountsQuery);
				idbTableCountsQuery = jdbc.printIDBCount("CIN");
				test.log(LogStatus.INFO, "<p>Each IDB Table Counts for : " + deviceName + " </p>" + idbTableCountsQuery);
				break;
			default : 

				log.debug("Device Name Passed is Incorrect/Not Found. Please re-check" );
				break;

			}

			//Call the Counts 
			Map<String, String> xlSDBTableCountMap = getStaticTableData(deviceName, "SDB");
			System.out.println("xlSDBTableCountMap : " + xlSDBTableCountMap);
			Map<String, String> xlIDBTableCountMap = getStaticTableData(deviceName, "IDB");
			System.out.println("xlIDBTableCountMap : " + xlIDBTableCountMap);
			Map<String, String> dbSDBTableCountMap = jdbc.getSDBTableCount(deviceName);
			Map<String, String> dbIDBTableCountMap = jdbc.getIDBTableCount(deviceName);

			System.out.println("dbSDBTableCountMap : " + dbSDBTableCountMap);

			System.out.println("dbIDBTableCountMap : " + dbIDBTableCountMap);
			test.log(LogStatus.PASS, "SDB and IDB Tables Data fetched successfully from Database");

			//Comparing SDB Tables & Counts with Expected Data Values 

			if(xlSDBTableCountMap.size() != dbSDBTableCountMap.size()) {
				// Sizes mismatch
				String message = "Number of Tables Fetched from DB and that from Expected Data dint match while verifying SDB Tables for device : " + deviceName;
				log.error(message);
				test.log(LogStatus.FAIL, message);
			} else {
				// Sizes match

				// Checking for all Tables from Excel
				String xlTableCount, dbTableCount;
				boolean isMismatch = false;
				for (String xlTableName : xlSDBTableCountMap.keySet()) {

					// Checking if XL Table Name is present DB Table Names
					if(!dbSDBTableCountMap.containsKey(xlTableName)) {
						// Isn't present
						isMismatch = true;
						System.out.println("if XL Table Name is present DB Table : " + xlTableName);
						String message = "Tables Fetched from DB and that from Expected Data Dint Match while verifying SDB Tables for device : " + deviceName;
						log.error(message);
						test.log(LogStatus.FAIL, message);
						break;
					}

					xlTableCount = xlSDBTableCountMap.get(xlTableName);
					dbTableCount = dbSDBTableCountMap.get(xlTableName);
					if(Double.parseDouble(xlTableCount) != Double.parseDouble(dbTableCount) ){
						// Table count from Excel and DB mismatch
						isMismatch = true;
						System.out.println("Table count from Excel and DB mismatch : " + dbTableCount + " " + xlTableCount);
						String message = "Table Counts Fetched from DB and that from Expected Counts Dint Match while verifying SDB Tables for device : " + deviceName;
						log.error(message);
						test.log(LogStatus.FAIL, message);
						break;
					}
				}

				if(isMismatch) {
					String message = "Data Mismatched with respect to Expected Data for SDB Tables.";
					log.error(message);
				} else {
					//Passed
					test.log(LogStatus.PASS, "All the Tables and Counts Matched while verifying SDB Tables for device : " + deviceName);
				}
			}

			//Comparing IDB Tables & Counts with Expected Data Values 

			if(xlIDBTableCountMap.size() != dbIDBTableCountMap.size()) {
				// Sizes mismatch
				String message = "Number of Tables Fetched from DB and that from Expected Data dint match while verifying IDB Tables for device : " + deviceName;
				log.error(message);
				test.log(LogStatus.FAIL, message);
			} else {
				// Sizes match

				// Checking for all Tables from Excel
				String xlTableCount, dbTableCount;
				boolean isMismatch = false;
				for (String xlTableName : xlIDBTableCountMap.keySet()) {

					// Checking if XL Table Name is present DB Table Names
					if(!dbIDBTableCountMap.containsKey(xlTableName)) {
						// Isn't present
						isMismatch = true;
						System.out.println("if XL Table Name is present DB Table : " + xlTableName);
						String message = "Tables Fetched from DB and that from Expected Data Dint Match while verifying IDB Tables for device : " + deviceName;
						log.error(message);
						test.log(LogStatus.FAIL, message);
						break;
					}

					xlTableCount = xlIDBTableCountMap.get(xlTableName);
					dbTableCount = dbIDBTableCountMap.get(xlTableName);
					if(Double.parseDouble(xlTableCount) != Double.parseDouble(dbTableCount) ){
						// Table count from Excel and DB mismatch
						isMismatch = true;
						System.out.println("Table count from Excel and DB mismatch : " + dbTableCount + " " + xlTableCount);
						String message = "Table Counts Fetched from DB and that from Expected Counts Dint Match while verifying IDB Tables for device : " + deviceName;
						log.error(message);
						test.log(LogStatus.FAIL, message);
						break;
					}
				}

				if(isMismatch) {
					String message = "DB Data Mismatched with respect to Expected Data. Recheck and re-run";
					log.error(message);
					test.log(LogStatus.FAIL, message);
				} else {
					//Passed
					test.log(LogStatus.PASS, "All the Tables and Counts Matched while verifying IDB Tables for device : " + deviceName);
				}
			}

		}catch (Exception e) {
			log.error("Error in validateIDBSDBTableCount :" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error in validating SDB & IDB Table Data with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}

	/*F****************************************************************************
	 *
	 * FUNCTION: validatePuttyPreCondition
	 *
	 * PURPOSE : To Validate Putty configuration is as expected.
	 *           
	 */
	public void validatePuttyPreCondition()
	{
		try
		{
			log.debug("Entering validatePuttyPreCondition : ");
			PuttyConnector puttyConnector = new PuttyConnector("qaapp030cn.netcracker.com", "netcrk", "n3w_netcrk", test);

			String folderPath = "/home/netcrk/QA/";

			boolean folderExists = puttyConnector.checkFolderInBox(folderPath, "No");
			if(folderExists == true )
			{
				log.debug("Path : " + folderPath + " exists in qaapp030cn.netcracker.com");
				test.log(LogStatus.INFO, "Path : " + folderPath + " exists in qaapp030cn.netcracker.com");
			}
			else
			{
				log.debug("Path : " + folderPath + " does not exists in qaapp030cn.netcracker.com");
				test.log(LogStatus.INFO, "Path : " + folderPath + " does not exists in qaapp030cn.netcracker.com");
			}
			puttyConnector.closeConnection();
			test.log(LogStatus.PASS, "SDB and IDB Table Counts fetched successfully");
		}catch (Exception e) {
			log.error("Error in validatePuttyPreCondition :" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error in Putty Validation with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}

	/*F****************************************************************************
	 *
	 * FUNCTION: validatePuttyCurrentDateFolder
	 *
	 * PURPOSE : To verify whether the source folder has today's date.
	 *           
	 */
	public void validatePuttyCurrentDateFolder()
	{
		try
		{
			log.debug("Entering validatePuttyCurrentDateFolder : ");
			PuttyConnector puttyConnector = new PuttyConnector(Utility.getValueFromPropertyFile("putty_srcfolder_login"), Utility.getValueFromPropertyFile("putty_srcfolder_user"), Utility.getValueFromPropertyFile("putty_srcfolder_pwd"), test);
			String folderPath = "/u02/netcracker/toms/u46_d735_6830/tmp";
			test.log(LogStatus.INFO, "Connection Established on server :  devapp735cn.netcracker.com");
			boolean folderExists = puttyConnector.checkFolderInBox(folderPath, "No");
			if(folderExists == true )
			{
				String currentDate = Constants.UNIX_FOLDER_DATE_FORMAT.format(new Date());
				boolean currentDateFolder = puttyConnector.checkFolderInBox(folderPath+"/"+currentDate, "Yes");
				if (currentDateFolder == true )
				{
					log.debug("Current Date Folder " + currentDate + " exists in devapp735cn.netcracker.com");
					test.log(LogStatus.INFO, "Current Date Folder " + currentDate + " exists in devapp735cn.netcracker.com");
				}
			}
			else
			{
				log.debug("Path : " + folderPath + " does not exists in devapp735cn.netcracker.com");
				test.log(LogStatus.INFO, "Path : " + folderPath + " does not exists in devapp735cn.netcracker.com");
			}
			puttyConnector.closeConnection();
			test.log(LogStatus.PASS, "Leaving validatePuttyCurrentDateFolder");
		}catch (Exception e) {
			log.error("Error in validatePuttyCurrentDateFolder :" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error in Validating the Current Date folder with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}


	/*F****************************************************************************
	 *
	 * FUNCTION: validatePuttyPostCondition
	 *
	 * PURPOSE : To verify the zip file creation after DE is successful.
	 *           
	 */
	public void validatePuttyPostCondition(String deviceName, int sessionNum, String spclAttribute)
	{
		try
		{
			log.debug("Entering validatePuttyPostCondition for device : " + deviceName);
			test.log(LogStatus.INFO, "Verifying Putty after DE run for device : " + deviceName);
			PuttyConnector puttyConnector = new PuttyConnector(Utility.getValueFromPropertyFile("putty_zipfile_login"), Utility.getValueFromPropertyFile("putty_zipfile_user"), Utility.getValueFromPropertyFile("putty_zipfile_pwd"), test);
			//String commonFolderPath = "/home/netcrk/QA/";
			String commonFolderPath = Utility.getValueFromPropertyFile("zip_file_path");
			String folderPath = null;
			String zipFolderPath = null;
			String backupFolderPath = null;

			switch (deviceName) {

			case "Alcatel":
				folderPath = commonFolderPath + "NFM-P/";
				zipFolderPath = folderPath+"AlcatelSAM.zip";
				backupFolderPath= folderPath+"Backup";
				break;
			case "IPBB":
				folderPath = commonFolderPath + "IPBB/";
				zipFolderPath = folderPath+"IPBB.zip";
				backupFolderPath= folderPath+"Backup";
				break;
			case "CMTS":
				folderPath = commonFolderPath + "CMTS/";
				zipFolderPath = folderPath+"cmtsfull/CMTS.zip";
				backupFolderPath= folderPath+"cmtsfull/Backup";
				break;

			case "CMTS-OnDemand":
				folderPath = commonFolderPath + "CMTS/cmtsondemand/";
				zipFolderPath = folderPath+ spclAttribute;
				backupFolderPath= zipFolderPath+ "/Backup";
				break;
			case "Spectrum":	
				folderPath = commonFolderPath + "Spectrum/";
				zipFolderPath = folderPath+"Spectrum.zip";
				backupFolderPath= folderPath+"Backup";
				break;

			case "ETX":
				folderPath = commonFolderPath + "ETX/";
				zipFolderPath = folderPath+"ETX.zip";
				backupFolderPath= folderPath+"Backup";
				break;

			case "FDB":
				folderPath = commonFolderPath + "FDB/";
				zipFolderPath = folderPath+"FDB.zip";
				backupFolderPath= folderPath+"Backup";
				break;

			case "IPV4":
				folderPath = commonFolderPath + "IPV4/";
				zipFolderPath = folderPath+"IPV4.zip";
				backupFolderPath= folderPath+"Backup";
				break;

			case "CIN":
				folderPath = commonFolderPath + "CIN/cinfull/";
				zipFolderPath = folderPath+ "CIN.zip";
				backupFolderPath= folderPath+"Backup";
				break;

			case "CIN-OnDemand":
				folderPath = commonFolderPath + "CIN/cinondemand/";
				zipFolderPath = folderPath+spclAttribute;
				backupFolderPath= zipFolderPath+"/Backup";
				break;
			default:
				log.debug("Given Location not found on Page");
			} 

			boolean folderExists = puttyConnector.checkFolderInBox(folderPath, "No");

			if(folderExists == true )
			{
				boolean zipFileExists = false; 
				zipFileExists = puttyConnector.checkFileInBox(zipFolderPath);
				log.debug("Boolean zipFileExists: " + zipFileExists);

				if ( sessionNum == 2)
				{
					System.out.println("In Session 2 with sessionNum : " + sessionNum);
					boolean backupFileExists = false;
					backupFileExists = puttyConnector.checkFileInBox(backupFolderPath);
					log.debug("Boolean backupFileExists: " + backupFileExists);

					if ( backupFileExists ==  false )
					{
						log.debug(backupFolderPath + " does not exists in putty server");
						test.log(LogStatus.FAIL, backupFolderPath + " does not exists in putty server");
						Assert.assertTrue(false,  backupFolderPath + " does not exists in putty server");
					}

				}

				if ( zipFileExists ==  false )
				{
					log.debug(zipFolderPath + " is not generated. Please check.");
					test.log(LogStatus.FAIL, zipFolderPath + " is not generated. Please check.");
					Assert.assertTrue(false,  zipFolderPath + " is not generated. Please check.");
				}

			}
			else
			{
				log.debug("Path : " + folderPath + " does not exists in putty server");
				test.log(LogStatus.INFO, "Path : " + folderPath + " does not exists in putty server");
			}

			puttyConnector.closeConnection();
			test.log(LogStatus.PASS, "Leaving validatePuttyPostCondition");
		}catch (Exception e) {
			log.error("Error in validatePuttyPostCondition :" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error in validating putty with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}

	/*F****************************************************************************
	 *
	 * FUNCTION: runDeleteScript
	 *
	 * PURPOSE : To Execute the Delete Script before starting DnR flow.
	 *           
	 */
	public void runDeleteScript(String deviceName)
	{
		try
		{
			log.debug("Entering runDeleteScript with deviceName: " + deviceName);
			test.log(LogStatus.INFO, "Running Delete Script for : " + deviceName);

			switch ( deviceName )
			{
			case "FDB" :

				jdbc.executeDeleteScriptFDB();

			case "IPV4" :

				jdbc.executeDeleteScriptIPV4();

			case "IPBB" :
			case "Alcatel" :
			case "ETX" :
			case "CIN" :
			case "CMTS" :
			case "CMTS-OnDemand" :
			case "Spectrum" :

				jdbc.executeDeleteScript();
			}

			test.log(LogStatus.PASS, "Delete Script Ran Successfully for : " + deviceName);
			log.debug("Delete Script Ran Successfully for : " + deviceName);
		}catch (Exception e) {
			log.error("Error in runDeleteScript :" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error in running DeleteScript");
			Assert.assertTrue(false, e.getMessage());
		}
	}

	/*F****************************************************************************
	 *
	 * FUNCTION: navigateToIntegrationInterface
	 *
	 * PURPOSE : Navigates to Integration Interface and particular Device Integration folder.
	 *           
	 */
	public void navigateToIntegrationInterface(String location, String fetchLoc, String storageLoc, String ftpLoc)
	{
		try {
			log.debug("Entering navigateToIntegrationInterface : " + location );
			wait(1);
			navigate(Utility.getValueFromPropertyFile("documents_url"));
			wait(1);
			test.log(LogStatus.INFO, "Navigation to Documents Path: " + test.addScreenCapture(addScreenshot()));
			click(INTEGRATION_INTERFACE);
			wait(1);
			test.log(LogStatus.INFO, "Navigation to Integration Interface Path: " + test.addScreenCapture(addScreenshot()));

			switch (location) {
			case "Alcatel":
				click(ALCATEL_INTERFACE);
				checkConfig("Alcatel", fetchLoc, storageLoc, null);
				wait(2);
				break;
			case "IPBB":
				click(CISCO_INTERFACE);
				test.log(LogStatus.INFO, "Navigation to CISCO IP BackBone Path: " + test.addScreenCapture(addScreenshot()));
				checkConfig("IPBB", fetchLoc, storageLoc, ftpLoc);
				wait(2);
				break;
			case "CMTS":
				click(CMTS_INTERFACE);
				checkConfig("CMTS", fetchLoc, storageLoc, null);
				wait(2);
				break;

			case "CMTS-OnDemand":
				click(CMTS_INTERFACE);
				checkConfig("CMTS", fetchLoc, storageLoc, null);
				wait(2);
				break;
			case "Spectrum":

				click(SPECTRUM_INTERFACE);
				checkConfig("Spectrum", fetchLoc, storageLoc, null);
				wait(2);
				break;

			case "ETX":
				click(ETX_INTERFACE);
				checkConfig("ETX", fetchLoc, storageLoc, null);
				wait(2);
				break;

			case "FDB":
				click(FDB_INTERFACE);
				checkConfig("FDB", fetchLoc, storageLoc, null);
				wait(2);
				break;

			case "CIN":
				click(CIN_INTERFACE);
				checkConfig("CIN", fetchLoc, storageLoc, null);
				wait(2);
				break;

			case "CIN-OnDemand":
				click(CIN_INTERFACE);
				checkConfig("CIN-OnDemand", fetchLoc, storageLoc, null);
				wait(2);
				break;

			case "IPV4":
				click(FDB_INTERFACE);
				checkConfig("IPV4", fetchLoc, storageLoc, null);
				wait(2);
				break;
			default:
				log.debug("Given Location not found on Page");
			}
			wait(3);
			test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			log.debug("Leaving selectLocation : " + location);
		} catch (Exception e) {
			log.error("Error in selectLocation :" + location + " " + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error in selecting the location needed with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}


	/*F****************************************************************************
	 *
	 * FUNCTION: checkConfig
	 *
	 * PURPOSE :  Generic method to validates the configuration details of All Devices.
	 *           
	 */
	public void checkConfig(String deviceName, String fetchLoc, String storageLoc, String ftpLoc) {
		try {
			log.debug("Entering checkConfigCmts with deviceName : " + deviceName);
			wait(1);
			click(PARAMETERS_TAB); 
			wait(1);
			click(EDIT);
			wait(1);
			test.log(LogStatus.INFO, "Initial Values of Configuration Details: " + test.addScreenCapture(addScreenshot()));

			switch (deviceName) {

			case "Alcatel" :
				if ( fetchLoc != null)
					inputText(FETCH_LOCATION_ALCATEL,fetchLoc);
				wait(1);
				if ( storageLoc != null)
					inputText(STORAGE_LOCATION_ALCATEL,storageLoc);
				wait(1);
				inputText(STORAGE_USER_ALCATEL,Utility.getValueFromPropertyFile("integ_user"));
				wait(1);
				inputText(FETCH_USER_ALCATEL,Utility.getValueFromPropertyFile("integ_user"));
				wait(1);
				inputText(STORAGE_PASS_ALCATEL,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(STORAGE_PASS2_ALCATEL,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(FETCH_PASS_ALCATEL,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(FETCH_PASS2_ALCATEL,Utility.getValueFromPropertyFile("integ_pwd"));
				break;

			case "IPBB":
				if ( fetchLoc != null)
					inputText(FETCH_LOCATION_IPBB, fetchLoc);
				wait(1);
				if ( storageLoc != null)
					inputText(STORAGE_LOCATION_IPBB, storageLoc);
				wait(1);
				if ( ftpLoc != null)
					inputText(FTP_LOCATION_IPBB, ftpLoc); 
				wait(1);
				inputText(STORAGE_USER_IPBB,Utility.getValueFromPropertyFile("integ_user"));
				wait(1);
				inputText(FETCH_USER_IPBB,Utility.getValueFromPropertyFile("integ_user"));
				wait(1);
				inputText(STORAGE_PASS_IPBB,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(STORAGE_PASS2_IPBB,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(FETCH_PASS_IPBB,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(FETCH_PASS2_IPBB,Utility.getValueFromPropertyFile("integ_pwd"));
				break;


			case "CMTS":
				if ( fetchLoc != null)
					inputText(FETCH_LOCATION_CMTS,fetchLoc);
				wait(1);
				if ( storageLoc != null)
					inputText(STORAGE_LOCATION_CMTS,storageLoc);
				wait(1);
				inputText(STORAGE_USER_CMTS,Utility.getValueFromPropertyFile("integ_user"));
				wait(1);
				inputText(FETCH_USER_CMTS,Utility.getValueFromPropertyFile("integ_user"));
				wait(1);
				inputText(STORAGE_PASS_CMTS,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(STORAGE_PASS2_CMTS,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(FETCH_PASS_CMTS,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(FETCH_PASS2_CMTS,Utility.getValueFromPropertyFile("integ_pwd"));
				break;

			case "CMTS-OnDemand":
				if ( fetchLoc != null)
					inputText(FETCH_LOCATION_CMTS, fetchLoc);
				wait(1);
				if ( storageLoc != null)
					inputText(STORAGE_LOCATION_CMTS,storageLoc);
				wait(1);
				inputText(STORAGE_USER_CMTS,Utility.getValueFromPropertyFile("integ_user"));
				wait(1);
				inputText(FETCH_USER_CMTS,Utility.getValueFromPropertyFile("integ_user"));
				wait(1);
				inputText(STORAGE_PASS_CMTS,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(STORAGE_PASS2_CMTS,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(FETCH_PASS_CMTS,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(FETCH_PASS2_CMTS,Utility.getValueFromPropertyFile("integ_pwd"));
				break;
			case "Spectrum":
				if ( fetchLoc != null)
					inputText(FETCH_LOCATION_SPEC,fetchLoc);
				wait(1);
				if ( storageLoc != null)
					inputText(STORAGE_LOCATION_SPEC,storageLoc);
				wait(1);
				inputText(STORAGE_USER_SPEC,Utility.getValueFromPropertyFile("integ_user"));
				wait(1);
				inputText(FETCH_USER_SPEC,Utility.getValueFromPropertyFile("integ_user"));
				wait(1);
				inputText(STORAGE_PASS_SPEC,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(FETCH_PASS_SPEC,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(STORAGE_PASS2_SPEC,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(FETCH_PASS2_SPEC,Utility.getValueFromPropertyFile("integ_pwd"));
				break;

			case "ETX":
				if ( fetchLoc != null)
					inputText(FETCH_LOCATION_ETX,fetchLoc);
				wait(1);
				if ( storageLoc != null)
					inputText(STORAGE_LOCATION_ETX,storageLoc);
				wait(1);
				inputText(STORAGE_USER_ETX,Utility.getValueFromPropertyFile("integ_user"));
				wait(1);
				inputText(FETCH_USER_ETX,Utility.getValueFromPropertyFile("integ_user"));
				wait(1);
				inputText(STORAGE_PASS_ETX,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(STORAGE_PASS2_ETX,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(FETCH_PASS_ETX,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(FETCH_PASS2_ETX,Utility.getValueFromPropertyFile("integ_pwd"));
				break;

			case "FDB":
				if ( fetchLoc != null)
					inputText(FETCH_LOCATION_FDB,fetchLoc);
				wait(1);
				if ( storageLoc != null)
					inputText(STORAGE_LOCATION_FDB,storageLoc);
				wait(1);
				inputText(STORAGE_USER_FDB,Utility.getValueFromPropertyFile("integ_user"));
				wait(1);
				inputText(FETCH_USER_FDB,Utility.getValueFromPropertyFile("integ_user"));
				wait(1);
				inputText(STORAGE_PASS_FDB,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(STORAGE_PASS2_FDB,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(FETCH_PASS_FDB,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(FETCH_PASS2_FDB,Utility.getValueFromPropertyFile("integ_pwd"));
				break;

			case "IPV4":
				if ( fetchLoc != null)
					inputText(FETCH_LOCATION_FDB,fetchLoc);
				wait(1);
				if ( storageLoc != null)
					inputText(STORAGE_LOCATION_FDB,storageLoc);
				wait(1);
				inputText(STORAGE_USER_FDB,Utility.getValueFromPropertyFile("integ_user"));
				wait(1);
				inputText(FETCH_USER_FDB,Utility.getValueFromPropertyFile("integ_user"));
				wait(1);
				inputText(STORAGE_PASS_FDB,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(STORAGE_PASS2_FDB,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(FETCH_PASS_FDB,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(FETCH_PASS2_FDB,Utility.getValueFromPropertyFile("integ_pwd"));
				break;

			case "CIN":
				if ( fetchLoc != null)	
					inputText(FETCH_LOCATION_CIN, fetchLoc);
				wait(1);
				if ( storageLoc != null)
					inputText(STORAGE_LOCATION_CIN,storageLoc);
				wait(1);
				inputText(STORAGE_USER_CIN,Utility.getValueFromPropertyFile("integ_user"));
				wait(1);
				inputText(FETCH_USER_CIN,Utility.getValueFromPropertyFile("integ_user"));
				wait(1);
				inputText(STORAGE_PASS_CIN,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(STORAGE_PASS2_CIN,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(FETCH_PASS_CIN,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(FETCH_PASS2_CIN,Utility.getValueFromPropertyFile("integ_pwd"));
				break;

			case "CIN-OnDemand":
				if ( fetchLoc != null)	
					inputText(FETCH_LOCATION_CIN, fetchLoc);
				wait(1);
				if ( storageLoc != null)
					inputText(STORAGE_LOCATION_CIN,storageLoc);
				wait(1);
				inputText(STORAGE_USER_CIN,Utility.getValueFromPropertyFile("integ_user"));
				wait(1);
				inputText(FETCH_USER_CIN,Utility.getValueFromPropertyFile("integ_user"));
				wait(1);
				inputText(STORAGE_PASS_CIN,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(STORAGE_PASS2_CIN,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(FETCH_PASS_CIN,Utility.getValueFromPropertyFile("integ_pwd"));
				wait(1);
				inputText(FETCH_PASS2_CIN,Utility.getValueFromPropertyFile("integ_pwd"));
				break;					
			default:
				log.debug("Given Location not found on Page");
			}

			wait(1);
			test.log(LogStatus.INFO, "Final Values of Configuration Details: " + test.addScreenCapture(addScreenshot()));
			click(UPDATE);
			wait(2);
			test.log(LogStatus.PASS, "Configuration Setup done Successfully for device : " + deviceName);
			log.debug("Leaving checkConfig of device : " + deviceName);
		} catch (Exception e) {
			log.error("Error in checkConfig of device " + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error in checking configuration parameters with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}

	/*F****************************************************************************
	 *
	 * FUNCTION: verifyFallOuts
	 *
	 * PURPOSE :  Verify the Fallouts occured during the sessions. 
	 * 
	 */
	public String verifyFallOuts(String deviceName) {
		String sessionId = null;
		try {
			log.debug("Entering verifyFallOuts");
			wait(1);
			//landing.navigatetoDocuments();
			navigate(Utility.getValueFromPropertyFile("documents_url"));
			wait(1);
			click(DATA_TRANSITION_PATH);
			wait(1);
			test.log(LogStatus.INFO, "Navigation to Data Transition path: " + test.addScreenCapture(addScreenshot()));
			click(CONFIGURATION_TAB);
			test.log(LogStatus.INFO, "Navigation to Configuration Tab of Data Transition: " + test.addScreenCapture(addScreenshot()));
			wait(1);
			switch (deviceName) {
			case "Alcatel":
				click(ALCATEL_CONFIG);
				wait(2);
				click(IDB_TABLE);
				test.log(LogStatus.INFO, "Navigation to Alcatel Config Folder: " + test.addScreenCapture(addScreenshot()));
				wait(2);
				break;
			case "IPBB":
				click(CISCO_CONFIG);
				wait(2);
				click(IDB_IBBB_TABLE);
				wait(2);
				test.log(LogStatus.INFO, "Navigation to CiscoIP BB Config Folder: " + test.addScreenCapture(addScreenshot()));
				break;
			case "CMTS":
				click(CMTS_CONFIG);
				wait(2);
				click(IDB_TABLE);
				test.log(LogStatus.INFO, "Navigation to CMTS Config Folder: " + test.addScreenCapture(addScreenshot()));
				wait(2);
				break;
			case "CMTS-OnDemand":
				click(CMTS_ON_DEMAND_CONFIG);
				wait(2);
				click(IDB_TABLE);
				test.log(LogStatus.INFO, "Navigation to CMTS On-Demand Config Folder: " + test.addScreenCapture(addScreenshot()));
				wait(2);
				break;
			case "Spectrum":
				click(SPECTRUM_CONFIG);
				wait(2);
				click(IDB_TABLE);
				test.log(LogStatus.INFO, "Navigation to Spectrum Config Folder: " + test.addScreenCapture(addScreenshot()));
				wait(2);
				break;										
			case "ETX":
				click(ETX_CONFIG);
				wait(2);
				click(IDB_TABLE);
				test.log(LogStatus.INFO, "Navigation to Spectrum Config Folder: " + test.addScreenCapture(addScreenshot()));
				wait(2);
				break;
			case "FDB":						
				click(FDB_CONFIG);
				wait(1);
				click(FDB_INTEG);
				wait(2);
				click(IDB_TABLE);
				test.log(LogStatus.INFO, "Navigation to FDB Config Folder: " + test.addScreenCapture(addScreenshot()));
				wait(2);
				break;
			case "IPV4":
				click(IPV4_CONFIG);
				wait(2);
				click(IDB_TABLE);
				test.log(LogStatus.INFO, "Navigation to IPV4 Config Folder: " + test.addScreenCapture(addScreenshot()));
				wait(2);
				break;
			case "CIN" : 
			case "CIN-OnDemand" : 
				click(CIN_CONFIG);
				wait(2);
				click(IDB_TABLE);
				test.log(LogStatus.INFO, "Navigation to CIN Config Folder: " + test.addScreenCapture(addScreenshot()));
				wait(2);
				break;



			default:
				log.debug("Given Location not found on Page");
			}
			wait(3);
			click(VALIDATION_SESSION_TAB);
			wait(1);
			if ( isElementPresent(VALIDATE_SESSION_ID))
			{
				sessionId = getText(INTEG_SESSION_ID);
				System.out.println("SessionID = " + sessionId);
				click(VALIDATE_SESSION_ID);
			}
			else
			{
				log.error("Error while verifying Fallouts  as Latest Validate Session was not found" );
				test.log(LogStatus.ERROR, "Error while verifying Fallouts  as Latest Validate Session was not found");
				test.log(LogStatus.FAIL, "Error while verifying Fallouts  as Latest Validate Session was not found " + test.addScreenCapture(addScreenshot()));
				Assert.assertTrue(false, "Error while verifying Fallouts  as Latest Validate Session was not found.");
			}
			wait(1);
			click(PARAMETERS_TAB);
			test.log(LogStatus.INFO, "Validation Session Tab of IDB Table Set : " + test.addScreenCapture(addScreenshot()));
			String fallOutTable = getText(FALLOUT_TABLE_CONTENT);
			System.out.println("falloutTable = " + fallOutTable);

			String fallOuts = jdbc.printFallOuts(fallOutTable);
			test.log(LogStatus.INFO, "<p> FallOut Tables and Counts Data from DB: </p>" + fallOuts);

			Map<String, String> xlTableCountMap = getStaticTableData(deviceName, "FallOut");
			System.out.println("tableCountMap : " + xlTableCountMap);
			Map<String, String> dbTableCountMap = jdbc.getFallOutCounts(fallOutTable);

			// Checking for sizes
			if(xlTableCountMap.size() != dbTableCountMap.size()) {
				// Sizes mismatch
				String message = "Number of Tables Fetched from DB and that from Expected Data dint match while verifying FallOuts for device : " + deviceName;
				log.error(message);
				test.log(LogStatus.ERROR, message);
				test.log(LogStatus.FAIL, "Failed in verifying FallOuts for " + deviceName);
			} else {
				// Sizes match

				// Checking for all Tables from Excel
				String xlTableCount, dbTableCount;
				boolean isMismatch = false;
				for (String xlTableName : xlTableCountMap.keySet()) {

					// Checking if XL Table Name is present DB Table Names
					if(!dbTableCountMap.containsKey(xlTableName)) {
						// Isn't present
						isMismatch = true;
						System.out.println("if XL Table Name is present DB Table : " + xlTableName);
						String message = "Tables Fetched from DB and that from Expected Data Dint Match while verifying FallOuts for device : " + deviceName;
						log.error(message);
						test.log(LogStatus.ERROR, message);
						test.log(LogStatus.FAIL, "Failed in verifying FallOuts for " + deviceName);
						break;
					}

					xlTableCount = xlTableCountMap.get(xlTableName);
					dbTableCount = dbTableCountMap.get(xlTableName);
					if(Double.parseDouble(xlTableCount) != Double.parseDouble(dbTableCount) ){
						// Table count from Excel and DB mismatch
						isMismatch = true;
						System.out.println("Table count from Excel and DB mismatch : " + dbTableCount + " " + xlTableCount);
						String message = "Table Counts Fetched from DB and that from Expected Counts Dint Match while verifying FallOuts for device : " + deviceName;
						log.error(message);
						test.log(LogStatus.ERROR, message);
						test.log(LogStatus.FAIL, "Failed in verifying FallOuts for " + deviceName);
						break;
					}
				}

				if(isMismatch) {
					String message = "Data Mismatched with Expeceted Data while verifying FallOuts.";
					log.error(message);
					test.log(LogStatus.ERROR, message);
				} else {
					//Passed
					test.log(LogStatus.PASS, "Tables and Counts Matched while verifying Fallouts for device : " + deviceName);
				}

			}

			wait(1);
			log.debug("Leaving verifyFallOuts");
		} catch (Exception e) {
			log.error("Error in verifyFallOuts:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error in verifying Fallouts with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
		return sessionId;
	}

	/*F*****************************************************************************************************
	 *
	 * FUNCTION: getStaticTableData
	 *
	 * PURPOSE : To get the Table/FallOut Counts for all the Integrations from Expected Table Counts Sheet
	 * 
	 */

	public  Map<String, String> getStaticTableData(String deviceName, String tableType) throws SQLException, ClassNotFoundException {
		ExcelOperation xls = new ExcelOperation(Constants.TestDataSuite_XLS);
		String sheetName = Constants.SHEET_EXPECTED_TABLE_COUNTS, xlDeviceName;
		String tableName, tableCount;

		int rowCount = 2;
		Map<String, String> tableMap = new HashMap<>();
		while (!(xlDeviceName = xls.getCellData(sheetName, 0, rowCount)).equals("") )  {
			if(xlDeviceName.equalsIgnoreCase(deviceName) && xls.getCellData(sheetName, 1, rowCount).equals(tableType) ) {
				tableName = xls.getCellData(sheetName, 2, rowCount);
				tableCount = xls.getCellData(sheetName, 3, rowCount);
				tableMap.put(tableName, tableCount);
			}

			rowCount++;
		}
		System.out.println("Table Map  Size: " + tableMap.size());
		return tableMap;
	}


	/*F****************************************************************************
	 *
	 * FUNCTION: navigatetoUpdate
	 *
	 * PURPOSE : To Update any parameters of a particular device. 
	 * 
	 */
	public void navigatetoUpdate(String objectID, String deviceName, String ipAddress) {
		try {
			log.debug("Entering navigatetoUpdate with Object id : " + objectID + " for device : " + deviceName);
			String url = "http://devapp735cn.netcracker.com:6830/ncobject.jsp?id=" + objectID;
			navigate(url);
			wait(1);
			click(PARAMETERS_TAB);
			wait(1);
			test.log(LogStatus.INFO, "Parameters before updation: " + test.addScreenCapture(addScreenshot()));
			click(EDIT);
			wait(1);
			switch(deviceName) {

			case "IPV4" : 
				String ipStatus = jdbc.getObjAndStatus("Status", ipAddress);
				System.out.println("ipStatus : " + ipStatus);
				switch(ipStatus){
				case "Available" : 
					selectFromList(IP_STATUS_SELECT, "Assigned");
					break;
				case "Assigned" :
					selectFromList(IP_STATUS_SELECT, "Reserved");
					break;
				case "Blocked" :	
					selectFromList(IP_STATUS_SELECT, "Available");
					break;
				case "Reserved" :
					selectFromList(IP_STATUS_SELECT, "Available");
					break;
				case "Removed" :
					selectFromList(IP_STATUS_SELECT, "Available");
					break;
				default :
					selectFromList(IP_STATUS_SELECT, "Available");
					break;
				}
				break;
			case "ETX": 
				inputText(SOFTWARE_VERSION_INPUT, "ETX-101Test AT: 1.0");
				break;
			case "Alcatel":
				inputText(DEV_NAME_INPUT, "Test-Auto-CG-81");
				break;
			case "IPBB":
			case "CMTS":
			case "CMTS-OnDemand":
			case "Spectrum":
			case "FDB":
			case "CIN":
				inputText(SERIAL_NUM_INPUT, "Test-12D-345");
				break;
			}

			wait(1);
			test.log(LogStatus.INFO, "Updated Parameter for the device: " + test.addScreenCapture(addScreenshot()));
			click(UPDATE);
			log.debug("Leaving navigatetoUpdate");
		} catch (Exception e) {
			log.error("Error in navigatetoUpdate:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error in Updating the parameters with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}

	/*F*****************************************************************************************************
	 *
	 * FUNCTION: verifyActionsforUpdate
	 *
	 * PURPOSE : To verify the actions returned for the particular object. 
	 * 
	 */

	public int verifyActionsforUpdate(String deviceName, String objectId) {

		int dbActionCount = 0;
		String dbActionCountStr = null;
		try {
			log.debug("Entering verifyActionsforUpdate with Object id : " + objectId + " for device : " + deviceName);
			test.log(LogStatus.INFO, "Verifying Actions for object id : " + objectId + " for device : " + deviceName);

			dbActionCountStr = jdbc.verifyObjectActions("Yes", deviceName, objectId);

			dbActionCount = Integer.parseInt(dbActionCountStr);

			processActionByActionCount(dbActionCount);

			log.debug("Leaving verifyActionsforUpdate");
		} catch (Exception e) {
			log.error("Error in verifyActionsforUpdate:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error while verifying actions ");
			Assert.assertTrue(false, e.getMessage());
		}
		return  dbActionCount;

	}

	/*F*****************************************************************************************************
	 *
	 * FUNCTION: verifyActionsBeforeDelete
	 *
	 * PURPOSE : To verify the actions returned for the particular object before Deletion is performed. 
	 * 
	 */

	public String verifyActionsBeforeDelete(String deviceName, String objectId) {

		int dbActionCount = 0;
		String dbActionCountStr = null , dbObjectName = null;
		try {
			log.debug("Entering verifyActionsBeforeDelete with Object id : " + objectId + " for device : " + deviceName);
			test.log(LogStatus.INFO, "Verifying Actions for object id : " + objectId + " for device : " + deviceName);

			dbObjectName = jdbc.verifyObjectActions("No", deviceName, objectId);
			test.log(LogStatus.INFO, "Object Name fetched from DB  : " + dbObjectName );
			System.out.println( "dbObjectName : " + dbObjectName);
			dbActionCountStr = jdbc.verifyObjectActions("Yes", deviceName, objectId);

			dbActionCount = Integer.parseInt(dbActionCountStr);

			processActionByActionCount(dbActionCount);

			log.debug("Leaving verifyActionsBeforeDelete");
		} catch (Exception e) {
			log.error("Error in verifyActionsBeforeDelete:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error while verifying actions before Deletion ");
			Assert.assertTrue(false, e.getMessage());
		}
		return  dbObjectName;

	}

	/*F*****************************************************************************************************
	 *
	 * FUNCTION: performDeletion
	 *
	 * PURPOSE : To perform Deletion operation on the object id that is passed. 
	 * 
	 */

	public void performDeletion(String deviceName, String objectId) {
		try {
			log.debug("Entering performDeletion for " + deviceName + " with Object id : " + objectId);
			test.log(LogStatus.INFO, "To Check Deletion of object id : " + objectId + " for device : " + deviceName);
			// before deletion num of rows 
			int initialNumOfRows = jdbc.verifyObjectData(objectId);

			test.log(LogStatus.INFO, "Num of rows returned for the object in nc_objects table before deletion : " + initialNumOfRows);

			if ( initialNumOfRows == 0 )
			{
				test.log(LogStatus.INFO, "Num of rows returned for the object in nc_objects table before deletion : " + initialNumOfRows + " hence, No Rows found for deletion of object, Please recheck and pass a valid object");
				test.log(LogStatus.FAIL, "Error while deleteObject : Object not found in DB for deletion");
				Assert.assertTrue(false, "Error while deleteObject : Object not found in DB");
			}
			// deletion 
			jdbc.executeDeleteScriptOnObject(objectId);

			int finalNumOfRows = jdbc.verifyObjectData(objectId);

			test.log(LogStatus.INFO, "Num of rows found for the object in nc_objects table after deletion : " + finalNumOfRows);

			log.debug("Leaving performDeletion");
		} catch (Exception e) {
			log.error("Error in performDeletion:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error while deleteObject ");
			Assert.assertTrue(false, e.getMessage());
		}
	}

	/*F*****************************************************************************************************
	 *
	 * FUNCTION: verifyActionsAfterDelete
	 *
	 * PURPOSE : To verify the actions returned for the particular object after Deletion is performed. 
	 * 
	 */

	public int verifyActionsAfterDelete(String deviceName, String objectName, String tableName) {

		int dbActionCount = 0;
		try {
			log.debug("Entering verifyActionsAfterDelete with Object : " + objectName + " for device : " + deviceName);
			test.log(LogStatus.INFO, "Verifying Actions After Delete for object : " + objectName + " for device : " + deviceName);

			dbActionCount = jdbc.getActionsFromObjName(tableName, objectName, deviceName);

			processActionByActionCount(dbActionCount);

			test.log(LogStatus.INFO, "Action Count Fetched from DB: " + dbActionCount);

			log.debug("Leaving verifyActionsAfterDelete");
		} catch (Exception e) {
			log.error("Error in verifyActionsAfterDelete:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error while verifying actions before Deletion ");
			Assert.assertTrue(false, e.getMessage());
		}
		return  dbActionCount;

	}

	/*F*****************************************************************************************************
	 *
	 * FUNCTION: verifyActionsWithTableforUpdate
	 *
	 * PURPOSE : To verify the actions returned for the particular object for Update Case( Session Based Cases) . 
	 * 
	 */

	public int verifyActionsWithTableforUpdate(String deviceName, String objectId, String sessionId) {

		int dbActionCount = 0;
		try {


			log.debug("Entering verifyActionsWithTable with Object id : " + objectId + " and session Id : " + sessionId + " for device : " + deviceName);

			test.log(LogStatus.INFO, "Object id : " + objectId + " session Id : " + sessionId + " for device : " + deviceName);

			String tableName = jdbc.getTableName(sessionId, deviceName);

			if ( tableName == null )
			{
				test.log(LogStatus.FAIL, "No Rows fetched from DB for getting the table name that is required to get the actions.");
				Assert.assertTrue(false, "No Rows fetched from DB for getting the table name that is required to get the actions.");
			}
			test.log(LogStatus.INFO, "Table Fetched from DB: " + tableName);

			dbActionCount = jdbc.getActionsFromTable(objectId, tableName);

			processActionByActionCount(dbActionCount);

			log.debug("Leaving verifyActionsWithTable");
		} catch (Exception e) {
			log.error("Error in verifyActionsWithTable:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error while verifying actions ");
			Assert.assertTrue(false, e.getMessage());
		}
		return dbActionCount;
	}

	/*F*****************************************************************************************************
	 *
	 * FUNCTION: validateActions
	 *
	 * PURPOSE : To validate whether proper action is being derived post Update/Delete. 
	 * 
	 */

	public void validateActions(String forAction, int actionCount) {
		try {
			log.debug("Entering validateActions with action Count : " + actionCount);

			if ( forAction.equalsIgnoreCase("Update") && ( actionCount == 2 ) ) 
			{
				test.log(LogStatus.PASS, "Expected Action returned after Recon run.");
				Assert.assertTrue(true, "Updation Done Sucessfully for the object.");
			}
			else if ( forAction.equalsIgnoreCase("Delete") && ( actionCount == 3 ) ) 
			{
				test.log(LogStatus.PASS, "Expected Action returned after Recon run.");
				Assert.assertTrue(true, "Updation Done Sucessfully for the object.");
			}else
			{
				test.log(LogStatus.FAIL, "Expected Action dint not return after Recon run.");
				Assert.assertTrue(false, "Expected Action dint not return after Recon run.");
			}

			log.debug("Leaving validateActions");
		} catch (Exception e) {
			log.error("Error in validateActions:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error while validating actions ");
			Assert.assertTrue(false, e.getMessage());
		}
	}

	/*F*****************************************************************************************************
	 *
	 * FUNCTION: verifyActionsWithTableBeforeDelete
	 *
	 * PURPOSE : To verify the actions returned for the particular object for before Delete( Session Based Cases) . 
	 * 
	 */

	public String verifyActionsWithTableBeforeDelete(String deviceName, String objectId, String sessionId) {
		int dbActionCount = 0;
		String objectName = null;
		try {

			log.debug("Entering verifyActionsWithTableBeforeDelete with Object id : " + objectId + " and session Id : " + sessionId + " for device : " + deviceName);

			test.log(LogStatus.INFO, "Object id : " + objectId + " session Id : " + sessionId + " for device : " + deviceName);

			String tableName = jdbc.getTableName(sessionId, deviceName);

			if ( tableName == null )
			{
				test.log(LogStatus.FAIL, "No Rows fetched from DB for getting the table name that is required to get the actions.");
				Assert.assertTrue(false, "No Rows fetched from DB for getting the table name that is required to get the actions.");
			}
			test.log(LogStatus.INFO, "Table Fetched from DB: " + tableName);

			objectName = jdbc.getObjNameFromTable(objectId, tableName);
			test.log(LogStatus.INFO, "Object Name Fetched from DB: " + objectName);
			dbActionCount = jdbc.getActionsFromObjName(tableName, objectName, deviceName);

			processActionByActionCount(dbActionCount);


			log.debug("Leaving verifyActionsWithTableBeforeDelete");
		} catch (Exception e) {
			log.error("Error in verifyActionsWithTableBeforeDelete:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error while verifying actions for Delete");
			Assert.assertTrue(false, e.getMessage());
		}
		return objectName;
	}


	/*F*****************************************************************************************************
	 *
	 * FUNCTION: verifyActionsWithTableBeforeDelete
	 *
	 * PURPOSE : To verify the actions returned for the particular object for after Delete( Session Based Cases) . 
	 * 
	 */

	public int verifyActionsWithTableAfterDelete(String deviceName, String objectName, String sessionId) {

		int dbActionCount = -1;
		try {

			log.debug("Entering verifyActionsWithTableAfterDelete with Object : " + objectName +  " for device : " + deviceName);

			test.log(LogStatus.INFO, "To get the action for Object : " + objectName + " for device : " + deviceName);

			String tableName = jdbc.getTableName(sessionId, deviceName);

			if ( tableName == null )
			{
				test.log(LogStatus.FAIL, "No Rows fetched from DB for getting the table name that is required to get the actions.");
				Assert.assertTrue(false, "No Rows fetched from DB for getting the table name that is required to get the actions.");
			}

			dbActionCount = jdbc.getActionsFromObjName(tableName, objectName, deviceName);

			processActionByActionCount(dbActionCount);

			log.debug("Leaving verifyActionsWithTableAfterDelete");
		} catch (Exception e) {
			log.error("Error in verifyActionsWithTableAfterDelete:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error while verifying actions for post Deletion");
			Assert.assertTrue(false, e.getMessage());
		}

		return dbActionCount;

	}

	/*F*****************************************************************************************************
	 *
	 * FUNCTION: verifyActionsWithTableBeforeDelete
	 *
	 * PURPOSE : To process and print the action name based on its count derived from DB for reporting purpose.  
	 * 
	 */

	public void processActionByActionCount(int dbActionCount) {
		try {

			log.debug("Entering processActionByActionCount with dbActionCount : " + dbActionCount);

			if ( dbActionCount == -1 )
			{
				test.log(LogStatus.FAIL, "No rows found for the particular object, Please verify and send valid object ID");
				Assert.assertTrue(false, "No rows found for the particular object, Please verify and send valid object ID");
			}

			switch(dbActionCount)
			{
			case 1 : 
				test.log(LogStatus.INFO, "Action Fetched from DB: No Action");
				break;
			case 2 : 
				test.log(LogStatus.INFO, "Action Fetched from DB: Update");
				break;
			case 3 :
				test.log(LogStatus.INFO, "Action Fetched from DB: Insert");
				break;
			case 4 : 
				test.log(LogStatus.INFO, "Action Fetched from DB: Delete");
				break;
			case 5 : 
				test.log(LogStatus.INFO, "Action Fetched from DB: Duplicate PK");
				break;
			case 6 :
				test.log(LogStatus.INFO, "Action Fetched from DB: Multiple Object Entry");
				break;
			case 7 : 
				test.log(LogStatus.INFO, "Action Fetched from DB: Parent with Duplicate PK");
				break;
			case 8 : 
				test.log(LogStatus.INFO, "Action Fetched from DB: Duplicate Internal PK");
				break;
			case 9 : 
				test.log(LogStatus.INFO, "Action Fetched from DB: Wrong Moved");
				break;
			case 10 : 
				test.log(LogStatus.INFO, "Action Fetched from DB: Update Instead of Delete");
				break;
			case 11 : 
				test.log(LogStatus.INFO, "Action Fetched from DB: No Action Instead if Delete");
				break;
			default : 
				test.log(LogStatus.INFO, "Action Count Fetched from DB has no Significance");
				break;
			}

			log.debug("Leaving processActionByActionCount");
		} catch (Exception e) {
			log.error("Error in processActionByActionCount:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error while verifying actions for Delete");
			Assert.assertTrue(false, e.getMessage());
		}
	}

	/*F*****************************************************************************************************
	 *
	 * FUNCTION: fetchObjIdFromDns
	 *
	 * PURPOSE : To fetch the object Id value from DNS_NAME that is passed as test data.  
	 * 
	 */

	public String fetchObjIdFromDns(String dnsName) {
		String objectId = null;
		try {

			log.debug("Entering fetchObjIdFromDns with DNS Name : " + dnsName);

			test.log(LogStatus.INFO, "To get the Object Id corresponding to dns name  : " +  dnsName);

			objectId = jdbc.getObjIdFromDns(dnsName);

			System.out.println("Object Id : " + objectId) ;

			if ( objectId == null )
			{
				test.log(LogStatus.FAIL, "No Rows fetched from DB for getting the Object Id that is required to get the actions.");
				Assert.assertTrue(false, "No Rows fetched from DB for getting the Object Id that is required to get the actions.");
			}


			log.debug("Leaving fetchObjIdFromDns");
		} catch (Exception e) {
			log.error("Error in fetchObjIdFromDns:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error while getting fetching object Id");
			Assert.assertTrue(false, e.getMessage());
		}
		return objectId;
	}

	/*F*****************************************************************************************************
	 *
	 * FUNCTION: fetchObjIdFromIp
	 *
	 * PURPOSE : To fetch the object Id value from IP_ADDRESS that is passed as test data.  
	 * 
	 */

	public String fetchObjIdFromIp(String ipAddress) {

		String objectId = null;
		try {

			log.debug("Entering fetchObjIdFromIp with DNS Name : " + ipAddress);

			test.log(LogStatus.INFO, "To get the Object Id corresponding to dns name  : " +  ipAddress);

			objectId = jdbc.getObjAndStatus("Object", ipAddress);

			System.out.println("Object Id : " + objectId) ;

			if ( objectId == null )
			{
				test.log(LogStatus.FAIL, "No Rows fetched from DB for getting the Object Id that is required to get the actions.");
				Assert.assertTrue(false, "No Rows fetched from DB for getting the Object Id that is required to get the actions.");
			}

			log.debug("Leaving fetchObjIdFromIp");
		} catch (Exception e) {
			log.error("Error in fetchObjIdFromIp:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error while getting fetching object Id");
			Assert.assertTrue(false, e.getMessage());
		}
		return objectId;
	}

	/*F*****************************************************************************************************
	 *
	 * FUNCTION: validateSecondSession
	 *
	 * PURPOSE : To verify Parallel Session Run results for DataExport and Reconciliation.  
	 * 
	 */
	public void validateSecondSession(String integName, String sessionType, String isDualSession) {
		try {
			log.debug("Entering validateSecondSession for integration: " +  integName + " and sessionType : " + sessionType + " with isDualSession Indicator : " + isDualSession);
			wait(1);

			String statusUrl = driver.getCurrentUrl();
			System.out.println("Status url : " + statusUrl);
			wait(2);
			switchFirstNewTab();
			wait(2);
			navigate(statusUrl);
			wait(1);
			click(START_SESSION);
			test.log(LogStatus.INFO, "Trigger the First Parallel Session: " + test.addScreenCapture(addScreenshot()));
			switchFirstTab();
			wait(1);
			if ( isDualSession.equalsIgnoreCase("Yes"))
			{
				click(INTEGRATION_FOLDER);
				wait(2);
				if ( integName.equalsIgnoreCase("FDB") || integName.equalsIgnoreCase("IPV4") )
				{
					if ( integName.equalsIgnoreCase("FDB")) 
					{
						click(FDB_RECON);
						wait(2);
						click(FDB_CUST_DATA_EXPORT);
						wait(2);
					}
					else
					{
						click(IPV4_RECON);
						wait(2);
						click(EXPORT_IPV4_RANGE);
						wait(2);
					}
				}
				else
				{
					click(RECONCILIATION);
				}
				test.log(LogStatus.INFO, "Navigation to Reconciliation : " + test.addScreenCapture(addScreenshot()));
				wait(1);
				if ( integName.equalsIgnoreCase("CMTS"))
				{
					click(SNMP_CLI_RECON);
					wait(2);
				}
				if ( integName.equalsIgnoreCase("Spectrum") || integName.equalsIgnoreCase("ETX") || integName.equalsIgnoreCase("CIN"))
				{
					clickNthElement(SNMP_NTW_DISCOVERY, 0);
					wait(2);
				}
			}
			test.log(LogStatus.INFO, "Navigated back to Trigger the Second Parallel Session: " + test.addScreenCapture(addScreenshot()));
			click(START_SESSION);
			wait(2);
			while(isElementPresent(PRIORITY))
			{
				wait(3);
				refreshPage();
				wait(3);
				//Validate for Error Occurrence
				if ( isElementPresent(DATA_SESSION_TERMINATED) && isElementPresent(PARALLEL_RUN_ERROR) )
				{
					System.out.println("In If ");
					test.log(LogStatus.INFO, "Triggered Parallel Session Result: " + test.addScreenCapture(addScreenshot()));
					test.log(LogStatus.PASS, "Error Occurred for Parallel Run of " + sessionType + " as expected.");
					Assert.assertTrue(true, "Error Occurred for Parallel Run of " + sessionType + " as expected.");
					break;

				}

				if ( isElementPresent(DATA_SESSION_TERMINATED) || isElementPresent(DATA_SESSION_COMPLETE) || isElementPresent(DATA_SESSION_WARNING) ) {
					test.log(LogStatus.INFO, "Triggered Parallel Session Result: " + test.addScreenCapture(addScreenshot()));
					test.log(LogStatus.FAIL, "Error for Parallel Run of " + sessionType + " is expected but dint occur. Please check.");
					Assert.assertTrue(false, "Error for Parallel Run of " + sessionType + " is expected but dint occur. Please check.");
					break;
				}
			}

			log.debug("Leaving validateSecondSession");
		} catch (Exception e) {
			log.error("Error in validateSecondSession:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error while validating second session of DE");
			Assert.assertTrue(false, e.getMessage());
		}

	}

	/*F*****************************************************************************************************
	 *
	 * FUNCTION: getDictionaryStaticData
	 *
	 * PURPOSE : To fetch the source object Ids required for dictionary test into a List from Test Data Suite.  
	 * 
	 */
	
	public  List<String> getDictionaryStaticData(String deviceName) throws SQLException, ClassNotFoundException {
		ExcelOperation xls = new ExcelOperation(Constants.TestDataSuite_XLS);
		String sheetName = Constants.SHEET_DICTIONARY_TEST_DATA, xlDeviceName;
		String objectIds;
		System.out.println("Device Name :  " + deviceName);

		int rowCount = 2;
		List<String> srcObjList = new ArrayList<String>();  
		while (!(xlDeviceName = xls.getCellData(sheetName, 0, rowCount)).equals("") )  {
			System.out.println("Im Here in while : xlDeviceName " + xlDeviceName +  " device: "  + deviceName);
			if( xlDeviceName.equalsIgnoreCase(deviceName) ) {
				System.out.println("Im Here in if : " + deviceName);
				objectIds = xls.getCellData(sheetName, 3, rowCount);
				System.out.println("objectIds : " + objectIds);
				srcObjList.add(objectIds);
			}
			System.out.println("srcObjList size : " + srcObjList.size());
			rowCount++;
		}
		System.out.println("List  Size: " + srcObjList.size());
		for(String sourceObj:srcObjList){  
			System.out.println(sourceObj);  
		}  
		return srcObjList;
	}
	
	/*F*****************************************************************************************************
	 *
	 * FUNCTION: validateDictionaryTest
	 *
	 * PURPOSE : To validate Dictionary Test for integration.  
	 * 
	 */
	public void validateDictionaryTest(String deviceName, String sessionId) {
		try {
			log.debug("Entering validateDictionaryTest for : " +  deviceName);
			List<String> sourceObjectIdsList = getDictionaryStaticData("ETX");
			String tableName = null;
			int srcObjCount = 0;
			if ( sessionId == null )
			{
				test.log(LogStatus.FAIL, "No Valid DF Session is found to run the validateDictionaryTest");
				Assert.assertTrue(false, "No Valid DF Session is found to run the validateDictionaryTest");
			}else
			{
				tableName = jdbc.getTableName(sessionId, deviceName);

				System.out.println("table Name : " + tableName);

				for ( String sourceObj : sourceObjectIdsList)
				{
					System.out.println("hi : ");
					System.out.println("srcObj : " + sourceObj);
					System.out.println("hello : ");
					srcObjCount = jdbc.getSrcObjectIdCount(tableName, sourceObj);

					test.log(LogStatus.INFO, "Num of rows returned for the source Object  : " + sourceObj + " is/are : " + srcObjCount);

					if ( srcObjCount == 0 )
					{
						test.log(LogStatus.FAIL, "Source Object with id : " + sourceObj + " was not found after Reconciliation.");
					}else
					{
						test.log(LogStatus.INFO, "Source Object with id : " + sourceObj + " was found after Reconciliation.");
					}
				}
			}
			log.debug("Leaving validateDictionaryTest");
		}catch (Exception e) {
			log.error("Error in validation Dictionary Test:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Error in validating dictionary test for " + deviceName);
			Assert.assertTrue(false, e.getMessage());
		}
	}

}