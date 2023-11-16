package com.examserver.exam.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examserver.exam.exception.ApiException;
import com.examserver.exam.exception.ResourceNotFoundException;
import com.examserver.exam.impl.UserServiceImpl;
import com.examserver.exam.jwt.AuthRequest;
import com.examserver.exam.jwt.JwtService;
import com.examserver.exam.model.Role;
import com.examserver.exam.model.User;
import com.examserver.exam.payload.ApiResponse;
import com.examserver.exam.repo.RoleRepo;
import com.examserver.exam.repo.UserRepo;
import com.examserver.exam.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("${PROJECT_NAME}")
public class UserController {

	 @Autowired
	 private PasswordEncoder passwordEncoder;
	 
	 @Autowired
	 private RoleRepo roleRepo;
	 
	 @Autowired
	 UserServiceImpl userService;
	 
	 @Autowired
	 UserRepo userRepo;
	 
	 
	   @Autowired
	    private JwtService jwtService; 
	  
	    @Autowired
	    private AuthenticationManager authenticationManager; 
	 
	@PostMapping("/public/user")
	public ResponseEntity<User> addUser(@RequestBody User user)
	{
	
		System.out.println("hi.<>..addUser...");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		Set <Role > roleSet=new HashSet<Role>();
		for(Role role:user.getRole())
		{
			System.out.println("hi......");
			if(role!=null && role.getName()!=null)
			{
				System.out.println("hi......"+role.getName());
				
				Optional <Role> roleObj=roleRepo.findByName(role.getName());
				
				if(!roleObj.isEmpty())
				{
					roleSet.add(roleObj.get());
					System.out.println("hllli"+roleObj.get().getId());
				}
			}
					
		}
		user.setRole(roleSet);
		
		return new ResponseEntity<>(userService.addUser(user), CREATED);
	}
	
	@GetMapping("/public/user/{emailId}")
	public ResponseEntity<User> findUser(@PathVariable String emailId )
	{ 
		System.out.println("find....."+emailId);
		
		 User user=userService.finduser(emailId);
	      return new ResponseEntity<>(user, OK);
	}
	
	@GetMapping("/public/user")
	public ResponseEntity<List<User>> findUser( )
	{ 
	
		
		 List<User> user=userService.findAllUser();
	      return new ResponseEntity<List<User>> (user, OK);
	}
	
	
	@PutMapping("/public/user/{emailId}")
	public ResponseEntity<User> updateUser(@RequestBody User user ,@PathVariable String emailId)
	{
		System.out.println("updateUser.....<>");
	
		User userUpdated=userService.updateUser(user, emailId);
		
		return new ResponseEntity<>(userUpdated, CREATED);
		
	}
	
	
	@DeleteMapping("/public/user/{emailId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable String emailId)
	{
		System.out.println("deleteUser.....<>");
	
		 userService.delete(emailId);
		
		ApiResponse apiResponse=	ApiResponse.builder()
		.message("User Deleted Successfully")
		.status(false)
		.errorCode("0").build();
		
		return new ResponseEntity<>(apiResponse, CREATED);
		
	}
	
	
	@PostMapping("/public/generateToken") 
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) { 
       System.out.println("hi55.+"+authRequest.getUsername());
       System.out.println("hi55.+"+authRequest.getPassword());
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())); 
		System.out.println("hi...");
		if (authentication.isAuthenticated()) { 
        	System.out.println("hi...");
            return jwtService.generateToken(authRequest.getUsername()); 
        } else { 
        	System.out.println("else");
            throw new ResourceNotFoundException("User Login","User notfound user request","NO user found","101"); 
        } 
    } 
	
	
}
