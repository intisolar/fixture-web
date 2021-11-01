
package com.web.fixture.repositorios;

import com.web.fixture.entidades.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EquipoRepositorio  extends JpaRepository<Equipo, Integer>{
    
          @Query("SELECT c FROM Equipo c WHERE c.grupo = :grupo ORDER BY c.puntaje")
    public Equipo buscarPorGrupo(@Param("grupo") String grupo);
    
}
