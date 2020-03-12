package br.com.Rtravel.services;

import br.com.Rtravel.domaim.Comentario;
import br.com.Rtravel.repositories.ComentarioRepository;
import br.com.Rtravel.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository repo;

    public Comentario find(Long id) throws ObjectNotFoundException {
        Optional<Comentario> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Comentario.class.getName()));
    }

    public List<Comentario> findAll() {
        return repo.findAll();
    }

    @Transactional
    public Comentario insert(Comentario obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Comentario update(Comentario obj){
        find(obj.getId()); //verifica se o objeto existe
        return repo.save(obj);
    }

    public void delete(Long id){
        find(id);
        repo.deleteById(id);

    }

    public Page<Comentario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage , Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

}
