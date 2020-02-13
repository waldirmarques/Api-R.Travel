package br.com.Rtravel.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.Rtravel.domaim.Parada;
import br.com.Rtravel.services.ParadaService;
import io.swagger.annotations.ApiOperation;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/v1/api/paradas")
@CrossOrigin(origins = "*")
public class ParadaResource {
	
	@Autowired
	private ParadaService service;

	@ApiOperation(value = "Seleciona parada por id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // lista usuário por id
	public ResponseEntity<Parada> find(@PathVariable Long id) {
		Parada obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@ApiOperation(value = "Seleciona todas as paradas do sistema")
	@RequestMapping(method = RequestMethod.GET) // lista todos os usuário
	public ResponseEntity<List<Parada>> findAll() {
		List<Parada> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation(value = "Seleciona parada com paginação")
	@RequestMapping(value = "/page", method = RequestMethod.GET) // lista todas os usuários
	public ResponseEntity<Page<Parada>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Parada> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation(value = "Adiciona nova parada")
	@RequestMapping(method = RequestMethod.POST) // adiciona um novo usuário
	public ResponseEntity<Parada> insert(@Valid @RequestBody Parada objDto) {
		Parada obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Atualiza parada por id")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT) // atualizar uma usuário
	public ResponseEntity<Void> update(@Valid @RequestBody Parada objDto, @PathVariable Long id)
			throws ObjectNotFoundException {

		Parada obj = service.fromDTO(objDto);
		obj.setId(id);
		service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Deleta parada por id")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) // Deleta usuário
	public ResponseEntity<Void> delete(@PathVariable Long id) throws ObjectNotFoundException {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
