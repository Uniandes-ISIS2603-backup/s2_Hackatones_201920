/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.LugarDTO;
import co.edu.uniandes.csw.hackatones.ejb.HackatonLogic;
import co.edu.uniandes.csw.hackatones.ejb.HackatonLugarLogic;
import co.edu.uniandes.csw.hackatones.ejb.LugarLogic;
import co.edu.uniandes.csw.hackatones.entities.LugarEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
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
 * @author jd.monsalve
 */
@Path("/lugar")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class LugarResource {
    
private static final Logger LOGGER = Logger.getLogger(LugarResource.class.getName());
     
@Inject
private LugarLogic lugarLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

@Inject
private HackatonLogic hackatonLogic;// Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

@Inject
private HackatonLugarLogic hackatonLugarLogic;// Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
     



/**
 * Crea un nuevo lugar con la informacion que se recibe en el cuerpo de la petición
 * @param lugar {@link LugarDTO}- el objeto lugar que se desea crear
 * @return JSON {@link LugarkDTO} -  El objeto con un id autogenerado
 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
 * Error de logica se genera cuando ya existe el libro o el isbn es invalido o si la editorial ingresada es invalida
 */
   @POST
    public LugarDTO createLugar (LugarDTO lugar) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "LugarResource createLugar: input: {0}", lugar);
        LugarDTO lugDTO = new LugarDTO(lugarLogic.createLugar(lugar.toEntity()));
        LOGGER.log(Level.INFO, "LugarResource createLugar: output: {0}", lugDTO);
        return lugDTO;
    }  
    
    
      /**
     * Busca el lugar con el id asociado recibido en la URL y lo devuelve.
     *
     * @param lugarId Identificador del lugar que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link LugarDetailDTO} - El lugar buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
   @GET
    @Path("{lugarId: \\d+}")
    public LugarDTO getLugar(@PathParam("lugarId") Long lugarId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "LugarResource getLugar: input: {0}", lugarId);
        LugarEntity lugarEntity = lugarLogic.getLugar(lugarId);
        if (lugarEntity == null) {
            throw new WebApplicationException("El recurso /lugar/" + lugarId + " no existe.", 404);
        }
        LugarDTO detailDTO = new LugarDTO(lugarEntity);
        LOGGER.log(Level.INFO, "AuthorResource getAuthor: output: {0}", detailDTO);
        return detailDTO;
    }
    
        /**
     * Actualiza el lugar con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param lugarId Identificador del lugar que se desea actualizar. Este debe
     * ser una cadena de dígitos.
     * @param lugar {@link lugarDTO} El libro que se desea guardar.
     * @return JSON {@link lugarDetailDTO} - El lugar guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el lugar a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el libro.
     */
     @PUT
    @Path("{lugarId: \\d+}")
    public LugarDTO updateLugar(@PathParam("lugarID") Long lugarID, LugarDTO lugar) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "LugarResource updateLugar: input: lugarId: {0} , lugar: {1}", new Object[]{lugarID, lugar});
        lugar.setID(lugarID);
        if (lugarLogic.getLugar(lugarID) == null) {
            throw new WebApplicationException("El recurso /lugar/" + lugarID + " no existe.", 404);
        }
        LugarDTO DTO = new LugarDTO(lugarLogic.updateLugar(lugarID, lugar.toEntity()));
        LOGGER.log(Level.INFO, "LugarResource updateLugar: output: {0}", DTO);
        return DTO;
    }
    
     /**
     * Borra el lugar con el id asociado recibido en la URL.
     *
     * @param lugarId Identificador del lugar que se desea borrar. Este debe ser
     * una cadena de dígitos.
     * @throws BusinesssLoigcException
     * cuando no se cumple alguna regla de negocio.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el lugar.
     */
      @DELETE
    @Path("{lugarId: \\d+}")
    public void deleteLugar(@PathParam("lugarID") Long lugarID, Long hackatonID) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "lugarResource deleteLugar: lugarId: {0}, hackatonID: {1} ", new Object[]{lugarID, hackatonID});
        LugarEntity entidad = lugarLogic.getLugar(lugarID);
        if (entidad == null) {
            throw new WebApplicationException("El recurso /lugar/" + lugarID + " no existe.", 404);
        }      
        hackatonLugarLogic.removeLugar(hackatonID, lugarID);
        lugarLogic.deleteLugar(lugarID);
        LOGGER.info("lugarResource deleteLugar: output: void");
    }   
}
