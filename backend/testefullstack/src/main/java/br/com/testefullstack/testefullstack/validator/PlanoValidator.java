package br.com.testefullstack.testefullstack.validator;

import br.com.testefullstack.testefullstack.entities.Plano;
import br.com.testefullstack.testefullstack.exceptions.RegistroDuplicadoException;
import br.com.testefullstack.testefullstack.repository.PlanoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PlanoValidator {

    private PlanoRepository repository;

    public PlanoValidator(PlanoRepository repository){
        this.repository = repository;
    }

    public void validar(Plano plano){
        if(existePlanoCadastrado(plano)){
            throw new RegistroDuplicadoException("Plano já cadastrado!");
        }
    }

    private boolean existePlanoCadastrado(Plano plano) {
        Optional<Plano> planoEncontrado = repository.findByNomeAndIdNot(plano.getNome(),plano.getId());

        if (plano.getId() == null) {
            return planoEncontrado.isPresent();
        }

        return planoEncontrado
                .filter(p -> !p.getId().equals(plano.getId()))
                .isPresent();
    }
}
