package com.project4.DTO;

public class UserDTO {

	private String username;
	private String country;
	
	public UserDTO() {}
	
	public UserDTO(String username, String country) {
		super();
		this.username = username;
		this.country = country;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
}
