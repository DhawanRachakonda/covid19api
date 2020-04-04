/*******************************************************************************
 * Copyright     : Copyright (c)  2020 Honeywell Inc. All rights reserved.
 *                 This work contains trade secrets and confidential materials
 *                 of Honeywell Inc., and its use or disclosure in whole or in 
 *                 part without express written permission of Honeywell Inc. 
 *                 is prohibited.
 *
 * Name          :  ApiUtility.java.java
 * 
 * Project Title :  demo 
 *  
 * Description	:
 * 
 * 
 * Company       : Honeywell Connected Enterprise, Hyderabad
 * 
 * @Author       : H156833
 * 
 * @Date		 : Apr 4, 2020
 *
 * @Version      : Version 1.0
 * 
 * Modification History:
 * 
 * By            : <Modifier Name>
 * On            : <Modified Date>
 * Description   : <Description of modification>
 ******************************************************************************/
package com.example.demo.covid.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


/**
 * @author H156833
 *
 */
@Component
public class ApiUtility {

	@Autowired
	private Environment env;

	public String getProperty(String pPropertyKey) throws Exception {

		String str = env.getProperty(pPropertyKey);
		if (null != str)
			return str;
		else
			throw new Exception("Error getting the property");
	}
	
}