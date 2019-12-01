package com.heystyles.usuarios.api.service;

import com.heystyles.usuarios.core.domain.Usuario;
import com.heystyles.usuarios.core.domain.UsuarioExtended;

import java.util.List;

public interface UsuarioService extends PersonableService<Usuario, Long> {

    Long insert(UsuarioExtended request);

    void update(UsuarioExtended request);

    Usuario getUsuario(Long usuarioId);

    Usuario getUsuarioByNumeroDocumento(String numeroDocumento);

    UsuarioExtended getUsuarioExtended(String numeroDocumento);

    List<Usuario> getUsuarios();
}
