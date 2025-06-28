package br.com.testefullstack.testefullstack.service;

import br.com.testefullstack.testefullstack.entities.Usuario;
import br.com.testefullstack.testefullstack.repository.UsuarioRepository;
import br.com.testefullstack.testefullstack.validator.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;
    private final UsuarioValidator validator;

    public void salvar(Usuario usuario) {
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        repository.save(usuario);
    }

    public Usuario obterPorLogin(String login) {
        return repository.findByLogin(login);
    }

    public Optional<Usuario> obterPorId(Integer id){
        return repository.findById(id);
    }

    public Usuario atualizar(Usuario usuario){
        if(usuario.getId() == null){
            throw new IllegalArgumentException("Para atualizar, é necessário que o usuario já esteja salvo na base.");
        }
       validator.validar(usuario);
        return repository.save(usuario);
    }
}
