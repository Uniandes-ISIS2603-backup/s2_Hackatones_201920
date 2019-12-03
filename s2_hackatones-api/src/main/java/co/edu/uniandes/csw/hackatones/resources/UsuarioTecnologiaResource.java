/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.TecnologiaDetailDTO;
import co.edu.uniandes.csw.hackatones.ejb.TecnologiaLogic;
import co.edu.uniandes.csw.hackatones.ejb.UsuarioTecnologiaLogic;
import co.edu.uniandes.csw.hackatones.entities.TecnologiaEntity;
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
 * @author s.estupinan
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioTecnologiaResource {
    
    private static final String RECURSO = "El recurso /tecnologias/";

    
    private static final Logger LOGGER = Logger.getLogger(UsuarioTecnologiaResource.class.getName());
        
    private static final String NOEXISTE = " no existe.";

    @Inject
    private UsuarioTecnologiaLogic usuarioTecnologiaLogic;

    @Inject
    private TecnologiaLogic tecnologiaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Asocia un tecnologia existente con un usuario existente
     *
     * @param usuariosId El ID del usuario al cual se le va a asociar el tecnologia
     * @param tecnologiasId El ID del tecnologia que se asocia
     * @return JSON {@link TecnologiaDetailDTO} - El tecnologia asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el tecnologia.
     */
    @POST
    @Path("{tecnologiasId: \\d+}")
    public TecnologiaDetailDTO addTecnologia(@PathParam("usuariosId") Long usuariosId, @PathParam("tecnologiasId") Long tecnologiasId) {
        LOGGER.log(Level.INFO, "UsuarioTecnologiaResource addTecnologia: input: usuariosId {0} , tecnologiasId {1}", new Object[]{usuariosId, tecnologiasId});
        if (tecnologiaLogic.getTecnologia(tecnologiasId) == null) {
            throw new WebApplicationException(RECURSO + tecnologiasId + NOEXISTE, 404);
        }
        TecnologiaDetailDTO detailDTO = new TecnologiaDetailDTO(usuarioTecnologiaLogic.addTecnologia(usuariosId, tecnologiasId));
        LOGGER.log(Level.INFO, "UsuarioTecnologiaResource addTecnologia: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Busca y devuelve todos los tecnologias que existen en un usuario.
     *
     * @param usuariosId El ID del usuario del cual se buscan los tecnologias
     * @return JSONArray {@link TecnologiaDetailDTO} - Los tecnologias encontrados en el
     * usuario. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<TecnologiaDetailDTO> getTecnologias(@PathParam("usuariosId") Long usuariosId) {
        LOGGER.log(Level.INFO, "UsuarioTecnologiaResource getTecnologias: input: {0}", usuariosId);
        List<TecnologiaDetailDTO> lista = tecnologiasListEntity2DTO(usuarioTecnologiaLogic.getTecnologias(usuariosId));
        LOGGER.log(Level.INFO, "UsuarioTecnologiaResource getTecnologias: output: {0}", lista);
        return lista;
    }

    /**
     * Busca y devuelve el tecnologia con el ID recibido en la URL, relativo a un
     * usuario.
     *
     * @param usuariosId El ID del usuario del cual se busca el tecnologia
     * @param tecnologiasId El ID del tecnologia que se busca
     * @return {@link TecnologiaDetailDTO} - El tecnologia encontrado en el usuario.
     * @throws co.edu.uniandes.csw.tecnologiastore.exceptions.BusinessLogicException
     * si el tecnologia no está asociado al usuario
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el tecnologia.
     */
    @GET
    @Path("{tecnologiasId: \\d+}")
    public TecnologiaDetailDTO getTecnologia(@PathParam("usuariosId") Long usuariosId, @PathParam("tecnologiasId") Long tecnologiasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioTecnologiaResource getTecnologia: input: usuariosId {0} , tecnologiasId {1}", new Object[]{usuariosId, tecnologiasId});
        if (tecnologiaLogic.getTecnologia(tecnologiasId) == null) {
            throw new WebApplicationException(RECURSO + tecnologiasId + NOEXISTE, 404);
        }
        TecnologiaDetailDTO detailDTO = new TecnologiaDetailDTO(usuarioTecnologiaLogic.getTecnologia(usuariosId, tecnologiasId));
        LOGGER.log(Level.INFO, "UsuarioTecnologiaResource getTecnologia: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la lista de tecnologias de un usuario con la lista que se recibe en el
     * cuerpo
     *
     * @param usuariosId El ID del usuario al cual se le va a asociar el tecnologia
     * @param tecnologias JSONArray {@link TecnologiaDetailDTO} - La lista de tecnologias que se
     * desea guardar.
     * @return JSONArray {@link TecnologiaDetailDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el tecnologia.
     */
    @PUT
    public List<TecnologiaDetailDTO> replaceTecnologias(@PathParam("usuariosId") Long usuariosId, List<TecnologiaDetailDTO> tecnologias) {
        LOGGER.log(Level.INFO, "UsuarioTecnologiaResource replaceTecnologias: input: usuariosId {0} , tecnologias {1}", new Object[]{usuariosId, tecnologias});
        for (TecnologiaDetailDTO tecnologia : tecnologias) {
            if (tecnologiaLogic.getTecnologia(tecnologia.getId()) == null) {
                throw new WebApplicationException(RECURSO + tecnologia.getId() + NOEXISTE, 404);
            }
        }
        List<TecnologiaDetailDTO> lista = tecnologiasListEntity2DTO(usuarioTecnologiaLogic.replaceTecnologias(usuariosId, tecnologiasListDTO2Entity(tecnologias)));
        LOGGER.log(Level.INFO, "UsuarioTecnologiaResource replaceTecnologias: output: {0}", lista);
        return lista;
    }

    /**
     * Elimina la conexión entre el tecnologia y e usuario recibidos en la URL.
     *
     * @param usuariosId El ID del usuario al cual se le va a desasociar el tecnologia
     * @param tecnologiasId El ID del tecnologia que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el tecnologia.
     */
    @DELETE
    @Path("{tecnologiasId: \\d+}")
    public void removeTecnologia(@PathParam("usuariosId") Long usuariosId, @PathParam("tecnologiasId") Long tecnologiasId) {
        LOGGER.log(Level.INFO, "UsuarioTecnologiaResource removeTecnologia: input: usuariosId {0} , tecnologiasId {1}", new Object[]{usuariosId, tecnologiasId});
        if (tecnologiaLogic.getTecnologia(tecnologiasId) == null) {
            throw new WebApplicationException(RECURSO + tecnologiasId + NOEXISTE, 404);
        }
        usuarioTecnologiaLogic.removeTecnologia(usuariosId, tecnologiasId);
        LOGGER.info("UsuarioTecnologiaResource removeTecnologia: output: void");
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

    
    
    
}
