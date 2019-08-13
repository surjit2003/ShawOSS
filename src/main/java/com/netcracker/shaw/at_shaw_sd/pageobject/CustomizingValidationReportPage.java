package com.netcracker.shaw.at_shaw_sd.pageobject;


import static com.netcracker.shaw.element.pageor.DnRFlowPageElement.*;
import static com.netcracker.shaw.element.pageor.DnRFlowPageElement.TOP_BUTTON;
import static com.netcracker.shaw.element.pageor.PostInstallationPageElement.CUST_REPO_VIEW_LINK;
import static com.netcracker.shaw.element.pageor.RIFlowPageElement.APPLY_BTN;
import static com.netcracker.shaw.element.pageor.RIFlowPageElement.CREATE_BUTTON;
import static com.netcracker.shaw.element.pageor.CustomizingValidationReportPageElement.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.netcracker.shaw.at_shaw_sd.util.Utility;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@SuppressWarnings(value = {"unchecked", "rawtypes"})
public class CustomizingValidationReportPage<LandingPageElement> extends BasePage {
	public ExtentTest test;
	Logger log = Logger.getLogger(CustomizingValidationReportPage.class);
	
	public CustomizingValidationReportPage(ExtentTest test)
	{
		super(test);
	}
	
	public CustomizingValidationReportPage(ExtentTest test, WebDriver driver)
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
	
	/*F****************************************************************************
	 *
	 * FUNCTION: validateCustomReport
	 *
	 * PURPOSE : To Validate the Custom Report View value is updated under Validation tab.
	 */
	
	public void validateCustomReport(String integration) {
		try {
			log.debug("Entering validateCustomReport for : " + integration);
			wait(2);
			navigate(Utility.getValueFromPropertyFile("documents_url"));
			wait(2);
			click(DATA_TRANSITION_PATH);
			wait(2);
			test.log(LogStatus.INFO, "Navigation to Data Transition path: " + test.addScreenCapture(addScreenshot()));
			click(CONFIGURATION_TAB);
			test.log(LogStatus.INFO, "Navigation to Configuration Tab of Data Transition: " + test.addScreenCapture(addScreenshot()));
			wait(2);
			switch (integration) {
					case "Alcatel":
										navigate(Utility.getValueFromPropertyFile("basepage_url_Oss") + "common/uobject.jsp?tab=_Validation&object=9129574126513844169");
										break;
										
					case "CMTS-IDB":
										navigate(Utility.getValueFromPropertyFile("basepage_url_Oss") + "common/uobject.jsp?tab=_Validation&object=9142712759413303256");
										break;
										
					case "CMTS-SNMP/CLI":
										navigate(Utility.getValueFromPropertyFile("basepage_url_Oss") + "common/uobject.jsp?tab=_Validation&object=9142814507813439516");
										break;
										
					case "Spectrum-IDB":
										navigate(Utility.getValueFromPropertyFile("basepage_url_Oss") + "common/uobject.jsp?tab=_Validation&object=9145078577713996145");
										break;
										
					case "Spectrum-SNMP/CLI":
										navigate(Utility.getValueFromPropertyFile("basepage_url_Oss") + "common/uobject.jsp?tab=_Validation&object=9145078577713997178");
										break;
										
					case "ETX":
										navigate(Utility.getValueFromPropertyFile("basepage_url_Oss") + "common/uobject.jsp?tab=_Validation&object=9151710934913719261");
										break;
										
					case "FDB-IDB":						
										navigate(Utility.getValueFromPropertyFile("basepage_url_Oss") + "common/uobject.jsp?tab=_Validation&object=9142892492913370355");
										break;
										
					case "FDB-CustIDB":						
										navigate(Utility.getValueFromPropertyFile("basepage_url_Oss") + "common/uobject.jsp?tab=_Validation&object=9129574126513844169");
										break;
										
					case "IPV4":
										navigate(Utility.getValueFromPropertyFile("basepage_url_Oss") + "common/uobject.jsp?tab=_Validation&object=9138594454113378874");
										break;
										
					default:
										log.debug("Given Location not found on Page");
					}
			
			if (isElementPresent(CUST_REPO_VIEW_LINK))
			{
				String custRepoLink = getText(CUST_REPO_VIEW_LINK);
				System.out.println("custRepoLink : " + custRepoLink);
				if(custRepoLink.contains("Validation Report View")) {
					test.log(LogStatus.PASS, "Customer Report View Link is Available : " + test.addScreenCapture(addScreenshot()));
				}
			}
			else {
				test.log(LogStatus.FAIL, "Customer Report View Link is NOT Available, Please check: " + test.addScreenCapture(addScreenshot()));
				Assert.assertTrue(false, "Customer Report View Link was NOT Available, hence failing the TC" );
			}
			log.debug("Leaving validateCustomReport : " + integration);
		} catch (Exception e) {
			log.error("Error in validateCustomReport :" + integration + " " + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error in validate CustomReport with Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	/*F****************************************************************************
	 *
	 * FUNCTION: validateLinkToServiceTicket
	 *
	 * PURPOSE : To Validate the link to service Ticket of FallOuts.
	 */
	
	public void validateLinkToServiceTicket(String numOfFalloutsTobeLinked, String svcTicketName) {
		try {
			log.debug("Entering validateLinkToServiceTicket with num of fallouts to be linked : " +  numOfFalloutsTobeLinked + " with svcTicketName : " + svcTicketName);
			navigate(Utility.getValueFromPropertyFile("fallout_url"));
			test.log(LogStatus.INFO, "Navigated to Validation Report url as shown in Snapshot below: " + test.addScreenCapture(addScreenshot()));
			wait(2);
			if ( numOfFalloutsTobeLinked.equalsIgnoreCase("Multiple") )
			{
				if (isElementPresent(MIN_TWO_FALLOUTS))
				{
					click(SEC_FALLOUT_INPUT);
				}else {
					test.log(LogStatus.FAIL, "Minimum Two Fallouts Needed for linking to the Service Ticket, Please check." + test.addScreenCapture(addScreenshot()));
					Assert.assertTrue(false, "Minimum Two Fallouts Needed for linking to the Service Ticket, Please check.");
				}
			}else {

				click(FIRST_FALLOUT_INPUT);
				wait(2);
				test.log(LogStatus.INFO, "Selected the Fallouts to be linked as shown in snapshot below: " + test.addScreenCapture(addScreenshot()));
				click(LINK_SERVICE_TKT);
				wait(2);
				switchPreviousTab();
				wait(2);
				System.out.println("url : " + driver.getCurrentUrl());
				wait(2);
				clickInputCheckBox(svcTicketName);
				test.log(LogStatus.INFO, "Linking the service ticket to Fallout as shown in Snapshot below: " + test.addScreenCapture(addScreenshot()));
				wait(2);
				click(SELECT_BTN);
				wait(2);
				click(SVC_TKT_FILTER);
				wait(2);
				click(SVC_TKT_FILTER_NAME_INPUT);
				wait(2);
				click(APPLY_BTN);
				wait(2);
				test.log(LogStatus.INFO, "Service Ticket Linked to Fallouts as shown in Snapshot below: " + test.addScreenCapture(addScreenshot()));
			}
		}catch (Exception e) {
			log.error("Error in validateLinkToServiceTicket:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Error while Validating the link to Service Ticket");
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	/*F****************************************************************************
	 *
	 * FUNCTION: createServiceTicket
	 *
	 * PURPOSE : To create a New Service Ticket.
	 */
	
	public void createServiceTicket(String svcTicketName) {
		try {
			log.debug("Entering createServiceTicket with svc ticket name : " +  svcTicketName);
			navigate(Utility.getValueFromPropertyFile("documents_url"));
			wait(2);
			clickDynamic("span", "Service Tickets");
			wait(2);
			if (isDisplayed(TOP_BUTTON)) {
				click(TOP_BUTTON);
			}
			wait(2);
			click(NEW_SVC_TICKET);
			wait(2);
			inputText(SVC_TKT_NAME_INPUT, svcTicketName);
			wait(2);
			inputText(DUE_DATE_INPUT, "Jul202030");
			wait(3);
			test.log(LogStatus.INFO, "Creating New Service Ticket as shown in Snapshot below: " + test.addScreenCapture(addScreenshot()));
			clickNthElement(CREATE_BUTTON, 1);
			wait(4);
			log.debug("Leaving createServiceTicket with svc ticket name : " +  svcTicketName);
		}catch (Exception e) {
			log.error("Error in creating new service ticket:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Error while Validating the link to Service Ticket");
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	/*F****************************************************************************
	 *
	 * FUNCTION: validateFiltering
	 *
	 * PURPOSE : To validate the Filtering Options of Validation Report Page.
	 */
	
	public void validateFiltering(String filterType) {
		try {
			log.debug("Entering validateFiltering ");
			boolean majorFallouts = false , minTwotable= false;
			String tableName = null;
			navigate(Utility.getValueFromPropertyFile("fallout_url"));
			wait(2);
			if ( !(isElementPresent(OBJ_NOT_FOUND)) ){
				
				if (isElementPresent(MIN_TWO_FALLOUTS))
				{
					//Majority Column Filtering 
					if ( filterType.equalsIgnoreCase("Majority")) { 

						wait(2);
						click(MAJORITY_FILTER);		
						wait(2);
						if ( isElementPresent(MAJOR_FALLOUTS))
						{	
							majorFallouts = true;
							removeTab();
							wait(2);
							click(INPUT_CHECKBOX_MAJOR); 
							test.log(LogStatus.INFO, "Fallouts Filtering criteria set as shown in snapshot." + test.addScreenCapture(addScreenshot()));
						}else {
							removeTab();
							wait(2);
							click(INPUT_CHECKBOX_MINOR); 
							test.log(LogStatus.INFO, "Fallouts Filtering criteria set as shown in snapshot." + test.addScreenCapture(addScreenshot()));
						}
						wait(2);
						click(APPLY_BTN);
						wait(2);
						if ( majorFallouts == true ) {
							if(isElementPresent(MAJOR_FALLOUTS)) {
								test.log(LogStatus.FAIL, "Major Fallouts should not be present, Please check." + test.addScreenCapture(addScreenshot()));
								Assert.assertTrue(false, "Major Fallouts should not be present, Please check.");
							}
						}else {
							if(isElementPresent(MINOR_FALLOUTS)) {
								test.log(LogStatus.FAIL, "Minor Fallouts should not be present, Please check." + test.addScreenCapture(addScreenshot()));
								Assert.assertTrue(false, "Minor Fallouts should not be present, Please check.");
							}
						}

						test.log(LogStatus.INFO, "Fallouts Filtered as per the criteria as shown in snapshot." + test.addScreenCapture(addScreenshot()));
					}
					else {

						//Table Name Column Filtering 
						click(TABLE_NAME_FILTER);
						removeTab();
						wait(2);
						if ( isElementPresent(TABLE_NAME_LIST3))
						{
							minTwotable = true;
							click(INPUT_CHECKBOX_TABLE3);
							wait(2);
							tableName = getText(TABLE_NAME_LIST3);
							System.out.println("Table Name : " + tableName);
							test.log(LogStatus.INFO, "Fallouts Filtering  with table name : " + tableName + " criteria set as shown in snapshot." + test.addScreenCapture(addScreenshot()));
						}else if (isElementPresent(TABLE_NAME_LIST2)) {
							minTwotable = true;
							click(INPUT_CHECKBOX_TABLE2);
							wait(2);
							tableName = getText(TABLE_NAME_LIST2);
							System.out.println("Table Name : " + tableName);
							test.log(LogStatus.INFO, "Fallouts Filtering  with table name : " + tableName + " criteria set as shown in snapshot." + test.addScreenCapture(addScreenshot()));
						}else {

							test.log(LogStatus.INFO, "Minimum Two Tables Needed for validating Filtering on Table Name Column, Please check." + test.addScreenCapture(addScreenshot()));
						}
						click(APPLY_BTN);
						wait(3);
						//Validation for Table Name Data after Filtering
						if ( minTwotable == true ) {
							List<WebElement> getTableList = getDynamicXpathElementInList(tableName, 0);
							if (getTableList.size() != 0 )
							{	
								test.log(LogStatus.PASS, "Filtering on Table Name Coulmn has worked as expected as shown in snapshot below: " + test.addScreenCapture(addScreenshot()));
								Assert.assertTrue(true, "Filtering on Table Name Coulmn has worked as expected.");

							}else {
								test.log(LogStatus.FAIL, "Filtering on Table Name Coulmn has not worked as expected, Please check." + test.addScreenCapture(addScreenshot()));
								Assert.assertTrue(false, "Filtering on Table Name Coulmn has not worked as expected, Please check.");
							}
						}
						test.log(LogStatus.INFO, "Fallouts Filtered as per the criteria as shown in snapshot." + test.addScreenCapture(addScreenshot()));

					}

				}
				else {
					test.log(LogStatus.FAIL, "Minimum Two Fallouts Needed for Validating the Filtering Options, Please check." + test.addScreenCapture(addScreenshot()));
					Assert.assertTrue(false, "Minimum Two Fallouts Needed for Validating the Filtering Options, Please check.");
				}

			}else {
				test.log(LogStatus.FAIL, "FallOut Url provided in config file does not exist in server, Please check and provide correct url and retest." + test.addScreenCapture(addScreenshot()));
				Assert.assertTrue(false, "FallOut Url provided in config file does not exist, Please check.");

			}
			wait(4);
			log.debug("Leaving validateFiltering");
		}catch (Exception e) {
			log.error("Error in validating the filtering options for Validation Report:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Error while Validating the Filtering Options.");
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	
}
