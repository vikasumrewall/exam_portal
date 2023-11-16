package com.examserver.exam.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examserver.exam.model.Question;
import com.examserver.exam.model.Quiz;
import com.examserver.exam.model.User;


@Repository
public interface QuizRepo  extends JpaRepository<Quiz,Long>{
	


	
	


}
