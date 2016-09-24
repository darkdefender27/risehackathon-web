/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package yodlee.ysl.api.beans;

public class RefreshStatus {

	private String providerAccountId;
	private RefreshInfo refreshInfo;
	LoginForm loginForm;
	
	public LoginForm getLoginForm() {
		return loginForm;
	}
	public void setLoginForm(LoginForm loginForm) {
		this.loginForm = loginForm;
	}
	public String getProviderAccountId() {
		return providerAccountId;
	}
	public void setProviderAccountId(String providerAccountId) {
		this.providerAccountId = providerAccountId;
	}
	public RefreshInfo getRefreshInfo() {
		return refreshInfo;
	}
	public void setRefreshInfo(RefreshInfo refreshInfo) {
		this.refreshInfo = refreshInfo;
	}
	
	public String toString()
	{
		StringBuilder refreshStatus = new StringBuilder("");
		refreshStatus.append("providerAccountId = " + providerAccountId).append(refreshInfo.toString());
		return refreshStatus.toString();
	}
	
	
	
}
