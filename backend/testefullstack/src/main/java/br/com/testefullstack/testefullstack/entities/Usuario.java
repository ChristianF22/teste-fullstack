package br.com.testefullstack.testefullstack.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 15, nullable = false)
    private String login;

    @Column(length = 500, nullable = false)
    private String senha;

    @Column(length = 60, nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = RolesConverter.class)
    private List<String> roles = new ArrayList<>();


}
