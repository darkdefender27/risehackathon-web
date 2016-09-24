/*
 * Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Yodlee, Inc.
 * Use is subject to license terms.
 */
package yodlee.ysl.api.beans;

public class Bills {

	Bill[] bill;

	public Bill[] getBill() {
		return bill;
	}

	public void setBill(Bill[] bill) {
		this.bill = bill;
	}
	
	public String toString()
	{
		StringBuilder bills = new StringBuilder("");
		for (int i = 0; i<bill.length; i++)
		{
			bills.append(bill[i].getId()).append("=>").append(bill[i].getDueAmount()).append("\n");
		}
		return bills.toString();
	}

}
