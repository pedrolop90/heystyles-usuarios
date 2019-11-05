package com.heystyles.usuarios.core.domain;

import com.heystyles.common.types.DomainBean;

public class ProveedorPersona extends DomainBean<Long> {

    private Long id;

    private Persona persona;

    private Long proveedorId;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Long getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Long proveedorId) {
        this.proveedorId = proveedorId;
    }
}
