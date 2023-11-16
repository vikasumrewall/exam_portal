package com.examserver.exam.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examserver.exam.model.User;


@Repository
public interface UserRepo  extends JpaRepository<User,Long>{
	
public Optional<User>findByEmailId(String emailId);

	
	


}
