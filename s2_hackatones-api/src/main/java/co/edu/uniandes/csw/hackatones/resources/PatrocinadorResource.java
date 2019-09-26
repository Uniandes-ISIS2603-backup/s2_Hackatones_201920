/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.PatrocinadorDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author s.estupinan
 */
@Path("patrocinadores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class PatrocinadorResource {
    
    private static final Logger LOGGER = Logger.getLogger(PatrocinadorResource.class.getName());
    
    @POST
    public PatrocinadorDTO createPatrocinador(PatrocinadorDTO dto) {
        return dto;
    }
}
