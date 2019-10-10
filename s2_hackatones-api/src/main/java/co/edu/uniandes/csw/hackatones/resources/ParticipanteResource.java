/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.ParticipanteDTO;
import co.edu.uniandes.csw.hackatones.dtos.ParticipanteDetailDTO;
import co.edu.uniandes.csw.hackatones.ejb.ParticipanteLogic;
import co.edu.uniandes.csw.hackatones.entities.ParticipanteEntity;
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
 * @author ne.cardenas
 */
@Path("participantes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ParticipanteResource {
    
    private static final Logger LOGGER = Logger.getLogger(ParticipanteResource.class.getName());
    
    /**
     * logica
     */
    @Inject
    private ParticipanteLogic logic;
    
    /**
     * crea un participante
     * @param dto dto del participante a crear
     * @return el dto del participante creado
     * @throws BusinessLogicException 
     */
    @POST
    public ParticipanteDTO createParticipante(ParticipanteDTO dto) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ParticipanteResource createParticipante: input: {0}", dto);
        ParticipanteEntity entity = dto.toEntity();
        ParticipanteEntity newEntity = logic.createParticipante(entity);
        ParticipanteDTO newDto = new ParticipanteDTO(newEntity);
        LOGGER.log(Level.INFO, "ParticipanteResource createParticipante: output: {0}", newDto);
        return newDto;
    }
    
    /**
     * devuelve todos los participantes
     * @return lista con todos los participantes
     */
    @GET
    public List<ParticipanteDetailDTO> getParticipantes() {
        LOGGER.info("EditorialParticipante getParticipante: input: void");
        List<ParticipanteDetailDTO> listaParticipantes = listEntity2DetailDTO(logic.getParticipantes());
        LOGGER.log(Level.INFO, "EditorialParticipante getParticipantes: output: {0}", listaParticipantes);
        return listaParticipantes;
    }
    
    /**
     * nose
     * @param entityList lista de entidades de participante
     * @return lista con los detail dto de participante
     */
    private List<ParticipanteDetailDTO> listEntity2DetailDTO(List<ParticipanteEntity> entityList) {
        List<ParticipanteDetailDTO> list = new ArrayList<>();
        for (ParticipanteEntity entity : entityList) {
            list.add(new ParticipanteDetailDTO(entity));
        }
        return list;
    }
    
    /**
     * obtiene un participante
     * @param participantesId id del participante
     * @return detail dto del participante
     * @throws WebApplicationException 
     */
    @GET
    @Path("{participantesId: \\d+}")
    public ParticipanteDetailDTO getParticipante(@PathParam("participantesId") Long participantesId) throws WebApplicationException {
        LOGGER.log(Level.INFO, "EditorialParticipante getParticipante: input: {0}", participantesId);
        ParticipanteEntity participanteEntity = logic.getParticipante(participantesId);
        if (participanteEntity == null) {
            throw new WebApplicationException("El recurso /participantes/" + participantesId + " no existe.", 404);
        }
        ParticipanteDetailDTO detailDTO = new ParticipanteDetailDTO(participanteEntity);
        LOGGER.log(Level.INFO, "EditorialParticipante getParticipante: output: {0}", detailDTO);
        return detailDTO;
    }
    
    /**
     * actualiza un participante
     * @param participantesId id del participante
     * @param participante detail dto del participante
     * @return detail dto participante
     * @throws WebApplicationException 
     */
    @PUT
    @Path("{participantesId: \\d+}")
    public ParticipanteDetailDTO updateParticipante(@PathParam("participantesId") Long participantesId, ParticipanteDetailDTO participante) throws WebApplicationException {
        LOGGER.log(Level.INFO, "ParticipanteResource updateParticipante: input: id:{0} , participante: {1}", new Object[]{participantesId, participante});
        participante.setId(participantesId);
        if (logic.getParticipante(participantesId) == null) {
            throw new WebApplicationException("El recurso /participantes/" + participantesId + " no existe.", 404);
        }
        ParticipanteDetailDTO detailDTO = new ParticipanteDetailDTO(logic.updateParticipante(participantesId, participante.toEntity()));
        LOGGER.log(Level.INFO, "ParticipanteResource updateParticipante: output: {0}", detailDTO);
        return detailDTO;

    }
    
    /**
     * borra un participante
     * @param participantesId id del participante a borrar
     * @throws BusinessLogicException 
     */
    @DELETE
    @Path("{participantesId: \\d+}")
    public void deleteParticipante(@PathParam("participantesId") Long participantesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ParticipanteResource deleteParticipante: input: {0}", participantesId);
        if (logic.getParticipante(participantesId) == null) {
            throw new WebApplicationException("El recurso /participantes/" + participantesId + " no existe.", 404);
        }
        logic.deleteParticipante(participantesId);
        LOGGER.info("ParticipanteResource deleteParticipante: output: void");
    }
    
}
