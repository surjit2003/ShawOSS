package com.netcracker.shaw.at_shaw_sd.soap;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.netcracker.shaw.at_shaw_sd.util.Constants;
import com.netcracker.shaw.at_shaw_sd.util.DisplayUtility;
import com.netcracker.shaw.at_shaw_sd.util.ExcelOperation;
import com.netcracker.shaw.at_shaw_sd.xml.XMLDocument;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SOAPHelper {
	Logger log = Logger.getLogger(SOAPHelper.class);
	public ExtentTest test;
	public ExtentTest getTest() {
		return test;
	}
	public void setTest(ExtentTest test) {
		this.test = test;
	}
	private String url = Constants.SOAP_DEFAULT_URL;
	private String xmlName;
	private String operation = Constants.SOAP_DEFAULT_OPERATION;
	private String header = Constants.SOAP_DEFAULT_HEADER;
	
	private SOAPClient soapClient;
	
	XMLDocument doc = new XMLDocument();
	
	public SOAPHelper() {
		super();
		soapClient = new SOAPClient(url);
	}
	
	public SOAPHelper(String xmlName) {
		super();
		this.xmlName = xmlName;
		soapClient = new SOAPClient(url);
	}

	public SOAPHelper(String xmlName, String header) {
		super();
		this.xmlName = xmlName;
		this.operation = header;
		soapClient = new SOAPClient(url);
	}
	
	public SOAPHelper(String xmlName, String header, String operation, ExtentTest test) {
		super();
		this.xmlName = xmlName;
		this.header = header;
		this.operation = operation;
		this.test = test;
		soapClient = new SOAPClient(url);
	}
	
	public SOAPHelper(String url, String xmlName, String operation, String header) {
		super();
		this.url = url;
		this.xmlName = xmlName;
		this.operation = operation;
		this.header = header;
		soapClient = new SOAPClient(url);
	}
	
	public static void main(String[] args) {
		new SOAPHelper("ReserveINVLAN2.xml").readRequestReplaceExcelValues("ReserveINVLAN_Negative_Test");
	}
	
	
	/**
	 * @Method : readRequestReplaceExcelValues
	 * @param xmlTestName : The test name provided for the each test in SOAPReqData 
	 * sheet (Ex : ReserveINVLAN_New_Account) 
	 * @return
	 */

	public String readRequestReplaceExcelValues(String xmlTestName) {

		String requestBody = readRequestBody();
		
		Map<String, String> excelParamMap = readExcelParamMap(xmlTestName);
		test.log(LogStatus.INFO, "Excel Parameters to be replaced: " + new DisplayUtility<String, String>().mapToString(excelParamMap));
		System.out.println("Excel Parameters to be replaced: " + new DisplayUtility<String, String>().mapToString(excelParamMap));
		
		String paramValue;
		for (String paramHead : excelParamMap.keySet()) {
			paramValue = excelParamMap.get(paramHead);
			requestBody = requestBody.replaceAll(paramHead, paramValue);
		}
		
		test.log(LogStatus.INFO, "<span style='font-weight:bold;'> Replaced XML Request: " + escapeFormatXml(requestBody));
		System.out.println("Replaced XML Request: " + requestBody);
		return requestBody;
	}
	
	private String escapeFormatXml(String s) {
	    
		String formattedString = s;
	    try {
		    final DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			builderFactory.setNamespaceAware(true);
			final InputStream stream = new ByteArrayInputStream(s.getBytes());
			final Document doc = builderFactory.newDocumentBuilder().parse(stream);
		    
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "html");
			Source source = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			Result output = new StreamResult(writer);
			transformer.transform(source, output);
			
			formattedString = writer.toString();
		} catch (TransformerException | SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	    
	    formattedString = formattedString.replaceAll("(<\\/\\s*\\S*>)","$1<br/>");
	    
	    formattedString = formattedString.replaceAll("&", "&amp;").replaceAll(">", "&gt;").replaceAll("<", "&lt;").replaceAll("\"", "&quot;").replaceAll("'", "&apos;");
	    
	    formattedString = formattedString.replaceAll("&gt;([\\d*\\-*\\w*\\s*:*]*)&lt;","&gt;<span style='font-weight:bold;'>$1</span>&lt;");
	    
	    return "<br/>"+formattedString.replaceAll("&lt;br\\/&gt;", "<br/>");
	}
	
	public Map<String, String> readExcelParamMap(String targetXmlTestName) {
		ExcelOperation xls = new ExcelOperation(Constants.TestDataSuite_XLS);
		String sheetName = Constants.SHEET_SOAP_REQUEST_DATA, excelXmlTestName, paramValue, paramHead;
		Map<String, String> excelParamMap = new HashMap<String, String>();
		
		int rowCounter = 2, columnCounter;
		System.out.println("Target: " + targetXmlTestName);
		while (!(excelXmlTestName = xls.getCellData(sheetName, 0, rowCounter)).equals("")) {
			System.out.println("Retrieved: " + excelXmlTestName);
			if (excelXmlTestName.equalsIgnoreCase(targetXmlTestName)) {
				System.out.println("Matched!");
				
				columnCounter = 1;
				while (!(paramValue = xls.getCellData(sheetName, columnCounter, rowCounter)).equals("")) {
					paramHead = xls.getCellData(sheetName, columnCounter, 1);
					System.out.println(">>>"+paramHead+"::"+paramValue);
					if ( paramValue.equalsIgnoreCase(Constants.EXCEL_NULL_VALUE)) {
						excelParamMap.put(paramHead, "");
					}else {
						excelParamMap.put(paramHead, paramValue);
					}
					columnCounter++;
				}
			}

			rowCounter++;
		}
		return excelParamMap;
	}
	
	public String readRequestBody() {
		StringBuilder requestXml = new StringBuilder();
		System.out.println("Xml Name =  " + xmlName);
		try (BufferedReader br = 
				new BufferedReader(new FileReader(Constants.TEST_DATA_FOLDER + xmlName))) {

			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				//System.out.println(sCurrentLine);
				requestXml.append(sCurrentLine);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		test.log(LogStatus.INFO, "<span style='font-weight:bold;'> Request from " + xmlName + "</span>: " + escapeFormatXml(requestXml.toString()));
		
		return requestXml.toString();
	}
	
	@Deprecated
	public String sendMessageToServer(String requestBody) throws SOAPException, SAXException, IOException, ParserConfigurationException {
		String responseXml = soapClient.sendMessageToSOAPServer(header, requestBody, operation);
		test.log(LogStatus.INFO, "<span style='font-weight:bold;'> Response: " + escapeFormatXml(responseXml));
		return responseXml;
	}
	
	public String sendMessageToServerReplaceHeader(String requestEnvelope) throws SOAPException, SAXException, IOException, ParserConfigurationException {
		String responseXml = soapClient.sendMessageToSOAPServerReplaceHeader(header, requestEnvelope, operation);
		test.log(LogStatus.INFO, "<span style='font-weight:bold;'> Response: " + escapeFormatXml(responseXml));
		return responseXml;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
		// Changing the SOAP Client when there is any change in URL
		soapClient = new SOAPClient(url);
	}
	public String getXmlName() {
		return xmlName;
	}
	public void setXmlName(String xmlName) {
		this.xmlName = xmlName;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	
	public void parseXml(String xml)
	{
		doc.parse(xml);
	}
	
	/**
	 * @method To get the attribute value of a given expression from the Request/Response Xml
	 * @param attributeExpression (Ex: Body/queryResponse/namedQueryResponse/result/response/vlan/cVlanName)
	 * @return Value corresponding to the attribute expression in String format
	 */
	public String getAttributeValue(String attributeExpression) {
		String value = doc.get(attributeExpression).getString();
		System.out.println("VLAN Expression: "+attributeExpression+", Name: "+value);
		//System.out.println("test Value: " + test);
		
		test.log(LogStatus.INFO, "Value returned for : " + attributeExpression + " is : " + value);
		
		return value;
	}
}
