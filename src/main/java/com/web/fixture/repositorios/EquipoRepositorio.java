
package com.web.fixture.repositorios;

import com.web.fixture.entidades.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EquipoRepositorio  extends JpaRepository<Equipo, Integer>{
    
          @Query("SELECT c FROM Equipo c WHERE c.grupo = :grupo ORDER BY c.puntaje")
    public Equipo buscarPorGrupo(@Param("grupo") String grupo); //Esto no tendría que devolver una lista y no solo un equipo??
    
    @Query("SELECT c.id FROM Equipo c WHERE c.fase = 'ganador' ") //Chequear esta QUERY CUANDO TENGAMOS LAS VISTAS O CARGADA LA BD
    public Integer getIdGanador();
    
}
