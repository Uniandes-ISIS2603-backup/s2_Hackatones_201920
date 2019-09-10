/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.LugarEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.LugarPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 *
 * @author jd.monsalve
 */
@Stateless
public class LugarLogic {
    
    @Inject
    private LugarPersistence persistencia;
    
    public LugarEntity createLugar(LugarEntity lugar) throws BusinessLogicException
    {
        LugarEntity yaExiste = persistencia.find(lugar.getId());
        
        if(yaExiste != null)
        {
            throw new BusinessLogicException("El lugar que se desea crear ya ha sido previamente creado");
        }
                
        if(lugar.getNombre() == null)
        {
            throw new BusinessLogicException("El nombre del lugar es nulo");
        }
        
        if(lugar.getCiudad() == null)
        {
            throw new BusinessLogicException("La ciudad es nula");
        }
        
        if(lugar.getDireccion()== null)
        {
            throw new BusinessLogicException("La dirección es nula");
        }
          
        
      lugar = persistencia.create(lugar);
      return lugar;
    }
    
    
    
}
