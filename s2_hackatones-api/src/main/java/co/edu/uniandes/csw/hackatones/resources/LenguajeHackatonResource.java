/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.HackatonDetailDTO;
import co.edu.uniandes.csw.hackatones.ejb.HackatonLogic;
import co.edu.uniandes.csw.hackatones.ejb.LenguajeHackatonLogic;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @lenguaje a.pedraza
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LenguajeHackatonResource {
    
    private static final Logger LOGGER = Logger.getLogger(LenguajeHackatonResource.class.getName());

    private String s1="El recurso /hackatones/";
    private String s2=" no existe.";
    
    @Inject
    private LenguajeHackatonLogic lenguajeHackatonLogic;

    @Inject
    private HackatonLogic hackatonLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Asocia un libro existente con un autor existente
     *
     * @param lenguajesId El ID del autor al cual se le va a asociar el libro
     * @param hackatonsId El ID del libro que se asocia
     * @return JSON {@link HackatonDetailDTO} - El libro asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{hackatonsId: \\d+}")
    public HackatonDetailDTO addHackaton(@PathParam("lenguajesId") Long lenguajesId, @PathParam("hackatonsId") Long hackatonsId) {
        LOGGER.log(Level.INFO, "LenguajeHackatonsResource addHackaton: input: lenguajesId {0} , hackatonsId {1}", new Object[]{lenguajesId, hackatonsId});
        if (hackatonLogic.getHackaton(hackatonsId) == null) {
            throw new WebApplicationException(s1 + hackatonsId + s2, 404);
        }
        HackatonDetailDTO detailDTO = new HackatonDetailDTO(lenguajeHackatonLogic.addHackaton(lenguajesId, hackatonsId));
        LOGGER.log(Level.INFO, "LenguajeHackatonsResource addHackaton: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Busca y devuelve todos los libros que existen en un autor.
     *
     * @param lenguajesId El ID del autor del cual se buscan los libros
     * @return JSONArray {@link HackatonDetailDTO} - Los libros encontrados en el
     * autor. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<HackatonDetailDTO> getHackatons(@PathParam("lenguajesId") Long lenguajesId) {
        LOGGER.log(Level.INFO, "LenguajeHackatonsResource getHackatons: input: {0}", lenguajesId);
        List<HackatonDetailDTO> lista = hackatonsListEntity2DTO(lenguajeHackatonLogic.getHackatones(lenguajesId));
        LOGGER.log(Level.INFO, "LenguajeHackatonsResource getHackatons: output: {0}", lista);
        return lista;
    }

    /**
     * Busca y devuelve el libro con el ID recibido en la URL, relativo a un
     * autor.
     *
     * @param lenguajesId El ID del autor del cual se busca el libro
     * @param hackatonsId El ID del libro que se busca
     * @return {@link HackatonDetailDTO} - El libro encontrado en el autor.
     * @throws co.edu.uniandes.csw.hackatonstore.exceptions.BusinessLogicException
     * si el libro no está asociado al autor
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @GET
    @Path("{hackatonsId: \\d+}")
    public HackatonDetailDTO getHackaton(@PathParam("lenguajesId") Long lenguajesId, @PathParam("hackatonsId") Long hackatonsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "LenguajeHackatonsResource getHackaton: input: lenguajesId {0} , hackatonsId {1}", new Object[]{lenguajesId, hackatonsId});
        if (hackatonLogic.getHackaton(hackatonsId) == null) {
            throw new WebApplicationException(s1 + hackatonsId + s2, 404);
        }
        HackatonDetailDTO detailDTO = new HackatonDetailDTO(lenguajeHackatonLogic.getHackaton(lenguajesId, hackatonsId));
        LOGGER.log(Level.INFO, "LenguajeHackatonsResource getHackaton: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la lista de libros de un autor con la lista que se recibe en el
     * cuerpo
     *
     * @param lenguajesId El ID del autor al cual se le va a asociar el libro
     * @param hackatons JSONArray {@link HackatonDetailDTO} - La lista de libros que se
     * desea guardar.
     * @return JSONArray {@link HackatonDetailDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @PUT
    public List<HackatonDetailDTO> replaceHackatons(@PathParam("lenguajesId") Long lenguajesId, List<HackatonDetailDTO> hackatons) {
        LOGGER.log(Level.INFO, "LenguajeHackatonsResource replaceHackatons: input: lenguajesId {0} , hackatons {1}", new Object[]{lenguajesId, hackatons});
        for (HackatonDetailDTO hackaton : hackatons) {
            if (hackatonLogic.getHackaton(hackaton.getId()) == null) {
                throw new WebApplicationException(s1 + hackaton.getId() + s2, 404);
            }
        }
        List<HackatonDetailDTO> lista = hackatonsListEntity2DTO(lenguajeHackatonLogic.replaceHackatones(lenguajesId, hackatonsListDTO2Entity(hackatons)));
        LOGGER.log(Level.INFO, "LenguajeHackatonsResource replaceHackatons: output: {0}", lista);
        return lista;
    }

    /**
     * Elimina la conexión entre el libro y e autor recibidos en la URL.
     *
     * @param lenguajesId El ID del autor al cual se le va a desasociar el libro
     * @param hackatonsId El ID del libro que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @DELETE
    @Path("{hackatonsId: \\d+}")
    public void removeHackaton(@PathParam("lenguajesId") Long lenguajesId, @PathParam("hackatonsId") Long hackatonsId) {
        LOGGER.log(Level.INFO, "LenguajeHackatonsResource deleteHackaton: input: lenguajesId {0} , hackatonsId {1}", new Object[]{lenguajesId, hackatonsId});
        if (hackatonLogic.getHackaton(hackatonsId) == null) {
            throw new WebApplicationException(s1 + hackatonsId + s2, 404);
        }
        lenguajeHackatonLogic.removeHackaton(lenguajesId, hackatonsId);
        LOGGER.info("LenguajeHackatonsResource deleteHackaton: output: void");
    }

    
    
    /**
     * Convierte una lista de HackatonEntity a una lista de HackatonDetailDTO.
     *
     * @param entityList Lista de HackatonEntity a convertir.
     * @return Lista de HackatonDetailDTO convertida.
     */
    private List<HackatonDetailDTO> hackatonsListEntity2DTO(List<HackatonEntity> entityList) {
        List<HackatonDetailDTO> list = new ArrayList<>();
        for (HackatonEntity entity : entityList) {
            list.add(new HackatonDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de HackatonDetailDTO a una lista de HackatonEntity.
     *
     * @param dtos Lista de HackatonDetailDTO a convertir.
     * @return Lista de HackatonEntity convertida.
     */
    private List<HackatonEntity> hackatonsListDTO2Entity(List<HackatonDetailDTO> dtos) {
        List<HackatonEntity> list = new ArrayList<>();
        for (HackatonDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
