package com.examserver.exam.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.examserver.exam.impl.QuizImpl;
import com.examserver.exam.model.Category;
import com.examserver.exam.model.Quiz;

@RestController
@RequestMapping("${PROJECT_NAME}")
public class QuizController {
	
	@Autowired
	QuizImpl QuizServiceImpl;
	
	@PostMapping("/public/quiz")
	public Quiz add(@RequestBody Quiz quiz)
	{
		return QuizServiceImpl.addQuiz(quiz);
	}
	
	
	@GetMapping("/public/quiz/{id}")
	public ResponseEntity<Quiz> find(@PathVariable Long id)
	{
	
		System.out.println("hi.<>.find cat");
		
		
		
		return new ResponseEntity<>(QuizServiceImpl.findQuiz(id), OK);
	}
	
	@GetMapping("/public/quiz")
	public ResponseEntity<List<Quiz>> findALlCat()
	{
	
		System.out.println("hi.<>.find cat");
		
		
		
		return new ResponseEntity<>(QuizServiceImpl.findAllQuiz(), OK);
	}

}
