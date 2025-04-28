package com.bhattaditya2.author_service.service;

import com.bhattaditya2.author_service.entity.Author;

import java.util.List;
import java.util.Map;

public interface AuthorService {
    Author createAuthor(Author author);
    List<Author> getAllAuthors();
    Author getAuthorById(Long id);
    Author updateAuthor(Long id, Author author);
    void deleteAuthor(Long id);
    Author patchAuthor(Long id, Map<Object, Object> fields);
}