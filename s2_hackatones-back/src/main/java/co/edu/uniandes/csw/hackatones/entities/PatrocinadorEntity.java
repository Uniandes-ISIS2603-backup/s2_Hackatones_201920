/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Santiago Estupinan
 */
@Entity
public class PatrocinadorEntity extends BaseEntity implements Serializable{
    
    /**
     * El nombre del patrocinador
     */
    private String nombre;
    
    /**
     * La descricpion del patrocinador
     */
    private String descripcion;
    
    /**
     * La ubicacion del patrocinador
     */
    private String ubicacion;
    
    /**
     * La info adicional del patrocinador
     */
    private String infoAdicional;
    
    /**
     * La lista de hackatones de las que hace parrte el patrocinador
     */
    @PodamExclude
    @ManyToMany
    private List<HackatonEntity> hackatones;
    
    /**
     * El responsable del patrocinador
     */
    @PodamExclude
    @OneToOne
    private UsuarioEntity responsable;
    
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

    /**
     * @return the responsable
     */
    public UsuarioEntity getResponsable() {
        return responsable;
    }

    /**
     * @param responsable the responsable to set
     */
    public void setResponsable(UsuarioEntity responsable) {
        this.responsable = responsable;
    }
    
    @Override
    public boolean equals(Object obj) {
    if (! super.equals(obj)) {
      return false;
    }
    PatrocinadorEntity fobj = (PatrocinadorEntity) obj;
    return this.getId().equals(fobj.getId());
  }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.nombre);
        hash = 83 * hash + Objects.hashCode(this.descripcion);
        hash = 83 * hash + Objects.hashCode(this.ubicacion);
        hash = 83 * hash + Objects.hashCode(this.infoAdicional);
        hash = 83 * hash + Objects.hashCode(this.hackatones);
        hash = 83 * hash + Objects.hashCode(this.responsable);
        return hash;
    }
}
