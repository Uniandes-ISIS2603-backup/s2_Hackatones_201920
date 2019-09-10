/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;


import co.edu.uniandes.csw.hackatones.entities.EquipoEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.EquipoPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ja.torresl
 */
 
@Stateless
public class EquipoLogic {
    
    @Inject
    private EquipoPersistence persistence;
    
    public EquipoEntity createEquipo(EquipoEntity equipo)throws BusinessLogicException{
        if(equipo.getNombre()==null){
            throw new BusinessLogicException("El nombre del equipo está vacío");
        }
        equipo= persistence.create(equipo);
        return equipo;
    }

    
}
