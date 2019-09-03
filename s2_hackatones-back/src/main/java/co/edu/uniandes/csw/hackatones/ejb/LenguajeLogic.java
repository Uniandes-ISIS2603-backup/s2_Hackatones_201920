/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.LenguajeEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.LenguajePersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Santiago Estupinan
 */
@Stateless
public class LenguajeLogic {
    
    @Inject
    private LenguajePersistence persistence;
    
    public LenguajeEntity createLenguaje(LenguajeEntity entity) throws BusinessLogicException
    {
        if(entity.getName().equals("") || entity.getName() == null)
        {
            throw new BusinessLogicException("El nombre del lenguaje esta vacío");
        }
        entity = persistence.create(entity);
        return entity;
    }
}
