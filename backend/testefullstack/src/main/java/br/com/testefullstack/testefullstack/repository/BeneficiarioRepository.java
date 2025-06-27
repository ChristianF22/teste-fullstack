package br.com.testefullstack.testefullstack.repository;

import br.com.testefullstack.testefullstack.entities.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Integer> {
    boolean existsByPlanoId(Integer planoId);

    Optional<Beneficiario> findByCpf(String cpf);

    Optional<Beneficiario> findByNomeAndCpfAndEmailAndIdade (String nome,String cpf,String email, Integer idade);
}
