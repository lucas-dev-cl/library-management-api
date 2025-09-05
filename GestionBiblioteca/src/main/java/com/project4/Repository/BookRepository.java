package com.project4.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project4.Entity.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long>{
	
	public Optional<BookEntity> findById(Long id); 
	
	public Optional<BookEntity> findByTitle(String title); 
	
	public List<BookEntity> findByTitleContainingIgnoreCase(String title); 
	
	public List<BookEntity> findByAuthor(String author);
	
	public List<BookEntity> findByAuthorContainingIgnoreCase(String author);
	
}
