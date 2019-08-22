/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;

import co.edu.uniandes.csw.hackatones.entities.PatrocinadorEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Estudiante
 */
@Stateless
public class PatrocinadorPersistence 
{
    @PersistenceContext(unitName = "hackatones")
    protected EntityManager em;

    
    public PatrocinadorEntity create(PatrocinadorEntity patrocinador)
    {
        em.persist(patrocinador);
        return patrocinador;
    }
            
            
            
}
