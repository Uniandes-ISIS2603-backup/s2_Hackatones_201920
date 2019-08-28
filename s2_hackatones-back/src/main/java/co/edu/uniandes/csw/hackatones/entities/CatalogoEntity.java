    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author ne.cardenas
 */
@Entity
public class CatalogoEntity extends BaseEntity implements Serializable {
    
    private List<PatrocinadorEntity> patrocinadores;
    
    private List<ProximaEntity> eventosProximos;
    
    private List<ActualEntity> eventosEnCurso;
    
    public CatalogoEntity() {
        
    }
    
    public List<PatrocinadorEntity> getPatrocinadores() {
        return patrocinadores;
    }
    
    public void setPatrocinadores(List<PatrocinadorEntity> pe) {
        patrocinadores = pe;
    }
    
    public List<ProximaEntity> getProximos() {
        return eventosProximos;
    }
    
    public void setProximos(List<ProximaEntity> pe) {
        eventosProximos = pe;
    }
    
    public List<ActualEntity> getActuales() {
        return eventosEnCurso;
    }
    
    public void setActuales(List<ActualEntity> ae) {
        eventosEnCurso = ae;
    }
}
