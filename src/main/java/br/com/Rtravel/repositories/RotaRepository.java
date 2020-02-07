package br.com.Rtravel.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.Rtravel.domaim.Rota;

@Repository	
public interface RotaRepository extends JpaRepository<Rota, Long>{

	Optional<Rota> findById(Long id);

}
