package com.netcracker.shaw.phase1;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.netcracker.shaw.at_shaw_sd.jdbc.JDBCValidator;
import com.netcracker.shaw.at_shaw_sd.pageobject.DnRFlowPage;
import com.netcracker.shaw.at_shaw_sd.pageobject.LandingPage;
import com.netcracker.shaw.at_shaw_sd.pageobject.RIFlowPage;
import com.netcracker.shaw.at_shaw_sd.pageobject.VLANFlowPage;
import com.netcracker.shaw.at_shaw_sd.soap.SOAPHelper;
import com.netcracker.shaw.at_shaw_sd.util.Constants;
import com.netcracker.shaw.at_shaw_sd.util.ExcelOperation;
import com.netcracker.shaw.at_shaw_sd.util.Utility;
import com.netcracker.shaw.setup.SeleniumTestUp;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class VLANFlow extends SeleniumTestUp{
	
	//public ExtentReports extent=ExtentReportManager.getInstance();
	
	LandingPage landing=null;
	DnRFlowPage dnrFlow =null;
	VLANFlowPage vlanFlow =null;
	RIFlowPage riFlow = null;
	JDBCValidator jdbc=null;
	SOAPHelper soapHelper=null;
	Logger log=Logger.getLogger(VLANFlow.class);
	
	/* 
	 * Test Scenario : To Validate Reserve VLAN Assignment via Reserve Next Available CVLAN button.
	 */
	
	@DataProvider
	public Object[][] getData111(){
		return Utility.getData(xls,"TC111");
	}

	@Test (dataProvider="getData111", priority = 111)
	@SuppressWarnings(value={ "rawtypes" })
	public void VLAN_Reserve_NEW_VLAN_VIA_GUI(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering VLAN_Reserve_NEW_VLAN_VIA_GUI");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			String poolOption=data.get("VLAN_POOL");
			
			System.out.println("Pool Option : " + poolOption);
			
			ExtentTest childTest1=extent.startTest("Verify VLAN Data before Creating new INVLAN");
			test.appendChild(childTest1);
			vlanFlow=createChildTest(childTest1,extent,vlanFlow);
			vlanFlow.getVlanDataDetails(poolOption);
			int initialSize = vlanFlow.getMapSize(poolOption);
			
			ExtentTest childTest2=extent.startTest("Login to the Server");
			test.appendChild(childTest2);
			landing=createChildTest(childTest2,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest3=extent.startTest("Navigate to Shaw Project");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.selectLocation("ShawProject");
			
			ExtentTest childTest4=extent.startTest("Navigate to VLANs in EOD Network and Reserve New VLAN");
			test.appendChild(childTest4);
			vlanFlow=createChildTest(childTest4,extent,vlanFlow);
			String acctNum = vlanFlow.reserveNewINVLAN(poolOption, "");
			
			ExtentTest childTest5=extent.startTest("Verify VLAN Data after Creating new INVLAN");
			test.appendChild(childTest5);
			vlanFlow=createChildTest(childTest5,extent,vlanFlow);
			vlanFlow.validatePostVlanData("AcctNum", acctNum, poolOption);
			int finalSize = vlanFlow.getMapSize(poolOption);
			vlanFlow.validateVLANMapSize(initialSize, finalSize, "Increment");
			
			log.debug("Leaving VLAN_Reserve_NEW_VLAN_VIA_GUI");
		}catch(Exception e)
		{
			log.error("Error in VLAN_Reserve_NEW_VLAN_VIA_GUI:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To Validate Reserve VLAN Assignment via External System Integration [ESI] calls.
	 */
	
	@DataProvider
	public Object[][] getData112(){
		return Utility.getData(xls,"TC112");
	}

	@Test (dataProvider="getData112", priority = 112)
	@SuppressWarnings(value={ "rawtypes" })
	public void VLAN_Reserve_VLAN_VIA_ESI(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering Reserve_CVLAN_GUI");
			System.out.println("hi");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			String poolOption=data.get("VLAN_POOL");
			System.out.println("Pool Option : " + poolOption);
			
			soapHelper = new SOAPHelper("ReserveINVLAN.xml", Constants.SOAP_DEFAULT_HEADER, "query", test);
			
			ExtentTest childTest=extent.startTest("Trigger the Request of ReserveINVLAN via Soap with New AcctNum");
			test.appendChild(childTest);
			soapHelper=createChildTest(childTest,extent,soapHelper);
			String reqXml = soapHelper.readRequestReplaceExcelValues("ReserveINVLAN_New_Account");
			String responseXml = soapHelper.sendMessageToServerReplaceHeader(reqXml);
			soapHelper.parseXml(responseXml);
			String newInVlan = soapHelper.getAttributeValue("Body/queryResponse/namedQueryResponse/result/response/vlan/cVlanName");
			
			//Check whether the new inner vlan created exists in DB 
			ExtentTest childTest1=extent.startTest("Verify the New Inner Vlan Created Exists in DB");
			test.appendChild(childTest1);
			vlanFlow=createChildTest(childTest1,extent,vlanFlow);
			vlanFlow.validatePostVlanData("INVLAN", newInVlan, poolOption);
			
			log.debug("Leaving VLAN_Reserve_VLAN_VIA_ESI");
		}catch(Exception e)
		{
			log.error("Error in VLAN_Reserve_VLAN_VIA_ESI:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* 
	 * Test Scenario : To Validate Commit VLAN Assignment via External System Integration [ESI] calls 
	 */
	
	@DataProvider
	public Object[][] getData113(){
		return Utility.getData(xls,"TC113");
	}

	@Test (dataProvider="getData113", priority = 113)
	@SuppressWarnings(value={ "rawtypes" })
	public void VLAN_Commit_VLAN_VIA_ESI(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering VLAN_Commit_VLAN_VIA_ESI");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String poolOption=data.get("VLAN_POOL");
			String poolId = xls.getCellData("SOAPRequestData", 2, 4);
			System.out.println("PoolId : " + poolId);
			soapHelper = new SOAPHelper("CommitVLAN.xml", Constants.SOAP_DEFAULT_HEADER, "setEntitiesByValues", test);
			
			ExtentTest childTest1=extent.startTest("Verify VLAN Data prior to Soap Transaction");
			test.appendChild(childTest1);
			vlanFlow=createChildTest(childTest1,extent,vlanFlow);
			vlanFlow.getVlanDataDetails(poolOption);
			
			ExtentTest childTest2=extent.startTest("Trigger the Request of CommitVLAN via Soap");
			test.appendChild(childTest2);
			soapHelper=createChildTest(childTest2,extent,soapHelper);
			String reqXml = soapHelper.readRequestReplaceExcelValues("CommitVLAN");
			String responseXml = soapHelper.sendMessageToServerReplaceHeader(reqXml);
			soapHelper.parseXml(responseXml);
			
			ExtentTest childTest3=extent.startTest("Verify the Status Got Changes to Assigned or Not");
			test.appendChild(childTest3);
			vlanFlow=createChildTest(childTest3,extent,vlanFlow);
			vlanFlow.validateStatus(poolOption, poolId);
			
			log.debug("Leaving VLAN_Commit_VLAN_VIA_ESI");
		}catch(Exception e)
		{
			log.error("Error in VLAN_Commit_VLAN_VIA_ESI:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To Validate Delete VLAN Assignment via External System Integration [ESI] calls.
	 */
	
	@DataProvider
	public Object[][] getData114(){
		return Utility.getData(xls,"TC114");
	}

	@Test (dataProvider="getData114", priority = 114)
	@SuppressWarnings(value={ "rawtypes" })
	public void VLAN_Delete_VLAN_VIA_ESI(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering VLAN_Delete_VLAN_VIA_ESI");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String poolOption=data.get("VLAN_POOL");
			String poolId = xls.getCellData("SOAPRequestData", 1, 6);
			System.out.println("PoolId : " + poolId);
			
			soapHelper = new SOAPHelper("DeleteINVLAN.xml", Constants.SOAP_DEFAULT_HEADER, "removeEntitiesByKeys", test);
			
			ExtentTest childTest1=extent.startTest("Verify VLAN Data prior to Soap Transaction");
			test.appendChild(childTest1);
			vlanFlow=createChildTest(childTest1,extent,vlanFlow);
			vlanFlow.getVlanDataDetails(poolOption);
			
			ExtentTest childTest2=extent.startTest("Trigger the Request of DeleteINVLAN via Soap");
			test.appendChild(childTest2);
			soapHelper=createChildTest(childTest2,extent,soapHelper);
			String reqXml = soapHelper.readRequestReplaceExcelValues("DeleteINVLAN");
			String responseXml = soapHelper.sendMessageToServerReplaceHeader(reqXml);
			soapHelper.parseXml(responseXml);
			
			//CHECK WHETHER VLAN got deleted
			ExtentTest childTest3=extent.startTest("Verify the Status Got Changes to Assigned or Not");
			test.appendChild(childTest3);
			vlanFlow=createChildTest(childTest3,extent,vlanFlow);
			vlanFlow.validateDeleteStatus(poolOption, poolId);
			
			log.debug("Leaving VLAN_Delete_VLAN_VIA_ESI");
		}catch(Exception e)
		{
			log.error("Error in VLAN_Delete_VLAN_VIA_ESI:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To Validate Delete VLAN Assignment via External System Integration [ESI] calls.
	 */
	
	@DataProvider
	public Object[][] getData115(){
		return Utility.getData(xls,"TC115");
	}

	@Test (dataProvider="getData115", priority = 115)
	@SuppressWarnings(value={ "rawtypes" })
	public void VLAN_VERIFY_JOB_SCHEDULER(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering VLAN_VERIFY_JOB_SCHEDULER");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			String poolOption = data.get("VLAN_POOL");
			
			ExtentTest childTest1=extent.startTest("Login to the Server and Navigate to Job Monitor");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			landing.LoginOSS();
			landing.navigatetoJobMonitor();
			
			ExtentTest childTest2=extent.startTest("Check the Parameters and Run Job");
			test.appendChild(childTest2);
			vlanFlow=createChildTest(childTest2,extent,vlanFlow);
			int numOfDays = Integer.parseInt(vlanFlow.getNumOfDays());
			Date runDtm = vlanFlow.runJob();
			
			ExtentTest childTest3=extent.startTest("Get the DB data and verify the data is appropriate");
			test.appendChild(childTest3);
			vlanFlow=createChildTest(childTest3,extent,vlanFlow);
			//vlanFlow.getVlanDataDetails(poolOption);
			vlanFlow.verifyCreationDate(numOfDays, runDtm, poolOption);
			
			log.debug("Leaving VLAN_VERIFY_JOB_SCHEDULER");
		}catch(Exception e)
		{
			log.error("Error in VLAN_VERIFY_JOB_SCHEDULER:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To Verify whether the system takes the corresponding CVLAN ID if the request has the existing Account Number via GUI.
	 */
	
	@DataProvider
	public Object[][] getData116(){
		return Utility.getData(xls,"TC116");
	}

	@Test (dataProvider="getData116", priority = 116)
	@SuppressWarnings(value={ "rawtypes" })
	public void VLAN_VERIFY_EXISTING_ACCT_NUM_GUI(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering VLAN_VERIFY_EXISTING_ACCT_NUM_GUI");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String poolOption=data.get("VLAN_POOL");
			String acctNum=data.get("ACCT_NUM");
			
			if(data.get("ACCT_NUM").equals("")){
				throw new SkipException("Skipping the test as ACCT_NUM column is Null");
			}
			
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			vlanFlow=createChildTest(childTest1,extent,vlanFlow);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Verify VLAN Data before Creating new INVLAN");
			test.appendChild(childTest2);
			vlanFlow=createChildTest(childTest2,extent,vlanFlow);
			vlanFlow.getVlanDataDetails(poolOption);
			int initialSize = vlanFlow.getMapSize(poolOption);
			
			ExtentTest childTest3=extent.startTest("Navigate to Shaw Project");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			vlanFlow=createChildTest(childTest3,extent,vlanFlow);
			dnrFlow.selectLocation("ShawProject");
			
			ExtentTest childTest4=extent.startTest("Navigate to VLANs in EOD Network and Reserve VLAN with Existing Acct Num via GUI");
			test.appendChild(childTest4);
			vlanFlow=createChildTest(childTest4,extent,vlanFlow);
			vlanFlow.reserveNewINVLAN(poolOption, acctNum);
			
			ExtentTest childTest5=extent.startTest("Verify VLAN Data after Creating new INVLAN");
			test.appendChild(childTest5);
			vlanFlow=createChildTest(childTest5,extent,vlanFlow);
			int finalSize = vlanFlow.getMapSize(poolOption);
			vlanFlow.validateVLANMapSize(initialSize, finalSize, "Equals");
		
			log.debug("Leaving VLAN_VERIFY_EXISTING_ACCT_NUM_GUI");
		}catch(Exception e)
		{
			log.error("Error in VLAN_VERIFY_EXISTING_ACCT_NUM_GUI:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To Verify whether the system takes the corresponding CVLAN ID if the request has the existing Account Number via ESI Calls.
	 */
	
	@DataProvider
	public Object[][] getData117(){
		return Utility.getData(xls,"TC117");
	}

	@Test (dataProvider="getData117", priority = 117)
	@SuppressWarnings(value={ "rawtypes" })
	public void VLAN_VERIFY_EXISTING_ACCT_NUM_ESI(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering VLAN_VERIFY_EXISTING_ACCT_NUM_ESI");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String poolOption=data.get("VLAN_POOL");
			soapHelper = new SOAPHelper("ReserveINVLAN.xml", Constants.SOAP_DEFAULT_HEADER, "query", test);
			
			ExtentTest childTest1=extent.startTest("Verify VLAN Data before Creating INVLAN");
			test.appendChild(childTest1);
			vlanFlow=createChildTest(childTest1,extent,vlanFlow);
			vlanFlow.getVlanDataDetails(poolOption);
			int initialSize = vlanFlow.getMapSize(poolOption);
			
			ExtentTest childTest2=extent.startTest("Trigger the Request of ReserveINVLAN with Existing AcctNum via Soap");
			test.appendChild(childTest2);
			soapHelper=createChildTest(childTest2,extent,soapHelper);
			String reqXml = soapHelper.readRequestReplaceExcelValues("ReserveINVLAN_Existing_Account");
			String responseXml = soapHelper.sendMessageToServerReplaceHeader(reqXml);
			soapHelper.parseXml(responseXml);
			String newInVlan = soapHelper.getAttributeValue("Body/queryResponse/namedQueryResponse/result/response/vlan/cVlanName");
			
			ExtentTest childTest3=extent.startTest("Verify VLAN Data after Creating new INVLAN");
			test.appendChild(childTest3);
			vlanFlow=createChildTest(childTest3,extent,vlanFlow);
			int finalSize = vlanFlow.getMapSize(poolOption);
			vlanFlow.validateVLANMapSize(initialSize, finalSize, "Equals");
		
			log.debug("Leaving VLAN_VERIFY_EXISTING_ACCT_NUM_ESI");
		}catch(Exception e)
		{
			log.error("Error in VLAN_VERIFY_EXISTING_ACCT_NUM_ESI:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To Validate Reserve New VLAN with Invalid ID.
	 */
	
	@DataProvider
	public Object[][] getData118(){
		return Utility.getData(xls,"TC118");
	}

	@Test (dataProvider="getData118", priority = 118)
	@SuppressWarnings(value={ "rawtypes" })
	public void VLAN_RESERVE_INVALID_VLAN(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering VLAN_RESERVE_INVALID_VLAN");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			String poolOption = data.get("VLAN_POOL");
			soapHelper = new SOAPHelper("ExhaustINVLAN.xml", Constants.SOAP_DEFAULT_HEADER, "setEntitiesByValues", test);
			
			ExtentTest childTest1=extent.startTest("Verify VLAN Data before exhausting the Pool");
			test.appendChild(childTest1);
			vlanFlow=createChildTest(childTest1,extent,vlanFlow);
			//int finalSize = vlanFlow.getMapSize(poolOption);
			//vlanFlow.validateVLANMapSize(initialSize, finalSize);
			int dbPoolCount = vlanFlow.getVLANCountData(poolOption);
			int maxIteratorValue = 4096 - dbPoolCount;
			System.out.println("maxIteration value : " + maxIteratorValue);
			
			ExtentTest childTest2=extent.startTest("Trigger the Request of ReserveINVLAN via Soap");
			test.appendChild(childTest2);
			soapHelper=createChildTest(childTest2,extent,soapHelper);
			vlanFlow=createChildTest(childTest2,extent,vlanFlow);
			vlanFlow.setSoapHelper(soapHelper);
			String reqXml = soapHelper.readRequestReplaceExcelValues("ExhaustINVLAN");
			String exhaustResponseXml = vlanFlow.prepareExhaustRequest(reqXml, maxIteratorValue);
			//soapHelper.parseXml(exhaustResponseXml);
			//String newInVlan = soapHelper.getAttributeValue("Body/queryResponse/namedQueryResponse/result/response/vlan/cVlanName");
		
			//CHECK for error 
			ExtentTest childTest3=extent.startTest("Validate whether Error Reported in Response");
			test.appendChild(childTest3);
			vlanFlow=createChildTest(childTest3,extent,vlanFlow);
			vlanFlow.setSoapHelper(soapHelper);
			soapHelper=createChildTest(childTest1,extent,soapHelper);
			vlanFlow.validateError(exhaustResponseXml, "Pool Exhausted");
			
			log.debug("Leaving VLAN_RESERVE_INVALID_VLAN");
		}catch(Exception e)
		{
			log.error("Error in VLAN_RESERVE_INVALID_VLAN:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To Validate Commit New VLAN with Invalid ID.
	 */
	
	@DataProvider
	public Object[][] getData119(){
		return Utility.getData(xls,"TC119");
	}

	@Test (dataProvider="getData119", priority = 119)
	@SuppressWarnings(value={ "rawtypes" })
	public void VLAN_COMMIT_INVALID_VLAN(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering VLAN_COMMIT_INVALID_VLAN");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			soapHelper = new SOAPHelper("CommitVLAN.xml", Constants.SOAP_DEFAULT_HEADER, "setEntitiesByValues", test);
			
			ExtentTest childTest=extent.startTest("Trigger the Request of CommitVLAN via Soap");
			test.appendChild(childTest);
			soapHelper=createChildTest(childTest,extent,soapHelper);
			String reqXml = soapHelper.readRequestReplaceExcelValues("CommitVLAN_Invalid_INVLAN");
			String responseXml = soapHelper.sendMessageToServerReplaceHeader(reqXml);
			soapHelper.parseXml(responseXml);
			//String newInVlan = soapHelper.getAttributeValue("Body/queryResponse/namedQueryResponse/result/response/vlan/cVlanName");
			
			//CHECK for error 
			ExtentTest childTest1=extent.startTest("Validate whether Error Reported in Response");
			test.appendChild(childTest1);
			vlanFlow=createChildTest(childTest1,extent,vlanFlow);
			vlanFlow.setSoapHelper(soapHelper);
			soapHelper=createChildTest(childTest1,extent,soapHelper);
			vlanFlow.validateError(responseXml, "Not Found");
			
			log.debug("Leaving VLAN_COMMIT_INVALID_VLAN");
		}catch(Exception e)
		{
			log.error("Error in VLAN_COMMIT_INVALID_VLAN:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To Validate Delete New VLAN with Invalid ID.
	 */
	
	@DataProvider
	public Object[][] getData120(){
		return Utility.getData(xls,"TC120");
	}

	@Test (dataProvider="getData120", priority = 120)
	@SuppressWarnings(value={ "rawtypes" })
	public void VLAN_DELETE_INVALID_VLAN(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering VLAN_DELETE_INVALID_VLAN");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			soapHelper = new SOAPHelper("DeleteINVLAN.xml", Constants.SOAP_DEFAULT_HEADER, "removeEntitiesByKeys", test);
			
			ExtentTest childTest=extent.startTest("Trigger the Request of DeleteINVLAN via Soap");
			test.appendChild(childTest);
			soapHelper=createChildTest(childTest,extent,soapHelper);
			String reqXml = soapHelper.readRequestReplaceExcelValues("DeleteINVLAN_Invalid_INVLAN");
			String responseXml = soapHelper.sendMessageToServerReplaceHeader(reqXml);
			soapHelper.parseXml(responseXml);
			//String newInVlan = soapHelper.getAttributeValue("Body/queryResponse/namedQueryResponse/result/response/vlan/cVlanName");
			
			//CHECK for error 
			ExtentTest childTest1=extent.startTest("Validate whether Error Reported in Response");
			test.appendChild(childTest1);
			vlanFlow=createChildTest(childTest1,extent,vlanFlow);
			vlanFlow.setSoapHelper(soapHelper);
			soapHelper=createChildTest(childTest1,extent,soapHelper);
			vlanFlow.validateError(responseXml, "Not Found");
			
			log.debug("Leaving VLAN_DELETE_INVALID_VLAN");
		}catch(Exception e)
		{
			log.error("Error in VLAN_DELETE_INVALID_VLAN:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To Validate Reserve NEW VLAN while Params Missing.
	 */
	
	@DataProvider
	public Object[][] getData121(){
		return Utility.getData(xls,"TC121");
	}

	@Test (dataProvider="getData121", priority = 121)
	@SuppressWarnings(value={ "rawtypes" })
	public void VLAN_RESERVE_VLAN_WHEN_PARAMS_MISSING(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering VLAN_RESERVE_VLAN_WHEN_PARAMS_MISSING");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			soapHelper = new SOAPHelper("ReserveINVLAN.xml", Constants.SOAP_DEFAULT_HEADER, "query", test);
			
			ExtentTest childTest3=extent.startTest("Trigger the Request of ReserveINVLAN with Missing Network Name");
			test.appendChild(childTest3);
			soapHelper=createChildTest(childTest3,extent,soapHelper);
			String reqXml2 = soapHelper.readRequestReplaceExcelValues("ReserveINVLAN_Missing_NetworkName");
			String responseXml2 = soapHelper.sendMessageToServerReplaceHeader(reqXml2);
			soapHelper.parseXml(responseXml2);
			
			ExtentTest childTest4=extent.startTest("Validate Error Reported in Response");
			test.appendChild(childTest4);
			vlanFlow=createChildTest(childTest4,extent,vlanFlow);
			vlanFlow.setSoapHelper(soapHelper);
			soapHelper=createChildTest(childTest4,extent,soapHelper);
			vlanFlow.validateError(responseXml2, "JavaException");
			
			ExtentTest childTest5=extent.startTest("Trigger the Request of ReserveINVLAN with Missing Pool Name");
			test.appendChild(childTest5);
			soapHelper=createChildTest(childTest5,extent,soapHelper);
			String reqXml3 = soapHelper.readRequestReplaceExcelValues("ReserveINVLAN_Missing_PoolName");
			String responseXml3 = soapHelper.sendMessageToServerReplaceHeader(reqXml3);
			soapHelper.parseXml(responseXml3);
			
			ExtentTest childTest6=extent.startTest("Validate Error Reported in Response");
			test.appendChild(childTest6);
			vlanFlow=createChildTest(childTest6,extent,vlanFlow);
			vlanFlow.setSoapHelper(soapHelper);
			soapHelper=createChildTest(childTest6,extent,soapHelper);
			vlanFlow.validateError(responseXml3, "JavaException");
			
			ExtentTest childTest7=extent.startTest("Trigger the Request of ReserveINVLAN with Missing Account Number");
			test.appendChild(childTest7);
			soapHelper=createChildTest(childTest7,extent,soapHelper);
			String reqXml4 = soapHelper.readRequestReplaceExcelValues("ReserveINVLAN_Missing_AcctNum");
			String responseXml4 = soapHelper.sendMessageToServerReplaceHeader(reqXml4);
			soapHelper.parseXml(responseXml4);
			
			ExtentTest childTest8=extent.startTest("Validate Error Reported in Response");
			test.appendChild(childTest8);
			vlanFlow=createChildTest(childTest8,extent,vlanFlow);
			vlanFlow.setSoapHelper(soapHelper);
			soapHelper=createChildTest(childTest8,extent,soapHelper);
			vlanFlow.validateError(responseXml4, "Account Number");
		
			log.debug("Leaving VLAN_RESERVE_VLAN_WHEN_PARAMS_MISSING");
		}catch(Exception e)
		{
			log.error("Error in VLAN_RESERVE_VLAN_WHEN_PARAMS_MISSING:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To Validate Commit NEW VLAN while Params Missing.
	 */
	
	@DataProvider
	public Object[][] getData122(){
		return Utility.getData(xls,"TC122");
	}

	@Test (dataProvider="getData122", priority = 122)
	@SuppressWarnings(value={ "rawtypes" })
	public void VLAN_COMMIT_VLAN_WHEN_PARAMS_MISSING(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering VLAN_COMMIT_VLAN_WHEN_PARAMS_MISSING");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			soapHelper = new SOAPHelper("CommitVLAN.xml", Constants.SOAP_DEFAULT_HEADER, "setEntitiesByValues", test);

			ExtentTest childTest3=extent.startTest("Trigger the Request of CommitVLAN with Missing Pool Name");
			test.appendChild(childTest3);
			soapHelper=createChildTest(childTest3,extent,soapHelper);
			String reqXml2 = soapHelper.readRequestReplaceExcelValues("CommitVLAN_Missing_PoolName");
			String responseXml2 = soapHelper.sendMessageToServerReplaceHeader(reqXml2);
			soapHelper.parseXml(responseXml2);
			
			ExtentTest childTest4=extent.startTest("Validate Error Reported in Response");
			test.appendChild(childTest4);
			vlanFlow=createChildTest(childTest4,extent,vlanFlow);
			vlanFlow.setSoapHelper(soapHelper);
			soapHelper=createChildTest(childTest4,extent,soapHelper);
			vlanFlow.validateError(responseXml2, "Bad primary key");
			
		
			log.debug("Leaving VLAN_COMMIT_VLAN_WHEN_PARAMS_MISSING");
		}catch(Exception e)
		{
			log.error("Error in VLAN_COMMIT_VLAN_WHEN_PARAMS_MISSING:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* 
	 * Test Scenario : To Validate Delete NEW VLAN while Params Missing.
	 */
	
	@DataProvider
	public Object[][] getData123(){
		return Utility.getData(xls,"TC123");
	}

	@Test (dataProvider="getData123", priority = 123)
	@SuppressWarnings(value={ "rawtypes" })
	public void VLAN_DELETE_VLAN_WHEN_PARAMS_MISSING(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering VLAN_DELETE_VLAN_WHEN_PARAMS_MISSING");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			soapHelper = new SOAPHelper("DeleteINVLAN.xml", Constants.SOAP_DEFAULT_HEADER, "removeEntitiesByKeys", test);
			
			ExtentTest childTest1=extent.startTest("Trigger the Request of DeleteVLAN with Missing Pool Name");
			test.appendChild(childTest1);
			soapHelper=createChildTest(childTest1,extent,soapHelper);
			String reqXml1 = soapHelper.readRequestReplaceExcelValues("DeleteVLAN_Missing_PoolName");
			String responseXml1 = soapHelper.sendMessageToServerReplaceHeader(reqXml1);
			soapHelper.parseXml(responseXml1);
			
			ExtentTest childTest2=extent.startTest("Validate Error Reported in Response");
			test.appendChild(childTest2);
			vlanFlow=createChildTest(childTest2,extent,vlanFlow);
			vlanFlow.setSoapHelper(soapHelper);
			soapHelper=createChildTest(childTest2,extent,soapHelper);
			vlanFlow.validateError(responseXml1, "Bad primary key");
		
			log.debug("Leaving VLAN_DELETE_VLAN_WHEN_PARAMS_MISSING");
		}catch(Exception e)
		{
			log.error("Error in VLAN_DELETE_VLAN_WHEN_PARAMS_MISSING:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
}
