package com.netcracker.shaw.element.pageor;
import static com.netcracker.shaw.factory.Locator.XPATH;
import com.netcracker.shaw.at_shaw_sd.util.Constants;
import org.openqa.selenium.By;
import com.netcracker.shaw.at_shaw_sd.util.Utility;
import com.netcracker.shaw.element.PageElement;
import com.netcracker.shaw.factory.ByType;
import com.netcracker.shaw.factory.Locator;

public enum IPPlannerReportsPageElement implements PageElement {
	
	//IP Planner Reports Details
    IP_PLANNER_REPORTS(XPATH,getPropValue("ipPlannerReports")),
    ALL_ASSIGNED_IP_REPORTS_LINK(XPATH,getPropValue("allAssignedIPRepoLink")),
    ALL_AVAIL_IP_REPORTS_LINK(XPATH,getPropValue("allAvailIPRepoLink")),
    ASSIGNED_IP_REPORTS_LINK(XPATH,getPropValue("assignedIPRepoLink")),
    RELEASED_IP_REPORTS_LINK(XPATH,getPropValue("releasedIPRepoLink")),
    REPORT_RESULTS_ELEMENT(XPATH,getPropValue("reportResultsElement")),
    STATUS_OF_IP_RANGE(XPATH,getPropValue("statusofIPRange")),
    FROM_TIME_INPUT(XPATH,getPropValue("fromTimeInput")),
    TO_TIME_INPUT(XPATH,getPropValue("toTimeInput")),
    DISPLAY_RESERVED_OPTION(XPATH,getPropValue("displayReservedOption")),
    TYPE_ELEMENT(XPATH,getPropValue("typeElement")),
    PARENT_RANGE_ELEMENT(XPATH,getPropValue("parentRangeElement")),
    PARENT_IP_RANGE_INPUT(XPATH,getPropValue("parentIpRangeInput")),
    SUB_RANGES_MASK_INPUT(XPATH,getPropValue("subRangesMaskInput"));
	
	private Locator locator;
	private String expression;
	
	public static String getPropValue(String value)
    {
        value = Utility.getValueFromPropertyFile( value, Constants.IP_PLANNER_REPORTS_PROP_PATH);
        return value;
    }
	
	IPPlannerReportsPageElement(Locator locator, String expression){
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
