/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.EquipoEntity;
import co.edu.uniandes.csw.hackatones.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author ja.torresl
 */
public class EquipoDetailDTO extends EquipoDTO implements Serializable{
    
    // relación  cero o muchos libros
    private List<UsuarioDTO> participantes;

    public EquipoDetailDTO() {
        super();
    }

    /**
     * Crea un objeto EquipoDetailDTO a partir de un objeto EquipoEntity
     * incluyendo los atributos de EquipoDTO.
     *
     * @param equipoEntity Entidad EquipoEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public EquipoDetailDTO(EquipoEntity equipoEntity) {
        super(equipoEntity);
        if (equipoEntity != null) {
            participantes = new ArrayList<>();
            for (UsuarioEntity entityUsuarios : equipoEntity.getParticipantes()) {
                participantes.add(new UsuarioDTO(entityUsuarios));
            }
        }
    }

    /**
     * Convierte un objeto EquipoDetailDTO a EquipoEntity incluyendo los
     * atributos de EquipoDTO.
     *
     * @return Nueva objeto EquipoEntity.
     *
     */
    @Override
    public EquipoEntity toEntity() {
        EquipoEntity authorEntity = super.toEntity();
        if (participantes != null) {
            List<UsuarioEntity> participantesEntity = new ArrayList<>();
            for (UsuarioDTO dtoUsuario : participantes) {
                participantesEntity.add(dtoUsuario.toEntity());
            }
            authorEntity.setParticipantes(participantesEntity);
        }
     
        return authorEntity;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

     /**
     * Modifica la lista de libros para el autor
     * @param participantes the books to set
     */
    public void setParticipantes(List<UsuarioDTO> participantes) {
        this.participantes = participantes;
    }

  

    
    
    /**
     * Obtiene la lista de participantes
     *
     * @return the participantes
     */
    public List<UsuarioDTO> getParticipantes() {
        return participantes;
    }

   
}
