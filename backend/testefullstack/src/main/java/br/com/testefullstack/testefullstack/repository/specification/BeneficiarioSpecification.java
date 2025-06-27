package br.com.testefullstack.testefullstack.repository.specification;

import br.com.testefullstack.testefullstack.entities.Beneficiario;
import org.springframework.data.jpa.domain.Specification;

public class BeneficiarioSpecification {

    public static Specification<Beneficiario> nomeLike(String nome) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }

    public static Specification<Beneficiario> cpfEqual(String cpf) {
        return (root, query, cb) ->
                cb.equal(root.get("cpf"), cpf);
    }

    public static Specification<Beneficiario> emailLike(String email) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%");
    }

    public static Specification<Beneficiario> idadeEqual(Integer idade) {
        return (root, query, cb) ->
                cb.equal(root.get("idade"), idade);
    }

    public static Specification<Beneficiario> planoIdEqual(Integer idPlano) {
        return (root, query, cb) ->
                cb.equal(root.get("plano").get("id"), idPlano);
    }
}
