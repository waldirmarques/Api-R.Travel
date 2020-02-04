package br.com.Rtravel.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.Rtravel.domaim.User;
import br.com.Rtravel.enums.Perfil;
import br.com.Rtravel.repositories.UserRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UserRepository userRepository;
	

	public void instantiateTestDatabase() throws ParseException{
		
		User userAdmin = new User(null,"MainAdmin","admin@gmail.com",pe.encode("admin"));
		userAdmin.addPerfil(Perfil.ADMIN);
		
		userRepository.saveAll(Arrays.asList(userAdmin));
		
		
	}
}

