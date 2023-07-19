package com.app.vdc.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.app.vdc.demo.Model.User;

public interface UserRepository extends JpaRepository<User,Integer>{

 
}
