/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.entities.UsuarioEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.HackatonPersistence;
import co.edu.uniandes.csw.hackatones.persistence.UsuarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jc.higuera
 */
@Stateless
public class HackatonUsuarioLogic {
    
    private static final Logger LOGGER = Logger.getLogger(HackatonUsuarioLogic.class.getName());

    @Inject
    private HackatonPersistence hackatonPersistence;

    @Inject
    private UsuarioPersistence usuarioPersistence;
    
    /**
     * Asocia un Usuario existente a un Hackaton
     *
     * @param usuarioId Identificador de la instancia de Usuario
     * @param hackatonId Identificador de la instancia de Hackaton
     * @return Instancia de UsuarioEntity que fue asociada a la hackaton
     */
    public UsuarioEntity addUsuario(Long hackatonId, Long usuarioId)  {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un usuario a la hackaton con id = {0}", hackatonId);
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        usuarioEntity.getHackatones().add(hackatonEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un hackaton al usuario con id = {0}", usuarioId);
        return usuarioPersistence.find(usuarioId);
        
    }
    
    /**
     * Obtiene una colección de instancias de UsuarioEntity asociadas a una
     * instancia de Hackaton
     *
     * @param hackatonId Identificador de la instancia de Usuario
     * @return Colección de instancias de UsuarioEntity asociadas a la instancia de
     * Hackaton
     */
    public List<UsuarioEntity> getUsuarios(Long hackatonId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los usuarios de la hackaton con id = {0}", hackatonId);
        return hackatonPersistence.find(hackatonId).getInscritos();
    }
    
    /**
     * Obtiene una instancia de UsuarioEntity asociada a una instancia de Hackaton
     *
     * @param hackatonId Identificador de la instancia de Hackaton
     * @param usuarioId Identificador de la instancia de Usuario
     * @return La entidad del Usuario asociada a la hackaton
     */
    public UsuarioEntity getUsuario(Long hackatonId, Long usuarioId)throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un usuario de la hackaton con id = {0}", hackatonId);
        List<UsuarioEntity> usuarios = hackatonPersistence.find(hackatonId).getInscritos();
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        int index = usuarios.indexOf(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar un usuario de la hackaton con id = {0}", hackatonId);
        if (index >= 0) {
            return usuarios.get(index);
        }
        throw new BusinessLogicException("El usuario no esta asociado a la hackaton");
    }
    
    /**
     * Remplaza las instancias de Usuario asociadas a una instancia de Hackaton
     *
     * @param hackatonId Identificador de la instancia de Hackaton
     * @param list Colección de instancias de UsuarioEntity a asociar a instancia
     * de Hackaton
     * @return Nueva colección de UsuarioEntity asociada a la instancia de Hackaton
     */
    public List<UsuarioEntity> replaceUsuarios(Long hackatonId, List<UsuarioEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los usuarios del hackaton con id = {0}", hackatonId);
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonId);
        hackatonEntity.setInscritos(list);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los usuarios del hackaton con id = {0}", hackatonId);
        return hackatonPersistence.find(hackatonId).getInscritos();
    }
    
    /**
     * Desasocia un Usuario existente de un Hackaton existente
     *
     * @param hackatonId Identificador de la instancia de Hackaton
     * @param usuarioId Identificador de la instancia de Usuario
     */
    public void removeUsuario(Long hackatonId, Long usuarioId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un usuario del hackaton con id = {0}", hackatonId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonId);
        usuarioEntity.getHackatones().remove(hackatonEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un usuario del hackaton con id = {0}", hackatonId);
    }
}
