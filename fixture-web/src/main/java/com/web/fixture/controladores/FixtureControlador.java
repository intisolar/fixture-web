package com.web.fixture.controladores;



import com.web.fixture.entidades.Equipo;
import com.web.fixture.entidades.Usuario;
import com.web.fixture.errores.ErrorServicio;
import com.web.fixture.repositorios.FixtureRepositorio;
import com.web.fixture.repositorios.PartidoGrupoRepositorio;
import com.web.fixture.servicios.FixtureServicio;
import com.web.fixture.servicios.PartidoEliminatorioServicio;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/fixture")
public class FixtureControlador {
    @Autowired
    private FixtureServicio fixtureServicio;
    @Autowired
    private FixtureRepositorio fixtureRepositorio;
    @Autowired
    private PartidoGrupoRepositorio partidoGrupoRepositorio;
    @Autowired
    private PartidoEliminatorioServicio partidoEliminatorioServicio;
    
    @GetMapping("/fixture")  
    public String fixture(HttpSession session , @RequestParam String id) {
        Usuario login= (Usuario) session.getAttribute("usuariosession");
        if (login == null || !login.getIdUsuario().equals(id))  {
        return "redirect:/inicio";}
        else{
            return "fixture.html";}
    }
    
    @GetMapping("/mostrar-Fixture")
    public String mostrarFixture(){
    /*model.put(datos previamente guardado )*/
    
    return "fixture.html";}
    
    
    @PostMapping("/definir-partido")
    public String definirPartidoGrupo(HttpSession session, ModelMap model ,@RequestParam String id, @RequestParam Integer golesEquipo1 , @RequestParam Integer golesEquipo2 ){
        
        /*testear metodos para elegir ganandor de la fase de grupos*/
        /*      persistir datos del usuario
          buscar el fixture del usuario
          traer el partido que corresponde  
        
        */
        
        
        
        System.out.println("id: " + id +"\n" + "||"  +  golesEquipo1 + " || " + golesEquipo2);
        return "redirect:/fixture";
    }
    
    /* MÃ‰TODO GANADOR VA A UTILIZAR UNA QUERY DE EQUIPOREP para buscar el equipo que tiene como fase "ganador". 
    Este mÃ©todo debe ejecutarse una vez que se reciban los datos del Ãºltimo partido. Devuelve un objeto Equipo 
    que es luego mostrado en las vistas. Desde las vistas se podrÃ­a hacer un if (si los campos de fase final 
    estÃ¡n llenos, entonces que llame a este mÃ©todo > /ganador {ganador*/    
//    @GetMapping("/ganador") ///REVER METODO.
//    public Equipo ganador() {
//        
//        try {
//            Equipo equipo = fixtureServicio.encontrarGanador();
//            return equipo;
//        } catch (Exception e) {
//            System.err.print(e.getMessage());
//            
//        }
//        return null;
//    }
    
    @GetMapping("/guardar")
    public String guardar(ModelMap model, @RequestParam Integer idPartido, @RequestParam String letraID, @RequestParam Integer goles1, @RequestParam Integer goles2, Integer complementario1, Integer complementario2, Integer penales1, Integer penales2) throws ErrorServicio {
        
        try {
            
            partidoEliminatorioServicio.guardarPartido(idPartido, letraID, goles1, goles2, complementario1, complementario2, penales1, penales2);
            
            
            return "fixture.html";
            
        } catch (Exception e) {
            
            System.out.println(e);
           
            ErrorServicio err = new ErrorServicio("Los cambios del partido no se guardaron debido a un error. Verifique los datos ingresados e intente guardarlos nuevamente.");
            model.put("error", err);
            
            return "fixture.html";
        }
        
    }
}
        

