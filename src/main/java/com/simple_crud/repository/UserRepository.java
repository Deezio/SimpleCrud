package com.simple_crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.simple_crud.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	@Query("SELECT u FROM users u WHERE u.name LIKE %:name%")
	public List<User> getUserByName(@Param(value = "name") String name);
}
