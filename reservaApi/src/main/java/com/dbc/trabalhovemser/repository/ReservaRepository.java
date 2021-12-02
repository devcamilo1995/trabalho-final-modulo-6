package com.dbc.trabalhovemser.repository;
import com.dbc.trabalhovemser.entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Integer> {

}