/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author Santiago Estupinan
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable{
    
    protected String nombre;
    
    @PodamExclude
    @OneToOne(cascade = CascadeType.REMOVE)
    private CredencialesEntity credenciales;

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
     * @return the credenciales
     */
    public CredencialesEntity getCredenciales() {
        return credenciales;
    }

    /**
     * @param credenciales the credenciales to set
     */
    public void setCredenciales(CredencialesEntity credenciales) {
        this.credenciales = credenciales;
    }
        
      
}
