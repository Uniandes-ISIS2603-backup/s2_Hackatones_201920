/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.InteresEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.InteresPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ja.torresl
 */
@Stateless
public class InteresLogic {
    
    @Inject
    private InteresPersistence persistence;
    
    public InteresEntity createInteres(InteresEntity interes)throws BusinessLogicException{
        if(interes.getNombre()==null){
            throw new BusinessLogicException("El nombre del interes está vacío");
        }
         if(interes.getDescripcion()==null){
            throw new BusinessLogicException("La descripcion del interes está vacío");
        }
        interes= persistence.create(interes);
        return interes;
    }

    
}
