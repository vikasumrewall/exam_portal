package com.examserver.exam.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.examserver.exam.impl.CategoryImpl;
import com.examserver.exam.impl.QuestionServiceImpl;
import com.examserver.exam.impl.UserServiceImpl;
import com.examserver.exam.model.Category;
import com.examserver.exam.model.Question;
import com.examserver.exam.model.Role;
import com.examserver.exam.model.User;
import com.examserver.exam.payload.ApiResponse;
import com.examserver.exam.repo.CategoryRepo;
import com.examserver.exam.repo.RoleRepo;
import com.examserver.exam.repo.UserRepo;
import com.examserver.exam.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("${PROJECT_NAME}")
public class QuestionController {

	
	 
	 @Autowired
	 private QuestionServiceImpl questionService;
	 
	
	 
	@PostMapping("/public/question")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question)
	{
	
		System.out.println("hi.addQuestion<>.");
		
	
		
		return new ResponseEntity<>(questionService.add(question), CREATED);
	}
	
	@GetMapping("/public/question/{id}")
	public ResponseEntity<Question> find(@PathVariable Long id)
	{
	
		System.out.println("hi.<>.find cat");
		
		
		
		return new ResponseEntity<>(questionService.find(id), OK);
	}
	
	@GetMapping("/public/question")
	public ResponseEntity<List<Question>> addUser()
	{
	
		System.out.println("hi.<>.find cat");
		
		
		
		return new ResponseEntity<>(questionService.findAll(), OK);
	}
	
	
	
}
