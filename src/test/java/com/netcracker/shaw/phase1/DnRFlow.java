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

public class DnRFlow extends SeleniumTestUp{
	
	//public ExtentReports extent=ExtentReportManager.getInstance();
	
	LandingPage landing=null;
	DnRFlowPage dnrFlow =null;
	JDBCValidator jdbc=null;
	Logger log=Logger.getLogger(DnRFlow.class);
	SoftAssert softAssert=new SoftAssert();
	ArrayList<String> valuesToPassForValidation=new ArrayList<String>();
	
	//Added for SHAW OSS -- DnR Flow Handling Scenarios
	/*@DataProvider
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
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();
			
			ExtentTest childTest0=extent.startTest("Run Delete Script for Cisco IPBB");
			test.appendChild(childTest0);
			order=createChildTest(childTest0,extent,order);
			order.runDeleteScript("Cisco IPBB");
			
			log.debug("Leaving DnR_Run_DeleScript_IPBB");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Run_DeleScript_IPBB:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	
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
			
			ExtentTest childTest1=extent.startTest("Verify Server has Current Date Folder");
			test.appendChild(childTest1);
			order=createChildTest(childTest1,extent,order);
			order.validatePuttyCurrentDateFolder();
			
			log.debug("Leaving DnR_Validate_Src_Folder_IPBB");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Validate_Src_Folder_IPBB:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
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
			
			ExtentTest childTest0=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			order=createChildTest(childTest0,extent,order);
			landing.LoginOSS();
			
			
			ExtentTest childTest1=extent.startTest("Navigate to Documents Integration Interface and Validate the parameters");
			test.appendChild(childTest1);
			order=createChildTest(childTest1,extent,order);
			order.navigateToIntegrationInterface("CiscoIPBB");
			
			log.debug("Leaving DnR_Validate_Config_IPBB");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Validate_Config_IPBB:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
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
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Run Pre-Requisite Scripts required for Data Export and Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataTransition("CiscoIPBB");
			
			log.debug("Leaving DnR_GenerateScripts_IPBB");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_GenerateScripts_IPBB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
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
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Trigger the Start Session of Data Export");
			test.appendChild(childTest1);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataFlow("CiscoIPBB", "DE", 1);
			
			ExtentTest childTest2=extent.startTest("Validate the Zip File Creation after Data Export");
			test.appendChild(childTest2);
			order=createChildTest(childTest2,extent,order);
			order.validatePuttyPostCondition("Cisco IPBB", 1);
			
			log.debug("Leaving DnR_DataExport_Run_IPBB");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_DataExport_Run_IPBB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData6(){
		return Utility.getData(xls,"TC6");
	}
	
	
	@Test (dataProvider="getData6", priority = 6)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Recon_Run_IPBB(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Recon_Run_IPBB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Trigger the Start Session of Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataFlow("CiscoIPBB", "Recon", 1);
			
			log.debug("Leaving DnR_Recon_Run_IPBB");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Recon_Run_IPBB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	@DataProvider
	public Object[][] getData7(){
		return Utility.getData(xls,"TC7");
	}
	
	@Test (dataProvider="getData7", priority = 7)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Data_IPBB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Data_IPBB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Validate Recon Reports");
			test.appendChild(childTest1);
			order=createChildTest(childTest1,extent,order);
			order.validateReconReports("Cisco IP Backbone", "FirstRun");
			
			ExtentTest childTest2=extent.startTest("Validate IDB SDB Counts");
			test.appendChild(childTest2);
			order=createChildTest(childTest2,extent,order);
			order.validateIDBSDBTableCount("IPBB");
			
			ExtentTest childTest3=extent.startTest("Verify FallOuts");
			test.appendChild(childTest3);
			order=createChildTest(childTest3,extent,order);
			order.verifyFallOuts();
			
			ExtentTest childTest4=extent.startTest("Run Recon Query");
			test.appendChild(childTest4);
			order=createChildTest(childTest4,extent,order);
			order.runReconQuery("true");
			
			ExtentTest childTest5=extent.startTest("Validate the object");
			test.appendChild(childTest5);
			order=createChildTest(childTest5,extent,order);
			order.runDB("CMTS On Demand");
			
			
			log.debug("Leaving DnR_Validate_Data_IPBB");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Data_IPBB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}

	
	@DataProvider
	public Object[][] getData10(){
		return Utility.getData(xls,"TC10");
	}

	@Test (dataProvider="getData10", priority = 1)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Run_DeleScript_ALCATEL(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Run_DeleScript_ALCATEL");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();
			
			ExtentTest childTest0=extent.startTest("Run Delete Script for ALCATEL");
			test.appendChild(childTest0);
			order=createChildTest(childTest0,extent,order);
			order.runDeleteScript("Alcatel");
			
			log.debug("Leaving DnR_Run_DeleScript_ALCATEL");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Run_DeleScript_ALCATEL:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	@DataProvider
	public Object[][] getData11(){
		return Utility.getData(xls,"TC11");
	}

	@Test (dataProvider="getData11", priority = 1)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Config_ALCATEL(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Config_ALCATEL");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();
			
			ExtentTest childTest1=extent.startTest("Navigate to Documents Integration Interface and Validate the parameters");
			test.appendChild(childTest1);
			order=createChildTest(childTest1,extent,order);
			order.navigateToIntegrationInterface("Alcatel");
			
			log.debug("Leaving DnR_Validate_Config_ALCATEL");

		}catch(Exception e)
		{
			log.error("Error in DnR_Validate_Config_ALCATEL:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	@DataProvider
	public Object[][] getData12(){
		return Utility.getData(xls,"TC12");
	}
	
	
	@Test (dataProvider="getData12", priority = 4)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_GenerateScripts_ALCATEL(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_GenerateScripts_ALCATEL");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Run Pre-Requisite Scripts required for Data Export and Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataTransition("Alcatel");
			
			
			
			log.debug("Leaving DnR_GenerateScripts_ALCATEL");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_GenerateScripts_ALCATEL:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData13(){
		return Utility.getData(xls,"TC13");
	}

	@Test (dataProvider="getData13", priority = 1)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_DataExport_Run_ALCATEL(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_DataExport_Run_ALCATEL");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest0=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			order=createChildTest(childTest0,extent,order);
			landing.LoginOSS();
			
			ExtentTest childTest1=extent.startTest("Trigger the Start Session of Data Export");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataFlow("Alcatel", "DE", 1);
			
			ExtentTest childTest2=extent.startTest("Validate the Zip File Creation after Data Export");
			test.appendChild(childTest2);
			order=createChildTest(childTest2,extent,order);
			order.validatePuttyPostCondition("Alcatel", 1);
			
			
			log.debug("Leaving DnR_DataExport_Run_ALCATEL");

		}catch(Exception e)
		{
			log.error("Error in DnR_DataExport_Run_ALCATEL:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	
	@DataProvider
	public Object[][] getData14(){
		return Utility.getData(xls,"TC14");
	}
	
	
	@Test (dataProvider="getData14", priority = 6)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Recon_Run_ALCATEL(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Recon_Run_ALCATEL");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Trigger the Start Session of Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataFlow("Alcatel", "Recon", 1);
			
			log.debug("Leaving DnR_Recon_Run_ALCATEL");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Recon_Run_ALCATEL:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	@DataProvider
	public Object[][] getData15(){
		return Utility.getData(xls,"TC15");
	}
	
	@Test (dataProvider="getData15", priority = 7)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Data_ALCATEL(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Data_ALCATEL");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();
			
			ExtentTest childTest1=extent.startTest("Validate Recon Reports");
			test.appendChild(childTest1);
			order=createChildTest(childTest1,extent,order);
			order.validateReconReports("[SHAW] Alcatel 5620", "FirstRun");
			
			ExtentTest childTest2=extent.startTest("Validate IDB SDB Counts");
			test.appendChild(childTest2);
			order=createChildTest(childTest2,extent,order);
			order.validateIDBSDBTableCount("Alcatel");
		
			ExtentTest childTest3=extent.startTest("Verify FallOuts");
			test.appendChild(childTest3);
			order=createChildTest(childTest3,extent,order);
			order.verifyFallOuts();
			
			ExtentTest childTest4=extent.startTest("Capture Recon Report Actions");
			test.appendChild(childTest4);
			order=createChildTest(childTest4,extent,order);
			order.runReconQuery("true");
			
			log.debug("Leaving DnR_Validate_Data_ALCATEL");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Data_ALCATEL:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData18(){
		return Utility.getData(xls,"TC18");
	}

	@Test (dataProvider="getData18", priority = 1)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Run_DeleScript_CMTS(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Run_DeleScript_CMTS");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();
			
			ExtentTest childTest0=extent.startTest("Run Delete Script for CMTS");
			test.appendChild(childTest0);
			order=createChildTest(childTest0,extent,order);
			order.runDeleteScript("CMTS");
			
			log.debug("Leaving DnR_Run_DeleScript_CMTS");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Run_DeleScript_CMTS:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	@DataProvider
	public Object[][] getData19(){
		return Utility.getData(xls,"TC19");
	}

	@Test (dataProvider="getData19", priority = 2)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Config_CMTS(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Config_CMTS");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();
			
			ExtentTest childTest1=extent.startTest("Navigate to Documents Integration Interface and Validate the parameters");
			test.appendChild(childTest1);
			order=createChildTest(childTest1,extent,order);
			order.navigateToIntegrationInterface("CMTS");
			
			log.debug("Leaving DnR_Validate_Config_CMTS");

		}catch(Exception e)
		{
			log.error("Error in DnR_Validate_Config_CMTS:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	@DataProvider
	public Object[][] getData20(){
		return Utility.getData(xls,"TC20");
	}
	
	
	@Test (dataProvider="getData20", priority = 3)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_GenerateScripts_CMTS(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_GenerateScripts_CMTS");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Run Pre-Requisite Scripts required for Data Export and Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataTransition("CMTS");
			
			
			log.debug("Leaving DnR_GenerateScripts_CMTS");
			}catch(Exception e)
			{
				log.error("Error in DnR_GenerateScripts_CMTS:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData21(){
		return Utility.getData(xls,"TC21");
	}

	@Test (dataProvider="getData21", priority = 4)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_DataExport_Run_CMTS(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_DataExport_Run_CMTS");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest0=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			order=createChildTest(childTest0,extent,order);
			landing.LoginOSS();
			
			ExtentTest childTest1=extent.startTest("Trigger the Start Session of Data Export");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataFlow("CMTS", "DE", 1);
			
			ExtentTest childTest2=extent.startTest("Validate the Zip File Creation after Data Export");
			test.appendChild(childTest2);
			order=createChildTest(childTest2,extent,order);
			order.validatePuttyPostCondition("CMTS", 1);
			
			
			log.debug("Leaving DnR_DataExport_Run_CMTS");

		}catch(Exception e)
		{
			log.error("Error in DnR_DataExport_Run_CMTS:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	
	@DataProvider
	public Object[][] getData22(){
		return Utility.getData(xls,"TC22");
	}
	
	
	@Test (dataProvider="getData22", priority = 5)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Recon_Run_CMTS(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Recon_Run_CMTS");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Trigger the Start Session of Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataFlow("CMTS", "Recon", 1);
			
			log.debug("Leaving DnR_Recon_Run_CMTS");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Recon_Run_CMTS:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	@DataProvider
	public Object[][] getData23(){
		return Utility.getData(xls,"TC23");
	}
	
	@Test (dataProvider="getData23", priority = 6)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Data_CMTS(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Data_CMTS");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Validate Recon Reports");
			test.appendChild(childTest1);
			order=createChildTest(childTest1,extent,order);
			order.validateReconReports("CMTS Reconciliation", "FirstRun");
			
			ExtentTest childTest2=extent.startTest("Validate IDB SDB Counts");
			test.appendChild(childTest2);
			order=createChildTest(childTest2,extent,order);
			order.validateIDBSDBTableCount("CMTS");
			
			ExtentTest childTest3=extent.startTest("Verify FallOuts");
			test.appendChild(childTest3);
			order=createChildTest(childTest3,extent,order);
			order.verifyFallOuts();   //Needs Change
			
			ExtentTest childTest4=extent.startTest("Run Recon Query");
			test.appendChild(childTest4);
			order=createChildTest(childTest4,extent,order);
			order.runReconQuery("true");
			
			log.debug("Leaving DnR_Validate_Data_CMTS");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Data_CMTS:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData26(){
		return Utility.getData(xls,"TC26");
	}

	@Test (dataProvider="getData26", priority = 1)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Run_DeleScript_CMTS_OnDemand(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Run_DeleScript_CMTS_OnDemand");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();
			
			ExtentTest childTest0=extent.startTest("Run Delete Script for CMTS-OnDemand");
			test.appendChild(childTest0);
			order=createChildTest(childTest0,extent,order);
			order.runDeleteScript("CMTS-OnDemand");
			
			log.debug("Leaving DnR_Run_DeleScript_CMTS_OnDemand");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Run_DeleScript_CMTS_OnDemand:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	//GenerateScripts TC's
	
	@DataProvider
	public Object[][] getData27(){
		return Utility.getData(xls,"TC27");
	}
	
	@Test (dataProvider="getData27", priority = 3)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Config_CMTS_OnDemand(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Config_CMTS_OnDemand");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();
			
			
			ExtentTest childTest2=extent.startTest("Navigate to Documents Integration Interface and Validate the parameters");
			test.appendChild(childTest2);
			order=createChildTest(childTest2,extent,order);
			order.navigateToIntegrationInterface("CMTS-OnDemand");
			
			log.debug("Leaving DnR_Validate_Config_CMTS_OnDemand");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Validate_Config_CMTS_OnDemand:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	@DataProvider
	public Object[][] getData28(){
		return Utility.getData(xls,"TC28");
	}
	
	
	@Test (dataProvider="getData28", priority = 4)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_GenerateScripts_CMTS_OnDemand(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_GenerateScripts_CMTS_OnDemand");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Run Pre-Requisite Scripts required for Data Export and Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataTransition("CMTS-OnDemand");
			
			log.debug("Leaving DnR_GenerateScripts_CMTS_OnDemand");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_GenerateScripts_CMTS_OnDemand:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData29(){
		return Utility.getData(xls,"TC29");
	}

	@Test (dataProvider="getData29", priority = 1)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_DataExport_Run_CMTS_OnDemand(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_DataExport_Run_CMTS_OnDemand");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest0=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			order=createChildTest(childTest0,extent,order);
			landing.LoginOSS();
			
			ExtentTest childTest1=extent.startTest("Trigger the Start Session of Data Export");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataFlow("CMTS-OnDemand", "DE", 1);
			
			ExtentTest childTest2=extent.startTest("Validate the Zip File Creation after Data Export");
			test.appendChild(childTest2);
			order=createChildTest(childTest2,extent,order);
			order.validatePuttyPostCondition("CMTS-OnDemand", 1);
			
			
			log.debug("Leaving DnR_DataExport_Run_CMTS_OnDemand");

		}catch(Exception e)
		{
			log.error("Error in DnR_DataExport_Run_CMTS_OnDemand:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	
	@DataProvider
	public Object[][] getData30(){
		return Utility.getData(xls,"TC30");
	}
	
	
	@Test (dataProvider="getData30", priority = 6)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Recon_Run_CMTS_OnDemand(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Recon_Run_CMTS_OnDemand");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Trigger the Start Session of Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataFlow("CMTS-OnDemand", "Recon", 1);
			
			log.debug("Leaving DnR_Recon_Run_CMTS_OnDemand");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Recon_Run_CMTS_OnDemand:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	@DataProvider
	public Object[][] getData31(){
		return Utility.getData(xls,"TC31");
	}
	
	@Test (dataProvider="getData31", priority = 7)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Data_CMTS_OnDemand(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Data_CMTS_OnDemand");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Validate Recon Reports");
			test.appendChild(childTest1);
			order=createChildTest(childTest1,extent,order);
			order.validateReconReports("CMTS OnDemand", "FirstRun");
			
			ExtentTest childTest2=extent.startTest("Validate IDB SDB Counts");
			test.appendChild(childTest2);
			order=createChildTest(childTest2,extent,order);
			order.validateIDBSDBTableCount("CMTS-OnDemand");
			
			ExtentTest childTest3=extent.startTest("Verify FallOuts");
			test.appendChild(childTest3);
			order=createChildTest(childTest3,extent,order);
			order.verifyFallOuts();
			
			ExtentTest childTest4=extent.startTest("Run Recon Query");
			test.appendChild(childTest4);
			order=createChildTest(childTest4,extent,order);
			order.runReconQuery("true");
			
			log.debug("Leaving DnR_Validate_Data_CMTS_OnDemand");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Validate_Data_CMTS_OnDemand:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	
	@DataProvider
	public Object[][] getData34(){
		return Utility.getData(xls,"TC34");
	}

	@Test (dataProvider="getData34", priority = 1)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Run_DeleScript_ETX(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Run_DeleScript_ETX");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();
			
			ExtentTest childTest0=extent.startTest("Run Delete Script for ETX");
			test.appendChild(childTest0);
			order=createChildTest(childTest0,extent,order);
			order.runDeleteScript("ETX");
			
			log.debug("Leaving DnR_Run_DeleScript_ETX");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Run_DeleScript_ETX:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	@DataProvider
	public Object[][] getData35(){
		return Utility.getData(xls,"TC35");
	}
	
	@Test (dataProvider="getData35", priority = 2)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Config_ETX(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Config_ETX");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();
			
			
			ExtentTest childTest2=extent.startTest("Navigate to Documents Integration Interface and Validate the parameters");
			test.appendChild(childTest2);
			order=createChildTest(childTest2,extent,order);
			order.navigateToIntegrationInterface("ETX");
			
			log.debug("Leaving DnR_Validate_Config_ETX");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Validate_Config_ETX:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	@DataProvider
	public Object[][] getData36(){
		return Utility.getData(xls,"TC36");
	}
	
	
	@Test (dataProvider="getData36", priority = 3)
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
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Run Pre-Requisite Scripts required for Data Export and Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataTransition("ETX");
			
			log.debug("Leaving DnR_GenerateScripts_ETX");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_GenerateScripts_ETX:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData37(){
		return Utility.getData(xls,"TC37");
	}
	
	
	@Test (dataProvider="getData37", priority = 4)
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
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();


			ExtentTest childTest1=extent.startTest("Trigger the Start Session of Data Export");
			test.appendChild(childTest1);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataFlow("ETX", "DE", 1);

			ExtentTest childTest2=extent.startTest("Validate the Zip File Creation after Data Export");
			test.appendChild(childTest2);
			order=createChildTest(childTest2,extent,order);
			order.validatePuttyPostCondition("ETX", 1);

			log.debug("Leaving DnR_DataExport_Run_ETX");

		}catch(Exception e)
		{
			log.error("Error in DnR_DataExport_Run_ETX:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	@DataProvider
	public Object[][] getData38(){
		return Utility.getData(xls,"TC38");
	}
	
	
	@Test (dataProvider="getData38", priority = 5)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Recon_Run_ETX(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Recon_Run_ETX");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp7337cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Trigger the Start Session of Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataFlow("ETX", "Recon", 1);
			
			log.debug("Leaving DnR_Recon_Run_ETX");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Recon_Run_ETX:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	@DataProvider
	public Object[][] getData42(){
		return Utility.getData(xls,"TC42");
	}

	@Test (dataProvider="getData42", priority = 1)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Run_DeleScript_Spectrum(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Run_DeleScript_Spectrum");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();
			
			ExtentTest childTest0=extent.startTest("Run Delete Script for Spectrum");
			test.appendChild(childTest0);
			order=createChildTest(childTest0,extent,order);
			order.runDeleteScript("Spectrum");
			
			log.debug("Leaving DnR_Run_DeleScript_Spectrum");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Run_DeleScript_Spectrum:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	
	@DataProvider
	public Object[][] getData43(){
		return Utility.getData(xls,"TC43");
	}
	
	@Test (dataProvider="getData43", priority = 3)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Config_Spectrum(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Config_Spectrum");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();
			
			
			ExtentTest childTest2=extent.startTest("Navigate to Documents Integration Interface and Validate the parameters");
			test.appendChild(childTest2);
			order=createChildTest(childTest2,extent,order);
			order.navigateToIntegrationInterface("Spectrum");
			
			log.debug("Leaving DnR_Validate_Config_Spectrum");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Validate_Config_Spectrum:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	@DataProvider
	public Object[][] getData44(){
		return Utility.getData(xls,"TC44");
	}
	
	
	@Test (dataProvider="getData44", priority = 4)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_GenerateScripts_Spectrum(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_GenerateScripts_Spectrum");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Run Pre-Requisite Scripts required for Data Export and Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataTransition("Spectrum");
			
			log.debug("Leaving DnR_GenerateScripts_Spectrum");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_GenerateScripts_Spectrum:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData45(){
		return Utility.getData(xls,"TC45");
	}
	
	
	@Test (dataProvider="getData45", priority = 5)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_DataExport_Run_Spectrum(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_DataExport_Run_Spectrum");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Trigger the Start Session of Data Export");
			test.appendChild(childTest1);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataFlow("Spectrum", "DE", 1);
			
			ExtentTest childTest2=extent.startTest("Validate the Zip File Creation after Data Export");
			test.appendChild(childTest2);
			order=createChildTest(childTest2,extent,order);
			order.validatePuttyPostCondition("Spectrum");
			
			log.debug("Leaving DnR_DataExport_Run_Spectrum");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_DataExport_Run_Spectrum:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData46(){
		return Utility.getData(xls,"TC46");
	}
	
	
	@Test (dataProvider="getData46", priority = 6)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Recon_Run_Spectrum(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Recon_Run_Spectrum");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Trigger the Start Session of Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataFlow("Spectrum", "Recon", 1);
			
			log.debug("Leaving DnR_Recon_Run_Spectrum");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Recon_Run_Spectrum:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	
	//// 
	
	@DataProvider
	public Object[][] getData50(){
		return Utility.getData(xls,"TC50");
	}

	@Test (dataProvider="getData50", priority = 1)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Run_DeleScript_FDB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Run_DeleScript_FDB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();
			
			ExtentTest childTest0=extent.startTest("Run Delete Script for FDB");
			test.appendChild(childTest0);
			order=createChildTest(childTest0,extent,order);
			order.runDeleteScript("FDB");
			
			log.debug("Leaving DnR_Run_DeleScript_FDB");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Run_DeleScript_FDB:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	
	@DataProvider
	public Object[][] getData51(){
		return Utility.getData(xls,"TC51");
	}
	
	@Test (dataProvider="getData51", priority = 3)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Config_FDB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Config_FDB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();
			
			
			ExtentTest childTest2=extent.startTest("Navigate to Documents Integration Interface and Validate the parameters");
			test.appendChild(childTest2);
			order=createChildTest(childTest2,extent,order);
			order.navigateToIntegrationInterface("FDB");
			
			log.debug("Leaving DnR_Validate_Config_FDB");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Validate_Config_FDB:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	@DataProvider
	public Object[][] getData52(){
		return Utility.getData(xls,"TC52");
	}
	
	
	@Test (dataProvider="getData52", priority = 4)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_GenerateScripts_FDB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_GenerateScripts_FDB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Run Pre-Requisite Scripts required for Data Export and Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataTransition("FDB");
			
			log.debug("Leaving DnR_GenerateScripts_FDB");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_GenerateScripts_FDB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData53(){
		return Utility.getData(xls,"TC53");
	}
	
	
	@Test (dataProvider="getData53", priority = 5)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_DataExport_Run_FDB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_DataExport_Run_FDB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Trigger the Start Session of Data Export");
			test.appendChild(childTest1);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataFlow("FDB", "DE", 1);
			
			ExtentTest childTest2=extent.startTest("Validate the Zip File Creation after Data Export");
			test.appendChild(childTest2);
			order=createChildTest(childTest2,extent,order);
			order.validatePuttyPostCondition("FDB");
			
			log.debug("Leaving DnR_DataExport_Run_FDB");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_DataExport_Run_FDB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData54(){
		return Utility.getData(xls,"TC54");
	}
	
	
	@Test (dataProvider="getData54", priority = 6)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Recon_Run_FDB(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Recon_Run_FDB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Trigger the Start Session of Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataFlow("FDB", "Recon", 1);
			
			log.debug("Leaving DnR_Recon_Run_FDB");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Recon_Run_FDB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	///
	@DataProvider
	public Object[][] getData58(){
		return Utility.getData(xls,"TC58");
	}

	@Test (dataProvider="getData58", priority = 1)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Run_DeleScript_IPV4(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Run_DeleScript_IPV4");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();
			
			ExtentTest childTest0=extent.startTest("Run Delete Script for IPV4");
			test.appendChild(childTest0);
			order=createChildTest(childTest0,extent,order);
			order.runDeleteScript("IPV4");
			
			log.debug("Leaving DnR_Run_DeleScript_IPV4");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Run_DeleScript_IPV4:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	
	@DataProvider
	public Object[][] getData59(){
		return Utility.getData(xls,"TC59");
	}
	
	@Test (dataProvider="getData59", priority = 3)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Config_IPV4(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Config_IPV4");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();
			
			
			ExtentTest childTest2=extent.startTest("Navigate to Documents Integration Interface and Validate the parameters");
			test.appendChild(childTest2);
			order=createChildTest(childTest2,extent,order);
			order.navigateToIntegrationInterface("IPV4");
			
			log.debug("Leaving DnR_Validate_Config_IPV4");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Validate_Config_IPV4:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	@DataProvider
	public Object[][] getData60(){
		return Utility.getData(xls,"TC60");
	}
	
	
	@Test (dataProvider="getData60", priority = 4)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_GenerateScripts_IPV4(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_GenerateScripts_IPV4");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Run Pre-Requisite Scripts required for Data Export and Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataTransition("IPV4");
			
			log.debug("Leaving DnR_GenerateScripts_IPV4");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_GenerateScripts_IPV4:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData61(){
		return Utility.getData(xls,"TC61");
	}
	
	
	@Test (dataProvider="getData61", priority = 5)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_DataExport_Run_IPV4(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_DataExport_Run_IPV4");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Trigger the Start Session of Data Export");
			test.appendChild(childTest1);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataFlow("IPV4", "DE", 1);
			
			ExtentTest childTest2=extent.startTest("Validate the Zip File Creation after Data Export");
			test.appendChild(childTest2);
			order=createChildTest(childTest2,extent,order);
			order.validatePuttyPostCondition("IPV4");
			
			log.debug("Leaving DnR_DataExport_Run_IPV4");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_DataExport_Run_IPV4:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData62(){
		return Utility.getData(xls,"TC62");
	}
	
	
	@Test (dataProvider="getData62", priority = 6)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Recon_Run_IPV4(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Recon_Run_IPV4");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Trigger the Start Session of Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataFlow("IPV4", "Recon", 1);
			
			log.debug("Leaving DnR_Recon_Run_IPV4");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Recon_Run_IPV4:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	
	//
	@DataProvider
	public Object[][] getData66(){
		return Utility.getData(xls,"TC66");
	}

	@Test (dataProvider="getData66", priority = 1)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Run_DeleScript_CIN(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Run_DeleScript_CIN");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();
			
			ExtentTest childTest0=extent.startTest("Run Delete Script for CIN");
			test.appendChild(childTest0);
			order=createChildTest(childTest0,extent,order);
			order.runDeleteScript("CIN");
			
			log.debug("Leaving DnR_Run_DeleScript_CIN");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Run_DeleScript_CIN:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	
	@DataProvider
	public Object[][] getData67(){
		return Utility.getData(xls,"TC67");
	}
	
	@Test (dataProvider="getData67", priority = 3)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Config_CIN(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Config_CIN");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();
			
			
			ExtentTest childTest2=extent.startTest("Navigate to Documents Integration Interface and Validate the parameters");
			test.appendChild(childTest2);
			order=createChildTest(childTest2,extent,order);
			order.navigateToIntegrationInterface("CIN");
			
			log.debug("Leaving DnR_Validate_Config_CIN");
		
		}catch(Exception e)
		{
			log.error("Error in DnR_Validate_Config_CIN:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	@DataProvider
	public Object[][] getData68(){
		return Utility.getData(xls,"TC68");
	}
	
	
	@Test (dataProvider="getData68", priority = 4)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_GenerateScripts_CIN(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_GenerateScripts_CIN");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Run Pre-Requisite Scripts required for Data Export and Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataTransition("CIN");
			
			log.debug("Leaving DnR_GenerateScripts_CIN");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_GenerateScripts_CIN:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData69(){
		return Utility.getData(xls,"TC69");
	}
	
	
	@Test (dataProvider="getData69", priority = 5)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_DataExport_Run_CIN(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_DataExport_Run_CIN");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Trigger the Start Session of Data Export");
			test.appendChild(childTest1);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataFlow("CIN", "DE", 1);
			
			ExtentTest childTest2=extent.startTest("Validate the Zip File Creation after Data Export");
			test.appendChild(childTest2);
			order=createChildTest(childTest2,extent,order);
			order.validatePuttyPostCondition("CIN");
			
			log.debug("Leaving DnR_DataExport_Run_CIN");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_DataExport_Run_CIN:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	@DataProvider
	public Object[][] getData70(){
		return Utility.getData(xls,"TC70");
	}
	
	
	@Test (dataProvider="getData70", priority = 6)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Recon_Run_CIN(Hashtable<String,String> data) throws Exception{
	try
		{
			log.debug("Entering DnR_Recon_Run_CIN");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to Server : http://devapp735cn.netcracker.com:6830");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			order=createChildTest(childTest,extent,order);
			landing.LoginOSS();

			
			ExtentTest childTest1=extent.startTest("Trigger the Start Session of Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			order=createChildTest(childTest1,extent,order);
			order.navigateToDataFlow("CIN", "Recon", 1);
			
			log.debug("Leaving DnR_Recon_Run_CIN");
			
			}catch(Exception e)
			{
				log.error("Error in DnR_Recon_Run_CIN:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
	}
	*/
}
