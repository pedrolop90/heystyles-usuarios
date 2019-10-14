package com.heystyles.usuarios.api.service;

import com.heystyles.usuarios.core.domain.Usuario;

public interface UsuarioService extends PersonableService<Usuario, Long> {

    Usuario getUsuario(Long usuarioId);

}
