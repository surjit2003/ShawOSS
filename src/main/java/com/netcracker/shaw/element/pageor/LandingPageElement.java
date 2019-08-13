package com.netcracker.shaw.element.pageor;

import static com.netcracker.shaw.factory.Locator.ID;
import static com.netcracker.shaw.factory.Locator.LINK_TEXT;
import static com.netcracker.shaw.factory.Locator.XPATH;

import org.openqa.selenium.By;
import com.netcracker.shaw.at_shaw_sd.util.Constants;
import com.netcracker.shaw.at_shaw_sd.util.Utility;
import com.netcracker.shaw.element.PageElement;
import com.netcracker.shaw.factory.ByType;
import com.netcracker.shaw.factory.Locator;

public enum LandingPageElement implements PageElement{
	USER_NAME(ID, "user"),
	PASSWORD(ID, "pass"),
	USER_NAME_OSS(ID, "user"),
	PASSWORD_OSS(ID, "pass"),
	LOGIN(LINK_TEXT, "Login"),
	CONTINUE_BTN(XPATH, getPropValue("continueBtn")),
	CUSTOMER(XPATH, getPropValue("customer"))
	;
	private Locator locator;
	private String expression;
	
	
	public static String getPropValue(String value)
	{
	   // String path = Utility.getValueFromPropertyFile("landingPropPath");
	    value = Utility.getValueFromPropertyFile( value, Constants.LANDING_PAGE_PROP_PATH );
	    return value;
	}
	
	LandingPageElement(Locator locator, String expression){
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
