package com.ercan.service.impl;

import com.ercan.entity.Book;
import com.ercan.exception.BookNotFoundException;
import com.ercan.repository.BookRepository;
import com.ercan.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Transactional
@Service
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book saveBook(Book book) {
        logger.info("saving book with id - {}", book.getId());
        return bookRepository.save(book);
    }

    @Override
    @CachePut(cacheNames = "books", key = "#book.id")
    public Book updateBook(Book book) {
        logger.info("book updated with new name");
        bookRepository.updateBook(book.getId(), book.getName());
        return book;
    }

    @Override
    @Cacheable(cacheNames = "books", key = "#id")
    public Book getBookById(long id) {
        logger.info("fetching book from db");
        Optional<Book> book = bookRepository.findById(id);
        return book.isPresent() ? book.get() : book.orElseThrow(() -> new BookNotFoundException("Book not found!"));

        //Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found!"));
        // return book;
    }

    @Override
    @CacheEvict(cacheNames = "books", key = "#id")
    public String deleteBookById(long id) {
        bookRepository.deleteById(id);
        return "Book deleted.";
    }
}
