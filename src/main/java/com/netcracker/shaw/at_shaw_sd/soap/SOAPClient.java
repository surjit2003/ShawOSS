package com.netcracker.shaw.at_shaw_sd.soap;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * This is an example of a simple SOAP Client class to send request body to a
 * SOAP Server.
 * 
 * Useful when you want to test a SOAP server and you don't want to generate all
 * SOAP client class from the WSDL.
 * 
 */
public class SOAPClient {

	// Default logger
	private static final Logger LOG = Logger.getLogger(SOAPClient.class);

	// The SOAP server URI
	private String uriSOAPServer;
	// The SOAP connection
	private SOAPConnection soapConnection = null;

	// If you want to add namespace to the header, follow this constant
	private static final String PREFIX_NAMESPACE = "ns";
	private static final String NAMESPACE = "http://other.namespace.to.add.to.header";

	/**
	 * A constructor who create a SOAP connection
	 * 
	 * @param url
	 *            the SOAP server URI
	 */
	public SOAPClient(final String url) {
		this.uriSOAPServer = url;

		try {
			createSOAPConnection();
		} catch (UnsupportedOperationException | SOAPException e) {
			LOG.error(e);
		}
	}

	/**
	 * Send a SOAP request for a specific operation
	 * 
	 * @param xmlRequestBody
	 *            the body of the SOAP message
	 * @param operation
	 *            the operation from the SOAP server invoked
	 * @return a response from the server
	 * @throws SOAPException
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public String sendMessageToSOAPServer(String xmlRequestHeader, String xmlRequestBody,
			String operation) throws SOAPException, SAXException, IOException,
			ParserConfigurationException {

		// Send SOAP Message to SOAP Server
		final SOAPElement stringToSOAPElement = stringToSOAPElement(xmlRequestBody);
		final SOAPElement stringToSOAPHeaderElement = stringToSOAPElement(xmlRequestHeader);
		final SOAPMessage soapResponse = soapConnection.call(
				createSOAPRequest(stringToSOAPHeaderElement, stringToSOAPElement, operation),
				uriSOAPServer);

		// Print SOAP Response
		LOG.info(soapMessageToString(soapResponse));
		return soapMessageToString(soapResponse);
	}
	
	public String sendMessageToSOAPServerReplaceHeader(String xmlRequestHeader, String xmlRequestEnvelope,
			String operation) throws SOAPException, SAXException, IOException,
			ParserConfigurationException {

		xmlRequestEnvelope = xmlRequestEnvelope.replaceAll("<soapenv:Header>\\s*</soapenv:Header>", 
				"<soapenv:Header>"+xmlRequestHeader+"</soapenv:Header>");
		
		SOAPMessage soapMessage = getSoapMessageFromString(xmlRequestEnvelope);
		
		final SOAPMessage soapResponse = soapConnection.call(soapMessage, uriSOAPServer);

		// Print SOAP Response
		LOG.info(soapMessageToString(soapResponse));
		System.out.println("MY RESPONSE: "+soapMessageToString(soapResponse));
		return soapMessageToString(soapResponse);
	}
	
	private SOAPMessage getSoapMessageFromString(String xml) throws SOAPException, IOException {
	    MessageFactory factory = MessageFactory.newInstance();
	    SOAPMessage message = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8"))));
	    return message;
	}

	/**
	 * Create a SOAP connection
	 * 
	 * @throws UnsupportedOperationException
	 * @throws SOAPException
	 */
	private void createSOAPConnection() throws UnsupportedOperationException,
			SOAPException {

		// Create SOAP Connection
		SOAPConnectionFactory soapConnectionFactory;
		soapConnectionFactory = SOAPConnectionFactory.newInstance();
		soapConnection = soapConnectionFactory.createConnection();
	}

	/**
	 * Create a SOAP request
	 * 
	 * @param body
	 *            the body of the SOAP message
	 * @param stringToSOAPElement 
	 * @param operation
	 *            the operation from the SOAP server invoked
	 * @return the SOAP message request completed
	 * @throws SOAPException
	 */
	private SOAPMessage createSOAPRequest(SOAPElement header, SOAPElement body, String operation)
			throws SOAPException {

		final MessageFactory messageFactory = MessageFactory.newInstance();
		final SOAPMessage soapMessage = messageFactory.createMessage();
		final SOAPPart soapPart = soapMessage.getSOAPPart();

		// SOAP Envelope
		final SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration(PREFIX_NAMESPACE, NAMESPACE);

		// SOAP HEADER
		SOAPHeader soapHeader = envelope.getHeader();
		soapHeader.addChildElement(header);
		/*SOAPHeaderElement headerElement = soapHeader
				.addHeaderElement(envelope.createName("SecurityToken", "ns1","http://www.bea.com/wsdl/wlcp/wlng/session_manager/service"));
		QName qn1 = new QName("username");
		SOAPElement quotation1 = headerElement.addChildElement(qn1);
		quotation1.addTextNode("sysadm");
		QName qn2 = new QName("password");
		SOAPElement quotation2 = headerElement.addChildElement(qn2);
		quotation2.addTextNode("netcracker");*/
		
		// SOAP Body
		final SOAPBody soapBody = envelope.getBody();
		soapBody.addChildElement(body);

		// Mime Headers
		final MimeHeaders headers = soapMessage.getMimeHeaders();
		LOG.info("SOAPAction : " + uriSOAPServer + operation);
		headers.addHeader("SOAPAction", uriSOAPServer + operation);

		soapMessage.saveChanges();

		/* Print the request message */
		LOG.info(soapMessageToString(soapMessage));
		return soapMessage;
	}

	private String soapMessageToString(SOAPMessage soapMessage) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		String message = "XXX";
		try {
			soapMessage.writeTo(stream);
			message = new String(stream.toByteArray(), "utf-8");
		} catch (SOAPException | IOException e) {
			e.printStackTrace();
		}
		return message;
	}

	/**
	 * Transform a String to a SOAP element
	 * 
	 * @param xmlRequestBody
	 *            the string body representation
	 * @return a SOAP element
	 * @throws SOAPException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	private SOAPElement stringToSOAPElement(String xmlRequestBody)
			throws SOAPException, SAXException, IOException,
			ParserConfigurationException {

		// Load the XML text into a DOM Document
		final DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		builderFactory.setNamespaceAware(true);
		final InputStream stream = new ByteArrayInputStream(
				xmlRequestBody.getBytes());
		final Document doc = builderFactory.newDocumentBuilder().parse(stream);

		// Use SAAJ to convert Document to SOAPElement
		// Create SoapMessage
		final MessageFactory msgFactory = MessageFactory.newInstance();
		final SOAPMessage message = msgFactory.createMessage();
		final SOAPBody soapBody = message.getSOAPBody();

		// This returns the SOAPBodyElement that contains ONLY the Payload
		return soapBody.addDocument(doc);
	}
}
