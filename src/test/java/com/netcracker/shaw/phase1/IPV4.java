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

public class IPV4 extends SeleniumTestUp{
	
	//public ExtentReports extent=ExtentReportManager.getInstance();
	
	LandingPage landing=null;
	DnRFlowPage dnrFlow =null;
	JDBCValidator jdbc=null;
	Logger log=Logger.getLogger(IPV4.class);
	
	/* 
	 * Test Scenario : To run Delete Script for Integration : IPV4 
	 */
	
	@DataProvider
	public Object[][] getData58(){
		return Utility.getData(xls,"TC58");
	}

	@Test (dataProvider="getData58", priority = 58)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Run_DeleScript_IPV4(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Run_DeleScript_IPV4");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest0=extent.startTest("Run Delete Script for IPV4");
			test.appendChild(childTest0);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			dnrFlow.runDeleteScript("IPV4");
			
			log.debug("Leaving DnR_Run_DeleScript_IPV4");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Run_DeleScript_IPV4:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To validate the integration interface catalogue parameters for Integration : IPV4 
	 */
	
	@DataProvider
	public Object[][] getData59(){
		return Utility.getData(xls,"TC59");
	}
	
	@Test (dataProvider="getData59", priority = 59)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Config_IPV4(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Config_IPV4");
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
			dnrFlow.navigateToIntegrationInterface("IPV4", fetchLoc, storageLoc, null);
			
			log.debug("Leaving DnR_Validate_Config_IPV4");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Validate_Config_IPV4:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To run the generate scripts required for Integration : IPV4 
	 */
	
	@DataProvider
	public Object[][] getData60(){
		return Utility.getData(xls,"TC60");
	}
	
	
	@Test (dataProvider="getData60", priority = 60)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_GenerateScripts_IPV4(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_GenerateScripts_IPV4");
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
			dnrFlow.navigateToDataTransition("IPV4");
			
			log.debug("Leaving DnR_GenerateScripts_IPV4");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_GenerateScripts_IPV4:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To run the Data Export Session for Integration : IPV4 
	 */
	
	@DataProvider
	public Object[][] getData61(){
		return Utility.getData(xls,"TC61");
	}
	
	
	@Test (dataProvider="getData61", priority = 61)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_DataExport_Run_IPV4(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_DataExport_Run_IPV4");
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
			dnrFlow.navigateToDataFlow("IPV4", "DE", "1", "SingleRun", "No");
			
			ExtentTest childTest2=extent.startTest("Validate the Zip File Creation after Data Export");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.validatePuttyPostCondition("IPV4", 1, "None");
			
			log.debug("Leaving DnR_DataExport_Run_IPV4");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_DataExport_Run_IPV4:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To run the Reconciliation Session for Integration : IPV4 
	 */
	
	@DataProvider
	public Object[][] getData62(){
		return Utility.getData(xls,"TC62");
	}
	
	
	@Test (dataProvider="getData62", priority = 62, dependsOnMethods = "DnR_GenerateScripts_IPV4")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Recon_Run_IPV4(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Recon_Run_IPV4");
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
			dnrFlow.navigateToIntegrationInterface("IPV4", fetchLoc, storageLoc, null);
			dnrFlow.navigateToDataFlow("IPV4", "Recon", "1", "SingleRun", "No");
			
			log.debug("Leaving DnR_Recon_Run_IPV4");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Recon_Run_IPV4:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	/* 
	 * Test Scenario : To Validate Data Points after Reconciliation for Integration : IPV4 
	 */
	
	@DataProvider
	public Object[][] getData63(){
		return Utility.getData(xls,"TC63");
	}
	
	@Test (dataProvider="getData63", priority = 63, dependsOnMethods = "DnR_Recon_Run_IPV4")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Data_IPV4(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Data_IPV4");
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
			dnrFlow.validateIDBSDBTableCount("IPV4");
			
			ExtentTest childTest3=extent.startTest("Verify FallOuts");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			String sessionId = dnrFlow.verifyFallOuts("IPV4");
					
			ExtentTest childTest4=extent.startTest("Capture Recon Report Actions");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			dnrFlow.runReconQuery("IPV4","FirstRun", sessionId);
			
			log.debug("Leaving DnR_Validate_Data_IPV4");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Data_IPV4:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To Validate Update Scenario after Reconciliation for Integration : IPV4 
	 */
	
	@DataProvider
	public Object[][] getData64(){
		return Utility.getData(xls,"TC64");
	}
	
	@Test (dataProvider="getData64",priority = 64)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Update_IPV4(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Update_IPV4");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String ipAddress = data.get("IP_ADDRESS");
			String fetchLoc = data.get("FETCH_LOC");
			String storageLoc = data.get("STORAGE_LOC");

			ExtentTest childTest1=extent.startTest("Login to the Server and set the Fetch Location");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			landing.LoginOSS();
			dnrFlow.navigateToIntegrationInterface("IPV4", fetchLoc, storageLoc, null);
			
			ExtentTest childTest2=extent.startTest("Run Initial Recon and get the intial actions before performing Update");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("IPV4", "Recon", "1", "SingleRun", "No");
			String objectId = dnrFlow.fetchObjIdFromIp(ipAddress);
			dnrFlow.verifyActionsforUpdate("IPV4", objectId);
			
			ExtentTest childTest3=extent.startTest("Update the Parameter for the particular object");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.navigatetoUpdate(objectId, "IPV4", ipAddress);
					
			ExtentTest childTest4=extent.startTest("Run Recon again and verify the action of the object");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("IPV4", "Recon", "1", "SingleRun", "No");
			int actionCount = dnrFlow.verifyActionsforUpdate("IPV4", objectId);
			dnrFlow.validateActions("Update" , actionCount);
			
			log.debug("Leaving DnR_Validate_Update_IPV4");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Update_IPV4:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To Validate Delete Scenario after Reconciliation for Integration : IPV4 
	 */
	
	@DataProvider
	public Object[][] getData65(){
		return Utility.getData(xls,"TC65");
	}
	
	@Test (dataProvider="getData65",priority = 65)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Delete_IPV4(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Delete_IPV4");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String ipAddress = data.get("IP_ADDRESS");
			String fetchLoc = data.get("FETCH_LOC");
			String storageLoc = data.get("STORAGE_LOC");

			ExtentTest childTest1=extent.startTest("Login to the Server and set the Fetch Location");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			landing.LoginOSS();
			dnrFlow.navigateToIntegrationInterface("IPV4", fetchLoc, storageLoc, null);
			
			ExtentTest childTest2=extent.startTest("Run Initial Recon and get the intial actions before performing Delete");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("IPV4", "Recon", "1", "SingleRun", "No");
			String objectId = dnrFlow.fetchObjIdFromIp(ipAddress);
			String objectName = dnrFlow.verifyActionsBeforeDelete("IPV4", objectId);
			
			ExtentTest childTest3=extent.startTest("Perform delete operation");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.performDeletion("IPV4", objectId);
					
			ExtentTest childTest4=extent.startTest("Run Recon again and verify the action of the object");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("IPV4", "Recon", "1", "SingleRun", "No");
			int actionCount = dnrFlow.verifyActionsAfterDelete("IPV4", objectName, "nc_rec_obj9152590184013408095");
			dnrFlow.validateActions("Delete" , actionCount);
			
			log.debug("Leaving DnR_Validate_Delete_IPV4");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Delete_IPV4:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
}
