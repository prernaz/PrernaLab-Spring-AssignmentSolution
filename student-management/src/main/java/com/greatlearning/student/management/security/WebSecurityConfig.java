package com.greatlearning.student.management.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.greatlearning.student.management.service.UserDetailsServiceImpl;

//Configuration class for creating spring-beans

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//methods with @Bean annotation returning instances of
//UserDetailsService,BCryptPasswordEncoder & DaoAuthenticationProvider.
	
	@Bean
	public UserDetailsService userDetailsService() {
	    return new UserDetailsServiceImpl();
	}
	 
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

//The DaoAuthenticatorProvider needs to be set with the 
//instances of UserDetailsService & BCryptPasswordEncoder 
//using the methods setUserDetailsService & setPasswordEncoder respectively.   
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService());
	    authProvider.setPasswordEncoder(passwordEncoder());
	    return authProvider;
	}
	    
//Now overriding the configure method as given below
//which takes AuthenticationManagerBuilder as parameter
//This method sets the AuthenticationProvider on the 
//builder instance using the authenticationProvider method
//This sets the AuthenticationProvider for the instance
// and now the authentiation will work using the database. 
//i.e. according to the entries in the users table.

			
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		    auth.authenticationProvider(authenticationProvider());
		}

//overriding other config method with HttpSecurity paramter to set
//the authorization, as follows: 
		     
		@Override
protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
		     .antMatchers("/",
		         "/student/save",
		        "/student/showFormForAdd",
		        "/student/403")
      //the above urls are accessible with roles STUDENT and ADMIN
		        .hasAnyAuthority("STUDENT","ADMIN")
		        .antMatchers("/student/showFormForUpdate",
		        "/student/delete")
	//the above urls are accessible with role of ADMIN only
		        .hasAuthority("ADMIN")
		        .anyRequest()
		        .authenticated()
		        .and()
		        .formLogin()
		           .loginProcessingUrl("/login")
	//successful login redirects to /student/list URL
		           .successForwardUrl("/student/list")
		           .permitAll()
		        .and()
		           .logout()
    //logout redirects to /login  URL
		        .logoutSuccessUrl("/login").permitAll()
		        .and()
	//unauthorized access attempt redirects to 403 URL
		        .exceptionHandling().accessDeniedPage("/student/403")
		        .and()
		        .cors().and().csrf().disable();
  
		}	      
		
	}



