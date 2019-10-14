package com.heystyles.usuarios.api.controller;

import com.heystyles.common.response.Responses;
import com.heystyles.common.types.BaseResponse;
import com.heystyles.common.types.IdResponse;
import com.heystyles.usuarios.api.service.UsuarioService;
import com.heystyles.usuarios.core.domain.Usuario;
import com.heystyles.usuarios.core.dto.UsuarioListResponse;
import com.heystyles.usuarios.core.dto.UsuarioResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping(value = "/usuarios")
@RestController
@Api(value = "Usuarios Controller",
        description = "Controller para el manejo de Usuarios.")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @ApiOperation(value = "Permite Crear un Usuario en la base de datos.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuario Creado.")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdResponse> insert(
            @NotNull @Valid @RequestBody Usuario usuario) {
        Long idUsuario = usuarioService.insert(usuario);
        return Responses.responseEntity(new IdResponse(idUsuario));
    }

    @ApiOperation(value = "Permite actualizar un Usuario en la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuario Actualizado."),
            @ApiResponse(code = 404, message = "Usuario no encontrado.")
    })
    @PutMapping(value = "/{usuarioId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> update(
            @NotNull @PathVariable(name = "usuarioId") Long usuarioId,
            @NotNull @Valid @RequestBody Usuario usuario) {
        usuarioService.update(usuarioId, usuario);
        return Responses.successEntity("Actualizacion correcta");
    }

    @ApiOperation(value = "Permite Eliminar un Usuario de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuario Eliminado."),
            @ApiResponse(code = 404, message = "Usuario no encontrado.")
    })
    @DeleteMapping(value = "/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> delete(
            @NotNull @PathVariable(name = "usuarioId") Long usuarioId) {
        usuarioService.delete(usuarioId);
        return Responses.successEntity("Eliminado correcto");
    }

    @ApiOperation(value = "Permite Buscar un Usuario en la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuario Encontrado."),
            @ApiResponse(code = 404, message = "Usuario no encontrado.")
    })
    @GetMapping(value = "/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioResponse> getUsuario(
            @NotNull @PathVariable(name = "usuarioId") Long usuarioId) {
        Usuario usuario = usuarioService.getUsuario(usuarioId);
        return Responses.responseEntity(new UsuarioResponse(usuario));
    }

    @ApiOperation(value = "Permite Listar todos los Usuarios de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuarios Encontrados."),
            @ApiResponse(code = 404, message = "Usuarios no encontrados.")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioListResponse> getUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();
        return Responses.responseEntity(new UsuarioListResponse(usuarios));
    }
}
