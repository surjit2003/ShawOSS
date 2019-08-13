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

public class FDB extends SeleniumTestUp{
	
	//public ExtentReports extent=ExtentReportManager.getInstance();
	
	LandingPage landing=null;
	DnRFlowPage dnrFlow =null;
	JDBCValidator jdbc=null;
	Logger log=Logger.getLogger(FDB.class);

	/* 
	 * Test Scenario : To run Delete Script for Integration : FDB 
	 */
	
	@DataProvider
	public Object[][] getData50(){
		return Utility.getData(xls,"TC50");
	}

	@Test (dataProvider="getData50", priority = 50)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Run_DeleScript_FDB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Run_DeleScript_FDB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest0=extent.startTest("Run Delete Script for FDB");
			test.appendChild(childTest0);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			dnrFlow.runDeleteScript("FDB");
			
			log.debug("Leaving DnR_Run_DeleScript_FDB");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Run_DeleScript_FDB:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To validate the integration interface catalogue parameters for Integration : FDB 
	 */
	
	@DataProvider
	public Object[][] getData51(){
		return Utility.getData(xls,"TC51");
	}
	
	@Test (dataProvider="getData51", priority = 51)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Config_FDB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Config_FDB");
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
			dnrFlow.navigateToIntegrationInterface("FDB", fetchLoc, storageLoc, null);
			
			log.debug("Leaving DnR_Validate_Config_FDB");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Validate_Config_FDB:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To run the generate scripts required for Integration : FDB 
	 */
	
	@DataProvider
	public Object[][] getData52(){
		return Utility.getData(xls,"TC52");
	}
	
	
	@Test (dataProvider="getData52", priority = 52)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_GenerateScripts_FDB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_GenerateScripts_FDB");
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
			dnrFlow.navigateToDataTransition("FDB");
			
			log.debug("Leaving DnR_GenerateScripts_FDB");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_GenerateScripts_FDB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To run the Data Export Session for Integration : FDB 
	 */
	
	@DataProvider
	public Object[][] getData53(){
		return Utility.getData(xls,"TC53");
	}
	
	
	@Test (dataProvider="getData53", priority = 53)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_DataExport_Run_FDB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_DataExport_Run_FDB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Trigger the Start Session of Data Export");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("FDB", "DE", "1", "SingleRun", "No");
			
			ExtentTest childTest2=extent.startTest("Validate the Zip File Creation after Data Export");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.validatePuttyPostCondition("FDB", 1, "None");
			
			log.debug("Leaving DnR_DataExport_Run_FDB");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_DataExport_Run_FDB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To run the Reconciliation Session for Integration : FDB 
	 */
	
	@DataProvider
	public Object[][] getData54(){
		return Utility.getData(xls,"TC54");
	}
	
	@Test (dataProvider="getData54", priority = 54, dependsOnMethods = "DnR_GenerateScripts_FDB")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Recon_Run_FDB(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Recon_Run_FDB");
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
			
			ExtentTest childTest1=extent.startTest("Trigger the Start Session of Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			if ( exportWorks.equalsIgnoreCase("No") && (fetchLoc != null) )
			dnrFlow.navigateToIntegrationInterface("FDB", fetchLoc, storageLoc, null);
			dnrFlow.navigateToDataFlow("FDB", "Recon", "1", "SingleRun", "No");
			
			log.debug("Leaving DnR_Recon_Run_FDB");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Recon_Run_FDB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	/* 
	 * Test Scenario : To Validate Data Points after Reconciliation for Integration : FDB 
	 */
	
	@DataProvider
	public Object[][] getData55(){
		return Utility.getData(xls,"TC55");
	}
	
	@Test (dataProvider="getData55", priority = 55, dependsOnMethods = "DnR_Recon_Run_FDB")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Data_FDB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Data_FDB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Validate IDB SDB Counts");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.validateIDBSDBTableCount("FDB");
			
			ExtentTest childTest3=extent.startTest("Verify FallOuts");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			String sessionId = dnrFlow.verifyFallOuts("FDB");
					
			ExtentTest childTest4=extent.startTest("Capture Recon Report Actions");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			dnrFlow.runReconQuery("FDB","FirstRun", sessionId);
			
			log.debug("Leaving DnR_Validate_Data_FDB");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Data_FDB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To Validate Update Scenario after Reconciliation for Integration : FDB 
	 */
	
	@DataProvider
	public Object[][] getData56(){
		return Utility.getData(xls,"TC56");
	}
	
	@Test (dataProvider="getData56",priority = 56)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Update_FDB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Update_FDB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String dnsName = data.get("DNS_NAME");
			String fetchLoc = data.get("FETCH_LOC");
			String storageLoc = data.get("STORAGE_LOC");


			ExtentTest childTest1=extent.startTest("Login to the Server and set the Fetch Location");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			landing.LoginOSS();
			dnrFlow.navigateToIntegrationInterface("FDB", fetchLoc, storageLoc, null);
			
			ExtentTest childTest2=extent.startTest("Run Initial Recon and get the intial actions before performing Update");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("FDB", "Recon", "1", "SingleRun", "No");
			String objectId = dnrFlow.fetchObjIdFromDns(dnsName);
			dnrFlow.verifyActionsforUpdate("FDB", objectId);
			
			ExtentTest childTest3=extent.startTest("Update the Parameter for the particular object");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.navigatetoUpdate(objectId, "FDB", null);
					
			ExtentTest childTest4=extent.startTest("Run Recon again and verify the action of the object");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("FDB", "Recon", "1", "SingleRun", "No");
			int actionCount = dnrFlow.verifyActionsforUpdate("FDB", objectId);
			dnrFlow.validateActions("Update" , actionCount);
			
			log.debug("Leaving DnR_Validate_Update_FDB");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Update_FDB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To Validate Delete Scenario after Reconciliation for Integration : FDB 
	 */
	
	@DataProvider
	public Object[][] getData57(){
		return Utility.getData(xls,"TC57");
	}
	
	@Test (dataProvider="getData57",priority = 57)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Delete_FDB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Delete_FDB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String dnsName = data.get("DNS_NAME");
			String fetchLoc = data.get("FETCH_LOC");
			String storageLoc = data.get("STORAGE_LOC");


			ExtentTest childTest1=extent.startTest("Login to the Server and set the Fetch Location");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			landing.LoginOSS();
			dnrFlow.navigateToIntegrationInterface("FDB", fetchLoc, storageLoc, null);
			
			ExtentTest childTest2=extent.startTest("Run Initial Recon and get the intial actions before performing Delete");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("FDB", "Recon", "1", "SingleRun", "No");
			String objectId = dnrFlow.fetchObjIdFromDns(dnsName);
			String objectName = dnrFlow.verifyActionsBeforeDelete("FDB", objectId);
			
			ExtentTest childTest3=extent.startTest("Perform delete operation");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.performDeletion("FDB", objectId);
					
			ExtentTest childTest4=extent.startTest("Run Recon again and verify the action of the object");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("FDB", "Recon", "1", "SingleRun", "No");
			int actionCount = dnrFlow.verifyActionsAfterDelete("FDB", objectName, "nc_rec_obj9142795970013432508");
			dnrFlow.validateActions("Delete" , actionCount);
			
			log.debug("Leaving DnR_Validate_Delete_FDB");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Delete_FDB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}

}
