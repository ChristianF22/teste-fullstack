package br.com.testefullstack.testefullstack.controller;

import br.com.testefullstack.testefullstack.controller.dto.UsuarioDTO;
import br.com.testefullstack.testefullstack.controller.mapper.UsuarioMapper;
import br.com.testefullstack.testefullstack.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("usuario")
@RequiredArgsConstructor
public class UsuarioController implements GenericController{

    private final UsuarioService service;
    private final UsuarioMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> salvarUsuario(@RequestBody @Valid UsuarioDTO dto) {
        var usuario = mapper.toEntity(dto);
        service.salvar(usuario);
        URI location = gerarHeaderLocation(usuario.getId());
        return ResponseEntity.created(location).build();
    }
}
