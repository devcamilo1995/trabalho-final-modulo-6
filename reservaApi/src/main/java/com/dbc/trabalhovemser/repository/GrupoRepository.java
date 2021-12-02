package com.dbc.trabalhovemser.repository;


import com.dbc.trabalhovemser.entity.GrupoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<GrupoEntity, Integer> {
}
