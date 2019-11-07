/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.LugarEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.LugarPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
/**
 *
 * Clase Logic correspondiente a la clase Lugar
 * @author jd.monsalve
 */
@Stateless
public class LugarLogic {
    
    /**
     * Atributo que maneja las transacciones
     */
     private static final Logger LOGGER = Logger.getLogger(LugarLogic.class.getName());
     /**
      * atributo persistencia 
      */
    @Inject
    private LugarPersistence persistencia;
    
    /**
     * Método correspondiente para crear un lugar
     * @param lugar, lugar que se desea crear
     * @return el lugar creado o null si no se pudo crear
     * @throws BusinessLogicException  lanza excepcion en caso que no se cumpla una regla de negocio
     */
    public LugarEntity createLugar(LugarEntity lugar) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del lugar"); 
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
        if(lugar.getImagen() == null)
        {
        throw new BusinessLogicException("La imagen es nula");
        }
          
      lugar = persistencia.create(lugar);
      LOGGER.log(Level.INFO, "Termina proceso de creación del lugar");
      return lugar;
    }
    
    /**
     * Método que busca un lugar por su identificador
     * @param identificador
     * @return el lugar deseado a buscar, o null si no lo encuentra
     * @throws BusinessLogicException  lanza excepcion si no cumple alguna regla de negocio
     */
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
     
     public List<LugarEntity> getLugares() throws BusinessLogicException
     {
      LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los lugares");
         List<LugarEntity> listaLugares = persistencia.findAll();        
        if(listaLugares == null)
        {
             LOGGER.log(Level.INFO, "no existe ningun lugar en el momento");
            throw new BusinessLogicException("No existen lugares por el momento");
        }
         LOGGER.log(Level.INFO, "Termina proceso de consultar todos los lugares");
        return listaLugares;
     }
     
     
     /**
      * actualiza un lugar con uno nuevo dado por parametro
      * @param identificador, identificador del lugae que se quiere actualizar
      * @param nuevoLugar, el nuevo objeto que se quiere reemplazar
      * @return el lugar actualizado, o null en el caso que no se logro actualizar
      */
     public LugarEntity updateLugar(Long identificador , LugarEntity nuevoLugar)
     {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el lugar  con id = {0}", identificador);
        LugarEntity newCalificacionEntity = persistencia.update(nuevoLugar);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el lugar con id = {0}", identificador);
        return nuevoLugar;
     }
     /**
      * se elimina el lugar dado por parametro
      * @param identificador, el identificador del lugar que se desea eliminar
      * @throws BusinessLogicException  lanza excepción si no se logro eliminar el lugar
      */
       public void deleteLugar(Long identificador) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el lugar con id = {0}", identificador);
        if(persistencia == null)
        {
            LOGGER.log(Level.INFO,"Error en el proceso eliminando el lugar con id = {0}", identificador);
            throw new BusinessLogicException("No se pudo borrar el lugar");
            
        }  
        persistencia.delete(identificador);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el lugar con id = {0}", identificador);
    }     
}
