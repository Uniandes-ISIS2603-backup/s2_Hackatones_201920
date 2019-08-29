/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author ja.torresl
 */
@Entity
public class TecnologiaEntity extends BaseEntity implements Serializable{
    private String nombre;

    private List<UsuarioEntity> interesados;
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
     * @return the interesados
     */
    public List<UsuarioEntity> getInteresados() {
        return interesados;
    }

    /**
     * @param interesados the interesados to set
     */
    public void setInteresados(List<UsuarioEntity> interesados) {
        this.interesados = interesados;
    }
}