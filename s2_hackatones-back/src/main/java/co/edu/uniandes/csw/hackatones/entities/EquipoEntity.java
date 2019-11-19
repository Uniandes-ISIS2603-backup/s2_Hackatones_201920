/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ja.torresl
 */
@Entity
public class EquipoEntity extends BaseEntity implements Serializable{
    private String nombre;
    
    private boolean esGanador;


  
    @PodamExclude
    @ManyToOne
    private HackatonEntity hackaton;
    
    @PodamExclude 
    @OneToMany(mappedBy = "equipo")
    private List<UsuarioEntity> participantes = new ArrayList<>();
    
    
    @Override
    public boolean equals(Object obj) {
    if (! super.equals(obj)) {
      return false;
    }
    EquipoEntity fobj = (EquipoEntity) obj;
    return this.getId().equals(fobj.getId());
    } 

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.nombre);
        hash = 97 * hash + Objects.hashCode(this.esGanador);
        hash = 97 * hash + Objects.hashCode(this.hackaton);
        hash = 97 * hash + Objects.hashCode(this.participantes);
        return hash;
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

    
        public boolean isEsGanador() {
        return esGanador;
    }

    public void setEsGanador(boolean esGanador) {
        this.esGanador = esGanador;
    }
    
    /**
     * @return the hackaton
     */
    public HackatonEntity getHackaton() {
        return hackaton;
    }

    /**
     * @param hackaton the hackaton to set
     */
    public void setHackaton(HackatonEntity hackaton) {
        this.hackaton = hackaton;
    }
    
    
    public List<UsuarioEntity> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<UsuarioEntity> participantes) {
        this.participantes = participantes;
    }
    
}
