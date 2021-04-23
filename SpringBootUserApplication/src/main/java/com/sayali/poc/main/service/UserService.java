package com.sayali.poc.main.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sayali.poc.main.model.User;

public interface UserService {
	
	List<User> getAllUsers();
	void saveUser(User user);
	User getUserById(Long id);
	void deleteUserById(Long id);
	Page<User> findPaginated(int pageNo, int pageSize, String sortFie,String sortDir);
	

}
