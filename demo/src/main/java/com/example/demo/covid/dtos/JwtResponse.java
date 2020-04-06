package com.example.demo.covid.dtos;

/**
 * @author explorervarun
 *
 */
import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final String username;

	public JwtResponse(String jwttoken,String username) {
		this.username = username;
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public String getUsername() {
		return username;
	}
}
