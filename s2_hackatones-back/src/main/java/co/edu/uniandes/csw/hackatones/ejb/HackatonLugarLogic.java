/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.entities.LugarEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.HackatonPersistence;
import co.edu.uniandes.csw.hackatones.persistence.LugarPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jd.monsalve
 */
@Stateless
public class HackatonLugarLogic {
    
    private static final Logger LOGGER = Logger.getLogger(HackatonLugarLogic.class.getName());

    @Inject
    private HackatonPersistence hackatonPersistence;

    @Inject
    private LugarPersistence lugarPersistence;

   
    public LugarEntity addLugar(Long hackatonId, Long lugarId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un lugar a la hackaton con id = {0}", hackatonId);
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonId);
        LugarEntity lugarEntity  = lugarPersistence.find(lugarId);
        hackatonEntity.setLugar(lugarEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un lugar a la hackaton con id = {0}", hackatonId);
        return lugarPersistence.find(lugarId);
    }

   

    public void removeLugar(Long hackatonId, Long lugarId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un lugar de la hackaton con id = {0}", hackatonId);
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonId);
        LugarEntity lugarEntity = lugarPersistence.find(lugarId);
        hackatonEntity.setLugar(null);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un lugar de la hackaton con id = {0}", hackatonId);
    }
    
}
