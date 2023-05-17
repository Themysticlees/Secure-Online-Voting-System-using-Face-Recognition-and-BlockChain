package net.codejava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import net.codejava.model.CustomUserDetails;
import net.codejava.model.User;
import net.codejava.repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// TODO Auto-generated method stub
		
		User user = this.repo.findByUsername(username);
		
		if(user==null)
			throw new UsernameNotFoundException("User not found!!!");
		
		return new CustomUserDetails(user);
	}

}
