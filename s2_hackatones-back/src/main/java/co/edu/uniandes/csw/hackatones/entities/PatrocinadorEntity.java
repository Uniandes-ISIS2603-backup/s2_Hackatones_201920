/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Santiago Estupinan
 */
@Entity
public class PatrocinadorEntity extends BaseEntity implements Serializable{

    private String nombre;
    private String descripcion;
    private String ubicacion;
    private String infoAdicional;
    
    @PodamExclude
    @ManyToMany
    private List<HackatonEntity> hackatones;
    
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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * @return the infoAdicional
     */
    public String getInfoAdicional() {
        return infoAdicional;
    }

    /**
     * @param infoAdicional the infoAdicional to set
     */
    public void setInfoAdicional(String infoAdicional) {
        this.infoAdicional = infoAdicional;
    }

    /**
     * @return the hackatones
     */
    public List<HackatonEntity> getHackatones() {
        return hackatones;
    }

    /**
     * @param hackatones the hackatones to set
     */
    public void setHackatones(List<HackatonEntity> hackatones) {
        this.hackatones = hackatones;
    }
    
}
