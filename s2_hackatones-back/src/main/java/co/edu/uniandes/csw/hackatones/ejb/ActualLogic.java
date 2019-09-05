/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.ActualEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.ActualPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author a.pedraza
 */
@Stateless
public class ActualLogic {
    
    @Inject
    private ActualPersistence persistence;
    
    public ActualEntity  createActual(ActualEntity actual) throws BusinessLogicException{
        
        if(actual.getReglas() ==null){
            throw new BusinessLogicException("Las reglas esta vacia");
        }
        if(actual.getRestricciones()==null){
            throw new BusinessLogicException("Las restricciones esta vacia");
        }
        if(actual.getReglas().equals("")){
            throw new BusinessLogicException("Las reglas es la cadena vacia");
        } 
        if(actual.getRestricciones().equals("")){
            throw new BusinessLogicException("Las restricciones es la cadena vacia");
        }
        
        
        actual = persistence.create(actual);
        return actual;
    }
}
