package br.com.testefullstack.testefullstack.service;

import br.com.testefullstack.testefullstack.entities.Beneficiario;
import br.com.testefullstack.testefullstack.entities.Plano;
import br.com.testefullstack.testefullstack.exceptions.PlanoNaoEncontradoException;
import br.com.testefullstack.testefullstack.exceptions.PlanoObrigatorioException;
import br.com.testefullstack.testefullstack.repository.BeneficiarioRepository;
import br.com.testefullstack.testefullstack.repository.PlanoRepository;
import br.com.testefullstack.testefullstack.repository.specification.BeneficiarioSpecification;
import br.com.testefullstack.testefullstack.validator.BeneficiarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BeneficiarioService {

    private final BeneficiarioRepository repository;
    private final BeneficiarioValidator validator;
    private final PlanoRepository planoRepository;

    public Beneficiario salvar(Beneficiario beneficiario) {
        Integer idPlano = beneficiario.getPlano() != null ? beneficiario.getPlano().getId() : null;
        if (idPlano == null) {
            throw new PlanoObrigatorioException("ID do plano é obrigatório.");
        }

        Plano plano = planoRepository.findById(idPlano)
                .orElseThrow(() -> new PlanoNaoEncontradoException("Plano com ID " + idPlano + " não encontrado."));

        beneficiario.setPlano(plano);
        validator.validar(beneficiario);
        return repository.save(beneficiario);
    }


    public Optional<Beneficiario> obterPorId(Integer id){
        return repository.findById(id);
    }

    public void deletar(Beneficiario beneficiario){

        repository.delete(beneficiario);
    }

    public Page<Beneficiario> pesquisar(
            String nome,
            String cpf,
            String email,
            Integer idade,
            Integer idPlano,
            int pagina,
            int tamanho) {

        Specification<Beneficiario> specs = Specification.where(null);

        if (nome != null) {
            specs = specs.and(BeneficiarioSpecification.nomeLike(nome));
        }

        if (cpf != null) {
            specs = specs.and(BeneficiarioSpecification.cpfEqual(cpf));
        }

        if (email != null) {
            specs = specs.and(BeneficiarioSpecification.emailLike(email));
        }

        if (idade != null) {
            specs = specs.and(BeneficiarioSpecification.idadeEqual(idade));
        }

        if (idPlano != null) {
            specs = specs.and(BeneficiarioSpecification.planoIdEqual(idPlano));
        }

        Pageable pageable = PageRequest.of(pagina, tamanho);
        return repository.findAll(pageable);
    }

    public Beneficiario atualizar(Beneficiario beneficiario){
        if(beneficiario.getId() == null){
            throw new IllegalArgumentException("Para atualizar, é necessário que o beneficiario já esteja salvo na base.");
        }
        validator.validar(beneficiario);
        return repository.save(beneficiario);
    }
}

