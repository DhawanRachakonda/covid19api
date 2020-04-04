/*******************************************************************************
 * Copyright     : Copyright (c)  2020 Honeywell Inc. All rights reserved.
 *                 This work contains trade secrets and confidential materials
 *                 of Honeywell Inc., and its use or disclosure in whole or in 
 *                 part without express written permission of Honeywell Inc. 
 *                 is prohibited.
 *
 * Name          :  JwtResponse.java.java
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
package com.example.demo.covid.dtos;

/**
 * @author H156833
 *
 */
import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}
