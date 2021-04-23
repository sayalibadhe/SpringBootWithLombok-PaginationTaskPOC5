package com.sayali.poc.main.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.sayali.poc.main.model.User;
import com.sayali.poc.main.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

	//injects repository
	@Autowired
	private UserRepository repository;
	
	
	//list of all users
	@Override
	public List<User> getAllUsers(){
		log.info("Gets all the listed users from the database");
		return repository.findAll();
	
	}
	
	//gets user by id 
	@Override
	public User getUserById(Long id) {
		log.info("Searches user with respective id");
		Optional<User> optional = repository.findById(id);
		User user = null;
		if (optional.isPresent()) {
			user = optional.get();
		} else {
			throw new RuntimeException(" User not found for id :: " + id);
		}
		return user;
	}
	

	//deletes user by id
	@Override
	public void deleteUserById(Long id) {
		log.warn("Deleted the user of specified id");
		this.repository.deleteById(id);
		
		
			}


	//saves user when clicked on save
	@Override
	public void saveUser(User user) {
		log.info("Save user");
		this.repository.save(user);
		
	}


	//method to use pagination which manages pagination operations 
	@Override
	public Page<User> findPaginated(int pageNo, int pageSize, String sortFie, String sortDir) {
		log.info("Pagination method to display pagination and in required order ");
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortFie).ascending() :
			Sort.by(sortFie).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.repository.findAll(pageable);
	}
		
	}


