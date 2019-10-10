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
class EquipoDTO implements  Serializable{
    
      private long id;
    
    private String nombre;

    public EquipoDTO() {
    }
    
        public EquipoDTO(EquipoEntity equipoEntity) {
        if (equipoEntity != null) {
            this.id = equipoEntity.getId();
            this.nombre = equipoEntity.getNombre();
        }
    }
        
        
    /**
     * Convierte un objeto EquipoDTO a EquipoEntity.
     *
     * @return Nueva objeto EquipoEntity.
     *
     */
    public EquipoEntity toEntity() {
        EquipoEntity authorEntity = new EquipoEntity();
        authorEntity.setId(this.getId());
        authorEntity.setNombre(this.getNombre());
        return authorEntity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
