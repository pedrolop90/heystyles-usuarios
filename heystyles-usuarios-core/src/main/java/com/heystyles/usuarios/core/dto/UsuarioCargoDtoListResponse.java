package com.heystyles.usuarios.core.dto;

import com.heystyles.common.types.ListResponse;

import java.util.List;

public class UsuarioCargoDtoListResponse extends ListResponse<UsuarioCargoDto> {

    public UsuarioCargoDtoListResponse() {
    }

    public UsuarioCargoDtoListResponse(List<UsuarioCargoDto> usuarioCargoDtos) {
        super(usuarioCargoDtos);
    }

    public UsuarioCargoDtoListResponse(Long count, List<UsuarioCargoDto> usuarioCargoDtos) {
        super(usuarioCargoDtos, count);
    }
}
