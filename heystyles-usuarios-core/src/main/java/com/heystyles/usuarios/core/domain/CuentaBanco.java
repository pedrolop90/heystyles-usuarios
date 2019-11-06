package com.heystyles.usuarios.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.heystyles.common.types.DomainBean;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CuentaBanco extends DomainBean<Long> {

    private Long id;

    private Long proveedorId;

    @NotBlank
    private String nombreBanco;

    @NotNull
    private TipoCuentaBanco tipoCuenta;

    @NotBlank
    private String numeroCuenta;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Long proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public TipoCuentaBanco getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuentaBanco tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }


    @Override
    public boolean equals(Object obj) {
        CuentaBanco cuentaBanco = (CuentaBanco) obj;
        if(cuentaBanco.getId() == this.id){
            return true;
        }
        return false;
    }
}
