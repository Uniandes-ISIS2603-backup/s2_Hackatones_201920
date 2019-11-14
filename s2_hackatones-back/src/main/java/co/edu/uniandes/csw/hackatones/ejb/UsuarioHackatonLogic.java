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
public class UsuarioHackatonLogic {
    
    private static final Logger LOGGER = Logger.getLogger(UsuarioHackatonLogic.class.getName());

    @Inject
    private HackatonPersistence hackatonPersistence;

    @Inject
    private UsuarioPersistence usuarioPersistence;

    
    /**
     * Asocia un Hackaton existente a un Usuario
     *
     * @param usuarioId Identificador de la instancia de Usuario
     * @param hackatonId Identificador de la instancia de Hackaton
     * @return Instancia de HackatonEntity que fue asociada a Usuario
     */
    public HackatonEntity addHackaton(Long usuarioId, Long hackatonId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle una hackaton al usuario con id = {0}", usuarioId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonId);
        usuarioEntity.getHackatones().add(hackatonEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un libro al autor con id = {0}", usuarioId);
        return hackatonPersistence.find(hackatonId);
    }
    
    
     /**
     * Obtiene una colección de instancias de HackatonEntity asociadas a una
     * instancia de Usuario
     *
     * @param usuarioId Identificador de la instancia de Usuario
     * @return Colección de instancias de HackatonEntity asociadas a la instancia de
     * Usuario
     */
    public List<HackatonEntity> getHackatones(Long usuarioId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las hackatones del usuario con id = {0}", usuarioId);
        return usuarioPersistence.find(usuarioId).getHackatones();
    }
    
    
    /**
     * Obtiene una instancia de HackatonEntity asociada a una instancia de Usuario
     *
     * @param usuarioId Identificador de la instancia de Usuario
     * @param hackatonId Identificador de la instancia de Hackaton
     * @return La entidadd de Libro del autor
     * @throws BusinessLogicException Si el libro no está asociado al autor
     */
    public HackatonEntity getHackaton(Long usuarioId, Long hackatonId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la hackaton con id = {0} del usuario con id = " + hackatonId, usuarioId);
        List<HackatonEntity> hackaton = usuarioPersistence.find(usuarioId).getHackatones();
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonId);
        int index = hackaton.indexOf(hackatonEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar ka hackaton con id = {0} del usuario con id = " + hackatonId, usuarioId);
        if (index >= 0) {
            return hackaton.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado al autor");
    }
    
    
    /**
     * Remplaza las instancias de Hackaton asociadas a una instancia de Usuario
     *
     * @param usuarioId Identificador de la instancia de Usuario
     * @param hackaton Colección de instancias de HackatonEntity a asociar a instancia
     * de Usuario
     * @return Nueva colección de HackatonEntity asociada a la instancia de Usuario
     */
    public List<HackatonEntity> replaceHackaton(Long usuarioId, List<HackatonEntity> hackatones) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar las hackatones asociadas al usuario con id = {0}", usuarioId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        List<HackatonEntity> hackatonList = hackatonPersistence.findAll();
        for (HackatonEntity hackaton : hackatonList) {
            if (hackatones.contains(hackaton)) {
                if (!hackaton.getInscritos().contains(usuarioEntity)) {
                    hackaton.getInscritos().add(usuarioEntity);
                }
            } else {
                hackaton.getInscritos().remove(usuarioEntity);
            }
        }
        usuarioEntity.setHackatones(hackatones);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar las hackatones asociadas al usuario con id = {0}", usuarioId);
        return usuarioEntity.getHackatones();
    }
    
    
    /**
     * Desasocia un Hackaton existente de un Usuario existente
     *
     * @param usuarioId Identificador de la instancia de Usuario
     * @param hackatonId Identificador de la instancia de Hackaton
     */
    public void removeHackaton(Long usuarioId, Long hackatonId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una hackaton del usuario con id = {0}", usuarioId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonId);
        usuarioEntity.getHackatones().remove(hackatonEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un libro del usuario con id = {0}", usuarioId);
    }
}
