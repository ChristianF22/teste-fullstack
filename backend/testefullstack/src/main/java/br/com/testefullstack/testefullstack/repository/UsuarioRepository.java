package br.com.testefullstack.testefullstack.repository;

import br.com.testefullstack.testefullstack.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByLogin(String login);
}
