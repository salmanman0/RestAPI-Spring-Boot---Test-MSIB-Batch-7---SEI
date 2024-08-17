package com.example.springboot_mongodb.repository;

import com.example.springboot_mongodb.model.Lokasi;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface LokasiRepository extends MongoRepository<Lokasi, Integer> {
    List<Lokasi> findAllById(Iterable<Integer> ids);
}
