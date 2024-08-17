package com.example.springboot_mongodb.repository;

import com.example.springboot_mongodb.model.Counter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CounterRepository extends MongoRepository<Counter, String> {
}
