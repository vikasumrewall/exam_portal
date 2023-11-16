package com.examserver.exam.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import com.examserver.exam.exception.ResourceNotFoundException;
import com.examserver.exam.model.Question;
import com.examserver.exam.repo.QuestionRepo;
import com.examserver.exam.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{
	
	@Autowired
	QuestionRepo questionRepo;

	@Override
	public Question add(Question question) {
		// TODO Auto-generated method stub
		return questionRepo.save(question);
	}

	@Override
	public Question find(Long id) {
		return questionRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("No Question found with this  id", "id", String.valueOf(id), "001"));
		
	}

	@Override
	public List<Question> findAll() {
		// TODO Auto-generated method stub
		return questionRepo.findAll();
	}

	@Override
	public Question update(Question question) {
		// TODO Auto-generated method stub
		return questionRepo.save(question);
	}

	@Override
	public void delete(Long id) {
		Question question=find(id);
		if(question!=null)
		{
			questionRepo.delete(question);
		}
		
	}

}
