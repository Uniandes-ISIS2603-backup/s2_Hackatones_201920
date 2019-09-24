/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.podam.DateStrategy;
import java.util.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author jc.higuera
 */
class HackatonDTO {
     private int limite_participantes;
    
    private String nombre;
    
    @Enumerated(EnumType.ORDINAL)
    private HackatonEntity.EnumTipo tipo;
    
    private String tema;
    
    private String especificacion;
    
    private Integer nivel;
    
    private String imagen;
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaInicio;
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaFin;
    
    private HackatonEntity.EnumPremio premio;
    
    private Boolean finalizada;
    
    public HackatonDTO(HackatonEntity entidad)
    {
        this.limite_participantes = entidad.getLimite_participantes();
        this.nombre = entidad.getNombre();
        //this.tipo = entidad.getTipo();
        this.tema = entidad.getTema();
        this.especificacion = entidad.getEspecificacion();
        this.nivel = entidad.getNivel();
        this.imagen = entidad.getImagen();
        this.fechaInicio = entidad.getFechaInicio();
        this.fechaFin = entidad.getFechaFin();
        //this.premio = entidad.getPremio();
        this.finalizada = entidad.getFinalizada();
    }

    public HackatonEntity toEntity(){
        HackatonEntity hackaton = new HackatonEntity();
        hackaton.setLimite_participantes(this.limite_participantes);
        hackaton.setNombre(this.nombre);
        hackaton.setTipo(this.tipo);
        hackaton.setTema(this.tema);
        hackaton.setEspecificacion(this.especificacion);
        hackaton.setNivel(this.nivel);
        hackaton.setImagen(this.imagen);
        hackaton.setFechaInicio(this.fechaInicio);
        hackaton.setFechaFin(this.fechaFin);
        hackaton.setPremio(this.premio);
        hackaton.setFinalizada(this.finalizada);
        return hackaton;
    }
    /**
     * @return the limite_participantes
     */
    public int getLimite_participantes() {
        return limite_participantes;
    }

    /**
     * @param limite_participantes the limite_participantes to set
     */
    public void setLimite_participantes(int limite_participantes) {
        this.limite_participantes = limite_participantes;
    }

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
     * @return the tipo
     */
    public HackatonEntity.EnumTipo getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(HackatonEntity.EnumTipo tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the tema
     */
    public String getTema() {
        return tema;
    }

    /**
     * @param tema the tema to set
     */
    public void setTema(String tema) {
        this.tema = tema;
    }

    /**
     * @return the especificacion
     */
    public String getEspecificacion() {
        return especificacion;
    }

    /**
     * @param especificacion the especificacion to set
     */
    public void setEspecificacion(String especificacion) {
        this.especificacion = especificacion;
    }

    /**
     * @return the nivel
     */
    public Integer getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the premio
     */
    public HackatonEntity.EnumPremio getPremio() {
        return premio;
    }

    /**
     * @param premio the premio to set
     */
    public void setPremio(HackatonEntity.EnumPremio premio) {
        this.premio = premio;
    }

    /**
     * @return the finalizada
     */
    public Boolean getFinalizada() {
        return finalizada;
    }

    /**
     * @param finalizada the finalizada to set
     */
    public void setFinalizada(Boolean finalizada) {
        this.finalizada = finalizada;
    }
}
