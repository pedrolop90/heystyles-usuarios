package com.heystyles.usuarios.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProveedorExtended extends Proveedor {

    private List<Persona> contactos;

    private List<CuentaBanco> cuentasBanco;

    public List<Persona> getContactos() {
        return contactos;
    }

    public void setContactos(List<Persona> contactos) {
        this.contactos = contactos;
    }

    public List<CuentaBanco> getCuentasBanco() {
        return cuentasBanco;
    }

    public void setCuentasBanco(List<CuentaBanco> cuentasBanco) {
        this.cuentasBanco = cuentasBanco;
    }
}
