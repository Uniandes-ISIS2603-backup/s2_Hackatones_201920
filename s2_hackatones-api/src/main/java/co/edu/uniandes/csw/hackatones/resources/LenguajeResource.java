/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.LenguajeDTO;
import co.edu.uniandes.csw.hackatones.dtos.LenguajeDetailDTO;
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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author s.estupinan
 */
@Path("/lenguajes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class LenguajeResource 
{
    private static final Logger LOGGER = Logger.getLogger(LenguajeResource.class.getName());
    
    private String s1="El recurso /lenguajes/";
    private String s2=" no existe.";
    
    @Inject
    private LenguajeLogic logic;
    
    @POST
    public LenguajeDTO createLenguaje(LenguajeDTO dto) throws BusinessLogicException 
    {
        return new LenguajeDTO(logic.createLenguaje(dto.toEntity()));
    }
    
    @GET
    public List<LenguajeDetailDTO> getUsuarios()
    {
        return listEntity2DTO(logic.getLenguajes());
    }
    
    
    @GET
    @Path("{lenguajeId: \\d+}")
    public LenguajeDetailDTO getLenguaje(@PathParam("lenguajeId") Long id) {
        LOGGER.log(Level.INFO, "LenguajeResource getLenguaje: input: {0}", id);
        LenguajeEntity entity = logic.getLenguaje(id);
        if (entity == null) {
            throw new WebApplicationException(s1 + id + s2, 404);
        }
        LenguajeDetailDTO detailDTO = new LenguajeDetailDTO(entity);
        LOGGER.log(Level.INFO, "LenguajeResource getLenguaje: output: {0}", detailDTO);
        return detailDTO;
    }
    
    @PUT
    @Path("{lenguajeId: \\d+}")
    public LenguajeDetailDTO updateLenguaje(@PathParam("lenguajeId") Long id, LenguajeDetailDTO lenguaje) {
        LOGGER.log(Level.INFO, "LenguajeResource updateLenguaje: input: lenguajeId: {0} , usuario: {1}", new Object[]{id, lenguaje});
        lenguaje.setId(id);
        if (logic.getLenguaje(id) == null) {
            throw new WebApplicationException(s1 + id + s2, 404);
        }
        LenguajeDetailDTO detailDTO = new LenguajeDetailDTO(logic.updateLenguaje(id, lenguaje.toEntity()));
        LOGGER.log(Level.INFO, "LenguajeResource updateLenguaje: output: {0}", detailDTO);
        return detailDTO;
    }
    
    
    @DELETE
    @Path("{lenguajeId: \\d+}")
    public void deleteLenguaje(@PathParam("lenguajeId") Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "LenguajeResource deleteLenguaje: input: {0}", id);
        if (logic.getLenguaje(id) == null) {
            throw new WebApplicationException(s1 + id + s2, 404);
        }
        logic.deleteLenguaje(id);
        LOGGER.info("LenguajeResource deleteLenguaje: output: void");
    }
    
    @Path("{lenguajesId: \\d+}/hackatones")
    public Class<LenguajeHackatonResource> getLenguajeHackatonResource(@PathParam("lenguajesId") Long lenguajesId) {
        if (logic.getLenguaje(lenguajesId) == null) {
            throw new WebApplicationException(s1 + lenguajesId + s2, 404);
        }
        return LenguajeHackatonResource.class;
    }
    
    private List<LenguajeDetailDTO> listEntity2DTO(List<LenguajeEntity> entityList) {
        List<LenguajeDetailDTO> list = new ArrayList<>();
        for (LenguajeEntity entity : entityList) {
            list.add(new LenguajeDetailDTO(entity));
        }
        return list;
    }
}
