package com.netcracker.shaw.at_shaw_sd.util;

import java.util.Map;

public class DisplayUtility<T, U> {
	public String mapToString(Map<T, U> map) {
		String mapString = "";
		
		if(map == null)
			return mapString;
		
		for(T key : map.keySet()) {
			mapString += "[" + key + " -> " + map.get(key) + "]";
		}
		return mapString;
	}
}
