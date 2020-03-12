package br.com.Rtravel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.Rtravel.domaim.Rota;
import br.com.Rtravel.repositories.RotaRepository;
import br.com.Rtravel.services.exceptions.ObjectNotFoundException;

@Service
public class RotaService {

	//@Autowired 
	//private BCryptPasswordEncoder pe; // usado para criptrografar a senha
	
	@Autowired //Essa anotação faz com que o objeto seja estanciado assim como está.
	private RotaRepository repo;

	public Rota find(Long id) throws ObjectNotFoundException {		
		Optional<Rota> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Rota.class.getName()));
	}
	
	public List<Rota> findAll() {
		return repo.findAll();
	}

	@Transactional
	public Rota insert(Rota obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Rota update(Rota obj){
		find(obj.getId()); //verifica se o objeto existe
		return repo.save(obj);
	}

	public void delete(Long id){
		find(id);
		repo.deleteById(id);

	}
	
	public Page<Rota> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage , Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

}
