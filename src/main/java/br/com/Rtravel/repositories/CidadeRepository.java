package br.com.Rtravel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.Rtravel.domaim.Cidade;

@Repository		//nome da classe que vc quer do bando mais o tipo da chave dele.
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
