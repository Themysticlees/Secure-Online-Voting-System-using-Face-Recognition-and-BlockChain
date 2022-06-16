package net.codejava.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import net.codejava.service.CustomUserDetailsService;



@Configuration

public class mySecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/index","/register","/js/**","/css/**","/fonts/**","/images/**","/inc/**","/scss/**","/plugin/**","/templates/register","/SweetAlert/**","/Register","/error","/forgotpassword/**","/news","/contact", "/sendemail","/videos/**","/getGraphData/**","/verify/**").permitAll()
			.antMatchers("/public/**","/vote/**").hasRole("USER")//only people with role user can access these pages
			.antMatchers("/admin/**").hasRole("ADMIN")
			//.antMatchers("/public/**").permitAll() doesnt authenticate these pages
			.anyRequest()
			.authenticated()
			.and()
			//.httpBasic();
			.formLogin()
			.loginPage("/index")
			.loginProcessingUrl("/dologin")
			.defaultSuccessUrl("/default",true) 
			// if login is successfull, system will be redirected to this url(/default)
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/index");
			
	}
	
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		// spring security provides authentication
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		//for encrypting the password using Bcryt object
		return new BCryptPasswordEncoder(10);
	}

}
