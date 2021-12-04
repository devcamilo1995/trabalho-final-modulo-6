package com.dbc.logapi.repository;

import com.dbc.logapi.entity.LogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface LogRepository extends MongoRepository<LogEntity,String> {
    public List<LogEntity> findBydataLogLessThan(LocalDateTime hoje);
}
