/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.TecnologiaDTO;
import co.edu.uniandes.csw.hackatones.dtos.TecnologiaDetailDTO;
import co.edu.uniandes.csw.hackatones.ejb.TecnologiaLogic;
import co.edu.uniandes.csw.hackatones.entities.TecnologiaEntity;
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
 * @author ja.torresl
 */
@Path("/tecnologias")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class TecnologiaResource {
    
  
    private static final Logger LOGGER = Logger.getLogger(TecnologiaResource.class.getName());

    @Inject
    private TecnologiaLogic tecnologiaLogic;

    /**
     * Crea un nuevo autor con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param tecnologia {@link TecnologiaDTO} - EL autor que se desea guardar.
     * @return JSON {@link TecnologiaDTO} - El autor guardado con el atributo id
     * autogenerado.
     */
    @POST
    public TecnologiaDTO createTecnologia(TecnologiaDTO tecnologia)  throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TecnologiaResource createTecnologia: input: {0}", tecnologia);
        TecnologiaDTO tecnologiaDTO = new TecnologiaDTO(tecnologiaLogic.createTecnologia(tecnologia.toEntity()));
        LOGGER.log(Level.INFO, "TecnologiaResource createTecnologia: output: {0}", tecnologiaDTO);
        return tecnologiaDTO;
    }

    /**
     * Busca y devuelve todos los autores que existen en la aplicacion.
     *
     * @return JSONArray {@link TecnologiaDetailDTO} - Los autores encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<TecnologiaDetailDTO> getTecnologias() {
        LOGGER.info("TecnologiaResource getTecnologias: input: void");
        List<TecnologiaDetailDTO> listaTecnologias = listEntity2DTO(tecnologiaLogic.getTecnologias());
        LOGGER.log(Level.INFO, "TecnologiaResource getTecnologias: output: {0}", listaTecnologias);
        return listaTecnologias;
    }

    /**
     * Busca el autor con el id asociado recibido en la URL y lo devuelve.
     *
     * @param tecnologiasId Identificador del autor que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link TecnologiaDetailDTO} - El autor buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @GET
    @Path("{tecnologiasId: \\d+}")
    public TecnologiaDetailDTO getTecnologia(@PathParam("tecnologiasId") Long tecnologiasId) {
        LOGGER.log(Level.INFO, "TecnologiaResource getTecnologia: input: {0}", tecnologiasId);
        TecnologiaEntity tecnologiaEntity = tecnologiaLogic.getTecnologia(tecnologiasId);
        if (tecnologiaEntity == null) {
            throw new WebApplicationException("El recurso /tecnologias/" + tecnologiasId + " no existe.", 404);
        }
        TecnologiaDetailDTO detailDTO = new TecnologiaDetailDTO(tecnologiaEntity);
        LOGGER.log(Level.INFO, "TecnologiaResource getTecnologia: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza el autor con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param tecnologiasId Identificador del autor que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param tecnologia {@link TecnologiaDetailDTO} El autor que se desea guardar.
     * @return JSON {@link TecnologiaDetailDTO} - El autor guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor a
     * actualizar.
     */
    @PUT
    @Path("{tecnologiasId: \\d+}")
    public TecnologiaDetailDTO updateTecnologia(@PathParam("tecnologiasId") Long tecnologiasId, TecnologiaDetailDTO tecnologia) {
        LOGGER.log(Level.INFO, "TecnologiaResource updateTecnologia: input: tecnologiasId: {0} , tecnologia: {1}", new Object[]{tecnologiasId, tecnologia});
        tecnologia.setId(tecnologiasId);
        if (tecnologiaLogic.getTecnologia(tecnologiasId) == null) {
            throw new WebApplicationException("El recurso /tecnologias/" + tecnologiasId + " no existe.", 404);
        }
        TecnologiaDetailDTO detailDTO = new TecnologiaDetailDTO(tecnologiaLogic.updateTecnologia(tecnologiasId, tecnologia.toEntity()));
        LOGGER.log(Level.INFO, "TecnologiaResource updateTecnologia: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Borra el autor con el id asociado recibido en la URL.
     *
     * @param tecnologiasId Identificador del autor que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * si el autor tiene libros asociados
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el autor a borrar.
     */
    @DELETE
    @Path("{tecnologiasId: \\d+}")
    public void deleteTecnologia(@PathParam("tecnologiasId") Long tecnologiasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TecnologiaResource deleteTecnologia: input: {0}", tecnologiasId);
        if (tecnologiaLogic.getTecnologia(tecnologiasId) == null) {
            throw new WebApplicationException("El recurso /tecnologias/" + tecnologiasId + " no existe.", 404);
        }
        tecnologiaLogic.deleteTecnologia(tecnologiasId);
        LOGGER.info("TecnologiaResource deleteTecnologia: output: void");
    }

  
    /**
     * Convierte una lista de TecnologiaEntity a una lista de TecnologiaDetailDTO.
     *
     * @param entityList Lista de TecnologiaEntity a convertir.
     * @return Lista de TecnologiaDetailDTO convertida.
     */
    private List<TecnologiaDetailDTO> listEntity2DTO(List<TecnologiaEntity> entityList) {
        List<TecnologiaDetailDTO> list = new ArrayList<>();
        for (TecnologiaEntity entity : entityList) {
            list.add(new TecnologiaDetailDTO(entity));
        }
        return list;
    }
}
