package br.com.Rtravel;

import br.com.Rtravel.domaim.*;
import br.com.Rtravel.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner{

	@Autowired
	CidadeRepository cidadeRepository;

	@Autowired
	ParadaRepository paradaRepository;

	@Autowired
	RotaRepository rotaRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ComentarioRepository comentarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Cidade c1 = new Cidade(null, "Paraíba", "Guarabira", "08.55", "03.10");
		Cidade c2 = new Cidade(null, "Paraíba", "Rio Tinto", "08.50", "03.05");
		Parada p1 = new Parada(null, "Perto dos pk's", "google.com", "05.05", "31.10");

		List<Parada> paradas = new ArrayList<>();
		paradas.add(p1);

		Rota r1 = new Rota(null, c1, c2, paradas);

		Usuario u1 = new Usuario(null, "Chico", "chico@gmail.com", "beterraba");

		Comentario cm1 = new Comentario(null, r1, u1, "Achei lamentável");

		cidadeRepository.saveAll(Arrays.asList(c1, c2));
		paradaRepository.save(p1);
		rotaRepository.save(r1);
		userRepository.save(u1);
		comentarioRepository.save(cm1);
	}

}
