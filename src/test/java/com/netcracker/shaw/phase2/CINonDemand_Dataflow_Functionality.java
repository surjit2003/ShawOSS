package com.netcracker.shaw.phase2;

import java.util.Hashtable;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.netcracker.shaw.at_shaw_sd.jdbc.JDBCValidator;
import com.netcracker.shaw.at_shaw_sd.pageobject.LandingPage;
import com.netcracker.shaw.at_shaw_sd.pageobject.PostInstallationPage;
import com.netcracker.shaw.at_shaw_sd.pageobject.DnRFlowPage;
import com.netcracker.shaw.at_shaw_sd.util.Utility;
import com.netcracker.shaw.setup.SeleniumTestUp;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CINonDemand_Dataflow_Functionality extends SeleniumTestUp{

	LandingPage landing=null;
	DnRFlowPage dnrFlow =null;
	JDBCValidator jdbc=null;
	PostInstallationPage piFlow = null;
	
	Logger log=Logger.getLogger(CINonDemand_Dataflow_Functionality.class);

	/* OSSNETCRACKER-299
	 * Test Scenario : To validate Integration Interface Catalog details for CIN-OnDemand
	 */

	@DataProvider
	public Object[][] getData429(){
		return Utility.getData(xls,"TC429");
	}

	@Test (dataProvider="getData429", priority = 429)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Config_CIN_OnDemand(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Config_CIN_OnDemand");
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
			dnrFlow.navigateToIntegrationInterface("CIN-OnDemand", fetchLoc, storageLoc, null);

			log.debug("Leaving DnR_Validate_Config_CIN_OnDemand");

		}catch(Exception e)
		{
			log.error("Error in DnR_Validate_Config_CIN_OnDemand:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-299
	 * Test Scenario : To validate DataFlow Session for CIN-OnDemand with dns name.
	 */
	
	@DataProvider
	public Object[][] getData430(){
		return Utility.getData(xls,"TC430");
	}


	@Test (dataProvider="getData430", priority = 430)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidateCINOnDemand(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidateCINOnDemand");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			String dnsName = data.get("DNS_NAME");

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Start Session of Data Export and Reconciliation");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("CIN-OnDemand", "DR", dnsName, "SingleRun", "No");

			log.debug("Leaving DnR_toValidateCINOnDemand");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidateCINOnDemand:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-299
	 * Test Scenario : To verify whether the dns named folder created after the DataFlow Session for CIN-OnDemand.
	 */
	
	@DataProvider
	public Object[][] getData431(){
		return Utility.getData(xls,"TC431");
	}


	@Test (dataProvider="getData431", priority = 431)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Verify_DNS_Folder_CIN_OnDemand(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Verify_DNS_Folder_CIN_OnDemand");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			String dnsName = data.get("DNS_NAME");

			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Validate the Zip folder Creation for CIN-OnDemand");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.validatePuttyPostCondition("CIN-OnDemand", 1, dnsName);

			log.debug("Leaving DnR_Verify_DNS_Folder_CIN_OnDemand");

		}catch(Exception e)
		{
			log.error("Error in DnR_Verify_DNS_Folder_CIN_OnDemand:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-299
	 * Test Scenario : To validate data as expected after the DataFlow Session for CIN-OnDemand.
	 */
	
	@DataProvider
	public Object[][] getData432(){
		return Utility.getData(xls,"TC432");
	}

	@Test (dataProvider="getData432", priority = 432, dependsOnMethods = "DnR_toValidateCINOnDemand" )
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_Validate_Data_CIN_OnDemand(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_Validate_Data_CIN_OnDemand");
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
			dnrFlow.validateIDBSDBTableCount("CIN-OnDemand");

			ExtentTest childTest3=extent.startTest("Verify FallOuts");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			String sessionId = dnrFlow.verifyFallOuts("CIN-OnDemand");

			ExtentTest childTest4=extent.startTest("Run Recon Query");
			test.appendChild(childTest4);
			dnrFlow=createChildTest(childTest4,extent,dnrFlow);
			dnrFlow.runReconQuery("CIN-OnDemand", "FirstRun", sessionId);

			log.debug("Leaving DnR_Validate_Data_CIN_OnDemand");

		}catch(Exception e)
		{
			log.error("Error in DnR_Validate_Data_CIN_OnDemand:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

}
