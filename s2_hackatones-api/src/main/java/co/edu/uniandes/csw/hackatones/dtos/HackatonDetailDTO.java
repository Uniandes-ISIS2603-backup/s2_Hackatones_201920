/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.CalificacionEntity;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.entities.PatrocinadorEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estudiante
 */
public class HackatonDetailDTO extends HackatonDTO implements Serializable {

    /**
     * Relacion cero o muchas califiaciones
     */
    private List<CalificacionDTO> calificaciones;
    
    /**
     * Relacion uno o muchos patrocinadores
     */
    private List<PatrocinadorDTO> patrocinadores;

    public HackatonDetailDTO() {
        super();
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param hackatonEntity La entidad de la cual se construye el DTO
     */
    public HackatonDetailDTO(HackatonEntity hackatonEntity) {
        super(hackatonEntity);
        if (hackatonEntity != null) {
            if (hackatonEntity.getCalificaciones() != null) {
                calificaciones = new ArrayList<>();
                for (CalificacionEntity entityCalificacion : hackatonEntity.getCalificaciones()) {
                    calificaciones.add(new CalificacionDTO(entityCalificacion));
                }
            }
            if (hackatonEntity.getPatrocinadores() != null) {
                patrocinadores = new ArrayList<>();
                for (PatrocinadorEntity entityPatrocinador : hackatonEntity.getPatrocinadores()) {
                    patrocinadores.add(new PatrocinadorDTO(entityPatrocinador));
                }
            }
        }
    }

    /**
     * Transformar el DTO a una entidad
     *
     * @return La entidad que representa la hackaton.
     */
    @Override
    public HackatonEntity toEntity() {
        HackatonEntity entity = super.toEntity();
        if (calificaciones != null) {
            List<CalificacionEntity> calificacionesEntity = new ArrayList<>();
            for (CalificacionDTO dtoCalificacion : calificaciones) {
                calificacionesEntity.add(dtoCalificacion.toEntity());
            }
            entity.setCalificaciones(calificacionesEntity);
        }
        if (patrocinadores != null) {
            List<PatrocinadorEntity> patrocinadoresEntity = new ArrayList<>();
            for (PatrocinadorDTO dtoPatrocinadores : patrocinadores) {
                patrocinadoresEntity.add(dtoPatrocinadores.toEntity());
            }
            entity.setPatrocinadores(patrocinadoresEntity);
        }
        return entity;
    }

    /**
     * @return the calificaciones
     */
    public List<CalificacionDTO> getCalificaciones() {
        return calificaciones;
    }

    /**
     * @param calificaciones the calificaciones to set
     */
    public void setCalificaciones(List<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }

    /**
     * @return the patrocinadores
     */
    public List<PatrocinadorDTO> getPatrocinadores() {
        return patrocinadores;
    }

    /**
     * @param patrocinadores the patrocinadores to set
     */
    public void setPatrocinadores(List<PatrocinadorDTO> patrocinadores) {
        this.patrocinadores = patrocinadores;
    }

}
