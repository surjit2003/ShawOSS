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

public class CMTS extends SeleniumTestUp{
	
	//public ExtentReports extent=ExtentReportManager.getInstance();
	
	LandingPage landing=null;
	DnRFlowPage dnrFlow =null;
	JDBCValidator jdbc=null;
	Logger log=Logger.getLogger(CMTS.class);
	SoftAssert softAssert=new SoftAssert();
	ArrayList<String> valuesToPassForValidation=new ArrayList<String>();
	
	/* 
	 * Test Scenario : To run Delete Script for Integration : CMTS 
	 */
	
	@DataProvider
	public Object[][] getData18(){
		return Utility.getData(xls,"TC18");
	}

	@Test (dataProvider="getData18", priority = 18)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Run_DeleScript_CMTS(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Run_DeleScript_CMTS");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest0=extent.startTest("Run Delete Script for CMTS");
			test.appendChild(childTest0);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			dnrFlow.runDeleteScript("CMTS");
			
			log.debug("Leaving DnR_Run_DeleScript_CMTS");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Run_DeleScript_CMTS:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* 
	 * Test Scenario : To validate the integration interface catalogue parameters for Integration : CMTS 
	 */
	
	@DataProvider
	public Object[][] getData19(){
		return Utility.getData(xls,"TC19");
	}

	@Test (dataProvider="getData19", priority = 19)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Config_CMTS(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Config_CMTS");
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
			
			ExtentTest childTest1=extent.startTest("Navigate to Documents Integration Interface and Validate the parameters");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToIntegrationInterface("CMTS", fetchLoc, storageLoc, null);
			
			log.debug("Leaving DnR_Validate_Config_CMTS");

		}catch(Exception e)
		{
			log.error("Error in DnR_Validate_Config_CMTS:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To run the generate scripts required for Integration : CMTS 
	 */
	
	@DataProvider
	public Object[][] getData20(){
		return Utility.getData(xls,"TC20");
	}
	
	
	@Test (dataProvider="getData20", priority = 20)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_GenerateScripts_CMTS(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_GenerateScripts_CMTS");
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
			dnrFlow.navigateToDataTransition("CMTS");
			
			
			log.debug("Leaving DnR_GenerateScripts_CMTS");
			}catch(Exception e)
			{
				log.error("Error in DnR_GenerateScripts_CMTS:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To run the Data Export Session for Integration : CMTS 
	 */
	
	@DataProvider
	public Object[][] getData21(){
		return Utility.getData(xls,"TC21");
	}

	@Test (dataProvider="getData21", priority = 21)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_DataExport_Run_CMTS(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_DataExport_Run_CMTS");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			
			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest1=extent.startTest("Trigger the Start Session of Data Export");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("CMTS", "DE", "1", "SingleRun", "No");
			
			ExtentTest childTest2=extent.startTest("Validate the Zip File Creation after Data Export");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.validatePuttyPostCondition("CMTS", 1, "None");
			
			log.debug("Leaving DnR_DataExport_Run_CMTS");

		}catch(Exception e)
		{
			log.error("Error in DnR_DataExport_Run_CMTS:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To run the Reconciliation Session for Integration : CMTS 
	 */
	
	@DataProvider
	public Object[][] getData22(){
		return Utility.getData(xls,"TC22");
	}
	
	
	@Test (dataProvider="getData22", priority = 22, dependsOnMethods = "DnR_GenerateScripts_CMTS")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Recon_Run_CMTS(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Recon_Run_CMTS");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String fetchLoc = data.get("FETCH_LOC");
			
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
			dnrFlow.navigateToIntegrationInterface("CMTS", fetchLoc, null, null);
			dnrFlow.navigateToDataFlow("CMTS", "Recon", "1", "SingleRun", "No");
			
			
			log.debug("Leaving DnR_Recon_Run_CMTS");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Recon_Run_CMTS:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	/* 
	 * Test Scenario : To Validate Data Points after Reconciliation for Integration : CMTS 
	 */
	
	@DataProvider
	public Object[][] getData23(){
		return Utility.getData(xls,"TC23");
	}
	
	@Test (dataProvider="getData23", priority = 23, dependsOnMethods = "DnR_Recon_Run_CMTS")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Data_CMTS(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Data_CMTS");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest1=extent.startTest("Validate IDB SDB Counts");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.validateIDBSDBTableCount("CMTS");
			
			ExtentTest childTest2=extent.startTest("Verify FallOuts");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			String sessionId = dnrFlow.verifyFallOuts("CMTS");   
			
			ExtentTest childTest3=extent.startTest("Capture Recon Report Actions");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.runReconQuery("CMTS", "FirstRun", sessionId);
			
			log.debug("Leaving DnR_Validate_Data_CMTS");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Data_CMTS:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To Validate Update Scenario after Reconciliation for Integration : CMTS 
	 */
	
	@DataProvider
	public Object[][] getData24(){
		return Utility.getData(xls,"TC24");
	}
	
	@Test (dataProvider="getData24",priority = 24)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Update_CMTS(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Update_CMTS");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String dnsName = data.get("DNS_NAME");
			String fetchLoc = data.get("FETCH_LOC");
			String storageLoc = data.get("STORAGE_LOC");
			
			ExtentTest childTest1=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:62430 and set the Fetch Location");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			landing.LoginOSS();
			dnrFlow.navigateToIntegrationInterface("CMTS", fetchLoc, storageLoc, null);

			
			ExtentTest childTest2=extent.startTest("Run Initial Recon and get the intial actions before performing Update");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("CMTS", "Recon", "1", "SingleRun", "No");
			String objectId = dnrFlow.fetchObjIdFromDns(dnsName);
			dnrFlow.verifyActionsforUpdate("CMTS", objectId);
			
			ExtentTest childTest3=extent.startTest("Update the Parameter for the particular object");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.navigatetoUpdate(objectId, "CMTS", null);
					
			ExtentTest childTest4=extent.startTest("Run Recon again and verify the action of the object");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("CMTS", "Recon", "1", "SingleRun", "No");
			int actionCount = dnrFlow.verifyActionsforUpdate("CMTS", objectId);
			dnrFlow.validateActions("Update" , actionCount);
			
			log.debug("Leaving DnR_Validate_Update_CMTS");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Update_CMTS:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To Validate Delete Scenario after Reconciliation for Integration : CMTS 
	 */
	
	@DataProvider
	public Object[][] getData25(){
		return Utility.getData(xls,"TC25");
	}
	
	@Test (dataProvider="getData25",priority = 25)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Delete_CMTS(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Delete_CMTS");
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
			dnrFlow.navigateToIntegrationInterface("CMTS", fetchLoc, storageLoc, null);

			
			ExtentTest childTest2=extent.startTest("Run Initial Recon and get the intial actions before performing Delete");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("CMTS", "Recon", "1", "SingleRun", "No");
			String objectId = dnrFlow.fetchObjIdFromDns(dnsName);
			String objectName = dnrFlow.verifyActionsBeforeDelete("CMTS", objectId);
			
			ExtentTest childTest3=extent.startTest("Perform delete operation");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.performDeletion("CMTS", objectId);
					
			ExtentTest childTest4=extent.startTest("Run Recon again and verify the action of the object");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("CMTS", "Recon", "1", "SingleRun", "No");
			int actionCount = dnrFlow.verifyActionsAfterDelete("CMTS", objectName, "nc_rec_obj9143308250013749189");
			dnrFlow.validateActions("Delete" , actionCount);
			
			log.debug("Leaving DnR_Validate_Delete_CMTS");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Delete_CMTS:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
}
