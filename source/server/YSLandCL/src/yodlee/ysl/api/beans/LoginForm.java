/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package yodlee.ysl.api.beans;

public class LoginForm {

	private String id;
	private String help;
	private String forgetPasswordURL;
	private String helpTitle;
	private String mfaInfo;
	private String mfaInfoTitle;
	private String formType;
	private String mfaTimeout;
	private LoginFormRow[] row;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHelp() {
		return help;
	}
	public void setHelp(String help) {
		this.help = help;
	}
	public String getForgetPasswordURL() {
		return forgetPasswordURL;
	}
	public void setForgetPasswordURL(String forgetPasswordURL) {
		this.forgetPasswordURL = forgetPasswordURL;
	}
	public LoginFormRow[] getRow() {
		return row;
	}
	public void setRow(LoginFormRow[] row) {
		this.row = row;
	}
	public String getHelpTitle() {
		return helpTitle;
	}
	public void setHelpTitle(String helpTitle) {
		this.helpTitle = helpTitle;
	}
	public String getMfaInfo() {
		return mfaInfo;
	}
	public void setMfaInfo(String mfaInfo) {
		this.mfaInfo = mfaInfo;
	}
	public String getMfaInfoTitle() {
		return mfaInfoTitle;
	}
	public void setMfaInfoTitle(String mfaInfoTitle) {
		this.mfaInfoTitle = mfaInfoTitle;
	}
	public String getFormType() {
		return formType;
	}
	public void setFormType(String formType) {
		this.formType = formType;
	}
	public String getMfaTimeout() {
		return mfaTimeout;
	}
	public void setMfaTimeout(String mfaTimeout) {
		this.mfaTimeout = mfaTimeout;
	}
	
	
}
