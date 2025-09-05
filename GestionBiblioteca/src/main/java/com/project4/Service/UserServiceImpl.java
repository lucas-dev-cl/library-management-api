package com.project4.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project4.DTO.UserDTO;
import com.project4.Entity.ERole;
import com.project4.Entity.RoleEntity;
import com.project4.Entity.UserEntity;
import com.project4.Repository.RoleRepository;
import com.project4.Repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private RoleRepository roleRepository; 
	
	@Override
	public void createUser(UserEntity userEntity, ERole role) {
		RoleEntity roleEntity = roleRepository.findByRoles(role).orElseThrow(() -> new RuntimeException("No se ha encontrado el rol.")); 
		
		Set<RoleEntity> roles = new HashSet<RoleEntity>(Set.of(roleEntity));
		
		userEntity.setRoles(roles);
		
		userRepository.save(userEntity); 
	}
	
	@Override
	public UserEntity findById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("No se ha encontrado el usuario."));
	}

	@Override
	public void updateUser(Long id, UserDTO userDto) {
		UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("No se ha encontrado el usuario."));
		
		userEntity.setUsername(userDto.getUsername());
		userEntity.setCountry(userDto.getCountry());
		
		userRepository.save(userEntity);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

}
