package com.netcracker.shaw.at_shaw_sd.pageobject;
import static com.netcracker.shaw.element.pageor.DnRFlowPageElement.*;
import static com.netcracker.shaw.element.pageor.PostInstallationPageElement.*;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.netcracker.shaw.at_shaw_sd.jdbc.JDBCValidator;
import com.netcracker.shaw.at_shaw_sd.soap.SOAPHelper;
import com.netcracker.shaw.at_shaw_sd.util.Constants;
import com.netcracker.shaw.at_shaw_sd.util.PuttyConnector;
import com.netcracker.shaw.at_shaw_sd.util.Utility;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@SuppressWarnings(value = {"unchecked", "rawtypes"})
public class PostInstallationPage<PostInstallationPageElement> extends BasePage {
	
	private static final TimeUnit SECONDS = null;
	private JDBCValidator jdbc = new JDBCValidator();
	//private PuttyConnector putty = null;
	SOAPHelper soapHelper = null; 
			
	public void setSoapHelper(SOAPHelper soapHelper) {
		this.soapHelper = soapHelper;
	}
	
	Logger log = Logger.getLogger(PostInstallationPage.class);

	public PostInstallationPage(ExtentTest test) {
		super(test);
	}
	public PostInstallationPage(ExtentTest test, WebDriver driver) {
		super(test, driver);
	}
	public void setTest(ExtentTest test1) {
		test = test1;
	}

	public void setDriver(WebDriver driver1) {
		driver = driver1;
	}
	
	
	/*F*****************************************************************************************************
	 *
	 * FUNCTION: getEnvPropFileData
	 *
	 * PURPOSE : To establish connection to putty and read the env.properties file data 
	 * 
	 */
	
	public Map<String, String> getEnvPropFileData()
	{
		Map<String, String> envPropFileMap = new HashMap<String, String>();
		try
		{
			log.debug("Entering getEnvPropFileData");
			PuttyConnector puttyConnector = new PuttyConnector(Utility.getValueFromPropertyFile("putty_srcfolder_login"), Utility.getValueFromPropertyFile("putty_srcfolder_user"), Utility.getValueFromPropertyFile("putty_srcfolder_pwd"), test);
			String folderPath = Utility.getValueFromPropertyFile("env_prop_file_path");
			test.log(LogStatus.INFO, "Connection Established on server :  devapp735cn.netcracker.com");
			boolean folderExists = puttyConnector.checkFolderInBox(folderPath, "No");
			if(folderExists == true )
			{
				envPropFileMap = puttyConnector.getEnvPropFileMap(folderPath);
				test.log(LogStatus.INFO, "<span style='font-weight:bold;'> Environment Properties Detailed List:");
				for (String keySet : envPropFileMap.keySet() )
			    {
			        System.out.println(keySet + ":" + envPropFileMap.get(keySet));
			        test.log(LogStatus.INFO, keySet + " ==> " + envPropFileMap.get(keySet));
			    }
			}
			else
			{
				log.debug("Path : " + folderPath + " does not exists in devapp735cn.netcracker.com");
				test.log(LogStatus.INFO, "Path : " + folderPath + " does not exists in devapp735cn.netcracker.com");
			}
			puttyConnector.closeConnection();
			test.log(LogStatus.PASS, "Leaving getEnvPropFileData");
			
		}catch (Exception e) {
			log.error("Error in getEnvPropFileData :" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error in reading the data from environment properties file");
			Assert.assertTrue(false, e.getMessage());
		}
		return envPropFileMap;
	}
	
	/*F*****************************************************************************************************
	 *
	 * FUNCTION: getMapperData
	 *
	 * PURPOSE : To read the mapped data and set the data into map.  
	 * 
	 */
	
	public Map<String, String> getMapperData()
	{
		Map<String, String> mappedData = new HashMap<String, String>();
		log.debug("Entering getEnvPropFileData");
			try (BufferedReader br = new BufferedReader(new FileReader(Constants.TEST_DATA_FOLDER + "mapper.properties"))) {
			    // read from br
			    String sCurrentLine, key, value;
			    String[] parts = null;
			    
				while ((sCurrentLine = br.readLine()) != null) {
					parts = sCurrentLine.split("=", 2);
			        if (parts.length >= 2)
			        {
			            key = parts[0];
			            value = parts[1];
			            mappedData.put(key, value);
			        } else {
			            System.out.println("ignoring line: " + sCurrentLine);
			        }
				}
				test.log(LogStatus.INFO, "<span style='font-weight:bold;'> Mapper Properties Detailed List");
				for (String keySet : mappedData.keySet() )
			    {
			        System.out.println("Mapped Key : " +  keySet + " Mapped Value :" + mappedData.get(keySet));
			        test.log(LogStatus.INFO,  keySet + " ==> " + mappedData.get(keySet));
			    }
				test.log(LogStatus.PASS, "Leaving getEnvPropFileData");
		}catch (Exception e) {
			log.error("Error in getEnvPropFileData :" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Error in reading the data from environment properties file");
			Assert.assertTrue(false, e.getMessage());
		}
		return mappedData;
	}
	
	
	/*F*****************************************************************************************************
	 *
	 * FUNCTION: getGUIPropData
	 *
	 * PURPOSE : To navigate to appropriate GUI path and get the properties data. 
	 * 
	 */
	public Map<String, String> getGUIPropData(String integName, String location) {
		
		Map<String, String> guiPropDataMap = new HashMap<String, String>();
		try
		{
			log.debug("Entering getGUIPropData with integName : " + integName);
			navigate(Utility.getValueFromPropertyFile("documents_url"));
			wait(1);
			test.log(LogStatus.INFO, "Navigation to Documents Path: " + test.addScreenCapture(addScreenshot()));
			if ( location.equalsIgnoreCase("Interface"))
				click(INTEGRATION_INTERFACE);
			else
				click(DATAFLOW_PROJECT);
			wait(1);
			switch (integName) {

			case "CMTS-Interface":
				click(CMTS_INTERFACE);
				wait(1);
				click(PARAMETERS_TAB);
				wait(1);
				break;

			case "Spectrum-Interface":
				click(SPECTRUM_INTERFACE);
				wait(1);
				click(PARAMETERS_TAB);
				break;

			case "ETX-Interface":
				click(ETX_INTERFACE);
				wait(1);
				break;

			case "FDB-Interface":
				click(FDB_INTERFACE);
				wait(1);
				break;

			case "CIN-Interface":
				click(CIN_INTERFACE);
				wait(1);
				break;
				
			case "CMTS-DataFlow":
				click(CMTS_INTEGRATION);
				wait(1);
				click(DATA_EXPORT);
				wait(1);
				clickNthElement(SNMP_NTW_DISCOVERY, 0);
				wait(2);
				break;
				
			case "CMTS-SnmpFlow":
				navigate(Utility.getValueFromPropertyFile("basepage_url_Oss") + "ncobject.jsp?id=9151541249613957795");
				wait(2);
				break;	
				
			case "ETX-DataFlow":
				click(ETX_INTEGRATION);
				wait(1);
				click(DATA_EXPORT);
				wait(1);
				clickNthElement(SNMP_NTW_DISCOVERY, 0);
				wait(2);
				break;
				
			case "Spectrum-DataFlow":
				click(SPECTRUM_INTEGRATION);
				wait(1);
				click(DATA_EXPORT);
				wait(1);
				clickNthElement(SNMP_NTW_DISCOVERY, 0);
				wait(2);
				break;
				
			case "Spectrum-SnmpFlow":
				navigate(Utility.getValueFromPropertyFile("basepage_url_Oss") + "ncobject.jsp?id=9151550119313561049");
				wait(2);
				break;	
			default:
				log.debug("Please re-check Device name passed to function runReconQuery");

			}
			test.log(LogStatus.INFO, "Navigated to Properties Validation Path: " + test.addScreenCapture(addScreenshot()));
			guiPropDataMap = getDataMap(integName);
			
			test.log(LogStatus.INFO, "Properties to be Validated listed below:");
			for ( String key : guiPropDataMap.keySet())
			{
				System.out.println("GUI Map : " + key + "=" + guiPropDataMap.get(key));
		        test.log(LogStatus.INFO, key + " ==> " + guiPropDataMap.get(key));
			}
			log.debug("Leaving getGUIPropData");
			
		}catch (Exception e) {
			log.error("Error in getGUIPropData :" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error in reading the data from GUI.");
			Assert.assertTrue(false, e.getMessage());
		}
		return guiPropDataMap;
	}
	
	/*F*****************************************************************************************************
	 *
	 * FUNCTION: getDataMap
	 *
	 * PURPOSE : To collect all the parameters that need to be validated from particular page and save them in a MAP. 
	 * 
	 */
	private Map<String, String> getDataMap(String integNameLocation) {
		Map<String, String> guiPropDataMap = new HashMap<String, String>();
		try
		{
			log.debug("Entering getDataMap with integName : " + integNameLocation);
			String dbLinkName, usedinInteg, managementSysName, snmpCommunityStr, discIpRange, discExcludeIpRange, cliPort, snmpPort, telnetPort;
			switch (integNameLocation) {
			
			case "CMTS-Interface":
				managementSysName = getText(MANAGEMENT_SYS_NAME); 
				usedinInteg = getText(USED_IN_INTEG);  
				discIpRange = getText(DISC_IP_RANGE);
				discExcludeIpRange = getText(DISC_EXCLUDE_IP_RANGE);
				snmpCommunityStr = getText(SNMP_COMMUNITY_STR);
				guiPropDataMap.put("is_use", usedinInteg);
				guiPropDataMap.put("management_system_name", managementSysName);
				guiPropDataMap.put("discovery_ip_range", discIpRange);
				guiPropDataMap.put("discovery_exclude_ip_range", discExcludeIpRange);
				break;

			case "Spectrum-Interface":
				
				managementSysName = getText(MANAGEMENT_SYS_NAME); 
				usedinInteg = getText(USED_IN_INTEG);  
				snmpCommunityStr = getText(SNMP_COMMUNITY_STR);
				guiPropDataMap.put("is_use_spectrum", usedinInteg);
				guiPropDataMap.put("management_system_name_spectrum", managementSysName);
				guiPropDataMap.put("discovery_snmp_community_string_spectrum", snmpCommunityStr);
				break;

			case "ETX-Interface":
				managementSysName = getText(MANAGEMENT_SYS_NAME); 
				usedinInteg = getText(USED_IN_INTEG);  
				snmpCommunityStr = getText(SNMP_COMMUNITY_STR);
				guiPropDataMap.put("is_use_etx", usedinInteg);
				guiPropDataMap.put("management_system_name_etx", managementSysName);
				guiPropDataMap.put("discovery_snmp_community_string_etx", snmpCommunityStr);
				break;

			case "FDB-Interface":
				dbLinkName = getText(FDB_DB_LINK_NAME);
				usedinInteg = getText(USED_IN_INTEG);  
				managementSysName = getText(MANAGEMENT_SYS_NAME); 
				guiPropDataMap.put("fdb_link_name", dbLinkName);
				guiPropDataMap.put("is_used", usedinInteg);
				guiPropDataMap.put("management_system_name_fdb", managementSysName);
				break;

			case "CIN-Interface":
				click(PARAMETERS_TAB);
				wait(2);
				managementSysName = getText(MANAGEMENT_SYS_NAME); 
				usedinInteg = getText(USED_IN_INTEG);  
				snmpCommunityStr = getText(SNMP_COMMUNITY_STR);
				guiPropDataMap.put("is_use_cin", usedinInteg);
				guiPropDataMap.put("management_system_name_cin", managementSysName);
				guiPropDataMap.put("discovery_snmp_community_string_cin", snmpCommunityStr);
				break;
				
			case "CMTS-DataFlow":
				
				cliPort = getText(CLI_PORT); 
				telnetPort = getText(TELNET_PORT); 
				guiPropDataMap.put("cli_port", cliPort);
				guiPropDataMap.put("telnet_port", telnetPort);
				break;
				
			case "CMTS-SnmpFlow":

				snmpPort = getText(PORTS_INFO); 
				guiPropDataMap.put("snmp_port", snmpPort);
				break;
				
			case "ETX-DataFlow":
				snmpPort = getText(SNMP_PORT); 
				guiPropDataMap.put("snmp_port_etx", snmpPort);
				System.out.println("snmpPort in ETX = "  + snmpPort);
				break;
				
			case "Spectrum-DataFlow":

				cliPort = getText(CLI_PORT); 
				telnetPort = getText(TELNET_PORT); 
				guiPropDataMap.put("cli_port_spectrum", cliPort);
				guiPropDataMap.put("telnet_port_spectrum", telnetPort);
				break;	
				
			case "Spectrum-SnmpFlow":

				snmpPort = getText(PORTS_INFO); 
				guiPropDataMap.put("snmp_port_spectrum", snmpPort);
				break;

			default:
				log.debug("Please re-check Device name passed to function runReconQuery");

			}
			log.debug("Leaving getDataMap");
			
		}catch (Exception e) {
			log.error("Error in getDataMap :" + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error in reading the data from GUI.");
			Assert.assertTrue(false, e.getMessage());
		}
		return guiPropDataMap;
	}
	
	/*F*****************************************************************************************************
	 *
	 * FUNCTION: compareEnvWithPage
	 *
	 * PURPOSE : To compare the MAP's created for each page with the expected Values. 
	 * 
	 */
	
	public boolean compareEnvWithPage(Map<String, String> envMap, Map<String, String> envVarMapper,
			Map<String, String> pageMap) {
		boolean everythingMatches = true;

		String pageKey, pageValue;
		try {
		for(Entry<String, String> pageEntry : pageMap.entrySet()) {
			pageValue = pageEntry.getValue();
			System.out.println("Checking for " + pageEntry.getKey());
			System.out.println("Page Key Value: " + pageValue);

			pageKey = getMappedValue(pageEntry.getKey(), envVarMapper);
			System.out.println("Mapped Key: " + pageKey);

			if(envMap.containsKey(pageKey)) {
				System.out.println("Page key present in mapper");
				if(envMap.get(pageKey).equalsIgnoreCase(pageValue)) {
					// Everything matches, do nothing.
					System.out.println("Matched!!" + pageKey + " --> " + pageValue);
					test.log(LogStatus.INFO, "Attribute : " + pageKey + " has been matched with value : "+ envMap.get(pageKey));
					
				} else {
					// Value mismatch, print and stop the loop
					System.out.println("Value doesn't match!" + envMap.get(pageKey) + ", " + pageValue);
					test.log(LogStatus.FAIL, "Attribute : " + pageKey + " has not matched with expected value : "+ envMap.get(pageKey));
					everythingMatches = false;
				}
			} else {
				// Key not present in Unix. Stop loop.
				System.out.println("Page key not present in environment" + pageKey);
				test.log(LogStatus.FAIL, "Attribute : " + pageKey + " has no entry in the environment properties");
				everythingMatches = false;
			}
		}
		
	}catch (Exception e) {
		log.error("Error in getDataMap :" + e.getMessage());
		test.log(LogStatus.ERROR, e);
		test.log(LogStatus.FAIL, "Error in reading the data from GUI.");
		Assert.assertTrue(false, e.getMessage());
	}
		return everythingMatches;
	}

	/*F*****************************************************************************************************
	 *
	 * FUNCTION: getMappedValue
	 *
	 * PURPOSE : To get the Mapped Value from mapper file. 
	 * 
	 */
	private String getMappedValue(String pageKey, Map<String, String> envVarMapper) {

		if(envVarMapper.containsKey(pageKey)) {
			return envVarMapper.get(pageKey);
		} else {
			return pageKey;
		}
	}
	
	/*F*****************************************************************************************************
	 *
	 * FUNCTION: navigateToIntegrationInterfaceDetails
	 *
	 * PURPOSE : To navigate to Integration Interface Page for each Integration.
	 * 
	 */
	public void navigateToIntegrationInterfaceDetails(String location)
	{
	
		try {
			log.debug("Entering navigateToIntegrationInterfaceDetails : " + location );
			wait(1);
			navigate(Utility.getValueFromPropertyFile("documents_url"));
			wait(1);
			test.log(LogStatus.INFO, "Navigation to Documents Path: " + test.addScreenCapture(addScreenshot()));
			click(INTEGRATION_INTERFACE);
			wait(1);
			test.log(LogStatus.INFO, "Navigation to Integration Interface Path: " + test.addScreenCapture(addScreenshot()));
			
			switch (location) {
					case "Alcatel":
										click(ALCATEL_INTERFACE);
										break;
					case "IPBB":
										click(CISCO_INTERFACE);
										break;
					case "CMTS":
										click(CMTS_INTERFACE);
										break;
										
					case "CMTS-OnDemand":
										click(CMTS_INTERFACE);
										break;
					case "Spectrum":
										
										click(SPECTRUM_INTERFACE);
										break;
										
					case "ETX":
										click(ETX_INTERFACE);
										break;
										
					case "FDB":
										click(FDB_INTERFACE);
										break;
										
					case "CIN":
										click(CIN_INTERFACE);
										break;
										
					case "CIN-OnDemand":
										click(CIN_INTERFACE);
										break;
										
					case "IPV4":
										click(FDB_INTERFACE);
										break;
					default:
										log.debug("Given Location not found on Page");
					}
			wait(1);
			click(PARAMETERS_TAB);
			wait(1);
			test.log(LogStatus.INFO, "Integration Interface Detailed View : " + test.addScreenCapture(addScreenshot()));
			log.debug("Leaving getIntegrationInterfaceDetails : " + location);
		} catch (Exception e) {
			log.error("Error in navigateToIntegrationInterfaceDetails :" + location + " " + e.getMessage());
			test.log(LogStatus.ERROR, e);
			test.log(LogStatus.FAIL, "Error in navigation of Integration Interface Details: " + test.addScreenCapture(addScreenshot()));
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	/*F*****************************************************************************************************
	 *
	 * FUNCTION: deletePreviousScreenShot
	 *
	 * PURPOSE : To delete Previous Screenshots which are present in Screenshot folder.
	 * 
	 */
	
	public void deletePreviousScreenShot(String previousPngFile) {
		log.debug("Entering deletePreviousScreenShot with previousPngFile name : " + previousPngFile);
		File file = new File(Constants.SCREENSHOT_PATH + previousPngFile + ".png"); 
        if(file.delete()) 
        { 
            System.out.println("File deleted successfully"); 
            log.debug("File : " + previousPngFile + ".png deleted successfully.");
        } 
        else
        { 
            System.out.println("Failed to delete the file"); 
            log.debug("Failed to delete File : " + previousPngFile + ".png");
        } 
        log.debug("Leaving deletePreviousScreenShot");
	}
	
	/*F*****************************************************************************************************
	 *
	 * FUNCTION: getScreenShotIntegInterface
	 *
	 * PURPOSE : To get screenshot of the Integration Interface Parameters to verify the parameters are same.
	 * 
	 */
	public List<BufferedImage> getScreenShotIntegInterface(String pngName) throws Exception {
		log.debug("Entering getScreenShotIntegInterface ");
		List<BufferedImage> imageList = new ArrayList<BufferedImage>();
		wait(1);
		BufferedImage expectedImage, extraImage = null ;
		File image1, image2 = null;
		//boolean status = false;
		try { 
			if ( pngName.contains("IPBB")) {
				Shutterbug.shootElement(driver, getWebElement(TABLE_ELEMENT)).withName(pngName).save(Constants.SCREENSHOT_PATH);
				image1 = new File(Constants.SCREENSHOT_PATH + pngName + ".png");
				if ( pngName.contains("IPBB_Pre")) {
					Shutterbug.shootElement(driver, getWebElement(TABLE_ELEMENT_IPBB)).withName("IPBB_PreFTPConnectivityDetails").save(Constants.SCREENSHOT_PATH);
					image2 = new File(Constants.SCREENSHOT_PATH + "IPBB_PreFTPConnectivityDetails" + ".png");
				}
				else {
					Shutterbug.shootElement(driver, getWebElement(TABLE_ELEMENT_IPBB)).withName("IPBB_PostFTPConnectivityDetails").save(Constants.SCREENSHOT_PATH);
					image2 = new File(Constants.SCREENSHOT_PATH + "IPBB_PostFTPConnectivityDetails" + ".png");
				}
				expectedImage = ImageIO.read(image1);
				extraImage = ImageIO.read(image2);
				imageList.add(expectedImage);
				imageList.add(extraImage);
			}
			else {
				Shutterbug.shootElement(driver, getWebElement(TABLE_ELEMENT)).withName(pngName).save(Constants.SCREENSHOT_PATH);
				image1 = new File(Constants.SCREENSHOT_PATH + pngName + ".png");
				expectedImage = ImageIO.read(image1);
				imageList.add(expectedImage);
			}
		}
		catch (Exception e) {
			log.error("Error in getScreenShotIntegInterface:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.ERROR, e);
			Assert.assertTrue(false, e.getMessage());
			}
			
		log.debug("Leaving getScreenShotIntegInterface ");
		return imageList;
	}
	
	/*F*****************************************************************************************************
	 *
	 * FUNCTION: compareImage
	 *
	 * PURPOSE : To compare the Images captured as screenshots of Integration Interface details.
	 * 
	 */
	public boolean compareImage(BufferedImage expectedImage, BufferedImage actualImage)
	{
		log.debug("Entering compareImage");
		boolean status = false;
		try { 
		if (expectedImage.getWidth() == actualImage.getWidth() && expectedImage.getHeight() == actualImage.getHeight()) {
	        for (int x = 0; x < expectedImage.getWidth(); x++) {
	            for (int y = 0; y < expectedImage.getHeight(); y++) {
	                if (expectedImage.getRGB(x, y) != actualImage.getRGB(x, y)) {
	                	System.out.println("Both Images are Not Equal : ");
	                	status =  false;
	                	test.log(LogStatus.FAIL, "Integration Interface details do not match, Please see the difference between images in screenshot folder : " + Constants.SCREENSHOT_PATH);
	                	Assert.assertTrue(false, "Integration Interface details do not match");
	                	return status;
	                }
	            }
	        }
	    } else {
	    	System.out.println("Both Image are Not Equal : ");
	       status =  false;
	       test.log(LogStatus.FAIL, "Integration Interface details do not match, Please see the difference between images in screenshot folder : " + Constants.SCREENSHOT_PATH);
       	   Assert.assertTrue(false, "Integration Interface details do not match");
       	   return status;
	    }
		status =  true;
		test.log(LogStatus.PASS, "Integration Interface details Matched After Fresh Installation.");
		Assert.assertTrue(true, "Integration Interface details Matched After Fresh Installation.");
		System.out.println("Both Image are Equal : " );
		return status;
		
		} 
		catch (Exception e) {
			log.error("Error in compareImage:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.ERROR, e);
			Assert.assertTrue(false, e.getMessage());
			}
		log.debug("Leaving compareImage ");
		return status;
	}
	
	/*F*****************************************************************************************************
	 *
	 * FUNCTION: validateAllPageResult
	 *
	 * PURPOSE : To validate whether all the Pages together passed or failed the test.
	 * 
	 */
	public void validateAllPageResult(boolean everythingMatches) {
		if ( everythingMatches == false ) {
			test.log(LogStatus.FAIL, "Attributes in Env and GUI have not matched. Please check Failures in each ChildTest's.");
			Assert.assertTrue(false,  "Attributes in Env and GUI have not matched. Please check Failures in each ChildTest's.");
		}
		else {
			test.log(LogStatus.PASS, "All Attributes in Env and GUI have been Matched.");
			Assert.assertTrue(true,  "All Attributes in Env and GUI have been Matched.");
		}
		
	}
	
	
}