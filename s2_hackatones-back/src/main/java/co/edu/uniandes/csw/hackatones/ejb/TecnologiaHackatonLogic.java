/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.entities.TecnologiaEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.HackatonPersistence;
import co.edu.uniandes.csw.hackatones.persistence.TecnologiaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jd.monsalve
 */
@Stateless
public class TecnologiaHackatonLogic 
{

    /**
     * Logger con la clase TeconologiaHackatonLogic
     */
    private static final Logger LOGGER = Logger.getLogger(TecnologiaHackatonLogic.class.getName());
    /**
    * Persistencia de la   clase hackaton
    */
    @Inject
    private HackatonPersistence hackatonPersistence;
    /**
     * Persistencia de la clase tecnologia
     */
    @Inject
    private TecnologiaPersistence tecnologiaPersistence;

    /**
     * Asocia una hackaton existente a ua tecnologia
     *
     * @param tecnologiasId Identificador de la instancia de Tecnologia
     * @param hackatonId Identificador de la instancia de hackaton
     * @return Instancia de HackatonEntity que fue asociada a Tecnologia
     */
    public HackatonEntity addHackaton(Long tecnologiasId, Long hackatonId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle una hackaton al tecnologia con id = {0}", tecnologiasId);
        TecnologiaEntity tecnologiaEntity = tecnologiaPersistence.find(tecnologiasId);
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonId);
       
        hackatonEntity.getTecnologias().add(tecnologiaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un usuario al tecnologia con id = {0}", tecnologiasId);
        return hackatonPersistence.find(hackatonId);
    }

    /**
     * Obtiene una colección de instancias de HackatonEntity asociadas a una
     * instancia de Tecnologia
     *
     * @param tecnologiasId Identificador de la instancia de Tecnologia
     * @return Colección de instancias de HackatonEntity asociadas a la instancia de tecnologia
     */
   
    public List<HackatonEntity> getHackatones(Long tecnologiasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas los hackatones del tecnologia con id = {0}", tecnologiasId);
        return tecnologiaPersistence.find(tecnologiasId).getHackatones();
    }
 
    /**
     * Obtiene una instancia de HackatonEntity asociada a una instancia de Tecnologia
     *
     * @param tecnologiasId Identificador de la instancia de Tecnologia
     * @param hackatonId Identificador de la instancia de hackatones
     * @return La entidadd de hackaton del tecnologia
     * @throws BusinessLogicException Si la hackaton no está asociado al tecnologia
     */
  
    public HackatonEntity getHackaton(Long tecnologiasId, Long hackatonId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la hackaton con id = {0}" , hackatonId);
        List<HackatonEntity> hackatones = tecnologiaPersistence.find(tecnologiasId).getHackatones();
     
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonId);
        int index = hackatones.indexOf(hackatonEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la hackaton con id = {0}", hackatonId);
        if (index >= 0) {
            return hackatones.get(index);
        }
        throw new BusinessLogicException("La hackaton no está asociado al tecnologia");
    }
    
     
 
    /**
     * Remplaza las instancias de Hackaton asociadas a una instancia de Tecnologia
     *
     * @param tecnologiaId Identificador de la instancia de Tecnologia
     * @param hackatones Colección de instancias de HackatonEntity a asociar a instancia
     * de Tecnologia
     * @return Nueva colección de HackatonEntity asociada a la instancia de Tecnologia
     */

    public List<HackatonEntity> replaceHackatones(Long tecnologiaId, List<HackatonEntity> hackatones) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar lsa hackatones asociadas a la tecnologia con id = {0}", tecnologiaId);
        TecnologiaEntity tecnologiaEntity = tecnologiaPersistence.find(tecnologiaId);
        List<HackatonEntity> hackatonesList = hackatonPersistence.findAll();
        for (HackatonEntity hackaton : hackatonesList) {
            if (hackatones.contains(hackaton)) {
                if (!hackaton.getTecnologias().contains(tecnologiaEntity)) {
                    hackaton.getTecnologias().add(tecnologiaEntity);
                }
            } else {
                hackaton.getTecnologias().remove(tecnologiaEntity);
            }
        }
        tecnologiaEntity.setHackatones(hackatones);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar las hackatones asociadas al tecnologia con id = {0}", tecnologiaId);
        return tecnologiaEntity.getHackatones();
    }

    /**
     * Desasocia una hackaton existente de un Tecnologia existente
     *
     * @param tecnologiasId Identificador de la instancia de Tecnologia
     * @param hackatonId Identificador de la instancia de Hackaton
     */
  
    public void removeHackaton(Long tecnologiasId, Long hackatonId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una hackaton de la tecnologia con id = {0}", tecnologiasId);
        TecnologiaEntity tecnologiaEntity = tecnologiaPersistence.find(tecnologiasId);
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonId);
        hackatonEntity.getTecnologias().remove(tecnologiaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar una hackaton de la tecnologia con id = {0}", tecnologiasId);
    }
    
}
