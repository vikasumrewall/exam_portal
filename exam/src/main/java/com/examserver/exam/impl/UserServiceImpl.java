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
import com.examserver.exam.model.Role;
import com.examserver.exam.model.User;
import com.examserver.exam.repo.RoleRepo;
import com.examserver.exam.repo.UserRepo;
import com.examserver.exam.service.UserService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	RoleRepo roleRepo;

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub

		Optional<User> usr = userRepo.findByEmailId(user.getEmailId());

		if (usr.isPresent()) {
			throw new ResourceNotFoundException("User already exists with value", "eamilid", usr.get().getEmailId(),
					"002");
		}

		return userRepo.save(user);
	}

	@Override
	public User finduser(String emailId) {
		return userRepo.findByEmailId(emailId).orElseThrow(
				() -> new ResourceNotFoundException("No User found with this email id", "eamilid", emailId, "001"));

	}

	@Override
	public List<User> findAllUser() {
		List<User> userLst = userRepo.findAll();
		return userLst;
	}

	@Override
	public User updateUser(User user, String emailId) {
		Optional<User> userWExisting = userRepo.findByEmailId(emailId);
		if (!userWExisting.isPresent()) {
			throw new ResourceNotFoundException("User not found exists with value", "eamilid", emailId, "001");
		}

		Set<Role> roleSet = new HashSet<Role>();

		for (Role roleObj : user.getRole()) {

			Optional<Role> roleOpt = roleRepo.findByName(roleObj.getName());
			if (!roleOpt.isPresent()) {
				throw new ResourceNotFoundException("Role not found exists with value", "RoleName", roleObj.getName(),
						"001");
			}
			roleSet.add(roleOpt.get());
		}

		System.out.println("-->" + user.getLastName());

		userWExisting.get().setFirstName(user.getFirstName());
		userWExisting.get().setLastName(user.getLastName());
		userWExisting.get().setActive(user.isActive());
		userWExisting.get().setRole(roleSet);
		/*
		 * User userNew=userWExisting.get().builder() .firstName(user.getFirstName())
		 * .lastName(user.getLastName()) .active(user.isActive()) .role(roleSet)
		 * .build();
		 */

		// System.out.println("-updated->"+userNew.getLastName());
		// System.out.println("-password->"+userNew.getPassword());

		log.info("--");

		userRepo.save(userWExisting.get());

		return userWExisting.get();
	}

	@Override
	public void delete(String emailId) {
		log.info("delete");
		//User usr = userRepo.findByEmailId(emailId).orElseThrow(()-> new  ResourceNotFoundException("User not found exists with value", "eamilid", emailId, "001"));
		Optional<User> usr = userRepo.findByEmailId(emailId);
		if (!usr.isPresent()) {
			log.info("delete......");
			throw new ResourceNotFoundException("User not found exists with value", "eamilid", emailId, "001");
		}
		userRepo.delete(usr.get());

	}

}
