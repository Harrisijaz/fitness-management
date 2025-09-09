package com.fitness.serviceactivity.repository;

import com.fitness.serviceactivity.entity.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryActivity extends MongoRepository<Activity,String> {


    List<Activity> findByUserId(String userId);
}
