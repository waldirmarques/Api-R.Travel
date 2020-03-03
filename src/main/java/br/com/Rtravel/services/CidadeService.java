package br.com.Rtravel.services;

import br.com.Rtravel.domaim.Cidade;
import br.com.Rtravel.domaim.Rota;
import br.com.Rtravel.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public Cidade find(Long id) {
        Optional<Cidade> obj = cidadeRepository.findById(id);
        return obj.orElseThrow(() -> new br.com.Rtravel.services.exceptions.ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cidade.class.getName()));
    }

    public List<Cidade> findAll(){
        return cidadeRepository.findAll();
    }

    public Page<Cidade> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage , Sort.Direction.valueOf(direction), orderBy);
        return cidadeRepository.findAll(pageRequest);
    }
}
