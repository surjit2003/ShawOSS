package com.netcracker.shaw.element.pageor;
import static com.netcracker.shaw.factory.Locator.XPATH;
import com.netcracker.shaw.at_shaw_sd.util.Constants;
import org.openqa.selenium.By;
import com.netcracker.shaw.at_shaw_sd.util.Utility;
import com.netcracker.shaw.element.PageElement;
import com.netcracker.shaw.factory.ByType;
import com.netcracker.shaw.factory.Locator;

public enum VLANFlowPageElement implements PageElement {
	
	// New OSS VLAN Details
    NETWORKS_TAB(XPATH,getPropValue("networksTab")),
    ETHERNET_NTW_FOLDER(XPATH,getPropValue("ethernetNtwFolder")),
    EOD_NETWORK(XPATH,getPropValue("eodNetwork")),
    VLANs_TAB(XPATH,getPropValue("vlansTab")),
    RESERVE_CVLAN(XPATH,getPropValue("reserveCVLans")),
    SVLAN24(XPATH,getPropValue("svlan24")),
    FILL_VLAN_POOL(XPATH,getPropValue("fillVLANPool")),
    ACCT_NUM(XPATH,getPropValue("acctNum")),
    FILL_ACCT_NUM(XPATH,getPropValue("fillAcctNum")),
    ADD_BTN(XPATH,getPropValue("addBtn")),
    OPTION_TWENTYFOUR(XPATH,getPropValue("24Option")),
    RELEASE_VLAN(XPATH,getPropValue("releaseVlan")),
    PARAMETERS_BOX_DAYS(XPATH,getPropValue("parametersBoxDays")),
    REMOVE_SYMBOL(XPATH,getPropValue("removeSymbol")),
    PARAMETERS_FIELD(XPATH,getPropValue("parametersField")),
    PARAMETERS_VALUE(XPATH,getPropValue("parametersValue")),
    TRIGGERS_TAB(XPATH,getPropValue("triggersTab")),
    RUN_JOB(XPATH,getPropValue("runJob")),
    ADD_SYMBOL(XPATH,getPropValue("addSymbol")),
    SEARCH_BOX(XPATH,getPropValue("searchBox")),
    MAC_ADDRESS_VALUE(XPATH,getPropValue("macAddressValue")),
    INPUT_MAC_ADDRESS_VALUE(XPATH,getPropValue("inputMacAddressValue")),
    ADMIN_STATUS_VALUE(XPATH,getPropValue("adminStatusValue")),
    DCCAP_VALUE(XPATH,getPropValue("dccapValue")),
    NEW_VLAN_BTN(XPATH,getPropValue("newVlanBtn")),
    NEW_INNER_VLAN_BTN(XPATH,getPropValue("newInnerVlanBtn")),
    VLAN_POOL_DRP_DWN(XPATH,getPropValue("vlanPoolDrpDown")),
    VLAN_ID_NAME(XPATH,getPropValue("vlanIDName")),
    VLAN_ID_INPUT(XPATH,getPropValue("vlanIdInput")),
    PATH_ELEMENTS_TAB(XPATH,getPropValue("pathElementsTab")),
    INNER_VLAN_IDS_INPUT(XPATH,getPropValue("innerVlanIdsInput")),
    ALREADY_EXIST_ERROR(XPATH,getPropValue("alreadyExistError")),
    /*EDIT_BUTTON_ELEMENT2(XPATH,getPropValue("editBtnElement2")),
    ELEMENT2_CARRIER_INPUT(XPATH,getPropValue("element2CarrierInput")),
    SAVE_BTN(XPATH,getPropValue("saveBtn")),*/
    PATH_ELEMENT2(XPATH,getPropValue("pathElement2")),
    RPD_NODE_INPUT(XPATH,getPropValue("rpdNodeInput")),
    RELEASE_BTN(XPATH,getPropValue("releaseBtn")),
    TRANSMISSION_CKT_FOLDER(XPATH,getPropValue("transCktFolder")),
    NUM_OF_DAYS(XPATH,getPropValue("numOfDays"));
    
	private Locator locator;
	private String expression;
	
	public static String getPropValue(String value)
    {
        //String path = Utility.getValueFromPropertyFile("vlanFlowPropPath");
        value = Utility.getValueFromPropertyFile( value, Constants.VLAN_FLOW_PROP_PATH);
        return value;
    }
	
	VLANFlowPageElement(Locator locator, String expression){
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
