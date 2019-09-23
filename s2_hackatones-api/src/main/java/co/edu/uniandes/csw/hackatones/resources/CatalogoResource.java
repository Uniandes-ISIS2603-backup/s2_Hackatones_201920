/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.CatalogoDTO;
import co.edu.uniandes.csw.hackatones.dtos.CredencialesDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author ne.cardenas
 */
@Path("catalogos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CatalogoResource {
    private static final Logger LOGGER = Logger.getLogger(CatalogoResource.class.getName());
    
    @POST
    public CatalogoDTO createCatalogo(CatalogoDTO dto) {
        return dto;
    }
}
