/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.HackatonDTO;
import co.edu.uniandes.csw.hackatones.dtos.TecnologiaDTO;
import co.edu.uniandes.csw.hackatones.entities.TecnologiaEntity;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author ja.torresl
 */
@Path("tecnologia")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TecnologiaResource {
    
    private final static Logger LOGGER = Logger.getLogger(TecnologiaEntity.class.getName());
    
    @POST
    public TecnologiaDTO createTecnologia(TecnologiaDTO tecnologia){
        return tecnologia;
    }
}
