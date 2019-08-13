package com.netcracker.shaw.at_shaw_sd.pageobject;


import static com.netcracker.shaw.element.pageor.RIFlowPageElement.*;

import java.util.Date;

import static com.netcracker.shaw.element.pageor.DnRFlowPageElement.*;
import com.netcracker.shaw.at_shaw_sd.pageobject.LandingPage;
import com.netcracker.shaw.at_shaw_sd.util.Constants;
import com.netcracker.shaw.at_shaw_sd.util.Utility;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@SuppressWarnings(value = {"unchecked", "rawtypes"})
public class RIFlowPage<RIFlowPageElement> extends BasePage {
	
	LandingPage landing=null;
	Logger log = Logger.getLogger(RIFlowPage.class);

	public RIFlowPage(ExtentTest test) {
		super(test);
	}
	public RIFlowPage(ExtentTest test, WebDriver driver) {
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
	 * FUNCTION: addNewNetworkElement
	 *
	 * PURPOSE : To Add New NetworkElement.
	 * 
	 */
	public void addNewNetworkElement(String elementName) {
		try {
			log.debug("Entering addNewNetworkElement");
			
			click(NETWORK_ELEMENT_TAB);
			wait(2);
			click(NEW_NETWORK_ELEMENT);
			wait(1);
			inputText(NETWORK_ELEMENT_NAME, elementName);
			test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			wait(2);
			clickNthElement(CREATE_BUTTON, 1);
			wait(3);
			click(UPDATE_BUTTON);
			wait(2);
			click(PREV_PATH);
			wait(1);
			click(NETWORK_ELEMENT_TAB);
			test.log(LogStatus.PASS, "New Network Element Added Successfully");
			test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			log.debug("Leaving addNewNetworkElement");
		} catch (Exception e) {
			log.error("Error in addNewNetworkElement:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	/*F****************************************************************************
	 *
	 * FUNCTION: addNewDeviceForNE
	 *
	 * PURPOSE : To Add New Device to the NetworkElement.
	 * 
	 */
	public void addNewDeviceForNE(String netwrkName, String deviceType, String deviceName) {
		try {
			log.debug("Entering addNewDeviceForNE with NE Name : " + netwrkName + " device type :" + deviceType + " deviceName : " + deviceName);
			
			clickDynamic("span", netwrkName);
			wait(2);
			click(ADD_NEW_DEVICE);
			wait(2);
			click(ADD_DEVICE_FROM_REPOS);
			wait(2);
			click(DEVICE_TITLE);
			wait(2);
			click(DEVICE_TITLE);
			getWebElement(DEVICE_TITLE).sendKeys(deviceType);
			wait(2);
			getWebElement(DEVICE_TITLE).sendKeys(Keys.ENTER);
			wait(2);
			click(SEARCH_BUTTON);
			wait(2);
			selectFromList(DEVICE_LIST, deviceName);
			wait(2);
			selectFromList(PHYSICAL_STATUS, "Planned");
			test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			wait(1);
			clickNthElement(CREATE_BUTTON, 1);
			wait(3);
			test.log(LogStatus.PASS, "New Device Added Successfully");
			test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			log.debug("Leaving addNewDeviceForNE");
		} catch (Exception e) {
			log.error("Error in addNewDeviceForNE:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	/*F****************************************************************************
	 *
	 * FUNCTION: insertCardInSlot
	 *
	 * PURPOSE : To InsertCard in Slot of Device.
	 * 
	 */
	public void insertCardInSlot(String deviceName) {
		try {
			log.debug("Entering insertCardInSlot");
			
			/*String dynamicXpath = "//span[contains(text(),'" + deviceName + "')]";
			WebElement e1 = driver.findElement(By.xpath(dynamicXpath));
			e1.click();*/
			clickDynamic("span", deviceName);
			wait(1);
			click(SLOTS_TAB);
			wait(1);
			click(SELECT_SLOT);
			wait(1);
			click(INSERT_CARD_BTN);
			wait(2);
			click(SELECT_CARD);
			wait(2);
			selectFromList(PHYSICAL_STATUS_CARD, "Planned");
			test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			wait(1);
			clickNthElement(INSERT_BTN, 2);
			test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			wait(2);
			click(PORTS_TAB);
			test.log(LogStatus.PASS, "New Card Added Successfully");
			test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			log.debug("Leaving insertCardInSlot");
		} catch (Exception e) {
			log.error("Error in insertCardInSlot:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	public void replaceCard() {
		try {
			log.debug("Entering replaceCard");
			wait(1);
			click(SLOTS_TAB);
			wait(1);
			click(SELECT_SLOT);
			wait(1);
			click(REPLACE_CARD_BTN);
			wait(2);
			click(SELECT_REPLACE_CARD);
			wait(2);
			selectFromList(PHYSICAL_STATUS_CARD, "Planned");
			test.log(LogStatus.INFO, "Replacing Card: " + test.addScreenCapture(addScreenshot()));
			wait(1);
			click(START_BTN); 
			test.log(LogStatus.INFO, "Clicked Start Button: " + test.addScreenCapture(addScreenshot()));
			wait(2);
			click(NEXT_BTN); 
			wait(2);
			click(NEXT_BTN);
			wait(2);
			click(COMPLETE_BTN);
			test.log(LogStatus.INFO, "Replaced Card Snapshot: " + test.addScreenCapture(addScreenshot()));
			wait(2);
			click(DEVICE_CREATED);
			wait(2);
			click(SLOTS_TAB);
			test.log(LogStatus.PASS, "Card Replaced Successfully" + test.addScreenCapture(addScreenshot()));
			log.debug("Leaving replaceCard");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error in replaceCard:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	public void removeCard() {
		try {
			log.debug("Entering removeCard");
			wait(1);
			click(SLOTS_TAB);
			wait(1);
			click(SELECT_SLOT);
			wait(1);
			click(REMOVE_CARD_BTN);
			wait(2);
			removeTab();
			wait(2);
			//test.log(LogStatus.INFO, "Deleting Card Snapshot: " + test.addScreenCapture(addScreenshot()));
			wait(2);
			clickNthElement(DELETE_BTN, 2);
			test.log(LogStatus.INFO, "Card Deleted Snapshot: " + test.addScreenCapture(addScreenshot()));
			wait(2);
			click(CARDS_TAB);
			test.log(LogStatus.PASS, "Cards Removed Successfully" + test.addScreenCapture(addScreenshot()));
			log.debug("Leaving removeCard");
		} catch (Exception e) {
			log.error("Error in removeCard:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	public void checkAutomationLoc() {
		try {
			log.debug("Entering checkAutomationLoc");
			
			if (!isElementPresent(AUTOMATION_LOC) ) {
				
				click(CREATE_PROVIDER_LOC);
				wait(2);
				inputText(INPUT_PROV_NAME, "Automation Location");
				wait(2);
				clickNthElement(CREATE_BUTTON, 1);
				wait(2);
				click(PREV_PATH);
				test.log(LogStatus.INFO, "Created Automation Location as it does not exist: " + test.addScreenCapture(addScreenshot()));
				wait(1);
				click(AUTOMATION_LOC);
				test.log(LogStatus.INFO, "Navigated to Automation Location: " + test.addScreenCapture(addScreenshot()));
			}
			else {
				click(AUTOMATION_LOC);
				test.log(LogStatus.INFO, "Navigated to Automation Location: " + test.addScreenCapture(addScreenshot()));
			}
			
			log.debug("Leaving checkAutomationLoc");
		} catch (Exception e) {
			log.error("Error in checkAutomationLoc:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	
	/*public void validateCustomReport(String integration) {
		try {
			log.debug("Entering validateCustomReport for : " + integration);
			wait(2);
			navigate(Utility.getValueFromPropertyFile("documents_url"));
			wait(2);
			click(DATA_TRANSITION_PATH);
			wait(2);
			test.log(LogStatus.INFO, "Navigation to Data Transition path: " + test.addScreenCapture(addScreenshot()));
			click(CONFIGURATION_TAB);
			test.log(LogStatus.INFO, "Navigation to Configuration Tab of Data Transition: " + test.addScreenCapture(addScreenshot()));
			wait(2);
			switch (integration) {
					case "Alcatel":
										click(ALCATEL_CONFIG);
										test.log(LogStatus.INFO, "Navigation to Alcatel Config Folder: " + test.addScreenCapture(addScreenshot()));
										wait(1);
										click(IDB_TABLE);
										wait(1);
										clickNthElement(VALIDATION_TAB, 0);
										switchToAlert();
										break;
										
										
					case "CMTS-IDB":
										click(CMTS_CONFIG);
										test.log(LogStatus.INFO, "Navigation to CMTS Config Folder: " + test.addScreenCapture(addScreenshot()));
										wait(1);
										click(IDB_TABLE);
										wait(1);
										clickNthElement(VALIDATION_TAB, 0);
										switchToAlert();
										break;
										
					case "CMTS-SNMP/CLI":
										click(CMTS_CONFIG);
										test.log(LogStatus.INFO, "Navigation to CMTS Config Folder: " + test.addScreenCapture(addScreenshot()));
										wait(1);
										click(SDB_SNMP_TABLE);
										wait(1);
										clickNthElement(VALIDATION_TAB, 0);
										switchToAlert();
										break;
										
										
					case "Spectrum-IDB":
										
										click(SPECTRUM_CONFIG);
										test.log(LogStatus.INFO, "Navigation to Spectrum Config Folder: " + test.addScreenCapture(addScreenshot()));
										wait(2);
										click(IDB_TABLE);
										wait(1);
										clickNthElement(VALIDATION_TAB, 0);
										//switchToAlert();
										break;
										
					case "Spectrum-SNMP/CLI":
						
										click(SPECTRUM_CONFIG);
										test.log(LogStatus.INFO, "Navigation to Spectrum Config Folder: " + test.addScreenCapture(addScreenshot()));
										wait(2);
										click(SDB_SNMP_TABLE);
										wait(1);
										clickNthElement(VALIDATION_TAB, 0);
										//switchToAlert();
										break;
										
					case "ETX":
										click(ETX_CONFIG);
										test.log(LogStatus.INFO, "Navigation to Spectrum Config Folder: " + test.addScreenCapture(addScreenshot()));
										wait(2);
										click(IDB_TABLE);
										wait(1);
										clickNthElement(VALIDATION_TAB, 0);
										switchToAlert();
										break;
										
					case "FDB-IDB":						
										click(FDB_CONFIG);
										wait(1);
										click(FDB_INTEG);
										wait(2);
										test.log(LogStatus.INFO, "Navigation to FDB Integration Folder: " + test.addScreenCapture(addScreenshot()));
										click(IDB_TABLE);
										wait(1);
										clickNthElement(VALIDATION_TAB, 0);
										switchToAlert();
										break;
										
					case "FDB-CustIDB":						
										click(FDB_CONFIG);
										wait(1);
										click(FDB_CUST_INTEG);
										wait(2);
										test.log(LogStatus.INFO, "Navigation to FDB Integration Folder: " + test.addScreenCapture(addScreenshot()));
										click(IDB_TABLE);
										wait(1);
										clickNthElement(VALIDATION_TAB, 0);
										//switchToAlert();
										break;
										
					case "IPV4":
										click(IPV4_CONFIG);
										wait(1);
										test.log(LogStatus.INFO, "Navigation to IPV4 Config Folder: " + test.addScreenCapture(addScreenshot()));
										click(IDB_TABLE);
										wait(1);
										clickNthElement(VALIDATION_TAB, 0);
										//switchToAlert();
										break;
										
					default:
										log.debug("Given Location not found on Page");
					}
			
			if (isElementPresent(CUST_REPO_VIEW_LINK))
			{
				String custRepoLink = getText(CUST_REPO_VIEW_LINK);
				System.out.println("custRepoLink : " + custRepoLink);
				if(custRepoLink.contains("Validation Report View")) {
					test.log(LogStatus.PASS, "Customer Report View Link is Available : " + test.addScreenCapture(addScreenshot()));
				}
			}
			else {
				test.log(LogStatus.FAIL, "Customer Report View Link is NOT Available, Please check: " + test.addScreenCapture(addScreenshot()));
				Assert.assertTrue(false, "Customer Report View Link was NOT Available, hence failing the TC" );
			}
			log.debug("Leaving validateCustomReport : " + integration);
		} catch (Exception e) {
			log.error("Error in validateCustomReport :" + integration + " " + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error in validate CustomReport with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}
	*/
	
	public void copyNetworkElement(String elementName) {
		try {
			log.debug("Entering copyNetworkElement");
			navigate(Utility.getValueFromPropertyFile("default_Loc"));
			if (isElementPresent(AUTOMATION_LOC) ) {
				wait(1);
				click(AUTOMATION_LOC);
				wait(1);
				clickInputCheckBox(elementName);
				wait(1);
				click(COPY_BTN);
				wait(1);
				selectFromList(TARGET_NE_DRP_DOWN_COPY, "Default location");		
				wait(2);
				test.log(LogStatus.INFO, "Trying to Copy : " + elementName + " from Automation to Default location " + test.addScreenCapture(addScreenshot()));
				clickNthElement(COPY_BTN, 1);
				wait(2);
				navigate(Utility.getValueFromPropertyFile("default_Loc"));
				wait(2);
				clickNthElement(DEFAULT_LOC, 3);
				wait(3);
				click(NAME_FILTER);
				wait(1);
				inputText(INPUT_NAME, elementName);  
				wait(1);
				clickNthElement(APPLY_BTN, 0); 
				wait(2);
				if(isElementPresent(SEARCH_NE_PRESENT))  
				test.log(LogStatus.INFO, "Network Element : " + elementName + " copied to Default location " + test.addScreenCapture(addScreenshot()));
				else {
					test.log(LogStatus.FAIL, "Network Element : " + elementName + " could not be copied to Default location. Please check " + test.addScreenCapture(addScreenshot()));
					Assert.assertTrue(false, "NE could not be copied successfully");
				}
			}
			else {
				test.log(LogStatus.FAIL, "Could not navigate to Automation Location: " + test.addScreenCapture(addScreenshot()));
				Assert.assertTrue(false, "Could not navigate to Automation Location");
			}
			
			log.debug("Leaving copyNetworkElement");
		} catch (Exception e) {
			log.error("Error in copyNetworkElement:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error encountered while copying Network Element : " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	
	public void moveNetworkElement(String elementName) {
		try {
			log.debug("Entering moveNetworkElement");
			navigate(Utility.getValueFromPropertyFile("default_Loc"));
			if (isElementPresent(AUTOMATION_LOC) ) {
				wait(1);
				click(AUTOMATION_LOC);
				wait(1);
				clickInputCheckBox(elementName);
				wait(1);
				clickNthElement(MOVE_BTN, 1);
				wait(1);
				selectFromList(TARGET_NE_DRP_DOWN_MOVE, "Default location");		
				wait(2);
				test.log(LogStatus.INFO, "Trying to Move : " + elementName + " from Automation to Default location " + test.addScreenCapture(addScreenshot()));
				clickNthElement(MOVE_BTN, 1);
				wait(2);
				if(!isElementPresent(SEARCH_NE_PRESENT))  
					test.log(LogStatus.INFO, "Network Element : " + elementName + " is no more in Automation Location " + test.addScreenCapture(addScreenshot()));
				else
					test.log(LogStatus.FAIL, "Network Element : " + elementName + " could not be moved to Default location. Please check " + test.addScreenCapture(addScreenshot()));
				navigate(Utility.getValueFromPropertyFile("default_Loc"));
				wait(2);
				clickNthElement(DEFAULT_LOC, 3);
				wait(2);
				click(NAME_FILTER);  
				wait(1);
				inputText(INPUT_NAME, elementName);  
				wait(1);
				clickNthElement(APPLY_BTN, 0); 
				wait(2);
				if(isElementPresent(SEARCH_NE_PRESENT))  
					test.log(LogStatus.INFO, "Network Element : " + elementName + " moved to Default location " + test.addScreenCapture(addScreenshot()));
				else {
					test.log(LogStatus.FAIL, "Network Element : " + elementName + " could not be moved to Default location. Please check " + test.addScreenCapture(addScreenshot()));
					Assert.assertTrue(false, "NE could not be moved successfully");
				}
			}
			else {
				test.log(LogStatus.INFO, "Could not navigate to Automation Location: " + test.addScreenCapture(addScreenshot()));
				Assert.assertTrue(false, "Could not navigate to Automation Location");
			}
			
			log.debug("Leaving moveNetworkElement");
		} catch (Exception e) {
			log.error("Error in moveNetworkElement:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error encountered while moving Network Element: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	public void createCircuit(String elementName, String deviceName, String carrier1 , String resource1, String carrier2 , String resource2, String isPortsTabReq) {
		try {
			log.debug("Entering createCircuit with NE : " + elementName + " deviceName : " + deviceName + " carrier1 : " + carrier1 + " resource1 : " + resource1 + " carrier2 : " + carrier2 + " resource2 : " + resource2);;
			//navigate("http://devapp735cn.netcracker.com:6830/ncobject.jsp?id=9154193072013096951");
			if ( isPortsTabReq.equalsIgnoreCase("Yes") ) {
				clickDynamic("span", deviceName);
				wait(1);
				test.log(LogStatus.INFO, "Ports Status before creating the circuit " + test.addScreenCapture(addScreenshot()));
				clickNthElement(CIRCUITS_TAB, 0);
			}
			wait(1);
			click(CREATE_CIRCUITS);
			wait(1);
			/*if (elementName.isEmpty() )
			inputText(CIRCUIT_NAME_INPUT, Constants.NE_DTM_FORMAT.format(new Date()) + " Circuit");
			else*/
			inputText(CIRCUIT_NAME_INPUT, elementName + " Circuit");
			
			wait(1);
			/*if (!elementName.isEmpty() ) {*/
				click(CARRIER1);
				getWebElement(CARRIER1).sendKeys(carrier1);
				wait(3);
				getWebElement(CARRIER1).sendKeys(Keys.ENTER);
			/*}*/
			wait(1);
			/*if (!elementName.isEmpty() ) {*/
				click(RESOURCE1);
				getWebElement(RESOURCE1).sendKeys(resource1);
				wait(3);
				getWebElement(RESOURCE1).sendKeys(Keys.ENTER);
			/*}*/
			wait(1);
			/*if (!elementName.isEmpty() ) {*/
				click(CARRIER2);
				getWebElement(CARRIER2).sendKeys(carrier2);
				wait(3);
				getWebElement(CARRIER2).sendKeys(Keys.ENTER);
			/*}*/
			wait(1);
			/*if (!elementName.isEmpty() ) {*/
				click(RESOURCE2);
				getWebElement(RESOURCE2).sendKeys(resource2);
				wait(3);
				getWebElement(RESOURCE2).sendKeys(Keys.ENTER);
			/*}*/
			wait(2);
			test.log(LogStatus.INFO, "Creating Circuit with the Carrier's and Resources as in Snapshot : " + test.addScreenCapture(addScreenshot()));
			wait(2);
			clickNthElement(CREATE_BUTTON, 1);
			wait(2);
			if ( isPortsTabReq.equalsIgnoreCase("Yes") ) {
				click(PORTS_TAB);
				test.log(LogStatus.INFO, "Ports Status after creating the circuit " + test.addScreenCapture(addScreenshot()));

				String lan1Status = getText(LAN1_STATUS);		
				String lan2Status = getText(LAN2_STATUS);

				//validation of Status change on the LAN's
				if ( lan1Status.equalsIgnoreCase("Assigned") && lan2Status.equalsIgnoreCase("Assigned") )
					test.log(LogStatus.PASS, "Status of LAN 1 and LAN 2 have been changed from Available to Assigned as Expected");
				else {
					test.log(LogStatus.FAIL, "Status of LAN 1 and LAN 2 have NOT changed from Available to Assigned as Expected, Please check");
					Assert.assertTrue(false, "Status of LAN's not changed as expected");
				}
			}else {
				wait(2);
				clickDynamic("span", elementName);
				wait(2);
				click(PARAMETERS_TAB);
				wait(1);
				click(EDIT);
				wait(2);
				click(OBJ_TYPE_DRP_DOWN); 
				wait(2);
				clickNthElement(TRANSMISSION_CKT, 1);
				wait(2);
				click(UPDATE);
				test.log(LogStatus.INFO, "Created Transmission Circuit Successfully as in Snapshot : " + test.addScreenCapture(addScreenshot()));
			}
			log.debug("Leaving createCircuit");
		} catch (Exception e) {
			log.error("Error in createCircuit:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error encountered while creating circuit : " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	
	public void addCarrierResource(String ethernetPort, String cinDevice) {
		try {
			log.debug("Entering addCarrierResource with ethernetPort : " + ethernetPort + " and cinDevice : " + cinDevice);
			click(CIRCUITS_TAB);
			wait(1);
			
			log.debug("Leaving addCarrierResource");
		} catch (Exception e) {
			log.error("Error in addCarrierResource:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error encountered while deleting circuit : " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	public void deleteCircuit(String elementName) {
		try {
			log.debug("Entering deleteCircuit with NE : " + elementName);
			click(CIRCUITS_TAB);
			wait(1);
			clickInputCheckBox(elementName);
			wait(1);
			click(TAB_DELETE_BTN);
			wait(2);
			switchToAlert();
			wait(2);
			test.log(LogStatus.INFO, "Deleted Circuit with the Carrier's and Resources as in Snapshot : " + test.addScreenCapture(addScreenshot()));
			wait(1);
			click(PORTS_TAB);
			test.log(LogStatus.INFO, "Ports Status after creating the circuit " + test.addScreenCapture(addScreenshot()));
			
			String lan1Status = getText(LAN1_STATUS);		
			String lan2Status = getText(LAN2_STATUS);
			
			//validation of Status change on the LAN's
			if ( lan1Status.equalsIgnoreCase("Available") && lan2Status.equalsIgnoreCase("Available") )
				test.log(LogStatus.PASS, "Status of LAN 1 and LAN 2 have been changed from Assigned to Available as Expected");
			else {
				test.log(LogStatus.FAIL, "Status of LAN 1 and LAN 2 have NOT changed from Assigned to Available as Expected, Please check");
				Assert.assertTrue(false, "Status of LAN's not changed as expected");
			}
			log.debug("Leaving deleteCircuit");
		} catch (Exception e) {
			log.error("Error in deleteCircuit:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error encountered while deleting circuit : " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	
	public void checkIPRangeExistence(String ipRange) {
		try {
			log.debug("Entering checkIPRangeExistence for ipRange : " + ipRange);
			clickDynamic("a", ipRange);
			if ( ( (ipRange.equalsIgnoreCase("IPv4 Ranges")) && (isElementPresent(DEFAULT_IP_RANGE_IPV4)) ) || 
					( (ipRange.equalsIgnoreCase("IPv6 Ranges")) && (isElementPresent(DEFAULT_IP_RANGE_IPV6)) )	)
			{
				//if ( isElementPresent(DEFAULT_IP_RANGE_IPV4) ) {
				System.out.println("In If ");
				wait(1);
				if ( ipRange.equalsIgnoreCase("IPv4 Ranges") )
				clickInputCheckBox("245.245.0.0/16");
				else
					clickInputCheckBox("555:0:0:0:0:0:0:0/16");
				wait(2);
				clickDynamic("a", "Delete Range");
				wait(1);
				switchToAlert();
				wait(2);
				test.log(LogStatus.INFO, "Deleted IP Range 245.245.0.0/16 as shown in Snapshot : " + test.addScreenCapture(addScreenshot()));
				wait(1);
				clickNthElement(ADD_RANGE_BTN, 0);		
				wait(2);
				if ( ipRange.equalsIgnoreCase("IPv4 Ranges") )
				inputText(ADDRESS_INPUT, "245245");
				else
					inputText(ADDRESS_INPUT, "555");
				wait(2);
				selectFromList(PREFIX_DRPDOWN, "16");		
				wait(3);
				clickNthElement(CREATE_BUTTON, 1);
				test.log(LogStatus.INFO, "Creating IP Range with Prefix 16 as shown in Snapshot : " + test.addScreenCapture(addScreenshot()));
				wait(3);
				if ( isElementPresent(DEFAULT_IP_RANGE_IPV4) || isElementPresent(DEFAULT_IP_RANGE_IPV6))
				test.log(LogStatus.PASS, "Successfully created IP Range with Prefix 16 : " + test.addScreenCapture(addScreenshot()));
				
			}else {
				System.out.println("In Else ");
				wait(2);
				clickNthElement(ADD_RANGE_BTN, 0);
				wait(2);
				if ( ipRange.equalsIgnoreCase("IPv4 Ranges") )
					inputText(ADDRESS_INPUT, "245245");
					else
						inputText(ADDRESS_INPUT, "555");
				wait(2);
				selectFromList(PREFIX_DRPDOWN, "16");
				wait(3);
				clickNthElement(CREATE_BUTTON, 1);
				test.log(LogStatus.INFO, "Creating IP Range 245.245.0.0 with Prefix 16 as shown in Snapshot : " + test.addScreenCapture(addScreenshot()));
				wait(3);
				if ( isElementPresent(DEFAULT_IP_RANGE_IPV4) || isElementPresent(DEFAULT_IP_RANGE_IPV6))
				test.log(LogStatus.PASS, "Successfully created IP Range 245.245.0.0 with Prefix 16 : " + test.addScreenCapture(addScreenshot()));
			}
			log.debug("Leaving checkIPRangeExistence");
		} catch (Exception e) {
			log.error("Error in checkIPRangeExistence:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error encountered while creating IP Range : " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	//Added for testing Split and Merge functionalities of IP Ranges ( IP Planner ) 
	public void splitAndMergeIPRange(String ipRange) {
		
		try {
			log.debug("Entering splitAndMergeIPRange for ipRange : " + ipRange);
			clickDynamic("a", ipRange);
			wait(1);
			if ( ipRange.equalsIgnoreCase("IPv4 Ranges") )
				clickInputCheckBox("245.245.0.0/16");
				else
					clickInputCheckBox("555:0:0:0:0:0:0:0/16");
			//clickInputCheckBox("245.245.0.0/16");
			wait(2);
			click(SPLIT_ICON);  
			wait(2);
			clickNthElement(SPLIT_ICON, 1);
			wait(2);
			if ( ipRange.equalsIgnoreCase("IPv4 Ranges") )
			clickDynamic("span", "245.245.0.0/16");
			wait(2);
			test.log(LogStatus.INFO, "Split on IP Range Completed Successfully : " + test.addScreenCapture(addScreenshot()));
			wait(1);
			clickNthElement(SUBRANGE_CHECKBOX, 0);
			wait(1);
			clickNthElement(SUBRANGE_CHECKBOX, 1);
			wait(1);
			click(MERGE_ICON);
			wait(2);
			clickNthElement(MERGE_ICON, 1);
			wait(2);
			test.log(LogStatus.INFO, "Merge on IP Range Completed Successfully : " + test.addScreenCapture(addScreenshot()));
			log.debug("Leaving splitAndMergeIPRange");
		} catch (Exception e) {
			log.error("Error in splitAndMergeIPRange:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error encountered while splitting and merging IP Range : " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
}