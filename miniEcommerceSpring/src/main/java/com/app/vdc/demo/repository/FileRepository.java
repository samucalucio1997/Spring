package com.app.vdc.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.vdc.demo.Model.File;

public interface FileRepository extends JpaRepository<File,UUID>{
}
