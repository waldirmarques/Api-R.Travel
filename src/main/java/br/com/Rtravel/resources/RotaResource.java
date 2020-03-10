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

import br.com.Rtravel.domaim.Rota;
import br.com.Rtravel.services.RotaService;
import io.swagger.annotations.ApiOperation;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/v1/api/rotas")
@CrossOrigin(origins = "*") // Todo dominio pode acessar essa api
public class RotaResource {

	@Autowired
	private RotaService service;

	@ApiOperation(value = "Seleciona e retorna uma rota de ônibus pelo seu ID")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // lista rota por id
	public ResponseEntity<Rota> find(@PathVariable Long id) {
		Rota obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@ApiOperation(value = "Lista todas as rotas cadastradas no sistema")
	@RequestMapping(method = RequestMethod.GET) // lista todos os rota
	public ResponseEntity<List<Rota>> findAll() {
		List<Rota> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation(value = "Lista as rotas de ônibus com paginação")
	@RequestMapping(value = "/page", method = RequestMethod.GET) // lista todas os rotas
	public ResponseEntity<Page<Rota>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "cidadeOrigem") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Rota> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation(value = "Adiciona uma nova rota de ônibus")
	@RequestMapping(method = RequestMethod.POST) // adiciona um novo rota
	public ResponseEntity<Rota> insert(@Valid @RequestBody Rota obj) {
		System.out.println(obj);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Atualiza uma rota de ônibus pelo seu ID")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT) // atualizar uma rota
	public ResponseEntity<Void> update(@Valid @RequestBody Rota obj, @PathVariable Long id)
			throws ObjectNotFoundException {
		obj.setId(id);
		service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Deleta uma rota de ônibus pelo seu ID")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) // Deleta rota
	public ResponseEntity<Void> delete(@PathVariable Long id) throws ObjectNotFoundException {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
