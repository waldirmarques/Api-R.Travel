package br.com.Rtravel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.Rtravel.domaim.Parada;
import br.com.Rtravel.repositories.ParadaRepository;
import br.com.Rtravel.services.exceptions.DataIntegrityException;
import br.com.Rtravel.services.exceptions.ObjectNotFoundException;

@Service
public class ParadaService {

	
	@Autowired
	private ParadaRepository repo;
	
	public Parada find(Long id) throws ObjectNotFoundException {		
		Optional<Parada> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Parada.class.getName()));
	}
	
	public List<Parada> findAll() {
		return repo.findAll();
	}

	@Transactional
	public Parada insert(Parada obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Parada update(Parada obj){
		find(obj.getId()); //verifica se o objeto existe
		return repo.save(obj);
	}

	public void delete(Long id){
		find(id);
		repo.deleteById(id);
	}
	
	public Page<Parada> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage , Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Parada fromDTO(Parada obj) {
		return new Parada(obj.getId(),obj.getPontoReferencia(),obj.getUrl(), obj.getLatitude(),obj.getLongitude());
	}


}
