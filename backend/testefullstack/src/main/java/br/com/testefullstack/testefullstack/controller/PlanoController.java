package br.com.testefullstack.testefullstack.controller;

import br.com.testefullstack.testefullstack.controller.dto.PlanoDTO;
import br.com.testefullstack.testefullstack.controller.mapper.PlanoMapper;
import br.com.testefullstack.testefullstack.entities.Plano;
import br.com.testefullstack.testefullstack.service.PlanoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;



@RestController
@RequestMapping("plano")
@RequiredArgsConstructor
public class PlanoController implements GenericController{

    private final PlanoService service;
    private final PlanoMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvarPLano(@RequestBody @Valid PlanoDTO dto){
        Plano plano = mapper.toEntity(dto);
        service.salvar(plano);
        URI localtion = gerarHeaderLocation(plano.getId());
        return ResponseEntity.created(localtion).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoDTO> obeterdetalhePlano(@PathVariable("id") String id) {
        Integer idPlano = Integer.parseInt(id);
        return service
                .obterPorid(idPlano)
                .map(plano -> {
                    PlanoDTO dto = mapper.toDTO(plano);
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletarPlano(@PathVariable("id") String id){
        var idPlano = Integer.parseInt(id);
        Optional<Plano> planoOptional = service.obterPorid(idPlano);
        if(planoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        service.deletar(idPlano);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<PlanoDTO>> pesquisarPlano(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanhoPagina", defaultValue = "10") Integer tamanhoPagina) {
        Page<Plano> paginaResultado = service.pesquisa(nome, pagina, tamanhoPagina);
        Page<PlanoDTO> dtoPage = paginaResultado.map(mapper::toDTO);
        return ResponseEntity.ok(dtoPage);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizarPlano(@PathVariable("id") String id, @RequestBody @Valid PlanoDTO dto){
        var idPlano = Integer.parseInt(id);
        Optional<Plano> planoOptional = service.obterPorid(idPlano);
        if(planoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var plano = planoOptional.get();
        plano.setNome(dto.nome());
        plano.setValor(dto.valor());

        service.atualizar(plano);
        return ResponseEntity.noContent().build();
    }
}
