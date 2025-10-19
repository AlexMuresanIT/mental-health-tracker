package com.health.mental.repository;

import com.health.mental.domain.MoodLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoodLogRepository extends MongoRepository<MoodLog, String> {}
