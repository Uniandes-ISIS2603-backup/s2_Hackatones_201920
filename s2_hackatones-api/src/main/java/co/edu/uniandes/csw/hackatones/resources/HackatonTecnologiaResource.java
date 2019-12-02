/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.TecnologiaDetailDTO;
import co.edu.uniandes.csw.hackatones.ejb.HackatonTecnologiaLogic;
import co.edu.uniandes.csw.hackatones.ejb.TecnologiaLogic;
import co.edu.uniandes.csw.hackatones.entities.TecnologiaEntity;
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
 *
 * @author s.estupinan
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HackatonTecnologiaResource {
    
    private static final Logger LOGGER = Logger.getLogger(HackatonTecnologiaResource.class.getName());

    @Inject
    private HackatonTecnologiaLogic hackatonTecnologiaLogic;

    @Inject
    private TecnologiaLogic tecnologiaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Asocia un tecnologia existente con un hackaton existente
     *
     * @param hackatonsId El ID del hackaton al cual se le va a asociar el tecnologia
     * @param tecnologiasId El ID del tecnologia que se asocia
     * @return JSON {@link TecnologiaDetailDTO} - El tecnologia asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el tecnologia.
     */
    @POST
    @Path("{tecnologiasId: \\d+}")
    public TecnologiaDetailDTO addTecnologia(@PathParam("hackatonesId") Long hackatonsId, @PathParam("tecnologiasId") Long tecnologiasId) {
        LOGGER.log(Level.INFO, "HackatonTecnologiaResource addTecnologia: input: hackatonsId {0} , tecnologiasId {1}", new Object[]{hackatonsId, tecnologiasId});
        if (tecnologiaLogic.getTecnologia(tecnologiasId) == null) {
            throw new WebApplicationException("El recurso /tecnologias/" + tecnologiasId + " no existe.", 404);
        }
        TecnologiaDetailDTO detailDTO = new TecnologiaDetailDTO(hackatonTecnologiaLogic.addTecnologia(hackatonsId, tecnologiasId));
        LOGGER.log(Level.INFO, "HackatonTecnologiaResource addTecnologia: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Busca y devuelve todos los tecnologias que existen en un hackaton.
     *
     * @param hackatonsId El ID del hackaton del cual se buscan los tecnologias
     * @return JSONArray {@link TecnologiaDetailDTO} - Los tecnologias encontrados en el
     * hackaton. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<TecnologiaDetailDTO> getTecnologias(@PathParam("hackatonesId") Long hackatonsId) {
        LOGGER.log(Level.INFO, "HackatonTecnologiaResource getTecnologias: input: {0}", hackatonsId);
        List<TecnologiaDetailDTO> lista = tecnologiasListEntity2DTO(hackatonTecnologiaLogic.getTecnologias(hackatonsId));
        LOGGER.log(Level.INFO, "HackatonTecnologiaResource getTecnologias: output: {0}", lista);
        return lista;
    }

    /**
     * Busca y devuelve el tecnologia con el ID recibido en la URL, relativo a un
     * hackaton.
     *
     * @param hackatonsId El ID del hackaton del cual se busca el tecnologia
     * @param tecnologiasId El ID del tecnologia que se busca
     * @return {@link TecnologiaDetailDTO} - El tecnologia encontrado en el hackaton.
     * @throws co.edu.uniandes.csw.tecnologiastore.exceptions.BusinessLogicException
     * si el tecnologia no está asociado al hackaton
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el tecnologia.
     */
    @GET
    @Path("{tecnologiasId: \\d+}")
    public TecnologiaDetailDTO getTecnologia(@PathParam("hackatonesId") Long hackatonsId, @PathParam("tecnologiasId") Long tecnologiasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "HackatonTecnologiaResource getTecnologia: input: hackatonsId {0} , tecnologiasId {1}", new Object[]{hackatonsId, tecnologiasId});
        if (tecnologiaLogic.getTecnologia(tecnologiasId) == null) {
            throw new WebApplicationException("El recurso /tecnologias/" + tecnologiasId + " no existe.", 404);
        }
        TecnologiaDetailDTO detailDTO = new TecnologiaDetailDTO(hackatonTecnologiaLogic.getTecnologia(hackatonsId, tecnologiasId));
        LOGGER.log(Level.INFO, "HackatonTecnologiaResource getTecnologia: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la lista de tecnologias de un hackaton con la lista que se recibe en el
     * cuerpo
     *
     * @param hackatonsId El ID del hackaton al cual se le va a asociar el tecnologia
     * @param tecnologias JSONArray {@link TecnologiaDetailDTO} - La lista de tecnologias que se
     * desea guardar.
     * @return JSONArray {@link TecnologiaDetailDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el tecnologia.
     */
    @PUT
    public List<TecnologiaDetailDTO> replaceTecnologias(@PathParam("hackatonesId") Long hackatonsId, List<TecnologiaDetailDTO> tecnologias) {
        LOGGER.log(Level.INFO, "HackatonTecnologiaResource replaceTecnologias: input: hackatonsId {0} , tecnologias {1}", new Object[]{hackatonsId, tecnologias});
        for (TecnologiaDetailDTO tecnologia : tecnologias) {
            if (tecnologiaLogic.getTecnologia(tecnologia.getId()) == null) {
                throw new WebApplicationException("El recurso /tecnologias/" + tecnologia.getId() + " no existe.", 404);
            }
        }
        List<TecnologiaDetailDTO> lista = tecnologiasListEntity2DTO(hackatonTecnologiaLogic.replaceTecnologias(hackatonsId, tecnologiasListDTO2Entity(tecnologias)));
        LOGGER.log(Level.INFO, "HackatonTecnologiaResource replaceTecnologias: output: {0}", lista);
        return lista;
    }

    /**
     * Elimina la conexión entre el tecnologia y e hackaton recibidos en la URL.
     *
     * @param hackatonsId El ID del hackaton al cual se le va a desasociar el tecnologia
     * @param tecnologiasId El ID del tecnologia que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el tecnologia.
     */
    @DELETE
    @Path("{tecnologiasId: \\d+}")
    public void removeTecnologia(@PathParam("hackatonesId") Long hackatonsId, @PathParam("tecnologiasId") Long tecnologiasId) {
        LOGGER.log(Level.INFO, "HackatonTecnologiaResource removeTecnologia: input: hackatonsId {0} , tecnologiasId {1}", new Object[]{hackatonsId, tecnologiasId});
        if (tecnologiaLogic.getTecnologia(tecnologiasId) == null) {
            throw new WebApplicationException("El recurso /tecnologias/" + tecnologiasId + " no existe.", 404);
        }
        hackatonTecnologiaLogic.removeTecnologia(hackatonsId, tecnologiasId);
        LOGGER.info("HackatonTecnologiaResource removeTecnologia: output: void");
    }

    /**
     * Convierte una lista de TecnologiaEntity a una lista de TecnologiaDetailDTO.
     *
     * @param entityList Lista de TecnologiaEntity a convertir.
     * @return Lista de TecnologiaDetailDTO convertida.
     */
    private List<TecnologiaDetailDTO> tecnologiasListEntity2DTO(List<TecnologiaEntity> entityList) {
        List<TecnologiaDetailDTO> list = new ArrayList<>();
        for (TecnologiaEntity entity : entityList) {
            list.add(new TecnologiaDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de TecnologiaDetailDTO a una lista de TecnologiaEntity.
     *
     * @param dtos Lista de TecnologiaDetailDTO a convertir.
     * @return Lista de TecnologiaEntity convertida.
     */
    private List<TecnologiaEntity> tecnologiasListDTO2Entity(List<TecnologiaDetailDTO> dtos) {
        List<TecnologiaEntity> list = new ArrayList<>();
        for (TecnologiaDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
}
