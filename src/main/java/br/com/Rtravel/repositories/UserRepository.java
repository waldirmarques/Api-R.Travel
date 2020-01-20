package br.com.Rtravel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.Rtravel.domaim.User;

@Repository	
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Transactional(readOnly=true)
	User findByEmail(String email);
	
}