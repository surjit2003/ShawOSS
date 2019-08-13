package com.netcracker.shaw.element.pageor;
import static com.netcracker.shaw.factory.Locator.XPATH;
import com.netcracker.shaw.at_shaw_sd.util.Constants;
import org.openqa.selenium.By;
import com.netcracker.shaw.at_shaw_sd.util.Utility;
import com.netcracker.shaw.element.PageElement;
import com.netcracker.shaw.factory.ByType;
import com.netcracker.shaw.factory.Locator;

public enum CustomizingValidationReportPageElement implements PageElement {
	
    ATLEAST_ONE_FALLOUT(XPATH,getPropValue("atleastOneFallOut")),
    MIN_TWO_FALLOUTS(XPATH,getPropValue("minTwoFallOuts")),
    FIRST_FALLOUT_SRC_DES(XPATH,getPropValue("firstFallOutSrcDesc")),
    SEC_FALLOUT_SRC_DES(XPATH,getPropValue("firstFallOutSrcDesc")),
    FIRST_FALLOUT_INPUT(XPATH,getPropValue("firstFallOutInput")),
    SEC_FALLOUT_INPUT(XPATH,getPropValue("secFallOutInput")),
    LINK_SERVICE_TKT(XPATH,getPropValue("linkServiceTkt")),
    TOP_BUTTON(XPATH,getPropValue("topButton")),
    NEW_SVC_TICKET(XPATH,getPropValue("newSvcTicket")),
    SVC_TKT_NAME_INPUT(XPATH,getPropValue("svcTktNameInput")),
    DUE_DATE_INPUT(XPATH,getPropValue("dueDateInput")),
    SELECT_BTN(XPATH,getPropValue("selectBtn")),
    SVC_TKT_FILTER(XPATH,getPropValue("svcTktFilter")),
    SVC_TKT_FILTER_NAME_INPUT(XPATH,getPropValue("svcTktFilterNameInput")),
    MAJORITY_FILTER(XPATH,getPropValue("majorityFilter")),
    TABLE_NAME_FILTER(XPATH,getPropValue("tableNameFilter")),
    INPUT_CHECKBOX_MAJOR(XPATH,getPropValue("inputCheckBoxMajor")),
    INPUT_CHECKBOX_MINOR(XPATH,getPropValue("inputCheckBoxMinor")),
    MAJOR_FALLOUTS(XPATH,getPropValue("majorFallouts")),
    MINOR_FALLOUTS(XPATH,getPropValue("minorFallouts")),
    TABLE_NAME_LIST1(XPATH,getPropValue("tableNameList1")),
    TABLE_NAME_LIST2(XPATH,getPropValue("tableNameList2")),
    TABLE_NAME_LIST3(XPATH,getPropValue("tableNameList3")),
    INPUT_CHECKBOX_TABLE3(XPATH,getPropValue("inputCheckBoxTable3")),
    INPUT_CHECKBOX_TABLE2(XPATH,getPropValue("inputCheckBoxTable2")),
    INPUT_CHECKBOX_TABLE1(XPATH,getPropValue("inputCheckBoxTable1")),
	OBJ_NOT_FOUND(XPATH,getPropValue("objNotFound"));
	
	private Locator locator;
	private String expression;
	
	public static String getPropValue(String value)
    {
        value = Utility.getValueFromPropertyFile( value, Constants.CUSTOMIZE_VALIDREPO_PROP_PATH);
        return value;
    }
	
	CustomizingValidationReportPageElement(Locator locator, String expression){
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
