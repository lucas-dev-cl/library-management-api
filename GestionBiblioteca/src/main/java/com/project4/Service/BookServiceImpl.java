package com.project4.Service;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project4.DTO.BookDTO;
import com.project4.Entity.BookEntity;
import com.project4.Repository.BookRepository;

@Service
public class BookServiceImpl implements IBookService{

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public BookEntity findById(Long id) {
		return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("No se ha encontrado el libro."));
	}

	@Override
	public List<BookEntity> searchBook(String search) {
		List<BookEntity> books = bookRepository.findByTitleContainingIgnoreCase(search); 
		
		if(books.isEmpty()) {
			books = bookRepository.findByAuthorContainingIgnoreCase(search);
		}
		
		return books;	
	}

	@Override
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}
	
	@Override
	public void updateBook(Long id, BookDTO bookDto) {
		BookEntity bookEntity = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("El libro no ha sido encontrado.")); 
		
		bookEntity.setAuthor(bookDto.getAuthor());
		bookEntity.setTitle(bookDto.getTitle());
		
		bookRepository.save(bookEntity);
		
	}

	@Override
	public void createBook(BookEntity bookEntity) {
		bookRepository.save(bookEntity);
	}

	@Override
	public List<BookEntity> getAllBooks() {
		List<BookEntity> books = bookRepository.findAll();
		return books;
	}

}
