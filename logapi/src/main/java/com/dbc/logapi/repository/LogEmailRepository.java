package com.dbc.logapi.repository;

import com.dbc.logapi.entity.LogEmailEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogEmailRepository extends MongoRepository<LogEmailEntity,String> {

}
