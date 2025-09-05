package com.project4.Service;

import java.util.List;  

import com.project4.DTO.BookDTO;
import com.project4.Entity.BookEntity;

public interface IBookService {

	public void createBook(BookEntity bookEntity);
	
	public void deleteBook(Long id);
	
	public void updateBook(Long id, BookDTO bookDto);
	
	public BookEntity findById(Long id); 
	
	public List<BookEntity> searchBook(String title); 
	
	public List<BookEntity> getAllBooks(); 

}
