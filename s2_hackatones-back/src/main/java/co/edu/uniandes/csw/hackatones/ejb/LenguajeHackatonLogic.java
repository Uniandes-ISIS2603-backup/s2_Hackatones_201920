/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.entities.LenguajeEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.HackatonPersistence;
import co.edu.uniandes.csw.hackatones.persistence.LenguajePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @hackaton a.pedraza
 */
@Stateless
public class LenguajeHackatonLogic {
    private static final Logger LOGGER = Logger.getLogger(LenguajeHackatonLogic.class.getName());

    @Inject
    private HackatonPersistence hackatonPersistence;

    @Inject
    private LenguajePersistence lenguajePersistence;

    /**
     * Asocia un Hackaton existente a un Lenguaje
     *
     * @param lenguajesId Identificador de la instancia de Lenguaje
     * @param hackatonsId Identificador de la instancia de Hackaton
     * @return Instancia de HackatonEntity que fue asociada a Lenguaje
     */
    public HackatonEntity addHackaton(Long lenguajesId, Long hackatonsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle una hackaton al lenguaje con id = {0}", lenguajesId);
        LenguajeEntity lenguajeEntity = lenguajePersistence.find(lenguajesId);
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonsId);
        hackatonEntity.getLenguajes().add(lenguajeEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle una hackaton al lenguaje con id = {0}", lenguajesId);
        return hackatonPersistence.find(hackatonsId);
    }

    /**
     * Obtiene una colección de instancias de HackatonEntity asociadas a una
     * instancia de Lenguaje
     *
     * @param lenguajesId Identificador de la instancia de Lenguaje
     * @return Colección de instancias de HackatonEntity asociadas a la instancia de
     * Lenguaje
     */
    public List<HackatonEntity> getHackatones(Long lenguajesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos las hackatones del lenguaje con id = {0}", lenguajesId);
        return lenguajePersistence.find(lenguajesId).getHackatones();
    }

    /**
     * Obtiene una instancia de HackatonEntity asociada a una instancia de Lenguaje
     *
     * @param lenguajesId Identificador de la instancia de Lenguaje
     * @param hackatonsId Identificador de la instancia de Hackaton
     * @return La entidadd de Libro del autor
     * @throws BusinessLogicException Si el libro no está asociado al autor
     */
    public HackatonEntity getHackaton(Long lenguajesId, Long hackatonsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la hackaton con id = {0} " , hackatonsId);
        List<HackatonEntity> hackatons = lenguajePersistence.find(lenguajesId).getHackatones();
        
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonsId);
        int index = hackatons.indexOf(hackatonEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la hackaton con id = {0} ", hackatonsId);
        if (index >= 0) {
            return hackatons.get(index);
        }
        throw new BusinessLogicException("La hackaton no está asociado al lenguaje");
        
    }

    /**
     * Remplaza las instancias de Hackaton asociadas a una instancia de Lenguaje
     *
     * @param lenguajeId Identificador de la instancia de Lenguaje
     * @param hackatons Colección de instancias de HackatonEntity a asociar a instancia
     * de Lenguaje
     * @return Nueva colección de HackatonEntity asociada a la instancia de Lenguaje
     */
    public List<HackatonEntity> replaceHackatones(Long lenguajeId, List<HackatonEntity> hackatons) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar las hackatones asocidos al lenguaje con id = {0}", lenguajeId);
        LenguajeEntity lenguajeEntity = lenguajePersistence.find(lenguajeId);
        List<HackatonEntity> hackatonList = hackatonPersistence.findAll();
        for (HackatonEntity hackaton : hackatonList) {
            if (hackatons.contains(hackaton)) {
                if (!hackaton.getLenguajes().contains(lenguajeEntity)) {
                    hackaton.getLenguajes().add(lenguajeEntity);
                }
            } else {
                hackaton.getLenguajes().remove(lenguajeEntity);
            }
        }
        lenguajeEntity.setHackatones(hackatons);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar las hackatones asocidos al lenguaje con id = {0}", lenguajeId);
        return lenguajeEntity.getHackatones();
    }

    /**
     * Desasocia un Hackaton existente de un Lenguaje existente
     *
     * @param lenguajesId Identificador de la instancia de Lenguaje
     * @param hackatonsId Identificador de la instancia de Hackaton
     */
    public void removeHackaton(Long lenguajesId, Long hackatonsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una hackaton del lenguaje con id = {0}", lenguajesId);
        LenguajeEntity lenguajeEntity = lenguajePersistence.find(lenguajesId);
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonsId);
        hackatonEntity.getLenguajes().remove(lenguajeEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar una hackaton del lenguaje con id = {0}", lenguajesId);
    }
}
