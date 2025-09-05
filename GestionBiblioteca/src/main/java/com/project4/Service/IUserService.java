package com.project4.Service;


import com.project4.DTO.UserDTO;
import com.project4.Entity.ERole;
import com.project4.Entity.UserEntity;

public interface IUserService {

	public void createUser(UserEntity userEntity, ERole roles);

	public UserEntity findById(Long id);
	
	public void updateUser(Long id, UserDTO userDto); 
	
	public void deleteUser(Long id); 

}
