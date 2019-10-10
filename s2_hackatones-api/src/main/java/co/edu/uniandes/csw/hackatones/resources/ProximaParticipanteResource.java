/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.ParticipanteDTO;
import co.edu.uniandes.csw.hackatones.ejb.ParticipanteLogic;
import co.edu.uniandes.csw.hackatones.ejb.ProximaParticipanteLogic;
import co.edu.uniandes.csw.hackatones.entities.ParticipanteEntity;
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
 * @author a.pedraza
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProximaParticipanteResource {
    
    private static final Logger LOGGER = Logger.getLogger(ProximaParticipanteResource.class.getName());

    @Inject
    private ProximaParticipanteLogic proximaParticipanteLogic;

    @Inject
    private ParticipanteLogic participanteLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @POST
    @Path("{ParticipanteId: \\d+}")
    public ParticipanteDTO addParticipante(@PathParam("proximaId") Long proximaId, @PathParam("participanteId") Long participanteId) {
        LOGGER.log(Level.INFO, "ProximaParticipanteResource addParticipante: input: proximaId {0} , participanteId {1}", new Object[]{proximaId, participanteId});
        if (participanteLogic.getParticipante(participanteId) == null) {
            throw new WebApplicationException("El recurso /participante/" + participanteId + " no existe.", 404);
        }
        ParticipanteDTO dto = new ParticipanteDTO(proximaParticipanteLogic.addParticipante(proximaId, participanteId));
        LOGGER.log(Level.INFO, "ProximaParticipanteResource addParticipante: output: {0}", dto);
        return dto;
    }

    @GET
    public List<ParticipanteDTO> getParticipantes(@PathParam("proximasId") Long proximasId) {
        LOGGER.log(Level.INFO, "ProximaParticipanteResource getParticipantes: input: {0}", proximasId);
        List<ParticipanteDTO> lista = participantesListEntity2DTO(proximaParticipanteLogic.getParticipantes(proximasId));
        LOGGER.log(Level.INFO, "ProximaParticipantesResource getParticipantes: output: {0}", lista);
        return lista;
    }
    
    @GET
    @Path("{participantesId: \\d+}")
    public ParticipanteDTO getParticipante(@PathParam("proximasId") Long proximasId, @PathParam("participantesId") Long participantesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProximaParticipanteResource getParticipante: input: proximasId {0} , participantesId {1}", new Object[]{proximasId, participantesId});
        if (participanteLogic.getParticipante(participantesId) == null) {
            throw new WebApplicationException("El recurso /participantes/" + participantesId + " no existe.", 404);
        }
        ParticipanteDTO detailDTO = new ParticipanteDTO(proximaParticipanteLogic.getParticipante(proximasId, participantesId));
        LOGGER.log(Level.INFO, "ProximaParticipanteResource getParticipante: output: {0}", detailDTO);
        return detailDTO;
    }

    @PUT
    public List<ParticipanteDTO> replaceParticipantes(@PathParam("proximasId") Long proximasId, List<ParticipanteDTO> participantes) {
        LOGGER.log(Level.INFO, "ProximaParticipanteResource replaceparticipantes: input: proximasId {0} , participantes {1}", new Object[]{proximasId, participantes});
        for (ParticipanteDTO participante : participantes) {
            if (participanteLogic.getParticipante(participante.getId()) == null) {
                throw new WebApplicationException("El recurso /participantes/" + participante.getId() + " no existe.", 404);
            }
        }
        List<ParticipanteDTO> lista = participantesListEntity2DTO(proximaParticipanteLogic.replaceParticipantess(proximasId, participantesListDTO2Entity(participantes)));
        LOGGER.log(Level.INFO, "ProximaParticipanteResource replaceParticipantes: output: {0}", lista);
        return lista;
    }

    @DELETE
    @Path("{participantesId: \\d+}")
    public void removeParticipante(@PathParam("proximasId") Long proximasId, @PathParam("participantesId") Long participantesId) {
        LOGGER.log(Level.INFO, "ProximaParticipanteResource deleteParticipante: input: proximasId {0} , participantesId {1}", new Object[]{proximasId, participantesId});
        if (participanteLogic.getParticipante(participantesId) == null) {
            throw new WebApplicationException("El recurso /participantes/" + participantesId + " no existe.", 404);
        }
        proximaParticipanteLogic.removeParticipante(proximasId, participantesId);
        LOGGER.info("ProximaParticipanteResource deleteParticipante: output: void");
    }

    
    private List<ParticipanteDTO> participantesListEntity2DTO(List<ParticipanteEntity> entityList) {
        List<ParticipanteDTO> list = new ArrayList<>();
        for (ParticipanteEntity entity : entityList) {
            list.add(new ParticipanteDTO(entity));
        }
        return list;
    }
    
    private List<ParticipanteEntity> participantesListDTO2Entity(List<ParticipanteDTO> dtos) {
        List<ParticipanteEntity> list = new ArrayList<>();
        for (ParticipanteDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
