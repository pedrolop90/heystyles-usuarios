package com.heystyles.usuarios.core.dto;

import com.heystyles.usuarios.core.domain.CuentaBanco;
import com.heystyles.usuarios.core.domain.Persona;
import com.heystyles.usuarios.core.domain.Proveedor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ProveedorRequest {

    @NotNull
    private Proveedor proveedor;

    @NotNull
    private List<Persona> contactos;

    @NotNull
    @Size(min = 1)
    private List<CuentaBanco> cuentasBancos;


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

    public List<CuentaBanco> getCuentasBancos() {
        return cuentasBancos;
    }

    public void setCuentasBancos(List<CuentaBanco> cuentasBancos) {
        this.cuentasBancos = cuentasBancos;
    }
}
