package com.examserver.exam.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examserver.exam.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
	
	public Optional <Role> findByName(String name);

}
