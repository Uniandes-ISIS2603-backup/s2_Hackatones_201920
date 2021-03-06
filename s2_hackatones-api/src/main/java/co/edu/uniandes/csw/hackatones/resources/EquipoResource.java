/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.EquipoDTO;
import co.edu.uniandes.csw.hackatones.dtos.EquipoDetailDTO;
import co.edu.uniandes.csw.hackatones.ejb.EquipoLogic;
import co.edu.uniandes.csw.hackatones.entities.EquipoEntity;
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
 * @author a.pedraza
 */
@Path("/equipos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class EquipoResource {
    
private static final Logger LOGGER = Logger.getLogger(EquipoResource.class.getName());

private String text1=" no existe.";
private String text2="El recurso /equipos/";

@Inject
    private EquipoLogic equipoLogic;

    /**
     * Crea un nuevo autor con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param equipo {@link EquipoDTO} - EL autor que se desea guardar.
     * @return JSON {@link EquipoDTO} - El autor guardado con el atributo id
     * autogenerado.
     */
    @POST
    public EquipoDetailDTO createEquipo(EquipoDetailDTO equipo) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EquipoResource createEquipo: input: {0}", equipo);
        EquipoDetailDTO equipoDTO = new EquipoDetailDTO(equipoLogic.createEquipo(equipo.toEntity()));
        LOGGER.log(Level.INFO, "EquipoResource createEquipo: output: {0}", equipoDTO);
        return equipoDTO;
    }

    /**
     * Busca y devuelve todos los autores que existen en la aplicacion.
     *
     * @return JSONArray {@link EquipoDTO} - Los autores encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<EquipoDetailDTO> getEquipos() {
        LOGGER.info("EquipoResource getEquipos: input: void");
        List<EquipoDetailDTO> listaEquipos = listEntity2DTO(equipoLogic.getEquipoes());
        LOGGER.log(Level.INFO, "EquipoResource getEquipos: output: {0}", listaEquipos);
        return listaEquipos;
    }

    /**
     * Busca el autor con el id asociado recibido en la URL y lo devuelve.
     *
     * @param equiposId Identificador del autor que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link EquipoDTO} - El autor buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @GET
    @Path("{equiposId: \\d+}")
    public EquipoDetailDTO getEquipo(@PathParam
        ("equiposId") Long equiposId) {
        LOGGER.log(Level.INFO, "EquipoResource getEquipo: input: {0}", equiposId);
        EquipoEntity equipoEntity = equipoLogic.getEquipo(equiposId);
        if (equipoEntity == null) {
            throw new WebApplicationException(text2 + equiposId + text1, 404);
        }
        EquipoDetailDTO detailDTO = new EquipoDetailDTO(equipoEntity);
        LOGGER.log(Level.INFO, "EquipoResource getEquipo: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza el autor con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param equiposId Identificador del autor que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param equipo {@link EquipoDTO} El autor que se desea guardar.
     * @return JSON {@link EquipoDTO} - El autor guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor a
     * actualizar.
     */
    @PUT
    @Path("{equiposId: \\d+}")
    public EquipoDetailDTO updateEquipo(@PathParam("equiposId") Long equiposId, EquipoDetailDTO equipo) {
        LOGGER.log(Level.INFO, "EquipoResource updateEquipo: input: equiposId: {0} , equipo: {1}", new Object[]{equiposId, equipo});
        equipo.setId(equiposId);
        if (equipoLogic.getEquipo(equiposId) == null) {
            throw new WebApplicationException(text2 + equiposId + text1, 404);
        }
        EquipoDetailDTO detailDTO = new EquipoDetailDTO(equipoLogic.updateEquipo(equiposId, equipo.toEntity()));
        LOGGER.log(Level.INFO, "EquipoResource updateEquipo: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Borra el autor con el id asociado recibido en la URL.
     *
     * @param equiposId Identificador del autor que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * si el autor tiene libros asociados
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el autor a borrar.
     */
    @DELETE
    @Path("{equiposId: \\d+}")
    public void deleteEquipo(@PathParam("equiposId") Long equiposId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EquipoResource deleteEquipo: input: {0}", equiposId);
        if (equipoLogic.getEquipo(equiposId) == null) {
            throw new WebApplicationException(text2 + equiposId + text1, 404);
        }
        equipoLogic.deleteEquipo(equiposId);
        LOGGER.info("EquipoResource deleteEquipo: output: void");
    }
    
    @Path("{equiposId: \\d+}/hackatones")
    public Class<EquipoHackatonResource> getEquipoHackatonResource(@PathParam("equiposId") Long equiposId) {
        if (equipoLogic.getEquipo(equiposId) == null) {
            throw new WebApplicationException(text2 + equiposId + text1, 404);
        }
        return EquipoHackatonResource.class;
    }
    
    /**
     * Convierte una lista de EquipoEntity a una lista de EquipoDTO.
     *
     * @param entityList Lista de EquipoEntity a convertir.
     * @return Lista de EquipoDTO convertida.
     */
    private List<EquipoDetailDTO> listEntity2DTO(List<EquipoEntity> entityList) {
        List<EquipoDetailDTO> list = new ArrayList<>();
        for (EquipoEntity entity : entityList) {
            list.add(new EquipoDetailDTO(entity));
        }
        return list;
    }
}
