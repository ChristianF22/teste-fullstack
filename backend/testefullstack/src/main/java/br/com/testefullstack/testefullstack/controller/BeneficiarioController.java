package br.com.testefullstack.testefullstack.controller;

import br.com.testefullstack.testefullstack.controller.dto.BeneficiarioDTO;
import br.com.testefullstack.testefullstack.controller.mapper.BeneficiarioMapper;
import br.com.testefullstack.testefullstack.entities.Beneficiario;
import br.com.testefullstack.testefullstack.entities.Plano;

import br.com.testefullstack.testefullstack.exceptions.PlanoObrigatorioException;
import br.com.testefullstack.testefullstack.service.BeneficiarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("beneficiario")
@RequiredArgsConstructor
public class BeneficiarioController implements GenericController{

    private final BeneficiarioService service;
    private final BeneficiarioMapper mapper;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE')")
    public ResponseEntity<Void> salvarBeneficiario(@RequestBody @Valid BeneficiarioDTO dto){
        Beneficiario beneficiario = mapper.toEntity(dto);
        service.salvar(beneficiario);
        URI localtion = gerarHeaderLocation(beneficiario.getId());
        return ResponseEntity.created(localtion).build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE')")
    public ResponseEntity<BeneficiarioDTO> obterdetalherBeneficiario(@PathVariable("id") String id){
        Integer idBeneficiario = Integer.parseInt(id);
        return service
                .obterPorId(idBeneficiario)
                .map(beneficiario -> {
                    BeneficiarioDTO dto = mapper.toDTO(beneficiario);
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deletarBeneficiario(@PathVariable("id") String id){
        return service.obterPorId(Integer.parseInt(id))
                .map(beneficiario -> {
                    service.deletar(beneficiario);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN','ATENDENTE')")
    public ResponseEntity<Page<BeneficiarioDTO>> pesquisarBeneficiarios(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Integer idade,
            @RequestParam(required = false) Integer idPlano,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanhoPagina
    ) {
        Page<Beneficiario> resultado = service.pesquisar(nome, cpf, email, idade, idPlano, pagina, tamanhoPagina);
        Page<BeneficiarioDTO> dtoPage = resultado.map(mapper::toDTO);
        return ResponseEntity.ok(dtoPage);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE')")
    public ResponseEntity<Void> atualizarBeneficiario(
            @PathVariable("id") Integer id,
            @RequestBody @Valid BeneficiarioDTO dto
    ) {
        Beneficiario beneficiarioAtualizado = mapper.toEntity(dto);
        beneficiarioAtualizado.setId(id);

        if (dto.idPlano() == null) {
            throw new PlanoObrigatorioException("ID do plano é obrigatório.");
        }

        Plano plano = new Plano();
        plano.setId(dto.idPlano());
        beneficiarioAtualizado.setPlano(plano);
        service.atualizar(beneficiarioAtualizado);
        return ResponseEntity.noContent().build();
    }

}
