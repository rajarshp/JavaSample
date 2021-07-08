package com.example.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.graphql.entity.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

}