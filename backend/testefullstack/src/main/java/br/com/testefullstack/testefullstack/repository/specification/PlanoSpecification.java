package br.com.testefullstack.testefullstack.repository.specification;

import br.com.testefullstack.testefullstack.entities.Plano;
import org.springframework.data.jpa.domain.Specification;

public class PlanoSpecification {

    public static Specification<Plano> nomeLike(String nome){
        return (root,query,cb) -> cb.like(cb.upper(root.get("nome")), "%" + nome.toUpperCase() + "%");
    }
}
