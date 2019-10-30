package com.heystyles.usuarios.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import domain.PermisoAuth0Extended;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CargoExtended extends Cargo {

    private List<PermisoAuth0Extended> permisos;

    public List<PermisoAuth0Extended> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<PermisoAuth0Extended> permisos) {
        this.permisos = permisos;
    }
}
