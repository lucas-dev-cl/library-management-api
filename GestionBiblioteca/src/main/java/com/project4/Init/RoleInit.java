package com.project4.Init;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.project4.Entity.ERole;
import com.project4.Entity.RoleEntity;
import com.project4.Repository.RoleRepository;

// Es un ejecutador como el main, pero se ejecuta despues de que spring
// inicializa todos los beans y prepara el contexto de la aplicación.
public class RoleInit implements CommandLineRunner{

	@Autowired 
	private RoleRepository roleRepository; 
	
	@Override
	public void run(String... args) throws Exception {
		// Recorremos los valores de ERole
		// for(ERole role : ERole.values()) {
			// Buscamos si el rol existe, si no es asi entonces se ejecuta el valor dentro de "orElseGet"
		 	// roleRepository.findByRoles(role).orElseGet(() -> roleRepository.save(new RoleEntity(role)));
		//	System.out.println("Roles cargados.");
		// }
		
		List<ERole> roles = Arrays.asList(ERole.values()); 
		roles.stream().forEach(
					role -> roleRepository.findByRoles(role)
					.orElseGet(() -> roleRepository.save(new RoleEntity(role)))
				);
	}
}

// orElseThrow "lanza una excepcion si no hay valor"
// orElseGet "Ejecuta otro codigo para un valor alternativo"