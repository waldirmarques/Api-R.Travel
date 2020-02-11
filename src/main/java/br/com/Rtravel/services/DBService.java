package br.com.Rtravel.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.Rtravel.domaim.Cidade;
import br.com.Rtravel.domaim.Parada;
import br.com.Rtravel.domaim.Rota;
import br.com.Rtravel.domaim.User;
import br.com.Rtravel.enums.Perfil;
import br.com.Rtravel.repositories.CidadeRepository;
import br.com.Rtravel.repositories.ParadaRepository;
import br.com.Rtravel.repositories.RotaRepository;
import br.com.Rtravel.repositories.UserRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RotaRepository rotaRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ParadaRepository paradaRepository;
	

	public void instantiateTestDatabase() throws ParseException{
		
		User userAdmin = new User(null,"MainAdmin","admin@gmail.com",pe.encode("admin"));
		userAdmin.addPerfil(Perfil.ADMIN);
		
		User user1 = new User(null,"Waldir Marques","waldir@gmail.com",pe.encode("normal"));
		user1.addPerfil(Perfil.USER);
		
		userRepository.saveAll(Arrays.asList(userAdmin));
		userRepository.saveAll(Arrays.asList(user1));
	
		Cidade cid1 = new Cidade(null,"Paraiba-PB","Rio Tinto", 83.0, 43.9);
		Cidade cid2 = new Cidade(null,"Paraiba-PB","Baia da Traição", 56.0, 90.9);
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2));
		
		Parada para1 = new Parada(null,"Perto do posto de Rio Tinto","https://www.google.com/search?q=posto+de+rio+tinto", 83.42, 42.23);
		Parada para2 = new Parada(null,"Perto da honda","https://www.google.com/search?q=honda+de+rio+tinto", 83.32, 42.53);
		
		List<Parada> paradas = new ArrayList<>();
		paradas.add(para1);
		paradas.add(para2);
		
		paradaRepository.saveAll(Arrays.asList(para1,para2));



		Rota rota1 = new Rota(null,cid1,cid2,paradas);
		Rota rota2 = new Rota(null,cid2,cid1,paradas);
		
		rotaRepository.saveAll(Arrays.asList(rota1, rota2));
		
		
		
		
		
		
	}
}

