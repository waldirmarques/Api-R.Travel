package br.com.Rtravel.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.Rtravel.domaim.Usuario;
import br.com.Rtravel.repositories.UserRepository;
import br.com.Rtravel.services.exceptions.ObjectNotFoundException;


@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private EmailService emailService;

	private Random rand = new Random();

	public void sendNewPassword(String email) {

		Usuario user = userRepository.findByEmail(email);
		
		if (user == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}

		String newPass = newPassword();
		user.setSenha(pe.encode(newPass));

		userRepository.save(user);
		emailService.sendNewPasswordEmail(user, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { //generate a digit
			return (char) (rand.nextInt(10) + 48);
		}
		else if (opt == 1) { //generate capital letter
			return (char) (rand.nextInt(26) + 65);
		}
		else { //generate lowercase letter
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
