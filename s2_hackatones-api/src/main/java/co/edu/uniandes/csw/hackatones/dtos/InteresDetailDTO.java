/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.InteresEntity;
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
public class InteresDetailDTO extends InteresDTO implements Serializable{
    
    // relación  cero o muchos libros
    private List<UsuarioDTO> participantes;

    public InteresDetailDTO() {
        super();
    }

    /**
     * Crea un objeto InteresDetailDTO a partir de un objeto InteresEntity
     * incluyendo los atributos de InteresDTO.
     *
     * @param interesEntity Entidad InteresEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public InteresDetailDTO(InteresEntity interesEntity) {
        super(interesEntity);
        if (interesEntity != null) {
            participantes = new ArrayList<>();
            for (UsuarioEntity entityUsuarios : interesEntity.getParticipantes()) {
                participantes.add(new UsuarioDTO(entityUsuarios));
            }
        }
    }

    /**
     * Convierte un objeto InteresDetailDTO a InteresEntity incluyendo los
     * atributos de InteresDTO.
     *
     * @return Nueva objeto InteresEntity.
     *
     */
    @Override
    public InteresEntity toEntity() {
        InteresEntity authorEntity = super.toEntity();
        if (participantes != null) {
            List<UsuarioEntity> participantesEntity = new ArrayList<>();
            for (UsuarioDTO dtoUsuario : participantes) {
                participantesEntity.add(dtoUsuario.toEntity());
            }
            authorEntity.setParticipantes(participantesEntity);
        }
     
        return authorEntity;
    }

    /**
     * Obtiene la lista de participantes
     *
     * @return the participantes
     */
    public List<UsuarioDTO> getParticipantes() {
        return participantes;
    }

    /**
     * Modifica la lista de libros para el autor
     * @param participantes the books to set
     */
    public void setParticipantes(List<UsuarioDTO> participantes) {
        this.participantes = participantes;
    }

  

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
