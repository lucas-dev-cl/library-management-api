package com.project4.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project4.Entity.ERole;
import com.project4.Entity.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>{

	public Optional<RoleEntity> findByRoles(ERole role); 
	
}
