/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.InteresDetailDTO;
import co.edu.uniandes.csw.hackatones.ejb.HackatonInteresLogic;
import co.edu.uniandes.csw.hackatones.ejb.InteresLogic;
import co.edu.uniandes.csw.hackatones.entities.InteresEntity;
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
 * @author a.pedraza
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HackatonInteresResource {
    
    private static final Logger LOGGER = Logger.getLogger(HackatonInteresResource.class.getName());

    private String s1= "El recurso /intereses/";
    private String s2= " no existe.";
    
    @Inject
    private HackatonInteresLogic hackatonInteresLogic;

    @Inject
    private InteresLogic interesLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    /**
     * Asocia un interes existente con un hackaton existente
     *
     * @param hackatonId El ID del hackaton al cual se le va a asociar el interes
     * @param interesesId El ID del interes que se asocia
     * @return JSON {@link InteresDetailDTO} - El interes asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el interes.
     */
    @POST
    @Path("{interesesId: \\d+}")
    public InteresDetailDTO addInteres(@PathParam("hackatonesId") Long hackatonId, @PathParam("interesesId") Long interesesId){
        LOGGER.log(Level.INFO, "HackatonInteresesResource addInteres: input: hackatonId {0} , interesId {1}", new Object[]{hackatonId, interesesId});
        if (interesLogic.getInteres(interesesId) == null) {
            throw new WebApplicationException(s1 + interesesId + s2, 404);
        }
        InteresDetailDTO detailDTO = new InteresDetailDTO(hackatonInteresLogic.addInteres(hackatonId, interesesId));
        LOGGER.log(Level.INFO, "HackatonInteresesResource addInteres: output: {0}", detailDTO);
        return detailDTO;
    }
    
    /**
     * Busca y devuelve todos los intereses que existen en un hackaton.
     *
     * @param hackatonId El ID del hackaton del cual se buscan los intereses
     * @return JSONArray {@link InteresDetailDTO} - Los intereses encontrados en el
     * hackaton. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<InteresDetailDTO> getIntereses(@PathParam("hackatonesId") Long hackatonId) {
        LOGGER.log(Level.INFO, "HackatonInteresesResource getIntereses: input: {0}", hackatonId);
        List<InteresDetailDTO> lista = interesesListEntity2DTO(hackatonInteresLogic.getIntereses(hackatonId));
        LOGGER.log(Level.INFO, "HackatonInteresesResource getIntereses: output: {0}", lista);
        return lista;
    }

    /**
     * Busca y devuelve el interes con el ID recibido en la URL, relativo a un
     * hackaton.
     *
     * @param hackatonId El ID del hackaton del cual se busca el interes
     * @param interesesId El ID del interes que se busca
     * @return {@link InteresDetailDTO} - El interes encontrado en el hackaton.
     * @throws BusinessLogicException
     * si el interes no está asociado al hackaton
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el interes.
     */
    @GET
    @Path("{interesesId: \\d+}")
    public InteresDetailDTO getInteres(@PathParam("hackatonesId") Long hackatonId, @PathParam("interesesId") Long interesesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "HackatonInteresesResource getInteres: input: hackatonId {0} , interesesId {1}", new Object[]{hackatonId, interesesId});
        if (interesLogic.getInteres(interesesId) == null) {
            throw new WebApplicationException(s1 + interesesId + s2, 404);
        }
        InteresDetailDTO detailDTO = new InteresDetailDTO(hackatonInteresLogic.getInteres(hackatonId, interesesId));
        LOGGER.log(Level.INFO, "HackatonInteresesResource getInteres: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la lista de intereses de un hackaton con la lista que se recibe en el
     * cuerpo
     *
     * @param hackatonId El ID del hackaton al cual se le va a asociar el interes
     * @param intereses JSONArray {@link InteresDetailDTO} - La lista de intereses que se
     * desea guardar.
     * @return JSONArray {@link InteresDetailDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el interes.
     */
    @PUT
    public List<InteresDetailDTO> replaceIntereses(@PathParam("hackatonesId") Long hackatonId, List<InteresDetailDTO> intereses) {
        LOGGER.log(Level.INFO, "HackatonInteresesResource replaceIntereses: input: hackatonId {0} , intereses {1}", new Object[]{hackatonId, intereses});
        for (InteresDetailDTO interes : intereses) {
            if (interesLogic.getInteres(interes.getId()) == null) {
                throw new WebApplicationException(s1 + interes.getId() + s2, 404);
            }
        }
        List<InteresDetailDTO> lista = interesesListEntity2DTO(hackatonInteresLogic.replaceIntereses(hackatonId, interesesListDTO2Entity(intereses)));
        LOGGER.log(Level.INFO, "HackatonInteresesResource replaceIntereses: output: {0}", lista);
        return lista;
    }

    /**
     * Elimina la conexión entre el interes y e hackaton recibidos en la URL.
     *
     * @param hackatonId El ID del hackaton al cual se le va a desasociar el interes
     * @param interesesId El ID del interes que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el interes.
     */
    @DELETE
    @Path("{interesesId: \\d+}")
    public void removeInteres(@PathParam("hackatonesId") Long hackatonId, @PathParam("interesesId") Long interesesId) {
        LOGGER.log(Level.INFO, "HackatonInteresesResource deleteInteres: input: hackatonId {0} , interesesId {1}", new Object[]{hackatonId, interesesId});
        if (interesLogic.getInteres(interesesId) == null) {
            throw new WebApplicationException(s1 + interesesId + s2, 404);
        }
        hackatonInteresLogic.removeInteres(hackatonId, interesesId);
        LOGGER.info("HackatonInteresesResource deleteInteres: output: void");
    }
    
    /**
     * Convierte una lista de InteresDetailDTO a una lista de InteresEntity.
     *
     * @param dtos Lista de InteresDetailDTO a convertir.
     * @return Lista de InteresEntity convertida.
     */
    private List<InteresEntity> interesesListDTO2Entity(List<InteresDetailDTO> dtos) {
        List<InteresEntity> list = new ArrayList<>();
        for (InteresDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
    /**
     * Convierte una lista de InteresEntity a una lista de InteresDetailDTO.
     *
     * @param entityList Lista de InteresEntity a convertir.
     * @return Lista de InteresDetailDTO convertida.
     */
    private List<InteresDetailDTO> interesesListEntity2DTO(List<InteresEntity> entityList) {
        List<InteresDetailDTO> list = new ArrayList<>();
        for (InteresEntity entity : entityList) {
            list.add(new InteresDetailDTO(entity));
        }
        return list;
    }
}
