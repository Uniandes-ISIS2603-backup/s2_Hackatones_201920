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

/**
 * Clase que implementa el recurso "hackatones/{id}/lugar".
 *
 * @author jd.monsalve
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HackatonesLugarResource {

    private static final Logger LOGGER = Logger.getLogger(HackatonesLugarResource.class.getName());

    @Inject
    private HackatonLugarLogic hackatonLugarLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private LugarLogic lugarLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un lugar dentro de una hackaton con la informacion que recibe en la
     * URL.
     *
     * @param hackatonId Identificador de la hackaton que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param lugarId Identificador del lugar que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link AuthorDTO} - El lugar guardado en la hackaton.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el lugar.
     */
    @POST
    @Path("{lugarId: \\d+}")
    public LugarDTO addLugar(@PathParam("hackatonId") Long hackatonId, @PathParam("lugarId") Long lugarId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "HackatonesLugarResource addLugar: input: hackatonId: {0} , lugarId: {1}", new Object[]{hackatonId, lugarId});
        if (lugarLogic.getLugar(lugarId) == null) {
            throw new WebApplicationException("El recurso /lugar/" + lugarId + " no existe.", 404);
        }
        LugarDTO lugarDTO = new LugarDTO(hackatonLugarLogic.addLugar(lugarId, hackatonId));
        LOGGER.log(Level.INFO, "HackatonesLugarResource addLugar: output: {0}", lugarDTO);
        return lugarDTO;
    }

    /**
     * Busca el lugar dentro de una hackaton id asociado.
     *
     * @param hackatonId Identificador de la hackaton que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link AuthorDetailDTO} - El lugar buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando la hackaton no tiene lugar.
     */
    @GET
    public LugarDTO getLugar(@PathParam("hackatonId") Long hackatonId) {
        LOGGER.log(Level.INFO, "HackatonesLugarResource getLugar: input: {0}", hackatonId);
        LugarEntity lugarEntity = hackatonLugarLogic.getLugar(hackatonId);
        if (lugarEntity == null) {
            throw new WebApplicationException("El recurso hackaton con  id /" + hackatonId + "/no tiene un lugar en el momento.", 404);
        }
        LugarDTO lugarDTO = new LugarDTO(lugarEntity);
        LOGGER.log(Level.INFO, "HackatonesLugarResource getLugar: output: {0}", lugarDTO);
        return lugarDTO;
    }

    /**
     * Remplaza la instancia de lugar asociada a una instancia de hackaton
     *
     * @param hackatonId Identificador de la hackatono que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param lugarId Identificador del lugar que se esta remplazando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link AuthorDetailDTO} - El lugar actualizado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el lugar.
     */
    @PUT
    @Path("{authorsId: \\d+}")
    public LugarDTO replaceLugar(@PathParam("hackatonId") Long hackatonId, @PathParam("lugarId") Long lugarId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "HackatonesLugarResource replaceLugar: input: hackatonId: {0} , lugarId: {1}", new Object[]{hackatonId, lugarId});
        if (lugarLogic.getLugar(lugarId) == null) {
            throw new WebApplicationException("El recurso /lugar/" + lugarId + " no existe.", 404);
        }
        LugarDTO lugarDTO = new LugarDTO(hackatonLugarLogic.replaceLugar(hackatonId, lugarId));
        LOGGER.log(Level.INFO, "HackatonesLugarResource replaceLugar: output: {0}", lugarDTO);
        return lugarDTO;
    }

    /**
     * Elimina la conexión entre el lugar y la hackaton recibido en la URL.
     *
     * @param hackatonId El ID de la hackaton la cual se le va a desasociar el lugar
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * Error de lógica que se genera cuando la hackaton no tiene lugar.
     */
    @DELETE
    public void removeLugar(@PathParam("hackatonId") Long hackatonId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "HackatonesLugarResource removeLugar: input: {0}", hackatonId);
        hackatonLugarLogic.removeLugar(hackatonId);
        LOGGER.info("HackatonesLugarResource removeLugar: output: void");
    }
 
}  