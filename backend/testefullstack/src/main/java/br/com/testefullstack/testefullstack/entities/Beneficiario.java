package br.com.testefullstack.testefullstack.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Table(name = "beneficiario")
@Data
public class Beneficiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 14, nullable = false, unique = true)
    private String cpf;

    @Column(length = 100)
    private String email;

    private Integer idade;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_plano", nullable = false, foreignKey = @ForeignKey(name = "fk_beneficiario_plano"))
    private Plano plano;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @ManyToOne
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "fk_beneficiario_usuario"))
    private Usuario usuario;
}