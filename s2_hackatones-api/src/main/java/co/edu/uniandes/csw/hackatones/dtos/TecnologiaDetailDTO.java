/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.dtos;

import co.edu.uniandes.csw.hackatones.entities.UsuarioEntity;
import co.edu.uniandes.csw.hackatones.entities.TecnologiaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author ja.torresl
 */
public class TecnologiaDetailDTO  extends  TecnologiaDTO implements Serializable{
    
    
    // relación  cero o muchos libros
    private List<UsuarioDTO> books;

    public TecnologiaDetailDTO() {
        super();
    }

    /**
     * Crea un objeto TecnologiaDetailDTO a partir de un objeto TecnologiaEntity
     * incluyendo los atributos de TecnologiaDTO.
     *
     * @param tecnologiaEntity Entidad TecnologiaEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public TecnologiaDetailDTO(TecnologiaEntity tecnologiaEntity) {
        super(tecnologiaEntity);
        if (tecnologiaEntity != null) {
            books = new ArrayList<>();
            for (UsuarioEntity entityUsuarios : tecnologiaEntity.getParticipantes()) {
                books.add(new UsuarioDTO(entityUsuarios));
            }
        }
    }

    /**
     * Convierte un objeto TecnologiaDetailDTO a TecnologiaEntity incluyendo los
     * atributos de TecnologiaDTO.
     *
     * @return Nueva objeto TecnologiaEntity.
     *
     */
    @Override
    public TecnologiaEntity toEntity() {
        TecnologiaEntity authorEntity = super.toEntity();
        if (books != null) {
            List<UsuarioEntity> participantesEntity = new ArrayList<>();
            for (UsuarioDTO dtoUsuario : books) {
                participantesEntity.add(dtoUsuario.toEntity());
            }
            authorEntity.setParticipantes(participantesEntity);
        }
     
        return authorEntity;
    }

    /**
     * Obtiene la lista de libros del autor
     *
     * @return the books
     */
    public List<UsuarioDTO> getUsuarios() {
        return books;
    }

    /**
     * Modifica la lista de libros para el autor
     *
     * @param books the books to set
     */
    public void setUsuarios(List<UsuarioDTO> books) {
        this.books = books;
    }

  

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
