package com.heystyles.usuarios.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import domain.PermisoAuth0;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CargoExtended extends Cargo {

    private List<PermisoAuth0> permisos;

    public List<PermisoAuth0> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<PermisoAuth0> permisos) {
        this.permisos = permisos;
    }
}
