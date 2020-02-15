package br.com.Rtravel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.Rtravel.domaim.Usuario;

@Repository	
public interface UserRepository extends JpaRepository<Usuario, Integer>{
	
	@Transactional(readOnly=true)
    Usuario findByEmail(String email);
	
}