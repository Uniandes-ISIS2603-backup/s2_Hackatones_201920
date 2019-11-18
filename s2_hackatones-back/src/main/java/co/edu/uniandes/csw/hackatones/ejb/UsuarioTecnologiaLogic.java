/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.TecnologiaEntity;
import co.edu.uniandes.csw.hackatones.entities.UsuarioEntity;
import co.edu.uniandes.csw.hackatones.persistence.TecnologiaPersistence;
import co.edu.uniandes.csw.hackatones.persistence.UsuarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.estupinan
 */
@Stateless
public class UsuarioTecnologiaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(HackatonTecnologiaLogic.class.getName());

    @Inject
    private UsuarioPersistence usuarioPersistence;

    @Inject
    private TecnologiaPersistence tecnologiaPersistence;

    /**
     * Asocia un Tecnologia existente a un usuario
     *
     * @param usuariosId Identificador de la instancia de Usuario
     * @param tecnologiasId Identificador de la instancia de Tecnologia
     * @return Instancia de TecnologiaEntity que fue asociada a Usuario
     */
    public TecnologiaEntity addTecnologia(Long usuariosId, Long tecnologiasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un autor al libro con id = {0}", usuariosId);
        TecnologiaEntity tecnologiaEntity = tecnologiaPersistence.find(tecnologiasId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuariosId);
        usuarioEntity.getTecnologias().add(tecnologiaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un autor al libro con id = {0}", usuariosId);
        return tecnologiaPersistence.find(tecnologiasId);
    }

    /**
     * Obtiene una colección de instancias de TecnologiaEntity asociadas a una
     * instancia de Usuario
     *
     * @param usuariosId Identificador de la instancia de Usuario
     * @return Colección de instancias de TecnologiaEntity asociadas a la instancia
     * de Usuario
     */
    public List<TecnologiaEntity> getTecnologias(Long usuariosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los autores del libro con id = {0}", usuariosId);
        return usuarioPersistence.find(usuariosId).getTecnologias();
    }

    /**
     * Obtiene una instancia de TecnologiaEntity asociada a una instancia de Usuario
     *
     * @param usuariosId Identificador de la instancia de Usuario
     * @param tecnologiasId Identificador de la instancia de Tecnologia
     * @return La entidad del Autor asociada al libro
     */
    public TecnologiaEntity getTecnologia(Long usuariosId, Long tecnologiasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un autor del libro con id = {0}", usuariosId);
        List<TecnologiaEntity> authors = usuarioPersistence.find(usuariosId).getTecnologias();
        TecnologiaEntity tecnologiaEntity = tecnologiaPersistence.find(tecnologiasId);
        int index = authors.indexOf(tecnologiaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar un autor del libro con id = {0}", usuariosId);
        if (index >= 0) {
            return authors.get(index);
        }
        return null;
    }

    /**
     * Remplaza las instancias de Tecnologia asociadas a una instancia de Usuario
     *
     * @param usuariosId Identificador de la instancia de Usuario
     * @param list Colección de instancias de TecnologiaEntity a asociar a instancia
     * de Usuario
     * @return Nueva colección de TecnologiaEntity asociada a la instancia de Usuario
     */
    public List<TecnologiaEntity> replaceTecnologias(Long usuariosId, List<TecnologiaEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los autores del libro con id = {0}", usuariosId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuariosId);
        usuarioEntity.setTecnologias(list);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los autores del libro con id = {0}", usuariosId);
        return usuarioPersistence.find(usuariosId).getTecnologias();
    }

    /**
     * Desasocia un Tecnologia existente de un Usuario existente
     *
     * @param usuariosId Identificador de la instancia de Usuario
     * @param tecnologiasId Identificador de la instancia de Tecnologia
     */
    public void removeTecnologia(Long usuariosId, Long tecnologiasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un autor del libro con id = {0}", usuariosId);
        TecnologiaEntity tecnologiaEntity = tecnologiaPersistence.find(tecnologiasId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuariosId);
        usuarioEntity.getTecnologias().remove(tecnologiaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un autor del libro con id = {0}", usuariosId);
    }
    
}
