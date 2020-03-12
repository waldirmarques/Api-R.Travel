package br.com.Rtravel.resources;

import br.com.Rtravel.domaim.Comentario;
import br.com.Rtravel.services.ComentarioService;
import io.swagger.annotations.ApiOperation;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "v1/api/comentarios")
@CrossOrigin(origins = "*") // Todo dominio pode acessar essa api
public class ComentarioResource {
    
    @Autowired
    private ComentarioService service;
    

    @ApiOperation(value = "Seleciona e retorna um Comentario pelo seu ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET) // lista Comentario por id
    public ResponseEntity<Comentario> find(@PathVariable Long id) {
        Comentario obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @ApiOperation(value = "Lista todas as Comentarios cadastradas no sistema")
    @RequestMapping(method = RequestMethod.GET) // lista todos os Comentarios
    public ResponseEntity<List<Comentario>> findAll() {
        List<Comentario> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = "Lista os Comentarios com paginação")
    @RequestMapping(value = "/page", method = RequestMethod.GET) // lista todas os Comentarios
    public ResponseEntity<Page<Comentario>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                               @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                               @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
                                               @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Comentario> list = service.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = "Adiciona um novo Comentario")
    @RequestMapping(method = RequestMethod.POST) // adiciona um novo Comentario
    public ResponseEntity<Comentario> insert(@Valid @RequestBody Comentario obj) {
        System.out.println(obj);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Atualiza um Comentario pelo seu ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT) // Atualizar um Comentario
    public ResponseEntity<Void> update(@Valid @RequestBody Comentario obj, @PathVariable Long id)
            throws ObjectNotFoundException {
        obj.setId(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Deleta um Comentario pelo seu ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) // Deletar Comentario
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ObjectNotFoundException {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
