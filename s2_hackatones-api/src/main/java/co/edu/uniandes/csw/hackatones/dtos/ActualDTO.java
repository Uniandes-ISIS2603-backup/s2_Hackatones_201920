/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.ActualEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * ActualDTO Objeto de transferencia de datos de Hackatones. Los DTO contienen
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

public class ActualDTO extends HackatonDetailDTO implements Serializable{
    
    private String reglas;
    private String restricciones;
    
    
     public ActualDTO() {
    }
    
    /**
     * Constructor a partir de la entidad
     *
     * @param actualEntity La entidad del libro
     */
    public ActualDTO(ActualEntity actualEntity) {
        super(actualEntity);
        if (actualEntity != null) {
            this.reglas = actualEntity.getReglas();
            this.restricciones = actualEntity.getRestricciones();
        }
    }
    
    /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad de la actual asociado.
     */
    public ActualEntity toEntity() {
        ActualEntity actualEntity = new ActualEntity();
        actualEntity.setId(this.getId());
        actualEntity.setReglas(this.getReglas());
        actualEntity.setRestricciones(this.getRestricciones());
        return actualEntity;
    }

    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
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