package br.com.testefullstack.testefullstack.repository;

import br.com.testefullstack.testefullstack.entities.Plano;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlanoRepository extends JpaRepository<Plano, UUID> {
}
