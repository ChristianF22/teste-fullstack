package br.com.testefullstack.testefullstack.repository;

import br.com.testefullstack.testefullstack.entities.Plano;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PlanoRepository extends JpaRepository<Plano, Integer> {

    Optional<Plano> findByNomeAndIdNot(String nome, Integer id);
}
