/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.EquipoEntity;
import java.io.Serializable;

/**
 *
 * @author ja.torresl
 */
public class EquipoDTO implements  Serializable{
    
    private Long id;
    
    private String nombre;
    
    private Boolean esGanador;
    
    private HackatonDTO hackaton;

    public EquipoDTO() {
    }
    
        public EquipoDTO(EquipoEntity equipoEntity) {
        if (equipoEntity != null) {
            this.id = equipoEntity.getId();
            this.nombre = equipoEntity.getNombre();
            if (equipoEntity.getHackaton()!= null) {
                this.hackaton = new HackatonDTO(equipoEntity.getHackaton());
            } else {
                this.hackaton = null;
            }
        }
    }
        
        
    /**
     * Convierte un objeto EquipoDTO a EquipoEntity.
     *
     * @return Nueva objeto EquipoEntity.
     *
     */
    public EquipoEntity toEntity() {
        EquipoEntity equipoEntity = new EquipoEntity();
        equipoEntity.setId(this.getId());
        equipoEntity.setNombre(this.getNombre());
        equipoEntity.setEsGanador(this.esGanador);
        if (this.hackaton != null) {
            equipoEntity.setHackaton(this.hackaton.toEntity());
        }
        return equipoEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean isEsGanador() {
        return esGanador;
    }

    public void setEsGanador(Boolean esGanador) {
        this.esGanador = esGanador;
    }

    public HackatonDTO getHackaton() {
        return hackaton;
    }

    public void setHackaton(HackatonDTO hackaton) {
        this.hackaton = hackaton;
    }

    

}
