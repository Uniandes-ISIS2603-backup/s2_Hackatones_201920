/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.ProximaDetailDTO;
import co.edu.uniandes.csw.hackatones.dtos.ProximaDTO;
import co.edu.uniandes.csw.hackatones.ejb.ProximaLogic;
import co.edu.uniandes.csw.hackatones.entities.ProximaEntity;
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
 * @author a.pedraza
 */
@Path("proximas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ProximaResource {
      private static final Logger LOGGER = Logger.getLogger(ProximaResource.class.getName());
    
    @Inject
    private ProximaLogic proximaLogic;
     
    @POST
    public ProximaDTO createCatalogo(ProximaDTO dto) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProximaResource createProxima: input: {0}", dto);
        ProximaDTO proximaDTO = new ProximaDTO(proximaLogic.createProxima(dto.toEntity()));
        LOGGER.log(Level.INFO, "ProximaResource createProxima: output: {0}", proximaDTO);
        return proximaDTO;
    }
    
    @GET
    public List<ProximaDetailDTO> getProximas() {
        LOGGER.info("ProximaResource getProximas: input: void");
        List<ProximaDetailDTO> listaProximas = listEntity2DTO(proximaLogic.getProximas());
        LOGGER.log(Level.INFO, "ProximaResource getProximas: output: {0}", listaProximas);
        return listaProximas;
    }
    
    @GET
    @Path("{proximasId: \\d+}")
    public ProximaDetailDTO getProxima(@PathParam("proximasId") Long proximasId) {
        LOGGER.log(Level.INFO, "ProximaResource getProxima: input: {0}", proximasId);
        ProximaEntity proximaEntity = proximaLogic.getProxima(proximasId);
        if (proximaEntity == null) {
            throw new WebApplicationException("El recurso /proximas/" + proximasId + " no existe.", 404);
        }
        ProximaDetailDTO detailDTO = new ProximaDetailDTO(proximaEntity);
        LOGGER.log(Level.INFO, "ProximaResource getProxima: output: {0}", detailDTO);
        return detailDTO;
    }
    
    @PUT
    @Path("{proximasId: \\d+}")
    public ProximaDetailDTO updateProxima(@PathParam("proximasId") Long proximasId, ProximaDetailDTO proxima) {
        LOGGER.log(Level.INFO, "ProximaResource updateProxima: input: proximasId: {0} , proxima: {1}", new Object[]{proximasId, proxima});
        proxima.setId(proximasId);
        if (proximaLogic.getProxima(proximasId) == null) {
            throw new WebApplicationException("El recurso /proximas/" + proximasId + " no existe.", 404);
        }
        ProximaDetailDTO detailDTO = new ProximaDetailDTO(proximaLogic.updateProxima(proximasId, proxima.toEntity()));
        LOGGER.log(Level.INFO, "ProximaResource updateProxima: output: {0}", detailDTO);
        return detailDTO;
    }

    @DELETE
    @Path("{proximasId: \\d+}")
    public void deleteProxima(@PathParam("proximasId") Long proximasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProximaResource deleteProxima: input: {0}", proximasId);
        if (proximaLogic.getProxima(proximasId) == null) {
            throw new WebApplicationException("El recurso /proximas/" + proximasId + " no existe.", 404);
        }
        proximaLogic.deleteProxima(proximasId);
        LOGGER.info("ProximaResource deleteProxima: output: void");
    }
    
    private List<ProximaDetailDTO> listEntity2DTO(List<ProximaEntity> entityList) {
        List<ProximaDetailDTO> list = new ArrayList<>();
        for (ProximaEntity entity : entityList) {
            list.add(new ProximaDetailDTO(entity));
        }
        return list;
    }
}
