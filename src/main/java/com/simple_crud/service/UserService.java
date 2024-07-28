package com.simple_crud.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.simple_crud.model.User;
import com.simple_crud.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	private UserRepository userRepo;
	
	public ResponseEntity<User> addUser(User user){
		if(userRepo.existsById(user.getEmail())) {
			throw new RuntimeException("user Already exist");
		}
		else {
			userRepo.save(user);
			return new ResponseEntity<User>(user,HttpStatus.CREATED);
		}
	}
	
	public ResponseEntity<User> updateUser(User user){
		if(!userRepo.existsById(user.getEmail())) {
			throw new RuntimeException("user does not exist");
		}
		else {
			userRepo.save(user);
			return new ResponseEntity<User>(user,HttpStatus.CREATED);
		}
	}
	
	public ResponseEntity<String> deleteUser(String userId){
		if(!userRepo.existsById(userId)) {
			throw new RuntimeException("user does not exists");
		}
		else {
			userRepo.deleteById(userId);
			return new ResponseEntity<String>("User with Id :- "+userId+" removed succesfullly",HttpStatus.OK);
		}
	}
	
	public ResponseEntity<User> getUser(String userId){
		if(!userRepo.existsById(userId)) {
			throw new RuntimeException("user does not exists");
		}
		else {
			return new ResponseEntity<User>(userRepo.findById(userId).get(),HttpStatus.FOUND);
		}
	}

	public ResponseEntity<List<User>> getAllUser() {
		return new ResponseEntity<List<User>>(userRepo.findAll(), HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<List<User>> getAllUser(String name) {
		return new ResponseEntity<List<User>>(userRepo.getUserByName(name), HttpStatus.ACCEPTED);
	}
}
