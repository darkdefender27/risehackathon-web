/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package yodlee.ysl.api.parsers;

import java.io.IOException;

public interface Parser 
{
	public <T> Object parseJSON(String json) throws IOException;
}
