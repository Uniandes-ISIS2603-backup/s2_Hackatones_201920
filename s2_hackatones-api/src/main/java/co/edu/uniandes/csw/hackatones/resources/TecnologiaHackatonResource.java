/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;
import co.edu.uniandes.csw.hackatones.dtos.HackatonDetailDTO;
import co.edu.uniandes.csw.hackatones.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.hackatones.ejb.HackatonLogic;
import co.edu.uniandes.csw.hackatones.ejb.TecnologiaHackatonLogic;
import co.edu.uniandes.csw.hackatones.ejb.TecnologiaUsuarioLogic;
import co.edu.uniandes.csw.hackatones.ejb.UsuarioLogic;
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
 * @author jd.monsalve
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TecnologiaHackatonResource 
{
    private static final Logger LOGGER = Logger.getLogger(TecnologiaHackatonResource.class.getName());

    @Inject
    private TecnologiaHackatonLogic tecnologiaHackatonLogic;

    @Inject
    private HackatonLogic hackatonLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    private String s1="El recurso /hackatones/";
    private String s2=" no existe.";
    
    /**
     * Asocia una hackaton existente con un tecnologia existente
     *
     * @param tecnologiaId El ID del tecnologia al cual se le va a asociar el usuario
     * @param hackatonId El ID de la hackaton que se asocia
     * @return JSON {@link HackatonDetailDTO} - La hackaton asociada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la hackaton.
     */
    @POST
    @Path("{hackatonesId: \\d+}")
    public HackatonDetailDTO addHackaton(@PathParam("tecnologiasId") Long tecnologiaId, @PathParam("hackatonesId") Long hackatonId) {
        LOGGER.log(Level.INFO, "TecnologiaHackatonResource addHackaton: input: tecnologiaId {0} , hackatonId {1}", new Object[]{tecnologiaId, hackatonId});
        if (hackatonLogic.getHackaton(hackatonId) == null) {
            throw new WebApplicationException(s1 + hackatonId + s2, 404);
        }
        HackatonDetailDTO detailDTO = new HackatonDetailDTO(tecnologiaHackatonLogic.addHackaton(tecnologiaId, hackatonId));
        LOGGER.log(Level.INFO, "TecnologiaHackatonResource addHackaton: output: {0}", detailDTO);
        return detailDTO;
    }
    
    /**
     * Busca y devuelve todas las hackatones que existen en un tecnologia.
     *
     * @param tecnologiaId El ID del tecnologia del cual se buscan los usuarios
     * @return JSONArray {@link HackatonDetailDTO} - Las hackatones encontrados en el
     * tecnologia. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<HackatonDetailDTO> getHackatones(@PathParam("tecnologiasId") Long tecnologiaId) {
        LOGGER.log(Level.INFO, "TecnologiaHackatonesResource getHackatones: input: {0}", tecnologiaId);
        List<HackatonDetailDTO> lista = hackatonesListEntity2DTO(tecnologiaHackatonLogic.getHackatones(tecnologiaId));
        LOGGER.log(Level.INFO, "TecnologiaHackatonesResource getHackatones: output: {0}", lista);
        return lista;
    }

    /**
     * Busca y devuelve la hackaton con el ID recibido en la URL, relativo a un
     * tecnologia.
     *
     * @param tecnologiaId El ID del tecnologia del cual se busca el usuario
     * @param hackatonId El ID de la hackaton que se busca
     * @return {@link HackatonDetailDTO} - La hackaton encontrada en el tecnologia.
     * @throws BusinessLogicException
     * si la hackaton no está asociada al tecnologia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la hackaton.
     */
    @GET
    @Path("{hackatonesId: \\d+}")
    public HackatonDetailDTO getHackaton(@PathParam("tecnologiasId") Long tecnologiaId, @PathParam("hackatonesId") Long hackatonId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TecnologiaHackatonResource getHackaton: input: tecnologiaId {0} , hackatonId {1}", new Object[]{tecnologiaId, hackatonId});
        if (hackatonLogic.getHackaton(hackatonId) == null) {
            throw new WebApplicationException(s1 + hackatonId + s2, 404);
        }
        HackatonDetailDTO detailDTO = new HackatonDetailDTO(tecnologiaHackatonLogic.getHackaton(tecnologiaId, hackatonId));
        LOGGER.log(Level.INFO, "TecnologiaHackatonResource getHackaton: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la lista de hackatones de un tecnologia con la lista que se recibe en el
     * cuerpo
     *
     * @param tecnologiaId El ID del tecnologia al cual se le va a asociar la hackaton
     * @param usuarios JSONArray {@link UsuarioDetailDTO} - La lista de hackatones que se
     * desea guardar.
     * @return JSONArray {@link UsuarioDetailDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la hackaton.
     */
    @PUT
    public List<HackatonDetailDTO> replaceHackatones(@PathParam("tecnologiasId") Long tecnologiaId, List<HackatonDetailDTO> hackatones) {
        LOGGER.log(Level.INFO, "TecnologiaHackatonResource replaceHackatones: input: tecnologiaId {0} , hackatones {1}", new Object[]{tecnologiaId, hackatones});
        for (HackatonDetailDTO hackaton : hackatones) {
            if (hackatonLogic.getHackaton(hackaton.getId()) == null) {
                throw new WebApplicationException(s1 + hackaton.getId() + s2, 404);
            }
        }
        List<HackatonDetailDTO> lista = hackatonesListEntity2DTO(tecnologiaHackatonLogic.replaceHackatones(tecnologiaId, hackatonesListDTO2Entity(hackatones)));
        LOGGER.log(Level.INFO, "TecnologiaHackatonResource replaceHackatones: output: {0}", lista);
        return lista;
    }

    /**
     * Elimina la conexión entre la hackaton y la tecnologia recibidos en la URL.
     *
     * @param tecnologiaId El ID del tecnologia al cual se le va a desasociar el usuario
     * @param hackatonId El ID de la hackaton que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el usuario.
     */
    @DELETE
    @Path("{hackatonesId: \\d+}")
    public void removeHackaton(@PathParam("tecnologiasId") Long tecnologiaId, @PathParam("hackatonesId") Long hackatonId) {
        LOGGER.log(Level.INFO, "TecnologiaHackatonResource removeHackaton: input: tecnologiaId {0} , usuariosId {1}", new Object[]{tecnologiaId, hackatonId});
        if (hackatonLogic.getHackaton(hackatonId) == null) {
            throw new WebApplicationException(s1 + hackatonId + s2, 404);
        }
        tecnologiaHackatonLogic.removeHackaton(tecnologiaId, hackatonId);
        LOGGER.info("TecnologiaHackatonsResource removeHackaton: output: void");
    }
    
    /**
     * Convierte una lista de UsuarioEntity a una lista de UsuarioDetailDTO.
     *
     * @param entityList Lista de UsuarioEntity a convertir.
     * @return Lista de UsuarioDetailDTO convertida.
     */
    private List<HackatonDetailDTO> hackatonesListEntity2DTO(List<HackatonEntity> entityList) {
        List<HackatonDetailDTO> list = new ArrayList<>();
        for (HackatonEntity entity : entityList) {
            list.add(new HackatonDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de UsuarioDetailDTO a una lista de UsuarioEntity.
     *
     * @param dtos Lista de UsuarioDetailDTO a convertir.
     * @return Lista de UsuarioEntity convertida.
     */
    private List<HackatonEntity> hackatonesListDTO2Entity(List<HackatonDetailDTO> dtos) {
        List<HackatonEntity> list = new ArrayList<>();
        for (HackatonDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
