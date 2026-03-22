package com.app.vdc.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.vdc.demo.Model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
   UserDetails findByUsername(String username); 
}
