package com.simple_crud.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simple_crud.model.User;
import com.simple_crud.service.UserService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/user")
public class UserController {
	UserService service;
	
	@PostMapping("")
	private ResponseEntity<User> addUser(@RequestBody @Valid User user){
		return service.addUser(user);
	}
	
	@PutMapping("")
	private ResponseEntity<User> updateUser(@RequestBody @Valid User user){
		return service.updateUser(user);
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<User> getUser(@PathVariable String id){
		return service.getUser(id);
	}
	
	@GetMapping("")
	private ResponseEntity<List<User>> getUsers(@RequestParam(name = "name",required = false) String name){ 
		if (name==null)return service.getAllUser();
		return service.getAllUser(name);
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String> deleteUser(@PathVariable String id){
		return service.deleteUser(id);
	}
}
