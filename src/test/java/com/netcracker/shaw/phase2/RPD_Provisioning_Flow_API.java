package com.netcracker.shaw.phase2;

import java.util.Date;
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
import com.netcracker.shaw.at_shaw_sd.util.Constants;
import com.netcracker.shaw.at_shaw_sd.util.Utility;
import com.netcracker.shaw.setup.SeleniumTestUp;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class RPD_Provisioning_Flow_API extends SeleniumTestUp{
	
	LandingPage landing=null;
	DnRFlowPage dnrFlow =null;
	VLANFlowPage vlanFlow =null;
	RIFlowPage riFlow = null;
	JDBCValidator jdbc=null;
	SOAPHelper soapHelper=null;
	Logger log=Logger.getLogger(RPD_Provisioning_Flow_API.class);
	
	/* OSSNETCRACKER-299
	 * Test Scenario : To Verify Transmission circuit creation and RPD Node addition on the NE.
	 */
	
	@DataProvider
	public Object[][] getData124(){
		return Utility.getData(xls,"TC124");
	}

	@Test (dataProvider="getData124", priority = 124)
	@SuppressWarnings(value={ "rawtypes" })
	public void RPD_toValidate_Creation_TransmissionCkt(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering RPD_toValidate_Creation_TransmissionCkt");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String ethernetPort=data.get("ETHERNET_PORT");
			String cinNtwElement=data.get("CIN_NE");
			String rpdNode=data.get("RPD_NODE");
			String circuitName = "Test_" + Constants.NE_DTM_FORMAT.format(new Date());
			String fetchLocFDB=data.get("FETCH_LOC"); 
			String fetchLocCIN=data.get("FTP_LOC");
			String macAddress = data.get("MAC_ADDRESS");
			String ccapValue = data.get("dCCAP_VALUE");
			
			System.out.println("rpd node , ethernetPort , cinDevice : " + rpdNode + " " + ethernetPort + " " + cinNtwElement );
			
			ExtentTest childTest=extent.startTest("Login to the Server");
			test.appendChild(childTest);
			landing=createChildTest(childTest,extent,landing);
			landing.LoginOSS();
			
			//Asked to Comment
			/*ExtentTest childTest1=extent.startTest("Run the FDB Reconciliation.");
			test.appendChild(childTest1);
			dnrFlow=createChildTest(childTest1,extent,dnrFlow);
			dnrFlow.navigateToIntegrationInterface("FDB", fetchLocFDB, null, null);
			dnrFlow.navigateToDataFlow("FDB", "Recon", "1", "VLANRun", "No");
			
			ExtentTest childTest2=extent.startTest("Run the CIN Reconciliation.");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.navigateToIntegrationInterface("CIN", fetchLocCIN, null, null);
			dnrFlow.navigateToDataFlow("CIN", "Recon", "1", "VLANRun", "No");*/
			
			ExtentTest childTest3=extent.startTest("Navigate to Shaw Inv Project , Transmission Circuit Folder.");
			test.appendChild(childTest3);
			dnrFlow=createChildTest(childTest3,extent,dnrFlow);
			riFlow=createChildTest(childTest3,extent,riFlow);
			dnrFlow.selectLocation("ShawProject");
			dnrFlow.selectLocation("Transmission Circuit");
			
			ExtentTest childTest4=extent.startTest("Create Transmission Circuit and Add Carrier and Resource Elements.");
			test.appendChild(childTest4);
			riFlow=createChildTest(childTest4,extent,riFlow);
			riFlow.createCircuit(circuitName, "", cinNtwElement, ethernetPort, rpdNode, "", "No");
			
			ExtentTest childTest5=extent.startTest("Update the Mac Address for the RPD Node");
			test.appendChild(childTest5);
			vlanFlow=createChildTest(childTest5,extent,vlanFlow);
			vlanFlow.updateOrValidateMacAddress(rpdNode, macAddress, "Update");
			
			ExtentTest childTest6=extent.startTest("Update the dCCAP Address for the Ethernet Port");
			test.appendChild(childTest6);
			vlanFlow=createChildTest(childTest6,extent,vlanFlow);
			vlanFlow.updateDCCAPValue(cinNtwElement, ethernetPort, ccapValue);
			
			log.debug("Leaving RPD_toValidate_Creation_TransmissionCkt");
		}catch(Exception e)
		{
			log.error("Error in RPD_toValidate_Creation_TransmissionCkt:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* OSSNETCRACKER-299
	 * Test Scenario : To fetch RPDNode details of RPD Node which is part of a circuit.
	 */
	
	@DataProvider
	public Object[][] getData125(){
		return Utility.getData(xls,"TC125");
	}

	@Test (dataProvider="getData125", priority = 125)
	@SuppressWarnings(value={ "rawtypes" })
	public void RPD_toGetRPDNodedetails_PartofCircuit(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering RPD_toGetRPDNodedetails_PartofCircuit");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String ethernetPort=data.get("ETHERNET_PORT");
			String cinNtwElement=data.get("CIN_NE");
			String rpdNode=data.get("RPD_NODE");
			String macAddress = data.get("MAC_ADDRESS");
			String dccapValue = data.get("dCCAP_VALUE");
			
			
			soapHelper = new SOAPHelper("GetRPDNode.xml", Constants.SOAP_DEFAULT_HEADER, "query", test);
			
			ExtentTest childTest1=extent.startTest("Trigger the Request of getRPDNodeConnectionDetails");
			test.appendChild(childTest1);
			soapHelper=createChildTest(childTest1,extent,soapHelper);
			String reqXml1 = soapHelper.readRequestReplaceExcelValues("GetRPDNode_InCircuit");
			String responseXml1 = soapHelper.sendMessageToServerReplaceHeader(reqXml1);
			soapHelper.parseXml(responseXml1);
			
			//Validate the Port 
			ExtentTest childTest2=extent.startTest("Get Port Name , MacAddress, CCAP from Response");
			test.appendChild(childTest2);
			vlanFlow=createChildTest(childTest2,extent,vlanFlow);
			vlanFlow.setSoapHelper(soapHelper);
			soapHelper=createChildTest(childTest2,extent,soapHelper);
			String resPortName = soapHelper.getAttributeValue("Body/queryResponse/namedQueryResponse/result/response/cinLeafPort/portName");
			String resMacAddValue = soapHelper.getAttributeValue("Body/queryResponse/namedQueryResponse/result/response/macAddress");
			String resCCAPValue = soapHelper.getAttributeValue("Body/queryResponse/namedQueryResponse/result/response/cinLeafPort/dccapFQDN");
			
			ExtentTest childTest3=extent.startTest("Validate Response Attributes");
			test.appendChild(childTest3);
			vlanFlow=createChildTest(childTest3,extent,vlanFlow);
			vlanFlow.setSoapHelper(soapHelper);
			soapHelper=createChildTest(childTest3,extent,soapHelper);
			vlanFlow.validateResponse(resPortName, ethernetPort, "PortName");
			vlanFlow.validateResponse(resMacAddValue, macAddress, "Mac Address");
			vlanFlow.validateResponse(resCCAPValue, dccapValue, "dCCAP FQDN Value");
			
			log.debug("Leaving RPD_toGetRPDNodedetails_PartofCircuit");
		}catch(Exception e)
		{
			log.error("Error in RPD_toGetRPDNodedetails_PartofCircuit:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* OSSNETCRACKER-299
	 * Test Scenario : To fetch RPDNode details of RPD Node which is not part of a circuit.
	 */
	
	@DataProvider
	public Object[][] getData126(){
		return Utility.getData(xls,"TC126");
	}

	@Test (dataProvider="getData126", priority = 126)
	@SuppressWarnings(value={ "rawtypes" })
	public void RPD_toGetRPDNodedetails_NotPartofCircuit(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering RPD_toGetRPDNodedetails_NotPartofCircuit");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			soapHelper = new SOAPHelper("GetRPDNode.xml", Constants.SOAP_DEFAULT_HEADER, "query", test);
			
			ExtentTest childTest1=extent.startTest("Trigger the Request of getRPDNodeConnectionDetails");
			test.appendChild(childTest1);
			soapHelper=createChildTest(childTest1,extent,soapHelper);
			String reqXml1 = soapHelper.readRequestReplaceExcelValues("GetRPDNode_NotInCircuit");
			String responseXml1 = soapHelper.sendMessageToServerReplaceHeader(reqXml1);
			soapHelper.parseXml(responseXml1);
			
			ExtentTest childTest2=extent.startTest("Validate Error Reported in Response");
			test.appendChild(childTest2);
			vlanFlow=createChildTest(childTest2,extent,vlanFlow);
			vlanFlow.setSoapHelper(soapHelper);
			soapHelper=createChildTest(childTest2,extent,soapHelper);
			vlanFlow.validateError(responseXml1, "Not Found");
		
			log.debug("Leaving RPD_toGetRPDNodedetails_NotPartofCircuit");
		}catch(Exception e)
		{
			log.error("Error in RPD_toGetRPDNodedetails_NotPartofCircuit:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* OSSNETCRACKER-299
	 * Test Scenario : To fetch RPDNode details of RPD Node which is not valid.
	 */
	
	@DataProvider
	public Object[][] getData127(){
		return Utility.getData(xls,"TC127");
	}

	@Test (dataProvider="getData127", priority = 127)
	@SuppressWarnings(value={ "rawtypes" })
	public void RPD_toGetRPDNodedetails_InvalidRPDNode(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering RPD_toGetRPDNodedetails_InvalidRPDNode");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			soapHelper = new SOAPHelper("GetRPDNode.xml", Constants.SOAP_DEFAULT_HEADER, "query", test);
			
			ExtentTest childTest1=extent.startTest("Trigger the Request of getRPDNodeConnectionDetails");
			test.appendChild(childTest1);
			soapHelper=createChildTest(childTest1,extent,soapHelper);
			String reqXml1 = soapHelper.readRequestReplaceExcelValues("GetRPDNode_InvalidNodeName");
			String responseXml1 = soapHelper.sendMessageToServerReplaceHeader(reqXml1);
			soapHelper.parseXml(responseXml1);
			
			ExtentTest childTest2=extent.startTest("Validate Error Reported in Response");
			test.appendChild(childTest2);
			vlanFlow=createChildTest(childTest2,extent,vlanFlow);
			vlanFlow.setSoapHelper(soapHelper);
			soapHelper=createChildTest(childTest2,extent,soapHelper);
			vlanFlow.validateError(responseXml1, "Not Found");
		
			log.debug("Leaving RPD_toGetRPDNodedetails_InvalidRPDNode");
		}catch(Exception e)
		{
			log.error("Error in RPD_toGetRPDNodedetails_InvalidRPDNode:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* OSSNETCRACKER-299
	 * Test Scenario : To update MAC Address field with a Valid MAC Address value.
	 */
	
	@DataProvider
	public Object[][] getData128(){
		return Utility.getData(xls,"TC128");
	}

	@Test (dataProvider="getData128", priority = 128)
	@SuppressWarnings(value={ "rawtypes" })
	public void RPD_updateMacAddress_ValidMACAddress(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering RPD_updateMacAddress_ValidMACAddress");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String rpdNode=data.get("RPD_NODE");
			String macAddress = xls.getCellData("SOAPRequestData", 3, 16);
			System.out.println("PoolId : " + macAddress);
			
			System.out.println("RPD Node : " + rpdNode);
			soapHelper = new SOAPHelper("updateMacAddress.xml", Constants.SOAP_DEFAULT_HEADER, "query", test);
			
			ExtentTest childTest1=extent.startTest("Trigger the Request of updateMacAddress");
			test.appendChild(childTest1);
			soapHelper=createChildTest(childTest1,extent,soapHelper);
			String reqXml1 = soapHelper.readRequestReplaceExcelValues("UpdateMacAddress_ValidMacAddres");
			String responseXml1 = soapHelper.sendMessageToServerReplaceHeader(reqXml1);
			soapHelper.parseXml(responseXml1);
			
			ExtentTest childTest2=extent.startTest("Login to the Server");
			test.appendChild(childTest2);
			landing=createChildTest(childTest2,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest3=extent.startTest("Verify the MacAddress Feild for the RPD Node");
			test.appendChild(childTest3);
			vlanFlow=createChildTest(childTest3,extent,vlanFlow);
			vlanFlow.updateOrValidateMacAddress(rpdNode, macAddress, "Validate");
		
			log.debug("Leaving RPD_updateMacAddress_ValidMACAddress");
		}catch(Exception e)
		{
			log.error("Error in RPD_updateMacAddress_ValidMACAddress:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* OSSNETCRACKER-299
	 * Test Scenario : To update Administrative Status field with Active Status.
	 */
	
	@DataProvider
	public Object[][] getData129(){
		return Utility.getData(xls,"TC129");
	}

	@Test (dataProvider="getData129", priority = 129)
	@SuppressWarnings(value={ "rawtypes" })
	public void RPD_toUpdate_PortStatus_Active(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering RPD_toUpdate_PortStatus_Active");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String ethernetObjID = xls.getCellData("SOAPRequestData", 2, 19);
			System.out.println("ethernetObjID : " + ethernetObjID);
			
			soapHelper = new SOAPHelper("updatePortStatus.xml", Constants.SOAP_DEFAULT_HEADER, "query", test);
			
			ExtentTest childTest1=extent.startTest("Trigger the Request of updatePortStatus with Valid Port Object Id");
			test.appendChild(childTest1);
			soapHelper=createChildTest(childTest1,extent,soapHelper);
			String reqXml1 = soapHelper.readRequestReplaceExcelValues("UpdatePortStatus_ValidPortId_StatusActive");
			String responseXml1 = soapHelper.sendMessageToServerReplaceHeader(reqXml1);
			soapHelper.parseXml(responseXml1);
			 
			ExtentTest childTest2=extent.startTest("Login to the Server");
			test.appendChild(childTest2);
			landing=createChildTest(childTest2,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest3=extent.startTest("Verify the Administrative Status field of the Ethernet Port");
			test.appendChild(childTest3);
			vlanFlow=createChildTest(childTest3,extent,vlanFlow);
			vlanFlow.validateAdminStatus(ethernetObjID, "Active");
		
			log.debug("Leaving RPD_toUpdate_PortStatus_Active");
		}catch(Exception e)
		{
			log.error("Error in RPD_toUpdate_PortStatus_Active:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* OSSNETCRACKER-299
	 * Test Scenario : To update Administrative Status field with Testing Status.
	 */
	
	@DataProvider
	public Object[][] getData130(){
		return Utility.getData(xls,"TC130");
	}

	@Test (dataProvider="getData130", priority = 130)
	@SuppressWarnings(value={ "rawtypes" })
	public void RPD_toUpdate_PortStatus_Testing(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering RPD_toUpdate_PortStatus_Testing");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String ethernetObjID = xls.getCellData("SOAPRequestData", 2, 20);
			System.out.println("ethernetObjID : " + ethernetObjID);
			
			soapHelper = new SOAPHelper("updatePortStatus.xml", Constants.SOAP_DEFAULT_HEADER, "query", test);
			
			ExtentTest childTest1=extent.startTest("Trigger the Request of updatePortStatus with Valid Port Object Id");
			test.appendChild(childTest1);
			soapHelper=createChildTest(childTest1,extent,soapHelper);
			String reqXml1 = soapHelper.readRequestReplaceExcelValues("UpdatePortStatus_ValidPortId_StatusTesting");
			String responseXml1 = soapHelper.sendMessageToServerReplaceHeader(reqXml1);
			soapHelper.parseXml(responseXml1);
			 
			ExtentTest childTest2=extent.startTest("Login to the Server");
			test.appendChild(childTest2);
			landing=createChildTest(childTest2,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest3=extent.startTest("Verify the Administrative Status feild of the Ethernet Port");
			test.appendChild(childTest3);
			vlanFlow=createChildTest(childTest3,extent,vlanFlow);
			vlanFlow.validateAdminStatus(ethernetObjID, "Testing");
		
			log.debug("Leaving RPD_toUpdate_PortStatus_Testing");
		}catch(Exception e)
		{
			log.error("Error in RPD_toUpdate_PortStatus_Testing:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* OSSNETCRACKER-299
	 * Test Scenario : To update Administrative Status field with Down Status.
	 */
	
	@DataProvider
	public Object[][] getData131(){
		return Utility.getData(xls,"TC131");
	}

	@Test (dataProvider="getData131", priority = 131)
	@SuppressWarnings(value={ "rawtypes" })
	public void RPD_toUpdate_PortStatus_Down(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering RPD_toUpdate_PortStatus_Down");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String ethernetObjID = xls.getCellData("SOAPRequestData", 2, 21);
			System.out.println("ethernetObjID : " + ethernetObjID);
			
			soapHelper = new SOAPHelper("updatePortStatus.xml", Constants.SOAP_DEFAULT_HEADER, "query", test);
			
			ExtentTest childTest1=extent.startTest("Trigger the Request of updatePortStatus with Valid Port Object Id");
			test.appendChild(childTest1);
			soapHelper=createChildTest(childTest1,extent,soapHelper);
			String reqXml1 = soapHelper.readRequestReplaceExcelValues("UpdatePortStatus_ValidPortId_StatusDown");
			String responseXml1 = soapHelper.sendMessageToServerReplaceHeader(reqXml1);
			soapHelper.parseXml(responseXml1);
			 
			ExtentTest childTest2=extent.startTest("Login to the Server");
			test.appendChild(childTest2);
			landing=createChildTest(childTest2,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest3=extent.startTest("Verify the Administrative Status feild of the Ethernet Port");
			test.appendChild(childTest3);
			vlanFlow=createChildTest(childTest3,extent,vlanFlow);
			vlanFlow.validateAdminStatus(ethernetObjID, "Down");
		
			log.debug("Leaving RPD_toUpdate_PortStatus_Down");
		}catch(Exception e)
		{
			log.error("Error in RPD_toUpdate_PortStatus_Down:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* OSSNETCRACKER-299
	 * Test Scenario : To update Administrative Status field with Invalid Port Object Id.
	 */
	
	@DataProvider
	public Object[][] getData132(){
		return Utility.getData(xls,"TC132");
	}

	@Test (dataProvider="getData132", priority = 132)
	@SuppressWarnings(value={ "rawtypes" })
	public void RPD_toUpdate_PortStatus_InvalidPortID(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering RPD_toUpdate_PortStatus_InvalidPortID");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			soapHelper = new SOAPHelper("updatePortStatus.xml", Constants.SOAP_DEFAULT_HEADER, "query", test);
			
			ExtentTest childTest1=extent.startTest("Trigger the Request of updatePortStatus");
			test.appendChild(childTest1);
			soapHelper=createChildTest(childTest1,extent,soapHelper);
			String reqXml1 = soapHelper.readRequestReplaceExcelValues("UpdatePortStatus_InvalidPortId");
			String responseXml1 = soapHelper.sendMessageToServerReplaceHeader(reqXml1);
			soapHelper.parseXml(responseXml1);
			
			ExtentTest childTest2=extent.startTest("Validate Error Reported in Response");
			test.appendChild(childTest2);
			vlanFlow=createChildTest(childTest2,extent,vlanFlow);
			vlanFlow.setSoapHelper(soapHelper);
			soapHelper=createChildTest(childTest2,extent,soapHelper);
			vlanFlow.validateError(responseXml1, "Not Found");
		
			log.debug("Leaving RPD_toUpdate_PortStatus_InvalidPortID");
		}catch(Exception e)
		{
			log.error("Error in RPD_toUpdate_PortStatus_InvalidPortID:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* OSSNETCRACKER-448
	 * Test Scenario : To fetch RPD Node details with Valid MAC Address value. 
	 */
	
	@DataProvider
	public Object[][] getData133(){
		return Utility.getData(xls,"TC133");
	}

	@Test (dataProvider="getData133", priority = 133)
	@SuppressWarnings(value={ "rawtypes" })
	public void RPD_getRPDNodeByMacAddress_ValidMACAddress(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering RPD_getRPDNodeByMacAddress_ValidMACAddress");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			soapHelper = new SOAPHelper("GetRPDNodeByMacAddress.xml", Constants.SOAP_DEFAULT_HEADER, "query", test);
			String macAddress = xls.getCellData("SOAPRequestData", 2, 23);
					
			ExtentTest childTest1=extent.startTest("Trigger the Request of getRPDNodeByMACAddress");
			test.appendChild(childTest1);
			soapHelper=createChildTest(childTest1,extent,soapHelper);
			String reqXml1 = soapHelper.readRequestReplaceExcelValues("GetNodeByMacAdd_ValidMacAdd");
			String responseXml1 = soapHelper.sendMessageToServerReplaceHeader(reqXml1);
			soapHelper.parseXml(responseXml1);
			String resRPDNode = soapHelper.getAttributeValue("Body/queryResponse/namedQueryResponse/result/response/nodeId");
			
			ExtentTest childTest2=extent.startTest("Login to the Server");
			test.appendChild(childTest2);
			landing=createChildTest(childTest2,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest3=extent.startTest("Validate the RPD Node in response");
			test.appendChild(childTest3);
			vlanFlow=createChildTest(childTest3,extent,vlanFlow);
			vlanFlow.setSoapHelper(soapHelper);
			soapHelper=createChildTest(childTest3,extent,soapHelper);
			vlanFlow.updateOrValidateMacAddress(resRPDNode, macAddress, "Validate");
		
			log.debug("Leaving RPD_getRPDNodeByMacAddress_ValidMACAddress");
		}catch(Exception e)
		{
			log.error("Error in RPD_getRPDNodeByMacAddress_ValidMACAddress:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* OSSNETCRACKER-448
	 * Test Scenario : To fetch RPD Node details with Invalid MAC Address value. 
	 */
	
	@DataProvider
	public Object[][] getData134(){
		return Utility.getData(xls,"TC134");
	}

	@Test (dataProvider="getData134", priority = 134)
	@SuppressWarnings(value={ "rawtypes" })
	public void RPD_getRPDNodeByMacAddress_InValidMACAddress(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering RPD_getRPDNodeByMacAddress_InValidMACAddress");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			soapHelper = new SOAPHelper("GetRPDNodeByMacAddress.xml", Constants.SOAP_DEFAULT_HEADER, "query", test);
			
			ExtentTest childTest1=extent.startTest("Trigger the Request of getRPDNodeByMACAddress");
			test.appendChild(childTest1);
			soapHelper=createChildTest(childTest1,extent,soapHelper);
			String reqXml1 = soapHelper.readRequestReplaceExcelValues("GetNodeByMacAdd_InvalidMacAdd");
			String responseXml1 = soapHelper.sendMessageToServerReplaceHeader(reqXml1);
			soapHelper.parseXml(responseXml1);
			
			ExtentTest childTest2=extent.startTest("Validate Error Reported in Response");
			test.appendChild(childTest2);
			vlanFlow=createChildTest(childTest2,extent,vlanFlow);
			vlanFlow.setSoapHelper(soapHelper);
			soapHelper=createChildTest(childTest2,extent,soapHelper);
			vlanFlow.validateError(responseXml1, "Invalid MAC address");
		
			log.debug("Leaving RPD_getRPDNodeByMacAddress_InValidMACAddress");
		}catch(Exception e)
		{
			log.error("Error in RPD_getRPDNodeByMacAddress_InValidMACAddress:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* OSSNETCRACKER- 448
	 * Test Scenario : To fetch RPD Node details with Blank MAC Address value. 
	 */
	
	@DataProvider
	public Object[][] getData135(){
		return Utility.getData(xls,"TC135");
	}

	@Test (dataProvider="getData135", priority = 135)
	@SuppressWarnings(value={ "rawtypes" })
	public void RPD_getRPDNodeByMacAddress_BlankMACAddress(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering RPD_getRPDNodeByMacAddress_BlankMACAddress");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			soapHelper = new SOAPHelper("GetRPDNodeByMacAddress.xml", Constants.SOAP_DEFAULT_HEADER, "query", test);
			
			ExtentTest childTest1=extent.startTest("Trigger the Request of getRPDNodeByMACAddress");
			test.appendChild(childTest1);
			soapHelper=createChildTest(childTest1,extent,soapHelper);
			String reqXml1 = soapHelper.readRequestReplaceExcelValues("GetNodeByMacAdd_BlankMacAdd");
			String responseXml1 = soapHelper.sendMessageToServerReplaceHeader(reqXml1);
			soapHelper.parseXml(responseXml1);
			
			ExtentTest childTest2=extent.startTest("Validate Error Reported in Response");
			test.appendChild(childTest2);
			vlanFlow=createChildTest(childTest2,extent,vlanFlow);
			vlanFlow.setSoapHelper(soapHelper);
			soapHelper=createChildTest(childTest2,extent,soapHelper);
			vlanFlow.validateError(responseXml1, "Cant be Null");
		
			log.debug("Leaving RPD_getRPDNodeByMacAddress_BlankMACAddress");
		}catch(Exception e)
		{
			log.error("Error in RPD_getRPDNodeByMacAddress_BlankMACAddress:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}

	
	/* OSSNETCRACKER-299
	 * Test Scenario : To verify whether the RPD node be removed from the transmission circuit and delete the circuit. 
	 */
	
	@DataProvider
	public Object[][] getData136(){
		return Utility.getData(xls,"TC136");
	}

	@Test (dataProvider="getData136", priority = 136)
	@SuppressWarnings(value={ "rawtypes" })
	public void RPD_toValidate_Remove_RPDNode(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering RPD_toValidate_Remove_RPDNode");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			String ethernetPort=data.get("ETHERNET_PORT");
			String cinNtwElement=data.get("CIN_NE");
			String rpdNode=data.get("RPD_NODE");
			String circuitName = "Test_" + Constants.NE_DTM_FORMAT.format(new Date());
			
			System.out.println("rpd node , ethernetPort , cinDevice : " + rpdNode + " " + ethernetPort + " " + cinNtwElement );
			
			ExtentTest childTest1=extent.startTest("Login to the Server");
			test.appendChild(childTest1);
			landing=createChildTest(childTest1,extent,landing);
			landing.LoginOSS();
			
			ExtentTest childTest2=extent.startTest("Navigate to Shaw Inv Project , Transmission Circuit Folder.");
			test.appendChild(childTest2);
			dnrFlow=createChildTest(childTest2,extent,dnrFlow);
			dnrFlow.selectLocation("ShawProject");
			dnrFlow.selectLocation("Transmission Circuit");
			
			ExtentTest childTest3=extent.startTest("Create Transmission Circuit and Add Carrier and Resource Elements.");
			test.appendChild(childTest3);
			riFlow=createChildTest(childTest3,extent,riFlow);
			riFlow.createCircuit(circuitName, "", cinNtwElement, ethernetPort, rpdNode, "", "No");
			
			ExtentTest childTest4=extent.startTest("Remove RPD Node Link to the Created Circuit and Delete the Circuit.");
			test.appendChild(childTest4);
			vlanFlow=createChildTest(childTest4,extent,vlanFlow);
			vlanFlow.removeRPDNodeAndDelete(circuitName, rpdNode);
			
			log.debug("Leaving RPD_toValidate_Remove_RPDNode");
		}catch(Exception e)
		{
			log.error("Error in RPD_toValidate_Remove_RPDNode:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
	/* OSSNETCRACKER-299
	 * Test Scenario : To verify whether the RPD node be removed from the transmission circuit and delete the circuit. 
	 */
	
	@DataProvider
	public Object[][] getData139(){
		return Utility.getData(xls,"TC139");
	}

	@Test (dataProvider="getData139", priority = 139)
	@SuppressWarnings(value={ "rawtypes" })
	public void RPD_updateMacAddress_InValidRPDNode(Hashtable<String,String> data) throws Exception{
		try
		{
			log.debug("Entering RPD_updateMacAddress_InValidRPDNode");
			if(data.get("Run").equals("No")){
				throw new SkipException("Skipping the test as runmode is N");
			}
			
			soapHelper = new SOAPHelper("updateMacAddress.xml", Constants.SOAP_DEFAULT_HEADER, "query", test);
			
			ExtentTest childTest1=extent.startTest("Trigger the Request of updateMacAddress");
			test.appendChild(childTest1);
			soapHelper=createChildTest(childTest1,extent,soapHelper);
			String reqXml1 = soapHelper.readRequestReplaceExcelValues("UpdateMacAddress_InvalidRPDNode");
			String responseXml1 = soapHelper.sendMessageToServerReplaceHeader(reqXml1);
			soapHelper.parseXml(responseXml1);
			
			ExtentTest childTest2=extent.startTest("Validate Error Reported in Response");
			test.appendChild(childTest2);
			vlanFlow=createChildTest(childTest2,extent,vlanFlow);
			vlanFlow.setSoapHelper(soapHelper);
			soapHelper=createChildTest(childTest2,extent,soapHelper);
			vlanFlow.validateError(responseXml1, "Not Found");
		
			log.debug("Leaving RPD_updateMacAddress_InValidRPDNode");
		}catch(Exception e)
		{
			log.error("Error in RPD_updateMacAddress_InValidRPDNode:" + e.getMessage());
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Test Failed");
			Assert.fail();
		}
	}
	
}
