package com.ercan.controller;

import com.ercan.entity.Book;
import com.ercan.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;


    @PostMapping("/book")
    public ResponseEntity addBook(@RequestBody Book book){
        return ResponseEntity.ok(bookService.saveBook(book));
    }

    @PutMapping("/book")
    public ResponseEntity updateBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(book));
    }

    @GetMapping("/book/{id}")
    public ResponseEntity getBook(@PathVariable long id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity deleteBook(@PathVariable long id){
        return ResponseEntity.ok(bookService.deleteBookById(id));
    }

}
