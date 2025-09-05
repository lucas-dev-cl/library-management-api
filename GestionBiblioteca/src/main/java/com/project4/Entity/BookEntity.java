package com.project4.Entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Book")
public class BookEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private String title; 
	private String author; 
	private int stock;
	@OneToMany(mappedBy = "book")
	private Set<LoanEntity> loans; 
	
	
	public BookEntity() {}
	
	public BookEntity(String title, String author, int stock) {
		super();
		this.title = title;
		this.author = author;
		this.stock = stock;
	}
	
	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	} 	
}
