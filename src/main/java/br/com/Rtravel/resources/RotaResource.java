package br.com.Rtravel.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
import br.com.Rtravel.dto.RotaDTO;
import br.com.Rtravel.services.RotaService;
import io.swagger.annotations.ApiOperation;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/api.rtravel/v1/rotas")
@CrossOrigin(origins = "*") // Todo dominio pode acessar essa api
public class RotaResource {

	@Autowired
	private RotaService service;

	@ApiOperation(value = "Seleciona rota por id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // lista usuário por id
	public ResponseEntity<Rota> find(@PathVariable Long id) {
		Rota obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@ApiOperation(value = "Seleciona todas as rotas do sistema")
	@RequestMapping(method = RequestMethod.GET) // lista todos os usuário
	public ResponseEntity<List<RotaDTO>> findAll() {
		List<Rota> list = service.findAll();
		List<RotaDTO> listDTO = list.stream().map(obj -> new RotaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@ApiOperation(value = "Seleciona rota com paginação")
	@RequestMapping(value = "/page", method = RequestMethod.GET) // lista todas os usuários
	public ResponseEntity<Page<RotaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Rota> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<RotaDTO> listDTO = list.map(obj -> new RotaDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}

	@ApiOperation(value = "Adiciona nova rota")
	@RequestMapping(method = RequestMethod.POST) // adiciona um novo usuário
	public ResponseEntity<Rota> insert(@Valid @RequestBody RotaDTO objDto) {
		Rota obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Atualiza rota por id")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT) // atualizar uma usuário
	public ResponseEntity<Void> update(@Valid @RequestBody RotaDTO objDto, @PathVariable Long id)
			throws ObjectNotFoundException {

		Rota obj = service.fromDTO(objDto);
		obj.setId(id);
		service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Deleta rota por id")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) // Deleta usuário
	public ResponseEntity<Void> delete(@PathVariable Long id) throws ObjectNotFoundException {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
