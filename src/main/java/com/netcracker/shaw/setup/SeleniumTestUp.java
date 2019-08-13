package com.netcracker.shaw.setup;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.netcracker.shaw.at_shaw_sd.pageobject.LandingPage;
import com.netcracker.shaw.at_shaw_sd.pageobject.PostInstallationPage;
import com.netcracker.shaw.at_shaw_sd.pageobject.RIFlowPage;
import com.netcracker.shaw.at_shaw_sd.pageobject.VLANFlowPage;
import com.netcracker.shaw.at_shaw_sd.soap.SOAPHelper;
import com.netcracker.shaw.at_shaw_sd.pageobject.CustomizingValidationReportPage;
import com.netcracker.shaw.at_shaw_sd.pageobject.DnRFlowPage;
import com.netcracker.shaw.at_shaw_sd.pageobject.IPPlannerReportsPage;
import com.netcracker.shaw.at_shaw_sd.util.Constants;
import com.netcracker.shaw.at_shaw_sd.util.ExcelOperation;
import com.netcracker.shaw.at_shaw_sd.util.Utility;
import com.netcracker.shaw.report.ExtentReportManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
public class SeleniumTestUp extends TestListenerAdapter{
	

	public static String NtwElementSrlNo=Utility.getValueFromPropertyFile("NtwElementSrlNum");
	public String exportWorks = Utility.getValueFromPropertyFile("isDEExpectedToPass");
	public static WebDriver DRIVER;
	ExtentReportManager ereport=new ExtentReportManager();
	public ExcelOperation xls =new ExcelOperation(Constants.TestDataSuite_XLS);
	//public static ExtentReports extent = ExtentReportManager.getInstance();
	//public static ExtentReports rep = ExtentReporManager.getInstance();
	public ExtentReports extent;
	public ExtentTest test;
	public static String testName;
	public String tcid;
	public static String sheetName;
	protected Logger log = Logger.getLogger(SeleniumTestUp.class); 

	public String getTcid() 
	{
		return tcid;
	}

	public void setTcid(String tcid) 
	{
		this.tcid = tcid;
	}
	
	public void setSrlNo(String srlNo){
		this.NtwElementSrlNo=srlNo;
	}
	
	
	@BeforeMethod
	public void beforeMethod(Method method) throws MalformedURLException
	{ 
		
		//extent = ExtentReportManager.getInstance("");
		
		extent = ereport.getInstance(method.getName());
		test = extent.startTest(method.getName());
		Utility util = new Utility();
		if(Utility.getValueFromPropertyFile("grid").equalsIgnoreCase("true"))
			DRIVER=util.getRemoteBrowser();
		else
			DRIVER=util.getBrowser();
		System.out.println("DRIVER:" + DRIVER);
	}
			
	@AfterMethod
	public void afterMethod(ITestResult result)
	{
		extent.endTest(test);
		extent.flush();
		
		DRIVER.quit();
		
		/*if (result.getStatus() == ITestResult.SKIP)
	    {
			ExtentTestManager.getTest().log(LogStatus.SKIP, "Error found. Retrying the test...");
	    }
	    else if (result.getStatus() == ITestResult.FAILURE)
	    {
	    	ExtentTestManager.getTest().log(LogStatus.FAIL, result.getThrowable());
	    }
	    else if (result.getStatus() == ITestResult.SUCCESS)
	    {
	    	ExtentTestManager.getTest().log(LogStatus.PASS, "Test completed successfully");
	    }*/
	}

	/*public DnRFlowPage createChildTest(ExtentTest test,ExtentReports rep,DnRFlowPage o,WebDriver driver)
	{
		//test=rep.startTest(TestName);
		o=new DnRFlowPage(test,driver);
		o.setTest(test);
		o.setDriver(driver);
		return o;
	}
*/
	public LandingPage createChildTest(ExtentTest test,ExtentReports rep,LandingPage l, WebDriver driver)
	{
		//test=rep.startTest(TestName);
		l=new LandingPage(test,driver);
		l.setTest(test);
		l.setDriver(driver);
		return l;
	}
	
	public DnRFlowPage createChildTest(ExtentTest test,ExtentReports rep,DnRFlowPage o)
	{
		//test=rep.startTest(TestName);
		o=new DnRFlowPage(test);
		o.setTest(test);
		return o;
	}
	
	public VLANFlowPage createChildTest(ExtentTest test,ExtentReports rep,VLANFlowPage v)
	{
		//test=rep.startTest(TestName);
		v=new VLANFlowPage(test);
		v.setTest(test);
		return v;
	}
	
	public RIFlowPage createChildTest(ExtentTest test,ExtentReports rep, RIFlowPage r)
	{
		//test=rep.startTest(TestName);
		r=new RIFlowPage(test);
		r.setTest(test);
		return r;
	}

	public LandingPage createChildTest(ExtentTest test,ExtentReports rep,LandingPage l)
	{
		//test=rep.startTest(TestName);
		l=new LandingPage(test);
		l.setTest(test);
		return l;
	}
	
	public SOAPHelper createChildTest(ExtentTest test,ExtentReports rep,SOAPHelper s)
	{
		//test=rep.startTest(TestName);
		//s=new SOAPHelper(test);
		s.setTest(test);
		return s;
	}
	
	public PostInstallationPage createChildTest(ExtentTest test,ExtentReports rep, PostInstallationPage p)
	{
		//test=rep.startTest(TestName);
		p=new PostInstallationPage(test);
		p.setTest(test);
		return p;
	}
	
	public CustomizingValidationReportPage createChildTest(ExtentTest test,ExtentReports rep, CustomizingValidationReportPage c)
	{
		//test=rep.startTest(TestName);
		c=new CustomizingValidationReportPage(test);
		c.setTest(test);
		return c;
	}
	
	public IPPlannerReportsPage createChildTest(ExtentTest test,ExtentReports rep, IPPlannerReportsPage i)
	{
		//test=rep.startTest(TestName);
		i=new IPPlannerReportsPage(test);
		i.setTest(test);
		return i;
	}
	
	@DataProvider
	public Object[][] getData() {
		return Utility.getData(xls, tcid);
	}
}

	