package com.sayali.poc.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")

//lombok annotations 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long  id;
	
	@Column(name = "fname", nullable = false)
	private String fname;
	
	@Column(name = "lname", nullable = false)
	private String lname;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "contact", nullable = false, unique = true)
	private int contact;
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "country", nullable = false)
	private String country;
	
	
		
	}
	
	


