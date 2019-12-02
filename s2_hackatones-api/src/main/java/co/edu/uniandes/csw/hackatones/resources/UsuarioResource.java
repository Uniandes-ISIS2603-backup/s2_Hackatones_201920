/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.UsuarioDTO;
import co.edu.uniandes.csw.hackatones.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.hackatones.ejb.UsuarioLogic;
import co.edu.uniandes.csw.hackatones.entities.UsuarioEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.WebApplicationException;
/**
 *
 * @author s.estupinan
 */
@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class UsuarioResource 
{
    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());
    
    @Inject
    private UsuarioLogic logic;
    
    @POST
    public UsuarioDTO createUsuario(UsuarioDTO dto) throws BusinessLogicException {
        return new UsuarioDTO(logic.createUsuario(dto.toEntity()));
    }
    
    @GET
    public List<UsuarioDetailDTO> getUsuarios()
    {
        return listEntity2DTO(logic.getUsuarios());
    }
    
    
    @GET
    @Path("{usuarioId: \\d+}")
    public UsuarioDetailDTO getUsuario(@PathParam("usuarioId") Long id) {
        LOGGER.log(Level.INFO, "UsuarioResource getUsuario: input: {0}", id);
        UsuarioEntity entity = logic.getUsuario(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + id + " no existe.", 404);
        }
        UsuarioDetailDTO detailDTO = new UsuarioDetailDTO(entity);
        LOGGER.log(Level.INFO, "UsuarioResource getUsuario: output: {0}", detailDTO);
        return detailDTO;
    }
    
    @PUT
    @Path("{usuarioId: \\d+}")
    public UsuarioDetailDTO updateUsuario(@PathParam("usuarioId") Long id, UsuarioDetailDTO usuario) {
        LOGGER.log(Level.INFO, "UsuarioResource updateUsuario: input: usuarioId: {0} , usuario: {1}", new Object[]{id, usuario});
        usuario.setId(id);
        if (logic.getUsuario(id) == null) {
            throw new WebApplicationException("El recurso /usuarios/" + id + " no existe.", 404);
        }
        UsuarioDetailDTO detailDTO = new UsuarioDetailDTO(logic.updateUsuario(id, usuario.toEntity()));
        LOGGER.log(Level.INFO, "UsuarioResource updateUsuario: output: {0}", detailDTO);
        return detailDTO;
    }
    
    @DELETE
    @Path("{usuarioId: \\d+}")
    public void deleteUsuario(@PathParam("usuarioId") Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioResource deleteUsuario: input: {0}", id);
        if (logic.getUsuario(id) == null) {
            throw new WebApplicationException("El recurso /usuarios/" + id + " no existe.", 404);
        }
        logic.deleteUsuario(id);
        LOGGER.info("UsuarioResource deleteUsuario: output: void");
    }
    
    @Path("{usuariosId: \\d+}/hackatones")
    public Class<UsuarioHackatonResource> getUsuarioHackatonResource(@PathParam("usuariosId") Long usuariosId) {
        if (logic.getUsuario(usuariosId) == null) {
            throw new WebApplicationException("El recurso /usuarios/" + usuariosId + " no existe.", 404);
        }
        return UsuarioHackatonResource.class;
    }
    
    @Path("{usuariosId: \\d+}/tecnologias")
    public Class<UsuarioTecnologiaResource> getUsuarioTecnologiaResource(@PathParam("usuariosId") Long usuariosId) {
        if (logic.getUsuario(usuariosId) == null) {
            throw new WebApplicationException("El recurso /usuarios/" + usuariosId + " no existe.", 404);
        }
        return UsuarioTecnologiaResource.class;
    }

    private List<UsuarioDetailDTO> listEntity2DTO(List<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }
}
