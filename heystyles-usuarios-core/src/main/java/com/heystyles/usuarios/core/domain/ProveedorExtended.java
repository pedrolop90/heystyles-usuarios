package com.heystyles.usuarios.core.domain;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ProveedorExtended {

    @NotNull
    private Proveedor proveedor;

    private List<Persona> contactos;

    private List<CuentaBanco> cuentasBanco;

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

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
