package com.example.demo.repository;

import com.example.demo.model.DemoString;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends ReactiveMongoRepository<DemoString, String> {
}
