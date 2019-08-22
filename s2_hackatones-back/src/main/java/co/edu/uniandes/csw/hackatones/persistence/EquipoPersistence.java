/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;

import co.edu.uniandes.csw.hackatones.entities.EquipoEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ja.torresl
 */
@Stateless
public class EquipoPersistence {
    
        @PersistenceContext(unitName = "HackatonPU")
    protected EntityManager em;
    
    public EquipoEntity create(EquipoEntity equipo){
       em.persist(equipo);
       
       return equipo;
    }
}
