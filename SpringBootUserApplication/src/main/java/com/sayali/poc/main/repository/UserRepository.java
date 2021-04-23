package com.sayali.poc.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.sayali.poc.main.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {

}
