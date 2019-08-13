package com.netcracker.shaw.at_shaw_sd.soap;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.netcracker.shaw.at_shaw_sd.util.Constants;
import com.netcracker.shaw.at_shaw_sd.xml.XMLDocument;

public class SOAPClientTest {
	
	public static void main(String[] args) throws SOAPException, SAXException, IOException, ParserConfigurationException {
		
		//new SOAPClientTest().readRequestAndReplaceFromExcel();
		
		//new SOAPClientTest().sendRequestAndReplace();
		
		//new SOAPClientTest().sendAnotherRequest();
		
		new SOAPClientTest().vlanHandlerCommit();
		
	}
	
	private void vlanHandlerCommit() throws SOAPException, SAXException, IOException, ParserConfigurationException {
		SOAPHelper soapHelper = new SOAPHelper("vlanHandlerCommit.xml");
		
		String requestEnvelope = soapHelper.readRequestBody();
		System.out.println("requestEnvelope : " + requestEnvelope);
		
		String responseXml = soapHelper.sendMessageToServerReplaceHeader(requestEnvelope);
		System.out.println("responseXml : " + responseXml);
	}


	private void sendAnotherRequest() throws SOAPException, SAXException, IOException, ParserConfigurationException {
		SOAPHelper soapHelper = new SOAPHelper("DeleteINVLAN.xml", Constants.SOAP_DEFAULT_HEADER, "removeEntitiesByKeys", "");
		String reqXml = soapHelper.readRequestReplaceExcelValues("DeleteINVLAN");
		System.out.println("reqXml : " + reqXml);
		
		String responseXml = soapHelper.sendMessageToServerReplaceHeader(reqXml);
		System.out.println("responseXml : " + responseXml);
		
		/*xmlDocHelper = new XMLDocumentHelper(responseXml);
		String newInVlan = xmlDocHelper.getAttributeValue("Body/queryResponse/namedQueryResponse/result/response/vlan/cVlanName");
		System.out.println("newInVlan : " + newInVlan);*/
	}

	private void readRequestAndReplaceFromExcel() {
		SOAPHelper soapHelper = new SOAPHelper("ReserveINVLAN2.xml");
		String replacedRequest = soapHelper.readRequestReplaceExcelValues("ReserveINVLAN_Negative_Test");
		System.out.println("Replaced Request: "+replacedRequest);
	}

	private void sendRequestAndReplace() throws SOAPException, SAXException, IOException, ParserConfigurationException {
		
		SOAPHelper soapHelper = new SOAPHelper("ReserveINVLAN.xml");
		
		String xmlRequestBody = soapHelper.readRequestBody();
		System.out.println("xmlRequestBody1 = " + xmlRequestBody);

		String response = soapHelper.sendMessageToServer(xmlRequestBody);
		System.out.println("Response: " + response);
		
		String expressionVlanName = "Body/queryResponse/namedQueryResponse/result/response/vlan/cVlanName";
		
		XMLDocument doc = new XMLDocument();
		doc.parse(response);
		String vlanName = doc.get(expressionVlanName).getString();
		System.out.println("XML: "+doc.getXML());
		System.out.println("VLAN Expression: "+expressionVlanName+", Name: "+vlanName);
		
		// To replace the value of an attribute { Example : Replace Ethernetnetwork with ID }
		soapHelper.setXmlName("ReserveINVLAN2.xml");
		String xmlRequestBody2 = soapHelper.readRequestBody();
		System.out.println("xmlRequestBody2 = " + xmlRequestBody2);
		xmlRequestBody2=xmlRequestBody2.replaceAll("\\$param1", vlanName);
		System.out.println("xmlRequestBody2 (Replaced) = " + xmlRequestBody2);
	}
	
}
