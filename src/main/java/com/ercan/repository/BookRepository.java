package com.ercan.repository;

import com.ercan.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book,Long> {

    //@Transactional
    @Modifying
    @Query("update Book u set u.name=?2 where u.id=?1")
    int updateBook(long id, String name);

}
