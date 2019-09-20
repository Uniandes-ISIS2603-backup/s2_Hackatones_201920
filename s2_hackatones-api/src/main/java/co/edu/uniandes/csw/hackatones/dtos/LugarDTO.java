/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.adapters.DateAdapter;
import co.edu.uniandes.csw.hackatones.entities.LugarEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author jd.monsalve
 */
public class LugarDTO implements Serializable {
 
    
    // Nombre del lugar donde se realizarÃ¡ la hackaton
private String nombre;

// Ciudad donde se realizarÃ¡ el evento
private String ciudad;

// DirecciÃ³n del evento
private String direccion;

 private Long ID;
 
public LugarDTO(){

}


public LugarDTO(LugarEntity entidad){

    if(entidad!= null){
    
    this.nombre = entidad.getNombre();
    this.ciudad = entidad.getCiudad();
    this.ID = entidad.getId();
    this.direccion = entidad.getDireccion();
    
    }


} 

    
public LugarEntity toEntity(){
LugarEntity lugar = new LugarEntity();
lugar.setId((this.getID()));
lugar.setCiudad(this.getCiudad());
lugar.setDireccion(this.getDireccion());
lugar.setNombre(this.getNombre());
return lugar;
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

    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public void setID(Long ID){
    this.ID = ID;
    }
    
    public Long getID(){
    return this.ID;
    }
    
   @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    } 
    
    
}
