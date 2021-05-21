package com.emms.model;

import java.util.List;

public class AuthenticationResponse {
	
	private final String jwt;
	private final String username;
	private List<String> roles;

	public AuthenticationResponse(String jwt,String username, List<String> roles) {
		this.jwt = jwt;
		this.username = username;
		this.roles = roles;
	}

	public String getJwt() {
		return jwt;
	}

	public List<String> getRoles() {
		return roles;
	}


	public String getUsername() {
		return username;
	}

}
