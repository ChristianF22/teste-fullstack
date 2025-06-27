package br.com.testefullstack.testefullstack.controller.mapper;

import br.com.testefullstack.testefullstack.controller.dto.PlanoDTO;
import br.com.testefullstack.testefullstack.entities.Plano;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlanoMapper {

    Plano toEntity(PlanoDTO dto);

    PlanoDTO toDTO(Plano plano);
}
