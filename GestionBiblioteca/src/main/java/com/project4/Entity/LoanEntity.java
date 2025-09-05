package com.project4.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "loan")
public class LoanEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	@ManyToOne
	// No es necesario pero me aseguro de como se va a llamar en el workbench
	@JoinColumn(name = "user_id")
	private UserEntity user;
	@ManyToOne
	@JoinColumn(name = "book_id")
	private BookEntity book; 
	private LocalDate loanDate; 
	private LocalDate dueDate; 
	private LocalDate returnDate; 
	private Boolean returned = false;
	
	public LoanEntity() {}
	
	public LoanEntity(UserEntity user, BookEntity book) {
		this.user = user; 
		this.book = book; 
		this.returned = false;		
	}
	
	public Long getId() {
		return id;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public BookEntity getBook() {
		return book;
	}
	public void setBook(BookEntity book) {
		this.book = book;
	}
	public LocalDate getLoanDate() {
		return loanDate;
	}
	// PrePersist es una funcion void que llena el valor del atributo antes de que sea guardado
	@PrePersist
	public void prePersist() {
		if(this.loanDate ==  null) {
			this.loanDate = LocalDate.now(); 
		}
		
		if(this.dueDate == null) {
			this.dueDate = LocalDate.now().plusWeeks(2);
		}
	}
	
	public LocalDate getDueDate() {
		return dueDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}
	
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	
	public Boolean isReturned() {
		return returned;
	}
	
	public void setReturned(Boolean returned) {
		this.returned = returned;
	} 
}
