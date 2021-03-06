package com.stepping.step5.entity.repository;

import com.stepping.step5.entity.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BooksRepository extends JpaRepository<Book, Integer> {
}
