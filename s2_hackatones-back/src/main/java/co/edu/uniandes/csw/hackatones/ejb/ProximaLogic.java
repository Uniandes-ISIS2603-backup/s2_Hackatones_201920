/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.ProximaEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.ProximaPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author a.pedraza
 */
@Stateless
public class ProximaLogic {
    
    @Inject
    private ProximaPersistence persistence;
    
    public ProximaEntity  createProxima(ProximaEntity proxima) throws BusinessLogicException{
        
        if(proxima.getReglas() ==null){
            throw new BusinessLogicException("Las reglas esta vacia");
        }
        if(proxima.getRestricciones()==null){
            throw new BusinessLogicException("Las restricciones esta vacia");
        }
        if(proxima.getReglas().equals("")){
            throw new BusinessLogicException("Las reglas es la cadena vacia");
        } 
        if(proxima.getRestricciones().equals("")){
            throw new BusinessLogicException("Las restricciones es la cadena vacia");
        }
        
        
        proxima = persistence.create(proxima);
        return proxima;
    }
}