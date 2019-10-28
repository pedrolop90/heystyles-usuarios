package com.heystyles.usuarios.core.dto;

import com.heystyles.usuarios.core.domain.Cargo;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CargoRequest {

    @NotNull
    private Cargo cargo;

    @NotNull
    private List<Long> permisos;

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public List<Long> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<Long> permisos) {
        this.permisos = permisos;
    }
}
