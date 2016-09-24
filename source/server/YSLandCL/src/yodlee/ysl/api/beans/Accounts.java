/*
 * Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Yodlee, Inc.
 * Use is subject to license terms.
 */
package yodlee.ysl.api.beans;

public class Accounts {

	Account[] account;

	public Account[] getAccounts() {
		return account;
	}

	public void setAccounts(Account[] account) {
		this.account = account;
	}
	
	public String toString()
	{
		StringBuilder accounts = new StringBuilder("");
		for (int i = 0; i<account.length; i++)
		{
			accounts.append(account[i].getId()).append("=>").append(account[i].getAccountName()).append("\n");
		}
		return accounts.toString();
	}

}
