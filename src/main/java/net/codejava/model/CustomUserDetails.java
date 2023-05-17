package net.codejava.model;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails{
	
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	@Autowired
	private User user;
	
	public CustomUserDetails(User userSecurity) {
		super();
		this.user = userSecurity;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		HashSet<SimpleGrantedAuthority> set = new HashSet<>();
		set.add(new SimpleGrantedAuthority(this.user.getRole()));
		
		return set;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user.getUsername();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.user.getPassword();
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
