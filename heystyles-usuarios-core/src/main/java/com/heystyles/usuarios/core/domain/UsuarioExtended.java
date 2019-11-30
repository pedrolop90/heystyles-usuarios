package com.heystyles.usuarios.core.domain;


import com.heystyles.file.core.domain.File;

public class UsuarioExtended {

    private Usuario usuario;

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
