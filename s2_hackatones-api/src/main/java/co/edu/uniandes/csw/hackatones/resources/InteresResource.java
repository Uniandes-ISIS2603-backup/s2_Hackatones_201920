/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.InteresDTO;
import co.edu.uniandes.csw.hackatones.dtos.InteresDetailDTO;
import co.edu.uniandes.csw.hackatones.ejb.InteresLogic;
import co.edu.uniandes.csw.hackatones.entities.InteresEntity;
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


/**
 *
 * @author ja.torresl
 */
@Path("interes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class InteresResource {
    
   private static final Logger LOGGER = Logger.getLogger(InteresResource.class.getName());

    @Inject
    private InteresLogic interesLogic;

    /**
     * Crea un nuevo autor con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param interes {@link InteresDTO} - EL autor que se desea guardar.
     * @return JSON {@link InteresDTO} - El autor guardado con el atributo id
     * autogenerado.
     */
    @POST
    public InteresDTO createInteres(InteresDTO interes) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "InteresResource createInteres: input: {0}", interes);
        InteresDTO interesDTO = new InteresDTO(interesLogic.createInteres(interes.toEntity()));
        LOGGER.log(Level.INFO, "InteresResource createInteres: output: {0}", interesDTO);
        return interesDTO;
    }

    /**
     * Busca y devuelve todos los autores que existen en la aplicacion.
     *
     * @return JSONArray {@link InteresDetailDTO} - Los autores encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<InteresDetailDTO> getInteress() {
        LOGGER.info("InteresResource getInteress: input: void");
        List<InteresDetailDTO> listaInteress = listEntity2DTO(interesLogic.getIntereses());
        LOGGER.log(Level.INFO, "InteresResource getInteress: output: {0}", listaInteress);
        return listaInteress;
    }

    /**
     * Busca el autor con el id asociado recibido en la URL y lo devuelve.
     *
     * @param interesesId Identificador del autor que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link InteresDetailDTO} - El autor buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @GET
    @Path("{interesesId: \\d+}")
    public InteresDetailDTO getInteres(@PathParam("interesesId") Long interesesId) {
        LOGGER.log(Level.INFO, "InteresResource getInteres: input: {0}", interesesId);
        InteresEntity interesEntity = interesLogic.getInteres(interesesId);
        if (interesEntity == null) {
            throw new WebApplicationException("El recurso /intereses/" + interesesId + " no existe.", 404);
        }
        InteresDetailDTO detailDTO = new InteresDetailDTO(interesEntity);
        LOGGER.log(Level.INFO, "InteresResource getInteres: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza el autor con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param interesesId Identificador del autor que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param interes {@link InteresDetailDTO} El autor que se desea guardar.
     * @return JSON {@link InteresDetailDTO} - El autor guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor a
     * actualizar.
     */
    @PUT
    @Path("{interesesId: \\d+}")
    public InteresDetailDTO updateInteres(@PathParam("interesesId") Long interesesId, InteresDetailDTO interes) {
        LOGGER.log(Level.INFO, "InteresResource updateInteres: input: interesesId: {0} , interes: {1}", new Object[]{interesesId, interes});
        interes.setId(interesesId);
        if (interesLogic.getInteres(interesesId) == null) {
            throw new WebApplicationException("El recurso /intereses/" + interesesId + " no existe.", 404);
        }
        InteresDetailDTO detailDTO = new InteresDetailDTO(interesLogic.updateInteres(interesesId, interes.toEntity()));
        LOGGER.log(Level.INFO, "InteresResource updateInteres: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Borra el autor con el id asociado recibido en la URL.
     *
     * @param interesesId Identificador del autor que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * si el autor tiene libros asociados
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el autor a borrar.
     */
    @DELETE
    @Path("{interesesId: \\d+}")
    public void deleteInteres(@PathParam("interesesId") Long interesesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "InteresResource deleteInteres: input: {0}", interesesId);
        if (interesLogic.getInteres(interesesId) == null) {
            throw new WebApplicationException("El recurso /intereses/" + interesesId + " no existe.", 404);
        }
        interesLogic.deleteInteres(interesesId);
        LOGGER.info("InteresResource deleteInteres: output: void");
    }

    /**
     * Conexión con el servicio de libros para un autor.
     * {@link InteresBooksResource}
     *
     * Este método conecta la ruta de /intereses con las rutas de /books que
     * dependen del autor, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de los libros.
     *
     * @param interesesId El ID del autor con respecto al cual se accede al
     * servicio.
     * @return El servicio de Libros para ese autor en paricular.
     */
    /**
    @Path("{interesesId: \\d+}/books")
    public Class<InteresBooksResource> getInteresBooksResource(@PathParam("interesesId") Long interesesId) {
        if (interesLogic.getInteres(interesesId) == null) {
            throw new WebApplicationException("El recurso /intereses/" + interesesId + " no existe.", 404);
        }
        return InteresBooksResource.class;
    }

    * /
    /**
     * Convierte una lista de InteresEntity a una lista de InteresDetailDTO.
     *
     * @param entityList Lista de InteresEntity a convertir.
     * @return Lista de InteresDetailDTO convertida.
     */
    private List<InteresDetailDTO> listEntity2DTO(List<InteresEntity> entityList) {
        List<InteresDetailDTO> list = new ArrayList<>();
        for (InteresEntity entity : entityList) {
            list.add(new InteresDetailDTO(entity));
        }
        return list;
    }

}
