package com.example.graphql.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.graphql.entity.Author;
import com.example.graphql.entity.Tutorial;
import com.example.graphql.repository.AuthorRepository;

import graphql.kickstart.tools.GraphQLResolver;

@Component
public class TutorialResolver implements GraphQLResolver<Tutorial> {
  @Autowired
  private AuthorRepository authorRepository;

  public TutorialResolver(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  public Author getAuthor(Tutorial tutorial) {
    return authorRepository.findById(tutorial.getAuthor().getId()).orElseThrow(null);
  }
}