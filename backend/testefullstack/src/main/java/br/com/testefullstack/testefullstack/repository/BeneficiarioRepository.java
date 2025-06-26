package br.com.testefullstack.testefullstack.repository;

import br.com.testefullstack.testefullstack.entities.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeneficiarioRepository extends JpaRepository<Beneficiario, UUID> {
}
