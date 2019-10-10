/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.hackatones.ejb;

import co.edu.uniandes.csw.hackatones.entities.ParticipanteEntity;
import co.edu.uniandes.csw.hackatones.entities.ProximaEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.ParticipantePersistence;
import co.edu.uniandes.csw.hackatones.persistence.ProximaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author a.pedraza
 */
@Stateless
public class ProximaParticipanteLogic {
    private static final Logger LOGGER = Logger.getLogger(ProximaParticipanteLogic.class.getName());
    
    @Inject
    private ProximaPersistence proximaPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    
    @Inject
    private ParticipantePersistence participantePersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    
   /**
     * Asocia un participante existente a una Proxima
     *
     * @param proximaId Identificador de la instancia de Proxima
     * @param participanteId Identificador de la instancia de Participante
     * @return Instancia de ParticipanteEntity que fue asociada a Proxima
     */
    public ParticipanteEntity addParticipante(Long proximaId, Long participanteId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un participante a la proxima con id = {0}", proximaId);
        ParticipanteEntity participanteEntity = participantePersistence.find(participanteId);
        ProximaEntity proximaEntity = proximaPersistence.find(proximaId);
        int equipoSize = proximaEntity.getTamanoEquipos();
        proximaEntity.getListaDeEspera().add(participanteEntity);
        if(proximaEntity.getListaDeEspera().size()+1==equipoSize){
           for(int i=equipoSize-1; i>=0; i--){
               proximaEntity.getInscritos().add(proximaEntity.getListaDeEspera().get(i));
               proximaEntity.getListaDeEspera().remove(i);
           }
           
        }
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un participante a la proxima con id = {0}", proximaId);
        return participantePersistence.find(participanteId);
    }

    /**
     * Obtiene una colección de instancias de ParticipanteEntity asociadas a una
     * instancia de Proxima
     *
     * @param proximaId Identificador de la instancia de Proxima
     * @return Colección de instancias de ParticipanteEntity asociadas a la instancia de
     * Proxima
     */
    public List<ParticipanteEntity> getParticipantes(Long proximaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los participantes de la proxima con id = {0}", proximaId);
        List<ParticipanteEntity> participantes = proximaPersistence.find(proximaId).getInscritos();
        List<ParticipanteEntity> espera = proximaPersistence.find(proximaId).getListaDeEspera();
        for (ParticipanteEntity participante : espera) {
            participantes.add(participante);
        }
        return participantes;
    }

    /**
     * Obtiene una instancia de ParticipanteEntity asociada a una instancia de proxima
     *
     * @param proximaId Identificador de la instancia de proxima
     * @param participanteId Identificador de la instancia de Participante
     * @return La entidad de participante de la proxima
     * @throws BusinessLogicException Si el participante no está asociado a la proxima
     */
    public ParticipanteEntity getParticipante(Long proximaId, Long participanteId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el participante con id = {0} de la proxima con id = " + proximaId, participanteId);
        List<ParticipanteEntity> participantes = proximaPersistence.find(proximaId).getInscritos();
        List<ParticipanteEntity> espera = proximaPersistence.find(proximaId).getListaDeEspera();
        for (ParticipanteEntity participante : espera) {
            participantes.add(participante);
        }
        int i = 0;
        LOGGER.log(Level.INFO, "Termina proceso de consultar el participante con id = {0} de la proxima con id = " + proximaId, participanteId);
        while(i<participantes.size()) {
            ParticipanteEntity actual=participantes.get(i);
            if(actual.getId().equals(participanteId))
                return actual;
        }
        throw new BusinessLogicException("El participante no está asociado a la proxima");
    }

    /**
     * Remplaza las instancias de Participante asociadas a una instancia de proxima
     *
     * @param proximaId Identificador de la instancia de proxima
     * @param participantes Colección de instancias de ParticipanteEntity a asociar a instancia
     * de proxima
     * @return Nueva colección de ParticipanteEntity asociada a la instancia de proxima
     */
    public List<ParticipanteEntity> replaceParticipantess(Long proximaId, List<ParticipanteEntity> participantes) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los participantes asocidos a la proxima con id = {0}", proximaId);
        ProximaEntity proximaEntity = proximaPersistence.find(proximaId);
        int cantidad=proximaEntity.getTamanoEquipos()*(participantes.size()/proximaEntity.getTamanoEquipos());
        proximaEntity.getInscritos().clear();
        for(int i=0; i<cantidad;i++){
            proximaEntity.getInscritos().add(participantes.get(i));
        }
        proximaEntity.getListaDeEspera().clear();
        for(int i=cantidad; i<participantes.size(); i++){
            proximaEntity.getListaDeEspera().add(participantes.get(i));
        }
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los participantes asocidos a la proxima con id = {0}", proximaId);
        return proximaEntity.getInscritos();
    }

    /**
     * Desasocia un Participante existente de una proxima existente
     *
     * @param proximaId Identificador de la instancia de Proxima
     * @param participanteId Identificador de la instancia de Participante
     */
    public void removeParticipante(Long proximaId, Long participanteId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un participante de la proxima con id = {0}", proximaId);
        ProximaEntity proximaEntity = proximaPersistence.find(proximaId);
        ParticipanteEntity participanteEntity = participantePersistence.find(participanteId);
        if(proximaEntity.getInscritos().contains(participanteEntity)){
            proximaEntity.getInscritos().remove(participanteEntity);
            if(proximaEntity.getListaDeEspera().size()>0){
                proximaEntity.getInscritos().add(proximaEntity.getListaDeEspera().get(0));
                proximaEntity.getListaDeEspera().remove(0);
            }
            else{
                int cantidadQuitar=proximaEntity.getTamanoEquipos()-1;
                for(int i=proximaEntity.getInscritos().size()-1; i>=proximaEntity.getInscritos().size()+cantidadQuitar ;i--){
                    proximaEntity.getListaDeEspera().add(proximaEntity.getInscritos().get(i));
                    proximaEntity.getInscritos().remove(i);
                }
            }
        }
        else{
            proximaEntity.getListaDeEspera().remove(participanteEntity);
        }
        LOGGER.log(Level.INFO, "Termina proceso de borrar un participante de la proxima con id = {0}", proximaId);
    }
}