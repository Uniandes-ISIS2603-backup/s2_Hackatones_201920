/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import co.edu.uniandes.csw.hackatones.dtos.HackatonDTO;
import co.edu.uniandes.csw.hackatones.dtos.HackatonDetailDTO;
import co.edu.uniandes.csw.hackatones.ejb.HackatonLogic;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author jc.higuera
 */
@Path("hackatones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class HackatonesResource {
    
    private static final Logger LOGGER = Logger.getLogger(HackatonEntity.class.getName());
    
    @Inject
    private HackatonLogic hackatonLogic;
    
    
    /**
     * Crea una nueva hackaton con la informacion que se recibe en el cuerpo
     * de la petición y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     *
     * @param hackaton {@link HackatonnDTO} - La hackaton que se
     * desea guardar.
     * @return JSON {@link HackatonDTO} - La hackaton guardada con el
     * atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la hackaton.
     */
    @POST
    public HackatonDTO createHackaton(HackatonDTO hackaton) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "HackatonResource createHackaton: input: {0}", hackaton);
        HackatonDTO nuevoDTO = new HackatonDTO(hackatonLogic.createHackaton(hackaton.toEntity()));
        LOGGER.log(Level.INFO, "HackatonResource createHackaton: output: {0}", nuevoDTO);
        return nuevoDTO;
    }
    
    
    /**
     * Busca y devuelve todos las Hackatones que existen en la aplicacion.
     *
     * @return JSONArray {@link HackatonDTO} - Las Hackatones
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<HackatonDetailDTO> getHackatones()
    {
        LOGGER.info("HackatonResource getHackatones: input: void");
        List<HackatonDetailDTO> listaHackatones = listEntity2DetailDTO(hackatonLogic.getHackatones());
        LOGGER.log(Level.INFO, "HackatonesResource getHackatones: output: {0}", listaHackatones);
        return listaHackatones;
    }
    
    /**

     * Busca la Hackaton con el id asociado recibido en la URL y lo
     * devuelve.
     *
     * @param HackatonsId Identificador de la Hackaton que se esta
     * buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link HackatonDetailDTO} - La Hackaton buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la Hackaton.
     */
    @GET
    @Path("{hackatonesId: \\d+}")
    public HackatonDetailDTO getHackaton(@PathParam("hackatonesId")Long hackatonesId)
    {
        LOGGER.log(Level.INFO, "HackatonResource getHackaton: input: {0}", hackatonesId);
        HackatonEntity hackatonEntity = hackatonLogic.getHackaton(hackatonesId);
        if (hackatonEntity == null) {
            throw new WebApplicationException("El recurso /hackatones/" + hackatonesId + " no existe.", 404);
        }
        HackatonDetailDTO hackatonDetailDTO = new HackatonDetailDTO(hackatonEntity);
        LOGGER.log(Level.INFO, "HackatonesResource getHackatones: output: {0}", hackatonDetailDTO);
        return hackatonDetailDTO;
    }
    
    /**
     * Actualiza la Hackaton con el id recibido en la URL con la información
     * que se recibe en el cuerpo de la petición.
     *
     * @param HackatonsId Identificador de la Hackaton que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param Hackaton {@link HackatonDTO} La Hackaton que se desea
     * guardar.
     * @return JSON {@link HackatonDTO} - La Hackaton guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la Hackaton a
     * actualizar.
     */
    @PUT
    @Path("{hackatonesId: \\d+}")
    public HackatonDetailDTO updateHackaton(@PathParam("hackatonesId") Long hackatonesId, HackatonDetailDTO hackaton) throws BusinessLogicException 
    
    {
        LOGGER.log(Level.INFO, "HackatonResource updateHackaton: input: id: {0} , hackaton: {1}", new Object[]{hackatonesId, hackaton});
        hackaton.setId(hackatonesId);
        if (hackatonLogic.getHackaton(hackatonesId) == null) {
            throw new WebApplicationException("El recurso /hackatones/" + hackatonesId + " no existe.", 404);
        }
       HackatonDetailDTO detailDTO = new HackatonDetailDTO(hackatonLogic.updateHackaton(hackatonesId, hackaton.toEntity()));
        LOGGER.log(Level.INFO, "HackatonesResource updateHackaton: output: {0}", detailDTO);
        return detailDTO;
    }
    
    /**
     * Borra la Hackaton con el id asociado recibido en la URL.
     *
     * @param HackatonsId Identificador de la Hackaton que se desea
     * borrar. Este debe ser una cadena de dígitos.
     * @throws WebApplicationException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la
     * Hackaton.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * Error de lógica que se genera cuando la organizacion tiene un premio
     * asociado.
     */
    @DELETE
    @Path("{hackatonesId: \\d+}")
    public void deleteHackaton(@PathParam("hackatonesId") Long hackatonesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "HackatonResource deleteHackaton: input: {0}", hackatonesId);
        HackatonEntity entity = hackatonLogic.getHackaton(hackatonesId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /hackaton/" + hackatonesId + " no existe.", 404);
        }
        hackatonLogic.deleteHackaton(hackatonesId);
        LOGGER.info("HackatonesResource deleteHackaton: output: void");
    }
    
    
        @Path("{hackatonesId: \\d+}/calificaciones")
    public Class<CalificacionResource> getCalificacionResource(@PathParam("hackatonesId") Long hackatonesId) {
        if (hackatonLogic.getHackaton(hackatonesId) == null) {
            throw new WebApplicationException("El recurso /hackatones/" + hackatonesId + "/calificaciones no existe.", 404);
        }
        return CalificacionResource.class;
    }
    
    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos HackatonEntity a una lista
     * de objetos HackatonDetailDTO (json)
     *
     * @param entityList corresponde a la lista de Hackatones de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de Hackatones en forma DTO (json)
     */
    private List<HackatonDetailDTO> listEntity2DetailDTO(List<HackatonEntity> entityList) {
        List<HackatonDetailDTO> list = new ArrayList<>();
        for (HackatonEntity entity : entityList) {
            list.add(new HackatonDetailDTO(entity));
        }
        return list;
    }
}
