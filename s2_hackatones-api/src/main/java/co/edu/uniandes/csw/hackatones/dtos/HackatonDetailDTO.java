/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Estudiante
 */
public class HackatonDetailDTO extends HackatonDTO implements Serializable {
    
    private List<CalificacionDTO> calificaciones;
    private List<UsuarioDTO> participantes;
    private EquipoDTO equipo;
    private LugarDTO lugar;
    
    public HackatonDetailDTO(HackatonEntity hackaton){
        super(hackaton);
    }
}
