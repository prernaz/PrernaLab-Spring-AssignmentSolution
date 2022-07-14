package com.greatlearning.student.management.service;
import com.greatlearning.student.management.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.greatlearning.student.management.repository.UserRepository;
import com.greatlearning.student.management.security.MyUserDetails;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if (user==null) {
			throw new UsernameNotFoundException("Username"+ username +" not found");
		}
		
		MyUserDetails myUserDetails = new MyUserDetails(user);
		
		return myUserDetails;
	}
}

	


