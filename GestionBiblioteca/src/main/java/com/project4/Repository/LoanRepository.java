package com.project4.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project4.Entity.BookEntity;
import com.project4.Entity.LoanEntity;
import com.project4.Entity.UserEntity;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long>{

	public Optional<LoanEntity> findById(Long id); 
	
	public Optional<LoanEntity> findByUser(UserEntity userEntity); 
	
	public List<LoanEntity> findByUserUsernameContainingIgnoreCase(String username);
	
	public Optional<LoanEntity> findByBook(BookEntity bookEntity);
	
	public List<LoanEntity> findByBookTitleContainingIgnoreCase(String title);
	
	public List<LoanEntity> findByLoanDate(LocalDate date); 
	
	public List<LoanEntity> findByUserId(Long id);
}
