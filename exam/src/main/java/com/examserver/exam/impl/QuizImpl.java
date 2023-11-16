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
import com.examserver.exam.model.Quiz;
import com.examserver.exam.model.Role;
import com.examserver.exam.model.User;
import com.examserver.exam.repo.QuizRepo;
import com.examserver.exam.repo.RoleRepo;
import com.examserver.exam.repo.UserRepo;
import com.examserver.exam.service.QuizService;
import com.examserver.exam.service.UserService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;

@Service
@Slf4j
public class QuizImpl implements QuizService {

	

	@Autowired
	QuizRepo quizRepo;

	@Override
	public Quiz addQuiz(Quiz quiz) {
		// TODO Auto-generated method stub

		return quizRepo.save(quiz);
	}

	@Override
	public Quiz findQuiz(Long id) {
		return quizRepo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("No quiz found with this  id", "id", String.valueOf(id), "001"));

	}

	@Override
	public List<Quiz> findAllQuiz() {
		
		return quizRepo.findAll();
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		return quizRepo.save(quiz);
	}

	@Override
	public void delete(Long id) {
		log.info("delete");
		//User usr = userRepo.findByEmailId(emailId).orElseThrow(()-> new  ResourceNotFoundException("User not found exists with value", "eamilid", emailId, "001"));
		Optional<Quiz> quiz = quizRepo.findById(id);
		if (!quiz.isPresent()) {
			log.info("delete......");
			throw new ResourceNotFoundException("Quiz not found exists with value", "eamilid", String.valueOf(id), "001");
		}
		quizRepo.delete(quiz.get());

	}

}
