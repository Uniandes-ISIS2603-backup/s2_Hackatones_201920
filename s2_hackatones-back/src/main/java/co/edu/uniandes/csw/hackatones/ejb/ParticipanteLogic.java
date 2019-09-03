/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.ParticipanteEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.ParticipantePersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ne.cardenas
 */
@Stateless  
public class ParticipanteLogic {
    
    @Inject
    private ParticipantePersistence persistencia;
    
    public ParticipanteEntity createParticipante(ParticipanteEntity entity) throws BusinessLogicException {
        
        if (entity.getHackaton() == null)
            throw new BusinessLogicException("El participante no tiene hackatón actual");
        
        if (entity.getTecnologias() == null)
            throw new BusinessLogicException("Las tecnologías están vacías");
        
        if (entity.getIntereses() == null)
            throw new BusinessLogicException("El participante no tiene intereses");
        
        if (entity.getLenguajes() == null)
            throw new BusinessLogicException("Los lenguajes están vacíos"); 
        
        entity = persistencia.create(entity);
        return entity;
        
    }
    
}
