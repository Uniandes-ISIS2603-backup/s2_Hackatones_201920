/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.LugarDTO;
import co.edu.uniandes.csw.hackatones.ejb.LugarLogic;
import co.edu.uniandes.csw.hackatones.entities.LugarEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jd.monsalve
 */
@Path("/lugar")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class LugarResource {
    
     private static final Logger LOGGER = Logger.getLogger(LugarResource.class.getName());
     
    @Inject
    private LugarLogic lugarLogic;
     
   @POST
    public LugarDTO createLugar (LugarDTO lugar) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ALugarResource createLugar: input: {0}", lugar);
        LugarDTO lugDTO = new LugarDTO(lugarLogic.createLugar(lugar.toEntity()));
        LOGGER.log(Level.INFO, "LugarResource createLugar: output: {0}", lugDTO);
        return lugDTO;
    }  
    
   @GET
    @Path("{lugarId: \\d+}")
    public LugarDTO getLugar(@PathParam("lugarId") Long lugarId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "LugarResource getLugar: input: {0}", lugarId);
        LugarEntity lugarEntity = lugarLogic.getLugar(lugarId);
        if (lugarEntity == null) {
            throw new WebApplicationException("El recurso /lugar/" + lugarId + " no existe.", 404);
        }
        LugarDTO detailDTO = new LugarDTO(lugarEntity);
        LOGGER.log(Level.INFO, "AuthorResource getAuthor: output: {0}", detailDTO);
        return detailDTO;
    }
    
     @PUT
    @Path("{lugarId: \\d+}")
    public LugarDTO updateLugar(@PathParam("lugarID") Long lugarID, LugarDTO lugar) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "LugarResource updateLugar: input: lugarId: {0} , lugar: {1}", new Object[]{lugarID, lugar});
        lugar.setID(lugarID);
        if (lugarLogic.getLugar(lugarID) == null) {
            throw new WebApplicationException("El recurso /lugar/" + lugarID + " no existe.", 404);
        }
        LugarDTO DTO = new LugarDTO(lugarLogic.updateLugar(lugarID, lugar.toEntity()));
        LOGGER.log(Level.INFO, "LugarResource updateLugar: output: {0}", DTO);
        return DTO;
    }
    
    
}
