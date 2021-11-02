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

   /*
      public void paseOctavos() {
        ArrayList<Equipo> listaGrupoA=  equipoRepositorio.buscarPorGrupo("grupo A");
        ArrayList<Equipo> listaGrupoB=  equipoRepositorio.buscarPorGrupo("grupo B");
        ArrayList<Equipo> listaGrupoC=  equipoRepositorio.buscarPorGrupo("grupo C");
        ArrayList<Equipo> listaGrupoD=  equipoRepositorio.buscarPorGrupo("grupo D");
        ArrayList<Equipo> listaGrupoE=  equipoRepositorio.buscarPorGrupo("grupo E");
        ArrayList<Equipo> listaGrupoF=  equipoRepositorio.buscarPorGrupo("grupo F");
        ArrayList<Equipo> listaGrupoG=  equipoRepositorio.buscarPorGrupo("grupo G");
        ArrayList<Equipo> listaGrupoH=  equipoRepositorio.buscarPorGrupo("grupo H");

        ArrayList<PartidoEliminatorio> PartidosOctavos = partidoElimiRepositorio.buscarPartidosxFase("Octavos");

        for (PartidoEliminatorio Partido : PartidosOctavos) { 
            if (Partido.getIdPartido() == 1) {
                Partido.setEquipo1(listaGrupoA.get(0));
                Partido.setEquipo2(listaGrupoB.get(1));
                cambioFaseOctavos(listaGrupoA.get(0));
                cambioFaseOctavos(listaGrupoB.get(1));
                partidoElimiRepositorio.save(Partido);
            }
            if (Partido.getIdPartido() == 2) {
                Partido.setEquipo1(listaGrupoC.get(0));
                Partido.setEquipo2(listaGrupoD.get(1));
                cambioFaseOctavos(listaGrupoC.get(0));
                cambioFaseOctavos(listaGrupoD.get(1));
                partidoElimiRepositorio.save(Partido);
            }
            if (Partido.getIdPartido() == 3) {
                Partido.setEquipo1(listaGrupoE.get(0));
                Partido.setEquipo2(listaGrupoF.get(1));
                cambioFaseOctavos(listaGrupoE.get(0));
                cambioFaseOctavos(listaGrupoF.get(1));
                partidoElimiRepositorio.save(Partido);
            }
            if (Partido.getIdPartido() == 4) {
                Partido.setEquipo1(listaGrupoG.get(0));
                Partido.setEquipo2(listaGrupoH.get(1));
                cambioFaseOctavos(listaGrupoG.get(0));
                cambioFaseOctavos(listaGrupoH.get(1));
                partidoElimiRepositorio.save(Partido);
            }
            if (Partido.getIdPartido() == 5) {
                Partido.setEquipo1(listaGrupoB.get(0));
                Partido.setEquipo2(listaGrupoA.get(1));
                cambioFaseOctavos(listaGrupoB.get(0));
                cambioFaseOctavos(listaGrupoA.get(1));
                partidoElimiRepositorio.save(Partido);
            }
            if (Partido.getIdPartido() == 6) {
                Partido.setEquipo1(listaGrupoD.get(0));
                Partido.setEquipo2(listaGrupoC.get(1));
                cambioFaseOctavos(listaGrupoD.get(0));
                cambioFaseOctavos(listaGrupoC.get(1));
                partidoElimiRepositorio.save(Partido);
            }
            if (Partido.getIdPartido() == 7) {
                Partido.setEquipo1(listaGrupoF.get(0));
                Partido.setEquipo2(listaGrupoE.get(1));
                cambioFaseOctavos(listaGrupoF.get(0));
                cambioFaseOctavos(listaGrupoE.get(1));
                partidoElimiRepositorio.save(Partido);
            }
            if (Partido.getIdPartido() == 8) {
                Partido.setEquipo1(listaGrupoH.get(0));
                Partido.setEquipo2(listaGrupoG.get(1));
                cambioFaseOctavos(listaGrupoH.get(0));
                cambioFaseOctavos(listaGrupoG.get(1));
                partidoElimiRepositorio.save(Partido);
            }
        } 
    }

    public void paseCuartos(){
        //partidos del 9 al 12 el ID
       ArrayList<PartidoEliminatorio> PartidosCuartos = partidoElimiRepositorio.buscarPartidosxFase("Cuartos");



    }

    public void cambioFaseOctavos(Equipo equipo) {
         equipo.setFase("Octavos");
         equipoRepositorio.save(equipo);   
    }
    */

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

    /*
     public void definirPartido(Integer golesEquipo1, Integer golesEquipo2, Integer idPartido, Integer golesComplementario1, Integer golesComplementario2, Integer penales1, Integer penales2) throws ErrorServicio {

        validar(golesEquipo1, golesEquipo2, idPartido);

        Optional<PartidoEliminatorio> demo = partidoRep.findById(idPartido);

        if (demo.isPresent()) {
            PartidoEliminatorio partido = demo.get();

            if (golesEquipo1 > golesEquipo2) { //si gana por goles equipo 1, suma 3 puntos a su puntaje
                partido.getEquipo2().setBaja(partido.getFecha());      
                cambioFaseEliminatoria(partido.getEquipo1());

            } else if (golesEquipo1 < golesEquipo2) { //si gana por goles equipo 2, suma 3 puntos a su puntaje
                partido.getEquipo1().setBaja(partido.getFecha());
               cambioFaseEliminatoria(partido.getEquipo2());

            } else if (golesEquipo1 == golesEquipo2) {

                if (golesComplementario1 > golesComplementario2) {
                    partido.getEquipo2().setBaja(partido.getFecha());
                    cambioFaseEliminatoria(partido.getEquipo1());

                }else if (golesComplementario1 < golesComplementario2) {
                    partido.getEquipo1().setBaja(partido.getFecha());
                    cambioFaseEliminatoria(partido.getEquipo2());

                }else if (golesComplementario1 == golesComplementario2) {

                    if (penales1 > penales2) {
                    partido.getEquipo2().setBaja(partido.getFecha());
                    cambioFaseEliminatoria(partido.getEquipo1());
                    }else{
                    partido.getEquipo1().setBaja(partido.getFecha());
                    cambioFaseEliminatoria(partido.getEquipo2());
                    }
                }
            }
            partidoRep.save(partido);
        } else {
            throw new ErrorServicio("El partido no se encuentra en la base de datos.");
        }
    }
    */
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
