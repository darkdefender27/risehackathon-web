/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package yodlee.ysl.api.parsers;

import java.io.IOException;
import yodlee.ysl.api.beans.Bills;
import com.google.gson.Gson;

public class BillsParser implements Parser
{
	
	private String fqcn = this.getClass().getName();
	public Bills parseJSON(String json) throws IOException 
	{
		String mn = "parseJSON(" + json + ")";
		System.out.println(fqcn + " :: " + mn);
		Gson gson = new Gson();
		return (Bills)gson.fromJson(json, Bills.class);
	}


	
}
