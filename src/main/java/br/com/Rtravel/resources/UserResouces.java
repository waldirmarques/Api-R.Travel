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
import io.swagger.annotations.Api;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/user")
@Api(value = "API Rest Rtravel")
@CrossOrigin(origins="*") //Todo dominio pode acessar essa api
public class UserResouces {
	
	@Autowired
	private UserService service;
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> find(@PathVariable Integer id){
		User obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/email", method=RequestMethod.GET)
	public ResponseEntity<User> find(@RequestParam(value="value") String email) {
		User obj = service.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN')") //--> Essa anotação obriga um usuario está logado para acessar esse endpoint
	@RequestMapping(method=RequestMethod.POST) //adiciona uma nova categoria
	public ResponseEntity<Void> insert(@Valid @RequestBody UserDTO objDto){// throws ObjectNotFoundException{
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}",method=RequestMethod.PUT) //atualizar uma categoria
	public ResponseEntity<Void> update(@Valid @RequestBody UserDTO objDto,@PathVariable Integer id) throws ObjectNotFoundException{
		User obj = service.fromDTO(objDto);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	/*
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/password/{id}",method=RequestMethod.PATCH) //update a password ADM
	public ResponseEntity<Void> update(@Valid @RequestBody PasswordDTO passwordDto, @PathVariable Integer id) throws ObjectNotFoundException{
		PasswordDTO objDTO = service.passwordDTO(passwordDto);
		service.update(objDTO);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/name/{id}",method=RequestMethod.PATCH) //update name ADM
	public ResponseEntity<Void> update(@Valid @RequestBody NomeDTO nomeDto, @PathVariable Integer id) throws ObjectNotFoundException{
		NomeDTO objDTO = service.nomeDTO(nomeDto);
		service.update(objDTO);
		return ResponseEntity.noContent().build();
	}
	*/
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/nome/{id}",method=RequestMethod.PATCH) //atualizar uma categoria
	public ResponseEntity<Void> updateName(@Valid @RequestBody UserDTO objDto,@PathVariable Integer id) throws ObjectNotFoundException{
		User obj = service.fromDTO(objDto);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE) //Deleta categoria
	public ResponseEntity<Void> delete(@PathVariable Integer id) throws ObjectNotFoundException {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET) //lista todas as categoria
	public ResponseEntity<List<UserDTO>> findPage() {
		List<User> list = service.findAll();
		List<UserDTO> listDTO = list.stream().map(obj -> new UserDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/page", method = RequestMethod.GET) //lista todas as categoria
	public ResponseEntity<Page<UserDTO>> findAll(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction){
		Page<User> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<UserDTO> listDTO = list.map(obj -> new UserDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}

}