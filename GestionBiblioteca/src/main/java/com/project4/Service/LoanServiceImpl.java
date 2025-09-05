package com.project4.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project4.Entity.BookEntity;
import com.project4.Entity.LoanEntity;
import com.project4.Entity.UserEntity;
import com.project4.Repository.BookRepository;
import com.project4.Repository.LoanRepository;
import com.project4.Repository.UserRepository;

@Service
public class LoanServiceImpl implements ILoanService{

	@Autowired
	private LoanRepository loanRepository; 
	
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private BookRepository bookRepository; 
	
	@Override
	public LoanEntity findById(Long id) {
		return loanRepository.findById(id).orElseThrow(() -> new RuntimeException("No se ha encontrado el prestamo."));
	}

	@Override
	public Optional<LoanEntity> findByBook(BookEntity bookEntity) {
		return loanRepository.findByBook(bookEntity);
	}
	
	@Override
	public List<LoanEntity> searchLoan(String search){
		List<LoanEntity> loans = loanRepository.findByUserUsernameContainingIgnoreCase(search); 
		
		if(loans.isEmpty()) {
			loans = loanRepository.findByBookTitleContainingIgnoreCase(search);
		}
		
		return loans;
		
	} 
	
	@Override
	public List<LoanEntity> findByLoanDate(LocalDate date) {
		List<LoanEntity> loans = loanRepository.findByLoanDate(date); 
		loans.stream().forEach(System.out::println);
		return loans; 
	}

	@Override
	public void crateLoan(Long userId, Long bookId) {
		UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("No se ha encontrado el usuario.")); 
		BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("No se ha encontrado el libro."));
		
		LoanEntity loanEntity = new LoanEntity(); 
		loanEntity.setUser(userEntity);
		loanEntity.setBook(bookEntity);
	
		if(bookEntity.getStock() > 0) {
			bookEntity.setStock(bookEntity.getStock() - 1);
			bookRepository.save(bookEntity);
			loanRepository.save(loanEntity);			
		} else {
		    throw new RuntimeException("No hay stock disponible para este libro.");
		}
	}
	
	@Override
	public void returnBook(Long loanId) {
		LoanEntity loanEntity = loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("No se ha encontrado el prestamo."));
		
		if(!loanEntity.isReturned()) {
			throw new RuntimeException("El libro ya ha sido devuelto."); 
		}
		
		loanEntity.setReturned(true);
		loanEntity.setReturnDate(LocalDate.now());
		loanRepository.save(loanEntity);
		
		BookEntity bookEntity = loanEntity.getBook();
		bookEntity.setStock(bookEntity.getStock() + 1);
		bookRepository.save(bookEntity);
	}

	@Override
	public List<LoanEntity> findByUserId(Long id) {
		return loanRepository.findByUserId(id); 
	}
	
}
