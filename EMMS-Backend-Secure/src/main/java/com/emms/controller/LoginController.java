package com.emms.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emms.dao.UserRepository;
import com.emms.model.AuthenticationRequest;
import com.emms.model.AuthenticationResponse;
import com.emms.userdetails.UserDetailsServiceImpl;
import com.emms.util.JwtUtil;

@RestController
@CrossOrigin(origins ="*",allowedHeaders = "*",exposedHeaders = "*")
public class LoginController {
	
	private AuthenticationManager authenticationManager;
	private UserDetailsServiceImpl userDetailsServiceImpl;
	private JwtUtil jwtUtil;
	//private UserRepository userRepository; --will be needed to retrieve user details
	
	@Autowired
	public LoginController(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsServiceImpl,
			JwtUtil jwtUtil, UserRepository userRepository) {
		
		this.authenticationManager = authenticationManager;
		this.userDetailsServiceImpl = userDetailsServiceImpl;
		this.jwtUtil = jwtUtil;
		//this.userRepository = userRepository;
	}
	
	@GetMapping(value="/hello")
	public String hello() {
		return "hello";
	}
	
	@GetMapping(value="/secured")
	public String secured() {
		return "secured hello";
	}
	
	@PostMapping(value="/authenticate")
	public ResponseEntity<?> createAuthenticationToken (@RequestBody  AuthenticationRequest authenticationRequest) throws Exception{
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		}
		catch(BadCredentialsException e) {
			throw new Exception("Incorrect username or password : "+e);
		}
		
		final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(authenticationRequest.getUsername());
		
		List<String> roles =  userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		String responseUsername = userDetails.getUsername();
		
		final String jwt = jwtUtil.generateToken(userDetails);
		
		System.out.println("Authenticating user : " +responseUsername);
		System.out.println("With roles : " + roles.toString());
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt, responseUsername, roles));
	}
	
	

}
