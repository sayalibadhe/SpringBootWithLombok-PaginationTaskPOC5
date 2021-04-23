package com.sayali.poc.main.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sayali.poc.main.model.User;
import com.sayali.poc.main.service.UserService;
import lombok.extern.slf4j.Slf4j;
//slf4j helps lombok to create a logger field to use functions like log.info
@Slf4j
@Controller

public class UserController {
	
	@Autowired
	private UserService service;
	
	// displays list of users
	 @RequestMapping("/")
		public String viewHomePage(Model model) {
		 	return findPaginated(1, "fname", "asc", model);		
		}
		
	 @RequestMapping(path = "/showNewUserPage")
		public String showNewUserForm(Model model) {
		 log.info("Displaying new user page and accepting the data entered");
			//binds form data using model attribute
			User user = new User();
			model.addAttribute("user", user);
			return "new_user";  //html page
		}
		
	 //saves user to database
	 @RequestMapping(path = "/saveUser")
		public String saveUser(@ModelAttribute("user") User user) {
		 log.info("Saves the user details in the database and redirects to original page");
			
			service.saveUser(user);
			return "redirect:/";  //redirect to previous page
		}
		
	 //lets user to update details
	 @RequestMapping(path= "/showPageForUpdate/{id}")
		public String showPageForUpdate(@PathVariable ( value = "id") long id, Model model) {
			log.info("Displays page for user to update details in the database");
			// get user from the service
			User user = service.getUserById(id);
			
			// set employee as a model attribute to pre-populate the form
			model.addAttribute("user", user);
			return "update_user"; //html page
		}
		
	 //deletes user
	 @RequestMapping(path= "/deleteUser/{id}")
		public String deleteUser(@PathVariable (value = "id") long id) {
			log.warn("Deletes the user of specified id");
			// call delete user method 
			this.service.deleteUserById(id);
			return "redirect:/"; //redirect to previous page
		}
		
		
	 //pagination
	 @RequestMapping(path ="/page/{pageNo}")
		public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
				@RequestParam("sortField") String sortFie,
				@RequestParam("sortDir") String sortDir,
				Model model) {
		 log.info("Pagination used to sort the pages according to required conditions and display the selected page ");
			int pageSize = 4;
			
			Page<User> page = service.findPaginated(pageNo, pageSize, sortFie, sortDir);
			List<User> listOfUsers = page.getContent();
			
			//gets page elements and also total pages
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems", page.getTotalElements());
			
			model.addAttribute("sortField", sortFie);
			model.addAttribute("sortDir", sortDir);
			model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
			
			model.addAttribute("listUsers", listOfUsers);
			return "index";  //html page
		}
	}
