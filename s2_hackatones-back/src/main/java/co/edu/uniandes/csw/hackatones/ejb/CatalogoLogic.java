/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.entities.CatalogoEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.CatalogoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ne.cardenas
 */
@Stateless
public class CatalogoLogic {
    private static final Logger LOGGER = Logger.getLogger(CatalogoLogic.class.getName());

    /**
     * persistencia del catálogo
     */
    @Inject
    private CatalogoPersistence persistence;

    /**
     * Crea un nuevo catálogo
     * @param entity La entidad del catálogo del nuevo libro a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el ISBN es inválido o ya existe en la
     * persistencia.
     */
    public CatalogoEntity createCatalogo(CatalogoEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del catálogo");
        if (persistence.find(entity.getId()) != null) {
            throw new BusinessLogicException("Ya existe un catálogo");
        }
        persistence.create(entity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del libro");
        return entity;
    }

    /**
     * Busca un libro por ID
     *
     * @param booksId El id del libro a buscar
     * @return El libro encontrado, null si no lo encuentra.
     */
    public CatalogoEntity getCatalogo(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el catálogo con id = {0}", id);
        CatalogoEntity entity = persistence.find(id);
        if (entity == null) {
            LOGGER.log(Level.SEVERE, "El catálogo con el id = {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el catálogo con id = {0}", id);
        return entity;
    }

    /**
     * Hackatonizar un libro por ID
     *
     * @param booksId El ID del libro a actualizar
     * @param bookEntity La entidad del libro con los cambios deseados
     * @return La entidad del libro luego de actualizarla
     * @throws BusinessLogicException Si el IBN de la actualización es inválido
     */
    public CatalogoEntity updateCatalogo(Long id, CatalogoEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el catálogo con id = {0}", id);
        CatalogoEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el catálogo con id = {0}", entity.getId());
        return newEntity;
    }

    /**
     * Eliminar un libro por ID
     *
     * @param booksId El ID del libro a eliminar
     * @throws BusinessLogicException si el libro tiene autores asociados
     */
    public void deleteCatalogo(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el catálogo con id = {0}", id);
        List<HackatonEntity> acts = getCatalogo(id).getEventosEnCurso();
        if (acts != null) {
            if (!acts.isEmpty())
                throw new BusinessLogicException("No se puede borrar el catálogo con id = " + id + " porque tiene eventos actuales asociados");
        }
        List<HackatonEntity> prox = getCatalogo(id).getEventosProximos();
        if (prox != null) {
            if (!prox.isEmpty())
                throw new BusinessLogicException("No se puede borrar el catálogo con id = " + id + " porque tiene eventos próximos asociados");
        }
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el catálogo con id = {0}", id);
    }
}
