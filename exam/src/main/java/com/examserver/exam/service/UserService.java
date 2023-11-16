package com.examserver.exam.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.examserver.exam.exception.ApiException;
import com.examserver.exam.model.User;

public interface UserService {
	
	public User addUser(User user);
	
	public User finduser(String emailId);
	
	public List<User> findAllUser();
	
	public User updateUser(User user,String emailId);
	
	public void delete(String emailId) ;
	

}
