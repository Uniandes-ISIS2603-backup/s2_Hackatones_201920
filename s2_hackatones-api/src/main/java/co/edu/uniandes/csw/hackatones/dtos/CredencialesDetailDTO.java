/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.CredencialesEntity;
import java.io.Serializable;

/**
 *
 * @author ne.cardenas
 */
public class CredencialesDetailDTO extends CredencialesDTO implements Serializable {
    
    private UsuarioDTO usuario;
    
    public CredencialesDetailDTO(CredencialesEntity entity) {
        super(entity);
    }
}
