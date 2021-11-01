
package com.web.fixture.servicios;

import com.web.fixture.entidades.Fixture;
import com.web.fixture.entidades.PartidoGrupo;
import com.web.fixture.entidades.Usuario;
import com.web.fixture.errores.ErrorServicio;
import com.web.fixture.repositorios.FixtureRepositorio;
import com.web.fixture.repositorios.UsuarioRepositorio;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.fixture.repositorios.PartidoGrupoRepositorio;


@Service
public class FixtureServicio {
    
//    @Autowired
//    private UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    private FixtureRepositorio fixtureRepositorio;
    
    @Autowired
    private PartidoGrupoRepositorio partidoRepositorio;
    
    @Transactional
    public void creaFixture(String idUsuario) throws ErrorServicio {
        
        //traigo todos los datos de los partidos para crear automaticamente el fixture
        List<PartidoGrupo> listaPartido= partidoRepositorio.findAll();
        
        Fixture fixture= new Fixture();
        //no es necesario buscar el usuario debido que solo utilizamos su id como atributo de la entidad
        fixture.setIdUsuario(idUsuario);
        fixture.setListaPartidos(listaPartido);
        
        
        fixtureRepositorio.save(fixture);
    }
    
    //traer de la base de datos la lista completa de partidos, el usuario modificaria unicamente 
}
