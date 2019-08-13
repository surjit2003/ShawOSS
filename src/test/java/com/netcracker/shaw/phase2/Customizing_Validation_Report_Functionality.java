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
import com.netcracker.shaw.at_shaw_sd.pageobject.CustomizingValidationReportPage;
import com.netcracker.shaw.at_shaw_sd.pageobject.DnRFlowPage;
import com.netcracker.shaw.at_shaw_sd.util.Utility;
import com.netcracker.shaw.setup.SeleniumTestUp;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Customizing_Validation_Report_Functionality extends SeleniumTestUp{

	LandingPage landing=null;
	DnRFlowPage dnrFlow =null;
	CustomizingValidationReportPage custValidRepoFlow = null;
	
	Logger log=Logger.getLogger(Customizing_Validation_Report_Functionality.class);

	
	/* OSSNETCRACKER-511
	 * Test Scenario : To validate whether Custom Report View value is updated under Validation tab after the build installation ( Alcatel )
	 */

	@DataProvider
	public Object[][] getData301(){
		return Utility.getData(xls,"TC301");
	}

	@Test (dataProvider="getData301", priority = 301)
	@SuppressWarnings(value={ "rawtypes" })
	public void Customize_toValidate_Custom_Report_Alcatel(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering Customize_toValidate_Custom_Report_Alcatel");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Navigate to Alcatel IDB Table Set and Validation Tab");
			test.appendChild(childTest1);
			custValidRepoFlow=createChildTest(childTest1,extent,custValidRepoFlow);
			custValidRepoFlow.validateCustomReport("Alcatel");

			log.debug("Leaving Customize_toValidate_Custom_Report_Alcatel");

		}catch(Exception e)
		{
			log.error("Error in Customize_toValidate_Custom_Report_Alcatel:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	

	/* OSSNETCRACKER-511
	 * Test Scenario : To validate whether Custom Report View value is updated under Validation tab after the build installation ( ETX )
	 */

	@DataProvider
	public Object[][] getData302(){
		return Utility.getData(xls,"TC302");
	}

	@Test (dataProvider="getData302", priority = 302)
	@SuppressWarnings(value={ "rawtypes" })
	public void Customize_toValidate_Custom_Report_ETX(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering Customize_toValidate_Custom_Report_ETX");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Navigate to ETX IDB Table Set and Validation Tab");
			test.appendChild(childTest1);
			custValidRepoFlow=createChildTest(childTest1,extent,custValidRepoFlow);
			custValidRepoFlow.validateCustomReport("ETX");

			log.debug("Leaving Customize_toValidate_Custom_Report_ETX");

		}catch(Exception e)
		{
			log.error("Error in Customize_toValidate_Custom_Report_ETX:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* OSSNETCRACKER-511
	 * Test Scenario : To validate whether Custom Report View value is updated under Validation tab after the build installation ( CMTS )
	 */

	@DataProvider
	public Object[][] getData303(){
		return Utility.getData(xls,"TC303");
	}

	@Test (dataProvider="getData303", priority = 303)
	@SuppressWarnings(value={ "rawtypes" })
	public void Customize_toValidate_Custom_Report_CMTS(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering Customize_toValidate_Custom_Report_CMTS");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Navigate to CMTS Integration IDB Table Set and Validation Tab");
			test.appendChild(childTest1);
			custValidRepoFlow=createChildTest(childTest1,extent,custValidRepoFlow);
			custValidRepoFlow.validateCustomReport("CMTS-IDB");

			ExtentTest childTest2=extent.startTest("Navigate to CMTS SDB SNMP/CLI Table Set and Validation Tab");
			test.appendChild(childTest2);
			custValidRepoFlow=createChildTest(childTest2,extent,custValidRepoFlow);
			custValidRepoFlow.validateCustomReport("CMTS-SNMP/CLI");

			log.debug("Leaving Customize_toValidate_Custom_Report_CMTS");

		}catch(Exception e)
		{
			log.error("Error in Customize_toValidate_Custom_Report_CMTS:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-511
	 * Test Scenario : To validate whether Custom Report View value is updated under Validation tab after the build installation ( Spectrum )
	 */


	@DataProvider
	public Object[][] getData304(){
		return Utility.getData(xls,"TC304");
	}

	@Test (dataProvider="getData304", priority = 304)
	@SuppressWarnings(value={ "rawtypes" })
	public void Customize_toValidate_Custom_Report_Spectrum(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering Customize_toValidate_Custom_Report_Spectrum");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Navigate to Spectrum Integration IDB Table Set and Validation Tab");
			test.appendChild(childTest1);
			custValidRepoFlow=createChildTest(childTest1,extent,custValidRepoFlow);
			custValidRepoFlow.validateCustomReport("Spectrum-IDB");

			ExtentTest childTest2=extent.startTest("Navigate to Spectrum SDB SNMP/CLI Table Set and Validation Tab");
			test.appendChild(childTest2);
			custValidRepoFlow=createChildTest(childTest2,extent,custValidRepoFlow);
			custValidRepoFlow.validateCustomReport("Spectrum-SNMP/CLI");

			log.debug("Leaving Customize_toValidate_Custom_Report_Spectrum");

		}catch(Exception e)
		{
			log.error("Error in Customize_toValidate_Custom_Report_Spectrum:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-511
	 * Test Scenario : To validate whether Custom Report View value is updated under Validation tab after the build installation ( FDB )
	 */

	@DataProvider
	public Object[][] getData305(){
		return Utility.getData(xls,"TC305");
	}

	@Test (dataProvider="getData305", priority = 305)
	@SuppressWarnings(value={ "rawtypes" })
	public void Customize_toValidate_Custom_Report_FDB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering Customize_toValidate_Custom_Report_FDB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();

			/*ExtentTest childTest1=extent.startTest("Navigate to FDB SHAW Wave 1 INT Customer IDB Table Set and Validation Tab");
			test.appendChild(childTest1);
			custValidRepoFlow=createChildTest(childTest1,extent,custValidRepoFlow);
			custValidRepoFlow.validateCustomReport("FDB-CustIDB");*/

			ExtentTest childTest2=extent.startTest("Navigate to FDB SHAW Wave 1 INT IDB Table Set and Validation Tab");
			test.appendChild(childTest2);
			custValidRepoFlow=createChildTest(childTest2,extent,custValidRepoFlow);
			custValidRepoFlow.validateCustomReport("FDB-IDB");

			log.debug("Leaving Customize_toValidate_Custom_Report_FDB");

		}catch(Exception e)
		{
			log.error("Error in Customize_toValidate_Custom_Report_FDB:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-511
	 * Test Scenario : To validate whether Custom Report View value is updated under Validation tab after the build installation ( IPV4 )
	 */

	@DataProvider
	public Object[][] getData306(){
		return Utility.getData(xls,"TC306");
	}

	@Test (dataProvider="getData306", priority = 306)
	@SuppressWarnings(value={ "rawtypes" })
	public void Customize_toValidate_Custom_Report_IPV4(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering Customize_toValidate_Custom_Report_IPV4");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Navigate to IPV4 IDB Table Set and Validation Tab");
			test.appendChild(childTest1);
			custValidRepoFlow=createChildTest(childTest1,extent,custValidRepoFlow);
			custValidRepoFlow.validateCustomReport("IPV4");

			log.debug("Leaving Customize_toValidate_Custom_Report_IPV4");

		}catch(Exception e)
		{
			log.error("Error in Customize_toValidate_Custom_Report_IPV4:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	// Commented for now, as there is an issue with Link Service Ticket Page. Will Uncomment if the issue gets resolved.
	/*@DataProvider
	public Object[][] getData433(){
		return Utility.getData(xls,"TC433");
	}

	@Test (dataProvider="getData433", priority = 433 )
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidateLinktoSingleServiceticket(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidateLinktoSingleServiceticket");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			String svcTicketName = "AT_SvcTicket_" + Constants.DATE_FORMAT.format(new Date());

			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest2=extent.startTest("Create New Service Ticket");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.createServiceTicket(svcTicketName);

			//Yet to Test
			ExtentTest childTest3=extent.startTest("Link Single Fallout to Service Ticket");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.validateLinkToServiceTicket("Single", "Meena Svc Tckt");

			log.debug("Leaving DnR_toValidateLinktoSingleServiceticket");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidateLinktoSingleServiceticket:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	@DataProvider
	public Object[][] getData434(){
		return Utility.getData(xls,"TC434");
	}

	@Test (dataProvider="getData434", priority = 434 )
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidateLinktoMultipleServiceticket(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidateLinktoMultipleServiceticket");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			String svcTicketName = "AT_SvcTicket_" + Constants.DATE_FORMAT.format(new Date());

			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest2=extent.startTest("Create New Service Ticket");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.createServiceTicket(svcTicketName);

			//Yet to Test
			ExtentTest childTest3=extent.startTest("Link Single Fallout to Service Ticket");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			dnrFlow.validateLinkToServiceTicket("Single", "Meena Svc Tckt");

			log.debug("Leaving DnR_toValidateLinktoMultipleServiceticket");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidateLinktoMultipleServiceticket:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}*/

	/* OSSNETCRACKER-509
	 * Test Scenario : To validate Validation Report Page Filtering
	 */

	@DataProvider
	public Object[][] getData435(){
		return Utility.getData(xls,"TC435");
	}

	@Test (dataProvider="getData435", priority = 435 )
	@SuppressWarnings(value={ "rawtypes" })
	public void Customize_toValidate_ValidationReport_Filtering(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering Customize_toValidate_ValidationReport_Filtering");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			dnrFlow=createChildTest(childTest,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest2=extent.startTest("Validate the Filtering for Table Name Column in Validation Report Page");
			test.appendChild(childTest2);
			custValidRepoFlow=createChildTest(childTest2,extent,custValidRepoFlow);
			custValidRepoFlow.validateFiltering("Table Name");

			ExtentTest childTest3=extent.startTest("Validate the Filtering for Majority Column in Validation Report Page");
			test.appendChild(childTest3);
			custValidRepoFlow=createChildTest(childTest3,extent,custValidRepoFlow);
			custValidRepoFlow.validateFiltering("Majority");

			log.debug("Leaving Customize_toValidate_ValidationReport_Filtering");

		}catch(Exception e)
		{
			log.error("Error in Customize_toValidate_ValidationReport_Filtering:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
}
