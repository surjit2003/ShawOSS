package com.netcracker.shaw.phase2;

import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.netcracker.shaw.at_shaw_sd.jdbc.JDBCValidator;
import com.netcracker.shaw.at_shaw_sd.pageobject.DnRFlowPage;
import com.netcracker.shaw.at_shaw_sd.pageobject.LandingPage;
import com.netcracker.shaw.at_shaw_sd.pageobject.RIFlowPage;
import com.netcracker.shaw.at_shaw_sd.pageobject.VLANFlowPage;
import com.netcracker.shaw.at_shaw_sd.soap.SOAPHelper;
import com.netcracker.shaw.at_shaw_sd.util.Utility;
import com.netcracker.shaw.setup.SeleniumTestUp;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Validation_Grants extends SeleniumTestUp{
	
	LandingPage landing=null;
	DnRFlowPage dnrFlow =null;
	VLANFlowPage vlanFlow =null;
	RIFlowPage riFlow = null;
	JDBCValidator jdbc=null;
	SOAPHelper soapHelper=null;
	Logger log=Logger.getLogger(Validation_Grants.class);
	
	/* OSSNETCRACKER-507
	 * Test Scenario : To Verify Creation and Deletion of SVLAN manually.
	 */
	
	@DataProvider
	public Object[][] getData137(){
		return Utility.getData(xls,"TC137");
	}

	@Test (dataProvider="getData137", priority = 137)
	@SuppressWarnings(value={ "rawtypes" })
	public void VLAN_toCreate_And_Delete_SVLAN_manual(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering VLAN_toCreate_And_Delete_SVLAN_manual");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			String poolOption = data.get("VLAN_POOL");
			int vlanID=Math.round(Float.valueOf(data.get("ACCT_NUM")));
			
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to EOD Network, VLAN Tab");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.selectLocation("EOD_VLAN");
			
			ExtentTest childTest3=extent.startTest("Create New SVLAN");
			test.appendChild(childTest3);
			vlanFlow=createChildTest(childTest3,extent,vlanFlow);
			String poolCreated = vlanFlow.createNewSVLANorINVLAN(poolOption, vlanID, "SVLAN");
			
			ExtentTest childTest4=extent.startTest("Delete the SVLAN that was created in previous Step.");
			test.appendChild(childTest4);
			vlanFlow=createChildTest(childTest4,extent,vlanFlow);
			vlanFlow.deleteNewSVLANorINVLAN(poolCreated, "SVLAN");
			
			log.debug("Leaving VLAN_toCreate_And_Delete_SVLAN_manual");
		}catch(Exception e)
		{
			log.error("Error in VLAN_toCreate_And_Delete_SVLAN_manual:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	/* OSSNETCRACKER-507
	 * Test Scenario : To Verify Creation and Deletion of INVLAN manually.
	 */
	
	@DataProvider
	public Object[][] getData138(){
		return Utility.getData(xls,"TC138");
	}

	@Test (dataProvider="getData138", priority = 138)
	@SuppressWarnings(value={ "rawtypes" })
	public void VLAN_toCreate_And_Delete_INVLAN_manual(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering VLAN_toCreate_And_Delete_INVLAN_manual");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			String poolOption = data.get("VLAN_POOL");
			int vlanID=Math.round(Float.valueOf(data.get("ACCT_NUM")));
			
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to EOD Network, VLAN Tab");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.selectLocation("EOD_VLAN");
			
			ExtentTest childTest3=extent.startTest("Create New INVLAN");
			test.appendChild(childTest3);
			vlanFlow=createChildTest(childTest3,extent,vlanFlow);
			String poolCreated = vlanFlow.createNewSVLANorINVLAN(poolOption, vlanID, "INVLAN");
			
			ExtentTest childTest4=extent.startTest("Delete the INVLAN that was created in previous Step.");
			test.appendChild(childTest4);
			vlanFlow=createChildTest(childTest4,extent,vlanFlow);
			vlanFlow.deleteNewSVLANorINVLAN(poolCreated, "INVLAN");
			
			log.debug("Leaving VLAN_toCreate_And_Delete_INVLAN_manual");
		}catch(Exception e)
		{
			log.error("Error in VLAN_toCreate_And_Delete_INVLAN_manual:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	
}
