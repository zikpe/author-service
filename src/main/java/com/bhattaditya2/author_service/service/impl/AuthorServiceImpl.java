package com.bhattaditya2.author_service.service.impl;

import com.bhattaditya2.author_service.entity.Author;
import com.bhattaditya2.author_service.exception.ResourceNotFoundException;
import com.bhattaditya2.author_service.repository.AuthorRepository;
import com.bhattaditya2.author_service.service.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAllAuthors() {

        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.
                findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found ID: " + id));
    }

    @Override
    public Author updateAuthor(Long id, Author author) { //updates whole resource - all fields
        Author updatedAuthor = getAuthorById(id);

        updatedAuthor.setName(author.getName());
        updatedAuthor.setDob(author.getDob());
        updatedAuthor.setEmail(author.getEmail());

        authorRepository.save(updatedAuthor);
        return updatedAuthor;
    }


    @Override
    public void deleteAuthor(Long id) {
        Author author = getAuthorById(id);
        authorRepository.delete(author);
    }

    @Override
    public Author patchAuthor(Long id, Map<Object, Object> fields) {
        Author author = getAuthorById(id);

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Author.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, author, value);
        });

        authorRepository.save(author);

        return author;

    }
}
