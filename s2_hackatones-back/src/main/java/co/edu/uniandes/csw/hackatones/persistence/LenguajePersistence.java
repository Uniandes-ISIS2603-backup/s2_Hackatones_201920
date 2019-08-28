/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;

import co.edu.uniandes.csw.hackatones.entities.LenguajeEntity;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author Santiago Estupinan
 */
@Stateless
public class LenguajePersistence {
    
    private static final Logger LOGGER = Logger.getLogger(CatalogoPersistence.class.getName());

    @PersistenceContext(unitName = "hackatonesPU")
    protected EntityManager em;

    
    public LenguajeEntity create(LenguajeEntity lenguaje)
    {
        em.persist(lenguaje);
        return lenguaje;
    }
     
}
