/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.CatalogoEntity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ne.cardenas
 */
public class CatalogoDetailDTO extends CatalogoDTO implements Serializable {
    
    private List<PatrocinadorDTO> patrocinadores;
    
    private List<ProximaDTO> eventosProximos;
    
    private List<ActualDTO> eventosEnCurso;
    
    public CatalogoDetailDTO(CatalogoEntity entity) {
        super(entity);
    }
    
}
