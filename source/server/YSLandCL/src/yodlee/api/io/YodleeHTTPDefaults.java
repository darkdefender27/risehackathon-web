/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package yodlee.api.io;

import java.util.HashMap;
import java.util.Map;

public class YodleeHTTPDefaults {

	private static Map<String,String> yodleeHTTPDefaults = new HashMap<String,String>();
	
	public static void initYodleeHTTPDefaultsMap()
	{
		yodleeHTTPDefaults.put("User-Agent", "Mozilla/5.0");
		yodleeHTTPDefaults.put("Content-Type","application/x-www-form-urlencoded");
		yodleeHTTPDefaults.put("Accept","application/json");
		
	}
	
	public static Map<String,String> getYodleeHTTPDefaultsMap()
	{
		return yodleeHTTPDefaults;
	}

}
