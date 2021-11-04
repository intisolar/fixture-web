package com.web.fixture.servicios;

import com.web.fixture.entidades.Equipo;
import com.web.fixture.entidades.Fixture;
import com.web.fixture.entidades.PartidoEliminatorio;
import com.web.fixture.entidades.PartidoGrupo;
import com.web.fixture.entidades.Usuario;
import com.web.fixture.errores.ErrorServicio;
import com.web.fixture.repositorios.EquipoRepositorio;
import com.web.fixture.repositorios.FixtureRepositorio;
import com.web.fixture.repositorios.PartidoEliminatorioRepositorio;
import com.web.fixture.repositorios.UsuarioRepositorio;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.fixture.repositorios.PartidoGrupoRepositorio;
import java.util.Optional;

@Service
public class FixtureServicio {

//    @Autowired
//    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private FixtureRepositorio fixtureRepositorio;

    @Autowired
    private PartidoGrupoRepositorio partidoRepositorio;

    @Autowired
    private PartidoEliminatorioRepositorio partidoEliminatorioRepositorio;

    @Autowired
    private EquipoRepositorio equipoRepositorio;
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public Fixture creaFixture(String idUsuario) throws ErrorServicio {

        //traigo todos los datos de los partidos para crear automaticamente el fixture
        List<PartidoGrupo> listaPartido = partidoRepositorio.findAll();
        List<PartidoEliminatorio> listaPartidoEliminatorio = partidoEliminatorioRepositorio.findAll();

        Fixture fixture = new Fixture();
        //no es necesario buscar el usuario debido que solo utilizamos su id como atributo de la entidad
        fixture.setIdUsuario(idUsuario);
        fixture.setListaPartidosGrupos(listaPartido);
        fixture.setListaPartidosEliminatorio(listaPartidoEliminatorio); //Al tener dos entidades diferentes hay que setear 
        
        
        fixtureRepositorio.save(fixture);
        
        System.err.println("///////////////////////////////////////////");
        System.out.println(fixture.getId());
        //cambie el metodo para que retorne un fixture y aprovechar en registrar usuario
        //lo llamo en registrar y realizo todos los seteos

        
        return fixture;
    }

    public Equipo encontrarGanador() throws ErrorServicio {

        Optional<Equipo> rta = equipoRepositorio.findById(equipoRepositorio.getIdGanador());

        if (rta.isPresent()) {
            Equipo equipo = rta.get();
            return equipo;
            
        } else {
            throw new ErrorServicio("No hay un equipo ganador todavía. Cargue los resultados de los partidos anteriores.");

        }

    }
    //traer de la base de datos la lista completa de partidos, el usuario modificaria unicamente 
}


