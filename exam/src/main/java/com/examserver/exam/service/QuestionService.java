package com.examserver.exam.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.examserver.exam.exception.ApiException;
import com.examserver.exam.model.Question;
import com.examserver.exam.model.Quiz;
import com.examserver.exam.model.User;

public interface QuestionService {
	
	public Question add(Question question);
	
	public Question find(Long id);
	
	public List<Question> findAll();
	
	public Question update(Question quiz);
	
	public void delete(Long id) ;
	

}
