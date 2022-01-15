package com.ercan.service;

import com.ercan.entity.Book;

public interface BookService {
    Book saveBook(Book book);

    Book updateBook(Book book);

    Book getBookById(long id);

    String deleteBookById(long id);
}
