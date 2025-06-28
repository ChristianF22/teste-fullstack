package br.com.testefullstack.testefullstack.validator;

import br.com.testefullstack.testefullstack.entities.Usuario;
import br.com.testefullstack.testefullstack.exceptions.RegistroDuplicadoException;
import br.com.testefullstack.testefullstack.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioValidator {

    private final UsuarioRepository repository;

    public UsuarioValidator(UsuarioRepository repository){
        this.repository = repository;
    }

    public void validar(Usuario usuario){
        Optional<Usuario> existente = repository.findById(
                usuario.getId()
        );

        if (existente.isPresent()){
            boolean duplicado = usuario.getId() == null ||
                    !usuario.getId().equals(existente.get().getId());
            if(duplicado){
                throw new RegistroDuplicadoException("Usuario já cadastrado: " + usuario.getId());
            }
        }
    }
}
