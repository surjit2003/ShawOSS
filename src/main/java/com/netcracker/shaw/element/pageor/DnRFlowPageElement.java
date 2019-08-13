package com.netcracker.shaw.element.pageor;
import static com.netcracker.shaw.factory.Locator.ID;
import static com.netcracker.shaw.factory.Locator.LINK_TEXT;
import static com.netcracker.shaw.factory.Locator.XPATH;
import com.netcracker.shaw.at_shaw_sd.util.Constants;
import org.openqa.selenium.By;
import com.netcracker.shaw.at_shaw_sd.util.Utility;
import com.netcracker.shaw.element.PageElement;
import com.netcracker.shaw.factory.ByType;
import com.netcracker.shaw.factory.Locator;

public enum DnRFlowPageElement implements PageElement {
	
	//SHAW OSS 
	SHAW_PROJECT(XPATH,getPropValue("shawProject")),
	COUNTRY_CANADA(XPATH,getPropValue("countryCanada")),
	STATE_ALBERTA(XPATH,getPropValue("stateAlberta")),
	CITY_CALGARY(XPATH,getPropValue("cityCalagary")),
	BUILDING_ARBOUR_LAKE(XPATH,getPropValue("buildingArbourLake")),
	NETWRK_ELEMENT_CREATED(XPATH,getPropValue("createdNEElement")),
	NAVIGATION_PATH(XPATH,getPropValue("navigationPath")),
	DOCUMENTS_PATH(XPATH,getPropValue("documentsPath")),
	INTEGRATION_INTERFACE(XPATH,getPropValue("integrationInterface")),
	ALCATEL_INTERFACE(XPATH,getPropValue("alcatelInterface")),
	CISCO_INTERFACE(XPATH,getPropValue("ciscoInterface")),
	CMTS_INTERFACE(XPATH,getPropValue("cmtsInterface")),
	SPECTRUM_INTERFACE(XPATH,getPropValue("spectrumInterface")),
	ETX_INTERFACE(XPATH,getPropValue("etxInterface")),
	FDB_INTERFACE(XPATH,getPropValue("fdbInterface")),
	CIN_INTERFACE(XPATH,getPropValue("cinInterface")),
	RECONCILIATION_INTERFACE(XPATH,getPropValue("reconInterface")),
	EDIT(XPATH,getPropValue("edit")),
	RETURN(XPATH,getPropValue("return")),
	FETCH_LOCATION_TXT(ID,getPropValue("fetchLocationTxt")),
    STORAGE_LOCATION_TXT(ID,getPropValue("storageLocationTxt")),
    UPDATE(XPATH,getPropValue("update")),
    DATA_TRANSITION_PATH(XPATH,getPropValue("dataTransition")),
    CONFIGURATION_TAB(XPATH,getPropValue("configurationTab")),
    DATAFLOW_PROJECT(XPATH,getPropValue("dataFlow")),
    SERIAL_NUM_INPUT(XPATH,getPropValue("serialNumInput")),
    INTEG_SESSION_ID(XPATH,getPropValue("integSessionId")),
    IP_NAME_INPUT(XPATH,getPropValue("ipNameInput")),
    SOFTWARE_VERSION_INPUT(XPATH,getPropValue("softwareVerInput")),
    ERROR_REC(XPATH,getPropValue("errorRec")),
    IP_STATUS_SELECT(XPATH,getPropValue("ipStatusSelect")),
    DEV_NAME_INPUT(XPATH,getPropValue("devNameInput")),
    TABLE_ELEMENT(XPATH,getPropValue("tableElement")),
    TABLE_ELEMENT_IPBB(XPATH,getPropValue("tableElementIPBB")),
    ATLEAST_ONE_FALLOUT(XPATH,getPropValue("atleastOneFallOut")),
    TOP_BUTTON(XPATH,getPropValue("topButton")),
    
    ALCATEL_CONFIG(XPATH,getPropValue("alcatelConfig")),
	CISCO_CONFIG(XPATH,getPropValue("ciscoConfig")),
	CMTS_CONFIG(XPATH,getPropValue("cmtsConfig")),
	CMTS_ON_DEMAND_CONFIG(XPATH,getPropValue("cmtsOnDemandConfig")),
	SPECTRUM_CONFIG(XPATH,getPropValue("spectrumConfig")),
	DATA_EXPORT(XPATH,getPropValue("dataExport")),
	RECONCILIATION(XPATH,getPropValue("reconciliation")),
    START_SESSION(XPATH,getPropValue("startSession")),
    SESSION_TERMINATED(XPATH,getPropValue("terminateSession")),
    ERROR_RUN(XPATH,getPropValue("errorMessage")),
    INTEGRATION_FOLDER(XPATH,getPropValue("integFolder")),
    CONFIRM(XPATH,getPropValue("confirmBtn")),
    SQL_INPUT(XPATH,getPropValue("sqlInput")),
    EXECUTE(XPATH,getPropValue("execute")),
    RECON_DOCUMENTS(XPATH,getPropValue("reconDocuments")),
    ACTION_FILTER(XPATH,getPropValue("actionFilter")),
    VALIDATION_SESSION_TAB(XPATH,getPropValue("validateSessionTab")),
    VALIDATION_TAB(XPATH,getPropValue("validationTab")),
    LATEST_VALID_SESSION(XPATH,getPropValue("lastValidSession")),
    CLOSE(XPATH,getPropValue("close")),
    PRIORITY(XPATH,getPropValue("priority")),
    MOVE_ERROR(XPATH,getPropValue("moveError")),
    FOLDER_ERROR(XPATH,getPropValue("folderError")),
    SNMP_NTW_DISCOVERY(XPATH,getPropValue("snmpNtwDiscovery")),
    OXIDIZED_DISC_EXPORT(XPATH,getPropValue("oxidizedDiscExport")),
    SNMP_CLI_RECON(XPATH,getPropValue("snmpCliRecon")),
    VALIDATION_REPORT_TAB(XPATH,getPropValue("validationReportTab")),
    DOWNLOAD_CSV(XPATH,getPropValue("downloadCSV")),
    TOP_TAB(XPATH,getPropValue("topTab")),
    VALIDATE_SESSION_ID(XPATH,getPropValue("validateSession")),
    STATUS_TAB(XPATH,getPropValue("statusTab")),
    FALLOUT_TABLE_CONTENT(XPATH,getPropValue("falloutTableTxt")),
    PARALLEL_RUN_ERROR(XPATH,getPropValue("parallelRunError")),
    
    // New OSS IPBB  Details 
    FTP_LOCATION_IPBB(XPATH,getPropValue("ftpLocIPBB")),
    FETCH_LOCATION_IPBB(XPATH,getPropValue("fetchLocIPBB")),
    STORAGE_LOCATION_IPBB(XPATH,getPropValue("storageLocIPBB")),
    STORAGE_USER_IPBB(XPATH,getPropValue("storageLocUserIPBB")),
    FETCH_USER_IPBB(XPATH,getPropValue("fectchLocUserIPBB")),
    STORAGE_PASS_IPBB(XPATH,getPropValue("storageLocPassIPBB")),
    FETCH_PASS_IPBB(XPATH,getPropValue("fetchLocPassIPBB")),
    STORAGE_PASS2_IPBB(XPATH,getPropValue("storageLocPass2IPBB")),
    FETCH_PASS2_IPBB(XPATH,getPropValue("fetchLocPass2IPBB")),
    SDB_TABLE_IPBB(XPATH,getPropValue("sdbTableIPBB")),
    IDB_TABLE_IPBB(XPATH,getPropValue("idbTableIPBB")),
    GENERATE_SCRIPTS(XPATH,getPropValue("generateScripts")),
    TRANSFORM_SCRIPTS(XPATH,getPropValue("transformScripts")),
    VALIDATE_SCRIPTS(XPATH,getPropValue("validateScripts")),
    PROCEED_BUTTON(XPATH,getPropValue("proceedBtn")),
    PROCEED_BTN_FOOTER(LINK_TEXT,getPropValue("proccedBtnFooter")),
    CISCO_INTEGRATION(XPATH,getPropValue("ciscoIntegrationFolder")),
    GENERATE_SCRIPTS_RECON(XPATH,getPropValue("genScriptsRecon")),
    REPORT_TAB(XPATH,getPropValue("reportTab")),
    UPDATE_DATA(XPATH,getPropValue("updateData")),
    DATA_SESSION_COMPLETE(XPATH,getPropValue("dataSessionComplete")),
    DATA_SESSION_WARNING(XPATH,getPropValue("dataSessionWarning")),
    DATA_SESSION_TERMINATED(XPATH,getPropValue("dataSessionTerminated")),
    IN_PROGRESS(LINK_TEXT,getPropValue("inProgress")),
    TABLE_RECORD(XPATH,getPropValue("sdbTableRecord")),
    IDB_IPBB_TABLE(XPATH,getPropValue("idbIPBBTable")),
    
    //New OSS Spectrum Details
    FETCH_LOCATION_SPEC(XPATH,getPropValue("fetchLocSpec")),
    STORAGE_LOCATION_SPEC(XPATH,getPropValue("storageLocSpec")),
    STORAGE_USER_SPEC(XPATH,getPropValue("storageLocUserSpec")),
    FETCH_USER_SPEC(XPATH,getPropValue("fectchLocUserSpec")),
    STORAGE_PASS_SPEC(XPATH,getPropValue("storageLocPassSpec")),
    FETCH_PASS_SPEC(XPATH,getPropValue("fetchLocPassSpec")),
    STORAGE_PASS2_SPEC(XPATH,getPropValue("storageLocPass2Spec")),
    FETCH_PASS2_SPEC(XPATH,getPropValue("fetchLocPass2Spec")),
    SPECTRUM_INTEGRATION(XPATH,getPropValue("specIntegrationFolder")),
    
    //New OSS CMTS Details 
    PARAMETERS_TAB(XPATH,getPropValue("parametersTab")),
    FETCH_LOCATION_CMTS(XPATH,getPropValue("fetchLocCmts")),
    STORAGE_LOCATION_CMTS(XPATH,getPropValue("storageLocCmts")),
    STORAGE_USER_CMTS(XPATH,getPropValue("storageLocUserCmts")),
    FETCH_USER_CMTS(XPATH,getPropValue("fectchLocUserCmts")),
    STORAGE_PASS_CMTS(XPATH,getPropValue("storageLocPassCmts")),
    FETCH_PASS_CMTS(XPATH,getPropValue("fetchLocPassCmts")),
    STORAGE_PASS2_CMTS(XPATH,getPropValue("storageLocPass2Cmts")),
    FETCH_PASS2_CMTS(XPATH,getPropValue("fetchLocPass2Cmts")),
    CMTS_INTEGRATION(XPATH,getPropValue("cmtsIntegrationFolder")),
    CMTS_ON_DEMAND_INTEGRATION(XPATH,getPropValue("cmtsOnDemandIntegFolder")),
    CMTS_START_DE_RECON(XPATH,getPropValue("cmtsStartDeRecon")),
    SESSIONS_TAB(XPATH,getPropValue("sessionsTab")),

    //CMTS-OnDemand Details
    OPERATIONS(XPATH,getPropValue("operations")),
    DEVICE_SPLIT(XPATH,getPropValue("deviceSplit")),
    ENTER_VALUE(XPATH,getPropValue("enterValue")),
    LAUNCH_BTN(XPATH,getPropValue("launchBtn")),
    
    //New OSS ALCATEL Details 
    FETCH_LOCATION_ALCATEL(XPATH,getPropValue("fetchLocAlcatel")),
    STORAGE_LOCATION_ALCATEL(XPATH,getPropValue("storageLocAlcatel")),
    STORAGE_USER_ALCATEL(XPATH,getPropValue("storageLocUserAlcatel")),
    FETCH_USER_ALCATEL(XPATH,getPropValue("fectchLocUserAlcatel")),
    STORAGE_PASS_ALCATEL(XPATH,getPropValue("storageLocPassAlcatel")),
    FETCH_PASS_ALCATEL(XPATH,getPropValue("fetchLocPassAlcatel")),
    STORAGE_PASS2_ALCATEL(XPATH,getPropValue("storageLocPass2Alcatel")),
    FETCH_PASS2_ALCATEL(XPATH,getPropValue("fetchLocPass2Alcatel")),
    
    //New OSS ETX Details
    FETCH_LOCATION_ETX(XPATH,getPropValue("fetchLocETX")),
    STORAGE_LOCATION_ETX(XPATH,getPropValue("storageLocETX")),
    STORAGE_USER_ETX(XPATH,getPropValue("storageLocUserETX")),
    FETCH_USER_ETX(XPATH,getPropValue("fectchLocUserETX")),
    STORAGE_PASS_ETX(XPATH,getPropValue("storageLocPassETX")),
    FETCH_PASS_ETX(XPATH,getPropValue("fetchLocPassETX")),
    STORAGE_PASS2_ETX(XPATH,getPropValue("storageLocPass2ETX")),
    FETCH_PASS2_ETX(XPATH,getPropValue("fetchLocPass2ETX")),
    ETX_INTEGRATION(XPATH,getPropValue("etxIntegrationFolder")),
    ETX_CONFIG(XPATH,getPropValue("etxConfig")),
    
    //New OSS FDB Details
    FETCH_LOCATION_FDB(XPATH,getPropValue("fetchLocFDB")),
    STORAGE_LOCATION_FDB(XPATH,getPropValue("storageLocFDB")),
    STORAGE_USER_FDB(XPATH,getPropValue("storageLocUserFDB")),
    FETCH_USER_FDB(XPATH,getPropValue("fectchLocUserFDB")),
    STORAGE_PASS_FDB(XPATH,getPropValue("storageLocPassFDB")),
    FETCH_PASS_FDB(XPATH,getPropValue("fetchLocPassFDB")),
    STORAGE_PASS2_FDB(XPATH,getPropValue("storageLocPass2FDB")),
    FETCH_PASS2_FDB(XPATH,getPropValue("fetchLocPass2FDB")),
    FDB_DATA_EXPORT(XPATH,getPropValue("fdbDataExport")),
    FDB_INTEGRATION(XPATH,getPropValue("fdbIntegrationFolder")),
    FDB_RECON(XPATH,getPropValue("fdbRecon")),
    FDB_CONFIG(XPATH,getPropValue("fdbConfig")),
    FDB_CUST_DATA_EXPORT(XPATH,getPropValue("fdbCustDataExport")),
    FDB_INTEG(XPATH,getPropValue("fdbInteg")),
    FDB_CUST_INTEG(XPATH,getPropValue("fdbCustInteg")),
    
    //New OSS Ipv4 Details
    IPV4_DATA_EXPORT(XPATH,getPropValue("ipvDataExport")),
    IPV4_RECON(XPATH,getPropValue("ipvRecon")),
    EXPORT_IPV4_RANGE(XPATH,getPropValue("exportIPVRange")),
    IPV4_CONFIG(XPATH,getPropValue("ipvConfig")),
    
    //New OSS CIN Details
    FETCH_LOCATION_CIN(XPATH,getPropValue("fetchLocCIN")),
    STORAGE_LOCATION_CIN(XPATH,getPropValue("storageLocCIN")),
    STORAGE_USER_CIN(XPATH,getPropValue("storageLocUserCIN")),
    FETCH_USER_CIN(XPATH,getPropValue("fectchLocUserCIN")),
    STORAGE_PASS_CIN(XPATH,getPropValue("storageLocPassCIN")),
    FETCH_PASS_CIN(XPATH,getPropValue("fetchLocPassCIN")),
    STORAGE_PASS2_CIN(XPATH,getPropValue("storageLocPass2CIN")),
    FETCH_PASS2_CIN(XPATH,getPropValue("fetchLocPass2CIN")),
    CIN_CONFIG(XPATH,getPropValue("cinConfig")),
    CIN_INTEGRATION(XPATH,getPropValue("cinIntegFolder")),
    CIN_ON_DEMAND_FOLDER(XPATH,getPropValue("cinOnDemandFolder")),
    CIN_START_DE_RECON(XPATH,getPropValue("cinStartDERecon")),
    DISCOVER_CIN_DEVICES(XPATH,getPropValue("discoverCinDevices")),
 
    
    //New OSS Alcatel Details
    ALCATEL_INTEGRATION(XPATH,getPropValue("alcatelIntegfolder")),
    
    //New OSS Common DataTransition Details
    SDB_TABLE_SET(XPATH,getPropValue("sdbTableSet")),
    IDB_TABLE_SET(XPATH,getPropValue("idbTableSet")),
    SDB_SNMP_TABLE_SET(XPATH,getPropValue("sdbSnmpTableSet")),
	SDB_FDB_TABLE(XPATH,getPropValue("sdbFdbTableSet")),
	SDB_SNMP_CLI_TABLE(XPATH,getPropValue("sdbSnmpCliTableSet")),
	INT_IDB_TABLE(XPATH,getPropValue("intIdbTableSet")),
	
	//For FallOuts
	INT_IDB_TAB(XPATH,getPropValue("intIdbTable")),
	IDB_TABLE(XPATH,getPropValue("idbTable")),
	SDB_SNMP_TABLE(XPATH,getPropValue("sdbSnmpCliTable")),
	IDB_IBBB_TABLE(XPATH,getPropValue("idbIPBBTable"));
	
	private Locator locator;
	private String expression;
	
	public static String getPropValue(String value)
    {
       // String path = Utility.getValueFromPropertyFile("dnrFlowPropPath", );
        value = Utility.getValueFromPropertyFile( value, Constants.DNR_FLOW_PROP_PATH);
        return value;
    }
	
	DnRFlowPageElement(Locator locator, String expression){
		this.locator = locator;
		this.expression = expression;
	}
	
	public Locator getLocator() {
		return locator;
	}

	public String getValue() {
		return expression;
	}

	public By getBy(String...placeholder) {
		return ByType.getLocator(locator, expression, placeholder);
	}

}
