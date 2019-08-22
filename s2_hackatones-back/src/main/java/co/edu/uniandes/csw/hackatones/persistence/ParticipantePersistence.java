/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;

import co.edu.uniandes.csw.hackatones.entities.ParticipanteEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ne.cardenas
 */
@Stateless
public class ParticipantePersistence {
    
    @PersistenceContext(unitName = "hackatonesPU")
    protected EntityManager em;
    
    public ParticipanteEntity create (ParticipanteEntity participanteEntity) {
        em.persist(participanteEntity);
        return participanteEntity;
    }
    
    public ParticipanteEntity find(Long participanteId) {
        return em.find(ParticipanteEntity.class, participanteId);
    }
    
    public List<ParticipanteEntity> findAll() {
        TypedQuery<ParticipanteEntity> query = em.createQuery("select u from ParticipanteEntity u", ParticipanteEntity.class);
        return query.getResultList();
    }
    
}
