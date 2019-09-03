/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.HackatonPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jc.higuera
 */
@Stateless
public class HackatonLogic {
    
    @Inject
    private HackatonPersistence persistence;
    
    public HackatonEntity createHackaton(HackatonEntity hackaton)throws BusinessLogicException{
        if(hackaton.getNombre()==null){
            throw new BusinessLogicException("El nombre de la hackaton está vacío");
        }
        hackaton= persistence.create(hackaton);
        return hackaton;
    }
    
}
