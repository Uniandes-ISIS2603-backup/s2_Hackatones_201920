/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.CalificacionDTO;
import co.edu.uniandes.csw.hackatones.ejb.CalificacionLogic;
import co.edu.uniandes.csw.hackatones.entities.CalificacionEntity;
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
@Path("calificaciones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CalificacionResource {
    private static final Logger LOGGER = Logger.getLogger(CalificacionResource.class.getName());
     
    @Inject
    private CalificacionLogic calificacionLogic;
    
    @POST
    public CalificacionDTO createCalificacion(@PathParam("hackatonesId") Long hackatonesId, CalificacionDTO calificacion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CalificacionResource createCalificacion: input: {0}", calificacion);
        CalificacionDTO nuevoCalificacionDTO = new CalificacionDTO(calificacionLogic.createCalificacion(hackatonesId, calificacion.toEntity()));
        LOGGER.log(Level.INFO, "CalificacionResource createCalificacion: output: {0}", nuevoCalificacionDTO);
        return nuevoCalificacionDTO;
    }
    
    @GET
    public List<CalificacionDTO> getCalificaciones(@PathParam("hackatonesId") Long hackatonId) {
        LOGGER.log(Level.INFO, "CalificacionResource getCalificaciones: input: {0}", hackatonId);
        List<CalificacionDTO> listaDTOs = listEntity2DTO(calificacionLogic.getCalificaciones(hackatonId));
        LOGGER.log(Level.INFO, "HackatonResource getCalificaciones: output: {0}", listaDTOs);
        return listaDTOs;
    }
    
    @GET
    @Path("{calificacionesId: \\d+}")
    public CalificacionDTO getCalificacion(@PathParam("hackatonesId") Long hackatonId, @PathParam("calificacionesId") Long calificacionsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CalificacionResource getCalificacion: input: {0}", calificacionsId);
        CalificacionEntity entity = calificacionLogic.getCalificacion(hackatonId, calificacionsId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /hackatones/" + hackatonId + "/calificaciones/" + calificacionsId + " no existe.", 404);
        }
        CalificacionDTO calificacionDTO = new CalificacionDTO(entity);
        LOGGER.log(Level.INFO, "CalificacionResource getCalificacion: output: {0}", calificacionDTO);
        return calificacionDTO;
    }
    
//    @GET
//    @Path("{calificacionesId: \\d+}")
//    public CalificacionDTO getCalificacion(@PathParam("calificacionesId") Long calificacionId) {
//        LOGGER.log(Level.INFO, "CalificacionResource getCalificacion: input: {0}", calificacionId);
//        CalificacionEntity calificacionEntity = calificacionLogic.getCalificacion(calificacionId);
//        if (calificacionEntity == null) {
//            throw new WebApplicationException("El recurso /calificacions/" + calificacionId + " no existe.", 404);
//        }
//        CalificacionDTO calificacionDTO = new CalificacionDTO(calificacionEntity);
//        LOGGER.log(Level.INFO, "CalificacionResource getCalificacion: output: {0}", calificacionDTO);
//        return calificacionDTO;
//    }
    
    @PUT
    @Path("{calificacionesId: \\d+}")
    public CalificacionDTO updateCalificacion(@PathParam("hackatonesId") Long hackatonesId, @PathParam("calificacionesId") Long calificacionesId, CalificacionDTO calificacion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CalificacionResource updateCalificacion: input: hackatonesId: {0} , calificacionesId: {1} , calificacion:{2}", new Object[]{hackatonesId, calificacionesId, calificacion});
        if (calificacionesId.equals(calificacion.getId())) {
            throw new BusinessLogicException("Los ids del Calificacion no coinciden.");
        }
        CalificacionEntity entity = calificacionLogic.getCalificacion(hackatonesId, calificacionesId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /hackatones/" + hackatonesId + "/calificaciones/" + calificacionesId + " no existe.", 404);

        }
        CalificacionDTO calificacionDTO = new CalificacionDTO(calificacionLogic.updateCalificacion(hackatonesId, calificacion.toEntity()));
        LOGGER.log(Level.INFO, "CalificacionResource updateCalificacion: output:{0}", calificacionDTO);
        return calificacionDTO;

    }
    
    @DELETE
    @Path("{calificacionesId: \\d+}")
    public void deleteCalificacion(@PathParam("hackatonesId") Long hackatonesId, @PathParam("calificacionesId") Long calificacionesId) throws BusinessLogicException{
        CalificacionEntity entity = calificacionLogic.getCalificacion(hackatonesId, calificacionesId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /hackatones/" + hackatonesId + "/calificaciones/" + calificacionesId + " no existe.", 404);
        }
        calificacionLogic.deleteCalificacion(hackatonesId, calificacionesId);
    }
    
    private List<CalificacionDTO> listEntity2DTO(List<CalificacionEntity> entityList) {
        List<CalificacionDTO> list = new ArrayList<CalificacionDTO>();
        for (CalificacionEntity entity : entityList) {
            list.add(new CalificacionDTO(entity));
        }
        return list;
    }

}
