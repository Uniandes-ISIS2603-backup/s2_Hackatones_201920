/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.resources;

import co.edu.uniandes.csw.hackatones.dtos.CatalogoDTO;
import co.edu.uniandes.csw.hackatones.dtos.CatalogoDetailDTO;
import co.edu.uniandes.csw.hackatones.dtos.CredencialesDTO;
import co.edu.uniandes.csw.hackatones.ejb.CatalogoLogic;
import co.edu.uniandes.csw.hackatones.entities.CatalogoEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
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
 * @author ne.cardenas
 */
@Path("catalogos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CatalogoResource {
    private static final Logger LOGGER = Logger.getLogger(CatalogoResource.class.getName());
    
    /**
     * logica de catalogo
     */
    @Inject
    private CatalogoLogic logic;
    
    /**
     * crea un catalogo
     * @param dto el dto de catalogo
     * @return el dto de catalogo
     * @throws BusinessLogicException
     */
    @POST
    public CatalogoDTO createCatalogo(CatalogoDTO dto) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CatalogoResource createCatalogo: input: {0}", dto);
        CatalogoEntity entity = dto.toEntity();
        CatalogoEntity newEntity = logic.createCatalogo(entity);
        CatalogoDTO newDto = new CatalogoDTO(newEntity);
        LOGGER.log(Level.INFO, "CatalogoResource createCatalogo: output: {0}", newDto);
        return dto;
    }
    
    /**
     * obtiene un catalogo
     * @param catalogoId id del catalogo
     * @return el catalogo buscado
     * @throws WebApplicationException 
     */
    @GET
    @Path("{catalogoId: \\d+}")
    public CatalogoDetailDTO getCatalogo(@PathParam("catalogoId") Long catalogoId) throws WebApplicationException {
        LOGGER.log(Level.INFO, "CatalogoResource getCatalogo: input: {0}", catalogoId);
        CatalogoEntity entity = logic.getCatalogo(catalogoId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /catalogo/" + catalogoId + " no existe.", 404);
        }
        CatalogoDetailDTO detailDTO = new CatalogoDetailDTO(entity);
        LOGGER.log(Level.INFO, "CatalogoResource getCatalogo: output: {0}", detailDTO);
        return detailDTO;
    }
    
    /**
     * actualiza un catalogo
     * @param catalogoId el id del catalogo
     * @param catalogo detalle de dto del catalogo
     * @return el detalle de dto del catalogo
     * @throws WebApplicationException
     * @throws BusinessLogicException 
     */
    @PUT
    @Path("{catalogoId: \\d+}")
    public CatalogoDetailDTO updateCatalogo(@PathParam("catalogoId") Long catalogoId, CatalogoDetailDTO catalogo) throws WebApplicationException, BusinessLogicException {
        LOGGER.log(Level.INFO, "CatalogoResource updateCatalogo: input: id:{0} , catalogo: {1}", new Object[]{catalogoId, catalogo});
        catalogo.setId(catalogoId);
        if (logic.getCatalogo(catalogoId) == null) {
            throw new WebApplicationException("El recurso /catalogo/" + catalogoId + " no existe.", 404);
        }
        CatalogoDetailDTO detailDTO = new CatalogoDetailDTO(logic.updateCatalogo(catalogoId, catalogo.toEntity()));
        LOGGER.log(Level.INFO, "CatalogoResource updateCatalogo: output: {0}", detailDTO);
        return detailDTO;

    }
    
    /**
     * elimina un catalogo
     * @param catalogoId id del catalogo
     * @throws BusinessLogicException 
     */
    @DELETE
    @Path("{catalogoId: \\d+}")
    public void deleteCatalogo(@PathParam("catalogoId") Long catalogoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CatalogoResource deleteCatalogo: input: {0}", catalogoId);
        if (logic.getCatalogo(catalogoId) == null) {
            throw new WebApplicationException("El recurso /catalogo/" + catalogoId + " no existe.", 404);
        }
        logic.deleteCatalogo(catalogoId);
        LOGGER.info("CatalogoResource deleteCatalogo: output: void");
    }
}
