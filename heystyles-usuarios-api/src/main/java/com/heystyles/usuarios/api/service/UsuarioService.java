package com.heystyles.usuarios.api.service;

import com.heystyles.usuarios.core.domain.Usuario;
import com.heystyles.usuarios.core.domain.UsuarioExtended;
import com.heystyles.usuarios.core.dto.UsuarioRequest;

public interface UsuarioService extends PersonableService<Usuario, Long> {

    Long insert(UsuarioRequest request);

    void update(UsuarioRequest request);

    Usuario getUsuario(Long usuarioId);

    Usuario getUsuarioByNumeroDocumento(String numeroDocumento);

    UsuarioExtended getUsuarioExtended(Long usuarioId);
}
