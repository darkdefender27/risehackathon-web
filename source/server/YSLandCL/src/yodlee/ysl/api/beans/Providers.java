/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package yodlee.ysl.api.beans;

public class Providers {

	private Provider[] provider;

	public Provider[] getProvider() {
		return provider;
	}

	public void setProvider(Provider[] provider) {
		this.provider = provider;
	}
	
	public String toString()
	{
		StringBuilder providers  = new StringBuilder("");
		for(int i=0;i<provider.length; i++)
		{
			providers.append(provider[i].toString());
		}
		return providers.toString();
	}
}
