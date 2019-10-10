/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.ActualDTO;
import co.edu.uniandes.csw.hackatones.dtos.ActualDetailDTO;
import co.edu.uniandes.csw.hackatones.ejb.ActualLogic;
import co.edu.uniandes.csw.hackatones.entities.ActualEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @actual a.pedraza
 */
@Path("actuales")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ActualResource {
     private static final Logger LOGGER = Logger.getLogger(ActualResource.class.getName());
    
    @Inject
    private ActualLogic actualLogic;
     
    @POST
    public ActualDTO createCatalogo(ActualDTO dto) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ActualResource createActual: input: {0}", dto);
        ActualDTO actualDTO = new ActualDTO(actualLogic.createActual(dto.toEntity()));
        LOGGER.log(Level.INFO, "ActualResource createActual: output: {0}", actualDTO);
        return actualDTO;
    }
    
    @GET
    public List<ActualDetailDTO> getActuales() {
        LOGGER.info("ActualResource getActuales: input: void");
        List<ActualDetailDTO> listaActuales = listEntity2DTO(actualLogic.getActuales());
        LOGGER.log(Level.INFO, "ActualResource getActuales: output: {0}", listaActuales);
        return listaActuales;
    }
    
    @GET
    @Path("{actualesId: \\d+}")
    public ActualDetailDTO getActual(@PathParam("actualesId") Long actualesId) {
        LOGGER.log(Level.INFO, "ActualResource getActual: input: {0}", actualesId);
        ActualEntity actualEntity = actualLogic.getActual(actualesId);
        if (actualEntity == null) {
            throw new WebApplicationException("El recurso /actuales/" + actualesId + " no existe.", 404);
        }
        ActualDetailDTO detailDTO = new ActualDetailDTO(actualEntity);
        LOGGER.log(Level.INFO, "ActualResource getActual: output: {0}", detailDTO);
        return detailDTO;
    }
    
    @PUT
    @Path("{actualsId: \\d+}")
    public ActualDetailDTO updateActual(@PathParam("actualesId") Long actualesId, ActualDetailDTO actual) {
        LOGGER.log(Level.INFO, "ActualResource updateActual: input: actualesId: {0} , actual: {1}", new Object[]{actualesId, actual});
        actual.setId(actualesId);
        if (actualLogic.getActual(actualesId) == null) {
            throw new WebApplicationException("El recurso /actuales/" + actualesId + " no existe.", 404);
        }
        ActualDetailDTO detailDTO = new ActualDetailDTO(actualLogic.updateActual(actualesId, actual.toEntity()));
        LOGGER.log(Level.INFO, "ActualResource updateActual: output: {0}", detailDTO);
        return detailDTO;
    }

    @DELETE
    @Path("{actualesId: \\d+}")
    public void deleteActual(@PathParam("actualesId") Long actualesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ActualResource deleteActual: input: {0}", actualesId);
        if (actualLogic.getActual(actualesId) == null) {
            throw new WebApplicationException("El recurso /actuales/" + actualesId + " no existe.", 404);
        }
        actualLogic.deleteActual(actualesId);
        LOGGER.info("ActualResource deleteActual: output: void");
    }
    
    private List<ActualDetailDTO> listEntity2DTO(List<ActualEntity> entityList) {
        List<ActualDetailDTO> list = new ArrayList<>();
        for (ActualEntity entity : entityList) {
            list.add(new ActualDetailDTO(entity));
        }
        return list;
    }
}
