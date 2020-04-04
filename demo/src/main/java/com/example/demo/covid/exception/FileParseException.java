/*******************************************************************************
 * Copyright     : Copyright (c)  2020 Honeywell Inc. All rights reserved.
 *                 This work contains trade secrets and confidential materials
 *                 of Honeywell Inc., and its use or disclosure in whole or in 
 *                 part without express written permission of Honeywell Inc. 
 *                 is prohibited.
 *
 * Name          :  FileParseException.java.java
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
package com.example.demo.covid.exception;

import org.springframework.http.HttpStatus;

/**
 * @author H156833
 *
 */
public class FileParseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpStatus httpStatus;
	
	public FileParseException(String errorMsg,HttpStatus httpStatus) {
		super(errorMsg);
		this.setHttpStatus(httpStatus);
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}
