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
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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

    @PodamExclude
    @ManyToMany(mappedBy = "hackatones")
    private List<TecnologiaEntity> tecnologias;
    
    
    private String reglas;
    
    private String restricciones;
    
    
    @PodamExclude
    @ManyToMany(mappedBy = "hackatones")
    private List<UsuarioEntity> inscritos;
    
    
   
    @PodamExclude
    @OneToMany(mappedBy = "hackaton")
    private List<EquipoEntity> equipos;
    
    
    /**
     * El limite de participantes de la hackaton
     */
    private Integer limiteParticipantes;
    
    /**
     * El nombre de la hakaton
     */
    private String nombre;
    
    
    /**
     * El tipo de hackaton
     */
    private String tipo;
    
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
    private String premio;
    
    /**
     * Indica si la hackaton ha finalizado o no
     */
    private Boolean finalizada;
    
    /**
     * 
     */
    private Boolean iniciada;
    
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
    
    
    @Override
    public boolean equals(Object obj) {
    if (! super.equals(obj)) {
      return false;
    }
    HackatonEntity fobj = (HackatonEntity) obj;
    return this.getId().equals(fobj.getId());
  }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.reglas);
        hash = 89 * hash + Objects.hashCode(this.restricciones);
        hash = 89 * hash + Objects.hashCode(this.inscritos);
        hash = 89 * hash + Objects.hashCode(this.equipos);
        hash = 89 * hash + Objects.hashCode(this.limiteParticipantes);
        hash = 89 * hash + Objects.hashCode(this.nombre);
        hash = 89 * hash + Objects.hashCode(this.tipo);
        hash = 89 * hash + Objects.hashCode(this.tema);
        hash = 89 * hash + Objects.hashCode(this.especificacion);
        hash = 89 * hash + Objects.hashCode(this.nivel);
        hash = 89 * hash + Objects.hashCode(this.imagen);
        hash = 89 * hash + Objects.hashCode(this.fechaInicio);
        hash = 89 * hash + Objects.hashCode(this.fechaFin);
        hash = 89 * hash + Objects.hashCode(this.premio);
        hash = 89 * hash + Objects.hashCode(this.finalizada);
        hash = 89 * hash + Objects.hashCode(this.iniciada);
        hash = 89 * hash + Objects.hashCode(this.tamanoEquipos);
        hash = 89 * hash + Objects.hashCode(this.lugar);
        hash = 89 * hash + Objects.hashCode(this.calificaciones);
        hash = 89 * hash + Objects.hashCode(this.patrocinadores);
        hash = 89 * hash + Objects.hashCode(this.observadores);
        hash = 89 * hash + Objects.hashCode(this.equipoGanador);
        return hash;
    }

    /**
     * @return the reglas
     */
    public String getReglas() {
        return reglas;
    }

    /**
     * @param reglas the reglas to set
     */
    public void setReglas(String reglas) {
        this.reglas = reglas;
    }

    /**
     * @return the restricciones
     */
    public String getRestricciones() {
        return restricciones;
    }

    /**
     * @param restricciones the restricciones to set
     */
    public void setRestricciones(String restricciones) {
        this.restricciones = restricciones;
    }

    /**
     * @return the inscritos
     */
    public List<UsuarioEntity> getInscritos() {
        return inscritos;
    }

    /**
     * @param inscritos the inscritos to set
     */
    public void setInscritos(List<UsuarioEntity> inscritos) {
        this.inscritos = inscritos;
    }

    /**
     * @return the equipos
     */
    public List<EquipoEntity> getEquipos() {
        return equipos;
    }

    /**
     * @param equipos the equipos to set
     */
    public void setEquipos(List<EquipoEntity> equipos) {
        this.equipos = equipos;
    }

    /**
     * @return the limiteParticipantes
     */
    public Integer getLimiteParticipantes() {
        return limiteParticipantes;
    }

    /**
     * @param limiteParticipantes the limiteParticipantes to set
     */
    public void setLimiteParticipantes(Integer limiteParticipantes) {
        this.limiteParticipantes = limiteParticipantes;
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
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
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
    public String getPremio() {
        return premio;
    }

    /**
     * @param premio the premio to set
     */
    public void setPremio(String premio) {
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

    /**
     * @return the iniciada
     */
    public Boolean getIniciada() {
        return iniciada;
    }

    /**
     * @param iniciada the iniciada to set
     */
    public void setIniciada(Boolean iniciada) {
        this.iniciada = iniciada;
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
     * @return the equipoGanador
     */
    public EquipoEntity getEquipoGanador() {
        return equipoGanador;
    }

    /**
     * @param equipoGanador the equipoGanador to set
     */
    public void setEquipoGanador(EquipoEntity equipoGanador) {
        this.equipoGanador = equipoGanador;
    }

    /**
     * @return the tecnologias
     */
    public List<TecnologiaEntity> getTecnologias() {
        return tecnologias;
    }

    /**
     * @param tecnologias the tecnologias to set
     */
    public void setTecnologias(List<TecnologiaEntity> tecnologias) {
        this.tecnologias = tecnologias;
    }

    
}
