package com.springtest.demoapp.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springtest.demoapp.entities.EmployeeMongo;

public interface EmployeRepositoryM extends MongoRepository<EmployeeMongo, Integer> {
    
}
