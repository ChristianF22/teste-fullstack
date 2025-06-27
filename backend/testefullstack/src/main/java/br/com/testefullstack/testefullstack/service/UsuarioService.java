package br.com.testefullstack.testefullstack.service;

import br.com.testefullstack.testefullstack.entities.Usuario;
import br.com.testefullstack.testefullstack.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

    public void salvar(Usuario usuario) {
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        repository.save(usuario);
    }

    public Usuario obterPorLogin(String login) {
        return repository.findByLogin(login);
    }
}
