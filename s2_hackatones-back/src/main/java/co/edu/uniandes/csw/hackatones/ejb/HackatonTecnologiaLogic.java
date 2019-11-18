/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.entities.TecnologiaEntity;
import co.edu.uniandes.csw.hackatones.persistence.HackatonPersistence;
import co.edu.uniandes.csw.hackatones.persistence.TecnologiaPersistence;
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
public class HackatonTecnologiaLogic 
{
    private static final Logger LOGGER = Logger.getLogger(HackatonTecnologiaLogic.class.getName());

    @Inject
    private HackatonPersistence hackatonPersistence;

    @Inject
    private TecnologiaPersistence tecnologiaPersistence;

    /**
     * Asocia un Tecnologia existente a un Hackaton
     *
     * @param hackatonsId Identificador de la instancia de Hackaton
     * @param tecnologiasId Identificador de la instancia de Tecnologia
     * @return Instancia de TecnologiaEntity que fue asociada a Hackaton
     */
    public TecnologiaEntity addTecnologia(Long hackatonsId, Long tecnologiasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un autor al libro con id = {0}", hackatonsId);
        TecnologiaEntity tecnologiaEntity = tecnologiaPersistence.find(tecnologiasId);
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonsId);
        tecnologiaEntity.getHackatones().add(hackatonEntity);
        //hackatonEntity.getTecnologias().add(tecnologiaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un autor al libro con id = {0}", hackatonsId);
        return tecnologiaPersistence.find(tecnologiasId);
    }

    /**
     * Obtiene una colección de instancias de TecnologiaEntity asociadas a una
     * instancia de Hackaton
     *
     * @param hackatonsId Identificador de la instancia de Hackaton
     * @return Colección de instancias de TecnologiaEntity asociadas a la instancia
     * de Hackaton
     */
    public List<TecnologiaEntity> getTecnologias(Long hackatonsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los autores del libro con id = {0}", hackatonsId);
        return hackatonPersistence.find(hackatonsId).getTecnologias();
    }

    /**
     * Obtiene una instancia de TecnologiaEntity asociada a una instancia de Hackaton
     *
     * @param hackatonsId Identificador de la instancia de Hackaton
     * @param tecnologiasId Identificador de la instancia de Tecnologia
     * @return La entidad del Autor asociada al libro
     */
    public TecnologiaEntity getTecnologia(Long hackatonsId, Long tecnologiasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un autor del libro con id = {0}", hackatonsId);
        List<TecnologiaEntity> authors = hackatonPersistence.find(hackatonsId).getTecnologias();
        TecnologiaEntity tecnologiaEntity = tecnologiaPersistence.find(tecnologiasId);
        int index = authors.indexOf(tecnologiaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar un autor del libro con id = {0}", hackatonsId);
        if (index >= 0) {
            return authors.get(index);
        }
        return null;
    }

    /**
     * Remplaza las instancias de Tecnologia asociadas a una instancia de Hackaton
     *
     * @param hackatonsId Identificador de la instancia de Hackaton
     * @param list Colección de instancias de TecnologiaEntity a asociar a instancia
     * de Hackaton
     * @return Nueva colección de TecnologiaEntity asociada a la instancia de Hackaton
     */
    public List<TecnologiaEntity> replaceTecnologias(Long hackatonsId, List<TecnologiaEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los autores del libro con id = {0}", hackatonsId);
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonsId);
        hackatonEntity.setTecnologias(list);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los autores del libro con id = {0}", hackatonsId);
        return hackatonPersistence.find(hackatonsId).getTecnologias();
    }

    /**
     * Desasocia un Tecnologia existente de un Hackaton existente
     *
     * @param hackatonsId Identificador de la instancia de Hackaton
     * @param tecnologiasId Identificador de la instancia de Tecnologia
     */
    public void removeTecnologia(Long hackatonsId, Long tecnologiasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un tecnologia del hackaton con id = {0}", hackatonsId);
        TecnologiaEntity tecnologiaEntity = tecnologiaPersistence.find(tecnologiasId);
        HackatonEntity hackatonEntity = hackatonPersistence.find(hackatonsId);
        tecnologiaEntity.getHackatones().remove(hackatonEntity);
        //hackatonEntity.getTecnologias().remove(tecnologiaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un autor del libro con id = {0}", hackatonsId);
    }
}
