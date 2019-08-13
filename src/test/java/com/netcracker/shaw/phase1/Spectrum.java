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

public class Spectrum extends SeleniumTestUp{
	
	//public ExtentReports extent=ExtentReportManager.getInstance();
	
	LandingPage landing=null;
	DnRFlowPage dnrFlow =null;
	JDBCValidator jdbc=null;
	Logger log=Logger.getLogger(Spectrum.class);
	
	/* 
	 * Test Scenario : To run Delete Script for Integration : Spectrum 
	 */
	
	@DataProvider
	public Object[][] getData42(){
		return Utility.getData(xls,"TC42");
	}

	@Test (dataProvider="getData42", priority = 42)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Run_DeleScript_Spectrum(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Run_DeleScript_Spectrum");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest1=extent.startTest("Run Delete Script for Spectrum");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.runDeleteScript("Spectrum");
			
			log.debug("Leaving DnR_Run_DeleScript_Spectrum");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Run_DeleScript_Spectrum:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To validate the integration interface catalogue parameters for Integration : Spectrum 
	 */
	
	@DataProvider
	public Object[][] getData43(){
		return Utility.getData(xls,"TC43");
	}
	
	@Test (dataProvider="getData43", priority = 43)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Config_Spectrum(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Config_Spectrum");
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
			dnrFlow.navigateToIntegrationInterface("Spectrum", fetchLoc, storageLoc, null);
			
			log.debug("Leaving DnR_Validate_Config_Spectrum");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Validate_Config_Spectrum:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To run the generate scripts required for Integration : Spectrum 
	 */
	
	@DataProvider
	public Object[][] getData44(){
		return Utility.getData(xls,"TC44");
	}
	
	
	@Test (dataProvider="getData44", priority = 44)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_GenerateScripts_Spectrum(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_GenerateScripts_Spectrum");
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
			dnrFlow.navigateToDataTransition("Spectrum");
			
			log.debug("Leaving DnR_GenerateScripts_Spectrum");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_GenerateScripts_Spectrum:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To run the Data Export Session for Integration : Spectrum 
	 */
	
	@DataProvider
	public Object[][] getData45(){
		return Utility.getData(xls,"TC45");
	}
	
	
	@Test (dataProvider="getData45", priority = 45)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_DataExport_Run_Spectrum(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_DataExport_Run_Spectrum");
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
			dnrFlow.navigateToDataFlow("Spectrum", "DE", "1", "SingleRun", "No");
			
			ExtentTest childTest2=extent.startTest("Validate the Zip File Creation after Data Export");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.validatePuttyPostCondition("Spectrum", 1, "None");
			
			log.debug("Leaving DnR_DataExport_Run_Spectrum");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_DataExport_Run_Spectrum:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To run the Reconciliation Session for Integration : Spectrum 
	 */
	
	@DataProvider
	public Object[][] getData46(){
		return Utility.getData(xls,"TC46");
	}
	
	@Test (dataProvider="getData46", priority = 46, dependsOnMethods = "DnR_GenerateScripts_Spectrum")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Recon_Run_Spectrum(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Recon_Run_Spectrum");
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
			dnrFlow.navigateToIntegrationInterface("Spectrum", fetchLoc, null, null);
			dnrFlow.navigateToDataFlow("Spectrum", "Recon", "1", "SingleRun", "No");
			
			log.debug("Leaving DnR_Recon_Run_Spectrum");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Recon_Run_Spectrum:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	/* 
	 * Test Scenario : To Validate Data Points after Reconciliation for Integration : Spectrum 
	 */
	
	@DataProvider
	public Object[][] getData47(){
		return Utility.getData(xls,"TC47");
	}
	
	@Test (dataProvider="getData47", priority = 47, dependsOnMethods = "DnR_Recon_Run_Spectrum")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Data_Spectrum(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Data_Spectrum");
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
			dnrFlow.validateIDBSDBTableCount("Spectrum");
			
			ExtentTest childTest3=extent.startTest("Verify FallOuts");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			String sessionId = dnrFlow.verifyFallOuts("Spectrum");
			
			ExtentTest childTest4=extent.startTest("Capture Recon Report Actions");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			dnrFlow.runReconQuery("Spectrum", "FirstRun", sessionId);
			
			log.debug("Leaving DnR_Validate_Data_Spectrum");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Data_Spectrum:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To Validate Update Scenario after Reconciliation for Integration : Spectrum 
	 */
	
	@DataProvider
	public Object[][] getData48(){
		return Utility.getData(xls,"TC48");
	}
	
	@Test (dataProvider="getData48",priority = 48)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Update_Spectrum(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Update_Spectrum");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String dnsName = data.get("DNS_NAME");
			String fetchLoc = data.get("FETCH_LOC");
			String storageLoc = data.get("STORAGE_LOC");
			
			System.out.println("DNS : " + dnsName);


			ExtentTest childTest1=extent.startTest("Login to the Server and set the Fetch Location");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			landing.LoginOSS();
			dnrFlow.navigateToIntegrationInterface("Spectrum", fetchLoc, storageLoc, null);
			
			ExtentTest childTest2=extent.startTest("Run Initial Recon and get the intial actions before performing Update");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("Spectrum", "Recon", "1", "SingleRun", "No");
			String objectId = dnrFlow.fetchObjIdFromDns(dnsName);
			dnrFlow.verifyActionsforUpdate("Spectrum", objectId);
			
			ExtentTest childTest3=extent.startTest("Update the Parameter for the particular object");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.navigatetoUpdate(objectId, "Spectrum", null);
					
			ExtentTest childTest4=extent.startTest("Run Recon again and verify the action of the object");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("Spectrum", "Recon", "1", "SingleRun", "No");
			int actionCount = dnrFlow.verifyActionsforUpdate("Spectrum", objectId);
			dnrFlow.validateActions("Update" , actionCount);
			
			log.debug("Leaving DnR_Validate_Update_Spectrum");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Update_Spectrum:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To Validate Delete Scenario after Reconciliation for Integration : Spectrum 
	 */
	
	@DataProvider
	public Object[][] getData49(){
		return Utility.getData(xls,"TC49");
	}
	
	@Test (dataProvider="getData49",priority = 49)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Delete_Spectrum(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Delete_Spectrum");
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
			dnrFlow.navigateToIntegrationInterface("Spectrum", fetchLoc, storageLoc, null);
			
			ExtentTest childTest2=extent.startTest("Run Initial Recon and get the intial actions before performing Delete");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("Spectrum", "Recon", "1", "SingleRun", "No");
			String objectId = dnrFlow.fetchObjIdFromDns(dnsName);
			String objectName = dnrFlow.verifyActionsBeforeDelete("Spectrum", objectId);
			
			ExtentTest childTest3=extent.startTest("Perform delete operation");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.performDeletion("Spectrum", objectId);
					
			ExtentTest childTest4=extent.startTest("Run Recon again and verify the action of the object");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("Spectrum", "Recon", "1", "SingleRun", "No");
			int actionCount = dnrFlow.verifyActionsAfterDelete("Spectrum", objectName, "nc_rec_obj9145189723013348792");
			dnrFlow.validateActions("Delete" , actionCount);
			
			log.debug("Leaving DnR_Validate_Delete_Spectrum");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Delete_Spectrum:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}

}
