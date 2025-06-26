package br.com.testefullstack.testefullstack.repository;

import br.com.testefullstack.testefullstack.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
}
