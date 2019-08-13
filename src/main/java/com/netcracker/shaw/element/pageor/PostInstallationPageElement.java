package com.netcracker.shaw.element.pageor;
import static com.netcracker.shaw.factory.Locator.XPATH;

import org.openqa.selenium.By;
import org.testng.Assert;
import com.netcracker.shaw.at_shaw_sd.util.Constants;
import com.netcracker.shaw.at_shaw_sd.util.Utility;
import com.netcracker.shaw.element.PageElement;
import com.netcracker.shaw.factory.ByType;
import com.netcracker.shaw.factory.Locator;

public enum PostInstallationPageElement implements PageElement {
	
	CUST_REPO_VIEW_LINK(XPATH,getPropValue("custRepoViewLink")),
	FDB_DB_LINK_NAME(XPATH,getPropValue("fdbDbLinkName")),
	USED_IN_INTEG(XPATH,getPropValue("usedInInteg")),
	MANAGEMENT_SYS_NAME(XPATH,getPropValue("managementSysName")),
	DISC_IP_RANGE(XPATH,getPropValue("discIpRange")),
	DISC_EXCLUDE_IP_RANGE(XPATH,getPropValue("discExcludeIpRange")),
	SNMP_COMMUNITY_STR(XPATH,getPropValue("snmpCommunityStr")),
	CLI_PORT(XPATH,getPropValue("cliPort")),
	TELNET_PORT(XPATH,getPropValue("telnetPort")),
	SNMP_PORT(XPATH,getPropValue("snmpPort")),
	PORTS_INFO(XPATH,getPropValue("portsInfo"));
    
	private Locator locator;
	private String expression;
	
	public static String getPropValue(String value)
    {
        value = Utility.getValueFromPropertyFile( value, Constants.PI_FLOW_PROP_PATH );
        return value;
    }
	
	PostInstallationPageElement(Locator locator, String expression){
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
