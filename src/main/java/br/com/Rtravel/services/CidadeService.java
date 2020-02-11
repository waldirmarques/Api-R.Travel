package br.com.Rtravel.services;

import br.com.Rtravel.domaim.Cidade;
import br.com.Rtravel.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

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

    public void delete(Long id) {
        try {
            cidadeRepository.deleteById(id);
        }catch (DataIntegrityViolationException e) {
            throw new br.com.Rtravel.services.exceptions.DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
        }
    }

    public Cidade update(Cidade cidade) {
        Cidade novaCidade = find(cidade.getId());
        atualizarCidade(novaCidade, cidade);
        return cidadeRepository.save(novaCidade);
    }

    public Cidade insert(Cidade cidade){
        cidade.setId(null);
        return cidadeRepository.save(cidade);
    }

    private void atualizarCidade(Cidade novaCidade, Cidade cidade){
        novaCidade.setNome(cidade.getNome());
        novaCidade.setEstado(cidade.getEstado());
        novaCidade.setLatitude(cidade.getLatitude());
        novaCidade.setLongitude(cidade.getLongitude());
        novaCidade.setRotasDestino(cidade.getRotasDestino());
        novaCidade.setRotasOrigem(cidade.getRotasOrigem());
    }
}
