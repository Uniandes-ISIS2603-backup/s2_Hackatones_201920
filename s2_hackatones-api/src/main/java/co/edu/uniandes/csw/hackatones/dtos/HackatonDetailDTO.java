/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.CalificacionEntity;
import co.edu.uniandes.csw.hackatones.entities.EquipoEntity;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.entities.PatrocinadorEntity;
import co.edu.uniandes.csw.hackatones.entities.UsuarioEntity;
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
    private List<PatrocinadorDetailDTO> patrocinadores;
    
    /**
     * Relacion uno o muchos inscritos
     */
    private List<UsuarioDetailDTO> inscritos;
    
    /**
     * Relacion uno o muchos observadores
     */
    private List<UsuarioDetailDTO> observadores;
    
    /**
     * Relacion uno o muchos equipos
     */
    private List<EquipoDTO> equipos;

    public HackatonDetailDTO() {
        super();
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param hackatonEntity La entidad de la cual se construye el DTO
     */
    public HackatonDetailDTO(HackatonEntity hackatonEntity) 
    {
        super(hackatonEntity);
        if (hackatonEntity != null) 
        {
            if (hackatonEntity.getCalificaciones() != null) 
            {
                calificaciones = new ArrayList<>();
                for (CalificacionEntity entityCalificacion : hackatonEntity.getCalificaciones()) 
                {
                    calificaciones.add(new CalificacionDTO(entityCalificacion));
                }
            }
            if (hackatonEntity.getPatrocinadores() != null) 
            {
                patrocinadores = new ArrayList<>();
                for (PatrocinadorEntity entityPatrocinador : hackatonEntity.getPatrocinadores()) 
                {
                    patrocinadores.add(new PatrocinadorDetailDTO(entityPatrocinador));
                }
            }
            if (hackatonEntity.getInscritos()!= null) 
            {
                inscritos = new ArrayList<>();
                for (UsuarioEntity entityUsuario : hackatonEntity.getInscritos())
                {
                    inscritos.add(new UsuarioDetailDTO(entityUsuario));
                }
            }
            if (hackatonEntity.getObservadores()!= null) 
            {
                observadores = new ArrayList<>();
                for (UsuarioEntity entityUsuario1 : hackatonEntity.getObservadores()) 
                {
                    observadores.add(new UsuarioDetailDTO(entityUsuario1));
                }
            }
            if (hackatonEntity.getEquipos()!= null) 
            {
                equipos = new ArrayList<>();
                for (EquipoEntity entityEquipo : hackatonEntity.getEquipos())
                {
                    equipos.add(new EquipoDTO(entityEquipo));
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
        if (inscritos != null) {
            List<UsuarioEntity> inscritosEntity = new ArrayList<>();
            for (UsuarioDetailDTO dtoUsuario : inscritos) {
                inscritosEntity.add(dtoUsuario.toEntity());
            }
            entity.setInscritos(inscritosEntity);
        }
        if (observadores != null) {
            List<UsuarioEntity> observadoresEntity = new ArrayList<>();
            for (UsuarioDetailDTO dtoUsuario : observadores) {
                observadoresEntity.add(dtoUsuario.toEntity());
            }
            entity.setInscritos(observadoresEntity);
        }
        if (equipos != null) {
            List<EquipoEntity> equipoEntity = new ArrayList<>();
            for (EquipoDTO dtoEquipo : equipos) {
                equipoEntity.add(dtoEquipo.toEntity());
            }
            entity.setEquipos(equipoEntity);
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
    public List<PatrocinadorDetailDTO> getPatrocinadores() {
        return patrocinadores;
    }

    /**
     * @param patrocinadores the patrocinadores to set
     */
    public void setPatrocinadores(List<PatrocinadorDetailDTO> patrocinadores) {
        this.patrocinadores = patrocinadores;
    }

    /**
     * @return the inscritos
     */
    public List<UsuarioDetailDTO> getInscritos() {
        return inscritos;
    }

    /**
     * @param inscritos the inscritos to set
     */
    public void setInscritos(List<UsuarioDetailDTO> inscritos) {
        this.inscritos = inscritos;
    }

    /**
     * @return the observadores
     */
    public List<UsuarioDetailDTO> getObservadores() {
        return observadores;
    }

    /**
     * @param observadores the observadores to set
     */
    public void setObservadores(List<UsuarioDetailDTO> observadores) {
        this.observadores = observadores;
    }

    /**
     * @return the equipos
     */
    public List<EquipoDTO> getEquipos() {
        return equipos;
    }

    /**
     * @param equipos the equipos to set
     */
    public void setEquipos(List<EquipoDTO> equipos) {
        this.equipos = equipos;
    }

}
