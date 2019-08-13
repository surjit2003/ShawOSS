package com.netcracker.shaw.at_shaw_sd.pageobject;

import static com.netcracker.shaw.element.pageor.LandingPageElement.*;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.netcracker.shaw.at_shaw_sd.util.Utility;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@SuppressWarnings(value = {"unchecked", "rawtypes"})
public class LandingPage<LandingPageElement> extends BasePage {
	public ExtentTest test;
	Logger log = Logger.getLogger(LandingPage.class);
	
	public LandingPage(ExtentTest test)
	{
		super(test);
	}
	
	public LandingPage(ExtentTest test, WebDriver driver)
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
	
	//Added for NEW OSS 
	
	public void LoginOSS() {
		String ExpectedTitle=null;
		if ( Utility.getValueFromPropertyFile("login_user").equalsIgnoreCase("sysadm") )
			ExpectedTitle = "Inventory - NetCracker";
		else
			ExpectedTitle = "Warning";	
		try {
			log.debug("Entering Login");
			driver.manage().window().maximize();
			navigate(Utility.getValueFromPropertyFile("basepage_url_Oss"));
			test.log(LogStatus.INFO, "Open URL", "Open" + Utility.getValueFromPropertyFile("basepage_url_Oss"));
			log.debug("Opening the URL");

			if (isDisplayed(LOGIN))
				test.log(LogStatus.PASS, "Browser opened Successfully", "LOGIN button is visisble");
			else
				test.log(LogStatus.FAIL, "Browser didn't open");

			inputText(USER_NAME_OSS, Utility.getValueFromPropertyFile("login_user"));
			log.debug("Entering User Name");
			wait(1);
			inputText(PASSWORD, Utility.getValueFromPropertyFile("login_pwd")); 
			log.debug("Entering Password");
			wait(1);
			click(LOGIN);
			log.debug("Clicking Login Button");
			wait(2);
			takeScreenShot();
			log.debug("Title::" + driver.getTitle());
			String expectedTitle = driver.getTitle();
			if (!expectedTitle.equalsIgnoreCase(ExpectedTitle)) {
				inputText(USER_NAME_OSS, Utility.getValueFromPropertyFile("login_user"));
				log.debug("Entering User Name");
				wait(1);
				inputText(PASSWORD, Utility.getValueFromPropertyFile("login_pwd"));
				log.debug("Entering Password");
				wait(1);
				click(LOGIN);
			}
			if (!expectedTitle.equalsIgnoreCase(ExpectedTitle)) {
				test.log(LogStatus.FAIL, "Tried to Login two times and still the server is Unable to login", "Please check.");
				Assert.assertTrue(false, "Tried to Login two times and still the server is Unable to login. Please check.");
			}
			if ( Utility.getValueFromPropertyFile("login_user").equalsIgnoreCase("ipmpls"))
				click(CONTINUE_BTN);
			test.log(LogStatus.PASS, "Login", "Logged IN Successfully");
			log.debug("Leaving Login");
		} catch (Exception e) {
			log.error("Error in Login:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	public void navigatetoJobMonitor() {
		String ExpectedTitle = "Job Monitor - NetCracker";
		try {
			log.debug("Entering navigatetoJobMonitor");
			navigate(Utility.getValueFromPropertyFile("job_monitor_url"));
			System.out.println("Job Monitor Path : " + Utility.getValueFromPropertyFile("basepage_url_Oss")+"common/uobject.jsp?object=6092066043013856097");
			wait(2);
			test.log(LogStatus.INFO, "Navigated to Job Monitor Path" + test.addScreenCapture(addScreenshot()));
			log.debug("Leaving navigatetoJobMonitor");
		}catch (Exception e) {
			log.error("Error in navigatetoJobMonitor:" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
}
