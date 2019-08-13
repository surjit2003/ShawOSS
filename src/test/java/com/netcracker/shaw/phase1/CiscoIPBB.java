package com.netcracker.shaw.phase1;

import java.awt.image.BufferedImage;
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

public class CiscoIPBB extends SeleniumTestUp{
	
	LandingPage landing=null;
	DnRFlowPage dnrFlow =null;
	JDBCValidator jdbc=null;
	Logger log=Logger.getLogger(CiscoIPBB.class);
	
	/* 
	 * Test Scenario : To run Delete Script for Integration : Cisco IPBB 
	 */
	
	@DataProvider
	public Object[][] getData1(){
		return Utility.getData(xls,"TC1");
	}

	@Test (dataProvider="getData1", priority = 1)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Run_DeleScript_IPBB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Run_DeleScript_IPBB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest1=extent.startTest("Run Delete Script for Cisco IPBB");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.runDeleteScript("IPBB");
			
			log.debug("Leaving DnR_Run_DeleScript_IPBB");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Run_DeleScript_IPBB:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To validate Source Folder Creation for Integration : Cisco IPBB 
	 */
	
	@DataProvider
	public Object[][] getData2(){
		return Utility.getData(xls,"TC2");
	}
	
	@Test (dataProvider="getData2", priority = 2)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Src_Folder_IPBB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Src_Folder_IPBB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Verify Server has Current Date Folder");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.validatePuttyCurrentDateFolder();
			
			log.debug("Leaving DnR_Validate_Src_Folder_IPBB");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Validate_Src_Folder_IPBB:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To validate the integration interface catalogue parameters for Integration : Cisco IPBB 
	 */
	
	@DataProvider
	public Object[][] getData3(){
		return Utility.getData(xls,"TC3");
	}
	
	@Test (dataProvider="getData3", priority = 3)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Config_IPBB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Config_IPBB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String fetchLoc = data.get("FETCH_LOC");
			String storageLoc = data.get("STORAGE_LOC");
			String ftpLoc = data.get("FTP_LOC");
			
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to Documents Integration Interface and Validate the parameters");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.navigateToIntegrationInterface("IPBB", fetchLoc, storageLoc, ftpLoc);
			
			log.debug("Leaving DnR_Validate_Config_IPBB");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Validate_Config_IPBB:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To run the generate scripts required for Integration : Cisco IPBB 
	 */
	
	@DataProvider
	public Object[][] getData4(){
		return Utility.getData(xls,"TC4");
	}
	
	
	@Test (dataProvider="getData4", priority = 4)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_GenerateScripts_IPBB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_GenerateScripts_IPBB");
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
			dnrFlow.navigateToDataTransition("IPBB");
			
			log.debug("Leaving DnR_GenerateScripts_IPBB");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_GenerateScripts_IPBB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To run the Data Export Session for Integration : Cisco IPBB 
	 */
	
	@DataProvider
	public Object[][] getData5(){
		return Utility.getData(xls,"TC5");
	}
	
	
	@Test (dataProvider="getData5", priority = 5)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_DataExport_Run_IPBB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_DataExport_Run_IPBB");
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
			dnrFlow.navigateToDataFlow("IPBB", "DE", "1", "SingleRun", "No");
			
			ExtentTest childTest2=extent.startTest("Validate the Zip File Creation after Data Export");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.validatePuttyPostCondition("IPBB", 1, "None");
			
			log.debug("Leaving DnR_DataExport_Run_IPBB");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_DataExport_Run_IPBB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To run the Reconciliation Session for Integration : Cisco IPBB 
	 */
	
	@DataProvider
	public Object[][] getData6(){
		return Utility.getData(xls,"TC6");
	}
	
	
	@Test (dataProvider="getData6", priority = 6, dependsOnMethods = "DnR_GenerateScripts_IPBB")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Recon_Run_IPBB(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Recon_Run_IPBB");
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
			dnrFlow.navigateToIntegrationInterface("IPBB", fetchLoc, null, null);
			dnrFlow.navigateToDataFlow("IPBB", "Recon", "1", "SingleRun", "No");
			
			log.debug("Leaving DnR_Recon_Run_IPBB");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Recon_Run_IPBB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	/* 
	 * Test Scenario : To Validate Data Points after Reconciliation for Integration : Cisco IPBB 
	 */
	
	@DataProvider
	public Object[][] getData7(){
		return Utility.getData(xls,"TC7");
	}
	
	@Test (dataProvider="getData7", priority = 7, dependsOnMethods = "DnR_Recon_Run_IPBB")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Data_IPBB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Data_IPBB");
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
			dnrFlow.validateIDBSDBTableCount("IPBB");
			
			ExtentTest childTest2=extent.startTest("Verify FallOuts");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			String sessionId = dnrFlow.verifyFallOuts("IPBB");
			
			ExtentTest childTest3=extent.startTest("Capture Recon Report Actions");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.runReconQuery("IPBB", "FirstRun", sessionId);
			
			log.debug("Leaving DnR_Validate_Data_IPBB");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Data_IPBB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To Validate Update Scenario after Reconciliation for Integration : Cisco IPBB 
	 */
	
	@DataProvider
	public Object[][] getData8(){
		return Utility.getData(xls,"TC8");
	}
	
	@Test (dataProvider="getData8",priority = 8)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Update_IPBB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Update_IPBB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String dnsName = data.get("DNS_NAME");
			String fetchLoc = data.get("FETCH_LOC");
			String storageLoc = data.get("STORAGE_LOC");
			String ftpLoc = data.get("FTP_LOC");

			ExtentTest childTest1=extent.startTest("Login to the Server and set the Fetch Location");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			landing.LoginOSS();
			dnrFlow.navigateToIntegrationInterface("IPBB", fetchLoc, storageLoc, ftpLoc);
			
			ExtentTest childTest2=extent.startTest("Run Initial Recon and get the intial actions before performing Update");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("IPBB", "Recon", "1", "SingleRun", "No");
			String objectId = dnrFlow.fetchObjIdFromDns(dnsName);
			dnrFlow.verifyActionsforUpdate("IPBB", objectId);
			
			ExtentTest childTest3=extent.startTest("Update the Parameter for the particular object");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.navigatetoUpdate(objectId, "IPBB", null);
					
			ExtentTest childTest4=extent.startTest("Run Recon again and verify the action of the object");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("IPBB", "Recon", "1", "SingleRun", "No");
			int actionCount= dnrFlow.verifyActionsforUpdate("IPBB", objectId);
			dnrFlow.validateActions("Update" , actionCount);
			
			log.debug("Leaving DnR_Validate_Update_IPBB");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Update_IPBB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* 
	 * Test Scenario : To Validate Delete Scenario after Reconciliation for Integration : Cisco IPBB 
	 */
	
	@DataProvider
	public Object[][] getData9(){
		return Utility.getData(xls,"TC9");
	}
	
	@Test (dataProvider="getData9",priority = 9)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Delete_IPBB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Delete_IPBB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String dnsName = data.get("DNS_NAME");
			String fetchLoc = data.get("FETCH_LOC");
			String storageLoc = data.get("STORAGE_LOC");
			String ftpLoc = data.get("FTP_LOC");

			ExtentTest childTest1=extent.startTest("Login to the Server and set the Fetch Location");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			landing.LoginOSS();
			dnrFlow.navigateToIntegrationInterface("IPBB", fetchLoc, storageLoc, ftpLoc);
			
			ExtentTest childTest2=extent.startTest("Run Initial Recon and get the intial actions before performing Delete");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("IPBB", "Recon", "1", "SingleRun", "No");
			String objectId = dnrFlow.fetchObjIdFromDns(dnsName);
			String objectName = dnrFlow.verifyActionsBeforeDelete("IPBB", objectId);
			
			ExtentTest childTest3=extent.startTest("Perform delete operation");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.performDeletion("IPBB", objectId);
					
			ExtentTest childTest4=extent.startTest("Run Recon again and verify the action of the object");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("IPBB", "Recon", "1", "SingleRun", "No");
			int actionCount = dnrFlow.verifyActionsAfterDelete("IPBB", objectName, "nc_rec_obj9145692433313670186");
			dnrFlow.validateActions("Delete" , actionCount);
			
			log.debug("Leaving DnR_Validate_Delete_IPBB");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Delete_IPBB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}

}
