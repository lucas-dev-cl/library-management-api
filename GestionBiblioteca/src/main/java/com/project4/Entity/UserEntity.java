package com.project4.Entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private String username;
	private String country; 
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> roles;
	// "user" porque asi lo llamamos en LoanEntity, mappedBy sirve para evitar duplicar columnas y no
	// hacer una columna de loans en la tabla de UserEntity porque ya existe en la tabla LoanEntity
	// esta colección solo sirve para navegar, la columna real ya existe en la otra entidad bajo el atributo "user"
	@OneToMany(mappedBy = "user")
	private Set<LoanEntity> loans; 
	
	public UserEntity() {}
	
	// Podemos usar los sets o los asignamos desde aca, en este caso voy a usar el constructor
	public UserEntity(String username, String country) {
		this.username = username;
		this.country = country;
	}	
	
	public Long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Set<RoleEntity> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	} 
	
	
	
}
