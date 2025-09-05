package com.project4.Service;

import java.time.LocalDate; 
import java.util.List;
import java.util.Optional;

import com.project4.Entity.BookEntity;
import com.project4.Entity.LoanEntity;

public interface ILoanService {

	public LoanEntity findById(Long id); 
	
	public Optional<LoanEntity> findByBook(BookEntity bookEntity);

	public List<LoanEntity> findByLoanDate(LocalDate date); 
	
	public List<LoanEntity> searchLoan(String search); 
	
	public void crateLoan(Long userId, Long bookId);

	public void returnBook(Long loanId);
	
	public List<LoanEntity> findByUserId(Long id);
}
