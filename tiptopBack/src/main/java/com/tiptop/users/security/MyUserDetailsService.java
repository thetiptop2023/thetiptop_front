package com.tiptop.users.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tiptop.users.entities.User;
import com.tiptop.users.service.UserService;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userService.findUserByUsername(username);
		if (user==null)
			throw new UsernameNotFoundException("Utilisateur introuvable!");
		
		List<GrantedAuthority> auths = new ArrayList<>();


// Adjusted code for the enum:
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());
		auths.add(authority);
		
		return new org.springframework.security.core.
		  userdetails.User(user.getUsername(), user.getPassword(), auths);
	
	}

}
