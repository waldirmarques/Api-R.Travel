package br.com.Rtravel.repositories;

import br.com.Rtravel.domaim.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
