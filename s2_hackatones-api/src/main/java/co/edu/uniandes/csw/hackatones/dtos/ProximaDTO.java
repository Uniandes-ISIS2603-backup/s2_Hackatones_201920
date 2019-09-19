/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.ProximaEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * ProximaDTO Objeto de transferencia de datos de Hackatones. Los DTO contienen
 * las representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 *   {
 *      "id": number,
 *      "reglas": string,
 *      "restricciones": string,
 *      Listas de usuarios no implementadas
 *   }

 *
 * @author a.pedraza
 */

public class ProximaDTO implements Serializable{
    
    private long id;
    private String reglas;
    private String restricciones;
    
     public ProximaDTO() {
    }
    
    /**
     * Constructor a partir de la entidad
     *
     * @param proximaEntity La entidad del libro
     */
    public ProximaDTO(ProximaEntity proximaEntity) {
        if (proximaEntity != null) {
            this.id = proximaEntity.getId();
            this.reglas = proximaEntity.getReglas();
            this.restricciones = proximaEntity.getRestricciones();
        }
    }
    
    /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad de la proxima asociado.
     */
    public ProximaEntity toEntity() {
        ProximaEntity proximaEntity = new ProximaEntity();
        proximaEntity.setId(this.getId());
        proximaEntity.setReglas(this.getReglas());
        proximaEntity.setRestricciones(this.getRestricciones());
        return proximaEntity;
    }

    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
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
    
}