package br.com.testefullstack.testefullstack.controller.mapper;

import br.com.testefullstack.testefullstack.controller.dto.UsuarioDTO;
import br.com.testefullstack.testefullstack.entities.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);
}
