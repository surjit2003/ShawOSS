package com.netcracker.shaw.at_shaw_sd.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.netcracker.shaw.at_shaw_sd.util.Utility;

public class JDBCValidator {
	TableHtmlFormatter htmlFormatter = new TableHtmlFormatter();
	Utility util = new Utility();
	SQLQuery query = new SQLQuery();
	
	public static void main(String[] args) throws Exception {
		JDBCValidator validator = new JDBCValidator();
		
	}
	
	public boolean executeDeleteScript() throws Exception {
		Connection con = util.getConnection();
		boolean deleteScriptResult = false;
		CallableStatement cstmt = null;
		try {
			// Executing Delete Script procedure
			cstmt = con.prepareCall(query.getDeleteScript());
			deleteScriptResult = cstmt.execute();
		}
		catch (SQLException e) {
		   e.printStackTrace();
		   throw e;
		}
		finally {
		   if(cstmt != null) {
			   cstmt.close();
		   }
		}
		
		return deleteScriptResult;
	}
	
	public boolean executeDeleteScriptIPV4() throws Exception {
		Connection con = util.getConnection();
		boolean deleteScriptResult = false;
		CallableStatement cstmt = null;
		try {
			// Executing Delete Script procedure
			cstmt = con.prepareCall(query.getDeleteScriptIPV4());
			deleteScriptResult = cstmt.execute();
		}
		catch (SQLException e) {
		   e.printStackTrace();
		   throw e;
		}
		finally {
		   if(cstmt != null) {
			   cstmt.close();
		   }
		}
		
		return deleteScriptResult;
	}
	
	public boolean executeDeleteScriptFDB() throws Exception {
		Connection con = util.getConnection();
		boolean deleteScriptResult = false;
		CallableStatement cstmt = null;
		try {
			// Executing Delete Script procedure
			cstmt = con.prepareCall(query.getDeleteScriptFDB());
			deleteScriptResult = cstmt.execute();
		}
		catch (SQLException e) {
		   e.printStackTrace();
		   throw e;
		}
		finally {
		   if(cstmt != null) {
			   cstmt.close();
		   }
		}
		
		return deleteScriptResult;
	}
	
	public String printSDBCount(String deviceName) throws Exception {
		Connection con = util.getConnection();
		PreparedStatement ps = con.prepareStatement(query.getSDBQuery(deviceName));
		ResultSet rs = ps.executeQuery();
		String formattedHtml = htmlFormatter.formatToHTML(rs);
		return formattedHtml;
	}
	
	public String printIDBCount(String deviceName) throws Exception {
		Connection con = util.getConnection();
		PreparedStatement ps = con.prepareStatement(query.getIDBQuery(deviceName));
		ResultSet rs = ps.executeQuery();
		String formattedHtml = htmlFormatter.formatToHTML(rs);
		return formattedHtml;
	}
	
	
	public Map<String, String>  getSDBTableCount(String deviceName) throws Exception {
		Map<String, String> dbTableMap = new HashMap<String, String>();
		Connection con = util.getConnection();
		PreparedStatement ps = con.prepareStatement(query.getSDBQuery(deviceName));
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			dbTableMap.put(rs.getString("table_name"), rs.getString("count"));
		}
		System.out.println("SDB DB Map Size: " + dbTableMap.size());
		return dbTableMap;
		
	}
	
	public Map<String, String>  getIDBTableCount(String deviceName) throws Exception {
		Map<String, String> dbTableMap = new HashMap<String, String>();
		Connection con = util.getConnection();
		PreparedStatement ps = con.prepareStatement(query.getIDBQuery(deviceName));
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			dbTableMap.put(rs.getString("table_name"), rs.getString("count"));
		}
		System.out.println("IDB DB Map Size: " + dbTableMap.size());
		return dbTableMap;
		
	}
	
	
	public String printIDBTables(String isCount, String deviceName) throws Exception {
		Connection con = util.getConnection();
		PreparedStatement ps = null, ps1 = null;
		switch (deviceName) {

		case "IPBB" : 
			if (isCount.equalsIgnoreCase("Yes") )
			{
				ps = con.prepareStatement(query.getIDBTablesData("Yes", "IDB_IPBB"));
			}
			else
			{
				ps = con.prepareStatement(query.getIDBTablesData("No", "IDB_IPBB"));
			}
			break;

		case "Alcatel" : 
			if (isCount.equalsIgnoreCase("Yes") )
			{
				ps = con.prepareStatement(query.getIDBTablesData("Yes", "IDB_5620"));
			}
			else
			{
				ps = con.prepareStatement(query.getIDBTablesData("No", "IDB_5620"));
			}
			break;
		case "CMTS" : 
			if (isCount.equalsIgnoreCase("Yes") )
			{
				ps = con.prepareStatement(query.getIDBTablesData("Yes", "IDB_CMTS"));
			}
			else
			{
				ps = con.prepareStatement(query.getIDBTablesData("No", "IDB_CMTS"));
			}
			break;

		case "CMTS-OnDemand" : 
			if (isCount.equalsIgnoreCase("Yes") )
			{
				ps = con.prepareStatement(query.getIDBTablesData("Yes", "IDB_CMTS"));
			}
			else
			{
				ps = con.prepareStatement(query.getIDBTablesData("No", "IDB_CMTS"));
			}
			break;


		case "ETX" : 

			if (isCount.equalsIgnoreCase("Yes") )
			{
				ps = con.prepareStatement(query.getIDBTablesData("Yes", "IDB_ETX"));
			}
			else
			{
				ps = con.prepareStatement(query.getIDBTablesData("No", "IDB_ETX"));
			}
			break;

		case "Spectrum" : 
			if (isCount.equalsIgnoreCase("Yes") )
			{
				ps = con.prepareStatement(query.getIDBTablesData("Yes", "IDB_SPEC"));
			}
			else
			{
				ps = con.prepareStatement(query.getIDBTablesData("No", "IDB_SPEC"));
			}
			break;

		case "FDB" : 

			if (isCount.equalsIgnoreCase("Yes") )
			{
				ps = con.prepareStatement(query.getIDBTablesData("Yes", "IDB_FDB"));
			}
			else
			{
				ps = con.prepareStatement(query.getIDBTablesData("No", "IDB_FDB"));
			}
			break;

		case "CIN" : 

			if (isCount.equalsIgnoreCase("Yes") )
			{
				ps = con.prepareStatement(query.getIDBTablesData("Yes", "IDB_CIN"));
			}
			else
			{
				ps = con.prepareStatement(query.getIDBTablesData("No", "IDB_CIN"));
			}
			break;

		case "IPV4" : 

			if (isCount.equalsIgnoreCase("Yes") )
			{
				ps = con.prepareStatement(query.getIDBTablesData("Yes", "IDB_PUBLIC_IP"));
				ps1 = con.prepareStatement(query.getIDBTablesData("Yes", "IDB_PUBLIC_IP"));
			}
			else
			{
				ps = con.prepareStatement(query.getIDBTablesData("No", "IDB_PUBLIC_IP"));
				ps1 = con.prepareStatement(query.getIDBTablesData("Yes", "IDB_PUBLIC_IP"));

			}
			break;

		}				
		ResultSet rs = ps.executeQuery();
		String formattedHtml = htmlFormatter.formatToHTML(rs);
		return formattedHtml;
	}
	
	public String printSDBTables(String isCount, String deviceName) throws Exception {
		Connection con = util.getConnection();
		PreparedStatement ps = null;
		
		switch (deviceName) {

		case "IPBB" : 
			if (isCount.equalsIgnoreCase("Yes") )
			{
				ps = con.prepareStatement(query.getSDBTablesData("Yes", "SDB_IPBB"));
			}
			else
			{
				ps = con.prepareStatement(query.getSDBTablesData("No", "SDB_IPBB"));
			}
			break;

		case "Alcatel" : 
			if (isCount.equalsIgnoreCase("Yes") )
			{
				ps = con.prepareStatement(query.getSDBTablesData("Yes", "SDB_5620"));
			}
			else
			{
				ps = con.prepareStatement(query.getSDBTablesData("No", "SDB_5620"));
			}
			break;

		case "CMTS" : 
			if (isCount.equalsIgnoreCase("Yes") )
			{
				ps = con.prepareStatement(query.getSDBTablesData("Yes", "SDB_CLI_CMTS"));
			}
			else
			{
				ps = con.prepareStatement(query.getSDBTablesData("No", "SDB_CLI_CMTS"));
			}
			break;

		case "CMTS-OnDemand" :

			if (isCount.equalsIgnoreCase("Yes") )
			{
				ps = con.prepareStatement(query.getSDBTablesData("Yes", "SDB_CLI_CMTS"));
			}
			else
			{
				ps = con.prepareStatement(query.getSDBTablesData("No", "SDB_CLI_CMTS"));
			}
			break;


		case "ETX" : 

			if (isCount.equalsIgnoreCase("Yes") )
			{
				ps = con.prepareStatement(query.getSDBTablesData("Yes", "SDB_ETX"));
			}
			else
			{
				ps = con.prepareStatement(query.getSDBTablesData("No", "SDB_ETX"));
			}
			break;


		case "Spectrum" : 
			if (isCount.equalsIgnoreCase("Yes") )
			{
				ps = con.prepareStatement(query.getSDBTablesData("Yes", "SDB_SPEC"));
			}
			else
			{
				ps = con.prepareStatement(query.getSDBTablesData("No", "SDB_SPEC"));
			}
			break;

		case "FDB" : 

			if (isCount.equalsIgnoreCase("Yes") )
			{
				ps = con.prepareStatement(query.getSDBTablesData("Yes", "SDB_FDB"));
			}
			else
			{
				ps = con.prepareStatement(query.getSDBTablesData("No", "SDB_FDB"));
			}
			break;

		case "CIN" : 

			if (isCount.equalsIgnoreCase("Yes") )
			{
				ps = con.prepareStatement(query.getSDBTablesData("Yes", "SDB_CIN"));
			}
			else
			{
				ps = con.prepareStatement(query.getSDBTablesData("No", "SDB_CIN"));
			}
			break;

		case "IPV4" : 

			if (isCount.equalsIgnoreCase("Yes") )
			{
				ps = con.prepareStatement(query.getSDBTablesData("Yes", "SDB_IP"));
			}
			else
			{
				ps = con.prepareStatement(query.getSDBTablesData("No", "SDB_IP"));
			}
			break;


		}
		
		ResultSet rs = ps.executeQuery();
		String formattedHtml = htmlFormatter.formatToHTML(rs);
		return formattedHtml;
	}
	
	
	public void runDeleteQuery() throws Exception {
		Connection con = util.getConnection();
		PreparedStatement ps = con.prepareStatement(query.deleteQuery());
		ResultSet rs = ps.executeQuery();
		//return rs;
		//String formattedHtml = htmlFormatter.formatToHTML(rs);
		//return formattedHtml;
	}
	
	
	public String getObject(String docName, String detailMode) throws Exception {
		System.out.println("docName : " + docName);
		String ObjectId = null;
		Connection con = util.getConnection();
		PreparedStatement ps = con.prepareStatement(query.getObjectQuery());
		ps.setString(1, docName);
		ResultSet rs = ps.executeQuery();
		
		if ( detailMode.equalsIgnoreCase("Query"))
		{
			String formattedHtml = htmlFormatter.formatToHTML(rs);
			return formattedHtml;
		}
		else
		{
			while (rs.next()) {
				ObjectId = rs.getString("OBJECT_ID");
			}
			return ObjectId;		
		}
	}
	
	public String getActions(String queryStr) throws Exception {
		System.out.println("Entered getActions with query : " + queryStr);
		Connection con = util.getConnection();
		PreparedStatement ps = con.prepareStatement(query.getActionsQuery(queryStr));
		ResultSet rs = ps.executeQuery();
		String formattedHtml = htmlFormatter.formatToHTML(rs);
		return formattedHtml;
	}
	
	public String printReconQueryDetails(String objectId) throws Exception {
		System.out.println("Entered printReconQueryDetails with objectId : " + objectId);
		Connection con = util.getConnection();
		PreparedStatement ps = con.prepareStatement(query.runReconQuery(objectId));
		ResultSet rs = ps.executeQuery();
		String formattedHtml = htmlFormatter.formatToHTML(rs);
		return formattedHtml;
	}

	
	//printReconQueryDetails
	
	public List<String> getReconQueryDetails(String objectId) throws Exception {
		System.out.println("Entered getReconQueryDetails with Object id : " + objectId);
		Connection con = util.getConnection();
		PreparedStatement ps = con.prepareStatement(query.runReconQuery(objectId));
		ResultSet rs = ps.executeQuery();

		String actionDetails = null;
		List<String> actionValues=new ArrayList<String>();
		while (rs.next()) {
			actionDetails = rs.getString("ACTIONS");

			actionValues.add(actionDetails);
		}
		System.out.println("Num of rows returned for the recon query :  " + actionValues.size());
		return actionValues;
	}
	
	
	public String getFalloutTable(String poolId , String integSessionId) throws Exception {
		System.out.println("poolId : " + poolId);
		System.out.println("integSessionId : " + integSessionId);
		Connection con = util.getConnection();
		PreparedStatement ps = con.prepareStatement(query.falloutTableQuery());
		ps.setString(1, poolId);
		ps.setString(2, integSessionId);
		ResultSet rs = ps.executeQuery();
		String fallOutTable = null;
		while (rs.next()) {
			fallOutTable = rs.getString("VALUE");
		}
		System.out.println("fallOutTable Value Outside: " + fallOutTable);
		return fallOutTable;
	}
	
	public String printFallOuts(String fallOutTable) throws Exception {
		System.out.println("fallOutTable : " + fallOutTable);
		Connection con = util.getConnection();
		PreparedStatement ps = con.prepareStatement(query.fallOutsQuery(fallOutTable));
		ResultSet rs = ps.executeQuery();
		String formattedHtml = htmlFormatter.formatToHTML(rs);
		return formattedHtml;
	}
	
	public Map<String, String> getFallOutCounts(String fallOutTable) throws Exception {
		System.out.println("fallOutTable : " + fallOutTable);
		Map<String, String> dbTableMap = new HashMap<String, String>();
		Connection con = util.getConnection();
		PreparedStatement ps = con.prepareStatement(query.fallOutsQuery(fallOutTable));
		//ps.setString(1, fallOutTable);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			dbTableMap.put(rs.getString("table_name"), rs.getString("count"));
		}
		System.out.println("DB Table Count: " + dbTableMap.size());
		return dbTableMap;
	}
	
	
	public String  printVlanDBDetails(String poolId) throws Exception {
		Connection con = util.getConnection();
		PreparedStatement ps = null;
		ps = con.prepareStatement(query.getVlanDataQuery(poolId));
		ResultSet rs = ps.executeQuery();
		String formattedHtml = htmlFormatter.formatToHTML(rs);
		return formattedHtml;
		
	}
	
	public Map<String, String>  getVLANAcctDetails(String poolId) throws Exception {
		Map<String, String> dbTableMap = new HashMap<String, String>();
		Connection con = util.getConnection();
		PreparedStatement ps = con.prepareStatement(query.getVlanDataQuery(poolId));
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			dbTableMap.put(rs.getString("VLAN"), rs.getString("ACCOUNT_NUMBER"));
		}
		System.out.println("VLAN DB Map Size: " + dbTableMap.size());
		return dbTableMap;
		
	}

	public Map<String, String> getVLANStatusDetails(String poolId) throws Exception {
		Map<String, String> dbTableMap = new HashMap<String, String>();
		Connection con = util.getConnection();
		PreparedStatement ps = con.prepareStatement(query.getVlanDataQuery(poolId));
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			dbTableMap.put(rs.getString("VLAN"), rs.getString("STATUS"));
		}
		System.out.println("VLAN DB Map Size: " + dbTableMap.size());
		return dbTableMap;
		
	}

	public String getMinDate(String poolOption) throws Exception {
		String maxDate = null;
		Connection con = util.getConnection();
		PreparedStatement ps = null;
		ps = con.prepareStatement(query.getVlanMinDateQuery(poolOption));
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			maxDate =  rs.getString("CREATED_WHEN");
		}
		return maxDate;
	}

	public int getVlanPoolCount(String poolOption) throws Exception {
		int dbCount = -1;
		Connection con = util.getConnection();
		PreparedStatement ps = null;
		ps = con.prepareStatement(query.getVlanPoolCountQuery(poolOption));
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			dbCount =  rs.getInt("COUNT");
		}
		return dbCount;
	}

	public String verifyObjectActions(String isCount, String deviceName, String objectID) throws Exception {
		String actionCount = null;
		String objectName = null;
		Connection con = util.getConnection();
		PreparedStatement ps = null;
		ps = con.prepareStatement(query.getObjectActionsQuery(isCount, deviceName));
		ps.setString(1, objectID);
		ResultSet rs = ps.executeQuery();
		
		if ( isCount.equalsIgnoreCase("Yes")) {
			while(rs.next()) {
				actionCount =  rs.getString("ACTION");
			}
			return actionCount;
		}else
		{
			while(rs.next()) {
				objectName =  rs.getString("NAME");
			}
			return objectName;
		}
		
		
	}
	
	public int getActionsFromTable(String objectId, String tableName) throws Exception {
		int actionCount = -1;
		Connection con = util.getConnection();
		PreparedStatement ps = null;
		ps = con.prepareStatement(query.getActionsWithTableQuery(tableName));
		//ps.setString(1, SessionId);
		ps.setString(1, objectId);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			actionCount =  rs.getInt("ACTION");
		}
		return actionCount;
	}
	
	public int getActionsFromObjName(String tableName, String objectName, String deviceName) throws Exception {
		int actionCount = -1;
		Connection con = util.getConnection();
		PreparedStatement ps = null;
		ps = con.prepareStatement(query.getActionsWithObjName(tableName, deviceName, objectName));
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			actionCount =  rs.getInt("ACTION");
		}
		return actionCount;
	}
	
	
	public String getObjNameFromTable(String objectId, String tableName) throws Exception {
		String objectName = null;
		Connection con = util.getConnection();
		PreparedStatement ps = null;
		ps = con.prepareStatement(query.getNameWithTableQuery(tableName));
		ps.setString(1, objectId);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			objectName =  rs.getString("NAME");
		}
		return objectName;
	}
	
	public void executeDeleteScriptOnObject(String ObjectId) throws Exception {
		Connection con = util.getConnection();
		PreparedStatement ps = null;
			ps = con.prepareStatement(query.getObjectDeleteQuery());
			ps.setString(1, ObjectId);
			ps.executeUpdate();
		
	}

	public int verifyObjectData(String objectId) throws Exception {
		int rowCount = -1;
		Connection con = util.getConnection();
		PreparedStatement ps = null;
		ps = con.prepareStatement(query.selectObjectQuery());
		ps.setString(1, objectId);
		ResultSet rs = ps.executeQuery();   
		
		while(rs.next()) {
			rowCount =  rs.getInt("COUNT");
		}
		return rowCount;
	}
	
	
	public String getTableName(String sessionId, String deviceName) throws Exception {
		String tableName = null;
		Connection con = util.getConnection();
		PreparedStatement ps = null;
		ps = con.prepareStatement(query.getTableForIntegrations(deviceName));
		ps.setString(1, sessionId);
		ResultSet rs = ps.executeQuery();   
		
		while(rs.next()) {
			tableName =  rs.getString("TAB");
		}
		return tableName;
	}

	public String getObjIdFromDns (String dnsName) throws Exception {
		String objectId = null;
		Connection con = util.getConnection();
		PreparedStatement ps = null;
		ps = con.prepareStatement(query.getObjIdFromDnsQuery(dnsName));
		ResultSet rs = ps.executeQuery();   
		
		while(rs.next()) {
			objectId =  rs.getString("OBJECT_ID");
		}
		return objectId;
	}
	
	public String getObjAndStatus(String paramType,String ipAddress) throws Exception {
		String objectId = null;
		String ipStatus = null;
		Connection con = util.getConnection();
		PreparedStatement ps = null;
		ps = con.prepareStatement(query.getObjAndStatusForIPQuery(paramType));
		ps.setString(1, ipAddress);
		ResultSet rs = ps.executeQuery();
		
		if ( paramType.equalsIgnoreCase("Object")) {
			while(rs.next()) {
				objectId =  rs.getString("OBJECT_ID");
			}
			return objectId;
		}else
		{
			while(rs.next()) {
				ipStatus =  rs.getString("STATUS");
			}
			return ipStatus;
		}
		
		
	}

	public Object getNumofRecordsOnSourceObj(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getSrcObjectIdCount(String tableName, String objectId) throws Exception {
		int rowCount = -1;
		Connection con = util.getConnection();
		PreparedStatement ps = null;
		ps = con.prepareStatement(query.getSrcObjectCountQuery(tableName));
		ps.setString(1, objectId);
		ResultSet rs = ps.executeQuery();   
		
		while(rs.next()) {
			rowCount =  rs.getInt("COUNT");
		}
		return rowCount;
	}
	
	
	
	
}
