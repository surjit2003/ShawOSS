package com.netcracker.shaw.at_shaw_sd.pageobject;


import static com.netcracker.shaw.element.pageor.IPPlannerReportsPageElement.*;
import static com.netcracker.shaw.element.pageor.DnRFlowPageElement.PROCEED_BUTTON;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.netcracker.shaw.at_shaw_sd.util.Constants;
import com.netcracker.shaw.at_shaw_sd.util.Utility;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@SuppressWarnings(value = {"unchecked", "rawtypes"})
public class IPPlannerReportsPage<LandingPageElement> extends BasePage {
	public ExtentTest test;
	Logger log = Logger.getLogger(IPPlannerReportsPage.class);
	
	public IPPlannerReportsPage(ExtentTest test)
	{
		super(test);
	}
	
	public IPPlannerReportsPage(ExtentTest test, WebDriver driver)
	{
		super(test,driver);
	}

	public void setTest(ExtentTest test1)
	{
		test=test1;
	}

	public void setDriver(WebDriver driver1)
	{
		driver=driver1;
	}
	
	/*F*****************************************************************************************************
	 *
	 * FUNCTION: navigatetoIPPlannerReports
	 *
	 * PURPOSE : To navigate to IP Planner Reports and validate the reports as needed.  
	 * 
	 */
	public void navigatetoIPPlannerReports(String reportType) {
		try {
			log.debug("Entering navigatetoIPPlannerReports with reportType: " +  reportType);
			wait(1);
			navigate(Utility.getValueFromPropertyFile("basepage_url_Oss") + "report/reports.jsp?report=6030654077013281451&object=9142185320113545658" );
			wait(2);
			click(IP_PLANNER_REPORTS); 
			wait(2);
			switch (reportType) {
			
			case "All Available IP Ranges Report":
				click(ALL_AVAIL_IP_REPORTS_LINK);
				test.log(LogStatus.INFO, "Navigated to All Available IP Ranges Report: " + test.addScreenCapture(addScreenshot()));
				wait(2);
				processIPPlannerAllReports("Available");
				break;
				
			case "All Assigned IP Ranges Report":
				click(ALL_ASSIGNED_IP_REPORTS_LINK);
				test.log(LogStatus.INFO, "Navigated to All Assigned IP Ranges Report: " + test.addScreenCapture(addScreenshot()));
				wait(2);
				processIPPlannerAllReports("Assigned");
				break;

			case "Assigned IP Ranges Report":
				click(ASSIGNED_IP_REPORTS_LINK);
				test.log(LogStatus.INFO, "Navigated to Assigned IP Ranges Report: " + test.addScreenCapture(addScreenshot()));
				wait(2);
				processIPPlannerDRReports("Assigned", "Yes");
				wait(2);
				navigate(Utility.getValueFromPropertyFile("basepage_url_Oss") + "report/reports.jsp?report=6030654077013281451&object=9142185320113545658" );
				wait(2);
				click(IP_PLANNER_REPORTS); 
				wait(2);
				click(ASSIGNED_IP_REPORTS_LINK);
				wait(1);
				processIPPlannerDRReports("Assigned", "No");
				break;

			case "Released IP Ranges Report":
				click(RELEASED_IP_REPORTS_LINK);
				test.log(LogStatus.INFO, "Navigated to Released IP Ranges Report: " + test.addScreenCapture(addScreenshot()));
				wait(2);
				processIPPlannerDRReports("Released", "");
				break;
				
			default:
				log.debug("Please re-check report type name passed to function navigatetoIPPlannerReports");

			}
		
		log.debug("Leaving navigatetoIPPlannerReports");
	} catch (Exception e) {
		log.error("Error in navigatetoIPPlannerReports:" + e.getMessage());
		test.log(LogStatus.ERROR, e);
		test.log(LogStatus.FAIL, "Error while IP Planner Reports Validation ");
		Assert.assertTrue(false, e.getMessage());
	}
	
}
	
	/*F*****************************************************************************************************
	 *
	 * FUNCTION: processIPPlannerAllReports
	 *
	 * PURPOSE : To verify the All Available and All Assigned Reports.  
	 * 
	 */
	public void processIPPlannerAllReports(String reportCategory){ 
		try {
			log.debug("Entering processIPPlannerAllReports with reportCategory: " +  reportCategory);

			inputText(PARENT_IP_RANGE_INPUT, "");
			wait(2);
			inputText(SUB_RANGES_MASK_INPUT, "");
			wait(1);
			click(PROCEED_BUTTON);
			do {
				wait(2);
			}while(!isDisplayed(PARENT_RANGE_ELEMENT));
			wait(2);
			test.log(LogStatus.INFO, "Results of All " + reportCategory+ " IP Ranges Report : " + test.addScreenCapture(addScreenshot()));
			wait(2);
			if (isElementPresent(REPORT_RESULTS_ELEMENT))
			{
				click(REPORT_RESULTS_ELEMENT);
				wait(2);
				String statusOfIpRange = getText(STATUS_OF_IP_RANGE);
				System.out.println("Status of IP Range : " + statusOfIpRange);
				if ( statusOfIpRange.contains(reportCategory))
					test.log(LogStatus.PASS, "Status of IP Range is as Expected " + reportCategory + test.addScreenCapture(addScreenshot()));
				else {
					test.log(LogStatus.FAIL, "Status of IP Range is as not as expected. Please check" + test.addScreenCapture(addScreenshot()));
					Assert.assertTrue(false, "Status of IP Range is as not as expected. Please check");
				}
			}
		}catch (Exception e) {
			log.error("Error in processIPPlannerAllReports:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error while IP Planner Reports Validation ");
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	/*F*****************************************************************************************************
	 *
	 * FUNCTION: processIPPlannerDRReports
	 *
	 * PURPOSE : To verify the Assigned and Reserved Reports.  
	 * 
	 */
	public void processIPPlannerDRReports(String reportCategory, String isDisplayReserve){ 
		try {
			log.debug("Entering processIPPlannerDRReports with reportCategory: " +  reportCategory + " DisplayReserve set to : " + isDisplayReserve );

			inputText(FROM_TIME_INPUT, "01012017");
			wait(2);
			inputText(TO_TIME_INPUT, Constants.IP_PLANNER_DATE_FORMAT.format(new Date()));
			wait(2);
			if ( reportCategory.equalsIgnoreCase("Assigned") )
			{
				if ( isDisplayReserve.equalsIgnoreCase("Yes"))
					selectFromList(DISPLAY_RESERVED_OPTION, "Yes");
				else
					selectFromList(DISPLAY_RESERVED_OPTION, "No");
			}
			wait(2);
			test.log(LogStatus.INFO, "Setting details of " + reportCategory + " IP Ranges Report with Display Reserved set to " +  isDisplayReserve + test.addScreenCapture(addScreenshot()));
			click(PROCEED_BUTTON);
			do {
				wait(2);
			}while(!isDisplayed(TYPE_ELEMENT));
			wait(2);
			test.log(LogStatus.INFO, "Results of " + reportCategory + " IP Ranges Report with Display Reserved set to " +  isDisplayReserve + test.addScreenCapture(addScreenshot()));
			wait(2);
			if (isElementPresent(REPORT_RESULTS_ELEMENT))
			{
				click(REPORT_RESULTS_ELEMENT);
				wait(2);
				test.log(LogStatus.INFO, "First Entry of IP Range's Snapshot  : " + test.addScreenCapture(addScreenshot()));
			}
		}catch (Exception e) {
			log.error("Error in processIPPlannerDRReports:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error while IP Planner Reports Validation ");
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	
}
