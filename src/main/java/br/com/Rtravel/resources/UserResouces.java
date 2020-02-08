package br.com.Rtravel.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import br.com.Rtravel.domaim.User;
import br.com.Rtravel.dto.UserDTO;
import br.com.Rtravel.services.UserService;
import io.swagger.annotations.ApiOperation;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/api/v1/user")
@CrossOrigin(origins="*") //Todo dominio pode acessar essa api
public class UserResouces {
	
	@Autowired
	private UserService service;
	
	
	@ApiOperation(value = "Seleciona usuário por id")
	@RequestMapping(value="/{id}", method = RequestMethod.GET) //lista usuário por id
	public ResponseEntity<User> find(@PathVariable Integer id){
		User obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@ApiOperation(value = "Seleciona usuário por email")
	@RequestMapping(value="/email", method=RequestMethod.GET) //lista usuário por email
	public ResponseEntity<User> find(@RequestParam(value="value") String email) {
		User obj = service.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	@ApiOperation(value = "Seleciona todos os usuários do sistema")
	@RequestMapping(method = RequestMethod.GET) //lista todos os usuário
	public ResponseEntity<List<UserDTO>> findPage() {
		List<User> list = service.findAll();
		List<UserDTO> listDTO = list.stream().map(obj -> new UserDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@ApiOperation(value = "Seleciona usuário com paginação")
	@RequestMapping(value = "/page", method = RequestMethod.GET) //lista todas os usuários
	public ResponseEntity<Page<UserDTO>> findAll(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction){
		Page<User> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<UserDTO> listDTO = list.map(obj -> new UserDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
	
	@ApiOperation(value = "Adiciona novo usuário")
	@RequestMapping(method=RequestMethod.POST) //adiciona um novo usuário
	public ResponseEntity<Void> insert(@Valid @RequestBody UserDTO objDto){
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Atualiza usuário por id")
	@RequestMapping(value="/{id}",method=RequestMethod.PUT) //atualizar uma usuário
	public ResponseEntity<Void> update(@Valid @RequestBody UserDTO objDto,@PathVariable Integer id) throws ObjectNotFoundException{
		User obj = service.fromDTO(objDto);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Deleta usuário por id")
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE) //Deleta usuário
	public ResponseEntity<Void> delete(@PathVariable Integer id) throws ObjectNotFoundException {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}