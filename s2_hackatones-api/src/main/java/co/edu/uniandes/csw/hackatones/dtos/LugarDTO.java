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
 
    
/*
/ Nombre del lugar donde se realizara la hackaton
*/
private String nombre;

/*
Ciudad donde se realizara el evento
*/
private String ciudad;
/*
direcion del evento
*/
private String direccion;
/*
identificador del lugar
*/
 private Long ID;
 
 
 private String imagen;
 
 /*
 constructor vacio de la clase
 */
public LugarDTO(){

}

/**
 * Clase que representa un lugar
 * @param entidad, entidad lugar que se quieren heredar atributos
 */
public LugarDTO(LugarEntity entidad){

    if(entidad!= null){
    
    this.nombre = entidad.getNombre();
    this.ciudad = entidad.getCiudad();
    this.ID = entidad.getId();
    this.direccion = entidad.getDireccion();
    this.imagen = entidad.getImagen();
    
    }
} 

/*
metodo que devuelve la clase en una entidad
*/   
public LugarEntity toEntity(){
LugarEntity lugar = new LugarEntity();
lugar.setId((this.getID()));
lugar.setCiudad(this.getCiudad());
lugar.setDireccion(this.getDireccion());
lugar.setNombre(this.getNombre());
lugar.setImagen(this.getImagen());
return lugar;
}


 /**
  * devuelve el nombre del lugar
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * establece el nombre del lugar
     * @param nombre el nombre a definir
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setImagen(String imagen){
    this.imagen = imagen;
    }
    
    public String getImagen(){
    return this.imagen;
    }

    /**
     * devuelve la ciudad del lugar
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * establece la ciudad del lugar
     * @param ciudad la ciudad del lugar
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * 
     * da la direccion del lugar
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * establece la direccion del lugar
     * @param direccion la direccion del lugar
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    /**
     * establece el id del lugar
     * @param ID 
     */
    public void setID(Long ID){
    this.ID = ID;
    }
    
    /*
    devuelve el id de la clase
    */
    public Long getID(){
    return this.ID;
    }
    
    /*
    metodo toString
    */
   @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    } 
    
    
}
