/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import java.io.Serializable;

/**
 *
 * @author ja.torresl
 */
public class TecnologiaDTO implements Serializable{
    
     //private List<UsuarioDTO> interesados;
    
    private String nombre;
    

    public TecnologiaDTO() {
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
/**
    public List<UsuarioDTO> getInteresados() {
        return interesados;
    }

    public void setInteresados(List<UsuarioDTO> interesados) {
        this.interesados = interesados;
    }
*/
   
    
}
