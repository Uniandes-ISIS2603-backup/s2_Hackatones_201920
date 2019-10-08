/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.LenguajeDTO;
import co.edu.uniandes.csw.hackatones.ejb.LenguajeLogic;
import co.edu.uniandes.csw.hackatones.entities.LenguajeEntity;
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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Estudiante
 */
@Path("lenguajes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class LenguajeResource 
{
    private static final Logger LOGGER = Logger.getLogger(LenguajeResource.class.getName());
    
    @Inject
    private LenguajeLogic logic;
    
    @POST
    public LenguajeDTO createLenguaje(LenguajeDTO dto) throws BusinessLogicException 
    {
        LenguajeDTO authorDTO = new LenguajeDTO(logic.createLenguaje(dto.toEntity()));
        return dto;
    }
    
//    @GET
//    public List<LenguajeDTO> getReviews(@PathParam("booksId") Long id) {
//        LOGGER.log(Level.INFO, "ReviewResource getReviews: input: {0}", id);
//        List<LenguajeDTO> listaDTOs = listEntity2DTO(logic.getLenguaje(id));
//        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: output: {0}", listaDTOs);
//        return listaDTOs;
//    }
    
    @DELETE
    @Path("{lenguajeId: \\d+}")
    public void deleteLenguaje(@PathParam("lenguajeId") Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "LenguajeResource deleteLenguaje: input: {0}", id);
        if (logic.getLenguaje(id) == null) {
            throw new WebApplicationException("El recurso /lenguaje/" + id + " no existe.", 404);
        }
        logic.deleteLenguaje(id);
        LOGGER.info("LenguajeResource deleteLenguaje: output: void");
    }
    
    private List<LenguajeDTO> listEntity2DTO(List<LenguajeEntity> entityList) {
        List<LenguajeDTO> list = new ArrayList<LenguajeDTO>();
        for (LenguajeEntity entity : entityList) {
            list.add(new LenguajeDTO(entity));
        }
        return list;
    }
}
