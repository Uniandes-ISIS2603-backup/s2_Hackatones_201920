/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author ne.cardenas
 */
@Entity
public class CredencialesEntity extends BaseEntity implements Serializable {
    
    private String correo;
    
    private String contrasenha;
    
    public CredencialesEntity() {
        
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getCorreo() {
        return correo;
    }

    /**
     * @return the contrasenha
     */
    public String getContrasenha() {
        return contrasenha;
    }

    /**
     * @param contrasenha the contrasenha to set
     */
    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }
}
