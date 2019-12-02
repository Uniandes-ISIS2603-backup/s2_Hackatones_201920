/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.HackatonDetailDTO;
import co.edu.uniandes.csw.hackatones.ejb.HackatonLogic;
import co.edu.uniandes.csw.hackatones.ejb.UsuarioHackatonLogic;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;


/**
 * Clase que implementa el recurso "usuario/{id}/hackatones".
 *
 * @usuario jc.higuera
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioHackatonResource 
{
   
    private static final Logger LOGGER = Logger.getLogger(UsuarioHackatonResource.class.getName());
       
    @Inject
    private UsuarioHackatonLogic usuarioHackatonLogic;
    
    @Inject
    private HackatonLogic hackatonLogic;
    
    private String s1="El recurso /hackatones/";
    private String s2=" no existe.";
    
    /**
     * Asocia una hackaton existente con un usuario existente
     *
     * @param usuarioId El ID del usuario al cual se le va a asociar la hackaton
     * @param hackatonId El ID de la hackaton que se asocia
     * @return JSON {@link HackatonDetailDTO} - El hackaton asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el hackaton.
     */
    @POST
    @Path("{hackatonId: \\d+}")
    public HackatonDetailDTO addHackaton(@PathParam("usuarioId") Long usuarioId, @PathParam("hackatonId") Long hackatonId) {
        LOGGER.log(Level.INFO, "UsuarioHackatonResource addHackaton: input: usuariosId {0} , hackatonesId {1}", new Object[]{usuarioId, hackatonId});
        if (hackatonLogic.getHackaton(hackatonId) == null) {
            throw new WebApplicationException(s1 + hackatonId + s2, 404);
        }
        HackatonDetailDTO detailDTO = new HackatonDetailDTO(usuarioHackatonLogic.addHackaton(usuarioId, hackatonId));
        LOGGER.log(Level.INFO, "UsuarioHackatonResource addHackaton: output: {0}", detailDTO);
        return detailDTO;
    }
    
    /**
     * Busca y devuelve todas las hackatones que existen en un usuario.
     *
     * @param usuariosId El ID del usuario del cual se buscan las hackatones
     * @return JSONArray {@link HackatonDetailDTO} - Los hackatones encontrados en el
     * usuario. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<HackatonDetailDTO> getHackatones(@PathParam("usuariosId") Long usuariosId) {
        LOGGER.log(Level.INFO, "UsuarioHackatonesResource getHackatones: input: {0}", usuariosId);
        List<HackatonDetailDTO> lista = hackatonesListEntity2DTO(usuarioHackatonLogic.getHackatones(usuariosId));
        LOGGER.log(Level.INFO, "UsuarioHackatonesResource getHackatones: output: {0}", lista);
        return lista;
    }
    
    /**
     * Busca y devuelve el hackaton con el ID recibido en la URL, relativo a un
     * usuario.
     *
     * @param usuariosId El ID del usuario del cual se busca el hackaton
     * @param hackatonId El ID del hackaton que se busca
     * @return {@link HackatonDetailDTO} - El hackaton encontrado en el usuario.
     * @throws BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el hackaton.
     */
    @GET
    @Path("{hackatonId: \\d+}")
    public HackatonDetailDTO getHackaton(@PathParam("usuariosId") Long usuariosId, @PathParam("hackatonId") Long hackatonId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioHackatonesResource getHackaton: input: usuariosId {0} , hackatonId {1}", new Object[]{usuariosId, hackatonId});
        if (hackatonLogic.getHackaton(hackatonId) == null) {
            throw new WebApplicationException(s1 + hackatonId + s2, 404);
        }
        HackatonDetailDTO detailDTO = new HackatonDetailDTO(usuarioHackatonLogic.getHackaton(usuariosId, hackatonId));
        LOGGER.log(Level.INFO, "UsuarioHackatonesResource getHackaton: output: {0}", detailDTO);
        return detailDTO;
    }
    
    /**
     * Actualiza la lista de hackatons de un usuario con la lista que se recibe en el
     * cuerpo
     *
     * @param usuariosId El ID del usuario al cual se le va a asociar el hackaton
     * @param hackatones JSONArray {@link HackatonDetailDTO} - La lista de hackatons que se
     * desea guardar.
     * @return JSONArray {@link HackatonDetailDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el hackaton.
     */
    @PUT
    public List<HackatonDetailDTO> replaceHackatones(@PathParam("usuariosId") Long usuariosId, List<HackatonDetailDTO> hackatones) {
        LOGGER.log(Level.INFO, "UsuarioHackatonsResource replaceHackatons: input: usuariosId {0} , hackatons {1}", new Object[]{usuariosId, hackatones});
        for (HackatonDetailDTO hackaton : hackatones) {
            if (hackatonLogic.getHackaton(hackaton.getId()) == null) {
                throw new WebApplicationException(s1 + hackaton.getId() + s2, 404);
            }
        }
        List<HackatonDetailDTO> lista = hackatonesListEntity2DTO(usuarioHackatonLogic.replaceHackaton(usuariosId, hackatonesListDTO2Entity(hackatones)));
        LOGGER.log(Level.INFO, "UsuarioHackatonsResource replaceHackatons: output: {0}", lista);
        return lista;
    }
    
    /**
     * Elimina la conexión entre el hackaton y e usuario recibidos en la URL.
     *
     * @param usuarioId El ID del usuario al cual se le va a desasociar el hackaton
     * @param hackatonId El ID del hackaton que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el hackaton.
     */
    @DELETE
    @Path("{hackatonId: \\d+}")
    public void removeHackaton(@PathParam("usuariosId") Long usuarioId, @PathParam("hackatonesId") Long hackatonId) {
        LOGGER.log(Level.INFO, "UsuarioHackatonesResource deleteHackaton: input: usuariosId {0} , hackatonesId {1}", new Object[]{usuarioId, hackatonId});
        if (hackatonLogic.getHackaton(hackatonId) == null) {
            throw new WebApplicationException(s1 + hackatonId + s2, 404);
        }
        usuarioHackatonLogic.removeHackaton(usuarioId, hackatonId);
        LOGGER.info("UsuarioHackatonesResource deleteHackaton: output: void");
    }
    
    /**
     * Convierte una lista de HackatonEntity a una lista de HackatonDetailDTO.
     *
     * @param entityList Lista de HackatonEntity a convertir.
     * @return Lista de HackatonDetailDTO convertida.
     */
    private List<HackatonDetailDTO> hackatonesListEntity2DTO(List<HackatonEntity> entityList) {
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
    private List<HackatonEntity> hackatonesListDTO2Entity(List<HackatonDetailDTO> dtos) {
        List<HackatonEntity> list = new ArrayList<>();
        for (HackatonDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
