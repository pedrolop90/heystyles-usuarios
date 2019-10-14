package com.heystyles.usuarios.core.dto;

import com.heystyles.common.types.ObjectResponse;
import com.heystyles.usuarios.core.domain.Usuario;

public class UsuarioResponse extends ObjectResponse<Usuario> {

    public UsuarioResponse() {
    }

    public UsuarioResponse(Usuario usuario) {
        super(usuario);
    }
}
