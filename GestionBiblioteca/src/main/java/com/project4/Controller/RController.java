package com.project4.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project4.DTO.BookDTO;
import com.project4.DTO.UserDTO;
import com.project4.Entity.BookEntity;
import com.project4.Entity.ERole;
import com.project4.Entity.LoanEntity;
import com.project4.Entity.UserEntity;
import com.project4.Mapper.MapperC;
import com.project4.Service.IBookService;
import com.project4.Service.ILoanService;
import com.project4.Service.IUserService;

@RestController
@RequestMapping("/project4")
public class RController {

	@Autowired
	private IUserService userService; 
	
	@Autowired
	private IBookService bookService; 
	
	@Autowired
	private ILoanService loanService;

	
	// UserEntity
	@PostMapping("/create/admin")
	public ResponseEntity<?> createAdmin(@RequestBody UserDTO userDto){
		try {
			UserEntity userEntity = MapperC.toUserEntity(userDto); 
			userService.createUser(userEntity, ERole.ROLE_ADMIN);
			return ResponseEntity.ok(Map.of("message", "Admin registrado."));
		} catch (Exception e){
			return ResponseEntity.internalServerError().body(Map.of("message", "Ocurrio un error al crear usuario: " + e.getMessage()));
		}
	}
	
	@PostMapping("/create/user")
	public ResponseEntity<?> createUser(@RequestBody UserDTO userDto){
		try {
			UserEntity userEntity = MapperC.toUserEntity(userDto); 
			userService.createUser(userEntity, ERole.ROLE_USER);
			return ResponseEntity.ok(Map.of("message", "Usuario registrado."));
		} catch (Exception e){
			return ResponseEntity.internalServerError().body(Map.of("message", "Ocurrio un error al crear usuario: " + e.getMessage()));
		}
	}
	
	@PostMapping("/create/librarian")
	public ResponseEntity<?> createLibrarian(@RequestBody UserDTO userDto){
		try {
			UserEntity userEntity = MapperC.toUserEntity(userDto); 
			userService.createUser(userEntity, ERole.ROLE_LIBRARIAN);
			return ResponseEntity.ok(Map.of("message", "Bibliotecario/a registrado/a."));
		} catch (Exception e){
			return ResponseEntity.internalServerError().body(Map.of("errorMessage", "Ocurrio un error al crear bibliotecario/a: " + e.getMessage()));
		}
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
		userService.deleteUser(id); 
		return ResponseEntity.ok(Map.of("message", "Usuario elminado."));
	}
	
	@PutMapping("/update/user/{idUser}")
	public ResponseEntity<?> updateUser(@PathVariable("idUser") Long id, @RequestBody UserDTO userDto){
		try { 
			userService.updateUser(id, userDto);
			return ResponseEntity.ok(Map.of("message", "Usuario actualizado."));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(Map.of("errorMessage", "No se ha podido modificar el usuario: " + e.getMessage()));
		}
	}
	
	@PostMapping("/createBook")
	public ResponseEntity<?> createBook(@RequestBody BookDTO bookDto){
		try {
			BookEntity bookEntity = MapperC.toBookEntity(bookDto); 
			bookService.createBook(bookEntity); 
			return ResponseEntity.ok(Map.of("message", "Libro registrado."));
		} catch (Exception e) {
		return ResponseEntity.internalServerError().body(Map.of("errorMessage", "Ocurrio un error al crear libro: " + e.getMessage())); 
		}
	}
	
	@DeleteMapping("/deleteBook/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable("id") Long id){
		bookService.deleteBook(id); 
		
		return ResponseEntity.ok(Map.of("message", "El libro fue eliminado"));
	}
	
	@PutMapping("/update/book/{idBook}")
	public ResponseEntity<?> updateBook(@PathVariable("idBook") Long id, @RequestBody BookDTO bookDto){
		bookService.updateBook(id, bookDto);
		return ResponseEntity.ok(Map.of("message", "El libro ha sido modificado."));	
	}
	
	@GetMapping("/getBook/all")
	public ResponseEntity<List<BookEntity>> getAllBooks(){
		return ResponseEntity.ok(bookService.getAllBooks());
	}
	
	@GetMapping("/getBook/{search}")
	public ResponseEntity<?> getAllBooks(@PathVariable("search") String search){
		try {
			return ResponseEntity.ok(bookService.searchBook(search));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
		}
	}
	
	@PostMapping("/createLoan/{userId}/{bookId}")
	public ResponseEntity<?> createLoan(@PathVariable("userId") Long userId, @PathVariable("bookId") Long bookId){
		try {
			loanService.crateLoan(userId, bookId);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(Map.of("message", "Ocurrio un error al crear el prestamo: " + e.getMessage()));
		}
	}
	
	@GetMapping("/getLoan/{search}")
	public ResponseEntity<List<LoanEntity>> searchLoan(@PathVariable("search") String search){
		return ResponseEntity.ok(loanService.searchLoan(search));			
	}
	
	@GetMapping("/getLoanDate/{date}")
	public ResponseEntity<?> getLoanByUser(@PathVariable("date") LocalDate date){
		return ResponseEntity.ok(loanService.findByLoanDate(date));			
	}
	
	@GetMapping("/requestBook/{userId}")
	public ResponseEntity<?> requestBook(@PathVariable("userId") Long id){
		return ResponseEntity.ok(loanService.findByUserId(id));			
	}
}

