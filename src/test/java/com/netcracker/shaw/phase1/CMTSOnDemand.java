package com.netcracker.shaw.phase1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.netcracker.shaw.at_shaw_sd.jdbc.JDBCValidator;
import com.netcracker.shaw.at_shaw_sd.pageobject.LandingPage;
import com.netcracker.shaw.at_shaw_sd.pageobject.DnRFlowPage;
import com.netcracker.shaw.at_shaw_sd.util.Utility;
import com.netcracker.shaw.report.ExtentReportManager;
import com.netcracker.shaw.report.ExtentTestManager;
import com.netcracker.shaw.setup.SeleniumTestUp;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CMTSOnDemand extends SeleniumTestUp{

	LandingPage landing=null;
	DnRFlowPage dnrFlow =null;
	JDBCValidator jdbc=null;
	Logger log=Logger.getLogger(CMTSOnDemand.class);
	
	/* 
	 * Test Scenario : To run Delete Script for Integration : CMTS-OnDemand 
	 */
	
	@DataProvider
	public Object[][] getData26(){
		return Utility.getData(xls,"TC26");
	}

	@Test (dataProvider="getData26", priority = 26)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Run_DeleScript_CMTS_OnDemand(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Run_DeleScript_CMTS_OnDemand");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest1=extent.startTest("Run Delete Script for CMTS-OnDemand");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.runDeleteScript("CMTS-OnDemand");
			
			log.debug("Leaving DnR_Run_DeleScript_CMTS_OnDemand");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Run_DeleScript_CMTS_OnDemand:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* 
	 * Test Scenario : To validate the integration interface catalogue parameters for Integration : CMTS-OnDemand 
	 */
	
	@DataProvider
	public Object[][] getData27(){
		return Utility.getData(xls,"TC27");
	}
	
	@Test (dataProvider="getData27", priority = 27)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Config_CMTS_OnDemand(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Config_CMTS_OnDemand");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String fetchLoc = data.get("FETCH_LOC");
			String storageLoc = data.get("STORAGE_LOC");

			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to Documents Integration Interface and Validate the parameters");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.navigateToIntegrationInterface("CMTS-OnDemand", fetchLoc, storageLoc, null);
			
			log.debug("Leaving DnR_Validate_Config_CMTS_OnDemand");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Validate_Config_CMTS_OnDemand:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To run the generate scripts required for Integration : CMTS-OnDemand 
	 */
	
	@DataProvider
	public Object[][] getData28(){
		return Utility.getData(xls,"TC28");
	}
	
	
	@Test (dataProvider="getData28", priority = 28)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_GenerateScripts_CMTS_OnDemand(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_GenerateScripts_CMTS_OnDemand");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Run Pre-Requisite Scripts required for Data Export and Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataTransition("CMTS-OnDemand");
			
			log.debug("Leaving DnR_GenerateScripts_CMTS_OnDemand");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_GenerateScripts_CMTS_OnDemand:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To run the Data Export Session for Integration : CMTS-OnDemand 
	 */
	
	@DataProvider
	public Object[][] getData29(){
		return Utility.getData(xls,"TC29");
	}

	@Test (dataProvider="getData29", priority = 29, dependsOnMethods = "DnR_GenerateScripts_CMTS_OnDemand")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_DataExport_Run_CMTS_OnDemand(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_DataExport_Run_CMTS_OnDemand");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String ipAddress = data.get("IP_ADDRESS");
			String fetchLoc = data.get("FETCH_LOC");
			
			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest1=extent.startTest("Trigger the Start Session of Data Export and Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			if ( exportWorks.equalsIgnoreCase("No") && (fetchLoc != null) )
			dnrFlow.navigateToIntegrationInterface("CMTS-OnDemand", fetchLoc, null, null);
			dnrFlow.navigateToDataFlow("CMTS-OnDemand", "DR", ipAddress, "SingleRun", "No");
			
			log.debug("Leaving DnR_DataExport_Run_CMTS_OnDemand");

		}catch(Exception e)
		{
			log.error("Error in DnR_DataExport_Run_CMTS_OnDemand:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To run the Reconciliation Session for Integration : CMTS-OnDemand 
	 */

	@DataProvider
	public Object[][] getData30(){
		return Utility.getData(xls,"TC30");
	}
	
	
	@Test (dataProvider="getData30", priority = 30)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Verify_IP_Folder_CMTS_OnDemand(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Verify_IP_Folder_CMTS_OnDemand");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String ipAddress = data.get("IP_ADDRESS");
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Validate the Zip folder Creation for CMTS-OnDemand");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.validatePuttyPostCondition("CMTS-OnDemand", 1, ipAddress);
			
			log.debug("Leaving DnR_Verify_IP_Folder_CMTS_OnDemand");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Verify_IP_Folder_CMTS_OnDemand:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	/* 
	 * Test Scenario : To Validate Data Points after Reconciliation for Integration : CMTS-OnDemand 
	 */
	
	@DataProvider
	public Object[][] getData31(){
		return Utility.getData(xls,"TC31");
	}
	
	@Test (dataProvider="getData31", priority = 31, dependsOnMethods = "DnR_DataExport_Run_CMTS_OnDemand" )
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Data_CMTS_OnDemand(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Data_CMTS_OnDemand");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest2=extent.startTest("Validate IDB SDB Counts");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.validateIDBSDBTableCount("CMTS-OnDemand");
			
			ExtentTest childTest3=extent.startTest("Verify FallOuts");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			String sessionId = dnrFlow.verifyFallOuts("CMTS-OnDemand");
			
			ExtentTest childTest4=extent.startTest("Run Recon Query");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			dnrFlow.runReconQuery("CMTS-OnDemand", "FirstRun", sessionId);
			
			log.debug("Leaving DnR_Validate_Data_CMTS_OnDemand");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Data_CMTS_OnDemand:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To Validate Update Scenario after Reconciliation for Integration : CMTS-OnDemand 
	 */
	
	@DataProvider
	public Object[][] getData32(){
		return Utility.getData(xls,"TC32");
	}
	
	@Test (dataProvider="getData32",priority = 32)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Update_CMTS_OnDemand(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Update_CMTS_OnDemand");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String dnsName = data.get("DNS_NAME");
			String ipAddress = data.get("IP_ADDRESS");
			String fetchLoc = data.get("FETCH_LOC");
			String storageLoc = data.get("STORAGE_LOC");

			ExtentTest childTest1=extent.startTest("Login to the Server and set the Fetch Location");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			landing.LoginOSS();
			dnrFlow.navigateToIntegrationInterface("CMTS-OnDemand", fetchLoc, storageLoc, null);

			
			ExtentTest childTest2=extent.startTest("Run Initial Recon and get the intial actions before performing Update");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			String sessionId = dnrFlow.navigateToDataFlow("CMTS-OnDemand", "Recon", ipAddress, "SingleRun", "No");
			String objectId = dnrFlow.fetchObjIdFromDns(dnsName);
			dnrFlow.verifyActionsWithTableforUpdate("CMTS-OnDemand", objectId, sessionId);
			
			ExtentTest childTest3=extent.startTest("Update the Parameter for the particular object");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.navigatetoUpdate(objectId, "CMTS-OnDemand", null);
					
			ExtentTest childTest4=extent.startTest("Run Recon again and verify the action of the object");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			sessionId = dnrFlow.navigateToDataFlow("CMTS-OnDemand", "Recon", ipAddress, "SingleRun", "No");
			int actionCount = dnrFlow.verifyActionsWithTableforUpdate("CMTS-OnDemand", objectId, sessionId);
			dnrFlow.validateActions("Update" , actionCount);
			
			log.debug("Leaving DnR_Validate_Update_CMTS_OnDemand");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Update_CMTS_OnDemand:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To Validate Delete Scenario after Reconciliation for Integration : CMTS-OnDemand 
	 */
	
	@DataProvider
	public Object[][] getData33(){
		return Utility.getData(xls,"TC33");
	}
	
	@Test (dataProvider="getData33",priority = 33)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Delete_CMTS_OnDemand(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Delete_CMTS_OnDemand");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String dnsName = data.get("DNS_NAME");
			String ipAddress = data.get("IP_ADDRESS");
			String fetchLoc = data.get("FETCH_LOC");
			String storageLoc = data.get("STORAGE_LOC");


			ExtentTest childTest1=extent.startTest("Login to the Server and set the Fetch Location");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			landing.LoginOSS();
			dnrFlow.navigateToIntegrationInterface("CMTS-OnDemand", fetchLoc, storageLoc, null);
			
			ExtentTest childTest2=extent.startTest("Run Initial Recon and get the intial actions before performing Delete");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			String sessionId = dnrFlow.navigateToDataFlow("CMTS-OnDemand", "Recon", ipAddress, "SingleRun", "No");
			String objectId = dnrFlow.fetchObjIdFromDns(dnsName);
			String objectName = dnrFlow.verifyActionsWithTableBeforeDelete("CMTS-OnDemand", objectId, sessionId);
			
			ExtentTest childTest3=extent.startTest("Perform delete operation");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.performDeletion("CMTS-OnDemand", objectId);
					
			ExtentTest childTest4=extent.startTest("Run Recon again and verify the action of the object");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			sessionId = dnrFlow.navigateToDataFlow("CMTS-OnDemand", "Recon", ipAddress, "SingleRun", "No");
			int actionCount = dnrFlow.verifyActionsWithTableAfterDelete("CMTS-OnDemand", objectName, sessionId);
			dnrFlow.validateActions("Delete" , actionCount);
			
			log.debug("Leaving DnR_Validate_Delete_CMTS_OnDemand");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Delete_CMTS_OnDemand:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}

}
