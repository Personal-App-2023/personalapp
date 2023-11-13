package com.dodo.personalapp.repository;

import com.dodo.personalapp.entity.UserThoughts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ThoughtsRepository extends MongoRepository<UserThoughts,Integer> {

    List<UserThoughts> findByDateGreaterThanAndUserId(LocalDate date, int userId);

    List<UserThoughts> findByThoughtsListFeelingsEmotionAndDateGreaterThanAndUserId(String emotion,LocalDate date, int userId);
}
