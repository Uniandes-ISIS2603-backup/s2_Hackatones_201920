/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.entities;
import co.edu.uniandes.csw.hackatones.podam.DateStrategy;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;
/**
 *
 * @author Juan Camilo Higuera
 */
@Entity
public class HackatonEntity extends BaseEntity implements Serializable{

    /**
     * @return the limite_participantes
     */
    public Integer getLimite_participantes() {
        return limite_participantes;
    }

    /**
     * @param limite_participantes the limite_participantes to set
     */
    public void setLimite_participantes(Integer limite_participantes) {
        this.limite_participantes = limite_participantes;
    }
    
    
    /**
     * El tipo de hackaton
     */
    public enum EnumTipo {
    TIPO1, TIPO2, TIPO3, TIPO4;
    }
    
    /**
     * El premio de la hackaton
     */
    public enum EnumPremio{
    PREMIO1,PREMIO2,PREMIO3,PREMIO4
    }
    
    /**
     * El limite de participantes de la hackaton
     */
    private Integer limite_participantes;
    
    /**
     * El nombre de la hakaton
     */
    private String nombre;
    
    
    /**
     * El tipo de hackaton
     */
    @Enumerated(EnumType.ORDINAL)
    private EnumTipo tipo;
    
    /**
     * El tema de la hackaton
     */
    private String tema;
    
    /**
     * La especificación de hackaton
     */
    private String especificacion;
    
    /**
     * El nivel de la hackaton
     */
    private Integer nivel;
    
    /**
     * La imagen de la hackaton
     */
    private String imagen;
    
    /**
     * La fecha de inicio de la hackaton
     */
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaInicio;
    
    /**
     * La fecha de fin de la hackaton
     */
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaFin;
    
    /**
     * EL premio de la hackaton
     */
    private EnumPremio premio;
    
    /**
     * Indica si la hackaton ha finalizado o no
     */
    private Boolean finalizada;
    
    /**
     * El tamaño de los equipos
     */
    private Integer tamanoEquipos;
   
    
    /**
     * El lugar de la hackaton
     */
    @PodamExclude
    @OneToOne
    private LugarEntity lugar;
  
 
    /**
     * Las calificaciones de la hackaton
     */
    @PodamExclude
    @OneToMany(mappedBy = "hackaton", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CalificacionEntity> calificaciones;
    
    /**
     * Los patrocinadores de la hackaton
     */
    @PodamExclude
    @ManyToMany
    private List<PatrocinadorEntity> patrocinadores;

    /**
     * Los observadores de la hackaton
     */
    @PodamExclude
    @OneToMany
    private List<UsuarioEntity> observadores;
    
    
    /**
     * El equipo ganador de la hackaton
     */
    @PodamExclude
    @OneToOne
    private EquipoEntity equipoGanador;
    
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
    
    public String getTipo(){
    return tipo.toString();
    }
    
    public void setTipo(EnumTipo nuevo){
    this.tipo = nuevo;
    }
    
    public EnumTipo getTipoEnum()
    {
        return tipo;
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
    
    public String getImagen(){
    return imagen;
    }
    
    public void setImagen(String ima){
    this.imagen = ima;
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
    
    public String getPremio(){
    return premio.toString();
    }
    
    public void setPremio(EnumPremio premioNuevo){
    this.premio = premioNuevo;
    }
    
    public EnumPremio getPremioEnum(){
        return premio;
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
    
     /**
     * @return the lugar
     */
    public LugarEntity getLugar() {
        return lugar;
    }

    /**
     * @param lugar the lugar to set
     */
    public void setLugar(LugarEntity lugar) {
        this.lugar = lugar;
    }
    
 /**
     * @return the calificaciones
     */
    public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

    /**
     * @param calificaciones the calificaciones to set
     */
    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }
    
    /**
     * @return the observadores
     */
    public List<UsuarioEntity> getObservadores() {
        return observadores;
    }

    /**
     * @param observadores the observadores to set
     */
    public void setObservadores(List<UsuarioEntity> observadores) {
       this.observadores = observadores;
    }
     
   

    /**
     * @return the patrocinadores
     */
    public List<PatrocinadorEntity> getPatrocinadores() {
        return patrocinadores;
    }

    /**
     * @param patrocinadores the patrocinadores to set
     */
    public void setPatrocinadores(List<PatrocinadorEntity> patrocinadores) {
        this.patrocinadores = patrocinadores;
    }

    /**
     * @return the equipo_ganador
     */
    public EquipoEntity getEquipoGanador() {
        return equipoGanador;
    }

    /**
     * @param equipo_ganador the equipo_ganador to set
     */
    public void setEquipoGanador(EquipoEntity equipoGanador) {
        this.equipoGanador = equipoGanador;
    }

    /**
     * @return the tamanoEquipos
     */
    public Integer getTamanoEquipos() {
        return tamanoEquipos;
    }

    /**
     * @param tamanoEquipos the tamanoEquipos to set
     */
    public void setTamanoEquipos(Integer tamanoEquipos) {
        this.tamanoEquipos = tamanoEquipos;
    }
 
}
