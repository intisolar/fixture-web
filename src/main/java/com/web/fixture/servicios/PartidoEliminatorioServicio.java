/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.fixture.servicios;

import com.web.fixture.entidades.Equipo;
import com.web.fixture.entidades.PartidoEliminatorio;
import com.web.fixture.errores.ErrorServicio;
import com.web.fixture.repositorios.EquipoRepositorio;
import com.web.fixture.repositorios.PartidoEliminatorioRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidoEliminatorioServicio {

    @Autowired
    private PartidoEliminatorioRepositorio partidoRep;
    @Autowired
    private EquipoServicio equipoServicio;
    @Autowired
    private EquipoRepositorio equipoRepositorio;

    public void paseOctavos() {

    }

    public void definirPartido(Integer golesEquipo1, Integer golesEquipo2, Integer idPartido, Integer golesComplementario1, Integer golesComplementario2, Integer penales1, Integer penales2) throws ErrorServicio {

        validar(golesEquipo1, golesEquipo2, idPartido);

        Optional<PartidoEliminatorio> demo = partidoRep.findById(idPartido);

        if (demo.isPresent()) {
            PartidoEliminatorio partido = demo.get();

            if (golesEquipo1 > golesEquipo2) { //si gana por goles equipo 1, suma 3 puntos a su puntaje

                partido.getEquipo2().setBaja(partido.getFecha());
                
                cambioFase(partido.getEquipo1());

            } else if (golesEquipo1 < golesEquipo2) { //si gana por goles equipo 2, suma 3 puntos a su puntaje

                partido.getEquipo1().setBaja(partido.getFecha());

                cambioFase(partido.getEquipo2());

            } else { //si ninguno gana, es que empataron, suman 1 pto cada uno

            }

            equipoServicio.estadisticaPartido(partido.getEquipo1(), partido.getEquipo2(), golesEquipo1, golesEquipo2);
            partidoRep.save(partido);

        } else {
            throw new ErrorServicio("El partido no se encuentra en la base de datos.");
        }

    }

    public void cambioFase(Equipo equipo) {

        switch (equipo.getFase()) {
            case "octavos":
                equipo.setFase("semifinal");
                break;

            case "cuartos":
                equipo.setFase("semifinal");
                break;

            case "semifinal":
                equipo.setFase("final");
                break;
            default:
                break;
        }

    }

    private void validar(Integer golesEquipo1, Integer golesEquipo2, Integer idPartido) throws ErrorServicio {

        if (golesEquipo1 == null || golesEquipo2 == null) {

            throw new ErrorServicio("Debe completar la cantidad de goles.");
        }

        if (idPartido == null) {
            System.out.println("el ID del partido es nulo.");
            throw new ErrorServicio("Error interno.");
        }

    }
}
