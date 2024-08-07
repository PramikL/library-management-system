package com.example.lms.repo;

import com.example.lms.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Integer> {
}
