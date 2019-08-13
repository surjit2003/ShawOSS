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

public class CIN extends SeleniumTestUp{
	
	//public ExtentReports extent=ExtentReportManager.getInstance();
	
	LandingPage landing=null;
	DnRFlowPage dnrFlow =null;
	JDBCValidator jdbc=null;
	Logger log=Logger.getLogger(CIN.class);
	SoftAssert softAssert=new SoftAssert();
	ArrayList<String> valuesToPassForValidation=new ArrayList<String>();
	
	/* 
	 * Test Scenario : To run Delete Script for Integration : CIN 
	 */
	
	@DataProvider
	public Object[][] getData66(){
		return Utility.getData(xls,"TC66");
	}

	@Test (dataProvider="getData66", priority = 66)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Run_DeleScript_CIN(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Run_DeleScript_CIN");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest1=extent.startTest("Run Delete Script for CIN");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.runDeleteScript("CIN");
			
			log.debug("Leaving DnR_Run_DeleScript_CIN");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Run_DeleScript_CIN:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To validate the integration interface catalogue parameters for Integration : CIN 
	 */
	
	@DataProvider
	public Object[][] getData67(){
		return Utility.getData(xls,"TC67");
	}
	
	@Test (dataProvider="getData67", priority = 67)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Config_CIN(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Config_CIN");
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
			dnrFlow.navigateToIntegrationInterface("CIN", fetchLoc, storageLoc, null);
			
			log.debug("Leaving DnR_Validate_Config_CIN");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Validate_Config_CIN:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To run the generate scripts required for Integration : CIN 
	 */
	
	@DataProvider
	public Object[][] getData68(){
		return Utility.getData(xls,"TC68");
	}
	
	
	@Test (dataProvider="getData68", priority = 68)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_GenerateScripts_CIN(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_GenerateScripts_CIN");
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
			dnrFlow.navigateToDataTransition("CIN");
			
			log.debug("Leaving DnR_GenerateScripts_CIN");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_GenerateScripts_CIN:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To run the Data Export Session for Integration : CIN 
	 */
	
	@DataProvider
	public Object[][] getData69(){
		return Utility.getData(xls,"TC69");
	}
	
	
	@Test (dataProvider="getData69", priority = 69)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_DataExport_Run_CIN(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_DataExport_Run_CIN");
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
			dnrFlow.navigateToDataFlow("CIN", "DE", "1", "SingleRun", "No");
			
			ExtentTest childTest2=extent.startTest("Validate the Zip File Creation after Data Export");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.validatePuttyPostCondition("CIN", 1, "None");
			
			log.debug("Leaving DnR_DataExport_Run_CIN");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_DataExport_Run_CIN:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To run the Reconciliation Session for Integration : CIN 
	 */
	
	@DataProvider
	public Object[][] getData70(){
		return Utility.getData(xls,"TC70");
	}
	
	
	@Test (dataProvider="getData70", priority = 70, dependsOnMethods = "DnR_GenerateScripts_CIN")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Recon_Run_CIN(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Recon_Run_CIN");
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
			dnrFlow.navigateToIntegrationInterface("CIN", fetchLoc, null, null);
			dnrFlow.navigateToDataFlow("CIN", "Recon", "1", "SingleRun", "No");
			
			log.debug("Leaving DnR_Recon_Run_CIN");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Recon_Run_CIN:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	/* 
	 * Test Scenario : To Validate Data Points after Reconciliation for Integration : CIN 
	 */
	
	@DataProvider
	public Object[][] getData71(){
		return Utility.getData(xls,"TC71");
	}
	
	@Test (dataProvider="getData71",priority = 71, dependsOnMethods = "DnR_Recon_Run_CIN")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Data_CIN(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Data_CIN");
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
			dnrFlow.validateIDBSDBTableCount("CIN");
			
			ExtentTest childTest3=extent.startTest("Verify FallOuts");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			String sessionId = dnrFlow.verifyFallOuts("CIN");
					
			ExtentTest childTest4=extent.startTest("Capture Recon Report Actions");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			dnrFlow.runReconQuery("CIN","FirstRun", sessionId);
			
			log.debug("Leaving DnR_Validate_Data_CIN");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Data_CIN:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To Validate Update Scenario after Reconciliation for Integration : CIN 
	 */
	
	@DataProvider
	public Object[][] getData72(){
		return Utility.getData(xls,"TC72");
	}
	
	@Test (dataProvider="getData72",priority = 72)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Update_CIN(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Update_CIN");
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
			dnrFlow.navigateToIntegrationInterface("CIN", fetchLoc, storageLoc, null);
			
			ExtentTest childTest2=extent.startTest("Run Initial Recon and get the intial actions before performing Update");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			String sessionId = dnrFlow.navigateToDataFlow("CIN", "Recon", "1", "SingleRun", "No");
			String objectId = dnrFlow.fetchObjIdFromDns(dnsName);
			dnrFlow.verifyActionsWithTableforUpdate("CIN", objectId, sessionId);
			
			ExtentTest childTest3=extent.startTest("Update the Parameter for the particular object");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.navigatetoUpdate(objectId, "CIN", null);
					
			ExtentTest childTest4=extent.startTest("Run Recon again and verify the action of the object");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			sessionId = dnrFlow.navigateToDataFlow("CIN", "Recon", "1", "SingleRun", "No");
			int actionCount = dnrFlow.verifyActionsWithTableforUpdate("CIN", objectId, sessionId);
			dnrFlow.validateActions("Update" , actionCount);
			
			log.debug("Leaving DnR_Validate_Update_CIN");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Update_CIN:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To Validate Delete Scenario after Reconciliation for Integration : CIN 
	 */
	
	@DataProvider
	public Object[][] getData73(){
		return Utility.getData(xls,"TC73");
	}
	
	@Test (dataProvider="getData73",priority = 73)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Delete_CIN(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Delete_CIN");
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
			dnrFlow.navigateToIntegrationInterface("CIN", fetchLoc, storageLoc, null);
			
			ExtentTest childTest2=extent.startTest("Run Initial Recon and get the intial actions before performing Delete");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			String sessionId = dnrFlow.navigateToDataFlow("CIN", "Recon", "1", "SingleRun", "No");
			String objectId = dnrFlow.fetchObjIdFromDns(dnsName);
			String objectName = dnrFlow.verifyActionsWithTableBeforeDelete("CIN", objectId, sessionId);
			
			ExtentTest childTest3=extent.startTest("Perform delete operation");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.performDeletion("CIN", objectId);
					
			ExtentTest childTest4=extent.startTest("Run Recon again and verify the action of the object");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			sessionId = dnrFlow.navigateToDataFlow("CIN", "Recon", "1", "SingleRun", "No");
			int actionCount = dnrFlow.verifyActionsWithTableAfterDelete("CIN", objectName, sessionId);
			dnrFlow.validateActions("Delete" , actionCount);
			
			log.debug("Leaving DnR_Validate_Delete_CIN");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Delete_CIN:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}

}