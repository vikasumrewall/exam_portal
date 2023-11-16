package com.examserver.exam.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.examserver.exam.exception.ApiException;
import com.examserver.exam.model.Quiz;
import com.examserver.exam.model.User;

public interface QuizService {
	
	public Quiz addQuiz(Quiz quiz);
	
	public Quiz findQuiz(Long id);
	
	public List<Quiz> findAllQuiz();
	
	public Quiz updateQuiz(Quiz quiz);
	
	public void delete(Long id) ;
	

}
