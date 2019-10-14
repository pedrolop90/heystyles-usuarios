package com.heystyles.usuarios.core.dto;

import com.heystyles.common.types.ListResponse;
import com.heystyles.usuarios.core.domain.Usuario;

import java.util.List;

public class UsuarioListResponse extends ListResponse<Usuario> {

    public UsuarioListResponse() {
    }

    public UsuarioListResponse(List<Usuario> usuarios) {
        super(usuarios);
    }

    public UsuarioListResponse(Long count, List<Usuario> usuarios) {
        super(usuarios, count);
    }
}
