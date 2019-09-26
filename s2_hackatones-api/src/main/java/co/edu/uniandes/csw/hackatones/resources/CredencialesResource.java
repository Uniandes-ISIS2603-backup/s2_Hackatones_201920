/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.CredencialesDTO;
import co.edu.uniandes.csw.hackatones.ejb.CredencialesLogic;
import co.edu.uniandes.csw.hackatones.entities.CredencialesEntity;
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
 * @author ne.cardenas
 */
@Path("credenciales")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CredencialesResource {
    
    private static final Logger LOGGER = Logger.getLogger(CredencialesResource.class.getName());
    
    @Inject
    private CredencialesLogic logic;
    
    @POST
    public CredencialesDTO createCredenciales(CredencialesDTO dto) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CredencialesResource createCredenciales: input: {0}", dto);
        
        CredencialesEntity entity = dto.toEntity();
        
        CredencialesEntity newEntity = logic.createCredenciales(entity);
        
        CredencialesDTO newDTO = new CredencialesDTO(newEntity);
        LOGGER.log(Level.INFO, "CredencialesResource createCredenciales: output: {0}", newDTO);
        return newDTO;
    }
    
    @GET
    public List<CredencialesDTO> getAllCredenciales() {
        LOGGER.info("CredencialesResource getAllCredenciales: input: void");
        List<CredencialesDTO> listaEditoriales = listEntity2DetailDTO(logic.getAllCredenciales());
        LOGGER.log(Level.INFO, "EditorialResource getEditorials: output: {0}", listaEditoriales);
        return listaEditoriales;
    }
    
    @GET
    @Path("{credencialesId: \\d+}")
    public CredencialesDTO getCredenciales(@PathParam("credencialesId") Long credencialesId) throws WebApplicationException {
        LOGGER.log(Level.INFO, "CredencialesResource getEditorial: input: {0}", credencialesId);
        CredencialesEntity entity = logic.getCredenciales(credencialesId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /editorials/" + credencialesId + " no existe.", 404);
        }
        CredencialesDTO detailDTO = new CredencialesDTO(entity);
        LOGGER.log(Level.INFO, "CredencialesResource getCredenciales: output: {0}", detailDTO);
        return detailDTO;
    }
    
    @PUT
    @Path("{credencialesId: \\d+}")
    public CredencialesDTO updateCredenciales(@PathParam("credencialesId") Long credencialesId, CredencialesDTO credenciales) throws WebApplicationException, Exception {
        LOGGER.log(Level.INFO, "CredencialesResource updateCredenciales: input: id:{0} , credenciales: {1}", new Object[]{credencialesId, credenciales});
        credenciales.setId(credencialesId);
        if (logic.getCredenciales(credencialesId) == null) {
            throw new WebApplicationException("El recurso /credenciales/" + credencialesId + " no existe.", 404);
        }
        
        CredencialesDTO detailDTO = null;
        try
        {
            detailDTO = new CredencialesDTO(logic.updateCredenciales(credencialesId, credenciales.toEntity()));
        }
        catch (Exception e)
        {
            throw e;
        }
        LOGGER.log(Level.INFO, "CredencialesResource updateCredenciales: output: {0}", detailDTO);
        return detailDTO;

    }
    
    @DELETE
    @Path("{credencialesId: \\d+}")
    public void deleteCredenciales(@PathParam("credencialesId") Long credencialesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EditorialResource deleteEditorial: input: {0}", credencialesId);
        if (logic.getCredenciales(credencialesId) == null) {
            throw new WebApplicationException("El recurso /credenciales/" + credencialesId + " no existe.", 404);
        }
        logic.deleteCredenciales(credencialesId);
        LOGGER.info("EditorialResource deleteEditorial: output: void");
    }
    
    private List<CredencialesDTO> listEntity2DetailDTO(List<CredencialesEntity> entityList) {
        List<CredencialesDTO> list = new ArrayList<>();
        for (CredencialesEntity entity : entityList) {
            list.add(new CredencialesDTO(entity));
        }
        return list;
    }
}