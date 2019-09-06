/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.CredencialesEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.CredencialesPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ne.cardenas
 */
@Stateless
public class CredencialesLogic {
    
    @Inject
    private CredencialesPersistence persistencia;
    
    public CredencialesEntity createCredenciales(CredencialesEntity entity) throws BusinessLogicException {
        
        if (entity.getCorreo() == null)
            throw new BusinessLogicException("El correo está vacío");
        else
        {
            if (persistencia.findByCorreo(entity.getCorreo()) != null)
                throw new BusinessLogicException("Ell correo ya existe");
        }
        
        if (entity.getContrasenha() == null)
            throw new BusinessLogicException("La contraseña está vacía");
        
        entity = persistencia.create(entity);
        return entity;
    }
}
