package com.dbc.logapi.repository;

import com.dbc.logapi.entity.LogEmailEntity;
import com.dbc.logapi.entity.LogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends MongoRepository<LogEntity,String> {

}
