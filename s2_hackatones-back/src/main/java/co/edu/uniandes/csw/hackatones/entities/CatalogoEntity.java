    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;


/**
 *
 * @author ne.cardenas
 */
@Entity
public class CatalogoEntity extends BaseEntity implements Serializable {
        
    @PodamExclude
    @OneToMany
    private List<PatrocinadorEntity> patrocinadores = new ArrayList<>();
    
    @PodamExclude
    @OneToMany
    private List<ProximaEntity> eventosProximos = new ArrayList<>();
    
    @PodamExclude
    @OneToMany
    private List<ActualEntity> eventosEnCurso = new ArrayList<>();
    
    private String nombre;

    /**
     * @return the patrocinadores
     */
    public List<PatrocinadorEntity> getPatrocinadores() {
        return patrocinadores;
    }

    /**
     * @param patrocinadores the patrocinadores to set
     */
    public void setPatrocinadores(List<PatrocinadorEntity> patrocinadores) {
        this.patrocinadores = patrocinadores;
    }

    /**
     * @return the eventosProximos
     */
    public List<ProximaEntity> getEventosProximos() {
        return eventosProximos;
    }

    /**
     * @param eventosProximos the eventosProximos to set
     */
    public void setEventosProximos(List<ProximaEntity> eventosProximos) {
        this.eventosProximos = eventosProximos;
    }

    /**
     * @return the eventosEnCurso
     */
    public List<ActualEntity> getEventosEnCurso() {
        return eventosEnCurso;
    }

    /**
     * @param eventosEnCurso the eventosEnCurso to set
     */
    public void setEventosEnCurso(List<ActualEntity> eventosEnCurso) {
        this.eventosEnCurso = eventosEnCurso;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    

}
