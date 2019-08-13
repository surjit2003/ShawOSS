package com.netcracker.shaw.report;

import java.io.File;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.netcracker.shaw.at_shaw_sd.util.Constants;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReportManager {
	public ExtentReports extent;
	static Logger log = Logger.getLogger(ExtentReportManager.class);
	public ExtentReports getInstance(String testName) {
		File theDir = new File(String.valueOf(Constants.REPORT_PATH)); // Defining Directory/Folder Name
		try {
			if (!theDir.exists()) { // Checks that Directory/Folder Doesn't Exists!
				System.out.println("Directory " + Constants.REPORT_PATH + " does not exist");
				boolean result = theDir.mkdir();
				if (result) {
					JOptionPane.showMessageDialog(null, "New Folder created!");
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		//if(extent == null) {
			Date d = new Date();
			String fileName = d.toString().replace(":", "_").replace(" ", "_") +testName+".html";
			log.debug("File name is :" +fileName);
			String fileName1 =d.toString()+".html";
			extent = new ExtentReports(theDir + "/" + fileName, true, DisplayOrder.OLDEST_FIRST);
            log.debug("Dir name is :"+theDir+" and File Name is: "+fileName);
			extent.loadConfig(new File(Constants.ExtentConfig_PATH));
			// optional
			extent.addSystemInfo("Selenium Version", "3.13.0").addSystemInfo("Environment", "QA");
		//}
		return extent;
	}
}

