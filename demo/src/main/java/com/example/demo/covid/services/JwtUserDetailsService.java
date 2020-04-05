package com.example.demo.covid.services;

/**
 * @author explorervarun
 *
 */
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.covid.dtos.AppUser;
import com.example.demo.covid.exception.UserNameException;
import com.example.demo.covid.repositiories.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRep;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser= userRep.findByUsername(username);
		if(appUser != null) {
			return new User(appUser.getUsername(), appUser.getPassword(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
	
	public AppUser save(AppUser user) throws UserNameException {
		
		AppUser appUserExists = userRep.findByUsername(user.getUsername());
		if (appUserExists == null) {
		AppUser newUser = new AppUser();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRep.save(newUser);
		} else {
		 throw new UserNameException("Username already exists",HttpStatus.CONFLICT);
		}
		
	}
}
