package com.javabuster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.javabuster.entity.Book;

@Service
public interface BookRepository extends JpaRepository<Book,Long> {
}