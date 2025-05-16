package com.bhattaditya2.author_service.repository;

import com.bhattaditya2.author_service.entity.Author;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    boolean existsByEmail(@NotBlank(message = "Email is mandatory") @Email(message = "Email should be valid") String email);
}
