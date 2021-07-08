package com.example.graphql.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.graphql.entity.Author;
import com.example.graphql.entity.Tutorial;
import com.example.graphql.repository.AuthorRepository;
import com.example.graphql.repository.TutorialRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class Query implements GraphQLQueryResolver {
	private AuthorRepository authorRepository;
	private TutorialRepository tutorialRepository;

	@Autowired
	public Query(AuthorRepository authorRepository, TutorialRepository tutorialRepository) {
		this.authorRepository = authorRepository;
		this.tutorialRepository = tutorialRepository;
	}

	public Author findAuthorById(Long id) {
		return authorRepository.findById(id).get();
	}

	public Iterable<Author> findAllAuthors() {
		return authorRepository.findAll();
	}

	public Iterable<Tutorial> findAllTutorials() {
		return tutorialRepository.findAll();
	}

	public Long countAuthors() {
		return authorRepository.count();
	}

	public Long countTutorials() {
		return tutorialRepository.count();
	}

}