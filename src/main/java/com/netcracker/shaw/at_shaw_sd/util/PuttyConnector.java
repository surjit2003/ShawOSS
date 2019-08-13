package com.netcracker.shaw.at_shaw_sd.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import org.testng.Assert;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.netcracker.shaw.at_shaw_sd.jdbc.JDBCValidator;
import com.netcracker.shaw.at_shaw_sd.pageobject.BasePage;
import com.netcracker.shaw.at_shaw_sd.pageobject.DnRFlowPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@SuppressWarnings(value = {"unchecked", "rawtypes"})
public class PuttyConnector{
	
	private ExtentTest test;
	
	private static final Logger log = Logger.getLogger(PuttyConnector.class);
	//private BasePage test =null;
	//Logger log = Logger.getLogger(PuttyConnector.class);
	public static void main(String[] args) {
		//PuttyConnector puttyConnector = new PuttyConnector("devapp735cn.netcracker.com", "netcrk", "n3w_netcrk");
		PuttyConnector puttyConnector = new PuttyConnector("qaapp030cn.netcracker.com", "netcrk", "n3w_netcrk");
		String folderPath = "/home/netcrk/QA/"+Constants.UNIX_FOLDER_DATE_FORMAT.format(new Date());
		boolean folderExists = puttyConnector.checkFolderInBox(folderPath, "No");
		
		folderExists = puttyConnector.checkFileInBox(folderPath+"/test.txt");
		folderExists = puttyConnector.checkFileInBox(folderPath+"/test.zip");
		System.out.println(folderExists);
		
		puttyConnector.closeConnection();
	}
	
	ChannelSftp channelSftp;
	Session session;
	
	public PuttyConnector(String host, String user, String password, ExtentTest test) {
		this(host, user, password);
		this.test = test;
	}
	
	public PuttyConnector(String host, String user, String password) {
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		config.put("PreferredAuthentications", "password");

		JSch.setConfig(config);
		
		JSch jsch = new JSch();
		
		try {
			log.debug("Attempting Connection to " + host + ", with User: " + user);
			session = jsch.getSession(user, host, 22);
			session.setPassword(password);
			session.connect();
			log.debug("Successfully Established Connection to : " + host );
	        		
			Channel channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp)channel;
			//System.out.println("Channel created");
		} catch (JSchException e) {
			e.printStackTrace();
			log.error("Error while Establishing connection to : " + host +  e.getMessage());
			test.log(LogStatus.ERROR, e);
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	public void closeConnection() {
		if(channelSftp != null)
			channelSftp.exit();
		if(session != null)
			session.disconnect();
	}
	
	public boolean checkFolderInBox(String folderPath, String createDir) {
		
		boolean folderExists = false;
		try {
			//System.out.println("Checking for Path :  " + folderPath);
			log.debug("Checking for Path : " + folderPath);
			if(test!=null)
				test.log(LogStatus.INFO, "At Path : " + folderPath);
			SftpATTRS attrs=null;
			try {
			    attrs = channelSftp.stat(folderPath);
			} catch (Exception e) {
			    log.error(folderPath +" not found");
			    log.error("Error in checking path in the session :" +  e.getMessage());
				test.log(LogStatus.ERROR, e);
				Assert.assertTrue(false, e.getMessage());
			}

			if (attrs != null) {
			   // System.out.println("Directory exists IsDir="+attrs.isDir());
			    log.debug( folderPath + " exists in the session");
			    test.log(LogStatus.INFO, folderPath + " exists in the session");
			    if(attrs.isDir()) {
			    	folderExists = true;
			    	String lastModiDate = channelSftp.lstat(folderPath).getMtimeString();
				    //System.out.println("Modified Date of file = " + lastModiDate);
				    test.log(LogStatus.INFO, "Modified Date of " + folderPath + " : " + lastModiDate);
			    }
			} else {
				
				if ( createDir.equalsIgnoreCase("Yes") )
				{
					//System.out.println("Directory doesn't exist, hence creating it");
					log.debug("Directory doesn't exist, hence creating it");
					test.log(LogStatus.INFO, folderPath + " does not exists in the session. Hence Creating it");
					channelSftp.mkdir(folderPath);
				}
				folderExists = true;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			Assert.assertTrue(false, e.getMessage());
		}
		return folderExists;
	}
	
	public boolean checkFileInBox(String filePath) {
		
		boolean fileExists = false;
		try {
			test.log(LogStatus.INFO, "Checking for existance of File: " + filePath);
			SftpATTRS attrs=null;
			try {
			    attrs = channelSftp.lstat(filePath);
			    
			} catch (Exception e) {
			    log.error(filePath +" not found");
			    log.error("Error in checking file in the session :" +  e.getMessage());
				test.log(LogStatus.ERROR, e);
				Assert.assertTrue(false, e.getMessage());
			}
			if(attrs == null) {
				test.log(LogStatus.INFO, " File does not exist ");
			} else {
				test.log(LogStatus.INFO, " File Exists ");
				String lastModiDate = channelSftp.lstat(filePath).getMtimeString();
				test.log(LogStatus.INFO, "Latest Modified Date of File : " + lastModiDate);
				fileExists = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			test.log(LogStatus.ERROR, e);
			Assert.assertTrue(false, e.getMessage());
		}
		return fileExists;
	}

	public Map<String, String> getEnvPropFileMap(String folderPath) throws SftpException {
		//StringBuilder envFileData = new StringBuilder();
		HashMap<String, String> envPropFilemap = new HashMap<String, String>();
		InputStream stream = channelSftp.get(folderPath + "environment.properties");
		try {
		    BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		    // read from br
		    String sCurrentLine, key, value;
		    String[] parts = null;
		    
			while ((sCurrentLine = br.readLine()) != null) {
				parts = sCurrentLine.split("=", 2);
		        if (parts.length >= 2)
		        {
		            key = parts[0];
		            value = parts[1];
		            envPropFilemap.put(key, value);
		        } else {
		            System.out.println("ignoring line: " + sCurrentLine);
		        }
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		    log.error("Error in fetching env properties file :" +  e.getMessage());
			test.log(LogStatus.ERROR, e);
			Assert.assertTrue(false, e.getMessage());
		}
		return envPropFilemap;
	}
	
}