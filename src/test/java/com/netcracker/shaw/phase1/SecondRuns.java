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

public class SecondRuns extends SeleniumTestUp{
	
	//public ExtentReports extent=ExtentReportManager.getInstance();
	
	LandingPage landing=null;
	DnRFlowPage dnrFlow =null;
	JDBCValidator jdbc=null;
	Logger log=Logger.getLogger(SecondRuns.class);
	SoftAssert softAssert=new SoftAssert();
	ArrayList<String> valuesToPassForValidation=new ArrayList<String>();
	
	//Added for SHAW OSS -- DnR Flow Handling Scenarios
	
	
	@DataProvider
	public Object[][] getData74(){
		return Utility.getData(xls,"TC74");
	}
	
	
	@Test (dataProvider="getData74", priority = 74)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_DataExport_SecondRun_IPBB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_DataExport_SecondRun_IPBB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			String fetchLoc = data.get("FETCH_LOC");
			String storageLoc = data.get("STORAGE_LOC");
			String ftpLoc = data.get("FTP_LOC");

			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest1=extent.startTest("Validate whether the Current Date Folder Exists");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.validatePuttyCurrentDateFolder();
			
			ExtentTest childTest2=extent.startTest("Trigger the Second Run Start Session of Data Export");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.navigateToIntegrationInterface("IPBB", fetchLoc, storageLoc, ftpLoc);
			dnrFlow.navigateToDataFlow("IPBB", "DE", "2", "SingleRun", "No");
			
			ExtentTest childTest3=extent.startTest("Validate the Zip File Creation after Second Run of Data Export");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.validatePuttyPostCondition("IPBB", 2, "None");
			
			log.debug("Leaving DnR_DataExport_SecondRun_IPBB");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_DataExport_SecondRun_IPBB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData75(){
		return Utility.getData(xls,"TC75");
	}
	
	
	@Test (dataProvider="getData75", priority = 75)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Recon_SeconRun_IPBB(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Recon_SeconRun_IPBB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String fetchLoc = data.get("FETCH_LOC");
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Trigger the Second Run Start Session of Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			if ( exportWorks.equalsIgnoreCase("No") && (fetchLoc != null) )
			dnrFlow.navigateToIntegrationInterface("IPBB", fetchLoc, null, null);
			dnrFlow.navigateToDataFlow("IPBB", "Recon", "2", "SingleRun", "No");
			
			log.debug("Leaving DnR_Recon_SeconRun_IPBB");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Recon_SeconRun_IPBB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	
	@DataProvider
	public Object[][] getData76(){
		return Utility.getData(xls,"TC76");
	}
	
	@Test (dataProvider="getData76", priority = 76, dependsOnMethods = "DnR_Recon_SeconRun_IPBB")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Data_SecondRun_IPBB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Data_SecondRun_IPBB");
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
			dnrFlow.validateIDBSDBTableCount("IPBB");
			
			ExtentTest childTest3=extent.startTest("Verify FallOuts");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			String sessionId = dnrFlow.verifyFallOuts("IPBB");
			
			ExtentTest childTest4=extent.startTest("Capture Recon Report Actions");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			dnrFlow.runReconQuery("IPBB", "SecondRun", sessionId);
			
			log.debug("Leaving DnR_Validate_Data_SecondRun_IPBB");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Data_SecondRun_IPBB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData77(){
		return Utility.getData(xls,"TC77");
	}
	
	
	@Test (dataProvider="getData77", priority = 77)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_DataExport_SecondRun_ALCATEL(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_DataExport_SecondRun_ALCATEL");
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
			
			ExtentTest childTest2=extent.startTest("Trigger the Second Run Start Session of Data Export");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.navigateToIntegrationInterface("Alcatel", fetchLoc, storageLoc, null);
			dnrFlow.navigateToDataFlow("Alcatel", "DE", "2", "SingleRun", "No");
			
			ExtentTest childTest3=extent.startTest("Validate the Zip File Creation after Second Run of Data Export");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.validatePuttyPostCondition("Alcatel", 2, "None");
			
			log.debug("Leaving DnR_DataExport_SecondRun_ALCATEL");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_DataExport_SecondRun_ALCATEL:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData78(){
		return Utility.getData(xls,"TC78");
	}
	
	
	@Test (dataProvider="getData78", priority = 78)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Recon_SeconRun_ALCATEL(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Recon_SeconRun_ALCATEL");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String fetchLoc = data.get("FETCH_LOC");
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Trigger the Second Run Start Session of Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			if ( exportWorks.equalsIgnoreCase("No") && (fetchLoc != null) )
			dnrFlow.navigateToIntegrationInterface("Alcatel", fetchLoc, null, null);
			dnrFlow.navigateToDataFlow("Alcatel", "Recon", "2", "SingleRun", "No");
			
			log.debug("Leaving DnR_Recon_SeconRun_ALCATEL");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Recon_SeconRun_ALCATEL:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	
	@DataProvider
	public Object[][] getData79(){
		return Utility.getData(xls,"TC79");
	}
	
	@Test (dataProvider="getData79", priority = 79, dependsOnMethods = "DnR_Recon_SeconRun_ALCATEL")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Data_SecondRun_ALCATEL(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Data_SecondRun_ALCATEL");
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
			dnrFlow.runReconQuery("Alcatel","SecondRun", sessionId);
			
			
			log.debug("Leaving DnR_Validate_Data_SecondRun_ALCATEL");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Data_SecondRun_ALCATEL:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}	

	@DataProvider
	public Object[][] getData80(){
		return Utility.getData(xls,"TC80");
	}
	
	
	@Test (dataProvider="getData80", priority =80)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_DataExport_SecondRun_CMTS(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_DataExport_SecondRun_CMTS");
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
			
			ExtentTest childTest2=extent.startTest("Trigger the Second Run Start Session of Data Export");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.navigateToIntegrationInterface("CMTS", fetchLoc, storageLoc, null);
			dnrFlow.navigateToDataFlow("CMTS", "DE", "2", "SingleRun", "No");
			
			ExtentTest childTest3=extent.startTest("Validate the Zip File Creation after Second Run of Data Export");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.validatePuttyPostCondition("CMTS", 2, "None");
			
			log.debug("Leaving DnR_DataExport_SecondRun_CMTS");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_DataExport_SecondRun_CMTS:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData81(){
		return Utility.getData(xls,"TC81");
	}
	
	
	@Test (dataProvider="getData81", priority = 81)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Recon_SeconRun_CMTS(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Recon_SeconRun_CMTS");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String fetchLoc = data.get("FETCH_LOC");
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest1=extent.startTest("Trigger the Second Run Start Session of Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			if ( exportWorks.equalsIgnoreCase("No") && (fetchLoc != null) )
			dnrFlow.navigateToIntegrationInterface("CMTS", fetchLoc, null, null);
			dnrFlow.navigateToDataFlow("CMTS", "Recon", "2", "SingleRun", "No");
			
			log.debug("Leaving DnR_Recon_SeconRun_CMTS");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Recon_SeconRun_CMTS:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	
	@DataProvider
	public Object[][] getData82(){
		return Utility.getData(xls,"TC82");
	}
	
	@Test (dataProvider="getData82", priority = 82 , dependsOnMethods = "DnR_Recon_SeconRun_CMTS")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Data_SecondRun_CMTS(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Data_SecondRun_CMTS");
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
			dnrFlow.runReconQuery("CMTS", "SecondRun", sessionId);
			
			log.debug("Leaving DnR_Validate_Data_SecondRun_CMTS");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Data_SecondRun_CMTS:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData83(){
		return Utility.getData(xls,"TC83");
	}
	
	
	@Test (dataProvider="getData83", priority = 83)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_DataExport_SecondRun_CMTS_OnDemand(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_DataExport_SecondRun_CMTS_OnDemand");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			String fetchLoc = data.get("FETCH_LOC");
			String ipAddress = data.get("IP_ADDRESS");

			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Trigger the Second Run Start Session of Data Export");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			if ( exportWorks.equalsIgnoreCase("No") && (fetchLoc != null) )
			dnrFlow.navigateToIntegrationInterface("CMTS-OnDemand", fetchLoc, null, null);
			dnrFlow.navigateToDataFlow("CMTS-OnDemand", "DE", ipAddress, "SingleRun", "No");
			
			log.debug("Leaving DnR_DataExport_SecondRun_CMTS_OnDemand");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_DataExport_SecondRun_CMTS_OnDemand:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData84(){
		return Utility.getData(xls,"TC84");
	}
	
	@Test (dataProvider="getData84", priority = 84)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_SeconRun_Verify_IP_Folder_CMTS_OnDemand(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_SeconRun_Verify_IP_Folder_CMTS_OnDemand");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String fetchLoc = data.get("FETCH_LOC");
			String ipAddress = data.get("IP_ADDRESS");
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest1=extent.startTest("Validate the Zip folder Creation for CMTS-OnDemand for Second Run");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.validatePuttyPostCondition("CMTS-OnDemand", 2, ipAddress);
			
			log.debug("Leaving DnR_SeconRun_Verify_IP_Folder_CMTS_OnDemand");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_SeconRun_Verify_IP_Folder_CMTS_OnDemand:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	
	@DataProvider
	public Object[][] getData85(){
		return Utility.getData(xls,"TC85");
	}
	
	@Test (dataProvider="getData85", priority = 85, dependsOnMethods = "DnR_DataExport_SecondRun_CMTS_OnDemand")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Data_SecondRun_CMTS_OnDemand(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Data_SecondRun_CMTS_OnDemand");
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
			
			ExtentTest childTest4=extent.startTest("Capture Recon Report Actions");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			dnrFlow.runReconQuery("CMTS-OnDemand", "SecondRun", sessionId);
			
			log.debug("Leaving DnR_Validate_Data_SecondRun_CMTS_OnDemand");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Data_SecondRun_CMTS_OnDemand:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData86(){
		return Utility.getData(xls,"TC86");
	}
	
	
	@Test (dataProvider="getData86", priority = 86)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_DataExport_SecondRun_ETX(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_DataExport_SecondRun_ETX");
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
			
			ExtentTest childTest2=extent.startTest("Trigger the Second Run Start Session of Data Export");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.navigateToIntegrationInterface("ETX", fetchLoc, storageLoc, null);
			dnrFlow.navigateToDataFlow("ETX", "DE", "2", "SingleRun", "No");
			
			ExtentTest childTest3=extent.startTest("Validate the Zip File Creation after Second Run of Data Export");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.validatePuttyPostCondition("ETX", 2, "None");
			
			log.debug("Leaving DnR_DataExport_SecondRun_ETX");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_DataExport_SecondRun_ETX:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData87(){
		return Utility.getData(xls,"TC87");
	}
	
	
	@Test (dataProvider="getData87", priority = 87)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Recon_SeconRun_ETX(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Recon_SeconRun_ETX");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String fetchLoc = data.get("FETCH_LOC");
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Trigger the Second Run Start Session of Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			if ( exportWorks.equalsIgnoreCase("No") && (fetchLoc != null) )
				dnrFlow.navigateToIntegrationInterface("ETX", fetchLoc, null, null);
			dnrFlow.navigateToDataFlow("ETX", "Recon", "2", "SingleRun", "No");
			
			log.debug("Leaving DnR_Recon_SeconRun_ETX");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Recon_SeconRun_ETX:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	
	@DataProvider
	public Object[][] getData88(){
		return Utility.getData(xls,"TC88");
	}
	
	@Test (dataProvider="getData88", priority = 88, dependsOnMethods = "DnR_Recon_SeconRun_ETX")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Data_SecondRun_ETX(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Data_SecondRun_ETX");
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
			
			ExtentTest childTest4=extent.startTest("Capture Recon Report Actions");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			dnrFlow.runReconQuery("ETX", "SecondRun", sessionId);
			
			log.debug("Leaving DnR_Validate_Data_SecondRun_ETX");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Data_SecondRun_ETX:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData89(){
		return Utility.getData(xls,"TC89");
	}
	
	
	@Test (dataProvider="getData89", priority = 89)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_DataExport_SecondRun_FDB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_DataExport_SecondRun_FDB");
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
			
			ExtentTest childTest2=extent.startTest("Trigger the Second Run Start Session of Data Export");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.navigateToIntegrationInterface("FDB", fetchLoc, storageLoc, null);
			dnrFlow.navigateToDataFlow("FDB", "DE", "2", "SingleRun", "No");
			
			ExtentTest childTest3=extent.startTest("Validate the Zip File Creation after Second Run of Data Export");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.validatePuttyPostCondition("FDB", 2, "None");
			
			log.debug("Leaving DnR_DataExport_SecondRun_FDB");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_DataExport_SecondRun_FDB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData90(){
		return Utility.getData(xls,"TC90");
	}
	
	
	@Test (dataProvider="getData90", priority = 90)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Recon_SeconRun_FDB(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Recon_SeconRun_FDB");
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
			
			ExtentTest childTest1=extent.startTest("Trigger the Second Run Start Session of Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			if ( exportWorks.equalsIgnoreCase("No") && (fetchLoc != null) )
				dnrFlow.navigateToIntegrationInterface("FDB", fetchLoc, storageLoc, null);
			dnrFlow.navigateToDataFlow("FDB", "Recon", "2", "SingleRun", "No");
			
			log.debug("Leaving DnR_Recon_SeconRun_FDB");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Recon_SeconRun_FDB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	
	@DataProvider
	public Object[][] getData91(){
		return Utility.getData(xls,"TC91");
	}
	
	@Test (dataProvider="getData91", priority = 91, dependsOnMethods = "DnR_Recon_SeconRun_FDB")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Data_SecondRun_FDB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Data_SecondRun_FDB");
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
			dnrFlow.runReconQuery("FDB","SecondRun", sessionId);
			
			log.debug("Leaving DnR_Validate_Data_SecondRun_FDB");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Data_SecondRun_FDB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData92(){
		return Utility.getData(xls,"TC92");
	}
	
	
	@Test (dataProvider="getData92", priority = 92)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_DataExport_SecondRun_Spectrum(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_DataExport_SecondRun_Spectrum");
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
			
			ExtentTest childTest2=extent.startTest("Trigger the Second Run Start Session of Data Export");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.navigateToIntegrationInterface("Spectrum", fetchLoc, storageLoc, null);
			dnrFlow.navigateToDataFlow("Spectrum", "DE", "2", "SingleRun", "No");
			
			ExtentTest childTest3=extent.startTest("Validate the Zip File Creation after Second Run of Data Export");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.validatePuttyPostCondition("Spectrum", 2, "None");
			
			log.debug("Leaving DnR_DataExport_SecondRun_Spectrum");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_DataExport_SecondRun_Spectrum:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData93(){
		return Utility.getData(xls,"TC93");
	}
	
	
	@Test (dataProvider="getData93", priority = 93)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Recon_SeconRun_Spectrum(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Recon_SeconRun_Spectrum");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String fetchLoc = data.get("FETCH_LOC");
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Second Run Start Session of Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			if ( exportWorks.equalsIgnoreCase("No") && (fetchLoc != null) )
				dnrFlow.navigateToIntegrationInterface("Spectrum", fetchLoc, null, null);
			dnrFlow.navigateToDataFlow("Spectrum", "Recon", "2", "SingleRun", "No");
			
			log.debug("Leaving DnR_Recon_SeconRun_Spectrum");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Recon_SeconRun_Spectrum:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	
	@DataProvider
	public Object[][] getData94(){
		return Utility.getData(xls,"TC94");
	}
	
	@Test (dataProvider="getData94", priority = 94, dependsOnMethods = "DnR_Recon_SeconRun_Spectrum")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Data_SecondRun_Spectrum(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Data_SecondRun_Spectrum");
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
			dnrFlow.runReconQuery("Spectrum", "SecondRun", sessionId);
			
			log.debug("Leaving DnR_Validate_Data_SecondRun_Spectrum");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Data_SecondRun_Spectrum:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}

	@DataProvider
	public Object[][] getData95(){
		return Utility.getData(xls,"TC95");
	}
	
	
	@Test (dataProvider="getData95", priority = 95)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_DataExport_SecondRun_IPV4(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_DataExport_SecondRun_IPV4");
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
			
			ExtentTest childTest2=extent.startTest("Trigger the Second Run Start Session of Data Export");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.navigateToIntegrationInterface("IPV4", fetchLoc, storageLoc, null);
			dnrFlow.navigateToDataFlow("IPV4", "DE", "2", "SingleRun", "No");
			
			ExtentTest childTest3=extent.startTest("Validate the Zip File Creation after Second Run of Data Export");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.validatePuttyPostCondition("IPV4", 2, "None");
			
			log.debug("Leaving DnR_DataExport_SecondRun_IPV4");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_DataExport_SecondRun_IPV4:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData96(){
		return Utility.getData(xls,"TC96");
	}
	
	
	@Test (dataProvider="getData96", priority = 96)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Recon_SeconRun_IPV4(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Recon_SeconRun_IPV4");
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

			ExtentTest childTest1=extent.startTest("Trigger the Second Run Start Session of Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			if ( exportWorks.equalsIgnoreCase("No") && (fetchLoc != null) )
				dnrFlow.navigateToIntegrationInterface("IPV4", fetchLoc, storageLoc, null);
			dnrFlow.navigateToDataFlow("IPV4", "Recon", "2", "SingleRun", "No");
			
			log.debug("Leaving DnR_Recon_SeconRun_IPV4");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Recon_SeconRun_IPV4:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	
	@DataProvider
	public Object[][] getData97(){
		return Utility.getData(xls,"TC97");
	}
	
	@Test (dataProvider="getData97", priority = 97, dependsOnMethods = "DnR_Recon_SeconRun_IPV4")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Data_SecondRun_IPV4(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Data_SecondRun_IPV4");
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
			dnrFlow.runReconQuery("IPV4","SecondRun", sessionId);
			
			log.debug("Leaving DnR_Validate_Data_SecondRun_IPV4");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Data_SecondRun_IPV4:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	

	@DataProvider
	public Object[][] getData98(){
		return Utility.getData(xls,"TC98");
	}
	
	
	@Test (dataProvider="getData98", priority = 98)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_DataExport_SecondRun_CIN(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_DataExport_SecondRun_CIN");
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
			
			ExtentTest childTest2=extent.startTest("Trigger the Second Run Start Session of Data Export");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.navigateToIntegrationInterface("CIN", fetchLoc, storageLoc, null);
			dnrFlow.navigateToDataFlow("CIN", "DE", "2", "SingleRun", "No");
			
			ExtentTest childTest3=extent.startTest("Validate the Zip File Creation after Second Run of Data Export");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.validatePuttyPostCondition("CIN", 2, "None");
			
			log.debug("Leaving DnR_DataExport_SecondRun_CIN");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_DataExport_SecondRun_CIN:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData99(){
		return Utility.getData(xls,"TC99");
	}
	
	
	@Test (dataProvider="getData99", priority = 99)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Recon_SeconRun_CIN(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Recon_SeconRun_CIN");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String fetchLoc = data.get("FETCH_LOC");
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest1=extent.startTest("Trigger the Second Run Start Session of Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			if ( exportWorks.equalsIgnoreCase("No") && (fetchLoc != null) )
				dnrFlow.navigateToIntegrationInterface("CIN", fetchLoc, null, null);
			dnrFlow.navigateToDataFlow("CIN", "Recon", "2", "SingleRun", "No");
			
			log.debug("Leaving DnR_Recon_SeconRun_CIN");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Recon_SeconRun_CIN:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	
	@DataProvider
	public Object[][] getData100(){
		return Utility.getData(xls,"TC100");
	}
	
	@Test (dataProvider="getData100", priority = 100, dependsOnMethods = "DnR_Recon_SeconRun_CIN")
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Data_SecondRun_CIN(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Data_SecondRun_CIN");
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
			dnrFlow.runReconQuery("CIN","SecondRun", sessionId);
			
			log.debug("Leaving DnR_Validate_Data_SecondRun_CIN");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Data_SecondRun_CIN:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
}
