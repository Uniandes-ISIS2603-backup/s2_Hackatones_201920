package co.edu.uniandes.csw.hackatones.entities;
import java.io.Serializable;
import javax.persistence.Entity;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jd.monsalve
 */
@Entity
public class LugarEntity extends BaseEntity implements Serializable{  

// Nombre del lugar donde se realizara la hackaton

private String nombre;

// Ciudad donde se realizara el evento
private String ciudad;

// Direccion del evento
private String direccion;

// Imagen del lugar
private String imagen;
    
 /**
  * devuelve el nombre del lugar
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
    
    public String getImagen(){
    return imagen;
    }
    
    public void setImagen(String imagen){
    this.imagen = imagen;
    }

    /**
     * establece el nombre del lugar
     * @param nombre el nombre a definir
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * da la direccion del lugar
     * @param direccion la direccion del lugar
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}