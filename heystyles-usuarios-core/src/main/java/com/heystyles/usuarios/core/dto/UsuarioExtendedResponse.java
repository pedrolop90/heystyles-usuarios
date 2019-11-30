package com.heystyles.usuarios.core.dto;

import com.heystyles.common.types.ObjectResponse;
import com.heystyles.usuarios.core.domain.UsuarioExtended;

public class UsuarioExtendedResponse extends ObjectResponse<UsuarioExtended> {

    public UsuarioExtendedResponse() {
    }

    public UsuarioExtendedResponse(UsuarioExtended usuario) {
        super(usuario);
    }
}
