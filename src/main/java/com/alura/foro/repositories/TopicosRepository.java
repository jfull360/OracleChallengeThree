package com.alura.foro.repositories;

import com.alura.foro.entities.Topicos;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JORGE DOMINGUEZ
 */
@Repository
public interface TopicosRepository extends JpaRepository<Topicos, Long> {
    @Query(value = "SELECT p FROM Topicos p WHERE p.titulo = :titulo OR p.mensaje = :mensaje")
    List<Topicos> checkIfExistsByTitleMessage(@Param("titulo") String titulo, @Param("mensaje") String mensaje);
}
