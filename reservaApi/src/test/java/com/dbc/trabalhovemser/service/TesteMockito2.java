package com.dbc.trabalhovemser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class TesteMockito2 {

    private EntityManager em;

    @Autowired
    public TesteMockito2(EntityManager em) {
        this.em = em;
    }
}
