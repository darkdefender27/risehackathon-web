/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package yodlee.ysl.api.parsers;

import java.io.IOException;

import yodlee.ysl.api.beans.Accounts;

import com.google.gson.Gson;

public class AccountsParser implements Parser 
{
	private String fqcn = this.getClass().getName();
	public Accounts parseJSON(String json) throws IOException 
	{
		String mn = "parseJSON(" + json + ")";
		System.out.println(fqcn + " :: " + mn);
		Gson gson = new Gson();
		return (Accounts)gson.fromJson(json, Accounts.class);
	}

}
