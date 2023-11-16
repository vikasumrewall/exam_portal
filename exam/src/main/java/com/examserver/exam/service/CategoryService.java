package com.examserver.exam.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.examserver.exam.exception.ApiException;
import com.examserver.exam.model.Category;
import com.examserver.exam.model.Quiz;
import com.examserver.exam.model.User;

public interface CategoryService {
	
	public Category addCategory(Category category);
	
	public Category findCategory(Long id);
	
	public List<Category> findAllCategory();
	
	public void delete(Long id) ;
	

}
