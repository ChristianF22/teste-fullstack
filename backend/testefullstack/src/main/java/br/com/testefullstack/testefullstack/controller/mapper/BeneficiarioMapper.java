package br.com.testefullstack.testefullstack.controller.mapper;

import br.com.testefullstack.testefullstack.controller.dto.BeneficiarioDTO;
import br.com.testefullstack.testefullstack.entities.Beneficiario;
import br.com.testefullstack.testefullstack.entities.Plano;
import br.com.testefullstack.testefullstack.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BeneficiarioMapper {

    @Mapping(target = "plano", expression = "java(dto.idPlano() != null ? mapPlano(dto.idPlano()) : null)")
    @Mapping(target = "usuario", expression = "java(dto.idUsuario() != null ? mapUsuario(dto.idUsuario()) : null)")
    Beneficiario toEntity(BeneficiarioDTO dto);

    @Mapping(target = "idPlano", source = "plano.id")
    @Mapping(target = "idUsuario", source = "usuario.id")
    BeneficiarioDTO toDTO(Beneficiario beneficiario);

    default Plano mapPlano(Integer id) {
        if (id == null) return null;
        Plano plano = new Plano();
        plano.setId(id);
        return plano;
    }

    default Usuario mapUsuario(Integer id) {
        if (id == null) return null;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return usuario;
    }
}
