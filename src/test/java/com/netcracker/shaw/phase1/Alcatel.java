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

public class Alcatel extends SeleniumTestUp{
	
	LandingPage landing=null;
	DnRFlowPage dnrFlow =null;
	JDBCValidator jdbc=null;
	Logger log=Logger.getLogger(Alcatel.class);
	SoftAssert softAssert=new SoftAssert();
	
	/* 
	 * Test Scenario : To run Delete Script for Integration : Alcatel 
	 */
	
	@DataProvider
	public Object[][] getData10(){
		return Utility.getData(xls,"TC10");
	}

	@Test (dataProvider="getData10", priority = 10)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Run_DeleScript_ALCATEL(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Run_DeleScript_ALCATEL");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest1=extent.startTest("Run Delete Script for ALCATEL");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.runDeleteScript("Alcatel");
			
			log.debug("Leaving DnR_Run_DeleScript_ALCATEL");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Run_DeleScript_ALCATEL:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To validate the integration interface catalogue parameters for Integration : Alcatel 
	 */
	
	@DataProvider
	public Object[][] getData11(){
		return Utility.getData(xls,"TC11");
	}

	@Test (dataProvider="getData11", priority = 11)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Config_ALCATEL(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Config_ALCATEL");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String fetchLoc = data.get("FETCH_LOC");
			String storageLoc = data.get("STORAGE_LOC");
			System.out.println("Fetch : " + fetchLoc + "storage : " + storageLoc);
			
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to Documents Integration Interface and Validate the parameters");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.navigateToIntegrationInterface("Alcatel", fetchLoc, storageLoc, null);
			
			log.debug("Leaving DnR_Validate_Config_ALCATEL");

		}catch(Exception e)
		{
			log.error("Error in DnR_Validate_Config_ALCATEL:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To run the generate scripts required for Integration : Alcatel 
	 */
	
	@DataProvider
	public Object[][] getData12(){
		return Utility.getData(xls,"TC12");
	}
	
	
	@Test (dataProvider="getData12", priority = 12)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_GenerateScripts_ALCATEL(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_GenerateScripts_ALCATEL");
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
			dnrFlow.navigateToDataTransition("Alcatel");
			
			log.debug("Leaving DnR_GenerateScripts_ALCATEL");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_GenerateScripts_ALCATEL:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To run the Data Export Session for Integration : Alcatel 
	 */
	
	@DataProvider
	public Object[][] getData13(){
		return Utility.getData(xls,"TC13");
	}

	@Test (dataProvider="getData13", priority = 13)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_DataExport_Run_ALCATEL(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_DataExport_Run_ALCATEL");
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
			dnrFlow.navigateToDataFlow("Alcatel", "DE", "1", "SingleRun", "No");
			
			ExtentTest childTest2=extent.startTest("Validate the Zip File Creation after Data Export");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.validatePuttyPostCondition("Alcatel", 1, "None");
			
			log.debug("Leaving DnR_DataExport_Run_ALCATEL");

		}catch(Exception e)
		{
			log.error("Error in DnR_DataExport_Run_ALCATEL:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To run the Reconciliation Session for Integration : Alcatel 
	 */
	
	@DataProvider
	public Object[][] getData14(){
		return Utility.getData(xls,"TC14");
	}
	
	
	@Test (dataProvider="getData14", priority = 14, dependsOnMethods = "DnR_GenerateScripts_ALCATEL")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Recon_Run_ALCATEL(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Recon_Run_ALCATEL");
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
			dnrFlow.navigateToIntegrationInterface("Alcatel", fetchLoc, null, null);
			dnrFlow.navigateToDataFlow("Alcatel", "Recon", "1", "SingleRun", "No");
			
			log.debug("Leaving DnR_Recon_Run_ALCATEL");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Recon_Run_ALCATEL:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	/* 
	 * Test Scenario : To Validate Data Points after Reconciliation for Integration : Alcatel 
	 */
	
	@DataProvider
	public Object[][] getData15(){
		return Utility.getData(xls,"TC15");
	}
	
	@Test (dataProvider="getData15", priority = 15, dependsOnMethods = "DnR_Recon_Run_ALCATEL")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Data_ALCATEL(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Data_ALCATEL");
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
			dnrFlow.validateIDBSDBTableCount("Alcatel");
			
			ExtentTest childTest3=extent.startTest("Verify FallOuts");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			String sessionId = dnrFlow.verifyFallOuts("Alcatel");
					
			ExtentTest childTest4=extent.startTest("Capture Recon Report Actions");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			dnrFlow.runReconQuery("Alcatel","FirstRun", sessionId);
			
			log.debug("Leaving DnR_Validate_Data_ALCATEL");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Data_ALCATEL:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To Validate Update Scenario after Reconciliation for Integration : Alcatel 
	 */
	
	@DataProvider
	public Object[][] getData16(){
		return Utility.getData(xls,"TC16");
	}
	
	@Test (dataProvider="getData16",priority = 16)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Update_ALCATEL(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Update_ALCATEL");
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
			dnrFlow.navigateToIntegrationInterface("Alcatel", fetchLoc, storageLoc, null);
			
			ExtentTest childTest2=extent.startTest("Run Initial Recon and get the intial actions before performing Update");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			String sessionId = dnrFlow.navigateToDataFlow("Alcatel", "Recon", "1", "SingleRun", "No");
			String objectId = dnrFlow.fetchObjIdFromDns(dnsName);
			dnrFlow.verifyActionsWithTableforUpdate("Alcatel", objectId, sessionId);
			
			ExtentTest childTest3=extent.startTest("Update the Parameter for the particular object");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.navigatetoUpdate(objectId, "Alcatel", null);
					
			ExtentTest childTest4=extent.startTest("Run Recon again and verify the action of the object");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			sessionId = dnrFlow.navigateToDataFlow("Alcatel", "Recon", "1", "SingleRun", "No");
			int actionCount = dnrFlow.verifyActionsWithTableforUpdate("Alcatel", objectId, sessionId);
			dnrFlow.validateActions("Update" , actionCount);
			
			log.debug("Leaving DnR_Validate_Update_ALCATEL");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Update_ALCATEL:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To Validate Delete Scenario after Reconciliation for Integration : Alcatel 
	 */
	
	@DataProvider
	public Object[][] getData17(){
		return Utility.getData(xls,"TC17");
	}
	
	@Test (dataProvider="getData17",priority = 17)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Delete_ALCATEL(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Delete_ALCATEL");
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
			dnrFlow.navigateToIntegrationInterface("Alcatel", fetchLoc, storageLoc, null);
			
			ExtentTest childTest2=extent.startTest("Run Initial Recon and get the intial actions before performing Delete");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			String sessionId = dnrFlow.navigateToDataFlow("Alcatel", "Recon", "1", "SingleRun", "No");
			String objectId = dnrFlow.fetchObjIdFromDns(dnsName);
			String objectName = dnrFlow.verifyActionsWithTableBeforeDelete("Alcatel", objectId, sessionId);
			
			ExtentTest childTest3=extent.startTest("Perform delete operation");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.performDeletion("Alcatel", objectId);
					
			ExtentTest childTest4=extent.startTest("Run Recon again and verify the action of the object");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			sessionId = dnrFlow.navigateToDataFlow("Alcatel", "Recon", "1", "SingleRun", "No");
			int actionCount = dnrFlow.verifyActionsWithTableAfterDelete("Alcatel", objectName, sessionId);
			dnrFlow.validateActions("Delete" , actionCount);
			
			log.debug("Leaving DnR_Validate_Delete_ALCATEL");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Delete_ALCATEL:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
}
