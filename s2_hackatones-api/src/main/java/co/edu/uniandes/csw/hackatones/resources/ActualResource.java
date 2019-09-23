/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.ActualDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author a.pedraza
 */
@Path("actuales")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ActualResource {
     private static final Logger LOGGER = Logger.getLogger(ActualResource.class.getName());
    
    @POST
    public ActualDTO createCatalogo(ActualDTO dto) {
        return dto;
    }
    
}
