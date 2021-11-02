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
// ENVIAR TODOS LOS DATOS (GOLES, COMPL, PENALES) Y HACER IFS PARA VER CUÁLES DATOS ENVIAMOS. HACER MÉTODO APARTE QUE LLAME A DEFINIR PARTIDO Y LE MANDE LOS DATOS DEPURADOS
        validar(golesEquipo1, golesEquipo2, idPartido);

        Optional<PartidoEliminatorio> demo = partidoRep.findById(idPartido);

        if (demo.isPresent()) {
            PartidoEliminatorio partido = demo.get();

            if (golesEquipo1 > golesEquipo2) { //si gana por goles equipo 1, suma 3 puntos a su puntaje

                partido.getEquipo2().setBaja(partido.getFecha());
                
                cambioFase(partido.getEquipo1());

            } else {

                partido.getEquipo1().setBaja(partido.getFecha());

                cambioFase(partido.getEquipo2());

        }

//            equipoServicio.estadisticaPartido(partido.getEquipo1(), partido.getEquipo2(), golesEquipo1, golesEquipo2); EL DATO QUE NOS LLEGA PUEDE NO SER LOS GOLES, SINO PENALES O COMPLEMENTARIO. LA ESTADÍSTICA SE MANDA DESDE OTRO MÉTODO
            partidoRep.save(partido);
        
        } else {
            throw new ErrorServicio("El partido no se encuentra en la base de datos.");
        }

    }

    public void cambioFase(Equipo equipo) {
        
//Habría que validar algún dato acá???? Me trae una copia del objeto equipo. le seteo la fase.

        if (equipo.getFase().equals("octavos")) {
            equipo.setFase("cuartos");
        } else if (equipo.getFase().equals("cuartos")) {
             equipo.setFase("semifinal");
        }else if (equipo.getFase().equals("semifinal")) {
             equipo.setFase("final");
        }else if (equipo.getFase().equals("final")) {
             equipo.setFase("ganador");
        }
        
        equipoRepositorio.save(equipo); //persisto el equipo en BD
        
//EL SWITCH ES MENOS SEGURO QUE EL IF. CREAR MÉTODO GANADOR
//        switch (equipo.getFase()) {
//            case "octavos":
//                equipo.setFase("semifinal");
//                break;
//
//            case "cuartos":
//                equipo.setFase("semifinal");
//                break;
//
//            case "semifinal":
//                equipo.setFase("final");
//                break;
//            case "final":
//               equipo.setFase("ganador");
//               break;
//            default:
//                break;
//        }

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
