package com.netcracker.shaw.element.pageor;
import static com.netcracker.shaw.factory.Locator.XPATH;
import com.netcracker.shaw.at_shaw_sd.util.Constants;
import org.openqa.selenium.By;
import com.netcracker.shaw.at_shaw_sd.util.Utility;
import com.netcracker.shaw.element.PageElement;
import com.netcracker.shaw.factory.ByType;
import com.netcracker.shaw.factory.Locator;

public enum RIFlowPageElement implements PageElement {
	
	// New OSS VLAN Details
   
	NETWORK_ELEMENT_TAB(XPATH,getPropValue("tabNetworkElements")),
	NEW_NETWORK_ELEMENT(XPATH,getPropValue("createNetworkElements")),
	NETWORK_ELEMENT_NAME(XPATH,getPropValue("inputNEName")),
	CREATE_BUTTON(XPATH,getPropValue("createBtn")),
	ADD_NEW_DEVICE(XPATH,getPropValue("addNewDevice")),
	ADD_DEVICE_FROM_REPOS(XPATH,getPropValue("deviceRepositoryTab")),
	DEVICE_TITLE(XPATH,getPropValue("deviceTitle")),
	SEARCH_BUTTON(XPATH,getPropValue("searchBtn")),
	DEVICE_LIST(XPATH,getPropValue("deviceListDropDwn")),
	PHYSICAL_STATUS(XPATH,getPropValue("physicalStatusDrpDwn")),
	DEVICE_CREATED(XPATH,getPropValue("deviceCreated")),
	SLOTS_TAB(XPATH,getPropValue("slotsTab")),
	SELECT_SLOT(XPATH,getPropValue("selectSlot1")),
	INSERT_CARD_BTN(XPATH,getPropValue("insertCardBtn")),
	REPLACE_CARD_BTN(XPATH,getPropValue("replaceCardBtn")),
	REMOVE_CARD_BTN(XPATH,getPropValue("removeCardBtn")),
	DELETE_BTN(XPATH,getPropValue("deleteBtn")),
	SELECT_CARD(XPATH,getPropValue("selectCard1")),
	SELECT_REPLACE_CARD(XPATH,getPropValue("selectReplaceCard2")),
	INSERT_BTN(XPATH,getPropValue("insertBtn")),
	START_BTN(XPATH,getPropValue("startBtn")),
	NEXT_BTN(XPATH,getPropValue("nextBtn")),
	COMPLETE_BTN(XPATH,getPropValue("completeBtn")),
	PORTS_TAB(XPATH,getPropValue("portsTab")),
	CARDS_TAB(XPATH,getPropValue("cardsTab")),
	PHYSICAL_STATUS_CARD(XPATH,getPropValue("physicalStatusCard")),
	AUTOMATION_LOC(XPATH,getPropValue("automationLoc")),
	DEFAULT_LOC(XPATH,getPropValue("defaultLoc")),
	CREATE_PROVIDER_LOC(XPATH,getPropValue("createProvLoc")),
	INPUT_PROV_NAME(XPATH,getPropValue("inputProvLocName")),
	PREV_PATH(XPATH,getPropValue("prevPath")),
	DEVICE_PATH(XPATH,getPropValue("devicePath")),
	COPY_BTN(XPATH,getPropValue("copyBtn")),
	MOVE_BTN(XPATH,getPropValue("moveBtn")),
	TARGET_NE_DRP_DOWN_COPY(XPATH,getPropValue("targetNEDropDownCopy")),
	TARGET_NE_DRP_DOWN_MOVE(XPATH,getPropValue("targetNEDropDownMove")),
	NAME_FILTER(XPATH,getPropValue("nameFilter")),
	INPUT_NAME(XPATH,getPropValue("inputName")),
	APPLY_BTN(XPATH,getPropValue("applyBtn")),
	SEARCH_NE_PRESENT(XPATH,getPropValue("searchNEPresent")),
	CIRCUITS_TAB(XPATH,getPropValue("circuitsTab")),
	CREATE_CIRCUITS(XPATH,getPropValue("createCircuits")),
	CIRCUIT_NAME_INPUT(XPATH,getPropValue("circuitNameInput")),
	CARRIER1(XPATH,getPropValue("carrier1")),
	CARRIER2(XPATH,getPropValue("carrier2")),
	RESOURCE1(XPATH,getPropValue("resource1")),
	RESOURCE2(XPATH,getPropValue("resource2")),
	LAN1_STATUS(XPATH,getPropValue("lan1Status")),
	LAN2_STATUS(XPATH,getPropValue("lan2Status")),
	TAB_DELETE_BTN(XPATH,getPropValue("tabDeleteBtn")),
	DEFAULT_IP_RANGE_IPV4(XPATH,getPropValue("defaultIPRangeIPV4")),
	DEFAULT_IP_RANGE_IPV6(XPATH,getPropValue("defaultIPRangeIPV6")),
	ADD_RANGE_BTN(XPATH,getPropValue("addRangeBtn")),
	ADDRESS_INPUT(XPATH,getPropValue("addressInput")),
	PREFIX_DRPDOWN(XPATH,getPropValue("prefixDrpDown")),
	SPLIT_ICON(XPATH,getPropValue("splitIcon")),
	MERGE_ICON(XPATH,getPropValue("mergeIcon")),
	SUBRANGE_CHECKBOX(XPATH,getPropValue("subRangeCheckBox")),
	OBJ_TYPE_DRP_DOWN(XPATH,getPropValue("objTypeDrpDown")),
	TRANSMISSION_CKT(XPATH,getPropValue("transmissionCkt")),
	
	
	UPDATE_BUTTON(XPATH,getPropValue("updateBtn"));
    
	private Locator locator;
	private String expression;
	
	public static String getPropValue(String value)
    {
       //String path = Utility.getValueFromPropertyFile("riFlowPropPath");
        value = Utility.getValueFromPropertyFile( value, Constants.RI_FLOW_PROP_PATH );
        return value;
    }
	
	RIFlowPageElement(Locator locator, String expression){
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
