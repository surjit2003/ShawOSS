package com.netcracker.shaw.phase2;

import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.netcracker.shaw.at_shaw_sd.pageobject.LandingPage;
import com.netcracker.shaw.at_shaw_sd.pageobject.IPPlannerReportsPage;
import com.netcracker.shaw.at_shaw_sd.util.Utility;
import com.netcracker.shaw.setup.SeleniumTestUp;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class IPV4_Reports_Functionality extends SeleniumTestUp{

	LandingPage landing=null;
	IPPlannerReportsPage ipFlow = null;
	
	Logger log=Logger.getLogger(IPV4_Reports_Functionality.class);


	/* OSSNETCRACKER-391
	 * Test Scenario : To Verify the reports that show list of ALL Available IP ranges.
	 */
	
	@DataProvider
	public Object[][] getData425(){
		return Utility.getData(xls,"TC425");
	}

	@Test (dataProvider="getData425", priority = 425)
	@SuppressWarnings(value={ "rawtypes" })
	public void IPV4_toValidate_All_IPRangeAvailable(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering IPV4_toValidate_All_IPRangeAvailable");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Navigate to IP Planner Reports and Check All Available IP Ranges Report");
			test.appendChild(childTest1);
			ipFlow=createChildTest(childTest1,extent,ipFlow);
			ipFlow.navigatetoIPPlannerReports("All Available IP Ranges Report");

			log.debug("Leaving IPV4_toValidate_All_IPRangeAvailable");

		}catch(Exception e)
		{
			log.error("Error in IPV4_toValidate_All_IPRangeAvailable:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-391
	 * Test Scenario : To Verify the reports that show list of ALL Assigned IP ranges.
	 */
	
	@DataProvider
	public Object[][] getData426(){
		return Utility.getData(xls,"TC426");
	}

	@Test (dataProvider="getData426", priority = 426)
	@SuppressWarnings(value={ "rawtypes" })
	public void IPV4_toValidate_All_IPRangeAssigned(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering IPV4_toValidate_All_IPRangeAssigned");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Navigate to IP Planner Reports and Check All Assigned IP Ranges Report");
			test.appendChild(childTest1);
			ipFlow=createChildTest(childTest1,extent,ipFlow);
			ipFlow.navigatetoIPPlannerReports("All Assigned IP Ranges Report");

			log.debug("Leaving IPV4_toValidate_All_IPRangeAssigned");

		}catch(Exception e)
		{
			log.error("Error in IPV4_toValidate_All_IPRangeAssigned:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-391
	 * Test Scenario : To Verify the reports that show list of Assigned IP ranges.
	 */

	@DataProvider
	public Object[][] getData427(){
		return Utility.getData(xls,"TC427");
	}

	@Test (dataProvider="getData427", priority = 427)
	@SuppressWarnings(value={ "rawtypes" })
	public void IPV4_toValidate_IPRangeAssigned(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering IPV4_toValidate_IPRangeAssigned");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Navigate to IP Planner Reports and Check Assigned IP Ranges Report with Display Reserved Set to YES and NO");
			test.appendChild(childTest1);
			ipFlow=createChildTest(childTest1,extent,ipFlow);
			ipFlow.navigatetoIPPlannerReports("Assigned IP Ranges Report");

			log.debug("Leaving IPV4_toValidate_IPRangeAssigned");

		}catch(Exception e)
		{
			log.error("Error in IPV4_toValidate_IPRangeAssigned:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-391
	 * Test Scenario : To Verify the reports that show list of Released IP ranges.
	 */
	
	@DataProvider
	public Object[][] getData428(){
		return Utility.getData(xls,"TC428");
	}

	@Test (dataProvider="getData428", priority = 428)
	@SuppressWarnings(value={ "rawtypes" })
	public void IPV4_toValidate_IPRangeReleased(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering IPV4_toValidate_IPRangeReleased");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Navigate to IP Planner Reports and Check Released IP Ranges Report");
			test.appendChild(childTest1);
			ipFlow=createChildTest(childTest1,extent,ipFlow);
			ipFlow.navigatetoIPPlannerReports("Released IP Ranges Report");

			log.debug("Leaving IPV4_toValidate_IPRangeReleased");

		}catch(Exception e)
		{
			log.error("Error in IPV4_toValidate_IPRangeReleased:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

}
