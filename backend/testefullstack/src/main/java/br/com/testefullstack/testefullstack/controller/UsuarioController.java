package br.com.testefullstack.testefullstack.controller;

import br.com.testefullstack.testefullstack.controller.dto.UsuarioDTO;
import br.com.testefullstack.testefullstack.controller.mapper.UsuarioMapper;
import br.com.testefullstack.testefullstack.entities.Usuario;
import br.com.testefullstack.testefullstack.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("usuario")
@RequiredArgsConstructor
public class UsuarioController implements GenericController{

    private final UsuarioService service;
    private final UsuarioMapper mapper;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> salvarUsuario(@RequestBody @Valid UsuarioDTO dto) {
        var usuario = mapper.toEntity(dto);
        service.salvar(usuario);
        URI location = gerarHeaderLocation(usuario.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDTO> obterdetalhesDoUsuario(@PathVariable("id") String id){
        Integer idUsuario = Integer.parseInt(id);
        return service
                .obterPorId(idUsuario)
                .map(usuario -> {
                    UsuarioDTO dto = mapper.toDTO(usuario);
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> atualizarUsuario(@PathVariable("id") String id, @RequestBody @Valid UsuarioDTO dto){
        var idUsuario = Integer.parseInt(id);
        Optional<Usuario> usuarioOptional = service.obterPorId(idUsuario);
        if(usuarioOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var usuario = usuarioOptional.get();
        usuario.setNome(dto.nome());
        usuario.setRoles(dto.roles());

        service.atualizar(usuario);
        return ResponseEntity.noContent().build();
    }

}
