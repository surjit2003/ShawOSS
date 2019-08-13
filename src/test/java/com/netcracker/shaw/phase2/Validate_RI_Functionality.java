package com.netcracker.shaw.phase2;

import java.util.Date;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.netcracker.shaw.at_shaw_sd.jdbc.JDBCValidator;
import com.netcracker.shaw.at_shaw_sd.pageobject.LandingPage;
import com.netcracker.shaw.at_shaw_sd.pageobject.RIFlowPage;
import com.netcracker.shaw.at_shaw_sd.pageobject.DnRFlowPage;
import com.netcracker.shaw.at_shaw_sd.util.Constants;
import com.netcracker.shaw.at_shaw_sd.util.Utility;
import com.netcracker.shaw.setup.SeleniumTestUp;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Validate_RI_Functionality extends SeleniumTestUp{
	
	LandingPage landing=null;
	DnRFlowPage dnrFlow =null;
	RIFlowPage riFlow =null;
	JDBCValidator jdbc=null;
	Logger log=Logger.getLogger(Validate_RI_Functionality.class);
	
	/* OSSNETCRACKER-439
	 * Test Scenario : To verify adding of device manually. 
	 */
	
	@DataProvider
	public Object[][] getData201(){
		return Utility.getData(xls,"TC201");
	}

	@Test (dataProvider="getData201", priority = 201)
	@SuppressWarnings(value={ "rawtypes" })
	public void RI_toValidate_Add_Device_Manually(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering RI_toValidate_Add_Device_Manually");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String elementName="TestNE_" + Constants.NE_DTM_FORMAT.format(new Date());
			String deviceType = "cisco";
			String deviceName = "Cisco 1002";
			
			ExtentTest childTest0=extent.startTest("Login to the Server");
			test.appendChild(childTest0);
			landing=createChildTest(childTest0,extent,landing);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			landing.LoginOSS();
			
			ExtentTest childTest1=extent.startTest("Navigate to Default Location and there by to Automation Provider Location");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			riFlow=createChildTest(childTest1,extent,riFlow);
			dnrFlow.selectLocation("DefaultLoc");
			riFlow.checkAutomationLoc();
			
			ExtentTest childTest2=extent.startTest("Select NetworkElements Tab and Addd New Network Element");
			test.appendChild(childTest2);
			riFlow=createChildTest(childTest2,extent,riFlow);
			riFlow.addNewNetworkElement(elementName);
			
			ExtentTest childTest3=extent.startTest("Add Device under NE from existing repository");
			test.appendChild(childTest3);
			riFlow=createChildTest(childTest3,extent,riFlow);
			riFlow.addNewDeviceForNE(elementName, deviceType, deviceName );
			
			log.debug("Leaving RI_toValidate_Add_Device_Manually");
		}catch(Exception e)
		{
			log.error("Error in RI_toValidate_Add_Device_Manually:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-439 - regression impacted
	 * Test Scenario : To verify insertion of card into device manually.  
	 */

	@DataProvider
	public Object[][] getData202(){
		return Utility.getData(xls,"TC202");
	}

	@Test (dataProvider="getData202", priority = 202)
	@SuppressWarnings(value={ "rawtypes" })
	public void RI_toValidate_Insert_Card_Manually(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering RI_toValidate_Insert_Card_Manually");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String elementName="TestNE_" + Constants.NE_DTM_FORMAT.format(new Date());
			String deviceType = "cisco";
			String deviceName = "Cisco 1002";
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest0=extent.startTest("Navigate to Default Location and there by to Automation Provider Location");
			test.appendChild(childTest0);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			riFlow=createChildTest(childTest0,extent,riFlow);
			dnrFlow.selectLocation("DefaultLoc");
			riFlow.checkAutomationLoc();
			
			ExtentTest childTest1=extent.startTest("Select NetworkElements Tab and Addd New Network Element");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			riFlow=createChildTest(childTest1,extent,riFlow);
			riFlow.addNewNetworkElement(elementName);
			
			ExtentTest childTest2=extent.startTest("Add Device under NE from existing repository");
			test.appendChild(childTest2);
			landing=createChildTest(childTest2,extent,landing);
			riFlow=createChildTest(childTest2,extent,riFlow);
			riFlow.addNewDeviceForNE(elementName, deviceType, deviceName );
			
			ExtentTest childTest3=extent.startTest("Select a Slot on the Device and Insert a Card");
			test.appendChild(childTest3);
			landing=createChildTest(childTest3,extent,landing);
			riFlow=createChildTest(childTest3,extent,riFlow);
			riFlow.insertCardInSlot(deviceName);
			
			log.debug("Leaving RI_toValidate_Insert_Card_Manually");
		}catch(Exception e)
		{
			log.error("Error in RI_toValidate_Insert_Card_Manually:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* OSSNETCRACKER-439 - regression impacted
	 * Test Scenario : To verify replacing of card from existing repository for a device manually.  
	 */
	
	@DataProvider
	public Object[][] getData203(){
		return Utility.getData(xls,"TC203");
	}

	@Test (dataProvider="getData203", priority = 203)
	@SuppressWarnings(value={ "rawtypes" })
	public void RI_toValidate_Replace_Card_From_Repository(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering RI_toValidate_Replace_Card_From_Repository");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String elementName="TestNE_" + Constants.NE_DTM_FORMAT.format(new Date());
			String deviceType = "cisco";
			String deviceName = "Cisco 1002";
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest0=extent.startTest("Navigate to Default Location and there by to Automation Provider Location");
			test.appendChild(childTest0);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			riFlow=createChildTest(childTest0,extent,riFlow);
			dnrFlow.selectLocation("DefaultLoc");
			riFlow.checkAutomationLoc();
			
			ExtentTest childTest1=extent.startTest("Select NetworkElements Tab and Addd New Network Element");
			test.appendChild(childTest1);
			riFlow=createChildTest(childTest1,extent,riFlow);
			riFlow.addNewNetworkElement(elementName);
			
			ExtentTest childTest2=extent.startTest("Add Device under NE from existing repository");
			test.appendChild(childTest2);
			riFlow=createChildTest(childTest2,extent,riFlow);
			riFlow.addNewDeviceForNE(elementName, deviceType, deviceName );
			
			ExtentTest childTest3=extent.startTest("Select a Slot on the Device and Insert a Card");
			test.appendChild(childTest3);
			riFlow=createChildTest(childTest3,extent,riFlow);
			riFlow.insertCardInSlot(deviceName);
			
			ExtentTest childTest4=extent.startTest("Replace the existing card from Repository");
			test.appendChild(childTest4);
			riFlow=createChildTest(childTest4,extent,riFlow);
			riFlow.replaceCard();
			
			log.debug("Leaving RI_toValidate_Replace_Card_From_Repository");
		}catch(Exception e)
		{
			log.error("Error in RI_toValidate_Replace_Card_From_Repository:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* OSSNETCRACKER-439 - regression impacted
	 * Test Scenario : To verify removing of existing card for a device manually.  
	 */
	
	@DataProvider
	public Object[][] getData204(){
		return Utility.getData(xls,"TC204");
	}

	@Test (dataProvider="getData204", priority = 204)
	@SuppressWarnings(value={ "rawtypes" })
	public void RI_toValidate_Remove_Existing_Card(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering RI_toValidate_Remove_Existing_Card");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String elementName="TestNE_" + Constants.NE_DTM_FORMAT.format(new Date());
			String deviceType = "cisco";
			String deviceName = "Cisco 1002";
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest0=extent.startTest("Navigate to Default Location and there by to Automation Provider Location");
			test.appendChild(childTest0);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			riFlow=createChildTest(childTest0,extent,riFlow);
			dnrFlow.selectLocation("DefaultLoc");
			riFlow.checkAutomationLoc();
			
			ExtentTest childTest1=extent.startTest("Select NetworkElements Tab and Addd New Network Element");
			test.appendChild(childTest1);
			riFlow=createChildTest(childTest1,extent,riFlow);
			riFlow.addNewNetworkElement(elementName);
			
			ExtentTest childTest2=extent.startTest("Add Device under NE from existing repository");
			test.appendChild(childTest2);
			riFlow=createChildTest(childTest2,extent,riFlow);
			riFlow.addNewDeviceForNE(elementName, deviceType, deviceName );
			
			ExtentTest childTest3=extent.startTest("Select a Slot on the Device and Insert a Card");
			test.appendChild(childTest3);
			riFlow=createChildTest(childTest3,extent,riFlow);
			riFlow.insertCardInSlot(deviceName);
			
			ExtentTest childTest4=extent.startTest("Remove the existing card from Slot");
			test.appendChild(childTest4);
			riFlow=createChildTest(childTest4,extent,riFlow);
			riFlow.removeCard();
			
			log.debug("Leaving RI_toValidate_Remove_Existing_Card");
		}catch(Exception e)
		{
			log.error("Error in RI_toValidate_Remove_Existing_Card:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* OSSNETCRACKER-439 - regression impacted
	 * Test Scenario : To verify move and copy functionalities of a Network Element.  
	 */
	
	@DataProvider
	public Object[][] getData205(){
		return Utility.getData(xls,"TC205");
	}

	@Test (dataProvider="getData205", priority = 205)
	@SuppressWarnings(value={ "rawtypes" })
	public void RI_toValidate_Move_And_Copy_NE(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering RI_toValidate_Move_And_Copy_NE");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String elementName="TestNE_" + Constants.NE_DTM_FORMAT.format(new Date());
			String deviceType = "cisco";
			String deviceName = "Cisco 1002";
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest0=extent.startTest("Navigate to Default Location and there by to Automation Provider Location");
			test.appendChild(childTest0);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			riFlow=createChildTest(childTest0,extent,riFlow);
			dnrFlow.selectLocation("DefaultLoc");
			riFlow.checkAutomationLoc();
			
			ExtentTest childTest1=extent.startTest("Select NetworkElements Tab and Addd New Network Element");
			test.appendChild(childTest1);
			riFlow=createChildTest(childTest1,extent,riFlow);
			riFlow.addNewNetworkElement(elementName);
			
			ExtentTest childTest2=extent.startTest("Add Device under NE from existing repository");
			test.appendChild(childTest2);
			riFlow=createChildTest(childTest2,extent,riFlow);
			riFlow.addNewDeviceForNE(elementName, deviceType, deviceName );
			
			ExtentTest childTest3=extent.startTest("Copy the NE created from Automation Location to Default Location");
			test.appendChild(childTest3);
			riFlow=createChildTest(childTest3,extent,riFlow);
			riFlow.copyNetworkElement(elementName);
			
			ExtentTest childTest4=extent.startTest("Move the NE created from Automation Location to Default Location");
			test.appendChild(childTest4);
			riFlow=createChildTest(childTest4,extent,riFlow);
			riFlow.moveNetworkElement(elementName);
			
			log.debug("Leaving RI_toValidate_Move_And_Copy_NE");
		}catch(Exception e)
		{
			log.error("Error in RI_toValidate_Move_And_Copy_NE:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-439 - regression impacted
	 * Test Scenario : To verify creation of Circuit.  
	 */
	
	@DataProvider
	public Object[][] getData206(){
		return Utility.getData(xls,"TC206");
	}

	@Test (dataProvider="getData206", priority = 206)
	@SuppressWarnings(value={ "rawtypes" })
	public void RI_toValidate_Create_Circuit(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering RI_toValidate_Create_Circuit");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String elementName="TestNE_" + Constants.NE_DTM_FORMAT.format(new Date());
			String deviceType = "HITRON";
			String deviceName = "CODA-46";
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest0=extent.startTest("Navigate to Default Location and there by to Automation Provider Location");
			test.appendChild(childTest0);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			riFlow=createChildTest(childTest0,extent,riFlow);
			dnrFlow.selectLocation("DefaultLoc");
			riFlow.checkAutomationLoc();
			
			ExtentTest childTest1=extent.startTest("Select NetworkElements Tab and Addd New Network Element");
			test.appendChild(childTest1);
			riFlow=createChildTest(childTest1,extent,riFlow);
			riFlow.addNewNetworkElement(elementName);
			
			ExtentTest childTest2=extent.startTest("Add Device under NE from existing repository");
			test.appendChild(childTest2);
			riFlow=createChildTest(childTest2,extent,riFlow);
			riFlow.addNewDeviceForNE(elementName, deviceType, deviceName );
			
			ExtentTest childTest3=extent.startTest("Create a circuit and Validate Status for the corresponding device");
			test.appendChild(childTest3);
			riFlow=createChildTest(childTest3,extent,riFlow);
			riFlow.createCircuit(elementName, deviceName , elementName, "LAN 1", elementName, "LAN 2", "Yes");
	
			log.debug("Leaving RI_toValidate_Create_Circuit");
		}catch(Exception e)
		{
			log.error("Error in RI_toValidate_Create_Circuit:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* OSSNETCRACKER-439 - regression impacted
	 * Test Scenario : To verify deletion of a Circuit.  
	 */
	
	@DataProvider
	public Object[][] getData207(){
		return Utility.getData(xls,"TC207");
	}

	@Test (dataProvider="getData207", priority = 207)
	@SuppressWarnings(value={ "rawtypes" })
	public void RI_toValidate_Delete_Circuit(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering RI_toValidate_Delete_Circuit");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String elementName="TestNE_" + Constants.NE_DTM_FORMAT.format(new Date());
			String deviceType = "HITRON";
			String deviceName = "CODA-46";
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest0=extent.startTest("Navigate to Default Location and there by to Automation Provider Location");
			test.appendChild(childTest0);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			riFlow=createChildTest(childTest0,extent,riFlow);
			dnrFlow.selectLocation("DefaultLoc");
			riFlow.checkAutomationLoc();
			
			ExtentTest childTest1=extent.startTest("Select NetworkElements Tab and Addd New Network Element");
			test.appendChild(childTest1);
			riFlow=createChildTest(childTest1,extent,riFlow);
			riFlow.addNewNetworkElement(elementName);
			
			ExtentTest childTest2=extent.startTest("Add Device under NE from existing repository");
			test.appendChild(childTest2);
			riFlow=createChildTest(childTest2,extent,riFlow);
			riFlow.addNewDeviceForNE(elementName, deviceType, deviceName );
			
			ExtentTest childTest3=extent.startTest("Create a circuit and Validate Status for the corresponding device");
			test.appendChild(childTest3);
			riFlow=createChildTest(childTest3,extent,riFlow);
			riFlow.createCircuit(elementName, deviceName , elementName, "LAN 1", elementName, "LAN 2", "Yes");
			
			ExtentTest childTest4=extent.startTest("Delete the circuit created earlier and Validate Status for the corresponding device");
			test.appendChild(childTest4);
			riFlow=createChildTest(childTest4,extent,riFlow);
			riFlow.deleteCircuit(elementName);
	
			log.debug("Leaving RI_toValidate_Delete_Circuit");
		}catch(Exception e)
		{
			log.error("Error in RI_toValidate_Delete_Circuit:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* OSSNETCRACKER-508
	 * Test Scenario : To verify IP Ranges Split and Merge functionalities for IPV4.  
	 */
	
	@DataProvider
	public Object[][] getData208(){
		return Utility.getData(xls,"TC208");
	}

	@Test (dataProvider="getData208", priority = 208)
	@SuppressWarnings(value={ "rawtypes" })
	public void RI_toValidate_IP_Range_Split_Merge_IPV4(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering RI_toValidate_IP_Range_Split_Merge_IPV4");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest0=extent.startTest("Navigate to IP Planner and check for IP Range: 245.245.0.0/16 in IPV4 Ranges");
			test.appendChild(childTest0);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			riFlow=createChildTest(childTest0,extent,riFlow);
			dnrFlow.selectLocation("IPPlanner");
			riFlow.checkIPRangeExistence("IPv4 Ranges");
			
			ExtentTest childTest1=extent.startTest("Split and Merge IP Range for IPV4");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			riFlow=createChildTest(childTest1,extent,riFlow);
			riFlow.splitAndMergeIPRange("IPv4 Ranges");
		
			log.debug("Leaving RI_toValidate_IP_Range_Split_Merge_IPV4");
		}catch(Exception e)
		{
			log.error("Error in RI_toValidate_IP_Range_Split_Merge_IPV4:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
		
	}
	
	/* OSSNETCRACKER-508
	 * Test Scenario : To verify IP Ranges Split and Merge functionalities for IPV6.  
	 */
	
	@DataProvider
	public Object[][] getData209(){
		return Utility.getData(xls,"TC209");
	}

	@Test (dataProvider="getData209", priority = 209)
	@SuppressWarnings(value={ "rawtypes" })
	public void RI_toValidate_IP_Range_Split_Merge_IPV6(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering RI_toValidate_IP_Range_Split_Merge_IPV6");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest0=extent.startTest("Navigate to IP Planner and check for IP Range: 555:0:0:0:0:0:0:0/16 in IPV6 Ranges");
			test.appendChild(childTest0);
			dnrFlow=createChildTest(childTest0,extent,dnrFlow);
			riFlow=createChildTest(childTest0,extent,riFlow);
			dnrFlow.selectLocation("IPPlanner");
			riFlow.checkIPRangeExistence("IPv6 Ranges");
			
			ExtentTest childTest1=extent.startTest("Split and Merge IP Range for IPV6");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			riFlow=createChildTest(childTest1,extent,riFlow);
			riFlow.splitAndMergeIPRange("IPv6 Ranges");
		
			log.debug("Leaving RI_toValidate_IP_Range_Split_Merge_IPV6");
		}catch(Exception e)
		{
			log.error("Error in RI_toValidate_IP_Range_Split_Merge_IPV6:" + e.getMessage());
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
		
	}
	
	

}
