package com.project4.DTO;

public class BookDTO {

	private String title; 
	private String author; 
	private int stock;
	
	public BookDTO() {}
	
	public BookDTO(String title, String author, int stock) {
		super();
		this.title = title;
		this.author = author;
		this.stock = stock;
	} 
	
	public BookDTO(String title, String author) {
		super(); 
		this.title = title; 
		this.author = author; 
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
