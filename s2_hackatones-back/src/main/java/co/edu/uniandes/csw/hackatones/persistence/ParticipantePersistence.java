/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.persistence;

import co.edu.uniandes.csw.hackatones.entities.ParticipanteEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private static final Logger LOGGER = Logger.getLogger(ParticipantePersistence.class.getName());
    
    @PersistenceContext(unitName = "hackatonesPU")
    protected EntityManager em;
    
    public ParticipanteEntity create (ParticipanteEntity participanteEntity) {
        LOGGER.info("Creando un participante nuevo");
        em.persist(participanteEntity);
        LOGGER.info("Participante creado");
        return participanteEntity;
    }
    
    public ParticipanteEntity find(Long participanteId) {
        LOGGER.log(Level.INFO, "Consultando participante con id={0}", participanteId);
        return em.find(ParticipanteEntity.class, participanteId);
    }
    
    public ParticipanteEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando participante con name= ", name);
        TypedQuery<ParticipanteEntity> q
                = em.createQuery("select u from ParticipanteEntity u where u.nombre = :name", ParticipanteEntity.class);
        q = q.setParameter("nombre", name);
        return q.getSingleResult();
    }
    
    public List<ParticipanteEntity> findAll() {
        LOGGER.info("Consultando todos los participantes");
        TypedQuery<ParticipanteEntity> query = em.createQuery("select u from ParticipanteEntity u", ParticipanteEntity.class);
        return query.getResultList();
    }
    
    public ParticipanteEntity update(ParticipanteEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando participante con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando participante con id={0}", id);
        ParticipanteEntity entity = em.find(ParticipanteEntity.class, id);
        em.remove(entity);
    }
}
