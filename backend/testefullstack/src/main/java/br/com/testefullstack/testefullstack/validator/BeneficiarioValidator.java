package br.com.testefullstack.testefullstack.validator;

import br.com.testefullstack.testefullstack.entities.Beneficiario;
import br.com.testefullstack.testefullstack.exceptions.RegistroDuplicadoException;
import br.com.testefullstack.testefullstack.repository.BeneficiarioRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BeneficiarioValidator {

    private final BeneficiarioRepository repository;

    public BeneficiarioValidator(BeneficiarioRepository repository) {
        this.repository = repository;
    }

    public void validar(Beneficiario beneficiario) {
        Optional<Beneficiario> existente = repository.findByNomeAndCpfAndEmailAndIdade(
                beneficiario.getNome(),
                beneficiario.getCpf(),
                beneficiario.getEmail(),
                beneficiario.getIdade()
        );

        if (existente.isPresent()) {
            boolean duplicado = beneficiario.getId() == null ||
                    !beneficiario.getId().equals(existente.get().getId());

            if (duplicado) {
                throw new RegistroDuplicadoException("CPF já cadastrado: " + beneficiario.getCpf());
            }
        }
    }
}

