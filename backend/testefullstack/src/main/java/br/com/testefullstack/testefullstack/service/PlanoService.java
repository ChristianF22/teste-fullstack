package br.com.testefullstack.testefullstack.service;

import br.com.testefullstack.testefullstack.entities.Plano;
import br.com.testefullstack.testefullstack.repository.BeneficiarioRepository;
import br.com.testefullstack.testefullstack.repository.PlanoRepository;
import br.com.testefullstack.testefullstack.validator.PlanoValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.testefullstack.testefullstack.repository.specification.PlanoSpecification.nomeLike;

@Service
@RequiredArgsConstructor
public class PlanoService {

    private final PlanoRepository repository;
    private final PlanoValidator validator;
    private final BeneficiarioRepository beneficiarioRepository;

    public Plano salvar(Plano plano) {
        validator.validar(plano);
        return repository.save(plano);

    }

    public Optional<Plano> obterPorid (Integer id){
        return repository.findById(id);
    }

    public void deletar(Integer idPlano){
        boolean possuiBeneficiariosNessePlano = beneficiarioRepository.existsByPlanoId(idPlano);
        if(possuiBeneficiariosNessePlano){
            throw new IllegalStateException("Não é possível deletar o plano pois existem beneficiários vinculados.");
        }
        repository.deleteById(idPlano);
    }

    public Page<Plano> pesquisa(String nome, Integer pagina,Integer tamanhoPagina){
        Specification<Plano> specs = Specification.where((root,query,cb) -> cb.conjunction());
        if(nome != null){
            specs = specs.and(nomeLike(nome));
        }
        Pageable pageable = PageRequest.of(pagina, tamanhoPagina);
        return repository.findAll(pageable);
    }

    public Plano atualizar(Plano plano) {
        if (plano.getId() == null) {
            throw new IllegalArgumentException("Para atualizar, é necessário que o plano já esteja salvo na base.");
        }
        validator.validar(plano);
        return repository.save(plano);
    }
}
