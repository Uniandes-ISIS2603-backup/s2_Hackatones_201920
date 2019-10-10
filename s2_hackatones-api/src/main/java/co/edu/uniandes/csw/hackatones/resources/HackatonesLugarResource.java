/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
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
 * Clase que implementa el recurso "editorial/{id}/books".
 *
 * @author ISIS2603
 * @version 1.0
 */
@Path("hackatones/{hackatonId: \\d+}/lugar")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HackatonesLugarResource {

    private static final Logger LOGGER = Logger.getLogger(HackatonesLugarResource.class.getName());
    @Inject
    private HackatonLogic hackatonLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private HackatonLugarLogic hackatonLugarLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private LugarLogic lugarLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    private PodamFactory podam = new PodamFactoryImpl();

    
    /**
 * Crea un nuevo lugar con la informacion que se recibe en el cuerpo de la petición
 * @param lugar {@link LugarDTO}- el objeto lugar que se desea crear
 * @return JSON {@link LugarkDTO} -  El objeto con un id autogenerado
 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
 */
   @POST
   @Path("{hackatonId: \\d+}")
    public LugarDTO createLugar (@PathParam("hackatonId") Long hackatonId, LugarDTO lugar) throws BusinessLogicException {
       LOGGER.log(Level.INFO, "LugarResource createLugar: input: hackatonId: {0} , lugar: {1}", new Object[]{hackatonId, lugar});
        LugarDTO lugDTO = new LugarDTO(lugarLogic.createLugar(lugar.toEntity()));
        hackatonLugarLogic.addLugar(hackatonId, lugDTO.getID());
        LOGGER.log(Level.INFO, "LugarResource createLugar: output: {0}", lugDTO);
        return lugDTO;
    } 
    
    
   /**
 * Crea un nuevo lugar con informacion aleatoria
 * @return JSON {@link LugarkDTO} -  El objeto con un id autogenerado
 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
 */
    @POST
    @Path("aleatorio , {hackatonId: \\d+}")
    public LugarDTO createLugarAleatorio(@PathParam("hackatonId") Long hackatonId) throws BusinessLogicException {
      LOGGER.log(Level.INFO, "LugarResource createLugarAleatorio: input: hackatonId: {0}", hackatonId);
        LugarEntity nuevaEntidad = podam.manufacturePojo(LugarEntity.class);
      LugarDTO lugDTO = new LugarDTO(lugarLogic.createLugar(nuevaEntidad));
      hackatonLugarLogic.addLugar(hackatonId, lugDTO.getID());
      LOGGER.log(Level.INFO, "LugarResource createLugarAleatorio: output: {0}", lugDTO);
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
    @Path("{lugarId: \\d+},{hackatonId: \\d+}")
    public LugarDTO updateLugar(@PathParam("lugarId") Long lugarID, @PathParam("hackatonId") Long hackatonId, LugarDTO lugar) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "LugarResource updateLugar: input: lugarId: {0} , hackatonId : {1} ,  lugar: {2}", new Object[]{lugarID,hackatonId,lugar});
        lugar.setID(lugarID);
        if (lugarLogic.getLugar(lugarID) == null) {
            throw new WebApplicationException("El recurso /lugar/" + lugarID + " no existe.", 404);
        } 
        LugarDTO DTO = new LugarDTO(lugarLogic.updateLugar(lugarID, lugar.toEntity()));
        hackatonLugarLogic.updateLugar(hackatonId, DTO.getID());
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
    @Path("{lugarId: \\d+},{hackatonId: \\d+}")
    public void deleteLugar(@PathParam("lugarId") Long lugarID,@PathParam("hackatonId") Long hackatonID) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "lugarResource deleteLugar: lugarId: {0}, hackatonId: {1} ", new Object[]{lugarID, hackatonID});
        LugarEntity entidad = lugarLogic.getLugar(lugarID);
        if (entidad == null) {
            throw new WebApplicationException("El recurso /lugar/" + lugarID + " no existe.", 404);
        }      
        hackatonLugarLogic.removeLugar(hackatonID, lugarID);
        lugarLogic.deleteLugar(lugarID);
        LOGGER.info("lugarResource deleteLugar: output: void");
    }   
}

