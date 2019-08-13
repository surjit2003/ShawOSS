package com.netcracker.shaw.report;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.netcracker.shaw.at_shaw_sd.util.Constants;

public class ExecutionReport {

	private static final String executionReportTemplateFile = Constants.EXECUTION_REPORT_PATH;

	public void generateExecutionReport(int totalCount, int passCount, int failCount, int skipCount,Date startDate,Date endDate) { 
		System.out.println("Entering execution report method:");
		File theDir = new File(String.valueOf(Constants.REPORT_PATH)); 
		try {
			if (!theDir.exists()) { 
				System.out.println("Directory " + Constants.REPORT_PATH + " does not exist");
				boolean dirExist = theDir.mkdir();
				if (dirExist) {
					JOptionPane.showMessageDialog(null, "New Folder created!");
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		try {
			
		String customReportTemplateStr = this.readEmailabelReportTemplate();
		String customReportTitle = this.getCustomReportTitle("Custom TestNG Report");
		String customSuiteSummary= this.getExecutionSummary(totalCount,passCount,failCount,skipCount,startDate,endDate);
	
		customReportTemplateStr = customReportTemplateStr.replaceAll("\\$TestNG_Custom_Report_Title\\$",customReportTitle);
		customReportTemplateStr = customReportTemplateStr.replaceAll("\\$Test_Case_Summary\\$", customSuiteSummary);

			Date d = new Date();
			String fileName = d.toString().replace(":", "_").replace(" ", "_")+ "execution-report.html";
			File targetFile = new File(theDir + "/"+fileName);
			FileWriter fw = new FileWriter(targetFile);
			fw.write(customReportTemplateStr);
			fw.flush();
			fw.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("finally")
	private String getExecutionSummary(int totalCount, int passCount, int failCount, int skipCount, Date startDate,
			Date endDate) {
		StringBuffer retBuf = new StringBuffer();
		System.out.println("End time is :" + endDate);
		try {
			retBuf.append("<tr>");
			// Test name.
			retBuf.append("<td>");
			retBuf.append("Regression Test");
			retBuf.append("</td>");

			// Total method count.
			retBuf.append("<td bgcolor=lightblue>");
			// retBuf.append(" ");
			retBuf.append(totalCount);
			retBuf.append("</td>");

			// Passed method count.
			retBuf.append("<td bgcolor=lightgreen>");
			retBuf.append(passCount);
			retBuf.append("</td>");

			// Skipped method count.
			retBuf.append("<td bgcolor=yellow>");
			retBuf.append(skipCount);
			retBuf.append("</td>");

			// Failed method count.
			retBuf.append("<td bgcolor=red>");
			retBuf.append(failCount);
			retBuf.append("</td>");

			// Get browser type.
			String browserType = "Chrome";

			// Append browser type.
			retBuf.append("<td>");
			retBuf.append(browserType);
			retBuf.append("</td>");

			// Start Date
			retBuf.append("<td>");
			retBuf.append(this.getDateInStringFormat(startDate));
			retBuf.append("</td>");

			// End Date
			retBuf.append("<td>");
			retBuf.append(this.getDateInStringFormat(endDate));
			retBuf.append("</td>");

			// Execute Time
			long deltaTime = endDate.getTime() - startDate.getTime();
			String deltaTimeStr = this.convertDeltaTimeToString(deltaTime);
			retBuf.append("<td>");
			retBuf.append(deltaTimeStr);
			retBuf.append("</td>");
			retBuf.append("</tr>");
			// System.out.println("Time taken is:"+deltaTimeStr);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return retBuf.toString();
		}
	}
	
	/*
	 * @SuppressWarnings({ "unused", "finally" }) private String
	 * getTestMehodSummary(List<ISuite> suites) { StringBuffer retBuf = new
	 * StringBuffer();
	 * 
	 * try { for (ISuite tempSuite : suites) {
	 * retBuf.append("<tr><td colspan=7><center><b>" + tempSuite.getName() +
	 * "</b></center></td></tr>");
	 * 
	 * Map<String, ISuiteResult> testResults = tempSuite.getResults();
	 * 
	 * for (ISuiteResult result : testResults.values()) {
	 * 
	 * ITestContext testObj = result.getTestContext();
	 * 
	 * String testName = testObj.getName();
	 * 
	 * // Get failed test method related data. IResultMap testFailedResult =
	 * testObj.getFailedTests(); String failedTestMethodInfo =
	 * this.getTestMethodReport(testName, testFailedResult, false, false);
	 * retBuf.append(failedTestMethodInfo);
	 * 
	 * // Get skipped test method related data. IResultMap testSkippedResult =
	 * testObj.getSkippedTests(); String skippedTestMethodInfo =
	 * this.getTestMethodReport(testName, testSkippedResult, false, true);
	 * retBuf.append(skippedTestMethodInfo);
	 * 
	 * // Get passed test method related data. IResultMap testPassedResult =
	 * testObj.getPassedTests(); String passedTestMethodInfo =
	 * this.getTestMethodReport(testName, testPassedResult, true, false);
	 * retBuf.append(passedTestMethodInfo); } } } catch (Exception ex) {
	 * ex.printStackTrace(); } finally { return retBuf.toString(); } }
	 */
	/*
	 * private String getTestMethodReport(String testName, IResultMap testResultMap,
	 * boolean passedReault, boolean skippedResult) { StringBuffer retStrBuf = new
	 * StringBuffer();
	 * 
	 * String resultTitle = testName;
	 * 
	 * String color = "lightgreen";
	 * 
	 * if (skippedResult) { resultTitle += " - Skipped "; color = "yellow"; } else {
	 * if (!passedReault) { resultTitle += " - Failed "; color = "red"; } else {
	 * resultTitle += " - Passed "; color = "lightgreen"; } } retStrBuf.append(
	 * "<tr bgcolor=" + color + "><td colspan=11><center><b>" + resultTitle +
	 * "</b></center></td></tr>"); Set<ITestResult> testResultSet =
	 * testResultMap.getAllResults(); for (ITestResult testResult : testResultSet) {
	 * String testClassName = ""; String testMethodName = ""; String startDateStr =
	 * ""; String executeTimeStr = ""; // String paramStr = ""; String
	 * reporterMessage = ""; String exceptionMessage = "";
	 * 
	 * // Get testClassName testClassName = testResult.getTestClass().getName();
	 * 
	 * // Get testMethodName testMethodName =
	 * testResult.getMethod().getMethodName();
	 * 
	 * // Get startDateStr long startTimeMillis = testResult.getStartMillis();
	 * startDateStr = this.getDateInStringFormat(new Date(startTimeMillis));
	 * 
	 * // Get Execute time. long deltaMillis = testResult.getEndMillis() -
	 * testResult.getStartMillis(); executeTimeStr =
	 * this.convertDeltaTimeToString(deltaMillis);
	 * 
	 * retStrBuf.append("<tr bgcolor=" + color + ">");
	 * 
	 * // Add test class name. retStrBuf.append("<td>");
	 * retStrBuf.append(testClassName); retStrBuf.append("</td>");
	 * 
	 * // Add test method name. retStrBuf.append("<td>");
	 * retStrBuf.append(testMethodName); retStrBuf.append("</td>");
	 * 
	 * // Add start time. retStrBuf.append("<td>"); retStrBuf.append(startDateStr);
	 * retStrBuf.append("</td>");
	 * 
	 * // Add execution time. retStrBuf.append("<td>");
	 * retStrBuf.append(executeTimeStr); retStrBuf.append("</td>");
	 * retStrBuf.append("</tr>"); } return retStrBuf.toString(); }
	 */
	private String getCustomReportTitle(String title) {
		StringBuffer retBuf = new StringBuffer();
		retBuf.append(title + " " + this.getDateInStringFormat(new Date()));
		return retBuf.toString();
	}

	private String getDateInStringFormat(Date date) {
		StringBuffer retBuf = new StringBuffer();
		if (date == null) {
			date = new Date();
		}
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		retBuf.append(df.format(date));
		return retBuf.toString();
	}

	private String convertDeltaTimeToString(long deltaTime) {
		StringBuffer retBuf = new StringBuffer();

		long milli = deltaTime;

		long seconds = deltaTime / 1000 % 60;

		long minutes = deltaTime / (60 * 1000) % 60;

		long hours = deltaTime / (60 * 60 * 1000) % 24;

		retBuf.append(hours + ":" + minutes + ":" + seconds);

		return retBuf.toString();
	}

	@SuppressWarnings("finally")
	private String readEmailabelReportTemplate() {
		StringBuffer retBuf = new StringBuffer();

		try {

			@SuppressWarnings("static-access")
			File file = new File(this.executionReportTemplateFile);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String line = br.readLine();
			while (line != null) {
				retBuf.append(line);
				line = br.readLine();
			}

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} finally {
			return retBuf.toString();
		}
	}
}
