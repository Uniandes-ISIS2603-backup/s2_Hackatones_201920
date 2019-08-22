/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;

import co.edu.uniandes.csw.hackatones.entities.ObservadoresEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Estudiante
 */
public class ObservadoresPersistence {
    
    @PersistenceContext(unitName = "Hackaton")
    protected EntityManager em;
    
    public ObservadoresEntity create(ObservadoresEntity observadores){
        em.persist(observadores);
        return observadores;
    }
    
}
