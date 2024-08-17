package com.example.springboot_mongodb.repository;

import com.example.springboot_mongodb.model.Proyek;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProyekRepository extends MongoRepository<Proyek, Integer> {
}
