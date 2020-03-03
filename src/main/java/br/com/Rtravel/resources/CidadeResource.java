package br.com.Rtravel.resources;

import br.com.Rtravel.domaim.Cidade;
import br.com.Rtravel.services.CidadeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/api/cidades")
@CrossOrigin(origins = "*")
public class CidadeResource {

    @Autowired
    private CidadeService service;


    @ApiOperation(value = "Seleciona rota por id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cidade> find(@PathVariable Long id){
        Cidade obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @ApiOperation(value = "Seleciona todas as rotas do sistema")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Cidade>> findAll(){
        List<Cidade> listObj = service.findAll();
        return ResponseEntity.ok().body(listObj);
    }

    @ApiOperation(value = "Seleciona rota com paginação")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<Cidade>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
        @RequestParam(value = "orderBy", defaultValue = "cidadeOrigem") String orderBy,
        @RequestParam(value = "direction", defaultValue = "ASC") String direction){

        Page<Cidade> listPage = service.findPage(page,linesPerPage,orderBy,direction);
        return ResponseEntity.ok().body(listPage);
    }
}
