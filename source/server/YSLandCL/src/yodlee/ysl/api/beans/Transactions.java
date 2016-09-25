/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package yodlee.ysl.api.beans;

public class Transactions {

	Transaction[] transaction;

	public Transaction[] getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction[] transaction) {
		this.transaction = transaction;
	}

	public String toString()
	{
		StringBuilder transactions = new StringBuilder("");
		for (int i = 0; i<transaction.length; i++)
		{
			transactions.append(transaction[i].getId()).append("=>").append("amount=").append(transaction[i].getAmount().getAmount() + " "+ transaction[i].getAmount().getCurrency()).append(" => ").append("date=").append(transaction[i].getDate().getDate() + transaction[i].getDate().getFormat()).append("=>" + transaction[i].getBaseType()).append("\n");
		}
		return transactions.toString();
	}
}
