package com.example.demo.covid.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


/**
 * @author explorervarun
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