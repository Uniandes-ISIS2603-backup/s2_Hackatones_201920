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
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jd.monsalve
 */
@Path("lugar")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class LugarResource {
    
private static final Logger LOGGER = Logger.getLogger(LugarResource.class.getName());

private PodamFactory podam = new PodamFactoryImpl();
     
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
     * Error de lógica que se genera cuando no se encuentra el lugar.
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
     * Busca y devuelve todos los lugares que existen en la aplicacion.
     *
     * @return JSONArray {@link BookDetailDTO} - Los lugares encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<LugarDTO> geLugares() throws BusinessLogicException {
        LOGGER.info("LugarResource geLugares: input: void");
        List<LugarDTO> listaLugares = listEntity2DetailDTO(lugarLogic.getLugares());
        LOGGER.log(Level.INFO, "BookResource geLugares: output: {0}", listaLugares);
        return listaLugares;
    }
    
    
        /**
     * Actualiza el lugar con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param lugarId Identificador del lugar que se desea actualizar. Este debe
     * ser una cadena de dígitos.
     * @param lugar {@link lugarDTO} El lugar que se desea guardar.
     * @return JSON {@link lugarDetailDTO} - El lugar guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el lugar a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el lugar.
     */
     @PUT
    @Path("{lugarId: \\d+}")
    public LugarDTO updateLugar(@PathParam("lugarId") Long lugarID, LugarDTO lugar) throws BusinessLogicException {
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
    public void deleteLugar(@PathParam("lugarId") Long lugarId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "lugarResource deleteLugar: lugarId: {0}", lugarId);
        LugarEntity entidad = lugarLogic.getLugar(lugarId);
        if (entidad == null) {
            throw new WebApplicationException("El recurso /lugar/" + lugarId + " no existe.", 404);
        }      
        lugarLogic.deleteLugar(lugarId);
        LOGGER.info("lugarResource deleteLugar: output: void");
    }    
    
    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos BookEntity a una lista de
     * objetos BookDetailDTO (json)
     *
     * @param entityList corresponde a la lista de libros de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de libros en forma DTO (json)
     */
    private List<LugarDTO> listEntity2DetailDTO(List<LugarEntity> entityList) {
        List<LugarDTO> list = new ArrayList<LugarDTO>();
        for (LugarEntity entity : entityList) {
            list.add(new LugarDTO(entity));
        }
        return list;
    }
    
}
