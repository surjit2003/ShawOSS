package com.netcracker.shaw.at_shaw_sd.pageobject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.netcracker.shaw.at_shaw_sd.util.Constants;
import com.netcracker.shaw.at_shaw_sd.util.Utility;
import com.netcracker.shaw.element.PageElement;
//import com.netcracker.shaw.report.TestListener;
import com.netcracker.shaw.setup.SeleniumTestUp;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/*import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.ExtentReports;*/



public class BasePage<T extends PageElement> {
	protected WebDriver driver = SeleniumTestUp.DRIVER;
	
	/*public WebDriverWait wait = new WebDriverWait(driver,50);
	  protected ExtentReports rep = TestListener.reports;
	  protected ExtentTest test = TestListener.test;*/
	JavascriptExecutor javascript = ((JavascriptExecutor) driver);
	Actions actions = new Actions(driver);
	public ExtentTest test;
	public long DEFAULT_TIME_OUT = Long.parseLong(Utility.getValueFromPropertyFile("timeout"));

	public BasePage(ExtentTest test) 
	{
		this.test = test;
	}
	
	public BasePage(ExtentTest test,WebDriver driver) 
	{
		this.test = test;
		this.driver=driver;
	}
	
	//JavascriptExecutor javascript = ((JavascriptExecutor) driver);
	
	public WebElement getWebElement(T element, String... placeholder) 
	{
		WebElement e1 = driver.findElement(element.getBy(placeholder));

		// return new WebDriverWait(driver,
		// 30).until(ExpectedConditions.elementToBeClickable(element.getBy(placeholder)));
		return e1;
	}

	/*
	 * public WebElement findWebElement(T element, String type1,String...
	 * placeholder) { WebDriverWait wait = new WebDriverWait(driver,100);
	 * switch(type){ case "click": return
	 * wait.until(ExpectedConditions.elementToBeClickable(element.getBy(
	 * placeholder)));
	 * 
	 * case "edit" : return
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(element.getBy(
	 * placeholder)));
	 * 
	 * case "select": return
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(element.getBy(
	 * placeholder)));
	 * 
	 * default: return
	 * wait.until(ExpectedConditions.presenceOfElementLocated(element.getBy(
	 * placeholder)));
	 * 
	 * }
	 * }
	 */
	
	// returns list of webelements
	public List<WebElement> getListElement(T element, String... placeholder) 
	{
		List<WebElement> list = driver.findElements(element.getBy(placeholder));
		return list;
	}

	// clicking on the webelement
	/*public void click(T element) 
	{
		getWebElement(element).click();
		test.log(LogStatus.PASS, "Clicked " + element);
		//log.debug("Clicking the element : "+ element);
		// test.log(Status.PASS,"Clicked "+element);
		
		
	}*/
	
	public void click(T element) 
	{
		try{
		  if(isElementPresent(element)){
		   getWebElement(element).click();
		   test.log(LogStatus.PASS, "Clicked " + element);
		  }
		  else{
			  test.log(LogStatus.FAIL, "Could not click " + element + test.addScreenCapture(addScreenshot()));
			  Assert.fail();
		  }
		}
		catch(Exception e){
			e.printStackTrace();
			test.log(LogStatus.FAIL, "WebElement " + element + " is not available for clicking. Please check");
			Assert.fail();
		}
			
	}
	
	//Click particular element when N num of elements have same xpath 
	public void clickNthElement(T element, int num) 
	{
		getElementInList(element, num).click();
		test.log(LogStatus.PASS, "Click element", "Clicked  " + element);
	}
	
	//supporting function for clickNthElement
	public WebElement getElementInList(T element, int num, String... placeholder) 
	{	
		List<WebElement> list = driver.findElements(element.getBy(placeholder));
		return list.get(num);	
	}
	
	public List<WebElement> getDynamicXpathElementInList(String dynamicName, int size)
	{
		String dynamicXpath = "//*[contains(text(),'" + dynamicName + "')]";
		System.out.println("Xpath = " + dynamicXpath );
		List<WebElement> list = driver.findElements(By.xpath(dynamicXpath));
		return list;
	}
	
	//To support Alert messages 
	public void switchToAlert()
	{
		Alert alt = driver.switchTo().alert();
		String alertText = alt.getText();
		System.out.println("Alert data: " + alertText);
		alt.accept();
	}
	
	public void mouseOver(T element)
	{
		
		Actions a1 = new Actions(driver);
		WebElement e1 = getWebElement(element);
		a1.moveToElement(e1);
		a1.build().perform();
	}
	
	public void clickDynamic(String keyword, String deviceName)
	{
		String dynamicXpath = "//" + keyword+ "[contains(text(),'" + deviceName + "')]";
		//System.out.println("Dynamic Xpath : " + dynamicXpath);
		WebElement e1 = driver.findElement(By.xpath(dynamicXpath));
		e1.click();
	}
	
	public void clickInputCheckBox(String deviceName)
	{
		String dynamicXpath = "//span[contains(text(),'" + deviceName + "')]/../../../td[1]/input";
		WebElement e1 = driver.findElement(By.xpath(dynamicXpath));
		e1.click();
	}
	
	
	public void latestValidateSession(String table)
	{
		Date date = new Date();
		String dynamicXpath = null;
		if ( table.equalsIgnoreCase("SDB"))
		{
			dynamicXpath = "(//td[contains(text(),'" + Constants.SESSION_DATE_FORMAT.format(date) + "')]/../td[2])[1]";
		}
		else
		{
			dynamicXpath = "(//td[contains(text(),'" + Constants.SESSION_DATE_FORMAT.format(date) + "')]/../td[2])[2]";
		}
		try
		{
			WebElement e1 = driver.findElement(By.xpath(dynamicXpath));
			e1.click();
		}catch(Exception e) {
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Latest Validate Session with Current Date is not found" + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
		
	}
	
	
	public WebElement getObject(String docName)
	{
		String dynamicXpath = "//td[contains(text(),'" + docName + "')]/../td[3]";
		WebElement e1 = driver.findElement(By.xpath(dynamicXpath));
		return e1;
	}
	
	
	public void checkDirInBox() throws JSchException,IOException, InterruptedException {
		String host = "qaapp030cn.netcracker.com";
		String user = "netcrk";
		String pasword = "n3w_netcrk";
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		config.put("PreferredAuthentications", "password");

		JSch jsch = new JSch();
		jsch.setConfig(config);
		try {
			
            
			Session session = null;
			session = jsch.getSession(user, host, 22);

			session.setPassword(pasword);

			session.connect();
            		
			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp channelSftp = (ChannelSftp)channel;
			
			channelSftp.cd("QA/IPBB");
			String currentDirectory=channelSftp.pwd();
			String dir="Backup";
			SftpATTRS attrs=null;
			try {
			    attrs = channelSftp.stat(currentDirectory+"/"+dir);
			} catch (Exception e) {
			    System.out.println(currentDirectory+"/"+dir+" not found");
			}

			if (attrs != null) {
			    System.out.println("Directory exists IsDir="+attrs.isDir());
			} else {
			    System.out.println("Creating dir "+dir);
			    channelSftp.mkdir(dir);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	// clearing the webelement
	public void clear(T element) 
	{
		getWebElement(element).clear();
	}

	/*
	 * public void inputPin(T element1, T element2,String value,String...
	 * placeholder) throws IOException{
	 * 
	 * WebElement
	 * e=wait.until(ExpectedConditions.visibilityOfElementLocated(element1.getBy
	 * (placeholder))); if(e.isDisplayed()){ getWebElement(element2).clear();
	 * 
	 * getWebElement(element2).sendKeys(value); }
	 * 
	 * }
	 */

	// sending value to the input text
	public void inputText(T element, String value) 
	{
		clear(element);
		wait("1");
		getWebElement(element).click();
		getWebElement(element).sendKeys(value);
		test.log(LogStatus.PASS, " Enter Text", "Entered value  " + value + " in " + element);
		//log.debug("Entering text "+value +" in field " +element);
	}

	// navigating to url
	public void navigate(String url) 
	{
		driver.get(url);
	}

	public void selectFromList(T element, String value) 
	{
		new Select(getWebElement(element)).selectByVisibleText(value);
		//*newSelect(findWebElement(element,"select")).selectByVisibleText(value);
		// test.log(Status.PASS,"Selected value "+value+ "from: "+element);
	}

	public boolean isElementPresent(T element) 
	{
		List<WebElement> e = null;
		e = getListElement(element);
		//System.out.println("e.size = " + e.size());
		if (e.size() == 0)
			return false;
		else
			return true;
	}

	public int countRowsinTable(T element) 
	{
		return getListElement(element).size();
	}

	public boolean verifyElementPresent(T element) 
	{
		boolean result = isElementPresent(element);
		return result;
	}

	public void wait(String timeout) 
	{
		try {
			Thread.sleep(Long.parseLong(timeout));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void frame(T element, String... placeholder) {
		WebElement e1 = driver.findElement(element.getBy(placeholder));
		driver.switchTo().frame(e1);
	}

	public String getText(T element) 
	{
		return getWebElement(element).getText();
	}
	
	public String getTextNthElement(T element, int num) 
	{
		return getElementInList(element, num).getText();
	}

	public boolean isDisplayed(T element) 
	{
		return getWebElement(element).isDisplayed();
	}

	public String getAttribue(T element, String value) 
	{
		return getWebElement(element).getAttribute(value);
	}

	public String getCssValue(T element, String value) 
	{
		return getWebElement(element).getCssValue(value);
	}

	public void switchWindow() 
	{
		String mainWindowHnd = driver.getWindowHandle();
		Set<String> set = driver.getWindowHandles();
		for (String handle : set) {
			if (!(handle == mainWindowHnd)) {
				driver.switchTo().window(handle);
			}
		}
	}

	public void switchFirstNewTab()
	{
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(1));
	}

	public void switchSecondNewTab() 
	{
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(2));
	}

	public void switchNextTab() throws Exception 
	{
		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(2));
		wait(1);
	}

	public void switchPreviousTab() throws Exception 
	{
		wait(1);
		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(1));
	}

	public void switchFirstTab() throws Exception 
	{
		wait(1);
		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(0));
	}
	
	// call getScreenShotForElement ( , meenakshi.png , Constansts.SCREENSHOT_PATH ) 
	/*public void getScreenShotForElementNow ( T element, String pngName, String location )  
	{
		//Shutterbug.shootElement(driver, element).withName("Visa and travle info3").save("C:\\Users\\kika1116\\ERPWorkspace\\ShutterBug\\ScreenShots");
		Shutterbug.shootElement(driver, getWebElement(element)).withName(pngName).save(location);
		
		//Shutterbug.shootPage(driver,ScrollStrategy.WHOLE_PAGE,500,false).withName("Expected").save(location);
	}*/
	
	public void removeTab() 
	{
		Set<String> windowHandle = driver.getWindowHandles();
		windowHandle.remove(windowHandle.iterator().next());
	}
	/*
	 * public void wait(long seconds) throws Exception { Thread.sleep(seconds);
	 * }
	 */
	
	public void wait(int timeout) throws Exception
	 {
		 long milliSeconds = timeout*1000;
		 Thread.sleep(milliSeconds);
	 }

	public void takeScreenShot() 
	{
		Date d = new Date();
		String screenshotFile = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		String path = Utility.getValueFromPropertyFile("screenshot_path");

		File directory = new File(String.valueOf(Constants.SCREENSHOT_PATH));

		if(!directory.exists()){

			directory.mkdir();
		}
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(directory +"/"+ screenshotFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void switchThirdNewTab() 
	{
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(3));
	}

	public String addScreenShot(String str) throws Exception 
	{
		File directory = new File(String.valueOf(Constants.SCREENSHOT_PATH));

		if(!directory.exists())
		{
			directory.mkdir();
		}
		System.out.println("Dir name is "+directory);
		DateFormat df = new SimpleDateFormat("yyyy_MMM_ hh_mm_ss a");
		Date d = new Date();
		String time = df.format(d);
		System.out.println(time);
		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f, new File(directory + "/"+str + time + ".png"));
		return directory + "/"+str + time + ".png";
	}
	
	public String addScreenshot() 
	{
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		String encodedBase64 = null;
		FileInputStream fileInputStreamReader = null;
		try {
			fileInputStreamReader = new FileInputStream(scrFile);
			byte[] bytes = new byte[(int)scrFile.length()];
			fileInputStreamReader.read(bytes);
			encodedBase64 = new String(Base64.getEncoder().encodeToString(bytes));
			//encodedBase64 =new String(Base64.encodeBase64(bytes), "UTF-8");
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "data:image/png;base64,"+encodedBase64;
	}
	
	public String getCurrentDateTime() {
		
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
		String strDate = dateFormat.format(date);
		String time = dateFormat.format(date);
		System.out.println("Date Value : " + dateFormat.format(date));
		System.out.println("Time Value : " + time);
		return strDate;
	}
	

	public boolean checkStatusComplete(T elementStatus) 
	{
		return getText(elementStatus).equalsIgnoreCase("completed");
	}

	public boolean validateAttributeValue(T element, String attrName, String attrVal) 
	{
		String responseXML = getText(element).trim().replaceAll("(\\s|\n)", "");
		String attr1 = attrName + ">" + attrVal;
		String attr2 = attrName + "=\"" + attrVal + "\"";
		String attr3 = attrName + "</ns3:CharacteristicName><ns3:CharacteristicCategory><ns3:CategoryName>DeviceInfo</ns3:CategoryName></ns3:CharacteristicCategory><ns3:CharacteristicValue><ns3:Value>" + attrVal;
		return (responseXML.contains(attr1) || responseXML.contains(attr2) || responseXML.contains(attr3));
	}

	public void returnToPreviousPage() throws Exception 
	{
		javascript.executeScript("javascript: setTimeout(\"history.go(-3)\", 2000)");
		test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
	}
	
	public void returnToPreviousPageTwosteps() throws Exception 
	{
		javascript.executeScript("javascript: setTimeout(\"history.go(-2)\", 2000)");
		wait(1);
		test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
	}
	
	public void scrollUp() throws Exception 
	{
		JavascriptExecutor javascript = ((JavascriptExecutor) driver);
		javascript.executeScript("window.scrollBy(0,-5000)", "");
		wait(1);
	}

	public void scrollUp600() throws Exception 
	{
		JavascriptExecutor javascript = ((JavascriptExecutor) driver);
		javascript.executeScript("window.scrollBy(0,-600)", "");
		wait(1);
	}
	public void scrollDown600() throws Exception 
	{
		JavascriptExecutor javascript = ((JavascriptExecutor) driver);
		javascript.executeScript("window.scrollBy(0,600)", "");
		wait(2);
		test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
	}
	public void scrollDown() 
	{
		JavascriptExecutor javascript = ((JavascriptExecutor) driver);
		javascript.executeScript("window.scrollBy(0,5000)", "");
		//test.log(LogStatus.INFO, "Snapshot Below: " + test.addScreenCapture(addScreenshot()));
	}
	
	public void scrollDownToElement(T element) 
	{
			
		actions.moveToElement(getWebElement(element));
		actions.perform();
	}
	
	public void scrollIntoView(T element)
	{
	//WebElement e = driver.findElement(By.xpath("//*[text()=' ']"));
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void refreshPage() throws Exception 
	{
		wait(2);
		driver.navigate().refresh();
	}

	public boolean validateAttributeValue(String responseXML, String attributeVal) 
	{
		return responseXML.contains(attributeVal);
	}
}


