/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.LugarEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.LugarPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 *
 * @author jd.monsalve
 */
@Stateless
public class LugarLogic {
    
     private static final Logger LOGGER = Logger.getLogger(LugarLogic.class.getName());
    
    
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
          
      LOGGER.log(Level.INFO, "Inicia proceso de creación del lugar");
      lugar = persistencia.create(lugar);
      LOGGER.log(Level.INFO, "Termina proceso de creación del lugar");
      return lugar;
    }
    
     public LugarEntity getLugar(Long identificador) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el lugar con id = {0}", identificador);
         LugarEntity yaExiste = persistencia.find(identificador);
        
        if(yaExiste == null)
        {
             LOGGER.log(Level.INFO, "el lugar con la identificacion id = {0} no existe", identificador);
            throw new BusinessLogicException("El lugar que se desea encontrarno existe");
        }
         LOGGER.log(Level.INFO, "Termina proceso de consultar el lugar con id = {0}", identificador);
        return yaExiste;   
    }
     
     
     public LugarEntity updateLugar(Long identificador , LugarEntity nuevoLugar)
     {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el lugar  con id = {0}", identificador);
        LugarEntity newCalificacionEntity = persistencia.update(nuevoLugar);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el lugar con id = {0}", identificador);
        return nuevoLugar;
     }
     
       public void deleteLugar(Long identificador) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el lugar con id = {0}", identificador);
        persistencia.delete(identificador);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el lugar con id = {0}", identificador);
    }
    
}
