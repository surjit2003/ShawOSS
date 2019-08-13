package com.netcracker.shaw.at_shaw_sd.pageobject;

import static com.netcracker.shaw.element.pageor.VLANFlowPageElement.*;
import static com.netcracker.shaw.element.pageor.DnRFlowPageElement.*;
import static com.netcracker.shaw.element.pageor.RIFlowPageElement.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.xml.sax.SAXException;

import com.netcracker.shaw.at_shaw_sd.jdbc.JDBCValidator;
import com.netcracker.shaw.at_shaw_sd.soap.SOAPHelper;
import com.netcracker.shaw.at_shaw_sd.util.Constants;
import com.netcracker.shaw.at_shaw_sd.util.Utility;
import com.netcracker.shaw.element.PageElement;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@SuppressWarnings(value = {"unchecked", "rawtypes"})
public class VLANFlowPage<VLANFlowPageElement> extends BasePage {
	
	private static final TimeUnit SECONDS = null;
	private JDBCValidator jdbc = new JDBCValidator();
	//private PuttyConnector putty = null;
	SOAPHelper soapHelper = null; 
			
	public void setSoapHelper(SOAPHelper soapHelper) {
		this.soapHelper = soapHelper;
	}
	
	Logger log = Logger.getLogger(VLANFlowPage.class);

	public VLANFlowPage(ExtentTest test) {
		super(test);
	}
	public VLANFlowPage(ExtentTest test, WebDriver driver) {
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
	 * FUNCTION: navigateToVLANs
	 *
	 * PURPOSE : To Reserve a new VLAN.
	 * 
	 */
	public String reserveNewINVLAN(String poolOption, String acctNum) {
		
		try {
			log.debug("Entering navigateToVLANs");
			wait(1);
			click(NETWORKS_TAB);
			wait(2);
			click(ETHERNET_NTW_FOLDER);
			test.log(LogStatus.INFO, "Inside Ethernet Network Folder: " + test.addScreenCapture(addScreenshot()));
			wait(2);
			click(EOD_NETWORK);
			wait(2);
			click(VLANs_TAB);
			wait(2);
			click(RESERVE_CVLAN);
			wait(2);
			removeTab();
			wait(3);
			click(FILL_VLAN_POOL);
			wait(2);
			clickDynamic("div", poolOption);
			wait(2);
			click(ACCT_NUM);
			wait(2);
			if ( acctNum.isEmpty() )
			{
				System.out.println("In Empty");
				acctNum =  Utility.getAccountNum();
				System.out.println("Acct Num = " + acctNum);
				inputText(FILL_ACCT_NUM, acctNum);  
			}
			System.out.println("Acct Num = " + acctNum);
			inputText(FILL_ACCT_NUM, acctNum);  
			wait(2);
			click(ADD_BTN); 
			test.log(LogStatus.INFO, "Adding New VLAN: " + test.addScreenCapture(addScreenshot()));
			test.log(LogStatus.PASS, "New VLAN reserved Successfully");
			log.debug("Leaving navigateToVLANs");
			
		} catch (Exception e) {
		log.error("Error in navigateToVLANs:" + e.getMessage());
		test.log(LogStatus.ERROR, e);
		test.log(LogStatus.FAIL, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
		Assert.assertTrue(false, e.getMessage());
		}
		return acctNum;
	}
	
	/*F****************************************************************************
	 *
	 * FUNCTION: printVlanDataDetails
	 *
	 * PURPOSE : To validate VLAN Data from DB.
	 * 
	 */
	public void getVlanDataDetails(String poolOption) {
		try {
			log.debug("Entering getVlanDataDetails with poolOption : " + poolOption);
			wait(1);
			String vLANData = jdbc.printVlanDBDetails(poolOption);
			
			test.log(LogStatus.INFO, "<p> VLAN Data of the pool : </p>" + vLANData);
			
			log.debug("Leaving getVlanDataDetails");
		} catch (Exception e) {
		log.error("Error in getVlanDataDetails:" + e.getMessage());
		test.log(LogStatus.ERROR, e);
		test.log(LogStatus.FAIL, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
		Assert.assertTrue(false, e.getMessage());
		}
	}
	
	/*F****************************************************************************
	 *
	 * FUNCTION: validatePostVlanData
	 *
	 * PURPOSE : To validate VLAN Data after the Soap/Gui request is created.
	 * 
	 */
	public void validatePostVlanData(String paramType, String param, String poolId) {
		try {
			log.debug("Entering validatePostVlanData");
			wait(1);
			//System.out.println(" Acct num in validatePostVlanData : " + acctNum);
			String postVLANData = jdbc.printVlanDBDetails(poolId);
			
			test.log(LogStatus.INFO, "<p> VLAN Data(Post) for Pool from DB: </p>" + postVLANData);
			
			Map<String, String> postVLANDataMap = jdbc.getVLANAcctDetails(poolId);
			
			//Validate whether the acctNum passed to the function exists in the db data or not
			boolean paramExists = false;
			if(!postVLANDataMap.containsValue(param)) {
				paramExists = true;
			}
			
			switch (paramType) {

			case "AcctNum" :
				
				if ( paramExists = true )	{
					test.log(LogStatus.INFO, "AcctNum that was generated via GUI is found in DB");
					//Assert.assertTrue(true, "Acctnum that was generated via GUI is found in DB");
				}
				else {
					test.log(LogStatus.INFO, "Acctnum that was generated via GUI not found in DB");
					Assert.assertTrue(false, "Acctnum that was generated via GUI is not found in DB");
				}
				break;
				
			case "INVLAN" : 
				
				if ( paramExists = true )	{
					test.log(LogStatus.INFO, "INVLAN that was generated via Soap is found in DB");
				//Assert.assertTrue(true, "Acctnum that was generated via Soap is not found in DB");
				}
				else {
					test.log(LogStatus.INFO, "INVLAN that was generated via Soap not found in DB");
					Assert.assertTrue(false, "Acctnum that was generated via Soap is not found in DB");
				}
				break;
				
			default:
				test.log(LogStatus.INFO, "Parameter passed is not a valid parameter");
				break;
			}
			
			log.debug("Leaving validatePostVlanData");
		} catch (Exception e) {
		log.error("Error in validatePostVlanData:" + e.getMessage());
		test.log(LogStatus.ERROR, e);
		test.log(LogStatus.FAIL, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
		Assert.assertTrue(false, e.getMessage());
		}
	}
	
	/*F****************************************************************************
	 *
	 * FUNCTION: getMapSize
	 *
	 * PURPOSE : To get the VLAN Pool Map Size .
	 * 
	 */
	
	public int getMapSize(String poolOption) {
		int mapSize = 0;
		try {
			log.debug("Entering getMapSize for poolOption : " + poolOption);
			Map<String, String> postVLANDataMap = jdbc.getVLANAcctDetails(poolOption);
			mapSize = postVLANDataMap.size();
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error in getMapSize:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
		}
		return mapSize;
	}
	
	/*F****************************************************************************
	 *
	 * FUNCTION: validateVLANMapSize
	 *
	 * PURPOSE : To validate VLAN Pool Map Size with intial and final values.
	 * 
	 */
	
	public void validateVLANMapSize(int initialSize, int finalSize, String operation) {
		
		try {
			log.debug("Entering validateVLANMapSize with initialSize : " + initialSize + " and  finalSize : " + finalSize);
			test.log(LogStatus.INFO, "Size of VLAN Data Map before the Transaction  : " + initialSize);
			test.log(LogStatus.INFO, "Size of VLAN Data Map after the Transaction : " + finalSize);
			switch(operation) {
			
			case "Equals" : 
			if ( initialSize == finalSize) {
				test.log(LogStatus.PASS, "VLAN Data Count is Same implies, the INVLAN generated with Existing AcctNum already exists in DB prior to the Transaction");
				Assert.assertTrue(true, "VLAN Data Count is Same implies, the INVLAN generated with Existing AcctNum already exists in DB prior to the Transaction");
			}
			else
			{
				test.log(LogStatus.FAIL, "VLAN Data Count is Different, Hence INVLAN was generated newly with existing AcctNum which is not expected ");
				Assert.assertTrue(false, "VLAN Data Count is  Different, Hence INVLAN was generated newly with existing AcctNum which is not expected ");
			}
			break;
			
			case "Increment" :
				if ( initialSize < finalSize) {
					test.log(LogStatus.PASS, "Final Data Count is more than the Initial Data Count, hence the INVLAN generated with New AcctNum created a new entry in DB");
					Assert.assertTrue(true, "Final Data Count is more than the Initial Data Count, hence the INVLAN generated with New AcctNum created a new entry in DB");
				}
				else
				{
					test.log(LogStatus.FAIL, "Final Data Count is less/equal than the Initial Data Count, hence the INVLAN generated with New AcctNum did not create a new entry in DB");
					Assert.assertTrue(false, "Final Data Count is less/equal than the Initial Data Count, hence the INVLAN generated with New AcctNum did not create a new entry in DB");
				}
				break;
				
		}
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error in validateVLANMapSize:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	/*F****************************************************************************
	 *
	 * FUNCTION: validateError
	 *
	 * PURPOSE : To validate the different errors occurred in SOAP UI Transaction.
	 * 
	 */
	
	public void validateError(String responseXml, String errorType) {
		try {
			log.debug("Entering validateError with response : " + responseXml);
			soapHelper.parseXml(responseXml);
			wait(1);

			switch(errorType) {

			case "JavaException" :
				if (responseXml.contains("java.lang.RuntimeException")) {
					test.log(LogStatus.PASS, "Java Runtime Exception Error Occured while processing the Soap Request as Expected");
				}else {
					test.log(LogStatus.FAIL, "Java Runtime Exception was Expected to occur while processing this Soap Request");
					Assert.assertTrue(false, "Java Runtime Exception was Expected to occur while processing this Soap Request");
				}
				break;

			case "Account Number" :

				if (responseXml.contains("Account Number Can't be Null")) {
					test.log(LogStatus.PASS, "Account Number Can't be Null Error Occured while processing the Soap Request as Expected");
				}else {
					test.log(LogStatus.FAIL, "Account Number Can't be Null was Expected to occur while processing this Soap Request");
					Assert.assertTrue(false, "Account Number Can't be Null was Expected to occur while processing this Soap Request");
				}
				break;


			case "Bad primary key" :

				if (responseXml.contains("Bad primary key")) {
					test.log(LogStatus.PASS, "Bad primary key Error Occured while processing the Soap Request as Expected");
				}
				else {
					test.log(LogStatus.FAIL, "Bad primary key was Expected to occur while processing this Soap Request");
					Assert.assertTrue(false, "Bad primary key was Expected to occur while processing this Soap Request");
				}
				break;


			case "Not Found" :

				if (responseXml.contains("not found")) {
					test.log(LogStatus.PASS, "Object Not Found Error Occured while processing the Soap Request as Expected");
				}
				else {
					test.log(LogStatus.FAIL, "Object Not Found was Expected to occur while processing this Soap Request");
					Assert.assertTrue(false, "Object Not Found was Expected to occur while processing this Soap Request");
				}
				break;

			case "Cant be Null" :

				if (responseXml.contains("Cant be Null")) {
					test.log(LogStatus.PASS, "Cant be Null Error Occured while processing the Soap Request as Expected");
				}else {
					test.log(LogStatus.FAIL, "Cant be Null Error was Expected to occur while processing this Soap Request");
					Assert.assertTrue(false, "Cant be Null Error was Expected to occur while processing this Soap Request");
				}
				break;

			case "Pool Exhausted" :

				if (responseXml.contains("SVLAN Pool Exhausted")) {
					test.log(LogStatus.PASS, "SVLAN Pool Exhausted Error Occured while processing the Soap Request as Expected");
				}else {
					test.log(LogStatus.FAIL, "SVLAN Pool Exhausted was Expected to occur while processing this Soap Request");
					Assert.assertTrue(false, "SVLAN Pool Exhausted was Expected to occur while processing this Soap Request");
				}
				break;

			case "Invalid MAC address" :

				if (responseXml.contains("Invalid MAC address")) {
					test.log(LogStatus.PASS, "Invalid MAC address Error Occured while processing the Soap Request as Expected");
				}
				else {
					test.log(LogStatus.FAIL, "Invalid MAC address was Expected to occur while processing this Soap Request");
					Assert.assertTrue(false, "Invalid MAC address was Expected to occur while processing this Soap Request");
				}
				break;

			default : 
				log.debug("No Proper Exception Passed to the function");

			}
			
			log.debug("Leaving validateErrorResponseCommit");
		} catch (Exception e) {
			log.error("Error in validateErrorResponseCommit:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	/*F****************************************************************************
	 *
	 * FUNCTION: validateStatus
	 *
	 * PURPOSE : To validate the Status of the VLAN ID.
	 * 
	 */
	
	public String validateStatus(String poolOption, String poolId)
	{
		String vLanStatus = null;
		
		try {
			log.debug("Entering validateStatus with poolId : " + poolId + " and poolOption : " + poolOption);
			Map<String, String> dataMap = jdbc.getVLANStatusDetails(poolOption);
			
			for (String  vLanID : dataMap.keySet()) {
				
				if (vLanID.equalsIgnoreCase(poolId))
				{
				vLanStatus = dataMap.get(vLanID);
				break;
				}
			}
			
			if ( vLanStatus != null && vLanStatus.equalsIgnoreCase("Assigned"))
			{
				test.log(LogStatus.PASS, "Status of INVLAN ID : " + poolId + " got changed from Reserved to Assigned successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Status of INVLAN ID : " + poolId + " dint not change from Reserved to Assigned, Please check");
			}
			
			String printDataMap = jdbc.printVlanDBDetails(poolOption);
			test.log(LogStatus.INFO, "<p> VLAN Pool Data after Commit : <p>" + printDataMap);
			log.debug("Leaving validateStatus ");
		} catch (Exception e) {
			log.error("Error in validateStatus:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
		}
		return vLanStatus;
	}
	
	
	/*F****************************************************************************
	 *
	 * FUNCTION: validateStatus
	 *
	 * PURPOSE : To validate the Delete Status of the VLAN ID via DB.
	 * 
	 */
	
	public void validateDeleteStatus(String poolOption, String poolId) {

		try {
			log.debug("Entering validateDeleteStatus with poolId : " + poolId + " and poolOption : " + poolOption);
			Map<String, String> dataMap = jdbc.getVLANAcctDetails(poolOption);

			if ( !dataMap.containsKey(poolId))
			{
				test.log(LogStatus.PASS, "INVLAN Pool : " + poolId + " has been deleted successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "INVLAN Pool : " + poolId + " could not be deleted, Please check");
			}
			
			String printDataMap = jdbc.printVlanDBDetails(poolOption);
			
			test.log(LogStatus.INFO, "<p> VLAN Pool Data after Deletion : <p>" + printDataMap);
			log.debug("Leaving validateDeleteStatus ");

		}catch (Exception e) {
			log.error("Error in validateDeleteStatus:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	/*F**************************************************************************************
	 *
	 * FUNCTION: getNumOfDays
	 *
	 * PURPOSE : To get the Number Of Days configured in the GUI for Job Scheduler Request.
	 * 
	 */
	
	public String getNumOfDays() {
		String dayField = null;
		try {
			log.debug("Entering processJobScheduler : ");
			wait(1);
			click(RELEASE_VLAN);  
			wait(1);
			test.log(LogStatus.INFO, "Parameters Tab Data : " + test.addScreenCapture(addScreenshot()));
			String numOfDaysField = getText(NUM_OF_DAYS);
			dayField = numOfDaysField.split("=")[1];
			System.out.println("Num of days = " + dayField);
			test.log(LogStatus.INFO, "Numberofdays field value set to  : " + dayField);
			wait(1);
			log.debug("Leaving processJobScheduler ");
		
	} catch (Exception e) {
		log.error("Error in processJobScheduler :" + e.getMessage());
		test.log(LogStatus.ERROR, e);
		test.log(LogStatus.FAIL, "Error Snapshot Below: " + test.addScreenCapture(addScreenshot()));
		Assert.assertTrue(false, e.getMessage());
	}
		return dayField;
}
	
	/*F**************************************************************************************
	 *
	 * FUNCTION: runJob
	 *
	 * PURPOSE : To run job Scheduler in the triggers tab.
	 * 
	 */
	
	public Date runJob() {
		Date date = null;
		try {
			log.debug("Entering runJob");
			click(TRIGGERS_TAB);
			test.log(LogStatus.INFO, "Navigate to Triggers Tab to run Job ");
			wait(1);
			click(RUN_JOB);
			wait(1);
			test.log(LogStatus.INFO, "Ran Job " + test.addScreenCapture(addScreenshot()));
			date = new Date();
			test.log(LogStatus.INFO, "The current date and time when the Job has triggered is : " + date);
			wait(1);
			log.debug("Leaving runJob ");
		} catch (Exception e) {
			log.error("Error in runJob :" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error in running Job with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
		return date;
	}
	
	/*F**************************************************************************************
	 *
	 * FUNCTION: verifyCreationDate
	 *
	 * PURPOSE : To validate the Date Logic involved when Job Scheduler job has been run .
	 * 
	 */
	public void verifyCreationDate(int numOfDays, Date runDtm, String poolOption) {
		try {
			log.debug("Entering verifyCreationDate with numOfDays : " + numOfDays + " and Job run date and time : " + runDtm);
			Calendar cal = Calendar.getInstance();
			cal.setTime(runDtm);
			cal.add(Calendar.DATE, numOfDays*-1);
			Date targetDate = cal.getTime();
			System.out.println("Target Date : " + Constants.DATE_FORMAT.format(targetDate));
			
			String dbMinDateStr = jdbc.getMinDate(poolOption);
			System.out.println("Min Date : " + dbMinDateStr);
			
			Date dbMinDate =Constants.DATE_FORMAT.parse(dbMinDateStr);  
			
		    System.out.println("formatted date " +dbMinDate);
		    
		    if (dbMinDate.compareTo(targetDate) > 0) {
	            System.out.println("DB Min Date is after Run Job Target Date");
				test.log(LogStatus.PASS, "Released VLAN's that are no more needed after running Job");
				Assert.assertTrue(true, "Released VLAN's that are no more needed after running Job");
	        } else if (dbMinDate.compareTo(targetDate) < 0) {
	            System.out.println("DB Min Date is before Run Job Target Date");
	            test.log(LogStatus.FAIL, "Found records in DB which are not supposed to be after the Job Run");
				Assert.assertTrue(false, "Found records in DB which are not supposed to be after the Job Run");
	        } else if (dbMinDate.compareTo(targetDate) == 0) {
	            System.out.println("DB Min Date is equal to Run Job Target Date");
	            test.log(LogStatus.FAIL, "Found records in DB which are not supposed to be after the Job Run");
				Assert.assertTrue(false, "Found records in DB which are not supposed to be after the Job Run");
	        }
		    
			log.debug("Leaving verifyCreationDate ");

		} catch (Exception e) {
			log.error("Error in verifyCreationDate :" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error while verifying the creation date data: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	/*F**************************************************************************************
	 *
	 * FUNCTION: prepareExhaustRequest
	 *
	 * PURPOSE : To prepare required number of new requests to recreate Vlan Pool Exhausted.
	 * 
	 */
	
	public String prepareExhaustRequest(String reqXml, int maxValue) throws SOAPException, SAXException, IOException, ParserConfigurationException {
		String acctNum, replacedXml , responseXml= null;
		for (int i = 1 ; i <= maxValue ; i++ )
		{
			acctNum =  Utility.getAccountNum();
			System.out.println("Acct Num = " + acctNum);
			replacedXml = reqXml.replace("ACCT_NUM", acctNum);
			System.out.println("replaced Xml : " + replacedXml);
			responseXml = soapHelper.sendMessageToServerReplaceHeader(replacedXml);
			System.out.println("response : " + responseXml);
		}
		test.log(LogStatus.INFO, "<span style='font-weight:bold;'> Response: " + responseXml);
		return responseXml;
	}
	
	/*F**************************************************************************************
	 *
	 * FUNCTION: getVLANCountData
	 *
	 * PURPOSE : To get the Count of VLAN's already created in the system.
	 * 
	 */
	
	public int getVLANCountData(String poolOption) {
		int vLANDataCount = -1;
		try {
			log.debug("Entering getVLANCountData with poolOption : " + poolOption);
			wait(1);
			vLANDataCount = jdbc.getVlanPoolCount(poolOption);
			test.log(LogStatus.INFO, "Initial DB Count prior to exhaustion : " + vLANDataCount);
			log.debug("Leaving getVLANCountData");
		} catch (Exception e) {
		log.error("Error in getVLANCountData:" + e.getMessage());
		test.log(LogStatus.ERROR, e);
		Assert.assertTrue(false, e.getMessage());
		}
		return vLANDataCount;
	}
	
	
	/*public void validateMacAddress(String rpdNode, String macAddress) {
		try {
			log.debug("Entering validateMacAddress with rpdNode : " + rpdNode);
			wait(1);
			inputText(SEARCH_BOX, rpdNode);
			wait(3);
			getWebElement(SEARCH_BOX).sendKeys(Keys.ENTER);
			wait(5);
			clickDynamic("span", rpdNode);
			wait(2);
			click(PARAMETERS_TAB);
			wait(2);
			click(EDIT);
			wait(2);
			//scrollDown600(); // need to change 
			scrollDownToElement(MAC_ADDRESS_VALUE);
			wait(2);
			test.log(LogStatus.INFO, "Scrolled To  : " + test.addScreenCapture(addScreenshot()));
			String macAddressValue = getText(MAC_ADDRESS_VALUE); 
			if ( macAddressValue.equalsIgnoreCase(macAddress))
			{
				//pass the TC
				System.out.println("MAC Address has been updated as expected in the GUI.");
				test.log(LogStatus.PASS, "MAC Address has been updated as expected in the GUI.");
				Assert.assertTrue(true, "MAC Address has been updated as expected in the GUI.");
			}else {
				//fail the TC
				System.out.println("MAC Address has not updated as expected in the GUI. Please check.");
				test.log(LogStatus.FAIL, "MAC Address has not updated as expected in the GUI. Please check.");
				Assert.assertTrue(false, "MAC Address has not updated as expected in the GUI. Please check.");
			}
			test.log(LogStatus.INFO, "Parameters Tab of : " + rpdNode + test.addScreenCapture(addScreenshot()));
			log.debug("Leaving validateMacAddress");
		} catch (Exception e) {
		log.error("Error in validating the Mac Address:" + e.getMessage());
		test.log(LogStatus.ERROR, e);
		Assert.assertTrue(false, e.getMessage());
		}
	}*/
	
	
	public void updateOrValidateMacAddress(String rpdNode, String macAddress, String actionToImplement) {
		try {
			log.debug("Entering updateMacAddress for rpdNode : " + rpdNode + " with macAddress : " + macAddress + " with actionToImplement " + actionToImplement);
			wait(1);
			inputText(SEARCH_BOX, rpdNode);
			wait(3);
			getWebElement(SEARCH_BOX).sendKeys(Keys.ENTER);
			wait(2);
			clickDynamic("span", rpdNode);
			wait(2);
			click(PARAMETERS_TAB);
			wait(2);
			scrollDownToElement(MAC_ADDRESS_VALUE);
			wait(2);
			if ( actionToImplement.equalsIgnoreCase("Update"))
			{
				click(EDIT);
				wait(2);
				inputText(INPUT_MAC_ADDRESS_VALUE, macAddress);
				wait(2);
				test.log(LogStatus.INFO, "Updating Mac Address field for : " + rpdNode + " with Value  : " + macAddress  + test.addScreenCapture(addScreenshot()));
				click(UPDATE);
			}else
			{
				String macAddressInGUI = getText(MAC_ADDRESS_VALUE); 
				test.log(LogStatus.INFO, "Mac Address field in GUI for : " + rpdNode + " has Value  : " + macAddressInGUI  + test.addScreenCapture(addScreenshot()));
				if (macAddressInGUI.equalsIgnoreCase(macAddress))
				{
					System.out.println("RPD Node : " + rpdNode + " has expected MacAddress : " + macAddressInGUI + " in the GUI.");
					test.log(LogStatus.PASS, "RPD Node :  " + rpdNode + " has expected MacAddress : " + macAddressInGUI + " in the GUI.");
					Assert.assertTrue(true, "RPD Node has expected Mac Address");
				}else {
					//fail the TC
					System.out.println("RPD Node does not have expected Mac Address.");
					test.log(LogStatus.FAIL, "RPD Node " + rpdNode + " does not have expected MacAddress" + macAddressInGUI + " in the GUI. Please check.");
					Assert.assertTrue(false, "RPD Node does not have expected Mac Address. Please check.");
				}
				
			}
			wait(1);
			log.debug("Leaving updateMacAddress");
		} catch (Exception e) {
		log.error("Error in updating the Mac Address:" + e.getMessage());
		test.log(LogStatus.ERROR, e);
		Assert.assertTrue(false, e.getMessage());
		}
	}
	
	
	public void updateDCCAPValue(String cinNtwElement, String ethernetPort, String ccapValue) {
		try {
			log.debug("Entering updateDCCAPValue for cinNtwElement : " + cinNtwElement + " with ethernetPort : " + ethernetPort + " with dCCAP Value : " + ccapValue);
			wait(1);
			inputText(SEARCH_BOX, cinNtwElement);
			wait(3);
			getWebElement(SEARCH_BOX).sendKeys(Keys.ENTER);
			wait(2);
			clickDynamic("span", cinNtwElement);
			wait(2);
			click(PORTS_TAB);
			wait(2);
			clickDynamic("span", ethernetPort);
			wait(2);
			click(PARAMETERS_TAB);
			wait(2);
			click(EDIT);
			wait(2);
			inputText(DCCAP_VALUE, ccapValue);
			wait(2);
			test.log(LogStatus.INFO, "Updating DCCAP field for : " + cinNtwElement + " with Value  : " + ccapValue  + test.addScreenCapture(addScreenshot()));
			click(UPDATE);
			wait(1);
			log.debug("Leaving updateDCCAPValue");
		} catch (Exception e) {
		log.error("Error in updating the DCCAP Value:" + e.getMessage());
		test.log(LogStatus.ERROR, e);
		Assert.assertTrue(false, e.getMessage());
		}
	}
	
	
	public void validateAdminStatus(String ethernetObjID, String statusToBe) {
		try {
			log.debug("Entering validateAdminStatus for ethernetObjID : " + ethernetObjID);
			wait(1);
			navigate(Utility.getValueFromPropertyFile("basepage_url_Oss") + "ncobject.jsp?id=" + ethernetObjID);
			wait(2);
			click(PARAMETERS_TAB);
			wait(2);
			/*click(EDIT);
			wait(1);*/
			String actualAdminStatus = getText(ADMIN_STATUS_VALUE);
			wait(1);
			test.log(LogStatus.INFO, "Administrative Status Value In GUI is : " + actualAdminStatus + test.addScreenCapture(addScreenshot()));
			if ( actualAdminStatus.equalsIgnoreCase(statusToBe))
			{
				//pass the TC
				System.out.println("Administrative Status has been updated as expected in the GUI.");
				test.log(LogStatus.PASS, "Administrative Status has been updated as expected in the GUI.");
				Assert.assertTrue(true, "Administrative Status has been updated as expected in the GUI.");
			}else {
				//fail the TC
				System.out.println("Administrative Status has not updated as expected in the GUI. Please check.");
				test.log(LogStatus.FAIL, "Administrative Status has not updated as expected in the GUI. Please check.");
				Assert.assertTrue(false, "Administrative Status has not updated as expected in the GUI. Please check.");
			}
			wait(1);
			log.debug("Leaving validateAdminStatus");
		} catch (Exception e) {
		log.error("Error in validating the Admninistrative Status :" + e.getMessage());
		test.log(LogStatus.ERROR, e);
		Assert.assertTrue(false, e.getMessage());
		}
	}
	
	
	public void validateResponse(String actualValue, String expectedValue, String attributeType) {
		try {
			log.debug("Entering validateResponse with actualValue : " + actualValue + " expected Value : " + expectedValue);
			wait(1);
			if ( actualValue.equalsIgnoreCase(expectedValue))
			{
				//pass the TC
				test.log(LogStatus.PASS, attributeType + "has been matched as expected.");
				Assert.assertTrue(true, attributeType + "has been matched as expected.");
			}else {
				//fail the TC
				test.log(LogStatus.FAIL, attributeType + "has not matched as expected. Please check.");
				Assert.assertTrue(false, attributeType + "has not matched as expected. Please check.");
			}
			wait(1);
			log.debug("Leaving validateResponse");
		} catch (Exception e) {
		log.error("Error in validating the Response :" + e.getMessage());
		test.log(LogStatus.ERROR, e);
		Assert.assertTrue(false, e.getMessage());
		}
	}
	
	
	public void removeRPDNodeAndDelete(String circuitName, String rpdNode) {
		try {
			log.debug("Entering removeRPDNodeAndDelete for circuit  : " + circuitName + " with rpd node: " +  rpdNode);
			wait(1);
			click(PATH_ELEMENTS_TAB);  
			wait(2);
			/*click(EDIT_BUTTON_ELEMENT2); 
			wait(1);
			inputText(ELEMENT2_CARRIER_INPUT, ""); 
			wait(2);
			click(SAVE_BTN);*/
			click(PATH_ELEMENT2);
			wait(2);
			click(RPD_NODE_INPUT);
			wait(2);
			clickNthElement(RELEASE_BTN, 0);
			wait(2);
			switchToAlert();
			wait(2);
			clickDynamic("span", circuitName);
			wait(2);
			test.log(LogStatus.INFO, "Removed RPD Node link to the Circuit: " + circuitName  + test.addScreenCapture(addScreenshot()));
			wait(1);
			click(TRANSMISSION_CKT_FOLDER);
			wait(2);
			clickInputCheckBox(circuitName);
			wait(2);
			click(TAB_DELETE_BTN);
			wait(2);
			removeTab();
			wait(2);
			clickNthElement(DELETE_BTN, 1);
			test.log(LogStatus.INFO, "Deleting the Circuit : " + circuitName  + test.addScreenCapture(addScreenshot()));
			wait(3);
			List<WebElement> getCircuit = getDynamicXpathElementInList(circuitName, 0);
			if (getCircuit.size() == 0 )
			{	
				test.log(LogStatus.PASS, "Deletion of Circuit worked as expected as shown in snapshot below: " + test.addScreenCapture(addScreenshot()));
				Assert.assertTrue(true, "Deletion of Circuit worked as expected.");

			}else {
				test.log(LogStatus.FAIL, "Deletion of Circuit has not worked as expected, Please check." + test.addScreenCapture(addScreenshot()));
				Assert.assertTrue(false, "Deletion of Circuit has not worked as expected, Please check.");
			}
			log.debug("Leaving removeRPDNodeAndDelete");
		} catch (Exception e) {
		log.error("Error in removing the RPD Node link to the Circuit :" + e.getMessage());
		test.log(LogStatus.ERROR, e);
		Assert.assertTrue(false, e.getMessage());
		}
	}
	
	public String createNewSVLANorINVLAN(String poolOption, int vlanId, String vlanType) {
		String poolCreated = null;
		try {
			log.debug("Entering createNewSVLAN with poolOption  : " + poolOption + " with vlanId: " +  vlanId);
			wait(1);
			if ( vlanType.equalsIgnoreCase("SVLAN")) {
				click(NEW_VLAN_BTN);
				wait(3);
				removeTab();
				wait(1);
				click(VLAN_POOL_DRP_DWN);	
				wait(2);
				clickDynamic("div", poolOption);
				wait(1);
				click(VLAN_ID_NAME);
				wait(2);
				inputText(VLAN_ID_INPUT, String.valueOf(vlanId));
				wait(2);
				test.log(LogStatus.INFO, "Entered New SVLAN Details as shown in snapshot below : " + test.addScreenCapture(addScreenshot()));
				click(ADD_BTN);
				wait(2);
				poolCreated = "VLAN" + String.valueOf(vlanId);
				System.out.println("Pool : " + poolCreated);
			}
			else {
				clickDynamic("span", poolOption);
				wait(2);
				click(NEW_INNER_VLAN_BTN);
				wait(3);
				removeTab();
				wait(1);
				inputText(INNER_VLAN_IDS_INPUT, String.valueOf(vlanId));
				wait(2);
				test.log(LogStatus.INFO, "Entered New SVLAN Details as shown in snapshot below : " + test.addScreenCapture(addScreenshot()));
				click(ADD_BTN);
				wait(2);
				poolCreated = "INVLAN " + poolOption.replaceAll("[^0-9]", "") + ":" +  String.valueOf(vlanId);
				System.out.println("Pool : " + poolCreated);
			}
			//Validating VLAN creation 
			if ( !isElementPresent(ALREADY_EXIST_ERROR)) {

				List<WebElement> getpoolCreatedList = getDynamicXpathElementInList(poolCreated, 0);
				if (getpoolCreatedList.size() != 0 )
				{	
					test.log(LogStatus.PASS, "New VLAN is created Successfully as shown in snapshot below: " + test.addScreenCapture(addScreenshot()));
					Assert.assertTrue(true, "New VLAN is created Successfully as expected.");

				}else {
					test.log(LogStatus.FAIL, "New VLAN was not created Successfully , Please check." + test.addScreenCapture(addScreenshot()));
					Assert.assertTrue(false, "New VLAN was not created Successfully , Please check.");
				}
			}else
			{
				test.log(LogStatus.FAIL, "New VLAN was not created Successfully , Please check." + test.addScreenCapture(addScreenshot()));
				Assert.assertTrue(false, "New VLAN was not created Successfully , Please check.");
			}

		} catch (Exception e) {
			log.error("Error in creating new SVLAN:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			Assert.assertTrue(false, e.getMessage());
		}
		return poolCreated;
	}
	
	public void deleteNewSVLANorINVLAN(String poolCreated, String vlanType) {
		try {
			log.debug("Entering deleteNewSVLANorINVLAN with poolCreated : " + poolCreated );
			wait(1);
			clickInputCheckBox(poolCreated);
			wait(2);
			if ( vlanType.equalsIgnoreCase("SVLAN")) { 
			clickNthElement(TAB_DELETE_BTN, 0);
			wait(2);
			removeTab();
			wait(1);
			clickNthElement(DELETE_BTN, 1);
			test.log(LogStatus.INFO, "Deleting the VLAN created as shown in snapshot below: " + test.addScreenCapture(addScreenshot()));
			}else {
				clickNthElement(TAB_DELETE_BTN, 2);
				wait(2);
				removeTab();
				wait(1);
				clickNthElement(DELETE_BTN, 2);
				test.log(LogStatus.INFO, "Deleting the VLAN created as shown in snapshot below: " + test.addScreenCapture(addScreenshot()));
			}
			wait(2);
			List<WebElement> getpoolCreatedList = getDynamicXpathElementInList(poolCreated, 0);
			if (getpoolCreatedList.size() == 0 )
			{	
				test.log(LogStatus.PASS, "New VLAN was deleted Successfully as shown in snapshot below: " + test.addScreenCapture(addScreenshot()));
				Assert.assertTrue(true, "New VLAN was deleted Successfully as expected.");

			}else {
				test.log(LogStatus.FAIL, "New VLAN was not deleted Successfully , Please check." + test.addScreenCapture(addScreenshot()));
				Assert.assertTrue(false, "New VLAN was not deleted Successfully , Please check.");
			}
			log.debug("Leaving deleteNewSVLANorINVLAN");
		} catch (Exception e) {
		log.error("Error in Deleting VLAN :" + e.getMessage());
		test.log(LogStatus.ERROR, e);
		Assert.assertTrue(false, e.getMessage());
		}
	}
	
	
	
}