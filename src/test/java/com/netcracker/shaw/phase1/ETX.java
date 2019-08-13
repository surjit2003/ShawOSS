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

public class ETX extends SeleniumTestUp{
	
	//public ExtentReports extent=ExtentReportManager.getInstance();
	
	LandingPage landing=null;
	DnRFlowPage dnrFlow =null;
	JDBCValidator jdbc=null;
	Logger log=Logger.getLogger(ETX.class);
	
	/* 
	 * Test Scenario : To run Delete Script for Integration : ETX 
	 */
	
	@DataProvider
	public Object[][] getData34(){
		return Utility.getData(xls,"TC34");
	}

	@Test (dataProvider="getData34", priority = 34)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Run_DeleScript_ETX(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Run_DeleScript_ETX");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest1=extent.startTest("Run Delete Script for ETX");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.runDeleteScript("ETX");
			
			log.debug("Leaving DnR_Run_DeleScript_ETX");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Run_DeleScript_ETX:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To validate the integration interface catalogue parameters for Integration : ETX 
	 */
	
	@DataProvider
	public Object[][] getData35(){
		return Utility.getData(xls,"TC35");
	}
	
	@Test (dataProvider="getData35", priority = 35)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Config_ETX(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Config_ETX");
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
			dnrFlow.navigateToIntegrationInterface("ETX", fetchLoc, storageLoc, null);
			
			log.debug("Leaving DnR_Validate_Config_ETX");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Validate_Config_ETX:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To run the generate scripts required for Integration : ETX 
	 */
	
	@DataProvider
	public Object[][] getData36(){
		return Utility.getData(xls,"TC36");
	}
	
	
	@Test (dataProvider="getData36", priority = 36)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_GenerateScripts_ETX(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_GenerateScripts_ETX");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp7337cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Run Pre-Requisite Scripts required for Data Export and Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataTransition("ETX");
			
			log.debug("Leaving DnR_GenerateScripts_ETX");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_GenerateScripts_ETX:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To run the Data Export Session for Integration : ETX 
	 */
	
	@DataProvider
	public Object[][] getData37(){
		return Utility.getData(xls,"TC37");
	}
	
	
	@Test (dataProvider="getData37", priority = 37)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_DataExport_Run_ETX(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_DataExport_Run_ETX");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest=extent.startTest("Login to Server : http://devapp7337cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Start Session of Data Export");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("ETX", "DE", "1", "SingleRun", "No");

			ExtentTest childTest2=extent.startTest("Validate the Zip File Creation after Data Export");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.validatePuttyPostCondition("ETX", 1, "None");

			log.debug("Leaving DnR_DataExport_Run_ETX");

		}catch(Exception e)
		{
			log.error("Error in DnR_DataExport_Run_ETX:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To run the Reconciliation Session for Integration : ETX 
	 */
	
	@DataProvider
	public Object[][] getData38(){
		return Utility.getData(xls,"TC38");
	}
	
	
	@Test (dataProvider="getData38", priority = 38, dependsOnMethods = "DnR_GenerateScripts_ETX")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Recon_Run_ETX(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Recon_Run_ETX");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			String fetchLoc = data.get("FETCH_LOC");
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp7337cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest1=extent.startTest("Trigger the Start Session of Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			if ( exportWorks.equalsIgnoreCase("No") && (fetchLoc != null) )
			dnrFlow.navigateToIntegrationInterface("ETX", fetchLoc, null, null);
			dnrFlow.navigateToDataFlow("ETX", "Recon", "1", "SingleRun", "No");
			
			log.debug("Leaving DnR_Recon_Run_ETX");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Recon_Run_ETX:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	/* 
	 * Test Scenario : To Validate Data Points after Reconciliation for Integration : ETX 
	 */
	
	@DataProvider
	public Object[][] getData39(){
		return Utility.getData(xls,"TC39");
	}
	
	@Test (dataProvider="getData39", priority = 39, dependsOnMethods = "DnR_Recon_Run_ETX")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Data_ETX(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Data_ETX");
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
			dnrFlow.validateIDBSDBTableCount("ETX");
			
			ExtentTest childTest3=extent.startTest("Verify FallOuts");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			String sessionId = dnrFlow.verifyFallOuts("ETX");
			
			ExtentTest childTest4=extent.startTest("Perform Dictionary Validation For Devices and Cards");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			dnrFlow.validateDictionaryTest("ETX", sessionId);
			
			ExtentTest childTest5=extent.startTest("Capture Recon Report Actions");
			test.appendChild(childTest5);
			dnrFlow=createChildTest(childTest5,extent,dnrFlow);
			dnrFlow.runReconQuery("ETX", "FirstRun", sessionId);
			
			
			log.debug("Leaving DnR_Validate_Data_ETX");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Data_ETX:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To Validate Update Scenario after Reconciliation for Integration : ETX 
	 */
	
	@DataProvider
	public Object[][] getData40(){
		return Utility.getData(xls,"TC40");
	}
	
	@Test (dataProvider="getData40",priority = 40)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Update_ETX(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Update_ETX");
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
			dnrFlow.navigateToIntegrationInterface("ETX", fetchLoc, storageLoc, null);
			
			ExtentTest childTest2=extent.startTest("Run Initial Recon and get the intial actions before performing Update");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("ETX", "Recon", "1", "SingleRun", "No");
			String objectId = dnrFlow.fetchObjIdFromDns(dnsName);
			dnrFlow.verifyActionsforUpdate("ETX", objectId);
			
			ExtentTest childTest3=extent.startTest("Update the Parameter for the particular object");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.navigatetoUpdate(objectId, "ETX", null);
					
			ExtentTest childTest4=extent.startTest("Run Recon again and verify the action of the object");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("ETX", "Recon", "1", "SingleRun", "No");
			int actionCount = dnrFlow.verifyActionsforUpdate("ETX", objectId);
			dnrFlow.validateActions("Update" , actionCount);
			
			log.debug("Leaving DnR_Validate_Update_ETX");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Update_ETX:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To Validate Delete Scenario after Reconciliation for Integration : ETX 
	 */
	
	@DataProvider
	public Object[][] getData41(){
		return Utility.getData(xls,"TC41");
	}
	
	@Test (dataProvider="getData41",priority = 41)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Delete_ETX(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Delete_ETX");
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
			dnrFlow.navigateToIntegrationInterface("ETX", fetchLoc, storageLoc, null);
			
			ExtentTest childTest2=extent.startTest("Run Initial Recon and get the intial actions before performing Delete");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("ETX", "Recon", "1", "SingleRun", "No");
			String objectId = dnrFlow.fetchObjIdFromDns(dnsName);
			String objectName = dnrFlow.verifyActionsBeforeDelete("ETX", objectId);
			
			ExtentTest childTest3=extent.startTest("Perform delete operation");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.performDeletion("ETX", objectId);
					
			ExtentTest childTest4=extent.startTest("Run Recon again and verify the action of the object");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("ETX", "Recon", "1", "SingleRun", "No");
			int actionCount = dnrFlow.verifyActionsAfterDelete("ETX", objectName, "nc_rec_obj9151710934913719258");
			dnrFlow.validateActions("Delete" , actionCount);
			
			log.debug("Leaving DnR_Validate_Delete_ETX");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Delete_ETX:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}


}
