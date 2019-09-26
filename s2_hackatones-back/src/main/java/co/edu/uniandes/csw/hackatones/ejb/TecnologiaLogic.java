/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.TecnologiaEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.TecnologiaPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ja.torresl
 */

@Stateless
public class TecnologiaLogic {
    
    @Inject
    private TecnologiaPersistence persistence;
    
    public TecnologiaEntity createTecnologia(TecnologiaEntity entity) throws BusinessLogicException{
        if(entity.getNombre() == null){
            throw new BusinessLogicException("EL nombre de la tecnologia esta vacio");
        }
        /**
        if(entity.getInteresados() == null){
            throw new BusinessLogicException("La lista de interesados esta vacia");
        }
*/
        entity = persistence.create(entity);
        return entity;
}
    
}
