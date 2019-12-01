package com.heystyles.usuarios.core.dto;

import com.heystyles.usuarios.core.domain.Cargo;
import com.heystyles.usuarios.core.domain.Usuario;

public class UsuarioCargoDto {

    private Usuario usuario;

    private Cargo cargo;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
