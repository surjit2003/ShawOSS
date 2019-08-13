package com.netcracker.shaw.phase2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.netcracker.shaw.at_shaw_sd.jdbc.JDBCValidator;
import com.netcracker.shaw.at_shaw_sd.pageobject.LandingPage;
import com.netcracker.shaw.at_shaw_sd.pageobject.RIFlowPage;
import com.netcracker.shaw.at_shaw_sd.pageobject.DnRFlowPage;
import com.netcracker.shaw.at_shaw_sd.pageobject.PostInstallationPage;
import com.netcracker.shaw.at_shaw_sd.util.Constants;
import com.netcracker.shaw.at_shaw_sd.util.Utility;
import com.netcracker.shaw.setup.SeleniumTestUp;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Decoupling_DnR_Requirement extends SeleniumTestUp{
	
	LandingPage landing=null;
	DnRFlowPage dnrFlow =null;
	RIFlowPage riFlow =null;
	PostInstallationPage piFlow = null;
	JDBCValidator jdbc=null;
	boolean everythingMatches = true;
	Logger log=Logger.getLogger(Decoupling_DnR_Requirement.class);
	
	/* OSSNETCRACKER-199
	 * Test Scenario : To validate whether parameters in env properties are getting updated after build installation ( FDB )
	 */
	
	@DataProvider
	public Object[][] getData307(){
		return Utility.getData(xls,"TC307");
	}
	
	@Test (dataProvider="getData307", priority = 307)
	@SuppressWarnings(value={ "rawtypes" })
	public void PostInstall_toValidate_EnvProp_FDB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering PostInstall_toValidate_EnvProp_FDB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to the Putty Server and get the environment.prop file data");
			test.appendChild(childTest);
			piFlow=createChildTest(childTest,extent,piFlow);
			Map<String, String>  envMap = piFlow.getEnvPropFileData();
			Map<String, String>  envVarMapper = piFlow.getMapperData();
		
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to appropriate GUI path to get the Properties data");
			test.appendChild(childTest2);
			piFlow=createChildTest(childTest2,extent,piFlow);
			Map<String, String> integInterfaceMap = piFlow.getGUIPropData("FDB-Interface", "Interface");
			
			ExtentTest childTest3=extent.startTest("Validate the Integration Interface Details");
			test.appendChild(childTest3);
			piFlow=createChildTest(childTest3,extent,piFlow);
			everythingMatches &= piFlow.compareEnvWithPage(envMap, envVarMapper, integInterfaceMap);
			piFlow.validateAllPageResult(everythingMatches);
			
			log.debug("Leaving PostInstall_toValidate_EnvProp_FDB");
			
			}catch(Exception e)
			{
				log.error("Error in PostInstall_toValidate_EnvProp_FDB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* OSSNETCRACKER-199
	 * Test Scenario : To validate whether parameters in env properties are getting updated after build installation ( CIN )
	 */
	
	@DataProvider
	public Object[][] getData308(){
		return Utility.getData(xls,"TC308");
	}
	
	@Test (dataProvider="getData308", priority = 308)
	@SuppressWarnings(value={ "rawtypes" })
	public void PostInstall_toValidate_EnvProp_CIN(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering PostInstall_toValidate_EnvProp_CIN");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			System.out.println("everythingMatches CIN = " + everythingMatches);
			ExtentTest childTest=extent.startTest("Login to the Putty Server and get the environment.prop file data");
			test.appendChild(childTest);
			piFlow=createChildTest(childTest,extent,piFlow);
			Map<String, String>  envMap = piFlow.getEnvPropFileData();
			Map<String, String>  envVarMapper = piFlow.getMapperData();
		
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to appropriate GUI path to get the Properties data");
			test.appendChild(childTest2);
			piFlow=createChildTest(childTest2,extent,piFlow);
			Map<String, String> integInterfaceMap = piFlow.getGUIPropData("CIN-Interface", "Interface");
			
			ExtentTest childTest3=extent.startTest("Validate the Integration Interface Details");
			test.appendChild(childTest3);
			piFlow=createChildTest(childTest3,extent,piFlow);
			everythingMatches &= piFlow.compareEnvWithPage(envMap, envVarMapper, integInterfaceMap);
			System.out.println("everythingMatches CIN = " + everythingMatches);
			piFlow.validateAllPageResult(everythingMatches);
			
			log.debug("Leaving PostInstall_toValidate_EnvProp_CIN");
			
			}catch(Exception e)
			{
				log.error("Error in PostInstall_toValidate_EnvProp_CIN:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* OSSNETCRACKER-199
	 * Test Scenario : To validate whether parameters in env properties are getting updated after build installation ( ETX )
	 */
	
	@DataProvider
	public Object[][] getData309(){
		return Utility.getData(xls,"TC309");
	}
	
	@Test (dataProvider="getData309", priority = 309)
	@SuppressWarnings(value={ "rawtypes" })
	public void PostInstall_toValidate_EnvProp_ETX(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering PostInstall_toValidate_EnvProp_ETX");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			System.out.println("everythingMatches ETX = " + everythingMatches);
			ExtentTest childTest=extent.startTest("Login to the Putty Server and get the environment.prop file data");
			test.appendChild(childTest);
			piFlow=createChildTest(childTest,extent,piFlow);
			Map<String, String>  envMap = piFlow.getEnvPropFileData();
			Map<String, String>  envVarMapper = piFlow.getMapperData();
		
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to appropriate GUI path to get the Properties data");
			test.appendChild(childTest2);
			piFlow=createChildTest(childTest2,extent,piFlow);
			Map<String, String> integInterfaceMap = piFlow.getGUIPropData("ETX-Interface", "Interface");
			Map<String, String> dataFlowMap = piFlow.getGUIPropData("ETX-DataFlow", "DataFlow");
			
			ExtentTest childTest3=extent.startTest("Validate the Integration Interface Details");
			test.appendChild(childTest3);
			piFlow=createChildTest(childTest3,extent,piFlow);
			everythingMatches &= piFlow.compareEnvWithPage(envMap, envVarMapper, integInterfaceMap);
			
			ExtentTest childTest4=extent.startTest("Validate the DataFlow Details");
			test.appendChild(childTest4);
			piFlow=createChildTest(childTest4,extent,piFlow);
			everythingMatches &= piFlow.compareEnvWithPage(envMap, envVarMapper, dataFlowMap);
			System.out.println("everythingMatches ETX = " + everythingMatches);
			piFlow.validateAllPageResult(everythingMatches);
			
			log.debug("Leaving PostInstall_toValidate_EnvProp_ETX");
			
			}catch(Exception e)
			{
				log.error("Error in PostInstall_toValidate_EnvProp_ETX:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* OSSNETCRACKER-199
	 * Test Scenario : To validate whether parameters in env properties are getting updated after build installation ( Spectrum )
	 */
	
	@DataProvider
	public Object[][] getData310(){
		return Utility.getData(xls,"TC310");
	}
	
	@Test (dataProvider="getData310", priority = 310)
	@SuppressWarnings(value={ "rawtypes" })
	public void PostInstall_toValidate_EnvProp_Spectrum(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering PostInstall_toValidate_EnvProp_Spectrum");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			
			ExtentTest childTest=extent.startTest("Login to the Putty Server and get the environment.prop file data");
			test.appendChild(childTest);
			piFlow=createChildTest(childTest,extent,piFlow);
			Map<String, String>  envMap = piFlow.getEnvPropFileData();
			Map<String, String>  envVarMapper = piFlow.getMapperData();
		
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to appropriate GUI path to get the Properties data");
			test.appendChild(childTest2);
			piFlow=createChildTest(childTest2,extent,piFlow);
			Map<String, String> integInterfaceMap = piFlow.getGUIPropData("Spectrum-Interface", "Interface");
			Map<String, String> dataFlowMap = piFlow.getGUIPropData("Spectrum-DataFlow", "DataFlow");
			Map<String, String> snmpFlowMap = piFlow.getGUIPropData("Spectrum-SnmpFlow", "SnmpFlow");
			
			ExtentTest childTest3=extent.startTest("Validate the Integration Interface Details");
			test.appendChild(childTest3);
			piFlow=createChildTest(childTest3,extent,piFlow);
			everythingMatches &= piFlow.compareEnvWithPage(envMap, envVarMapper, integInterfaceMap);
			
			ExtentTest childTest4=extent.startTest("Validate the DataFlow Details");
			test.appendChild(childTest4);
			piFlow=createChildTest(childTest4,extent,piFlow);
			everythingMatches &= piFlow.compareEnvWithPage(envMap, envVarMapper, dataFlowMap);
			
			ExtentTest childTest5=extent.startTest("Validate the Snmp Details");
			test.appendChild(childTest5);
			piFlow=createChildTest(childTest5,extent,piFlow);
			everythingMatches &= piFlow.compareEnvWithPage(envMap, envVarMapper, snmpFlowMap);
			piFlow.validateAllPageResult(everythingMatches);
			
			log.debug("Leaving PostInstall_toValidate_EnvProp_Spectrum");
			
			}catch(Exception e)
			{
				log.error("Error in PostInstall_toValidate_EnvProp_Spectrum:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* OSSNETCRACKER-199
	 * Test Scenario : To validate whether parameters in env properties are getting updated after build installation ( CMTS )
	 */
	
	@DataProvider
	public Object[][] getData311(){
		return Utility.getData(xls,"TC311");
	}
	
	@Test (dataProvider="getData311", priority = 311)
	@SuppressWarnings(value={ "rawtypes" })
	public void PostInstall_toValidate_EnvProp_CMTS(Hashtable<String,String> data) throws Exception{
		try
		{
			
			log.debug("Entering PostInstall_toValidate_EnvProp_CMTS");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to the Putty Server and get the environment.prop file data");
			test.appendChild(childTest);
			piFlow=createChildTest(childTest,extent,piFlow);
			Map<String, String>  envMap = piFlow.getEnvPropFileData();
			Map<String, String>  envVarMapper = piFlow.getMapperData();
		
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to appropriate GUI path to get the Properties data");
			test.appendChild(childTest2);
			piFlow=createChildTest(childTest2,extent,piFlow);
			Map<String, String> integInterfaceMap = piFlow.getGUIPropData("CMTS-Interface", "Interface");
			Map<String, String> dataFlowMap = piFlow.getGUIPropData("CMTS-DataFlow", "DataFlow");
			Map<String, String> snmpFlowMap = piFlow.getGUIPropData("CMTS-SnmpFlow", "SnmpFlow");
			
			ExtentTest childTest3=extent.startTest("Validate the Integration Interface Details");
			test.appendChild(childTest3);
			piFlow=createChildTest(childTest3,extent,piFlow);
			everythingMatches &= piFlow.compareEnvWithPage(envMap, envVarMapper, integInterfaceMap);
			
			ExtentTest childTest4=extent.startTest("Validate the DataFlow Details");
			test.appendChild(childTest4);
			piFlow=createChildTest(childTest4,extent,piFlow);
			everythingMatches &= piFlow.compareEnvWithPage(envMap, envVarMapper, dataFlowMap);
			
			ExtentTest childTest5=extent.startTest("Validate the Snmp Details");
			test.appendChild(childTest5);
			piFlow=createChildTest(childTest5,extent,piFlow);
			everythingMatches &= piFlow.compareEnvWithPage(envMap, envVarMapper, snmpFlowMap);
			piFlow.validateAllPageResult(everythingMatches);
			
			log.debug("Leaving PostInstall_toValidate_EnvProp_CMTS");
			
			}catch(Exception e)
			{
				log.error("Error in PostInstall_toValidate_EnvProp_CMTS" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* OSSNETCRACKER-283
	 * Test Scenario : To validate whether integration interface credentials getting restored after build installation ( Alcatel )
	 */
	
	@DataProvider
	public Object[][] getData312(){
		return Utility.getData(xls,"TC312");
	}
	
	@Test (dataProvider="getData312", priority = 312)
	@SuppressWarnings(value={ "rawtypes" })
	public void PreInstall_toValidate_FTPCredentials_Alcatel(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering PreInstall_toValidate_FTPCredentials_Alcatel");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to Documents Integration Interface and get the Intermediate FTP Server Screen");
			test.appendChild(childTest2);
			piFlow=createChildTest(childTest2,extent,piFlow);
			piFlow.deletePreviousScreenShot("Alcatel_PreIntegDetails");
			piFlow.navigateToIntegrationInterfaceDetails("Alcatel");
			piFlow.getScreenShotIntegInterface("Alcatel_PreIntegDetails");
			
			log.debug("Leaving PreInstall_toValidate_FTPCredentials_Alcatel");
			
			}catch(Exception e)
			{
				log.error("Error in PreInstall_toValidate_FTPCredentials_Alcatel:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* OSSNETCRACKER-283
	 * Test Scenario : To validate whether integration interface credentials getting restored after build installation ( Alcatel )
	 */
	
	@DataProvider
	public Object[][] getData313(){
		return Utility.getData(xls,"TC313");
	}
	
	@Test (dataProvider="getData313", priority = 313)
	@SuppressWarnings(value={ "rawtypes" })
	public void PostInstall_toValidate_FTPCredentials_Alcatel(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering PostInstall_toValidate_FTPCredentials_Alcatel");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			File imgPre = new File(Constants.SCREENSHOT_PATH + "Alcatel_PreIntegDetails.png");
			BufferedImage imagePre = ImageIO.read(imgPre);
					
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to Documents Integration Interface and get the Intermediate FTP Server Screen");
			test.appendChild(childTest2);
			piFlow=createChildTest(childTest2,extent,piFlow);
			piFlow.navigateToIntegrationInterfaceDetails("Alcatel");
			List<BufferedImage> imagePostList = piFlow.getScreenShotIntegInterface("Alcatel_PostIntegDetails");
			piFlow.compareImage(imagePre, imagePostList.get(0));
			
			log.debug("Leaving PostInstall_toValidate_FTPCredentials_Alcatel");
			
			}catch(Exception e)
			{
				log.error("Error in PostInstall_toValidate_FTPCredentials_Alcatel:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* OSSNETCRACKER-283
	 * Test Scenario : To validate whether integration interface credentials getting restored after build installation ( CIN )
	 */
	
	@DataProvider
	public Object[][] getData314(){
		return Utility.getData(xls,"TC314");
	}
	
	@Test (dataProvider="getData314", priority = 314)
	@SuppressWarnings(value={ "rawtypes" })
	public void PreInstall_toValidate_FTPCredentials_CIN(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering PreInstall_toValidate_FTPCredentials_CIN");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to Documents Integration Interface and get the Intermediate FTP Server Screen");
			test.appendChild(childTest2);
			piFlow=createChildTest(childTest2,extent,piFlow);
			piFlow.deletePreviousScreenShot("CIN_PreIntegDetails");
			piFlow.navigateToIntegrationInterfaceDetails("CIN");
			piFlow.getScreenShotIntegInterface("CIN_PreIntegDetails");
			
			log.debug("Leaving PreInstall_toValidate_FTPCredentials_CIN");
			
			}catch(Exception e)
			{
				log.error("Error in PreInstall_toValidate_FTPCredentials_CIN:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* OSSNETCRACKER-283
	 * Test Scenario : To validate whether integration interface credentials getting restored after build installation ( CIN )
	 */
	
	@DataProvider
	public Object[][] getData315(){
		return Utility.getData(xls,"TC315");
	}
	
	@Test (dataProvider="getData315", priority = 315)
	@SuppressWarnings(value={ "rawtypes" })
	public void PostInstall_toValidate_FTPCredentials_CIN(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering PostInstall_toValidate_FTPCredentials_CIN");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			File imgPre = new File(Constants.SCREENSHOT_PATH + "CIN_PreIntegDetails.png");
			BufferedImage imagePre = ImageIO.read(imgPre);
					
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to Documents Integration Interface and get the Intermediate FTP Server Screen");
			test.appendChild(childTest2);
			piFlow=createChildTest(childTest2,extent,piFlow);
			piFlow.navigateToIntegrationInterfaceDetails("CIN");
			List<BufferedImage> imagePostList = piFlow.getScreenShotIntegInterface("CIN_PostIntegDetails");
			piFlow.compareImage(imagePre, imagePostList.get(0));
					
			log.debug("Leaving PostInstall_toValidate_FTPCredentials_CIN");
			
			}catch(Exception e)
			{
				log.error("Error in PostInstall_toValidate_FTPCredentials_CIN:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* OSSNETCRACKER-283
	 * Test Scenario : To validate whether integration interface credentials getting restored after build installation ( IPBB )
	 */
	
	@DataProvider
	public Object[][] getData316(){
		return Utility.getData(xls,"TC316");
	}
	
	@Test (dataProvider="getData316", priority = 316)
	@SuppressWarnings(value={ "rawtypes" })
	public void PreInstall_toValidate_FTPCredentials_IPBB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering PreInstall_toValidate_FTPCredentials_IPBB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to Documents Integration Interface and get the Intermediate FTP Server Screen");
			test.appendChild(childTest2);
			piFlow=createChildTest(childTest2,extent,piFlow);
			piFlow.deletePreviousScreenShot("IPBB_PreIntegDetails");
			piFlow.navigateToIntegrationInterfaceDetails("IPBB");
			piFlow.getScreenShotIntegInterface("IPBB_PreIntegDetails");
			
			log.debug("Leaving PreInstall_toValidate_FTPCredentials_IPBB");
			
			}catch(Exception e)
			{
				log.error("Error in PreInstall_toValidate_FTPCredentials_IPBB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* OSSNETCRACKER-283
	 * Test Scenario : To validate whether integration interface credentials getting restored after build installation ( IPBB )
	 */
	
	@DataProvider
	public Object[][] getData317(){
		return Utility.getData(xls,"TC317");
	}
	
	@Test (dataProvider="getData317", priority = 317)
	@SuppressWarnings(value={ "rawtypes" })
	public void PostInstall_toValidate_FTPCredentials_IPBB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering PostInstall_toValidate_FTPCredentials_IPBB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			File imgPre1 = new File(Constants.SCREENSHOT_PATH + "IPBB_PreIntegDetails.png");
			BufferedImage imagePre1 = ImageIO.read(imgPre1);
			File imgPre2 = new File(Constants.SCREENSHOT_PATH + "IPBB_PreFTPConnectivityDetails.png");
			BufferedImage imagePre2 = ImageIO.read(imgPre2);
					
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to Documents Integration Interface and get the Intermediate FTP Server Screen");
			test.appendChild(childTest2);
			piFlow=createChildTest(childTest2,extent,piFlow);
			piFlow.navigateToIntegrationInterfaceDetails("IPBB");
			List<BufferedImage> imagePostList = piFlow.getScreenShotIntegInterface("IPBB_PostIntegDetails");
			piFlow.compareImage(imagePre1, imagePostList.get(0));
			piFlow.compareImage(imagePre2, imagePostList.get(1));
			
			log.debug("Leaving PostInstall_toValidate_FTPCredentials_IPBB");
			
			}catch(Exception e)
			{
				log.error("Error in PostInstall_toValidate_FTPCredentials_IPBB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* OSSNETCRACKER-283
	 * Test Scenario : To validate whether integration interface credentials getting restored after build installation ( CMTS )
	 */
	
	@DataProvider
	public Object[][] getData318(){
		return Utility.getData(xls,"TC318");
	}
	
	@Test (dataProvider="getData318", priority = 318)
	@SuppressWarnings(value={ "rawtypes" })
	public void PreInstall_toValidate_FTPCredentials_CMTS(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering PreInstall_toValidate_FTPCredentials_CMTS");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to Documents Integration Interface and get the Intermediate FTP Server Screen");
			test.appendChild(childTest2);
			piFlow=createChildTest(childTest2,extent,piFlow);
			piFlow.deletePreviousScreenShot("CMTS_PreIntegDetails");
			piFlow.navigateToIntegrationInterfaceDetails("CMTS");
			piFlow.getScreenShotIntegInterface("CMTS_PreIntegDetails");
			
			log.debug("Leaving PreInstall_toValidate_FTPCredentials_CMTS");
			
			}catch(Exception e)
			{
				log.error("Error in PreInstall_toValidate_FTPCredentials_CMTS:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* OSSNETCRACKER-283
	 * Test Scenario : To validate whether integration interface credentials getting restored after build installation ( CMTS )
	 */
	
	@DataProvider
	public Object[][] getData319(){
		return Utility.getData(xls,"TC319");
	}
	
	@Test (dataProvider="getData319", priority = 319)
	@SuppressWarnings(value={ "rawtypes" })
	public void PostInstall_toValidate_FTPCredentials_CMTS(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering PostInstall_toValidate_FTPCredentials_CMTS");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			File imgPre = new File(Constants.SCREENSHOT_PATH + "CMTS_PreIntegDetails.png");
			BufferedImage imagePre = ImageIO.read(imgPre);
					
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to Documents Integration Interface and get the Intermediate FTP Server Screen");
			test.appendChild(childTest2);
			piFlow=createChildTest(childTest2,extent,piFlow);
			piFlow.navigateToIntegrationInterfaceDetails("CMTS");
			List<BufferedImage> imagePostList = piFlow.getScreenShotIntegInterface("CMTS_PostIntegDetails");
			piFlow.compareImage(imagePre, imagePostList.get(0));
			
			log.debug("Leaving PostInstall_toValidate_FTPCredentials_CMTS");
			
			}catch(Exception e)
			{
				log.error("Error in PostInstall_toValidate_FTPCredentials_CMTS:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* OSSNETCRACKER-283
	 * Test Scenario : To validate whether integration interface credentials getting restored after build installation ( ETX )
	 */
	
	@DataProvider
	public Object[][] getData320(){
		return Utility.getData(xls,"TC320");
	}
	
	@Test (dataProvider="getData320", priority = 320)
	@SuppressWarnings(value={ "rawtypes" })
	public void PreInstall_toValidate_FTPCredentials_ETX(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering PreInstall_toValidate_FTPCredentials_ETX");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to Documents Integration Interface and get the Intermediate FTP Server Screen");
			test.appendChild(childTest2);
			piFlow=createChildTest(childTest2,extent,piFlow);
			piFlow.deletePreviousScreenShot("ETX_PreIntegDetails");
			piFlow.navigateToIntegrationInterfaceDetails("ETX");
			piFlow.getScreenShotIntegInterface("ETX_PreIntegDetails");
			
			log.debug("Leaving PreInstall_toValidate_FTPCredentials_ETX");
			
			}catch(Exception e)
			{
				log.error("Error in PreInstall_toValidate_FTPCredentials_ETX:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* OSSNETCRACKER-283
	 * Test Scenario : To validate whether integration interface credentials getting restored after build installation ( ETX )
	 */
	
	@DataProvider
	public Object[][] getData321(){
		return Utility.getData(xls,"TC321");
	}
	
	@Test (dataProvider="getData321", priority = 321)
	@SuppressWarnings(value={ "rawtypes" })
	public void PostInstall_toValidate_FTPCredentials_ETX(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering PostInstall_toValidate_FTPCredentials_ETX");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			File imgPre = new File(Constants.SCREENSHOT_PATH + "ETX_PreIntegDetails.png");
			BufferedImage imagePre = ImageIO.read(imgPre);
					
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to Documents Integration Interface and get the Intermediate FTP Server Screen");
			test.appendChild(childTest2);
			piFlow=createChildTest(childTest2,extent,piFlow);
			piFlow.navigateToIntegrationInterfaceDetails("ETX");
			List<BufferedImage> imagePostList = piFlow.getScreenShotIntegInterface("ETX_PostIntegDetails");
			piFlow.compareImage(imagePre, imagePostList.get(0));
			
			log.debug("Leaving PostInstall_toValidate_FTPCredentials_ETX");
			
			}catch(Exception e)
			{
				log.error("Error in PostInstall_toValidate_FTPCredentials_ETX:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* OSSNETCRACKER-283
	 * Test Scenario : To validate whether integration interface credentials getting restored after build installation ( FDB )
	 */
	
	@DataProvider
	public Object[][] getData322(){
		return Utility.getData(xls,"TC322");
	}
	
	@Test (dataProvider="getData322", priority = 322)
	@SuppressWarnings(value={ "rawtypes" })
	public void PreInstall_toValidate_FTPCredentials_FDB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering PreInstall_toValidate_FTPCredentials_FDB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to Documents Integration Interface and get the Intermediate FTP Server Screen");
			test.appendChild(childTest2);
			piFlow=createChildTest(childTest2,extent,piFlow);
			piFlow.deletePreviousScreenShot("FDB_PreIntegDetails");
			piFlow.navigateToIntegrationInterfaceDetails("FDB");
			piFlow.getScreenShotIntegInterface("FDB_PreIntegDetails");
			
			log.debug("Leaving PreInstall_toValidate_FTPCredentials_FDB");
			
			}catch(Exception e)
			{
				log.error("Error in PreInstall_toValidate_FTPCredentials_FDB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* OSSNETCRACKER-283
	 * Test Scenario : To validate whether integration interface credentials getting restored after build installation ( FDB )
	 */
	
	@DataProvider
	public Object[][] getData323(){
		return Utility.getData(xls,"TC323");
	}
	
	@Test (dataProvider="getData323", priority = 323)
	@SuppressWarnings(value={ "rawtypes" })
	public void PostInstall_toValidate_FTPCredentials_FDB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering PostInstall_toValidate_FTPCredentials_FDB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			File imgPre = new File(Constants.SCREENSHOT_PATH + "FDB_PreIntegDetails.png");
			BufferedImage imagePre = ImageIO.read(imgPre);
					
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to Documents Integration Interface and get the Intermediate FTP Server Screen");
			test.appendChild(childTest2);
			piFlow=createChildTest(childTest2,extent,piFlow);
			piFlow.navigateToIntegrationInterfaceDetails("FDB");
			List<BufferedImage> imagePostList = piFlow.getScreenShotIntegInterface("FDB_PostIntegDetails");
			piFlow.compareImage(imagePre, imagePostList.get(0));
			
			log.debug("Leaving PostInstall_toValidate_FTPCredentials_FDB");
			
			}catch(Exception e)
			{
				log.error("Error in PostInstall_toValidate_FTPCredentials_FDB:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* OSSNETCRACKER-283
	 * Test Scenario : To validate whether integration interface credentials getting restored after build installation ( IPV4 )
	 */
	
	@DataProvider
	public Object[][] getData324(){
		return Utility.getData(xls,"TC324");
	}
	
	@Test (dataProvider="getData324", priority = 324)
	@SuppressWarnings(value={ "rawtypes" })
	public void PreInstall_toValidate_FTPCredentials_IPV4(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering PreInstall_toValidate_FTPCredentials_IPV4");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to Documents Integration Interface and get the Intermediate FTP Server Screen");
			test.appendChild(childTest2);
			piFlow=createChildTest(childTest2,extent,piFlow);
			piFlow.deletePreviousScreenShot("IPV4_PreIntegDetails");
			piFlow.navigateToIntegrationInterfaceDetails("IPV4");
			piFlow.getScreenShotIntegInterface("IPV4_PreIntegDetails");
			
			log.debug("Leaving PreInstall_toValidate_FTPCredentials_IPV4");
			
			}catch(Exception e)
			{
				log.error("Error in PreInstall_toValidate_FTPCredentials_IPV4:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* OSSNETCRACKER-283
	 * Test Scenario : To validate whether integration interface credentials getting restored after build installation ( IPV4 )
	 */
	
	@DataProvider
	public Object[][] getData325(){
		return Utility.getData(xls,"TC325");
	}
	
	@Test (dataProvider="getData325", priority = 325)
	@SuppressWarnings(value={ "rawtypes" })
	public void PostInstall_toValidate_FTPCredentials_IPV4(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering PostInstall_toValidate_FTPCredentials_IPV4");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			File imgPre = new File(Constants.SCREENSHOT_PATH + "IPV4_PreIntegDetails.png");
			BufferedImage imagePre = ImageIO.read(imgPre);
					
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to Documents Integration Interface and get the Intermediate FTP Server Screen");
			test.appendChild(childTest2);
			piFlow=createChildTest(childTest2,extent,piFlow);
			piFlow.navigateToIntegrationInterfaceDetails("IPV4");
			List<BufferedImage> imagePostList = piFlow.getScreenShotIntegInterface("IPV4_PostIntegDetails");
			piFlow.compareImage(imagePre, imagePostList.get(0));
			
			log.debug("Leaving PostInstall_toValidate_FTPCredentials_IPV4");
			
			}catch(Exception e)
			{
				log.error("Error in PostInstall_toValidate_FTPCredentials_IPV4:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* OSSNETCRACKER-283
	 * Test Scenario : To validate whether integration interface credentials getting restored after build installation ( Spectrum )
	 */
	
	@DataProvider
	public Object[][] getData326(){
		return Utility.getData(xls,"TC326");
	}
	
	@Test (dataProvider="getData326", priority = 326)
	@SuppressWarnings(value={ "rawtypes" })
	public void PreInstall_toValidate_FTPCredentials_Spectrum(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering PreInstall_toValidate_FTPCredentials_Spectrum");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to Documents Integration Interface and get the Intermediate FTP Server Screen");
			test.appendChild(childTest2);
			piFlow=createChildTest(childTest2,extent,piFlow);
			piFlow.deletePreviousScreenShot("Spectrum_PreIntegDetails");
			piFlow.navigateToIntegrationInterfaceDetails("Spectrum");
			piFlow.getScreenShotIntegInterface("Spectrum_PreIntegDetails");
			
			log.debug("Leaving PreInstall_toValidate_FTPCredentials_Spectrum");
			
			}catch(Exception e)
			{
				log.error("Error in PreInstall_toValidate_FTPCredentials_Spectrum:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* OSSNETCRACKER-283
	 * Test Scenario : To validate whether integration interface credentials getting restored after build installation ( Spectrum )
	 */
	
	@DataProvider
	public Object[][] getData327(){
		return Utility.getData(xls,"TC327");
	}
	
	@Test (dataProvider="getData327", priority = 327)
	@SuppressWarnings(value={ "rawtypes" })
	public void PostInstall_toValidate_FTPCredentials_Spectrum(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering PostInstall_toValidate_FTPCredentials_Spectrum");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			File imgPre = new File(Constants.SCREENSHOT_PATH + "Spectrum_PreIntegDetails.png");
			BufferedImage imagePre = ImageIO.read(imgPre);
					
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to Documents Integration Interface and get the Intermediate FTP Server Screen");
			test.appendChild(childTest2);
			piFlow=createChildTest(childTest2,extent,piFlow);
			piFlow.navigateToIntegrationInterfaceDetails("Spectrum");
			List<BufferedImage> imagePostList = piFlow.getScreenShotIntegInterface("Spectrum_PostIntegDetails");
			piFlow.compareImage(imagePre, imagePostList.get(0));
			
			log.debug("Leaving PostInstall_toValidate_FTPCredentials_Spectrum");
			
			}catch(Exception e)
			{
				log.error("Error in PostInstall_toValidate_FTPCredentials_Spectrum:" + e.getMessage());
				test.log(LogStatus.FAIL,"Test Failed");
				Assert.fail();
			}
		}
	
	/* OSSNETCRACKER-301
	 * Test Scenario : To verify user should not be able to run multiple DE's at the same time ( ALCATEL )
	 */
	
	@DataProvider
	public Object[][] getData400(){
		return Utility.getData(xls,"TC400");
	}


	@Test (dataProvider="getData400", priority = 400)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidate_Parallel_DataExport_Sessions_Alcatel(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidate_Parallel_DataExport_Sessions_Alcatel");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Parallel Sessions of Data Export and Check for Error");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("Alcatel", "DE", "1", "ParallelRun", "No");

			log.debug("Leaving DnR_toValidate_Parallel_DataExport_Sessions_Alcatel");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidate_Parallel_DataExport_Sessions_Alcatel:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-301
	 * Test Scenario : To verify user should not be able to run multiple DE's at the same time ( CIN )
	 */

	@DataProvider
	public Object[][] getData401(){
		return Utility.getData(xls,"TC401");
	}


	@Test (dataProvider="getData401", priority = 401)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidate_Parallel_DataExport_Sessions_CIN(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidate_Parallel_DataExport_Sessions_CIN");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Parallel Sessions of Data Export and Check for Error");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("CIN", "DE", "1", "ParallelRun", "No");

			log.debug("Leaving DnR_toValidate_Parallel_DataExport_Sessions_CIN");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidate_Parallel_DataExport_Sessions_CIN:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-301
	 * Test Scenario : To verify user should not be able to run multiple DE's at the same time ( IPBB )
	 */

	@DataProvider
	public Object[][] getData402(){
		return Utility.getData(xls,"TC402");
	}


	@Test (dataProvider="getData402", priority = 402)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidate_Parallel_DataExport_Sessions_IPBB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidate_Parallel_DataExport_Sessions_IPBB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Parallel Sessions of Data Export and Check for Error");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("IPBB", "DE", "1", "ParallelRun", "No");

			log.debug("Leaving DnR_toValidate_Parallel_DataExport_Sessions_IPBB");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidate_Parallel_DataExport_Sessions_IPBB:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}


	/* OSSNETCRACKER-301
	 * Test Scenario : To verify user should not be able to run multiple DE's at the same time ( CMTS )
	 */

	@DataProvider
	public Object[][] getData403(){
		return Utility.getData(xls,"TC403");
	}


	@Test (dataProvider="getData403", priority = 403)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidate_Parallel_DataExport_Sessions_CMTS(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidate_Parallel_DataExport_Sessions_CMTS");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Parallel Sessions of Data Export and Check for Error");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("CMTS", "DE", "1", "ParallelRun", "No");

			log.debug("Leaving DnR_toValidate_Parallel_DataExport_Sessions_CMTS");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidate_Parallel_DataExport_Sessions_CMTS:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-301
	 * Test Scenario : To verify user should not be able to run multiple DE's at the same time ( ETX )
	 */

	@DataProvider
	public Object[][] getData404(){
		return Utility.getData(xls,"TC404");
	}


	@Test (dataProvider="getData404", priority = 404)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidate_Parallel_DataExport_Sessions_ETX(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidate_Parallel_DataExport_Sessions_ETX");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Parallel Sessions of Data Export and Check for Error");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("ETX", "DE", "1", "ParallelRun", "No");

			log.debug("Leaving DnR_toValidate_Parallel_DataExport_Sessions_ETX");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidate_Parallel_DataExport_Sessions_ETX:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-301
	 * Test Scenario : To verify user should not be able to run multiple DE's at the same time ( FDB )
	 */

	@DataProvider
	public Object[][] getData405(){
		return Utility.getData(xls,"TC405");
	}


	@Test (dataProvider="getData405", priority = 405)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidate_Parallel_DataExport_Sessions_FDB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidate_Parallel_DataExport_Sessions_FDB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Parallel Sessions of Data Export and Check for Error");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("FDB", "DE", "1", "ParallelRun", "No");

			log.debug("Leaving DnR_toValidate_Parallel_DataExport_Sessions_FDB");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidate_Parallel_DataExport_Sessions_FDB:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-301
	 * Test Scenario : To verify user should not be able to run multiple DE's at the same time ( IPV4 )
	 */

	@DataProvider
	public Object[][] getData406(){
		return Utility.getData(xls,"TC406");
	}


	@Test (dataProvider="getData406", priority = 406)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidate_Parallel_DataExport_Sessions_IPV4(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidate_Parallel_DataExport_Sessions_IPV4");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Parallel Sessions of Data Export and Check for Error");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("IPV4", "DE", "1", "ParallelRun", "No");

			log.debug("Leaving DnR_toValidate_Parallel_DataExport_Sessions_IPV4");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidate_Parallel_DataExport_Sessions_IPV4:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-301
	 * Test Scenario : To verify user should not be able to run multiple DE's at the same time ( Spectrum )
	 */

	@DataProvider
	public Object[][] getData407(){
		return Utility.getData(xls,"TC407");
	}


	@Test (dataProvider="getData407", priority = 407)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidate_Parallel_DataExport_Sessions_Spectrum(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidate_Parallel_DataExport_Sessions_Spectrum");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Parallel Sessions of Data Export and Check for Error");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("Spectrum", "DE", "1", "ParallelRun", "No");

			log.debug("Leaving DnR_toValidate_Parallel_DataExport_Sessions_Spectrum");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidate_Parallel_DataExport_Sessions_Spectrum:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-301
	 * Test Scenario : To verify user should not be able to run multiple Recon's at the same time ( ALCATEL )
	 */

	@DataProvider
	public Object[][] getData408(){
		return Utility.getData(xls,"TC408");
	}

	@Test (dataProvider="getData408", priority = 408)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidate_Parallel_Recon_Sessions_Alcatel(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidate_Parallel_Recon_Sessions_Alcatel");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Parallel Sessions of Reconciliation and Check for Error");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("Alcatel", "Recon", "1", "ParallelRun", "No");

			log.debug("Leaving DnR_toValidate_Parallel_Recon_Sessions_Alcatel");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidate_Parallel_Recon_Sessions_Alcatel:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-301
	 * Test Scenario : To verify user should not be able to run multiple Recon's at the same time ( CIN )
	 */
	
	@DataProvider
	public Object[][] getData409(){
		return Utility.getData(xls,"TC409");
	}

	@Test (dataProvider="getData409", priority = 409)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidate_Parallel_Recon_Sessions_CIN(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidate_Parallel_Recon_Sessions_CIN");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Parallel Sessions of Reconciliation and Check for Error");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("CIN", "Recon", "1", "ParallelRun", "No");

			log.debug("Leaving DnR_toValidate_Parallel_Recon_Sessions_CIN");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidate_Parallel_Recon_Sessions_CIN:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-301
	 * Test Scenario : To verify user should not be able to run multiple Recon's at the same time ( IPBB )
	 */
	
	@DataProvider
	public Object[][] getData410(){
		return Utility.getData(xls,"TC410");
	}

	@Test (dataProvider="getData410", priority = 410)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidate_Parallel_Recon_Sessions_IPBB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidate_Parallel_Recon_Sessions_IPBB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Parallel Sessions of Reconciliation and Check for Error");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("IPBB", "Recon", "1", "ParallelRun", "No");

			log.debug("Leaving DnR_toValidate_Parallel_Recon_Sessions_IPBB");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidate_Parallel_Recon_Sessions_IPBB:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-301
	 * Test Scenario : To verify user should not be able to run multiple Recon's at the same time ( CMTS )
	 */
	
	@DataProvider
	public Object[][] getData411(){
		return Utility.getData(xls,"TC411");
	}

	@Test (dataProvider="getData411", priority = 411)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidate_Parallel_Recon_Sessions_CMTS(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidate_Parallel_Recon_Sessions_CMTS");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Parallel Sessions of Reconciliation and Check for Error");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("CMTS", "Recon", "1", "ParallelRun", "No");

			log.debug("Leaving DnR_toValidate_Parallel_Recon_Sessions_CMTS");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidate_Parallel_Recon_Sessions_CMTS:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* OSSNETCRACKER-301
	 * Test Scenario : To verify user should not be able to run multiple Recon's at the same time ( ETX )
	 */

	@DataProvider
	public Object[][] getData412(){
		return Utility.getData(xls,"TC412");
	}

	@Test (dataProvider="getData412", priority = 412)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidate_Parallel_Recon_Sessions_ETX(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidate_Parallel_Recon_Sessions_ETX");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Parallel Sessions of Reconciliation and Check for Error");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("ETX", "Recon", "1", "ParallelRun", "No");

			log.debug("Leaving DnR_toValidate_Parallel_Recon_Sessions_ETX");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidate_Parallel_Recon_Sessions_ETX:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* OSSNETCRACKER-301
	 * Test Scenario : To verify user should not be able to run multiple Recon's at the same time ( FDB )
	 */

	@DataProvider
	public Object[][] getData413(){
		return Utility.getData(xls,"TC413");
	}

	@Test (dataProvider="getData413", priority = 413)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidate_Parallel_Recon_Sessions_FDB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidate_Parallel_Recon_Sessions_FDB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Parallel Sessions of Reconciliation and Check for Error");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("FDB", "Recon", "1", "ParallelRun", "No");

			log.debug("Leaving DnR_toValidate_Parallel_Recon_Sessions_FDB");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidate_Parallel_Recon_Sessions_FDB:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* OSSNETCRACKER-301
	 * Test Scenario : To verify user should not be able to run multiple Recon's at the same time ( IPV4 )
	 */

	@DataProvider
	public Object[][] getData414(){
		return Utility.getData(xls,"TC414");
	}

	@Test (dataProvider="getData414", priority = 414)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidate_Parallel_Recon_Sessions_IPV4(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidate_Parallel_Recon_Sessions_IPV4");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Parallel Sessions of Reconciliation and Check for Error");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("IPV4", "Recon", "1", "ParallelRun", "No");

			log.debug("Leaving DnR_toValidate_Parallel_Recon_Sessions_IPV4");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidate_Parallel_Recon_Sessions_IPV4:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-301
	 * Test Scenario : To verify user should not be able to run multiple Recon's at the same time ( Spectrum )
	 */
	
	@DataProvider
	public Object[][] getData415(){
		return Utility.getData(xls,"TC415");
	}

	@Test (dataProvider="getData415", priority = 415)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidate_Parallel_Recon_Sessions_Spectrum(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidate_Parallel_Recon_Sessions_Spectrum");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Parallel Sessions of Reconciliation and Check for Error");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("Spectrum", "Recon", "1", "ParallelRun", "No");

			log.debug("Leaving DnR_toValidate_Parallel_Recon_Sessions_Spectrum");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidate_Parallel_Recon_Sessions_Spectrum:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* OSSNETCRACKER-301
	 * Test Scenario : To verify user should not be able to run multiple DataFlow session's at the same time ( ALCATEL )
	 */

	@DataProvider
	public Object[][] getData416(){
		return Utility.getData(xls,"TC416");
	}

	@Test (dataProvider="getData416", priority = 416)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidate_Parallel_DataFlow_Sessions_Alcatel(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidate_Parallel_DataFlow_Sessions_Alcatel");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Parallel Sessions of DataExport and Recon and Check for Error");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("Alcatel", "DE", "1", "ParallelRun", "Yes");

			log.debug("Leaving DnR_toValidate_Parallel_DataFlow_Sessions_Alcatel");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidate_Parallel_DataFlow_Sessions_Alcatel:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-301
	 * Test Scenario : To verify user should not be able to run multiple DataFlow session's at the same time ( CIN )
	 */
	
	@DataProvider
	public Object[][] getData417(){
		return Utility.getData(xls,"TC417");
	}

	@Test (dataProvider="getData417", priority = 417)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidate_Parallel_DataFlow_Sessions_CIN(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidate_Parallel_DataFlow_Sessions_CIN");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Parallel Sessions of DataExport and Recon and Check for Error");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("CIN", "DE", "1", "ParallelRun", "Yes");

			log.debug("Leaving DnR_toValidate_Parallel_DataFlow_Sessions_CIN");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidate_Parallel_DataFlow_Sessions_CIN:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-301
	 * Test Scenario : To verify user should not be able to run multiple DataFlow session's at the same time ( IPBB )
	 */
	
	@DataProvider
	public Object[][] getData418(){
		return Utility.getData(xls,"TC418");
	}

	@Test (dataProvider="getData418", priority = 418)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidate_Parallel_DataFlow_Sessions_IPBB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidate_Parallel_DataFlow_Sessions_IPBB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Parallel Sessions of DataExport and Recon and Check for Error");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("IPBB", "DE", "1", "ParallelRun", "Yes");

			log.debug("Leaving DnR_toValidate_Parallel_DataFlow_Sessions_IPBB");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidate_Parallel_DataFlow_Sessions_IPBB:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* OSSNETCRACKER-301
	 * Test Scenario : To verify user should not be able to run multiple DataFlow session's at the same time ( CMTS )
	 */

	@DataProvider
	public Object[][] getData419(){
		return Utility.getData(xls,"TC419");
	}

	@Test (dataProvider="getData419", priority = 419)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidate_Parallel_DataFlow_Sessions_CMTS(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidate_Parallel_DataFlow_Sessions_CMTS");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Parallel Sessions of DataExport and Recon and Check for Error");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("CMTS", "DE", "1", "ParallelRun", "Yes");

			log.debug("Leaving DnR_toValidate_Parallel_DataFlow_Sessions_CMTS");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidate_Parallel_DataFlow_Sessions_CMTS:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-301
	 * Test Scenario : To verify user should not be able to run multiple DataFlow session's at the same time ( ETX )
	 */
	
	@DataProvider
	public Object[][] getData420(){
		return Utility.getData(xls,"TC420");
	}

	@Test (dataProvider="getData420", priority = 420)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidate_Parallel_DataFlow_Sessions_ETX(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidate_Parallel_DataFlow_Sessions_ETX");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Parallel Sessions of DataExport and Recon and Check for Error");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("ETX", "DE", "1", "ParallelRun", "Yes");

			log.debug("Leaving DnR_toValidate_Parallel_DataFlow_Sessions_ETX");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidate_Parallel_DataFlow_Sessions_ETX:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* OSSNETCRACKER-301
	 * Test Scenario : To verify user should not be able to run multiple DataFlow session's at the same time ( FDB )
	 */

	@DataProvider
	public Object[][] getData421(){
		return Utility.getData(xls,"TC421");
	}

	@Test (dataProvider="getData421", priority = 421)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidate_Parallel_DataFlow_Sessions_FDB(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidate_Parallel_DataFlow_Sessions_FDB");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Parallel Sessions of DataExport and Recon and Check for Error");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("FDB", "DE", "1", "ParallelRun", "Yes");

			log.debug("Leaving DnR_toValidate_Parallel_DataFlow_Sessions_FDB");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidate_Parallel_DataFlow_Sessions_FDB:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* OSSNETCRACKER-301
	 * Test Scenario : To verify user should not be able to run multiple DataFlow session's at the same time ( IPV4 )
	 */

	@DataProvider
	public Object[][] getData422(){
		return Utility.getData(xls,"TC422");
	}

	@Test (dataProvider="getData422", priority = 422)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidate_Parallel_DataFlow_Sessions_IPV4(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidate_Parallel_DataFlow_Sessions_IPV4");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Parallel Sessions of DataExport and Recon and Check for Error");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("IPV4", "DE", "1", "ParallelRun", "Yes");

			log.debug("Leaving DnR_toValidate_Parallel_DataFlow_Sessions_IPV4");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidate_Parallel_DataFlow_Sessions_IPV4:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-301
	 * Test Scenario : To verify user should not be able to run multiple DataFlow session's at the same time ( Spectrum )
	 */
	
	@DataProvider
	public Object[][] getData423(){
		return Utility.getData(xls,"TC423");
	}

	@Test (dataProvider="getData423", priority = 423)
	@SuppressWarnings(value={ "rawtypes" })
	public void DnR_toValidate_Parallel_DataFlow_Sessions_Spectrum(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering DnR_toValidate_Parallel_DataFlow_Sessions_Spectrum");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}

			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();

			ExtentTest childTest1=extent.startTest("Trigger the Parallel Sessions of DataExport and Recon and Check for Error");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToDataFlow("Spectrum", "DE", "1", "ParallelRun", "Yes");

			log.debug("Leaving DnR_toValidate_Parallel_DataFlow_Sessions_Spectrum");

		}catch(Exception e)
		{
			log.error("Error in DnR_toValidate_Parallel_DataFlow_Sessions_Spectrum:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
}
