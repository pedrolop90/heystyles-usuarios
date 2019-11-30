package com.heystyles.usuarios.core.dto;

import com.heystyles.file.core.domain.File;
import com.heystyles.usuarios.core.domain.Usuario;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class UsuarioRequest {

    @NotNull
    @Valid
    private Usuario usuario;

    @Valid
    private File fotografia;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public File getFotografia() {
        return fotografia;
    }

    public void setFotografia(File fotografia) {
        this.fotografia = fotografia;
    }
}
