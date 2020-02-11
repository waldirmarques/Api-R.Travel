package br.com.Rtravel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Rtravel.domaim.Parada;
import org.springframework.stereotype.Repository;

@Repository
public interface ParadaRepository extends JpaRepository<Parada, Long>{

}
