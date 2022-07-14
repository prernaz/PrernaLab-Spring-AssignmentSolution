package com.greatlearning.student.management.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.greatlearning.student.management.entity.Role;
import com.greatlearning.student.management.entity.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*MyUserDetails class implements the UserDetails interface of 
spring-security.It uses a private instance variable for the User
(the entity class created earlier). 
It adds constructor which uses User as parameter to initialize
the private variable. 

It implements all the methods as follows:     
for all methods returning boolean, returns true          
for getUsername & getPassword methods,
	returns username & password from the user object.
     
It implement the getAuthorities method to 
return List<GrantedAuthority> for each role of the user.
*/

public class MyUserDetails implements UserDetails {
//this class will use the User class, 
//whose object can be taken thru the constructor.
	
	private User user;
	
	public MyUserDetails(User user) {
		super();
		this.user = user;
	}
	
// getAuthorities() returns List<GrantedAuthority> for each role of d user.
	
	@Override
	public Collection <? extends GrantedAuthority>  getAuthorities()
	{
		 List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		 for(Role role : user.getRoles()) 
		 {
		   grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		 }
		 return grantedAuthorities;
	}
		
	// getUsername(), getPassword() return username,password of user.
		
	@Override
		public String getUsername(){
			return user.getUsername();
		}
		
	@Override
	public String getPassword(){
		return user.getPassword();
	}
	
//implement the methods returning boolean to return true;
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
  }



