package br.com.testefullstack.testefullstack.security;

import br.com.testefullstack.testefullstack.entities.Usuario;
import br.com.testefullstack.testefullstack.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDatailsService implements UserDetailsService {

    private final UsuarioService service;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = service.obterPorLogin(login);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + login);
        }

        return User.builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(usuario.getRoles().toArray(new String[0]))
                .build();
    }
}
