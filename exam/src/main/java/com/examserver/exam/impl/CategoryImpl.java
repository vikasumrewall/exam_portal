package com.examserver.exam.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.examserver.exam.exception.ApiException;
import com.examserver.exam.exception.ResourceNotFoundException;
import com.examserver.exam.model.Category;
import com.examserver.exam.model.Quiz;
import com.examserver.exam.model.Role;
import com.examserver.exam.model.User;
import com.examserver.exam.repo.CategoryRepo;
import com.examserver.exam.repo.QuizRepo;
import com.examserver.exam.repo.RoleRepo;
import com.examserver.exam.repo.UserRepo;
import com.examserver.exam.service.CategoryService;
import com.examserver.exam.service.QuizService;
import com.examserver.exam.service.UserService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;

@Service
@Slf4j
public class CategoryImpl implements CategoryService {

	

	@Autowired
	CategoryRepo categoryRepo;

	

	@Autowired
	QuizRepo quizRepo;
	
	
	@Override
	public Category addCategory(Category category) {
		// TODO Auto-generated method stub

		return categoryRepo.save(category);
		
	}

	@Override
	public Category findCategory(Long id) {
		return categoryRepo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("No Categopry found with this  id", "id", String.valueOf(id), "001"));

	}

	@Override
	public List<Category> findAllCategory() {
		
		return categoryRepo.findAll();
	}

	

	@Override
	public void delete(Long id) {
		log.info("delete");
		//User usr = userRepo.findByEmailId(emailId).orElseThrow(()-> new  ResourceNotFoundException("User not found exists with value", "eamilid", emailId, "001"));
		Optional<Category> usr = categoryRepo.findById(id);
		if (!usr.isPresent()) {
			log.info("delete......");
			throw new ResourceNotFoundException("Category not found exists with value", "id", String.valueOf(id), "001");
		}
		categoryRepo.delete(usr.get());

	}

}
