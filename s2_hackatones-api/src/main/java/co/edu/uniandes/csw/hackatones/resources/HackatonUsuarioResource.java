/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.hackatones.ejb.HackatonUsuarioLogic;
import co.edu.uniandes.csw.hackatones.ejb.UsuarioLogic;
import co.edu.uniandes.csw.hackatones.entities.UsuarioEntity;
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
 * @author jc.higuerah
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HackatonUsuarioResource {
    
    private static final Logger LOGGER = Logger.getLogger(HackatonUsuarioResource.class.getName());

    @Inject
    private HackatonUsuarioLogic hackatonUsuarioLogic;

    @Inject
    private UsuarioLogic usuarioLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    /**
     * Asocia un usuario existente con un hackaton existente
     *
     * @param hackatonId El ID del hackaton al cual se le va a asociar el usuario
     * @param usuariosId El ID del usuario que se asocia
     * @return JSON {@link UsuarioDetailDTO} - El usuario asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el usuario.
     */
    @POST
    @Path("{usuariosId: \\d+}")
    public UsuarioDetailDTO addUsuario(@PathParam("hackatonId") Long hackatonId, @PathParam("usuariosId") Long usuariosId) throws WebApplicationException {
        LOGGER.log(Level.INFO, "HackatonUsuariosResource addUsuario: input: hackatonId {0} , usuariosId {1}", new Object[]{hackatonId, usuariosId});
        if (usuarioLogic.getUsuario(usuariosId) == null) {
            throw new WebApplicationException("El recurso /usuarios/" + usuariosId + " no existe.", 404);
        }
        UsuarioDetailDTO detailDTO = new UsuarioDetailDTO(hackatonUsuarioLogic.addUsuario(hackatonId, usuariosId));
        LOGGER.log(Level.INFO, "HackatonUsuariosResource addUsuario: output: {0}", detailDTO);
        return detailDTO;
    }
    
    /**
     * Busca y devuelve todos los usuarios que existen en un hackaton.
     *
     * @param hackatonId El ID del hackaton del cual se buscan los usuarios
     * @return JSONArray {@link UsuarioDetailDTO} - Los usuarios encontrados en el
     * hackaton. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<UsuarioDetailDTO> getUsuarios(@PathParam("hackatonId") Long hackatonId) {
        LOGGER.log(Level.INFO, "HackatonUsuariosResource getUsuarios: input: {0}", hackatonId);
        List<UsuarioDetailDTO> lista = usuariosListEntity2DTO(hackatonUsuarioLogic.getUsuarios(hackatonId));
        LOGGER.log(Level.INFO, "HackatonUsuariosResource getUsuarios: output: {0}", lista);
        return lista;
    }

    /**
     * Busca y devuelve el usuario con el ID recibido en la URL, relativo a un
     * hackaton.
     *
     * @param hackatonId El ID del hackaton del cual se busca el usuario
     * @param usuariosId El ID del usuario que se busca
     * @return {@link UsuarioDetailDTO} - El usuario encontrado en el hackaton.
     * @throws BusinessLogicException
     * si el usuario no está asociado al hackaton
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el usuario.
     */
    @GET
    @Path("{usuariosId: \\d+}")
    public UsuarioDetailDTO getUsuario(@PathParam("hackatonId") Long hackatonId, @PathParam("usuariosId") Long usuariosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "HackatonUsuariosResource getUsuario: input: hackatonId {0} , usuariosId {1}", new Object[]{hackatonId, usuariosId});
        if (usuarioLogic.getUsuario(usuariosId) == null) {
            throw new WebApplicationException("El recurso /usuarios/" + usuariosId + " no existe.", 404);
        }
        UsuarioDetailDTO detailDTO = new UsuarioDetailDTO(hackatonUsuarioLogic.getUsuario(hackatonId, usuariosId));
        LOGGER.log(Level.INFO, "HackatonUsuariosResource getUsuario: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la lista de usuarios de un hackaton con la lista que se recibe en el
     * cuerpo
     *
     * @param hackatonId El ID del hackaton al cual se le va a asociar el usuario
     * @param usuarios JSONArray {@link UsuarioDetailDTO} - La lista de usuarios que se
     * desea guardar.
     * @return JSONArray {@link UsuarioDetailDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el usuario.
     */
    @PUT
    public List<UsuarioDetailDTO> replaceUsuarios(@PathParam("hackatonId") Long hackatonId, List<UsuarioDetailDTO> usuarios) {
        LOGGER.log(Level.INFO, "HackatonUsuariosResource replaceUsuarios: input: hackatonId {0} , usuarios {1}", new Object[]{hackatonId, usuarios});
        for (UsuarioDetailDTO usuario : usuarios) {
            if (usuarioLogic.getUsuario(usuario.getId()) == null) {
                throw new WebApplicationException("El recurso /usuarios/" + usuario.getId() + " no existe.", 404);
            }
        }
        List<UsuarioDetailDTO> lista = usuariosListEntity2DTO(hackatonUsuarioLogic.replaceUsuarios(hackatonId, usuariosListDTO2Entity(usuarios)));
        LOGGER.log(Level.INFO, "HackatonUsuariosResource replaceUsuarios: output: {0}", lista);
        return lista;
    }

    /**
     * Elimina la conexión entre el usuario y e hackaton recibidos en la URL.
     *
     * @param hackatonId El ID del hackaton al cual se le va a desasociar el usuario
     * @param usuariosId El ID del usuario que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el usuario.
     */
    @DELETE
    @Path("{usuariosId: \\d+}")
    public void removeUsuario(@PathParam("hackatonId") Long hackatonId, @PathParam("usuariosId") Long usuariosId) {
        LOGGER.log(Level.INFO, "HackatonUsuariosResource deleteUsuario: input: hackatonId {0} , usuariosId {1}", new Object[]{hackatonId, usuariosId});
        if (usuarioLogic.getUsuario(usuariosId) == null) {
            throw new WebApplicationException("El recurso /usuarios/" + usuariosId + " no existe.", 404);
        }
        hackatonUsuarioLogic.removeUsuario(hackatonId, usuariosId);
        LOGGER.info("HackatonUsuariosResource deleteUsuario: output: void");
    }
    
    /**
     * Convierte una lista de UsuarioEntity a una lista de UsuarioDetailDTO.
     *
     * @param entityList Lista de UsuarioEntity a convertir.
     * @return Lista de UsuarioDetailDTO convertida.
     */
    private List<UsuarioDetailDTO> usuariosListEntity2DTO(List<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de UsuarioDetailDTO a una lista de UsuarioEntity.
     *
     * @param dtos Lista de UsuarioDetailDTO a convertir.
     * @return Lista de UsuarioEntity convertida.
     */
    private List<UsuarioEntity> usuariosListDTO2Entity(List<UsuarioDetailDTO> dtos) {
        List<UsuarioEntity> list = new ArrayList<>();
        for (UsuarioDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
