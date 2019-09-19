/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.ParticipanteEntity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ne.cardenas
 */
public class ParticipanteDetailDTO extends ParticipanteDTO implements Serializable {
    
    private Boolean inscrito;
    
    private HackatonDTO hackaton;
    
    private EquipoDTO equipo;
    
    private List<TecnologiaDTO> tecnologias;
    
    private List<InteresDTO> intereses;
    
    private List<LenguajeDTO> lenguajes;
    
    public ParticipanteDetailDTO (ParticipanteEntity entity) {
        super(entity);
    }
}
