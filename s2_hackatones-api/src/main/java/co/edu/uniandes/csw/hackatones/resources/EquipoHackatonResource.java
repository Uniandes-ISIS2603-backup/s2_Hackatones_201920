/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;


import co.edu.uniandes.csw.hackatones.dtos.HackatonDTO;
import co.edu.uniandes.csw.hackatones.dtos.HackatonDetailDTO;
import co.edu.uniandes.csw.hackatones.ejb.HackatonLogic;
import co.edu.uniandes.csw.hackatones.ejb.EquipoHackatonLogic;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
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
public class EquipoHackatonResource {
    
    private static final Logger LOGGER = Logger.getLogger(EquipoHackatonResource.class.getName());

    @Inject
    private EquipoHackatonLogic equipoHackatonLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private HackatonLogic hackatonLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un hackaton dentro de un premio con la informacion que recibe el la
     * URL.
     *
     * @param equiposId Identificador de el premio que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param hackatonesId Identificador del autor que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link HackatonDTO} - El autor guardado en el premio.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @POST
    @Path("{hackatonesId: \\d+}")
    public HackatonDTO addHackaton(@PathParam("equiposId") Long equiposId, @PathParam("hackatonesId") Long hackatonesId) {
        LOGGER.log(Level.INFO, "EquipoHackatonResource addHackaton: input: equiposID: {0} , hackatonesId: {1}", new Object[]{equiposId, hackatonesId});
        if (hackatonLogic.getHackaton(hackatonesId) == null) {
            throw new WebApplicationException("El recurso /hackatones/" + hackatonesId + " no existe.", 404);
        }
        HackatonDTO hackatonDTO = new HackatonDTO(equipoHackatonLogic.addHackaton(hackatonesId, equiposId));
        LOGGER.log(Level.INFO, "EquipoHackatonResource addHackaton: output: {0}", hackatonDTO);
        return hackatonDTO;
    }

    /**
     * Busca el autor dentro de el premio con id asociado.
     *
     * @param equiposId Identificador de el premio que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link HackatonDetailDTO} - El autor buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando el premio no tiene autor.
     */
    @GET
    public HackatonDetailDTO getHackaton(@PathParam("equiposId") Long equiposId) {
        LOGGER.log(Level.INFO, "EquipoHackatonResource getHackaton: input: {0}", equiposId);
        HackatonEntity hackatonEntity = equipoHackatonLogic.getHackaton(equiposId);
        if (hackatonEntity == null) {
            throw new WebApplicationException("El recurso /equipos/" + equiposId + "/hackaton no existe.", 404);
        }
        HackatonDetailDTO hackatonDetailDTO = new HackatonDetailDTO(hackatonEntity);
        LOGGER.log(Level.INFO, "EquipoHackatonResource getHackaton: output: {0}", hackatonDetailDTO);
        return hackatonDetailDTO;
    }

    /**
     * Remplaza la instancia de Hackaton asociada a una instancia de Equipo
     *
     * @param equiposId Identificador de el premio que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param hackatonesId Identificador de el hackaton que se esta remplazando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link HackatonDetailDTO} - El autor actualizado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @PUT
    @Path("{hackatonesId: \\d+}")
    public HackatonDetailDTO replaceHackaton(@PathParam("equiposId") Long equiposId, @PathParam("hackatonesId") Long hackatonesId) {
        LOGGER.log(Level.INFO, "EquipoHackatonResource replaceHackaton: input: equiposId: {0} , hackatonesId: {1}", new Object[]{equiposId, hackatonesId});
        if (hackatonLogic.getHackaton(hackatonesId) == null) {
            throw new WebApplicationException("El recurso /hackatones/" + hackatonesId + " no existe.", 404);
        }
        HackatonDetailDTO hackatonDetailDTO = new HackatonDetailDTO(equipoHackatonLogic.replaceHackaton(equiposId, hackatonesId));
        LOGGER.log(Level.INFO, "EquipoHackatonResource replaceHackaton: output: {0}", hackatonDetailDTO);
        return hackatonDetailDTO;
    }

    /**
     * Elimina la conexión entre el autor y el premio recibido en la URL.
     *
     * @param equiposId El ID del premio al cual se le va a desasociar el autor
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * Error de lógica que se genera cuando el premio no tiene autor.
     */
    @DELETE
    public void removeHackaton(@PathParam("equiposId") Long equiposId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EquipoHackatonResource removeHackaton: input: {0}", equiposId);
        equipoHackatonLogic.removeHackaton(equiposId);
        LOGGER.info("EquipoHackatonResource removeHackaton: output: void");
    }
}
