/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.fixture.repositorios;

import com.web.fixture.entidades.PartidoEliminatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoEliminatorioRepositorio extends JpaRepository<PartidoEliminatorio, Integer>{
//        @Query("SELECT * FROM PartidoEliminatorio c WHERE c.fase = :fase ORDER BY c.id_partido asc")
//    public ArrayList<PartidoEliminatorio> buscarPartidosxFase(@Param("fase") String grupo);
}
