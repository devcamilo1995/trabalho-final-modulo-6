package com.dbc.trabalhovemser.repository;
import com.dbc.trabalhovemser.entity.HoteisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoteisRepository extends JpaRepository<HoteisEntity, Integer> {

}

