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

import br.com.Rtravel.domaim.Usuario;
import br.com.Rtravel.services.UserService;
import io.swagger.annotations.ApiOperation;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/v1/api/user")
@CrossOrigin(origins = "*") // Todo dominio pode acessar essa api
public class UserResouces {

	@Autowired
	private UserService service;

	@ApiOperation(value = "Seleciona usuário por id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // lista usuário por id
	public ResponseEntity<Usuario> find(@PathVariable final Integer id) {
		final Usuario obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	// @ApiOperation(value = "Seleciona usuário por email")
	// @RequestMapping(value="/email", method=RequestMethod.GET) //lista usuário por
	// email
	// public ResponseEntity<Usuario> find(@RequestParam(value="value") String
	// email) {
	// Usuario obj = service.findByEmail(email);
	// return ResponseEntity.ok().body(obj);
	// }

	@ApiOperation(value = "Lista todos os usuários cadastradas no sistema")
	@RequestMapping(method = RequestMethod.GET) // lista todos os usuário
	public ResponseEntity<List<Usuario>> findPage() {
		final List<Usuario> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation(value = "Seleciona usuários com paginação")
	@RequestMapping(value = "/page", method = RequestMethod.GET) // lista todas os usuários
	public ResponseEntity<Page<Usuario>> findAll(@RequestParam(value = "page", defaultValue = "0") final Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") final Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") final String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") final String direction) {
		final Page<Usuario> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation(value = "Adiciona novo usuário")
	@RequestMapping(method = RequestMethod.POST) // adiciona um novo usuário
	public ResponseEntity<Void> insert(@Valid @RequestBody final Usuario obj) {
		service.insert(obj);
		final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Atualiza um usuário pelo seu ID")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT) // atualizar uma usuário
	public ResponseEntity<Void> update(@Valid @RequestBody Usuario obj, @PathVariable final Integer id)
			throws ObjectNotFoundException {
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Deleta um usuário pelo seu ID")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) // Deleta usuário
	public ResponseEntity<Void> delete(@PathVariable final Integer id) throws ObjectNotFoundException {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}