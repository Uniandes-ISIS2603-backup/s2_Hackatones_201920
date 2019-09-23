/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.PatrocinadorEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.PatrocinadorPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Santiago Estupinan
 */
@Stateless
public class PatrocinadorLogic {
    @Inject
    private PatrocinadorPersistence persistence;
    
    public PatrocinadorEntity createPatrocinador(PatrocinadorEntity entity) throws BusinessLogicException
    {
        if(entity.getNombre().equals("") || entity.getNombre() == null)
        {
            throw new BusinessLogicException("El nombre del patrocinador esta vacío");
        }
        entity = persistence.create(entity);
        return entity;
    }
}
