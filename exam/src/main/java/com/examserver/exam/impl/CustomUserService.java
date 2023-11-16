package com.examserver.exam.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.examserver.exam.exception.ResourceNotFoundException;
import com.examserver.exam.model.User;
import com.examserver.exam.repo.UserRepo;
@Service
public class CustomUserService implements UserDetailsService{
	
	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Optional <User> user=userRepo.findByEmailId(userName);
		
		if(user.isEmpty())
			new ResourceNotFoundException("user not found with name:","user",userName,"003");
		
		return user.get();
	}

}
