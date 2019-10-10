/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.PatrocinadorDTO;
import co.edu.uniandes.csw.hackatones.dtos.PatrocinadorDetailDTO;
import co.edu.uniandes.csw.hackatones.ejb.PatrocinadorLogic;
import co.edu.uniandes.csw.hackatones.entities.PatrocinadorEntity;
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
@Path("/patrocinadores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class PatrocinadorResource {
    
    private static final Logger LOGGER = Logger.getLogger(PatrocinadorResource.class.getName());
    
    @Inject
    private PatrocinadorLogic logic;
    
    
    @POST
    public PatrocinadorDTO createPatrocinador(PatrocinadorDTO dto) throws BusinessLogicException {
        PatrocinadorDTO patrocinadorDTO = new PatrocinadorDTO(logic.createPatrocinador(dto.toEntity()));
        return patrocinadorDTO;
    }
    
    @GET
    public List<PatrocinadorDetailDTO> getPatrocinadores()
    {
        List<PatrocinadorDetailDTO> lista = listEntity2DTO(logic.getPatrocinadores());
        return lista;
    }
    
    
    @GET
    @Path("{patrocinadorId: \\d+}")
    public PatrocinadorDetailDTO getPatrocinador(@PathParam("patrocinadorId") Long id) {
        LOGGER.log(Level.INFO, "PatrocinadorResource getPatrocinador: input: {0}", id);
        PatrocinadorEntity entity = logic.getPatrocinador(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /patrocinadores/" + id + " no existe.", 404);
        }
        PatrocinadorDetailDTO detailDTO = new PatrocinadorDetailDTO(entity);
        LOGGER.log(Level.INFO, "PatrocinadorResource getPatrocinador: output: {0}", detailDTO);
        return detailDTO;
    }
    
    @PUT
    @Path("{patrocinadorId: \\d+}")
    public PatrocinadorDetailDTO updatePatrocinador(@PathParam("patrocinadorId") Long id, PatrocinadorDetailDTO patrocinador) {
        LOGGER.log(Level.INFO, "PatrocinadorResource updatePatrocinador: input: patrocinadorId: {0} , usuario: {1}", new Object[]{id, patrocinador});
        patrocinador.setId(id);
        if (logic.getPatrocinador(id) == null) {
            throw new WebApplicationException("El recurso /patrocinadores/" + id + " no existe.", 404);
        }
        PatrocinadorDetailDTO detailDTO = new PatrocinadorDetailDTO(logic.updatePatrocinador(id, patrocinador.toEntity()));
        LOGGER.log(Level.INFO, "PatrocinadorResource updatePatrocinador: output: {0}", detailDTO);
        return detailDTO;
    }
    
    @DELETE
    @Path("{patrocinadorId: \\d+}")
    public void deletePatrocinador(@PathParam("patrocinadorId") Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PatrocinadorResource deletePatrocinador: input: {0}", id);
        if (logic.getPatrocinador(id) == null) {
            throw new WebApplicationException("El recurso /patrocinadores/" + id + " no existe.", 404);
        }
        logic.deletePatrocinador(id);
        LOGGER.info("PatrocinadorResource deletePatrocinador: output: void");
    }
    


    private List<PatrocinadorDetailDTO> listEntity2DTO(List<PatrocinadorEntity> entityList) {
        List<PatrocinadorDetailDTO> list = new ArrayList<>();
        for (PatrocinadorEntity entity : entityList) {
            list.add(new PatrocinadorDetailDTO(entity));
        }
        return list;
    }
    
}
